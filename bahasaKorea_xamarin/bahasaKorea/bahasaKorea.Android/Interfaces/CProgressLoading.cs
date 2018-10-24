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
using AndroidHUD;

[assembly: Dependency(typeof(CProgressLoading))]
namespace bahasaKorea.Droid.Interfaces
{
    public class CProgressLoading : IProgressLoading
    {
        public CProgressLoading()
        {
        }

        public void Hide()
        {
            Device.BeginInvokeOnMainThread(() =>
            {
                AndHUD.Shared.Dismiss();
            });
        }

        public void Show(string title = "pemuatan data")
        {
            Device.BeginInvokeOnMainThread(() =>
            {
                AndHUD.Shared.ShowToast(Forms.Context, status: title, maskType: MaskType.Black);

                //AndHUD.Shared.Show(Forms.Context, status: title, maskType: MaskType.Black);
            });
        }
    }
}