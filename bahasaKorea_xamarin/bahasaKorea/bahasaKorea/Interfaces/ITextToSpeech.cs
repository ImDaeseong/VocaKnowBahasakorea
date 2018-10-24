using System;

namespace bahasaKorea.Interfaces
{
    public interface ITextToSpeech
    {
        void Speak(string sText);
        void SetLanguage(string sLanguage);
        bool IsSpeaking { get; }
    }
}
