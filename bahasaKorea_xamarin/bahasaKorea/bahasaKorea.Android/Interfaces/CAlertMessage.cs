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

[assembly: Dependency(typeof(CAlertMessage))]
namespace bahasaKorea.Droid.Interfaces
{
    public class CAlertMessage : IAlertMessage
    {
        public void LongAlert(string sMessage)
        {
            Toast.MakeText(Android.App.Application.Context, sMessage, ToastLength.Long).Show();
        }

        public void ShortAlert(string sMessage)
        {
            Toast.MakeText(Android.App.Application.Context, sMessage, ToastLength.Short).Show();
        }
    }
}