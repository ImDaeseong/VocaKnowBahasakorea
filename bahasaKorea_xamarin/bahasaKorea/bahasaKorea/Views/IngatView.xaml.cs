using bahasaKorea.Modal;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace bahasaKorea.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class IngatView : ContentView
    {
        private SingleTurn.UserSetting Setting = SingleTurn.UserSetting.getInstance;
        private SingleTurn.ToSpeak TextToSpeech = SingleTurn.ToSpeak.getInstance;
        private SingleTurn.DBSqlite KataDB = SingleTurn.DBSqlite.getInstance;

        private int nRepeatCount;
        private int nRepeatTime = 0;
        private bool bIsAutoPlay = false;
        private bool bUseSound = false;
        private bool IsSoundPlaying = false;


        private KataItems item;
        private ObservableCollection<KataItems> mCollection;
        private int CurrentPlayIndex;
        private int nTotalPageCount;



        private int SkinType;
        private Color CurSkin;
        private void InitSkinStyle()
        {
            SkinType = Setting.SkinStyle;

            if (SkinType == 1)
                CurSkin = Color.FromHex("#33A7D6");
            else if (SkinType == 2)
                CurSkin = Color.FromHex("#493335");
            else if (SkinType == 3)
                CurSkin = Color.FromHex("#FF80AB");
            else if (SkinType == 4)
                CurSkin = Color.FromHex("#4CAF50");
            else if (SkinType == 5)
                CurSkin = Color.FromHex("#3F51B5");
            else if (SkinType == 6)
                CurSkin = Color.FromHex("#B71C1C");
            else if (SkinType == 7)
                CurSkin = Color.FromHex("#37474F");

            Kataviewin.BackgroundColor = CurSkin;
            upPrePayImage.ButtonColor = CurSkin;
            upNextplayImage.ButtonColor = CurSkin;
        }

        public IngatView()
        {
            InitializeComponent();

            try
            {
                InitSkinStyle();

                TextToSpeech.InitSpeak();

                //사운드 사용 여부
                bUseSound = Setting.IngatRepeatSound;

                DisplayMarquee();

                mCollection = new ObservableCollection<KataItems>(KataDB.GetItems());

                nTotalPageCount = mCollection.Count;

                CurrentKata();

            }
            catch { }

            this.SizeChanged += IngatView_SizeChanged;
        }

        private async void CurrentKata()
        {
            try
            {
                if (CurrentPlayIndex < 0 || mCollection.Count == 0) return;

                item = mCollection[CurrentPlayIndex];
                KataKor.Text = item.KataKor;
                KataIndo.Text = item.KataIndo;
                KataEng.Text = item.KataEng;

                PlaySuaraSound();

                CounterTimerLabel();

            }
            catch { }
        }

        private async void PreKata()
        {
            try
            {
                if (mCollection.Count == 0) return;

                await SwipeAnimateEffect(true);

                CurrentPlayIndex--;

                if (CurrentPlayIndex < 0)
                    CurrentPlayIndex = mCollection.Count - 1;

                item = mCollection[CurrentPlayIndex];
                KataKor.Text = item.KataKor;
                KataIndo.Text = item.KataIndo;
                KataEng.Text = item.KataEng;

                PlaySuaraSound();

                if (!bIsAutoPlay)
                    CounterTimerLabel();

            }
            catch { }
        }

        private async void NextKata()
        {
            try
            {
                if (mCollection.Count == 0) return;

                await SwipeAnimateEffect(false);

                CurrentPlayIndex++;

                if (CurrentPlayIndex > (mCollection.Count - 1))
                    CurrentPlayIndex = 0;

                item = mCollection[CurrentPlayIndex];
                KataKor.Text = item.KataKor;
                KataIndo.Text = item.KataIndo;
                KataEng.Text = item.KataEng;

                PlaySuaraSound();

                if (!bIsAutoPlay)
                    CounterTimerLabel();

            }
            catch { }
        }

        private void DisplayMarquee()
        {
            if (bIsAutoPlay)
            {
                nRepeatTime = Convert.ToInt32(Setting.IngatRepeatTime);

                if (bUseSound)
                    mView.ContentMarquee = string.Format("Sekarang sedang berjalan dengan suara and kata");
                else
                    mView.ContentMarquee = string.Format("Sekarang sedang berjalan secara otomatis setiap {0} detik.", nRepeatTime);
            }
            else
            {
                nRepeatTime = Convert.ToInt32(Setting.IngatRepeatTime);
                mView.ContentMarquee = string.Format("Klik tombol play menjalankan secara otomatis setiap {0} detik.", nRepeatTime);
            }
        }

        private async Task SwipeAnimateEffect(bool bLeft)
        {
            if (bLeft)
            {
                var animate = new Animation();
                var registerin = new Animation(callback: ax => Kataview.TranslationX = ax,
                        start: Width,
                        end: 0,
                        easing: Easing.SpringOut);
                animate.Add(0, 1, registerin);
                animate.Commit(Kataview, "RegisterIn", length: 1000);
            }
            else
            {
                var animate = new Animation();
                var registerin = new Animation(callback: ax => Kataview.TranslationX = ax,
                        start: -Width,
                        end: 0,
                        easing: Easing.SpringOut);
                animate.Add(0, 1, registerin);
                animate.Commit(Kataview, "RegisterIn", length: 1000);
            }
        }

        private async void Kataview_SwipeLeft(object sender, EventArgs e)
        {
            PreKata();

            if (bIsAutoPlay) nRepeatCount = 0;
        }

        private async void Kataview_SwipeRight(object sender, EventArgs e)
        {
            NextKata();

            if (bIsAutoPlay) nRepeatCount = 0;
        }

        private async void TapGestureRecognizer_TappedPrePlayKata(object sender, EventArgs e)
        {
            PreKata();
        }

        private async void TapGestureRecognizer_TappedNextPlayKata(object sender, EventArgs e)
        {
            NextKata();
        }

        private void CounterTimerLabel()
        {
            CounterTimer.TextColor = CurSkin;

            int nDisplayIndex = CurrentPlayIndex + 1;
            CounterTimer.Text = string.Format("Kata saat ini ({0}/{1})", nDisplayIndex, nTotalPageCount);
        }

        public async void RunAutoPlay()
        {
            Device.StartTimer(TimeSpan.FromSeconds(1), () =>
            {
                if (!bIsAutoPlay)
                {
                    nRepeatCount = 0;
                    CounterTimerLabel();
                    return false;
                }

                nRepeatCount++;


                if (nRepeatCount % 2 == 0)
                {
                    CounterTimer.TextColor = CurSkin;
                }
                else
                {
                    CounterTimer.TextColor = Color.Transparent;
                }

                int nDisplayIndex = CurrentPlayIndex + 1;
                CounterTimer.Text = string.Format("{0} detik berlalu ({1}/{2})", nRepeatCount, nDisplayIndex, nTotalPageCount);


                //사운드 사용시
                if (bUseSound)
                {
                    if (IsSoundPlaying) return true;
                }


                if (nRepeatTime <= nRepeatCount)
                {
                    nRepeatCount = 0;
                    NextKata();
                }

                return true;
            });
        }

        private async void TapGestureRecognizer_TappedPlay(object sender, EventArgs e)
        {
            if (!bIsAutoPlay)
            {
                bIsAutoPlay = true;
                playImage.ButtonColor = CurSkin;

                IsVisibleBottomArrow(bIsAutoPlay);

                RunAutoPlay();
            }
            else
            {
                bIsAutoPlay = false;
                playImage.ButtonColor = Color.Gray;

                IsVisibleBottomArrow(bIsAutoPlay);

                RunAutoPlay();
            }

            DisplayMarquee();
        }

        protected override void InvalidateLayout()
        {
            base.InvalidateLayout();
        }

        private void IsVisibleBottomArrow(bool bVisible)
        {
            if (this.Width > this.Height)
            {
                if (bIsAutoPlay)
                {
                    upPrePayImage.IsVisible = false;
                    upNextplayImage.IsVisible = false;
                }
                else
                {
                    upPrePayImage.IsVisible = !bVisible;
                    upNextplayImage.IsVisible = !bVisible;
                }
            }
            else
            {
                upPrePayImage.IsVisible = false;
                upNextplayImage.IsVisible = false;
            }
        }

        private void SetLocationArrow(bool bVisible)
        {
            if (bVisible)
            {
                IsVisibleBottomArrow(false);

                RelativeLayout.SetBoundsConstraint(upPrePayImage,
                BoundsConstraint.FromExpression(
                    () => new Rectangle(
                        relLayout.X + 10,
                        ((relLayout.Height - upPrePayImage.Height) - 10),
                        upPrePayImage.Width,
                        upPrePayImage.Height),
                null));

                RelativeLayout.SetBoundsConstraint(upNextplayImage,
                BoundsConstraint.FromExpression(
                    () => new Rectangle(
                        ((relLayout.Width - upNextplayImage.Width) - 10),
                        ((relLayout.Height - upPrePayImage.Height) - 10),
                        upNextplayImage.Width,
                        upNextplayImage.Height),
                null));
            }
            else
            {
                IsVisibleBottomArrow(true);
            }

            RelativeLayout.SetBoundsConstraint(playImage,
               BoundsConstraint.FromExpression(
                   () => new Rectangle(
                       ((relLayout.Width - playImage.Width) - 1.5),
                       (relLayout.Y + 1.5),
                       playImage.Width,
                       playImage.Height),
               null));
        }

        private void IngatView_SizeChanged(object sender, EventArgs e)
        {
            if (this.Width > this.Height)
            {
                Kataview.WidthRequest = (this.Width - 200);
                Kataview.HeightRequest = (this.Height - 55) + (MarqueeMenu.Height);

                MarqueeMenu.IsVisible = false;

                SetLocationArrow(true);
            }
            else
            {
                Kataview.WidthRequest = (this.Width - 10);
                Kataview.HeightRequest = (this.Height / 2) + ((MarqueeMenu.Height) / 2);

                MarqueeMenu.IsVisible = true;

                SetLocationArrow(false);
            }

            this.UpdateChildrenLayout();
        }

        private async void PlaySuaraSound()
        {
            try
            {
                if (bUseSound)
                {
                    await Task.Delay(1000);

                    TextToSpeech.Speak(KataKor.Text);
                }
            }
            catch { }
        }

        private async Task SlideIn()
        {
            await this.FadeTo(0);
            var animate = new Animation();
            var slidein = new Animation(callback: ax => this.TranslationY = ax,
                    start: -Height,
                    end: 0,
            easing: Easing.SpringOut);
            animate.Add(0, 1, slidein);
            animate.Commit(this, "SlideIn", length: 1000);
            await this.FadeTo(1);
        }

        private void IsShowControls(bool bShow)
        {
            PlayBoard.IsVisible = bShow;
            playImage.IsVisible = bShow;
        }

        protected async override void OnParentSet()
        {
            base.OnParentSet();

            if (Parent != null)
            {
                //Appearing
                await SlideIn();

                //깜박임 방지
                IsShowControls(true);
            }
            else
            {
                //Disappearing
                try
                {
                    //깜박임 방지
                    IsShowControls(false);

                    if (bIsAutoPlay)
                    {
                        bIsAutoPlay = false;
                        playImage.ButtonColor = Color.Gray;
                        upPrePayImage.ButtonColor = Color.Gray;
                        upNextplayImage.ButtonColor = Color.Gray;

                        IsVisibleBottomArrow(bIsAutoPlay);

                        RunAutoPlay();

                        DisplayMarquee();
                    }
                }
                catch { }
            }

        }

    }
}