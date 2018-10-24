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
    public partial class TongueTwisterView : ContentView
    {
        List<TextSpinClass> mText;
        int nPos = 0;

        public TongueTwisterView()
        {
            InitializeComponent();

            try
            {
                mText = new List<TextSpinClass>
                {
                    new TextSpinClass { sLineText = "내가 그린 기린 그림은 잘 그린 기린 그림이고 니가 그린 기린 그림은 잘 못 그린 기린 그림이다.", clrText = Color.White },
                    new TextSpinClass { sLineText = "중앙청 창살은 쌍창살이고 시청의 창살은 외창살이다.", clrText = Color.White },
                    new TextSpinClass { sLineText = "간장 공장 공장장은 강 공장장이고 된장 공장 공장장은 공 공장장이다.", clrText = Color.White }
                };

                Device.StartTimer(TimeSpan.FromSeconds(3), () =>
                {
                    scrollText.Text = mText[nPos].sLineText;
                    scrollText.TextColor = mText[nPos].clrText;
                    nPos++;
                    if (nPos == mText.Count)
                        nPos = 0;
                    return true;
                });

            }
            catch { }
        }

    }

    public class TextSpinClass
    {
        public string sLineText { get; set; }

        public Color clrText { get; set; }
    }

}