using System;

namespace bahasaKorea.Interfaces
{
    public interface IUserSetting
    {
        //스킨 타입
        int SkinStyle { get; set; }

        //가나다라... 자동 반복 시간
        int AlfabetRepeatTime { get; set; }

        //가나다라... 음성사용 여부
        bool AlfabetRepeatSound { get; set; }

        //단어장 자동 반복 시간
        int IngatRepeatTime { get; set; }

        //단어장 음성사용 여부
        bool IngatRepeatSound { get; set; }

        //스크린 화면 락 처리여부
        bool ScreenLock { get; set; }

    }
}
