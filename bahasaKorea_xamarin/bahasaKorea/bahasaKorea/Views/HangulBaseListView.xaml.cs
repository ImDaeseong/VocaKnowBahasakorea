using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace bahasaKorea.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class HangulBaseListView : ContentView
    {
        private SingleTurn.UserSetting Setting = SingleTurn.UserSetting.getInstance;


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
        }


        public HangulBaseListView()
        {
            InitializeComponent();

            try
            {
                //스킨 설정
                InitSkinStyle();

                LoadContents();

                //Navigation.PushModalAsync(new Pages.WelcomePage(), true);

            }
            catch { }

            scrollView.Scrolled += ScrollView_Scrolled;
        }

        protected override void OnParentSet()
        {
            base.OnParentSet();

            try
            {
                if (Parent != null)
                {
                    scrollView.ScrollToAsync(grid, ScrollToPosition.Start, true);
                    UpScroll.FadeTo(0);
                    UpScroll.IsVisible = false;
                }
            }
            catch { }
        }

        private async void LoadContents()
        {
            await LoadAlpabatData();
        }

        private async void UpScroll_Clicked(object sender, EventArgs e)
        {
            try
            {
                await scrollView.ScrollToAsync(grid, ScrollToPosition.Start, true);
                await UpScroll.FadeTo(0);
                UpScroll.IsVisible = false;
            }
            catch { }
        }

        private async void ScrollView_Scrolled(object sender, ScrolledEventArgs e)
        {
            try
            {
                if (e.ScrollY > 0)
                {
                    await UpScroll.FadeTo(1);
                    UpScroll.IsVisible = true;
                }
                else
                {
                    await UpScroll.FadeTo(0);
                    UpScroll.IsVisible = false;
                }
            }
            catch { }
        }

        private async Task<bool> LoadAlpabatData()
        {
            string[,] AlpabatList = new string[20, 11]
            {
                {  "", "ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ" },
                {"ㄱ", "가", "갸", "거", "겨", "고", "교", "구", "규", "그", "기" },
                {"ㄴ", "나", "냐", "너", "녀", "노", "뇨", "누", "뉴", "느", "니" },
                {"ㄷ", "다", "댜", "더", "뎌", "도", "됴", "두", "듀", "드", "디" },
                {"ㄹ", "라", "랴", "러", "려", "로", "료", "루", "류", "르", "리" },
                {"ㅁ", "마", "먀", "머", "며", "모", "묘", "무", "뮤", "므", "미" },
                {"ㅂ", "바", "뱌", "버", "벼", "보", "뵤", "부", "뷰", "브", "비" },
                {"ㅅ", "사", "샤", "서", "셔", "소", "쇼", "수", "슈", "스", "시" },
                {"ㅇ", "아", "야", "어", "여", "오", "요", "우", "유", "으", "이" },
                {"ㅈ", "자", "쟈", "저", "져", "조", "죠", "주", "쥬", "즈", "지" },
                {"ㅊ", "차", "챠", "처", "쳐", "초", "쵸", "추", "츄", "츠", "치" },
                {"ㅋ", "카", "캬", "커", "켜", "코", "쿄", "쿠", "큐", "크", "키" },
                {"ㅌ", "타", "탸", "터", "텨", "토", "툐", "투", "튜", "트", "티" },
                {"ㅍ", "파", "퍄", "퍼", "펴", "포", "표", "푸", "퓨", "프", "피" },
                {"ㅎ", "하", "햐", "허", "혀", "호", "효", "후", "휴", "흐", "히" },
                {"ㄲ", "까", "꺄", "꺼", "껴", "꼬", "꾜", "꾸", "뀨", "끄", "끼" },
                {"ㄸ", "따", "땨", "떠", "뗘", "또", "뚀", "뚜", "뜌", "뜨", "띠" },
                {"ㅃ", "빠", "뺘", "뻐", "뼈", "뽀", "뾰", "뿌", "쀼", "쁘", "삐" },
                {"ㅆ", "싸", "쌰", "써", "쎠", "쏘", "쑈", "쑤", "쓔", "쓰", "씨" },
                {"ㅉ", "짜", "쨔", "쩌", "쪄", "쪼", "쬬", "쭈", "쮸", "쯔", "찌" }
            };

            for (int i = 0; i < 20; i++)
            {
                for (int k = 0; k < 11; k++)
                {
                    var item = new HangulBaseListViewCell();
                    item.BindingContext = new HangulDataAlpabet(AlpabatList[i, k]);
                    item.AlpabatText = AlpabatList[i, k];

                    if (i == 0)
                    {
                        if (k == 0)
                            item.clrBgColor = Color.White;
                        else if (k == 1)
                            item.clrBgColor = Color.Gray;
                        else if (k == 2)
                            item.clrBgColor = Color.Gray;
                        else if (k == 3)
                            item.clrBgColor = Color.Gray;
                        else if (k == 4)
                            item.clrBgColor = Color.Gray;
                        else if (k == 5)
                            item.clrBgColor = Color.Gray;
                        else if (k == 6)
                            item.clrBgColor = Color.Gray;
                        else if (k == 7)
                            item.clrBgColor = Color.Gray;
                        else if (k == 8)
                            item.clrBgColor = Color.Gray;
                        else if (k == 9)
                            item.clrBgColor = Color.Gray;
                        else if (k == 10)
                            item.clrBgColor = Color.Gray;
                    }
                    else if (k == 0)
                    {
                        if (i == 1)
                            item.clrBgColor = Color.Gray;
                        else if (i == 2)
                            item.clrBgColor = Color.Gray;
                        else if (i == 3)
                            item.clrBgColor = Color.Gray;
                        else if (i == 4)
                            item.clrBgColor = Color.Gray;
                        else if (i == 5)
                            item.clrBgColor = Color.Gray;
                        else if (i == 6)
                            item.clrBgColor = Color.Gray;
                        else if (i == 7)
                            item.clrBgColor = Color.Gray;
                        else if (i == 8)
                            item.clrBgColor = Color.Gray;
                        else if (i == 9)
                            item.clrBgColor = Color.Gray;
                        else if (i == 10)
                            item.clrBgColor = Color.Gray;
                        else if (i == 11)
                            item.clrBgColor = Color.Gray;
                        else if (i == 12)
                            item.clrBgColor = Color.Gray;
                        else if (i == 13)
                            item.clrBgColor = Color.Gray;
                        else if (i == 14)
                            item.clrBgColor = Color.Gray;
                        else if (i == 15)
                            item.clrBgColor = Color.Gray;
                        else if (i == 16)
                            item.clrBgColor = Color.Gray;
                        else if (i == 17)
                            item.clrBgColor = Color.Gray;
                        else if (i == 18)
                            item.clrBgColor = Color.Gray;
                        else if (i == 19)
                            item.clrBgColor = Color.Gray;
                    }
                    else
                        item.clrBgColor = CurSkin;

                    grid.Children.Add(item, k, i);
                }
            }

            return true;
        }

        public void ChangeSkin()
        {
            try
            {
                //스킨 설정
                InitSkinStyle();

                foreach (var cell in grid.Children)
                {
                    if (((HangulBaseListViewCell)cell).clrBgColor == Color.White)
                        continue;

                    if (((HangulBaseListViewCell)cell).clrBgColor == Color.Gray)
                        continue;

                    ((HangulBaseListViewCell)cell).clrBgColor = CurSkin;
                }
            }
            catch { }
        }

    }

    class HangulDataAlpabet
    {
        public string TextHangul { get; set; }
        public HangulDataAlpabet(string TextHangul)
        {
            this.TextHangul = TextHangul;
        }
    }
}