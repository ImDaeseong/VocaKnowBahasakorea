using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace bahasaKorea.Renderers
{
    public class ImageColorEx : Image
    {
        public static readonly BindableProperty ImageColorProperty = BindableProperty.Create("ImageColor", typeof(Color), typeof(ImageColorEx), default(Color));

        public Color ImageColor
        {
            get { return (Color)GetValue(ImageColorProperty); }
            set { SetValue(ImageColorProperty, value); }
        }
    }
}
