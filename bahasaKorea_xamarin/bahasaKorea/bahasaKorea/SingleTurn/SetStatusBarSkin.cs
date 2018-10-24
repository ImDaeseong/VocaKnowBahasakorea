using bahasaKorea.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace bahasaKorea.SingleTurn
{
    public class SetStatusBarSkin
    {
        private static SetStatusBarSkin selfInstance = null;
        public static SetStatusBarSkin getInstance
        {
            get
            {
                if (selfInstance == null) selfInstance = new SetStatusBarSkin();
                return selfInstance;
            }
        }

        static ISetStatusBarStyle StatusBarSkin
        {
            get
            {
                return DependencyService.Get<ISetStatusBarStyle>();
            }
        }

        public void ChangeStatusBarColor(Color color)
        {
            StatusBarSkin.ChangeStatusBarColor(color);
        }
    }
}
