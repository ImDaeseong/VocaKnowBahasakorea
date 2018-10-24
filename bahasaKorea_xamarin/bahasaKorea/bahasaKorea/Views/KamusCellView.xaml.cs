using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace bahasaKorea.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class KamusCellView : ContentView, INotifyPropertyChanged
    {
        private SingleTurn.UserSetting Setting = SingleTurn.UserSetting.getInstance;


        public event PropertyChangedEventHandler PropertyChanged;
        protected void RaisePropertyChanged(string propertyName = "")
        {
            var changed = PropertyChanged;
            if (changed != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
            }
        }

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

            boxLine.BackgroundColor = CurSkin;
        }

        public KamusCellView(string KataKor, string KataIndo, string KataEng)
        {
            InitializeComponent();

            try
            {
                InitSkinStyle();
                this.KataKor = KataKor;
                this.KataIndo = KataIndo;
                this.KataEng = KataEng;
                this.MinimumHeightRequest = 20;// 50;
            }
            catch { }
        }

        private string _KataKor;
        public string KataKor
        {
            get { return _KataKor; }
            set { _KataKor = value; lblKataKor.Text = value; }
        }

        private string _KataIndo;
        public string KataIndo
        {
            get { return _KataIndo; }
            set { _KataIndo = value; lblKataIndo.Text = value; }
        }

        private string _KataEng;
        public string KataEng
        {
            get { return _KataIndo; }
            set { _KataIndo = value; lblKataEng.Text = value; }
        }
    }
}