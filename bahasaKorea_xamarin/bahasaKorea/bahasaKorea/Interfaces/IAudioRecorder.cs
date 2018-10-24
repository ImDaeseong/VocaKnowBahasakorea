using System;

namespace bahasaKorea.Interfaces
{
    public interface IAudioRecorder
    {
        void StartRecord(string sFileName);
        string StopRecord();
    }
}
