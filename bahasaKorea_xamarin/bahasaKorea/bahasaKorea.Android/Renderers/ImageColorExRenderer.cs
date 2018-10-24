using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Xamarin.Forms.Platform.Android;
using bahasaKorea.Renderers;
using bahasaKorea.Droid.Renderers;
using Xamarin.Forms;
using System.ComponentModel;

[assembly: ExportRenderer(typeof(ImageColorEx), typeof(ImageColorExRenderer))]
namespace bahasaKorea.Droid.Renderers
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
                Control.SetColorFilter(element.ImageColor.ToAndroid(), Android.Graphics.PorterDuff.Mode.SrcAtop);
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