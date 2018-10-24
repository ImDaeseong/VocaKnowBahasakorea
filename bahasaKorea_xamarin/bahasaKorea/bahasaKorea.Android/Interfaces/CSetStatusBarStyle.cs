﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using bahasaKorea.Interfaces;
using bahasaKorea.Droid.Interfaces;
using Xamarin.Forms;
using Plugin.CurrentActivity;
using Xamarin.Forms.Platform.Android;

[assembly: Dependency(typeof(CSetStatusBarStyle))]
namespace bahasaKorea.Droid.Interfaces
{
    public class CSetStatusBarStyle : ISetStatusBarStyle
    {
        public CSetStatusBarStyle()
        {
        }

        public void ChangeStatusBarColor(Color color)
        {
            MainActivity main = ((MainActivity)CrossCurrentActivity.Current.Activity);

            // Set the status bar color.
            if (Build.VERSION.SdkInt >= BuildVersionCodes.Lollipop)
            {
                main.Window.ClearFlags(WindowManagerFlags.TranslucentStatus);
                main.Window.AddFlags(WindowManagerFlags.DrawsSystemBarBackgrounds);
                main.Window.SetStatusBarColor(color.ToAndroid()); //main.Window.SetStatusBarColor(new Android.Graphics.Color(30, 138, 118));                  
            }
        }

        private static Color ConvertFromString(string HexColor)
        {
            HexColor = HexColor.Replace("#", "");
            int r = Convert.ToInt32(HexColor.Substring(0, 2), 16);
            int g = Convert.ToInt32(HexColor.Substring(2, 2), 16);
            int b = Convert.ToInt32(HexColor.Substring(4, 2), 16);
            return Color.FromRgb(r, g, b);
        }

    }
}