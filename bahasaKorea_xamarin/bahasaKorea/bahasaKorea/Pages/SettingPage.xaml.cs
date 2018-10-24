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
    public partial class SettingPage : ContentPage
    {
        private SingleTurn.UserSetting Setting = SingleTurn.UserSetting.getInstance;
        private SingleTurn.ScreenLockUnLock Lock = SingleTurn.ScreenLockUnLock.getInstance;
        private SingleTurn.AlertMsg Alert = SingleTurn.AlertMsg.getInstance;


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

            SkinButton(SkinType);

            EnterClose.BackgroundColor = CurSkin;
            iconview.Foreground = CurSkin;

            set3.TintColor = CurSkin;
            set3.ThumbColor = CurSkin;

            set4.TintColor = CurSkin;
            set4.ThumbColor = CurSkin;

            set5.TintColor = CurSkin;
            set5.ThumbColor = CurSkin;
        }

        private void SkinButton(int SkinStyle)
        {
            lblSkin.TextColor = CurSkin;

            if (SkinStyle == 1)
            {
                f1.BackgroundColor = Color.Orange;
                f1.Opacity = 0.8;

                f2.BackgroundColor = Color.Transparent;
                f2.Opacity = 1;

                f3.BackgroundColor = Color.Transparent;
                f3.Opacity = 1;

                f4.BackgroundColor = Color.Transparent;
                f4.Opacity = 1;

                f5.BackgroundColor = Color.Transparent;
                f5.Opacity = 1;

                f6.BackgroundColor = Color.Transparent;
                f6.Opacity = 1;

                f7.BackgroundColor = Color.Transparent;
                f7.Opacity = 1;
            }
            else if (SkinStyle == 2)
            {
                f1.BackgroundColor = Color.Transparent;
                f1.Opacity = 1;

                f2.BackgroundColor = Color.Orange;
                f2.Opacity = 0.8;

                f3.BackgroundColor = Color.Transparent;
                f3.Opacity = 1;

                f4.BackgroundColor = Color.Transparent;
                f4.Opacity = 1;

                f5.BackgroundColor = Color.Transparent;
                f5.Opacity = 1;

                f6.BackgroundColor = Color.Transparent;
                f6.Opacity = 1;

                f7.BackgroundColor = Color.Transparent;
                f7.Opacity = 1;
            }
            else if (SkinStyle == 3)
            {
                f1.BackgroundColor = Color.Transparent;
                f1.Opacity = 1;

                f2.BackgroundColor = Color.Transparent;
                f2.Opacity = 1;

                f3.BackgroundColor = Color.Orange;
                f3.Opacity = 0.8;

                f4.BackgroundColor = Color.Transparent;
                f4.Opacity = 1;

                f5.BackgroundColor = Color.Transparent;
                f5.Opacity = 1;

                f6.BackgroundColor = Color.Transparent;
                f6.Opacity = 1;

                f7.BackgroundColor = Color.Transparent;
                f7.Opacity = 1;
            }
            else if (SkinStyle == 4)
            {
                f1.BackgroundColor = Color.Transparent;
                f1.Opacity = 1;

                f2.BackgroundColor = Color.Transparent;
                f2.Opacity = 1;

                f3.BackgroundColor = Color.Transparent;
                f3.Opacity = 1;

                f4.BackgroundColor = Color.Orange;
                f4.Opacity = 0.8;

                f5.BackgroundColor = Color.Transparent;
                f5.Opacity = 1;

                f6.BackgroundColor = Color.Transparent;
                f6.Opacity = 1;

                f7.BackgroundColor = Color.Transparent;
                f7.Opacity = 1;
            }
            else if (SkinStyle == 5)
            {
                f1.BackgroundColor = Color.Transparent;
                f1.Opacity = 1;

                f2.BackgroundColor = Color.Transparent;
                f2.Opacity = 1;

                f3.BackgroundColor = Color.Transparent;
                f3.Opacity = 1;

                f4.BackgroundColor = Color.Transparent;
                f4.Opacity = 1;

                f5.BackgroundColor = Color.Orange;
                f5.Opacity = 0.8;

                f6.BackgroundColor = Color.Transparent;
                f6.Opacity = 1;

                f7.BackgroundColor = Color.Transparent;
                f7.Opacity = 1;
            }
            else if (SkinStyle == 6)
            {
                f1.BackgroundColor = Color.Transparent;
                f1.Opacity = 1;

                f2.BackgroundColor = Color.Transparent;
                f2.Opacity = 1;

                f3.BackgroundColor = Color.Transparent;
                f3.Opacity = 1;

                f4.BackgroundColor = Color.Transparent;
                f4.Opacity = 1;

                f5.BackgroundColor = Color.Transparent;
                f5.Opacity = 1;

                f6.BackgroundColor = Color.Orange;
                f6.Opacity = 0.8;

                f7.BackgroundColor = Color.Transparent;
                f7.Opacity = 1;
            }
            else if (SkinStyle == 7)
            {
                f1.BackgroundColor = Color.Transparent;
                f1.Opacity = 1;

                f2.BackgroundColor = Color.Transparent;
                f2.Opacity = 1;

                f3.BackgroundColor = Color.Transparent;
                f3.Opacity = 1;

                f4.BackgroundColor = Color.Transparent;
                f4.Opacity = 1;

                f5.BackgroundColor = Color.Transparent;
                f5.Opacity = 1;

                f6.BackgroundColor = Color.Transparent;
                f6.Opacity = 1;

                f7.BackgroundColor = Color.Orange;
                f7.Opacity = 0.8;
            }
        }

        public SettingPage()
        {
            InitializeComponent();

            try
            {
                InitSkinStyle();
                InitSetOption();
            }
            catch { }
        }

        private void InitSetOption(bool bLoad = true)
        {
            if (bLoad)
            {
                //가나다라... 자동 반복 시간
                set1.Text = string.Format("{0}", Setting.AlfabetRepeatTime);

                //가나다라... 음성사용 여부
                set3.IsToggled = Setting.AlfabetRepeatSound;

                //단어장 자동 반복 시간
                set2.Text = string.Format("{0}", Setting.IngatRepeatTime);

                //단어장 음성사용 여부
                set4.IsToggled = Setting.IngatRepeatSound;

                //스크린 화면 락 처리여부
                set5.IsToggled = Setting.ScreenLock;
            }
            else
            {
                //가나다라... 자동 반복 시간
                Setting.AlfabetRepeatTime = Convert.ToInt32(set1.Text);

                //가나다라... 음성사용 여부
                Setting.AlfabetRepeatSound = set3.IsToggled;

                //단어장 자동 반복 시간
                Setting.IngatRepeatTime = Convert.ToInt32(set2.Text);

                //단어장 음성사용 여부
                Setting.IngatRepeatSound = set4.IsToggled;

                //스크린 화면 락 처리여부
                Setting.ScreenLock = set5.IsToggled;
                Lock.SetLockUnLock(set5.IsToggled);
            }
        }

        private async void EnterClose_Click(object sender, EventArgs e)
        {
            try
            {
                Device.BeginInvokeOnMainThread(() =>
                {
                    InitSetOption(false);

                    //함수에 스트링 전달
                    MessagingCenter.Send(MainPage.GetMainPageInstance(), "ChangeSetting");
                });
                await Navigation.PopModalAsync();

                /*       
                Device.BeginInvokeOnMainThread(async () =>
                {
                    InitSetOption(false);

                    //함수에 스트링 전달
                    MessagingCenter.Send(MainPage.GetMainPageInstance(), "ChangeSetting");

                    await Navigation.PopModalAsync();
                });
                */
            }
            catch { }
        }

        private void TapGestureRecognizerF1_Tapped(object sender, EventArgs e)
        {
            Setting.SkinStyle = 1;
            InitSkinStyle();
            MessagingCenter.Send(MainPage.GetMainPageInstance(), "SkinStyle");
        }

        private void TapGestureRecognizerF2_Tapped(object sender, EventArgs e)
        {
            Setting.SkinStyle = 2;
            InitSkinStyle();
            MessagingCenter.Send(MainPage.GetMainPageInstance(), "SkinStyle");
        }

        private void TapGestureRecognizerF3_Tapped(object sender, EventArgs e)
        {
            Setting.SkinStyle = 3;
            InitSkinStyle();
            MessagingCenter.Send(MainPage.GetMainPageInstance(), "SkinStyle");
        }

        private void TapGestureRecognizerF4_Tapped(object sender, EventArgs e)
        {
            Setting.SkinStyle = 4;
            InitSkinStyle();
            MessagingCenter.Send(MainPage.GetMainPageInstance(), "SkinStyle");
        }

        private void TapGestureRecognizerF5_Tapped(object sender, EventArgs e)
        {
            Setting.SkinStyle = 5;
            InitSkinStyle();
            MessagingCenter.Send(MainPage.GetMainPageInstance(), "SkinStyle");
        }

        private void TapGestureRecognizerF6_Tapped(object sender, EventArgs e)
        {
            Setting.SkinStyle = 6;
            InitSkinStyle();
            MessagingCenter.Send(MainPage.GetMainPageInstance(), "SkinStyle");
        }

        private void TapGestureRecognizerF7_Tapped(object sender, EventArgs e)
        {
            Setting.SkinStyle = 7;
            InitSkinStyle();
            MessagingCenter.Send(MainPage.GetMainPageInstance(), "SkinStyle");
        }

        protected override bool OnBackButtonPressed()
        {
            //함수에 스트링 전달
            MessagingCenter.Send(MainPage.GetMainPageInstance(), "ChangeSetting");

            return base.OnBackButtonPressed();
        }

    }
}