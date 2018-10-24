using bahasaKorea.Interfaces;
using bahasaKorea.iOS.Interfaces;
using Foundation;
using System;
using System.Collections.Generic;
using System.Text;
using UIKit;
using Xamarin.Forms;

[assembly: Dependency(typeof(CAlertMessage))]
namespace bahasaKorea.iOS.Interfaces
{
    public class CAlertMessage : IAlertMessage
    {
        const double LONG_DELAY = 2.0;
        const double SHORT_DELAY = 1.0;

        NSTimer alertDelay;
        UIAlertController alert;

        public void LongAlert(string sMessage)
        {
            ShowAlert(sMessage, LONG_DELAY);
        }
        public void ShortAlert(string sMessage)
        {
            ShowAlert(sMessage, SHORT_DELAY);
        }

        void ShowAlert(string sMessage, double seconds)
        {
            alertDelay = NSTimer.CreateScheduledTimer(seconds, (obj) =>
            {
                dismissMessage();
            });
            alert = UIAlertController.Create(null, sMessage, UIAlertControllerStyle.Alert);
            UIApplication.SharedApplication.KeyWindow.RootViewController.PresentViewController(alert, true, null);
        }

        void dismissMessage()
        {
            if (alert != null)
            {
                alert.DismissViewController(true, null);
            }
            if (alertDelay != null)
            {
                alertDelay.Dispose();
            }
        }

    }
}
