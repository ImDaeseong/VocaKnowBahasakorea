using bahasaKorea.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace bahasaKorea.SingleTurn
{
    public class UserSetting
    {
        private static UserSetting selfInstance = null;
        public static UserSetting getInstance
        {
            get
            {
                if (selfInstance == null) selfInstance = new UserSetting();
                return selfInstance;
            }
        }

        static IUserSetting LocalSettings
        {
            get
            {
                return DependencyService.Get<IUserSetting>();
            }
        }

        public int SkinStyle
        {
            get
            {
                if (LocalSettings.SkinStyle == 0)
                    LocalSettings.SkinStyle = 1;

                return LocalSettings.SkinStyle;
            }
            set
            {
                LocalSettings.SkinStyle = value;
            }
        }

        public int AlfabetRepeatTime
        {
            get
            {
                if (LocalSettings.AlfabetRepeatTime == 0)
                    LocalSettings.AlfabetRepeatTime = 10;

                return LocalSettings.AlfabetRepeatTime;
            }
            set
            {
                LocalSettings.AlfabetRepeatTime = value;
            }
        }

        public bool AlfabetRepeatSound
        {
            get
            {
                return LocalSettings.AlfabetRepeatSound;
            }
            set
            {
                LocalSettings.AlfabetRepeatSound = value;
            }
        }

        public int IngatRepeatTime
        {
            get
            {
                if (LocalSettings.IngatRepeatTime == 0)
                    LocalSettings.IngatRepeatTime = 10;

                return LocalSettings.IngatRepeatTime;
            }
            set
            {
                LocalSettings.IngatRepeatTime = value;
            }
        }

        public bool IngatRepeatSound
        {
            get
            {
                return LocalSettings.IngatRepeatSound;
            }
            set
            {
                LocalSettings.IngatRepeatSound = value;
            }
        }

        public bool ScreenLock
        {
            get
            {
                return LocalSettings.ScreenLock;
            }
            set
            {
                LocalSettings.ScreenLock = value;
            }
        }

    }
}
