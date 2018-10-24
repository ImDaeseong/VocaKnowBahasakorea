using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using bahasaKorea.Interfaces;

namespace bahasaKorea.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class WelcomePage : ContentPage
    {
        private SingleTurn.UserSetting Setting = SingleTurn.UserSetting.getInstance;


        private int SkinType;
        private Color CurSkin;
        private void InitSkinStyle()
        {
            SkinType = Setting.SkinStyle;

            if (SkinType == 1)
                CurSkin = Color.FromHex("#33A7D6");
            else if (SkinType == 2)
                CurSkin = Color.FromHex("#493335");
            else if (SkinType == 3)
                CurSkin = Color.FromHex("#FF80AB");
            else if (SkinType == 4)
                CurSkin = Color.FromHex("#4CAF50");
            else if (SkinType == 5)
                CurSkin = Color.FromHex("#3F51B5");
            else if (SkinType == 6)
                CurSkin = Color.FromHex("#B71C1C");
            else if (SkinType == 7)
                CurSkin = Color.FromHex("#37474F");

            EnterClose.BackgroundColor = CurSkin;
        }

        public WelcomePage()
        {
            InitializeComponent();

            try
            {
                InitSkinStyle();

                if (Manager.ENABLEADS)
                {
                    AdName.IsVisible = true;
                }
                else
                {
                    AdName.IsVisible = false;
                }

                if (DependencyService.Get<INetworkAvailable>().HasNetworkAccess())
                {
                    //internet 사용가능
                }

            }
            catch { }
        }

        private async void EnterClose_Click(object sender, EventArgs e)
        {
            try
            {
                await Navigation.PopModalAsync(false);
            }
            catch { }
        }

    }
}