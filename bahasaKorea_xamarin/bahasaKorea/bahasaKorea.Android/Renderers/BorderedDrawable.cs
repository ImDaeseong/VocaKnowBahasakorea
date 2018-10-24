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
using Android.Graphics.Drawables;
using Android.Graphics;

namespace bahasaKorea.Droid.Renderers
{
    public class BorderedDrawable : Drawable
    {
        private readonly float _borderHeight;
        public Paint BackgroundPaint { get; set; }

        public BorderedDrawable(Color borderColor, double borderHeight)
        {
            BackgroundPaint = new Paint { Color = borderColor };
            _borderHeight = Convert.ToSingle(borderHeight);
        }

        public override void Draw(Canvas canvas)
        {
            var width = canvas.Width;
            var height = canvas.Height;
            canvas.DrawColor(Color.Transparent);
            canvas.DrawRect(0, height - _borderHeight, width, height, BackgroundPaint);
        }

        public override void SetAlpha(int alpha) { }
        public override void SetColorFilter(ColorFilter colorFilter) { }
        public override int Opacity => (int)Format.Translucent;
    }
}