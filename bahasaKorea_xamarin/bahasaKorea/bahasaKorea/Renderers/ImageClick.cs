using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace bahasaKorea.Renderers
{
    public class ImageClick : Image
    {
        public event EventHandler Click;

        public ImageClick()
        {
            this.AddTouchHandler(this, this.OnClick);
        }

        private void OnClick()
        {
            Click?.Invoke(this, EventArgs.Empty);
        }

        protected void AddTouchHandler(View view, Action action)
        {
            view.GestureRecognizers.Add(new TapGestureRecognizer
            {
                Command = new Command(() =>
                {
                    view.Opacity = 0.6;
                    view.FadeTo(1);
                    action();
                })
            });
        }

    }
}
