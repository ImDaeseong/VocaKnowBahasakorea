using bahasaKorea.Interfaces;
using bahasaKorea.Views;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace bahasaKorea
{
    public partial class MainPage : ContentPage
    {
        private SingleTurn.UserSetting Setting = SingleTurn.UserSetting.getInstance;
        private SingleTurn.SetStatusBarSkin StatusBarSkin = SingleTurn.SetStatusBarSkin.getInstance;

        HangulBaseListView HangulcontentView;


        public static MainPage instance = new MainPage();
        public static MainPage GetMainPageInstance()
        {
            if (instance == null)
            {
                return new MainPage();
            }
            return instance;
        }


        public int nSelectTabIndex;
        public int CurrentPlayIndex;


        private string sSkinType;
        private int SkinType;
        private Color CurSkin;
        private Color StatusSkin;
        private void InitSkinStyle()
        {
            SkinType = Setting.SkinStyle;

            if (SkinType == 1)
            {
                CurSkin = Color.FromHex("#33A7D6");
                StatusSkin = Color.FromHex("#0299CC");
                sSkinType = "bookA.png";
            }
            else if (SkinType == 2)
            {
                CurSkin = Color.FromHex("#493335");
                StatusSkin = Color.FromHex("#3D2B2B");
                sSkinType = "bookB.png";
            }
            else if (SkinType == 3)
            {
                CurSkin = Color.FromHex("#FF80AB");
                StatusSkin = Color.FromHex("#EA7CAA");
                sSkinType = "bookC.png";
            }
            else if (SkinType == 4)
            {
                CurSkin = Color.FromHex("#4CAF50");
                StatusSkin = Color.FromHex("#42AA49");
                sSkinType = "bookD.png";
            }
            else if (SkinType == 5)
            {
                CurSkin = Color.FromHex("#3F51B5");
                StatusSkin = Color.FromHex("#3551AF");
                sSkinType = "bookE.png";
            }
            else if (SkinType == 6)
            {
                CurSkin = Color.FromHex("#B71C1C");
                StatusSkin = Color.FromHex("#A31416");
                sSkinType = "bookF.png";
            }
            else if (SkinType == 7)
            {
                CurSkin = Color.FromHex("#37474F");
                StatusSkin = Color.FromHex("#334249");
                sSkinType = "bookG.png";
            }

            boxline.Color = CurSkin;

            tabImg1.Source = sSkinType;
            tabImg2.Source = sSkinType;
            tabImg3.Source = sSkinType;
            tabImg4.Source = sSkinType;
            tabImg5.Source = sSkinType;

            StatusBarSkin.ChangeStatusBarColor(StatusSkin);
        }


        public MainPage()
        {
            InitializeComponent();


            try
            {
                HangulcontentView = new HangulBaseListView();

                //스킨 설정
                InitSkinStyle();

                //------------ MessagingCenter 해제           

                //스킨 변경
                MessagingCenter.Unsubscribe<MainPage>(this, "SkinStyle");

                //설정이 끝나면 현재뷰 초기화
                MessagingCenter.Unsubscribe<MainPage>(this, "ChangeSetting");


                //------------ MessagingCenter 설정    

                //스킨 변경
                MessagingCenter.Subscribe<MainPage>(this, "SkinStyle", (sender) =>
                {
                    InitSkinStyle();
                });

                //설정이 끝나면 현재뷰 초기화
                MessagingCenter.Subscribe<MainPage>(this, "ChangeSetting", (sender) =>
                {
                    if (ChangeContent.Content.ToString() == "bahasaKorea.Views.HangulBaseListView")
                    {
                        /*
                        var content = new HangulBaseListView();
                        ChangeContent.Content = content;
                        SetTabTextColor(1);
                        */

                        HangulcontentView.ChangeSkin();
                        ChangeContent.Content = HangulcontentView;
                        SetTabTextColor(1);
                    }
                    else if (ChangeContent.Content.ToString() == "bahasaKorea.Views.AlfabetView")
                    {
                        var content = new AlfabetView();
                        ChangeContent.Content = content;
                        SetTabTextColor(2);
                    }
                    else if (ChangeContent.Content.ToString() == "bahasaKorea.Views.IngatView")
                    {
                        var content = new IngatView();
                        ChangeContent.Content = content;
                        SetTabTextColor(3);
                    }
                    else if (ChangeContent.Content.ToString() == "bahasaKorea.Views.KamusView")
                    {
                        var content = new KamusView();
                        ChangeContent.Content = content;
                        SetTabTextColor(4);
                    }
                });


                NavigationPage.SetHasNavigationBar(this, false);
                SetTabTextColor(4); //SetTabTextColor(1);

                //Random Twister
                //Navigation.PushModalAsync(new TongueTwisterPage(), true);
                //Navigation.PushModalAsync(new WelcomePage(), true);
            }
            catch { }
        }

        private void cancelDelegate()
        {
            //var hud = CrossHud.Current;
            //hud.Dismiss();
        }

        private async void TapGestureRecognizer_HangulBaseListView_Tapped(object sender, EventArgs e)
        {
            try
            {
                if (nSelectTabIndex == 1) return;

                App.ShowOverlay("pemuatan data");
                await Task.Factory.StartNew(() =>
                {
                    HangulcontentView.ChangeSkin();
                    ChangeContent.Content = HangulcontentView;
                    SetTabTextColor(1);
                    App.HideOverlay();
                });


                //IProgressLoading 사용
                /*
                DependencyService.Get<IProgressLoading>().Show();
                await Task.Delay(1000);

                Device.BeginInvokeOnMainThread(() => {
                    HangulcontentView.ChangeSkin();
                    ChangeContent.Content = HangulcontentView;
                    SetTabTextColor(1);
                    DependencyService.Get<IProgressLoading>().Hide();
                });
                */
                               
            }
            catch { }
        }

        private async void TapGestureRecognizer_AlfabetView_Tapped(object sender, EventArgs e)
        {
            try
            {
                if (nSelectTabIndex == 2) return;

                Device.BeginInvokeOnMainThread(() => {
                    var content = new AlfabetView();
                    ChangeContent.Content = content;
                    SetTabTextColor(2);
                });
            }
            catch { }
        }

        private async void TapGestureRecognizer_Perkataan_Tapped(object sender, EventArgs e)
        {
            try
            {
                if (nSelectTabIndex == 3) return;

                Device.BeginInvokeOnMainThread(() => {
                    var content = new IngatView();
                    ChangeContent.Content = content;
                    SetTabTextColor(3);
                });
            }
            catch { }
        }

        private async void TapGestureRecognizer_Kamus_Tapped(object sender, EventArgs e)
        {
            try
            {
                if (nSelectTabIndex == 4) return;

                Device.BeginInvokeOnMainThread(() => {
                    var content = new KamusView();
                    ChangeContent.Content = content;
                    SetTabTextColor(4);
                });
            }
            catch { }
        }

        private async void TapGestureRecognizer_Pengaturan_Tapped(object sender, EventArgs e)
        {
            try
            {
                await AnimateStackLayout(sl5);
                await Navigation.PushModalAsync(new Pages.SettingPage());
            }
            catch { }
        }

        private async Task<bool> AnimateStackLayout(StackLayout sl)
        {
            await sl.ScaleTo(0.9, 75, Easing.CubicOut);
            await sl.ScaleTo(1, 75, Easing.CubicIn);
            return true;
        }

        private void SetTabTextColor(int nIndex)
        {
            nSelectTabIndex = nIndex;

            if (nIndex == 1)
            {
                sl1.ScaleTo(1, 75);
                sl2.ScaleTo(0.9, 75);
                sl3.ScaleTo(0.9, 75);
                sl4.ScaleTo(0.9, 75);
                sl5.ScaleTo(0.9, 75);

                tab1.TextColor = Color.Orange;
                tab2.TextColor = Color.FromHex("#5A297D");
                tab3.TextColor = Color.FromHex("#5A297D");
                tab4.TextColor = Color.FromHex("#5A297D");
                tab5.TextColor = Color.FromHex("#5A297D");
            }
            else if (nIndex == 2)
            {
                sl1.ScaleTo(0.9, 75);
                sl2.ScaleTo(1, 75);
                sl3.ScaleTo(0.9, 75);
                sl4.ScaleTo(0.9, 75);
                sl5.ScaleTo(0.9, 75);

                tab1.TextColor = Color.FromHex("#5A297D");
                tab2.TextColor = Color.Orange;
                tab3.TextColor = Color.FromHex("#5A297D");
                tab4.TextColor = Color.FromHex("#5A297D");
                tab5.TextColor = Color.FromHex("#5A297D");
            }
            else if (nIndex == 3)
            {
                sl1.ScaleTo(0.9, 75);
                sl2.ScaleTo(0.9, 75);
                sl3.ScaleTo(1, 75);
                sl4.ScaleTo(0.9, 75);
                sl5.ScaleTo(0.9, 75);

                tab1.TextColor = Color.FromHex("#5A297D");
                tab2.TextColor = Color.FromHex("#5A297D");
                tab3.TextColor = Color.Orange;
                tab4.TextColor = Color.FromHex("#5A297D");
                tab5.TextColor = Color.FromHex("#5A297D");
            }
            else if (nIndex == 4)
            {
                sl1.ScaleTo(0.9, 75);
                sl2.ScaleTo(0.9, 75);
                sl3.ScaleTo(0.9, 75);
                sl4.ScaleTo(1, 75);
                sl5.ScaleTo(0.9, 75);

                tab1.TextColor = Color.FromHex("#5A297D");
                tab2.TextColor = Color.FromHex("#5A297D");
                tab3.TextColor = Color.FromHex("#5A297D");
                tab4.TextColor = Color.Orange;
                tab5.TextColor = Color.FromHex("#5A297D");
            }
            else if (nIndex == 5)
            {
                sl1.ScaleTo(0.9, 75);
                sl2.ScaleTo(0.9, 75);
                sl3.ScaleTo(0.9, 75);
                sl4.ScaleTo(0.9, 75);
                sl5.ScaleTo(1, 75);

                tab1.TextColor = Color.FromHex("#5A297D");
                tab2.TextColor = Color.FromHex("#5A297D");
                tab3.TextColor = Color.FromHex("#5A297D");
                tab4.TextColor = Color.FromHex("#5A297D");
                tab5.TextColor = Color.Orange;
            }
        }

        private async Task FlowAnimateEffect(ContentView view)
        {
            var width = Application.Current.MainPage.Width;

            var storyboard = new Animation();

            var flowRight = new Animation(callback: d => view.TranslationX = d,
                                           start: 0,
                                           end: width,
                                           easing: Easing.SpringIn);

            var flowLeft = new Animation(callback: d => view.TranslationX = d,
                                           start: -width,
                                           end: 0,
                                           easing: Easing.BounceOut);

            storyboard.Add(0, 0.5, flowRight);
            storyboard.Add(0.5, 1, flowLeft);
            storyboard.Commit(view, "Loop", length: 1400);
        }

    }
}
