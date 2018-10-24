using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace bahasaKorea.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class HangulBaseListViewCell : ContentView
    {
        private Color _clrBgColor;
        public Color clrBgColor
        {
            get { return _clrBgColor; }
            set { _clrBgColor = value; AlfabetHangulViewCell.BackgroundColor = value; }
        }


        private string _AlpabatText;
        public string AlpabatText
        {
            get { return _AlpabatText; }
            set { _AlpabatText = value; }
        }

        private SingleTurn.ToSpeak TextToSpeech = SingleTurn.ToSpeak.getInstance;

        public HangulBaseListViewCell()
        {
            InitializeComponent();

            TextToSpeech.InitSpeak();
        }

        private async void TapGestureRecognizer_Tapped(object sender, EventArgs e)
        {
            try
            {
                TextToSpeech.Speak(AlpabatText);
            }
            catch { }
        }
    }
}