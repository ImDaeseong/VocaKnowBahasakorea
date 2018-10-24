using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using System.Threading.Tasks;

namespace bahasaKorea.Droid
{
    [Activity(MainLauncher = true, Icon = "@drawable/icon", NoHistory = true, Theme = "@style/Theme.Splash")]
    public class SplashActivity : Activity
    {
        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);

            SetContentView(Resource.Layout.SplashLayout);

            //LoadMainActivity();
        }

        protected override void OnResume()
        {
            base.OnResume();

            Task startupWork = new Task(() => { LoadMainActivity(); });
            startupWork.Start();
        }

        private async void LoadMainActivity()
        {
            StartActivity(new Intent(Application.Context, typeof(MainActivity)));
        }

        public override void OnBackPressed()
        {
        }

    }
}