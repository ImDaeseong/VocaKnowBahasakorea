using bahasaKorea.Interfaces;
using bahasaKorea.iOS.Interfaces;
using Foundation;
using System;
using System.Collections.Generic;
using System.Text;
using Xamarin.Forms;

[assembly: Dependency(typeof(CUserSetting))]
namespace bahasaKorea.iOS.Interfaces
{
    public class CUserSetting : IUserSetting
    {
        const string SETTINGSKEY_SKINSTYLE = "SkinStyle";
        const string SETTINGSKEY_ALFABETTIMEREPEAT = "AlfabetRepeatTime";
        const string SETTINGSKEY_ALFABETTIMESOUND = "AlfabetRepeatSound";
        const string SETTINGSKEY_INGATTIMEREPEAT = "IngatRepeatTime";
        const string SETTINGSKEY_INGATTIMESOUND = "IngatRepeatSound";
        const string SETTINGSKEY_SCREENLOCK = "ScreenLock";

        public int SkinStyle
        {
            get
            {
                return (int)NSUserDefaults.StandardUserDefaults.IntForKey(SETTINGSKEY_SKINSTYLE);
            }
            set
            {
                NSUserDefaults.StandardUserDefaults.SetInt(value, SETTINGSKEY_SKINSTYLE);
                NSUserDefaults.StandardUserDefaults.Synchronize();
            }
        }

        public int AlfabetRepeatTime
        {
            get
            {
                return (int)NSUserDefaults.StandardUserDefaults.IntForKey(SETTINGSKEY_ALFABETTIMEREPEAT);
            }
            set
            {
                NSUserDefaults.StandardUserDefaults.SetInt(value, SETTINGSKEY_ALFABETTIMEREPEAT);
                NSUserDefaults.StandardUserDefaults.Synchronize();
            }
        }

        public bool AlfabetRepeatSound
        {
            get
            {
                return NSUserDefaults.StandardUserDefaults.BoolForKey(SETTINGSKEY_ALFABETTIMESOUND);
            }
            set
            {
                NSUserDefaults.StandardUserDefaults.SetBool(value, SETTINGSKEY_ALFABETTIMESOUND);
                NSUserDefaults.StandardUserDefaults.Synchronize();
            }
        }

        public int IngatRepeatTime
        {
            get
            {
                return (int)NSUserDefaults.StandardUserDefaults.IntForKey(SETTINGSKEY_INGATTIMEREPEAT);
            }
            set
            {
                NSUserDefaults.StandardUserDefaults.SetInt(value, SETTINGSKEY_INGATTIMEREPEAT);
                NSUserDefaults.StandardUserDefaults.Synchronize();
            }
        }

        public bool IngatRepeatSound
        {
            get
            {
                return NSUserDefaults.StandardUserDefaults.BoolForKey(SETTINGSKEY_INGATTIMESOUND);
            }
            set
            {
                NSUserDefaults.StandardUserDefaults.SetBool(value, SETTINGSKEY_INGATTIMESOUND);
                NSUserDefaults.StandardUserDefaults.Synchronize();
            }
        }

        public bool ScreenLock
        {
            get
            {
                return NSUserDefaults.StandardUserDefaults.BoolForKey(SETTINGSKEY_SCREENLOCK);
            }
            set
            {
                NSUserDefaults.StandardUserDefaults.SetBool(value, SETTINGSKEY_SCREENLOCK);
                NSUserDefaults.StandardUserDefaults.Synchronize();
            }
        }

    }
}
