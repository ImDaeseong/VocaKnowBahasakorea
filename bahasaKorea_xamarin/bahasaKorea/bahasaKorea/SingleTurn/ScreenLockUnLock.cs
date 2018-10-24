using bahasaKorea.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace bahasaKorea.SingleTurn
{
    public class ScreenLockUnLock
    {
        private static ScreenLockUnLock selfInstance = null;
        public static ScreenLockUnLock getInstance
        {
            get
            {
                if (selfInstance == null) selfInstance = new ScreenLockUnLock();
                return selfInstance;
            }
        }

        static IScreenLock ScreenLock
        {
            get
            {
                return DependencyService.Get<IScreenLock>();
            }
        }

        public void SetLockUnLock(bool bLock)
        {
            if (bLock)
                ScreenLock.Lock();
            else
                ScreenLock.Unlock();
        }
    }
}
