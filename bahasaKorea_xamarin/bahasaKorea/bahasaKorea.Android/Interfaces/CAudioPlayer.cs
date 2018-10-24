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
using Android.Media;
using bahasaKorea.Interfaces;
using bahasaKorea.Droid.Interfaces;
using Xamarin.Forms;

[assembly: Dependency(typeof(CAudioPlayer))]
namespace bahasaKorea.Droid.Interfaces
{
    public class CAudioPlayer : IAudioPlayer
    {
        MediaPlayer player = new MediaPlayer();
        private bool finished = false;
        private DateTime startTime;
        private int elapsed;

        public CAudioPlayer()
        {
        }

        public void SetupPlayer(string sFileName)
        {
            player = new MediaPlayer();
            var path = Android.App.Application.Context.GetExternalFilesDir(null).AbsolutePath + "/" + sFileName;

            player.SetDataSource(path);
            player.Prepare();
            player.Completion += Player_Completion;
        }

        public bool IsPlaying()
        {
            return player.IsPlaying;
        }

        public void PausePlayback()
        {
            elapsed = GetCurrentTime();

            player.Pause();
        }

        public void PlayAudioFile()
        {
            player.Start();

            startTime = DateTime.Now;
        }

        public void ResumePlayBack()
        {
            startTime = DateTime.Now;
            startTime.AddSeconds(elapsed * (-1));

            player.Start();
        }

        public void StopPlayBack()
        {
            player.Stop();
            player.Release();
            player = null;
        }

        public int GetDuration()
        {
            //Console.WriteLine(player.Duration);
            var duration = (double)player.Duration / 1000;
            return (int)duration;
        }

        void Player_Completion(object sender, EventArgs e)
        {
            player.Stop();
            player.Release();

            finished = true;

            MessagingCenter.Send<CAudioPlayer, bool>(this, "finished_playback", finished);
        }

        public int GetCurrentTime()
        {
            return (int)(DateTime.Now - startTime).TotalSeconds;
        }


        public bool HasFinishedPlaying()
        {
            return finished;
        }
    }
}