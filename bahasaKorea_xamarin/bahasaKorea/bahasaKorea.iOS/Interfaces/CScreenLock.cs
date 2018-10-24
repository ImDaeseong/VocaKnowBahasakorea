using bahasaKorea.Interfaces;
using bahasaKorea.iOS.Interfaces;
using System;
using System.Collections.Generic;
using System.Text;
using UIKit;
using Xamarin.Forms;

[assembly: Dependency(typeof(CScreenLock))]
namespace bahasaKorea.iOS.Interfaces
{
    public class CScreenLock : IScreenLock
    {
        public CScreenLock()
        {
        }

        public void Lock()
        {
            UIApplication.SharedApplication.IdleTimerDisabled = true;
        }

        public void Unlock()
        {
            UIApplication.SharedApplication.IdleTimerDisabled = false;
        }
    }
}
