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
    public partial class MarqueeView : ContentView
    {
        private bool bMoveLeft = false;

        string sContent { get; set; }
        public String ContentMarquee
        {
            get
            {
                if (sContent == "" || sContent == null)
                    sContent = "Klik tombol play menjalankan secara otomatis setiap 10 detik.";

                return sContent;
            }
            set
            {
                if (!string.IsNullOrEmpty(value))
                {
                    sContent = value.Replace("\n", " ");
                    Ticker.Text = sContent;
                }
            }
        }


        public MarqueeView()
        {
            InitializeComponent();

            Device.StartTimer(TimeSpan.FromSeconds(10), OnTimerTick);
        }

        private bool OnTimerTick()
        {
            playMarquee();
            return true;
        }

        public static Animation TransLateXAnimation(VisualElement element, double from, double to)
        {
            return new Animation(d => { element.TranslationX = d; }, from, to);
        }

        private async void playMarquee()
        {
            if (bMoveLeft)
            {
                bMoveLeft = false;
                var ani1 = TransLateXAnimation(Ticker, 1000, 0);
                this.Animate("tranx", ani1, 16, 1000, Easing.Linear, (d, f) => { });
            }
            else
            {
                bMoveLeft = true;
                var ani1 = TransLateXAnimation(Ticker, -1000, 0);
                this.Animate("tranx", ani1, 16, 1000, Easing.Linear, (d, f) => { });
            }
        }

    }
}