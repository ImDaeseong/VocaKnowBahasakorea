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
    public partial class CloseTopButtonView : ContentView
    {
        public string Text
        {
            set { lblEditor.Text = value; }
        }


        public event EventHandler Click;

        public CloseTopButtonView()
        {
            InitializeComponent();

            var columnDefinitions = CloseButtonView.ColumnDefinitions;
            columnDefinitions[1].Width = 0;

            this.WidthRequest = 240;

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