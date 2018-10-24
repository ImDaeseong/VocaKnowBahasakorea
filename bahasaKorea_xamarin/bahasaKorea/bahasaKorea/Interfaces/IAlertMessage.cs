using System;

namespace bahasaKorea.Interfaces
{
    public interface IAlertMessage
    {
        void LongAlert(string sMessage);
        void ShortAlert(string sMessage);
    }
}
