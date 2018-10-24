using bahasaKorea.Modal;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace bahasaKorea.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class KamusView : ContentView
    {
        private SingleTurn.UserSetting Setting = SingleTurn.UserSetting.getInstance;
        private SingleTurn.DBSqlite KataDB = SingleTurn.DBSqlite.getInstance;
        private SingleTurn.AlertMsg Alert = SingleTurn.AlertMsg.getInstance;

        private string sKataKor;
        private ObservableCollection<KataItems> mCollection;


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

            eSearch.BorderColor = CurSkin;
        }

        public KamusView()
        {
            InitializeComponent();

            try
            {
                InitSkinStyle();

                stackList.Children.Clear();

                //LoadAllKataData();

            }
            catch { }

            scrollView.Scrolled += ScrollView_Scrolled;
        }

        protected override void OnSizeAllocated(double width, double height)
        {
            base.OnSizeAllocated(width, height);

        }

        private void ScrollView_Scrolled(object sender, ScrolledEventArgs e)
        {
            /*
            try
            {
                string sMsg = "Position: " + e.ScrollX + " x " + e.ScrollY;
                System.Diagnostics.Debug.WriteLine(sMsg);

                if (e.ScrollY > 0)
                {
                    System.Diagnostics.Debug.WriteLine(e.ScrollY);            
                }
                else
                {
                    System.Diagnostics.Debug.WriteLine(e.ScrollY);
                }
            }
            catch { }
            */
        }

        private void LoadAllKataData()
        {
            try
            {
                var ItemTapped = new TapGestureRecognizer();
                ItemTapped.Tapped += ItemTapped_Tapped;

                stackList.Children.Clear();

                mCollection = new ObservableCollection<KataItems>(KataDB.GetItems());

                foreach (KataItems data in mCollection)
                {
                    var item = new KamusCellView(data.KataKor, data.KataIndo, data.KataEng);
                    item.GestureRecognizers.Add(ItemTapped);
                    stackList.Children.Add(item);
                }
                mCollection.Clear();
            }
            catch { }
        }

        private void eSearch_TextChanged(object sender, TextChangedEventArgs e)
        {
            try
            {
                var eSearch = (Entry)sender;
                string sSearch = eSearch.Text;

                if (sSearch == "") return;

                var ItemTapped = new TapGestureRecognizer();
                ItemTapped.Tapped += ItemTapped_Tapped;

                stackList.Children.Clear();

                mCollection = new ObservableCollection<KataItems>(KataDB.GetSearchKata(sSearch));

                foreach (KataItems data in mCollection)
                {
                    var item = new KamusCellView(data.KataKor, data.KataIndo, data.KataEng);
                    item.GestureRecognizers.Add(ItemTapped);
                    stackList.Children.Add(item);
                }
                mCollection.Clear();
            }
            catch { }
        }

        private void ItemTapped_Tapped(object sender, EventArgs e)
        {
            try
            {
                var item = (KamusCellView)sender;
                var slItem = item.Parent as StackLayout;
                if (slItem != null)
                {
                    foreach (var viewitem in slItem.Children)
                    {
                        viewitem.BackgroundColor = Color.White;
                    }
                }

                if (item.KataKor == sKataKor)
                {
                    item.BackgroundColor = Color.White;
                    sKataKor = "";
                }
                else
                {
                    item.BackgroundColor = Color.FromHex("#FFC10E");
                    sKataKor = item.KataKor;
                }
            }
            catch { }

        }


    }
}