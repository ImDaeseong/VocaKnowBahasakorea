using AVFoundation;
using bahasaKorea.Interfaces;
using bahasaKorea.iOS.Interfaces;
using Foundation;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using Xamarin.Forms;

[assembly: Dependency(typeof(CAudioRecorder))]
namespace bahasaKorea.iOS.Interfaces
{
    public class CAudioRecorder : IAudioRecorder
    {
        AVAudioRecorder recorder;
        NSError error;
        NSUrl url;
        NSDictionary settings;
        string sFileName;

        public CAudioRecorder()
        {
        }

        public void StartRecord(string sFileName)
        {
            if (recorder != null)
            {
                recorder.PrepareToRecord();
                recorder.Record();
            }
            else
            {
                SetupRecorder(sFileName);

                recorder.PrepareToRecord();
                recorder.Record();
            }
        }

        public string StopRecord()
        {
            recorder.Stop();
            recorder.Dispose();
            recorder = null;
            return sFileName;
        }

        private void SetupRecorder(string sFileName)
        {
            var audioSession = AVAudioSession.SharedInstance();
            var err = audioSession.SetCategory(AVAudioSessionCategory.PlayAndRecord);
            if (err != null)
            {
                //Console.WriteLine("audioSession: {0}", err);
                //return false;
            }

            err = audioSession.SetActive(true);
            if (err != null)
            {
                //Console.WriteLine("audioSession: {0}", err);
                //return false;
            }

            this.sFileName = sFileName;
            //Declare string for application temp path and tack on the file extension
            //string fileName = string.Format("{0}.wav", DateTime.Now.ToString("yyyyMMddHHmmss"));
            string audioFilePath = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.Personal), sFileName);

            url = NSUrl.FromFilename(audioFilePath);
            //set up the NSObject Array of values that will be combined with the keys to make the NSDictionary
            NSObject[] values = new NSObject[]
            {
                NSNumber.FromFloat (44100.0f), //Sample Rate
                NSNumber.FromInt32 ((int)AudioToolbox.AudioFormatType.MPEG4AAC), //AVFormat
                NSNumber.FromInt32 (2), //Channels
                NSNumber.FromInt32 (16), //PCMBitDepth
                NSNumber.FromInt32((int) AVAudioQuality.High),
                NSNumber.FromBoolean (false), //IsBigEndianKey
                NSNumber.FromBoolean (false) //IsFloatKey
            };

            //Set up the NSObject Array of keys that will be combined with the values to make the NSDictionary
            NSObject[] keys = new NSObject[]
            {
                AVAudioSettings.AVSampleRateKey,
                AVAudioSettings.AVFormatIDKey,
                AVAudioSettings.AVNumberOfChannelsKey,
                AVAudioSettings.AVEncoderAudioQualityKey,
                AVAudioSettings.AVLinearPCMBitDepthKey,
                AVAudioSettings.AVLinearPCMIsBigEndianKey,
                AVAudioSettings.AVLinearPCMIsFloatKey,
            };

            //Set Settings with the Values and Keys to create the NSDictionary
            settings = NSDictionary.FromObjectsAndKeys(values, keys);

            //Set recorder parameters
            this.recorder = AVAudioRecorder.Create(url, new AudioSettings(settings), out error);
        }

    }
}
