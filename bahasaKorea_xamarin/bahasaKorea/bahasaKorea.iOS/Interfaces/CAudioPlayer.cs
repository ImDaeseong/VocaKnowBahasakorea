using AVFoundation;
using bahasaKorea.Interfaces;
using bahasaKorea.iOS.Interfaces;
using Foundation;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using Xamarin.Forms;

[assembly: Dependency(typeof(CAudioPlayer))]
namespace bahasaKorea.iOS.Interfaces
{
    public class CAudioPlayer : IAudioPlayer
    {
        AVAudioPlayer _player;
        bool finished = false;
        private AVAudioSession audioSession;

        public CAudioPlayer()
        {
        }

        public void SetupPlayer(string sFileName)
        {
            audioSession = AVAudioSession.SharedInstance();
            var error = audioSession.SetCategory(AVAudioSessionCategory.Playback);
            if (error != null)
            {
                Console.WriteLine(error);
            }
            error = audioSession.SetActive(true);
            if (error != null)
            {
                Console.WriteLine(error);
            }

            string path = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.Personal), sFileName);
            var url = NSUrl.FromString(path);

            _player = AVAudioPlayer.FromUrl(url);
            _player.Volume = 1.0f;

            _player.FinishedPlaying += PlayerFinishedPlaying;
            _player.PrepareToPlay();
        }

        public void PlayAudioFile()
        {
            _player.Play();
        }

        public void PausePlayback()
        {
            _player.Pause();
        }

        public void ResumePlayBack()
        {
            _player.Play();
        }

        public void StopPlayBack()
        {
            _player.Stop();
        }

        public void PlayerFinishedPlaying(object sender, AVStatusEventArgs e)
        {
            _player.Stop();
            finished = true;

            MessagingCenter.Send<CAudioPlayer, bool>(this, "finished_playback", finished);
        }

        public bool IsPlaying()
        {
            if (_player == null)
            {
                return false;
            }
            else
            {
                return _player.Playing;
            }
        }

        public int GetDuration()
        {
            return (int)_player.Duration;
        }

        public int GetCurrentTime()
        {
            return (int)_player.CurrentTime;
        }

        public bool HasFinishedPlaying()
        {
            return finished;
        }

    }
}
