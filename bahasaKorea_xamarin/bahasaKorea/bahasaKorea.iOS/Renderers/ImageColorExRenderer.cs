using bahasaKorea.iOS.Renderers;
using bahasaKorea.Renderers;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;
using UIKit;
using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;

[assembly: ExportRenderer(typeof(ImageColorEx), typeof(ImageColorExRenderer))]
namespace bahasaKorea.iOS.Renderers
{
    public class ImageColorExRenderer : ImageRenderer
    {
        protected override void OnElementChanged(ElementChangedEventArgs<Image> e)
        {
            base.OnElementChanged(e);

            SetImageColor();
        }

        private void SetImageColor()
        {
            var element = (ImageColorEx)Element;

            if (element != null && Control != null)
            {
                Control.Image = Control.Image.ImageWithRenderingMode(UIImageRenderingMode.AlwaysTemplate);
                Control.TintColor = Color.Red.ToUIColor();
            }
        }

        protected override void OnElementPropertyChanged(object sender, PropertyChangedEventArgs e)
        {
            base.OnElementPropertyChanged(sender, e);

            if (e.PropertyName == ImageColorEx.ImageColorProperty.PropertyName)
            {
                SetImageColor();
            }
        }
    }
}
