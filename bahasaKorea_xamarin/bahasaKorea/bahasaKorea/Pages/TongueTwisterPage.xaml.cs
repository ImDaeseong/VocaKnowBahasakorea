using bahasaKorea.Interfaces;
using Plugin.Permissions;
using Plugin.Permissions.Abstractions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace bahasaKorea.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class TongueTwisterPage : ContentPage
    {
        private SingleTurn.UserSetting Setting = SingleTurn.UserSetting.getInstance;

        private bool recording = false;
        private string sFileName;


        private int rawDuration;


        bool bStartTimer;

        List<TextSpinClass> mText;
        int nPos = 0;


        private int SkinType;
        private Color CurSkin;
        private void InitSkinStyle()
        {
            SkinType = Setting.SkinStyle;

            if (SkinType == 1)
                CurSkin = Color.FromHex("#33A7D6");
            else if (SkinType == 2)
                CurSkin = Color.FromHex("#493335");
            else if (SkinType == 3)
                CurSkin = Color.FromHex("#FF80AB");
            else if (SkinType == 4)
                CurSkin = Color.FromHex("#4CAF50");
            else if (SkinType == 5)
                CurSkin = Color.FromHex("#3F51B5");
            else if (SkinType == 6)
                CurSkin = Color.FromHex("#B71C1C");
            else if (SkinType == 7)
                CurSkin = Color.FromHex("#37474F");

            EnterClose.BackgroundColor = CurSkin;
            FrameView.BackgroundColor = CurSkin;
            imgColorEx.ImageColor = CurSkin;
            framePlay.BackgroundColor = CurSkin;
        }


        public TongueTwisterPage()
        {
            InitializeComponent();

            try
            {
                InitSkinStyle();

                mText = new List<TextSpinClass>
                {
                    new TextSpinClass { sLineText = "내가 그린 기린 그림은 잘 그린 기린 그림이고 니가 그린 기린 그림은 잘 못 그린 기린 그림이다.", clrText = Color.White },
                    new TextSpinClass { sLineText = "중앙청 창살은 쌍창살이고 시청의 창살은 외창살이다.", clrText = Color.White },
                    new TextSpinClass { sLineText = "간장 공장 공장장은 강 공장장이고 된장 공장 공장장은 공 공장장이다.", clrText = Color.White }
                };

                //nPos = 0
                scrollText.Text = mText[nPos].sLineText;
                scrollText.TextColor = mText[nPos].clrText;
                nPos++;

                Device.StartTimer(TimeSpan.FromSeconds(30), () =>
                {
                    scrollText.Text = mText[nPos].sLineText;
                    scrollText.TextColor = mText[nPos].clrText;
                    nPos++;
                    if (nPos == mText.Count)
                        nPos = 0;
                    return bStartTimer;// true;
                });


                var audioAccess = Task.Run(() => checkAudioPerms()).Result;
                var storageAccess = Task.Run(() => checkStoragePerms()).Result;
                if (!(audioAccess && storageAccess))
                {
                    Navigation.PopAsync();
                }

            }
            catch { }
        }

        protected override void OnAppearing()
        {
            base.OnAppearing();

            bStartTimer = true;
        }

        protected override void OnDisappearing()
        {
            base.OnDisappearing();

            bStartTimer = false;
        }

        protected override bool OnBackButtonPressed()
        {
            return base.OnBackButtonPressed();
        }

        private async void EnterClose_Click(object sender, EventArgs e)
        {
            try
            {
                await Navigation.PopModalAsync(false);
            }
            catch { }
        }

        private void TapGestureRecognizer_Tapped(object sender, EventArgs e)
        {
            try
            {
                //연속 클릭시 오류 발생
                //ScaleEffect((sender as View));

                if (recording)
                {
                    imgColorEx.ImageColor = Color.Gray;
                    sFileName = DependencyService.Get<IAudioRecorder>().StopRecord();
                    recording = false;
                }
                else
                {
                    imgColorEx.ImageColor = Color.Red;
                    recording = true;
                    DependencyService.Get<IAudioRecorder>().StartRecord("u_" + DateTime.Now.ToString("yyyyMMddHHmmss") + ".m4a");
                }
            }
            catch { }
        }

        private void btnPlay_Click(object sender, EventArgs e)
        {
            try
            {
                //연속 클릭시 오류 발생
                //ScaleEffect((sender as View));

                DependencyService.Get<IAudioPlayer>().SetupPlayer(sFileName);

                rawDuration = DependencyService.Get<IAudioPlayer>().GetDuration();
                var mins = rawDuration / 60;
                var seconds = rawDuration % 60;
                var durString = string.Empty;

                if (seconds < 10)
                {
                    durString = string.Format("{0}:0{1}", mins, seconds);
                }
                else
                {
                    durString = string.Format("{0}:{1}", mins, seconds);
                }

                //https://github.com/digitalinteraction/ticket-to-talk-client/blob/8a51ce182eac6b0c8f937bf20c5ff816caf49218/TicketToTalk/Views/Audio/AudioPlayerLayout.cs
            }
            catch { }
        }

        private static void ScaleEffect(View view)
        {
            Device.BeginInvokeOnMainThread(async () =>
            {
                await view.ScaleTo(0.95, 100);
                await view.ScaleTo(1, 100);
            });
        }

        private static void ItemDeleteFromListAffect(View view)
        {
            Device.BeginInvokeOnMainThread(() =>
            {
                view.FadeTo(0);
                view.TranslateTo(0, 100);
            });
        }

        private static void ItemAddToListAffect(View view)
        {
            Device.BeginInvokeOnMainThread(() =>
            {
                view.FadeTo(1, 100);
                view.TranslateTo(0, 0, 100);
            });
        }

        private async Task<bool> checkAudioPerms()
        {
            try
            {
                var status = await CrossPermissions.Current.CheckPermissionStatusAsync(Permission.Microphone);
                if (status != PermissionStatus.Granted)
                {
                    if (await CrossPermissions.Current.ShouldShowRequestPermissionRationaleAsync(Permission.Microphone))
                    {
                        //await DisplayAlert("Microphone", "Ticket to Talk needs access to the microphone to record audio.", "OK");
                    }
                    var results = await CrossPermissions.Current.RequestPermissionsAsync(new[] { Permission.Microphone });
                    status = results[Permission.Microphone];
                }

                if (status == PermissionStatus.Granted)
                {
                    return true;
                }
                else if (status != PermissionStatus.Unknown)
                {
                    //await DisplayAlert("Microphone Denied", "Cannot record audio without microphone permissions.", "OK");
                    return false;
                }
            }
            catch (Exception e)
            {
                //Debug.WriteLine(e.StackTrace);
                return false;
            }

            return false;
        }

        private async Task<bool> checkStoragePerms()
        {
            try
            {
                var status = await CrossPermissions.Current.CheckPermissionStatusAsync(Permission.Storage);
                if (status != PermissionStatus.Granted)
                {
                    if (await CrossPermissions.Current.ShouldShowRequestPermissionRationaleAsync(Permission.Storage))
                    {
                        //await DisplayAlert("Storage", "Ticket to Talk needs access to storage to save tickets.", "OK");
                    }
                    var results = await CrossPermissions.Current.RequestPermissionsAsync(new[] { Permission.Storage });
                    status = results[Permission.Storage];
                }

                if (status == PermissionStatus.Granted)
                {
                    return true;
                }
                else if (status != PermissionStatus.Unknown)
                {
                    //await DisplayAlert("Storage Denied", "Cannot save tickets without access to audio.", "OK");
                    return false;
                }
            }
            catch (Exception e)
            {
                //Debug.WriteLine(e.StackTrace);
                return false;
            }

            return false;
        }



    }

    public class TextSpinClass
    {
        public string sLineText { get; set; }

        public Color clrText { get; set; }
    }
}