using bahasaKorea.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace bahasaKorea.SingleTurn
{
    public class AlertMsg
    {
        private static AlertMsg selfInstance = null;
        public static AlertMsg getInstance
        {
            get
            {
                if (selfInstance == null) selfInstance = new AlertMsg();
                return selfInstance;
            }
        }

        static IAlertMessage Msg
        {
            get
            {
                return DependencyService.Get<IAlertMessage>();
            }
        }

        public void ShortAlert(string sMessage)
        {
            Msg.ShortAlert(sMessage);
        }

        public void LongAlert(string sMessage)
        {
            Msg.LongAlert(sMessage);
        }

    }
}
