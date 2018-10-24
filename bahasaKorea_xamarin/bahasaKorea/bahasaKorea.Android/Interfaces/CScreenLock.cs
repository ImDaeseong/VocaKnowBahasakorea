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
using bahasaKorea.Interfaces;
using bahasaKorea.Droid.Interfaces;
using Xamarin.Forms;
using Plugin.CurrentActivity;

[assembly: Dependency(typeof(CScreenLock))]
namespace bahasaKorea.Droid.Interfaces
{
    public class CScreenLock : IScreenLock
    {
        public CScreenLock()
        {
        }

        public void Lock()
        {
            MainActivity main = ((MainActivity)CrossCurrentActivity.Current.Activity);
            main.Window.SetFlags(WindowManagerFlags.KeepScreenOn, WindowManagerFlags.KeepScreenOn);
        }

        public void Unlock()
        {
            MainActivity main = ((MainActivity)CrossCurrentActivity.Current.Activity);
            main.Window.ClearFlags(WindowManagerFlags.KeepScreenOn);
        }
    }
}