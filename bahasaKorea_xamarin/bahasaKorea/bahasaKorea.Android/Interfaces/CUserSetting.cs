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

[assembly: Dependency(typeof(CUserSetting))]
namespace bahasaKorea.Droid.Interfaces
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
                var prefs = Android.App.Application.Context.GetSharedPreferences("MySharedPrefs", FileCreationMode.Private);
                return prefs.GetInt(SETTINGSKEY_SKINSTYLE, 1);
            }
            set
            {
                var prefs = Android.App.Application.Context.GetSharedPreferences("MySharedPrefs", FileCreationMode.Private);
                var prefsEditor = prefs.Edit();
                prefsEditor.PutInt(SETTINGSKEY_SKINSTYLE, value);
                prefsEditor.Commit();
            }
        }

        public int AlfabetRepeatTime
        {
            get
            {
                var prefs = Android.App.Application.Context.GetSharedPreferences("MySharedPrefs", FileCreationMode.Private);
                return prefs.GetInt(SETTINGSKEY_ALFABETTIMEREPEAT, 10);
            }
            set
            {
                var prefs = Android.App.Application.Context.GetSharedPreferences("MySharedPrefs", FileCreationMode.Private);
                var prefsEditor = prefs.Edit();
                prefsEditor.PutInt(SETTINGSKEY_ALFABETTIMEREPEAT, value);
                prefsEditor.Commit();
            }
        }

        public bool AlfabetRepeatSound
        {
            get
            {
                var prefs = Android.App.Application.Context.GetSharedPreferences("MySharedPrefs", FileCreationMode.Private);
                return prefs.GetBoolean(SETTINGSKEY_ALFABETTIMESOUND, false);
            }
            set
            {
                var prefs = Android.App.Application.Context.GetSharedPreferences("MySharedPrefs", FileCreationMode.Private);
                var prefsEditor = prefs.Edit();
                prefsEditor.PutBoolean(SETTINGSKEY_ALFABETTIMESOUND, value);
                prefsEditor.Commit();
            }
        }

        public int IngatRepeatTime
        {
            get
            {
                var prefs = Android.App.Application.Context.GetSharedPreferences("MySharedPrefs", FileCreationMode.Private);
                return prefs.GetInt(SETTINGSKEY_INGATTIMEREPEAT, 10);
            }
            set
            {
                var prefs = Android.App.Application.Context.GetSharedPreferences("MySharedPrefs", FileCreationMode.Private);
                var prefsEditor = prefs.Edit();
                prefsEditor.PutInt(SETTINGSKEY_INGATTIMEREPEAT, value);
                prefsEditor.Commit();
            }
        }

        public bool IngatRepeatSound
        {
            get
            {
                var prefs = Android.App.Application.Context.GetSharedPreferences("MySharedPrefs", FileCreationMode.Private);
                return prefs.GetBoolean(SETTINGSKEY_INGATTIMESOUND, false);
            }
            set
            {
                var prefs = Android.App.Application.Context.GetSharedPreferences("MySharedPrefs", FileCreationMode.Private);
                var prefsEditor = prefs.Edit();
                prefsEditor.PutBoolean(SETTINGSKEY_INGATTIMESOUND, value);
                prefsEditor.Commit();
            }
        }

        public bool ScreenLock
        {
            get
            {
                var prefs = Android.App.Application.Context.GetSharedPreferences("MySharedPrefs", FileCreationMode.Private);
                return prefs.GetBoolean(SETTINGSKEY_SCREENLOCK, false);
            }
            set
            {
                var prefs = Android.App.Application.Context.GetSharedPreferences("MySharedPrefs", FileCreationMode.Private);
                var prefsEditor = prefs.Edit();
                prefsEditor.PutBoolean(SETTINGSKEY_SCREENLOCK, value);
                prefsEditor.Commit();
            }
        }

    }
}