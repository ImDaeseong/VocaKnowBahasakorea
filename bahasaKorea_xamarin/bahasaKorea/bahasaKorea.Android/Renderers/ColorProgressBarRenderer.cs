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

[assembly: ExportRenderer(typeof(ColorProgressBar), typeof(ColorProgressBarRenderer))]
namespace bahasaKorea.Droid.Renderers
{
    public class ColorProgressBarRenderer : ProgressBarRenderer
    {
        protected override void OnElementChanged(ElementChangedEventArgs<Xamarin.Forms.ProgressBar> e)
        {
            base.OnElementChanged(e);
        
            try
            {
                var solidTransparentColor = new Color(255, 255, 255, 1.0);
                Control.ProgressDrawable.SetColorFilter(solidTransparentColor.ToAndroid(), Android.Graphics.PorterDuff.Mode.SrcIn);
                Control.ProgressTintList = Android.Content.Res.ColorStateList.ValueOf(solidTransparentColor.ToAndroid());
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine(ex.Message);
            }
        }
    }
}