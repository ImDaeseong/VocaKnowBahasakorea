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
using Android.Graphics;

[assembly: ExportRenderer(typeof(UnderLineLabel), typeof(UnderLineLabelRenderer))]
namespace bahasaKorea.Droid.Renderers
{
    public class UnderLineLabelRenderer : LabelRenderer
    {
        protected override void OnElementChanged(ElementChangedEventArgs<Label> e)
        {
            base.OnElementChanged(e);
            try
            {
                var textView = (TextView)Control;
                textView.PaintFlags |= PaintFlags.UnderlineText;
            }
            catch { }
        }
    }
}