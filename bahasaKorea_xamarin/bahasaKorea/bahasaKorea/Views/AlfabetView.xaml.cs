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
    public partial class AlfabetView : ContentView
    {
        private SingleTurn.UserSetting Setting = SingleTurn.UserSetting.getInstance;
        private SingleTurn.ToSpeak TextToSpeech = SingleTurn.ToSpeak.getInstance;


        private int nRepeatCount;
        private int nRepeatTime = 0;
        private bool bIsAutoPlay = false;
        private bool bUseSound = false;

        //Dictionary<string, Tuple<string, string>> mCollection;

        private AlfabetItems item;
        private ObservableCollection<AlfabetItems> mCollection;
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

        public AlfabetView()
        {
            InitializeComponent();

            try
            {
                InitSkinStyle();

                TextToSpeech.InitSpeak();

                //사운드 사용 여부
                bUseSound = Setting.AlfabetRepeatSound;

                DisplayMarquee();

                GetLoadData();

                nTotalPageCount = mCollection.Count;

                CurrentKata();

            }
            catch { }

            this.SizeChanged += AlfabetView_SizeChanged;
        }

        private void GetLoadData()
        {
            mCollection = new ObservableCollection<AlfabetItems>()
            {
                 new Modal.AlfabetItems ("가", "GA", "ㄱ+ㅏ"),
                 new Modal.AlfabetItems ("갸", "GYA", "ㄱ+ㅑ"),
                 new Modal.AlfabetItems ("거", "GYEO", "ㄱ+ㅓ"),
                 new Modal.AlfabetItems ("고", "GO", "ㄱ+ㅗ"),
                 new Modal.AlfabetItems ("교", "GYO", "ㄱ+ㅛ"),
                 new Modal.AlfabetItems ("구", "GU", "ㄱ+ㅜ"),
                 new Modal.AlfabetItems ("규", "GYU", "ㄱ+ㅠ"),
                 new Modal.AlfabetItems ("그", "GEU", "ㄱ+ㅡ"),
                 new Modal.AlfabetItems ("기", "GI", "ㄱ+ㅣ"),
                 new Modal.AlfabetItems ("나", "NA", "ㄴ+ㅏ"),
                 new Modal.AlfabetItems ("냐", "NYA", "ㄴ+ㅑ"),
                 new Modal.AlfabetItems ("너", "NEO", "ㄴ+ㅓ"),
                 new Modal.AlfabetItems ("녀", "NYEO", "ㄴ+ㅕ"),
                 new Modal.AlfabetItems ("노", "NO", "ㄴ+ㅗ"),
                 new Modal.AlfabetItems ("뇨", "NYO", "ㄴ+ㅛ"),
                 new Modal.AlfabetItems ("누", "NU", "ㄴ+ㅜ"),
                 new Modal.AlfabetItems ("뉴", "NYU", "ㄴ+ㅠ"),
                 new Modal.AlfabetItems ("느", "NEU", "ㄴ+ㅡ"),
                 new Modal.AlfabetItems ("니", "NI", "ㄴ+ㅣ"),
                 new Modal.AlfabetItems ("다", "DA", "ㄷ+ㅏ"),
                 new Modal.AlfabetItems ("댜", "DYA", "ㄷ+ㅑ"),
                 new Modal.AlfabetItems ("더", "DEO", "ㄷ+ㅓ"),
                 new Modal.AlfabetItems ("뎌", "DYEO", "ㄷ+ㅕ"),
                 new Modal.AlfabetItems ("도", "DO", "ㄷ+ㅗ"),
                 new Modal.AlfabetItems ("됴", "DYO", "ㄷ+ㅛ"),
                 new Modal.AlfabetItems ("두", "DU", "ㄷ+ㅜ"),
                 new Modal.AlfabetItems ("듀", "DYU", "ㄷ+ㅠ"),
                 new Modal.AlfabetItems ("드", "DEU", "ㄷ+ㅡ"),
                 new Modal.AlfabetItems ("디", "DI", "ㄷ+ㅣ"),
                 new Modal.AlfabetItems ("라", "RA", "ㄹ+ㅏ"),
                 new Modal.AlfabetItems ("랴", "RYA", "ㄹ+ㅑ"),
                 new Modal.AlfabetItems ("러", "REO", "ㄹ+ㅓ"),
                 new Modal.AlfabetItems ("려", "RYEO", "ㄹ+ㅕ"),
                 new Modal.AlfabetItems ("로", "RO", "ㄹ+ㅗ"),
                 new Modal.AlfabetItems ("료", "RYO", "ㄹ+ㅛ"),
                 new Modal.AlfabetItems ("루", "RU", "ㄹ+ㅜ"),
                 new Modal.AlfabetItems ("류", "RYU", "ㄹ+ㅠ"),
                 new Modal.AlfabetItems ("르", "REU", "ㄹ+ㅡ"),
                 new Modal.AlfabetItems ("리", "RI", "ㄹ+ㅣ"),
                 new Modal.AlfabetItems ("마", "MA", "ㅁ+ㅏ"),
                 new Modal.AlfabetItems ("먀", "MYA", "ㅁ+ㅑ"),
                 new Modal.AlfabetItems ("머", "MEO", "ㅁ+ㅓ"),
                 new Modal.AlfabetItems ("며", "MYEO", "ㅁ+ㅕ"),
                 new Modal.AlfabetItems ("모", "MO", "ㅁ+ㅗ"),
                 new Modal.AlfabetItems ("묘", "MYO", "ㅁ+ㅛ"),
                 new Modal.AlfabetItems ("무", "MU", "ㅁ+ㅜ"),
                 new Modal.AlfabetItems ("뮤", "MYU", "ㅁ+ㅠ"),
                 new Modal.AlfabetItems ("므", "MEU", "ㅁ+ㅡ"),
                 new Modal.AlfabetItems ("미", "MI", "ㅁ+ㅣ"),
                 new Modal.AlfabetItems ("바", "BA", "ㅂ+ㅏ"),
                 new Modal.AlfabetItems ("뱌", "BYA", "ㅂ+ㅑ"),
                 new Modal.AlfabetItems ("버", "BEO", "ㅂ+ㅓ"),
                 new Modal.AlfabetItems ("벼", "BYEO", "ㅂ+ㅕ"),
                 new Modal.AlfabetItems ("보", "BO", "ㅂ+ㅗ"),
                 new Modal.AlfabetItems ("뵤", "BYO", "ㅂ+ㅛ"),
                 new Modal.AlfabetItems ("부", "BU", "ㅂ+ㅜ"),
                 new Modal.AlfabetItems ("뷰", "BYU", "ㅂ+ㅠ"),
                 new Modal.AlfabetItems ("브", "BEU", "ㅂ+ㅡ"),
                 new Modal.AlfabetItems ("비", "BI", "ㅂ+ㅣ"),
                 new Modal.AlfabetItems ("사", "SA", "ㅅ+ㅏ"),
                 new Modal.AlfabetItems ("샤", "SYA", "ㅅ+ㅑ"),
                 new Modal.AlfabetItems ("서", "SEO", "ㅅ+ㅓ"),
                 new Modal.AlfabetItems ("셔", "SYEO", "ㅅ+ㅕ"),
                 new Modal.AlfabetItems ("소", "SO", "ㅅ+ㅗ"),
                 new Modal.AlfabetItems ("쇼", "SYO", "ㅅ+ㅛ"),
                 new Modal.AlfabetItems ("수", "SU", "ㅅ+ㅜ"),
                 new Modal.AlfabetItems ("슈", "SYU", "ㅅ+ㅠ"),
                 new Modal.AlfabetItems ("스", "SEU", "ㅅ+ㅡ"),
                 new Modal.AlfabetItems ("시", "SI", "ㅅ+ㅣ"),
                 new Modal.AlfabetItems ("아", "A", "ㅇ+ㅏ"),
                 new Modal.AlfabetItems ("야", "YA", "ㅇ+ㅑ"),
                 new Modal.AlfabetItems ("어", "EO", "ㅇ+ㅓ"),
                 new Modal.AlfabetItems ("여", "YEO", "ㅇ+ㅕ"),
                 new Modal.AlfabetItems ("오", "O", "ㅇ+ㅗ"),
                 new Modal.AlfabetItems ("요", "YO", "ㅇ+ㅛ"),
                 new Modal.AlfabetItems ("우", "U", "ㅇ+ㅜ"),
                 new Modal.AlfabetItems ("유", "YU", "ㅇ+ㅠ"),
                 new Modal.AlfabetItems ("으", "EU", "ㅇ+ㅡ"),
                 new Modal.AlfabetItems ("이", "I", "ㅇ+ㅣ"),
                 new Modal.AlfabetItems ("자", "JA", "ㅈ+ㅏ"),
                 new Modal.AlfabetItems ("쟈", "JYA", "ㅈ+ㅑ"),
                 new Modal.AlfabetItems ("저", "JEO", "ㅈ+ㅓ"),
                 new Modal.AlfabetItems ("져", "JYEO", "ㅈ+ㅕ"),
                 new Modal.AlfabetItems ("조", "JO", "ㅈ+ㅗ"),
                 new Modal.AlfabetItems ("죠", "JYO", "ㅈ+ㅛ"),
                 new Modal.AlfabetItems ("주", "JU", "ㅈ+ㅜ"),
                 new Modal.AlfabetItems ("쥬", "JYU", "ㅈ+ㅠ"),
                 new Modal.AlfabetItems ("즈", "JEU", "ㅈ+ㅡ"),
                 new Modal.AlfabetItems ("지", "JI", "ㅈ+ㅣ"),
                 new Modal.AlfabetItems ("차", "CHA", "ㅊ+ㅏ"),
                 new Modal.AlfabetItems ("챠", "CHYA", "ㅊ+ㅑ"),
                 new Modal.AlfabetItems ("처", "CHEO", "ㅊ+ㅓ"),
                 new Modal.AlfabetItems ("쳐", "CHYEO", "ㅊ+ㅕ"),
                 new Modal.AlfabetItems ("초", "CHO", "ㅊ+ㅗ"),
                 new Modal.AlfabetItems ("쵸", "CHYO", "ㅊ+ㅛ"),
                 new Modal.AlfabetItems ("추", "CHU", "ㅊ+ㅜ"),
                 new Modal.AlfabetItems ("츄", "CHYU", "ㅊ+ㅠ"),
                 new Modal.AlfabetItems ("츠", "CHEU", "ㅊ+ㅡ"),
                 new Modal.AlfabetItems ("치", "CHI", "ㅊ+ㅣ"),
                 new Modal.AlfabetItems ("카", "KA", "ㅋ+ㅏ"),
                 new Modal.AlfabetItems ("캬", "KYA", "ㅋ+ㅑ"),
                 new Modal.AlfabetItems ("커", "KEO", "ㅋ+ㅓ"),
                 new Modal.AlfabetItems ("켜", "KYEO", "ㅋ+ㅕ"),
                 new Modal.AlfabetItems ("코", "KO", "ㅋ+ㅗ"),
                 new Modal.AlfabetItems ("쿄", "KYO", "ㅋ+ㅛ"),
                 new Modal.AlfabetItems ("쿠", "KU", "ㅋ+ㅜ"),
                 new Modal.AlfabetItems ("큐", "KYU", "ㅋ+ㅠ"),
                 new Modal.AlfabetItems ("크", "KEU", "ㅋ+ㅡ"),
                 new Modal.AlfabetItems ("키", "KI", "ㅋ+ㅣ"),
                 new Modal.AlfabetItems ("타", "TA", "ㅌ+ㅏ"),
                 new Modal.AlfabetItems ("탸", "TYA", "ㅌ+ㅑ"),
                 new Modal.AlfabetItems ("터", "TEO", "ㅌ+ㅓ"),
                 new Modal.AlfabetItems ("텨", "TYEO", "ㅌ+ㅕ"),
                 new Modal.AlfabetItems ("토", "TO", "ㅌ+ㅗ"),
                 new Modal.AlfabetItems ("툐", "TYO", "ㅌ+ㅛ"),
                 new Modal.AlfabetItems ("투", "TU", "ㅌ+ㅜ"),
                 new Modal.AlfabetItems ("튜", "TYU", "ㅌ+ㅠ"),
                 new Modal.AlfabetItems ("트", "TEU", "ㅌ+ㅡ"),
                 new Modal.AlfabetItems ("티", "TI", "ㅌ+ㅣ"),
                 new Modal.AlfabetItems ("파", "PA", "ㅍ+ㅏ"),
                 new Modal.AlfabetItems ("퍄", "PYA", "ㅍ+ㅑ"),
                 new Modal.AlfabetItems ("퍼", "PEO", "ㅍ+ㅓ"),
                 new Modal.AlfabetItems ("펴", "PYEO", "ㅍ+ㅕ"),
                 new Modal.AlfabetItems ("포", "PO", "ㅍ+ㅗ"),
                 new Modal.AlfabetItems ("표", "PYO", "ㅍ+ㅛ"),
                 new Modal.AlfabetItems ("푸", "PU", "ㅍ+ㅜ"),
                 new Modal.AlfabetItems ("퓨", "PYU", "ㅍ+ㅠ"),
                 new Modal.AlfabetItems ("프", "PEU", "ㅍ+ㅡ"),
                 new Modal.AlfabetItems ("피", "PI", "ㅍ+ㅣ"),
                 new Modal.AlfabetItems ("하", "HA", "ㅎ+ㅏ"),
                 new Modal.AlfabetItems ("햐", "HYA", "ㅎ+ㅑ"),
                 new Modal.AlfabetItems ("허", "HEO", "ㅎ+ㅓ"),
                 new Modal.AlfabetItems ("혀", "HYEO", "ㅎ+ㅕ"),
                 new Modal.AlfabetItems ("호", "HO", "ㅎ+ㅗ"),
                 new Modal.AlfabetItems ("효", "HYO", "ㅎ+ㅛ"),
                 new Modal.AlfabetItems ("후", "HU", "ㅎ+ㅜ"),
                 new Modal.AlfabetItems ("휴", "HYU", "ㅎ+ㅠ"),
                 new Modal.AlfabetItems ("흐", "HEU", "ㅎ+ㅡ"),
                 new Modal.AlfabetItems ("히", "HI", "ㅎ+ㅣ"),
                 new Modal.AlfabetItems ("까", "KA", "ㄱ+ㄱ+ㅏ"),
                 new Modal.AlfabetItems ("꺄", "KYA", "ㄱ+ㄱ+ㅑ"),
                 new Modal.AlfabetItems ("꺼", "KEO", "ㄱ+ㄱ+ㅓ"),
                 new Modal.AlfabetItems ("껴", "KYEO", "ㄱ+ㄱ+ㅕ"),
                 new Modal.AlfabetItems ("꼬", "KO", "ㄱ+ㄱ+ㅗ"),
                 new Modal.AlfabetItems ("꾜", "KYO", "ㄱ+ㄱ+ㅛ"),
                 new Modal.AlfabetItems ("꾸", "KU", "ㄱ+ㄱ+ㅜ"),
                 new Modal.AlfabetItems ("뀨", "KYU", "ㄱ+ㄱ+ㅠ"),
                 new Modal.AlfabetItems ("끄", "KEU", "ㄱ+ㄱ+ㅡ"),
                 new Modal.AlfabetItems ("끼", "KI", "ㄱ+ㄱ+ㅣ"),
                 new Modal.AlfabetItems ("따", "TA", "ㄷ+ㄷ+ㅏ"),
                 new Modal.AlfabetItems ("땨", "TYA", "ㄷ+ㄷ+ㅑ"),
                 new Modal.AlfabetItems ("떠", "TEO", "ㄷ+ㄷ+ㅓ"),
                 new Modal.AlfabetItems ("뗘", "TYEO", "ㄷ+ㄷ+ㅕ"),
                 new Modal.AlfabetItems ("또", "TO", "ㄷ+ㄷ+ㅗ"),
                 new Modal.AlfabetItems ("뚀", "TYO", "ㄷ+ㄷ+ㅛ"),
                 new Modal.AlfabetItems ("뚜", "TU", "ㄷ+ㄷ+ㅜ"),
                 new Modal.AlfabetItems ("뜌", "TYU", "ㄷ+ㄷ+ㅠ"),
                 new Modal.AlfabetItems ("뜨", "TEU", "ㄷ+ㄷ+ㅡ"),
                 new Modal.AlfabetItems ("띠", "TI", "ㄷ+ㄷ+ㅣ"),
                 new Modal.AlfabetItems ("빠", "PA", "ㅂ+ㅂ+ㅏ"),
                 new Modal.AlfabetItems ("뺘", "PYA", "ㅂ+ㅂ+ㅑ"),
                 new Modal.AlfabetItems ("뻐", "PEO", "ㅂ+ㅂ+ㅓ"),
                 new Modal.AlfabetItems ("뼈", "PYEO", "ㅂ+ㅂ+ㅕ"),
                 new Modal.AlfabetItems ("뽀", "PO", "ㅂ+ㅂ+ㅗ"),
                 new Modal.AlfabetItems ("뾰", "PYO", "ㅂ+ㅂ+ㅛ"),
                 new Modal.AlfabetItems ("뿌", "PU", "ㅂ+ㅂ+ㅜ"),
                 new Modal.AlfabetItems ("쀼", "PYU", "ㅂ+ㅂ+ㅠ"),
                 new Modal.AlfabetItems ("쁘", "PEU", "ㅂ+ㅂ+ㅡ"),
                 new Modal.AlfabetItems ("삐", "PI", "ㅂ+ㅂ+ㅣ"),
                 new Modal.AlfabetItems ("싸", "SA", "ㅅ+ㅅ+ㅏ"),
                 new Modal.AlfabetItems ("쌰", "SYA", "ㅅ+ㅅ+ㅑ"),
                 new Modal.AlfabetItems ("써", "SEO", "ㅅ+ㅅ+ㅓ"),
                 new Modal.AlfabetItems ("쎠", "SYEO", "ㅅ+ㅅ+ㅕ"),
                 new Modal.AlfabetItems ("쏘", "SO", "ㅅ+ㅅ+ㅗ"),
                 new Modal.AlfabetItems ("쑈", "SYO", "ㅅ+ㅅ+ㅛ"),
                 new Modal.AlfabetItems ("쑤", "SU", "ㅅ+ㅅ+ㅜ"),
                 new Modal.AlfabetItems ("쓔", "SYU", "ㅅ+ㅅ+ㅠ"),
                 new Modal.AlfabetItems ("쓰", "SEU", "ㅅ+ㅅ+ㅡ"),
                 new Modal.AlfabetItems ("씨", "SI", "ㅅ+ㅅ+ㅣ"),
                 new Modal.AlfabetItems ("짜", "CHA", "ㅈ+ㅈ+ㅏ"),
                 new Modal.AlfabetItems ("쨔", "CHYA", "ㅈ+ㅈ+ㅑ"),
                 new Modal.AlfabetItems ("쩌", "CHEO", "ㅈ+ㅈ+ㅓ"),
                 new Modal.AlfabetItems ("쪄", "CHYEO", "ㅈ+ㅈ+ㅕ"),
                 new Modal.AlfabetItems ("쪼", "CHO", "ㅈ+ㅈ+ㅗ"),
                 new Modal.AlfabetItems ("쬬", "CHYO", "ㅈ+ㅈ+ㅛ"),
                 new Modal.AlfabetItems ("쭈", "CHU", "ㅈ+ㅈ+ㅜ"),
                 new Modal.AlfabetItems ("쮸", "CHYU", "ㅈ+ㅈ+ㅠ"),
                 new Modal.AlfabetItems ("쯔", "CHEU", "ㅈ+ㅈ+ㅡ"),
                 new Modal.AlfabetItems ("찌", "CHI", "ㅈ+ㅈ+ㅣ")
            };

            /*
            mCollection = new Dictionary<string, Tuple<string, string>>()
            {
                {"가", new Tuple<string, string> ( "GA", "ㄱ+ㅏ")},
                {"갸", new Tuple<string, string> ( "GYA", "ㄱ+ㅑ")},
                {"거", new Tuple<string, string> ( "GYEO", "ㄱ+ㅓ")},
                {"고", new Tuple<string, string> ( "GO", "ㄱ+ㅗ")},
                {"교", new Tuple<string, string> ( "GYO", "ㄱ+ㅛ")},
                {"구", new Tuple<string, string> ( "GU", "ㄱ+ㅜ")},
                {"규", new Tuple<string, string> ( "GYU", "ㄱ+ㅠ")},
                {"그", new Tuple<string, string> ( "GEU", "ㄱ+ㅡ")},
                {"기", new Tuple<string, string> ( "GI", "ㄱ+ㅣ")},
                {"나", new Tuple<string, string> ( "NA", "ㄴ+ㅏ")},
                {"냐", new Tuple<string, string> ( "NYA", "ㄴ+ㅑ")},
                {"너", new Tuple<string, string> ( "NEO", "ㄴ+ㅓ")},
                {"녀", new Tuple<string, string> ( "NYEO", "ㄴ+ㅕ")},
                {"노", new Tuple<string, string> ( "NO", "ㄴ+ㅗ")},
                {"뇨", new Tuple<string, string> ( "NYO", "ㄴ+ㅛ")},
                {"누", new Tuple<string, string> ( "NU", "ㄴ+ㅜ")},
                {"뉴", new Tuple<string, string> ( "NYU", "ㄴ+ㅠ")},
                {"느", new Tuple<string, string> ( "NEU", "ㄴ+ㅡ")},
                {"니", new Tuple<string, string> ( "NI", "ㄴ+ㅣ")},
                {"다", new Tuple<string, string> ( "DA", "ㄷ+ㅏ")},
                {"댜", new Tuple<string, string> ( "DYA", "ㄷ+ㅑ")},
                {"더", new Tuple<string, string> ( "DEO", "ㄷ+ㅓ")},
                {"뎌", new Tuple<string, string> ( "DYEO", "ㄷ+ㅕ")},
                {"도", new Tuple<string, string> ( "DO", "ㄷ+ㅗ")},
                {"됴", new Tuple<string, string> ( "DYO", "ㄷ+ㅛ")},
                {"두", new Tuple<string, string> ( "DU", "ㄷ+ㅜ")},
                {"듀", new Tuple<string, string> ( "DYU", "ㄷ+ㅠ")},
                {"드", new Tuple<string, string> ( "DEU", "ㄷ+ㅡ")},
                {"디", new Tuple<string, string> ( "DI", "ㄷ+ㅣ")},
                {"라", new Tuple<string, string> ( "RA", "ㄹ+ㅏ")},
                {"랴", new Tuple<string, string> ( "RYA", "ㄹ+ㅑ")},
                {"러", new Tuple<string, string> ( "REO", "ㄹ+ㅓ")},
                {"려", new Tuple<string, string> ( "RYEO", "ㄹ+ㅕ")},
                {"로", new Tuple<string, string> ( "RO", "ㄹ+ㅗ")},
                {"료", new Tuple<string, string> ( "RYO", "ㄹ+ㅛ")},
                {"루", new Tuple<string, string> ( "RU", "ㄹ+ㅜ")},
                {"류", new Tuple<string, string> ( "RYU", "ㄹ+ㅠ")},
                {"르", new Tuple<string, string> ( "REU", "ㄹ+ㅡ")},
                {"리", new Tuple<string, string> ( "RI", "ㄹ+ㅣ")},
                {"마", new Tuple<string, string> ( "MA", "ㅁ+ㅏ")},
                {"먀", new Tuple<string, string> ( "MYA", "ㅁ+ㅑ")},
                {"머", new Tuple<string, string> ( "MEO", "ㅁ+ㅓ")},
                {"며", new Tuple<string, string> ( "MYEO", "ㅁ+ㅕ")},
                {"모", new Tuple<string, string> ( "MO", "ㅁ+ㅗ")},
                {"묘", new Tuple<string, string> ( "MYO", "ㅁ+ㅛ")},
                {"무", new Tuple<string, string> ( "MU", "ㅁ+ㅜ")},
                {"뮤", new Tuple<string, string> ( "MYU", "ㅁ+ㅠ")},
                {"므", new Tuple<string, string> ( "MEU", "ㅁ+ㅡ")},
                {"미", new Tuple<string, string> ( "MI", "ㅁ+ㅣ")},
                {"바", new Tuple<string, string> ( "BA", "ㅂ+ㅏ")},
                {"뱌", new Tuple<string, string> ( "BYA", "ㅂ+ㅑ")},
                {"버", new Tuple<string, string> ( "BEO", "ㅂ+ㅓ")},
                {"벼", new Tuple<string, string> ( "BYEO", "ㅂ+ㅕ")},
                {"보", new Tuple<string, string> ( "BO", "ㅂ+ㅗ")},
                {"뵤", new Tuple<string, string> ( "BYO", "ㅂ+ㅛ")},
                {"부", new Tuple<string, string> ( "BU", "ㅂ+ㅜ")},
                {"뷰", new Tuple<string, string> ( "BYU", "ㅂ+ㅠ")},
                {"브", new Tuple<string, string> ( "BEU", "ㅂ+ㅡ")},
                {"비", new Tuple<string, string> ( "BI", "ㅂ+ㅣ")},
                {"사", new Tuple<string, string> ( "SA", "ㅅ+ㅏ")},
                {"샤", new Tuple<string, string> ( "SYA", "ㅅ+ㅑ")},
                {"서", new Tuple<string, string> ( "SEO", "ㅅ+ㅓ")},
                {"셔", new Tuple<string, string> ( "SYEO", "ㅅ+ㅕ")},
                {"소", new Tuple<string, string> ( "SO", "ㅅ+ㅗ")},
                {"쇼", new Tuple<string, string> ( "SYO", "ㅅ+ㅛ")},
                {"수", new Tuple<string, string> ( "SU", "ㅅ+ㅜ")},
                {"슈", new Tuple<string, string> ( "SYU", "ㅅ+ㅠ")},
                {"스", new Tuple<string, string> ( "SEU", "ㅅ+ㅡ")},
                {"시", new Tuple<string, string> ( "SI", "ㅅ+ㅣ")},
                {"아", new Tuple<string, string> ( "A", "ㅇ+ㅏ")},
                {"야", new Tuple<string, string> ( "YA", "ㅇ+ㅑ")},
                {"어", new Tuple<string, string> ( "EO", "ㅇ+ㅓ")},
                {"여", new Tuple<string, string> ( "YEO", "ㅇ+ㅕ")},
                {"오", new Tuple<string, string> ( "O", "ㅇ+ㅗ")},
                {"요", new Tuple<string, string> ( "YO", "ㅇ+ㅛ")},
                {"우", new Tuple<string, string> ( "U", "ㅇ+ㅜ")},
                {"유", new Tuple<string, string> ( "YU", "ㅇ+ㅠ")},
                {"으", new Tuple<string, string> ( "EU", "ㅇ+ㅡ")},
                {"이", new Tuple<string, string> ( "I", "ㅇ+ㅣ")},
                {"자", new Tuple<string, string> ( "JA", "ㅈ+ㅏ")},
                {"쟈", new Tuple<string, string> ( "JYA", "ㅈ+ㅑ")},
                {"저", new Tuple<string, string> ( "JEO", "ㅈ+ㅓ")},
                {"져", new Tuple<string, string> ( "JYEO", "ㅈ+ㅕ")},
                {"조", new Tuple<string, string> ( "JO", "ㅈ+ㅗ")},
                {"죠", new Tuple<string, string> ( "JYO", "ㅈ+ㅛ")},
                {"주", new Tuple<string, string> ( "JU", "ㅈ+ㅜ")},
                {"쥬", new Tuple<string, string> ( "JYU", "ㅈ+ㅠ")},
                {"즈", new Tuple<string, string> ( "JEU", "ㅈ+ㅡ")},
                {"지", new Tuple<string, string> ( "JI", "ㅈ+ㅣ")},
                {"차", new Tuple<string, string> ( "CHA", "ㅊ+ㅏ")},
                {"챠", new Tuple<string, string> ( "CHYA", "ㅊ+ㅑ")},
                {"처", new Tuple<string, string> ( "CHEO", "ㅊ+ㅓ")},
                {"쳐", new Tuple<string, string> ( "CHYEO", "ㅊ+ㅕ")},
                {"초", new Tuple<string, string> ( "CHO", "ㅊ+ㅗ")},
                {"쵸", new Tuple<string, string> ( "CHYO", "ㅊ+ㅛ")},
                {"추", new Tuple<string, string> ( "CHU", "ㅊ+ㅜ")},
                {"츄", new Tuple<string, string> ( "CHYU", "ㅊ+ㅠ")},
                {"츠", new Tuple<string, string> ( "CHEU", "ㅊ+ㅡ")},
                {"치", new Tuple<string, string> ( "CHI", "ㅊ+ㅣ")},
                {"카", new Tuple<string, string> ( "KA", "ㅋ+ㅏ")},
                {"캬", new Tuple<string, string> ( "KYA", "ㅋ+ㅑ")},
                {"커", new Tuple<string, string> ( "KEO", "ㅋ+ㅓ")},
                {"켜", new Tuple<string, string> ( "KYEO", "ㅋ+ㅕ")},
                {"코", new Tuple<string, string> ( "KO", "ㅋ+ㅗ")},
                {"쿄", new Tuple<string, string> ( "KYO", "ㅋ+ㅛ")},
                {"쿠", new Tuple<string, string> ( "KU", "ㅋ+ㅜ")},
                {"큐", new Tuple<string, string> ( "KYU", "ㅋ+ㅠ")},
                {"크", new Tuple<string, string> ( "KEU", "ㅋ+ㅡ")},
                {"키", new Tuple<string, string> ( "KI", "ㅋ+ㅣ")},
                {"타", new Tuple<string, string> ( "TA", "ㅌ+ㅏ")},
                {"탸", new Tuple<string, string> ( "TYA", "ㅌ+ㅑ")},
                {"터", new Tuple<string, string> ( "TEO", "ㅌ+ㅓ")},
                {"텨", new Tuple<string, string> ( "TYEO", "ㅌ+ㅕ")},
                {"토", new Tuple<string, string> ( "TO", "ㅌ+ㅗ")},
                {"툐", new Tuple<string, string> ( "TYO", "ㅌ+ㅛ")},
                {"투", new Tuple<string, string> ( "TU", "ㅌ+ㅜ")},
                {"튜", new Tuple<string, string> ( "TYU", "ㅌ+ㅠ")},
                {"트", new Tuple<string, string> ( "TEU", "ㅌ+ㅡ")},
                {"티", new Tuple<string, string> ( "TI", "ㅌ+ㅣ")},
                {"파", new Tuple<string, string> ( "PA", "ㅍ+ㅏ")},
                {"퍄", new Tuple<string, string> ( "PYA", "ㅍ+ㅑ")},
                {"퍼", new Tuple<string, string> ( "PEO", "ㅍ+ㅓ")},
                {"펴", new Tuple<string, string> ( "PYEO", "ㅍ+ㅕ")},
                {"포", new Tuple<string, string> ( "PO", "ㅍ+ㅗ")},
                {"표", new Tuple<string, string> ( "PYO", "ㅍ+ㅛ")},
                {"푸", new Tuple<string, string> ( "PU", "ㅍ+ㅜ")},
                {"퓨", new Tuple<string, string> ( "PYU", "ㅍ+ㅠ")},
                {"프", new Tuple<string, string> ( "PEU", "ㅍ+ㅡ")},
                {"피", new Tuple<string, string> ( "PI", "ㅍ+ㅣ")},
                {"하", new Tuple<string, string> ( "HA", "ㅎ+ㅏ")},
                {"햐", new Tuple<string, string> ( "HYA", "ㅎ+ㅑ")},
                {"허", new Tuple<string, string> ( "HEO", "ㅎ+ㅓ")},
                {"혀", new Tuple<string, string> ( "HYEO", "ㅎ+ㅕ")},
                {"호", new Tuple<string, string> ( "HO", "ㅎ+ㅗ")},
                {"효", new Tuple<string, string> ( "HYO", "ㅎ+ㅛ")},
                {"후", new Tuple<string, string> ( "HU", "ㅎ+ㅜ")},
                {"휴", new Tuple<string, string> ( "HYU", "ㅎ+ㅠ")},
                {"흐", new Tuple<string, string> ( "HEU", "ㅎ+ㅡ")},
                {"히", new Tuple<string, string> ( "HI", "ㅎ+ㅣ")},
                {"까", new Tuple<string, string> ( "KA", "ㄱ+ㄱ+ㅏ")},
                {"꺄", new Tuple<string, string> ( "KYA", "ㄱ+ㄱ+ㅑ")},
                {"꺼", new Tuple<string, string> ( "KEO", "ㄱ+ㄱ+ㅓ")},
                {"껴", new Tuple<string, string> ( "KYEO", "ㄱ+ㄱ+ㅕ")},
                {"꼬", new Tuple<string, string> ( "KO", "ㄱ+ㄱ+ㅗ")},
                {"꾜", new Tuple<string, string> ( "KYO", "ㄱ+ㄱ+ㅛ")},
                {"꾸", new Tuple<string, string> ( "KU", "ㄱ+ㄱ+ㅜ")},
                {"뀨", new Tuple<string, string> ( "KYU", "ㄱ+ㄱ+ㅠ")},
                {"끄", new Tuple<string, string> ( "KEU", "ㄱ+ㄱ+ㅡ")},
                {"끼", new Tuple<string, string> ( "KI", "ㄱ+ㄱ+ㅣ")},
                {"따", new Tuple<string, string> ( "TA", "ㄷ+ㄷ+ㅏ")},
                {"땨", new Tuple<string, string> ( "TYA", "ㄷ+ㄷ+ㅑ")},
                {"떠", new Tuple<string, string> ( "TEO", "ㄷ+ㄷ+ㅓ")},
                {"뗘", new Tuple<string, string> ( "TYEO", "ㄷ+ㄷ+ㅕ")},
                {"또", new Tuple<string, string> ( "TO", "ㄷ+ㄷ+ㅗ")},
                {"뚀", new Tuple<string, string> ( "TYO", "ㄷ+ㄷ+ㅛ")},
                {"뚜", new Tuple<string, string> ("TU", "ㄷ+ㄷ+ㅜ")},
                {"뚜", new Tuple<string, string> ( "TU", "ㄷ+ㄷ+ㅜ")},
                {"뜌", new Tuple<string, string> ( "TYU", "ㄷ+ㄷ+ㅠ")},
                {"뜨", new Tuple<string, string> ( "TEU", "ㄷ+ㄷ+ㅡ")},
                {"띠", new Tuple<string, string> ( "TI", "ㄷ+ㄷ+ㅣ")},
                {"빠", new Tuple<string, string> ( "PA", "ㅂ+ㅂ+ㅏ")},
                {"뺘", new Tuple<string, string> ( "PYA", "ㅂ+ㅂ+ㅑ")},
                {"뻐", new Tuple<string, string> ( "PEO", "ㅂ+ㅂ+ㅓ")},
                {"뼈", new Tuple<string, string> ( "PYEO", "ㅂ+ㅂ+ㅕ")},
                {"뽀", new Tuple<string, string> ( "PO", "ㅂ+ㅂ+ㅗ")},
                {"뾰", new Tuple<string, string> ( "PYO", "ㅂ+ㅂ+ㅛ")},
                {"뿌", new Tuple<string, string> ( "PU", "ㅂ+ㅂ+ㅜ")},
                {"쀼", new Tuple<string, string> ( "PYU", "ㅂ+ㅂ+ㅠ")},
                {"쁘", new Tuple<string, string> ( "PEU", "ㅂ+ㅂ+ㅡ")},
                {"삐", new Tuple<string, string> ( "PI", "ㅂ+ㅂ+ㅣ")},
                {"싸", new Tuple<string, string> ( "SA", "ㅅ+ㅅ+ㅏ")},
                {"쌰", new Tuple<string, string> ( "SYA", "ㅅ+ㅅ+ㅑ")},
                {"써", new Tuple<string, string> ( "SEO", "ㅅ+ㅅ+ㅓ")},
                {"쎠", new Tuple<string, string> ( "SYEO", "ㅅ+ㅅ+ㅕ")},
                {"쏘", new Tuple<string, string> ( "SO", "ㅅ+ㅅ+ㅗ")},
                {"쑈", new Tuple<string, string> ( "SYO", "ㅅ+ㅅ+ㅛ")},
                {"쑤", new Tuple<string, string> ( "SU", "ㅅ+ㅅ+ㅜ")},
                {"쓔", new Tuple<string, string> ( "SYU", "ㅅ+ㅅ+ㅠ")},
                {"쓰", new Tuple<string, string> ( "SEU", "ㅅ+ㅅ+ㅡ")},
                {"씨", new Tuple<string, string> ( "SI", "ㅅ+ㅅ+ㅣ")},
                {"짜", new Tuple<string, string> ( "CHA", "ㅈ+ㅈ+ㅏ")},
                {"쨔", new Tuple<string, string> ( "CHYA", "ㅈ+ㅈ+ㅑ")},
                {"쩌", new Tuple<string, string> ( "CHEO", "ㅈ+ㅈ+ㅓ")},
                {"쪄", new Tuple<string, string> ( "CHYEO", "ㅈ+ㅈ+ㅕ")},
                {"쪼", new Tuple<string, string> ( "CHO", "ㅈ+ㅈ+ㅗ")},
                {"쬬", new Tuple<string, string> ( "CHYO", "ㅈ+ㅈ+ㅛ")},
                {"쭈", new Tuple<string, string> ( "CHU", "ㅈ+ㅈ+ㅜ")},
                {"쮸", new Tuple<string, string> ( "CHYU", "ㅈ+ㅈ+ㅠ")},
                {"쯔", new Tuple<string, string> ( "CHEU", "ㅈ+ㅈ+ㅡ")},
                {"찌", new Tuple<string, string> ( "CHI", "ㅈ+ㅈ+ㅣ")}
            };
            */
        }

        private async void CurrentKata()
        {
            try
            {
                if (CurrentPlayIndex < 0 || mCollection.Count == 0) return;

                item = mCollection[CurrentPlayIndex];
                KataKor.Text = item.KataKor;
                Lembutlidah.Text = item.Lembutlidah;
                BuatKan.Text = item.BuatKan;

                CounterTimerLabel();

                PlaySuaraSound(KataKor.Text);
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
                Lembutlidah.Text = item.Lembutlidah;
                BuatKan.Text = item.BuatKan;

                if (!bIsAutoPlay)
                    CounterTimerLabel();

                PlaySuaraSound(KataKor.Text);
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
                Lembutlidah.Text = item.Lembutlidah;
                BuatKan.Text = item.BuatKan;

                if (!bIsAutoPlay)
                    CounterTimerLabel();

                PlaySuaraSound(KataKor.Text);
            }
            catch { }
        }

        private async void PlaySuaraSound(string sText)
        {
            try
            {
                if (bUseSound)
                {
                    await Task.Delay(1000);

                    TextToSpeech.Speak(sText);
                }
            }
            catch { }
        }

        private void DisplayMarquee()
        {
            if (bIsAutoPlay)
            {
                nRepeatTime = Convert.ToInt32(Setting.AlfabetRepeatTime);
                mView.ContentMarquee = string.Format("Sekarang sedang berjalan secara otomatis setiap {0} detik.", nRepeatTime);
            }
            else
            {
                nRepeatTime = Convert.ToInt32(Setting.AlfabetRepeatTime);
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
            CounterTimer.Text = string.Format("Halaman saat ini ({0}/{1})", nDisplayIndex, nTotalPageCount);
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
                    if (TextToSpeech.IsSpeaking) return true;
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

        private void AlfabetView_SizeChanged(object sender, EventArgs e)
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

        protected override void OnSizeAllocated(double width, double height)
        {
            base.OnSizeAllocated(width, height);

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
                //깜박임 방지
                IsShowControls(false);

                //Disappearing
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
        }

    }
}