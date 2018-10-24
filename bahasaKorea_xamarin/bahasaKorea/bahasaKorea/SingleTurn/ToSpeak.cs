using bahasaKorea.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace bahasaKorea.SingleTurn
{
    public class ToSpeak
    {
        private static ToSpeak selfInstance = null;
        public static ToSpeak getInstance
        {
            get
            {
                if (selfInstance == null) selfInstance = new ToSpeak();
                return selfInstance;
            }
        }

        static ITextToSpeech TextToSpeech
        {
            get
            {
                return DependencyService.Get<ITextToSpeech>();
            }
        }

        public void InitSpeak()
        {
            TextToSpeech.Speak("");
        }


        public void Speak(string sText)
        {
            TextToSpeech.Speak(sText);
        }

        public void SetLanguage(string sLanguage)
        {
            TextToSpeech.SetLanguage(sLanguage);
        }

        public bool IsSpeaking
        {
            get
            {
                return TextToSpeech.IsSpeaking;
            }
        }


    }
}
