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
using bahasaKorea.Interfaces;
using bahasaKorea.Droid.Interfaces;
using Android.Media;
using Xamarin.Forms;
using System.IO;

[assembly: Dependency(typeof(CAudioRecorder))]
namespace bahasaKorea.Droid.Interfaces
{
    public class CAudioRecorder : IAudioRecorder
    {
        //public static int requestAudioId = 0;

        MediaRecorder _recorder = new MediaRecorder();
        String sFileName;

        public CAudioRecorder()
        {
        }

        public void StartRecord(string sFileName)
        {
            this.sFileName = sFileName;

            var path = Path.Combine(System.Environment.GetFolderPath(System.Environment.SpecialFolder.Personal), sFileName);
            path = Android.App.Application.Context.GetExternalFilesDir(null).AbsolutePath + "/" + sFileName;

            _recorder.SetAudioSource(AudioSource.Mic);
            _recorder.SetOutputFormat(OutputFormat.Mpeg4);
            _recorder.SetAudioEncoder(AudioEncoder.Aac);
            _recorder.SetOutputFile(path);

            if (Int32.Parse(Android.OS.Build.VERSION.Sdk) >= 10)
            {
                _recorder.SetAudioSamplingRate(44100);
                _recorder.SetAudioEncodingBitRate(96000);
            }
            else
            {
                _recorder.SetAudioSamplingRate(8000);
                _recorder.SetAudioEncodingBitRate(12200);
            }

            _recorder.Prepare();
            _recorder.Start();
        }

        public string StopRecord()
        {
            _recorder.Stop();
            _recorder.Reset();
            _recorder.Release();
            return sFileName;
        }

    }
}