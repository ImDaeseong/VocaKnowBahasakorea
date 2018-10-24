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
using Android.Speech.Tts;
using Java.Util;

[assembly: Dependency(typeof(CTextToSpeech))]
namespace bahasaKorea.Droid.Interfaces
{
    public class CTextToSpeech : Java.Lang.Object, ITextToSpeech, TextToSpeech.IOnInitListener
    {
        private TextToSpeech speaker;
        private Locale language = Locale.Korean;

        public bool IsInitialized { get; private set; }
        public bool IsSpeaking { get; private set; }

        public CTextToSpeech()
        {
            speaker = new TextToSpeech(Forms.Context.ApplicationContext, this);
        }


        public void Speak(string sText)
        {
            if (this.IsSpeaking || !this.IsInitialized)
                return;

            this.IsSpeaking = true;
            try
            {
                this.speaker.SetLanguage(language);
                this.speaker.Speak(sText, QueueMode.Flush, null, null);
            }
            finally
            {
                this.IsSpeaking = false;
            }
        }

        public void Stop()
        {
            if (!this.IsSpeaking) return;

            try
            {
                this.speaker.Stop();
            }
            finally
            {
                this.IsSpeaking = false;
            }
        }

        public void SetLanguage(string sLanguage)
        {
            switch (sLanguage)
            {
                case "Japanese":
                    this.language = Locale.Japanese;
                    break;

                case "Korean":
                    this.language = Locale.Korean;
                    break;

                case "Indonesian":
                    this.language = Locale.Us; //this.language = Locale.Indonesian;
                    break;

                default:
                    this.language = Locale.Us;
                    break;
            }
        }

        public void OnInit(OperationResult status)
        {
            this.IsInitialized = (status == OperationResult.Success);
        }

        private bool CheckPermissions()
        {
            string rec = Android.Content.PM.PackageManager.FeatureMicrophone;
            if (rec == "android.hardware.microphone")
                return true;

            return false;
        }

    }
}