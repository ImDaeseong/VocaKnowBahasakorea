using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace bahasaKorea.Renderers
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class LoadingView : ContentView
    {
        public LoadingView()
        {
            InitializeComponent();

            loadingText.Text = "memuat data";
            loadingText.TextColor = Color.White;

            Device.BeginInvokeOnMainThread(async () =>
            {
                await RotateImage();
            });
        }

        public async Task RotateImage()
        {
            int i = 0;
            while (true)
            {
                if (i == 10)
                    i = 0;
                await Task.Delay(100);
                loadingImage.Rotation = (360 / 10) * (i + 1);
                i++;
            }
        }
    }
}