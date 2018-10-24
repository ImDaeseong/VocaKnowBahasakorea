using System;

namespace bahasaKorea.Interfaces
{
    public interface IAudioPlayer
    {
        void SetupPlayer(string sFileName);
        void PlayAudioFile();
        void StopPlayBack();
        void PausePlayback();
        void ResumePlayBack();
        bool IsPlaying();
        int GetDuration();
        int GetCurrentTime();
        bool HasFinishedPlaying();
    }
}
