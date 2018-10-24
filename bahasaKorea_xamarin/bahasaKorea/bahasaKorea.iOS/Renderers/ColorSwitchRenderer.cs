using bahasaKorea.iOS.Renderers;
using bahasaKorea.Renderers;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;
using UIKit;
using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;

[assembly: ExportRenderer(typeof(ColorSwitch), typeof(ColorSwitchRenderer))]
namespace bahasaKorea.iOS.Renderers
{
    public class ColorSwitchRenderer : SwitchRenderer
    {
        private UIColor TintColor;
        private UIColor ThumbColor;

        protected override void OnElementChanged(ElementChangedEventArgs<Switch> e)
        {
            /*
            base.OnElementChanged(e);

            if (Control != null)
            {
                //Control.BackgroundColor = UIColor.White;
                Control.OnTintColor = UIColor.FromRGB(51, 167, 214);//UIColor.Orange;
                Control.ThumbTintColor = UIColor.FromRGB(225, 225, 227);//UIColor.Gray;

                //Control.TintColor = UIColor.FromRGB(98, 26, 65);
                //Control.OnTintColor = UIColor.FromRGB(98, 26, 65);
            }
            */

            base.OnElementChanged(e);

            if (Control == null || Element == null) return;
            var element = (ColorSwitch)Element;
            UpdateProperties(element);
            UpdateSwitchColor();
        }

        protected override void OnElementPropertyChanged(object sender, PropertyChangedEventArgs e)
        {
            base.OnElementPropertyChanged(sender, e);

            if (sender == null) return;
            var element = (ColorSwitch)sender;
            UpdateProperties(element);
            UpdateSwitchColor();
        }

        private void UpdateProperties(ColorSwitch SwitchElement)
        {
            TintColor = SwitchElement.TintColor.ToUIColor();
            ThumbColor = SwitchElement.ThumbColor.ToUIColor();
        }

        private void UpdateSwitchColor()
        {
            Control.BackgroundColor = UIColor.White;
            Control.TintColor = TintColor;
            Control.OnTintColor = ThumbColor;
        }

    }
}
