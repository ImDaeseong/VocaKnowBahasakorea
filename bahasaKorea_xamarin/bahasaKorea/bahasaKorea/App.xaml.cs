using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Xamarin.Forms;

namespace bahasaKorea
{
    public partial class App : Application
    {
        private SingleTurn.ToSpeak TextToSpeech = SingleTurn.ToSpeak.getInstance;
        private SingleTurn.ScreenLockUnLock Lock = SingleTurn.ScreenLockUnLock.getInstance;
        private SingleTurn.UserSetting Setting = SingleTurn.UserSetting.getInstance;

        
        public static void ShowOverlay(string sText)
        {
            DependencyService.Get<Interfaces.IOverlayDependency>().ShowOverlay(sText);
        }
        public static void HideOverlay()
        {
            DependencyService.Get<Interfaces.IOverlayDependency>().HideOverlay();
        }
       

        private void SetLock()
        {
            Lock.SetLockUnLock(Setting.ScreenLock);
        }

        public App()
        {
            InitializeComponent();

            MainPage = new NavigationPage(new MainPage());

            //음성 초기화
            TextToSpeech.InitSpeak();
        }

        protected override void OnStart()
        {
            SetLock();
        }

        protected override void OnSleep()
        {
            // Handle when your app sleeps
        }

        protected override void OnResume()
        {
            // Handle when your app resumes
        }
    }
}
