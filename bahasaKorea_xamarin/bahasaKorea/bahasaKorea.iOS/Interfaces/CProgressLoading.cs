using bahasaKorea.Interfaces;
using bahasaKorea.iOS.Interfaces;
using BigTed;
using System;
using System.Collections.Generic;
using System.Text;
using Xamarin.Forms;

[assembly: Dependency(typeof(CProgressLoading))]
namespace bahasaKorea.iOS.Interfaces
{
    public class CProgressLoading : IProgressLoading
    {
        public CProgressLoading()
        {
        }

        public void Hide()
        {
            BTProgressHUD.Dismiss();
        }

        public void Show(string title = "pemuatan data")
        {
            BTProgressHUD.ShowToast(title, maskType: ProgressHUD.MaskType.Black);

            //BTProgressHUD.Show(title, maskType: ProgressHUD.MaskType.Black);
        }
    }
}
