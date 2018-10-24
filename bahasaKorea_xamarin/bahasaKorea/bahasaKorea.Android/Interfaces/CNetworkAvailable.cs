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
using Android.Net;
using Xamarin.Forms;

[assembly: Dependency(typeof(CNetworkAvailable))]
namespace bahasaKorea.Droid.Interfaces
{
    public class CNetworkAvailable : INetworkAvailable
    {
        public bool HasNetworkAccess()
        {
            Context c = Android.App.Application.Context;
            ConnectivityManager cm = (ConnectivityManager)c.GetSystemService(Context.ConnectivityService);
            NetworkInfo netInfo = cm.ActiveNetworkInfo;
            return netInfo != null && netInfo.IsConnectedOrConnecting;
        }
    }
}