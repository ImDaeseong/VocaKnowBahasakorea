package com.daeseong.bahasakorea.BahasaAPI

import com.daeseong.bahasakorea.Common.HangulItem
import java.util.*

class hangulApi {

    private val hangulItemHashMap: HashMap<Int, HangulItem> = HashMap<Int, HangulItem>()

    companion object {
        private var instance: hangulApi? = null
        fun getInstance(): hangulApi {
            if (instance == null) {
                instance = hangulApi()
            }
            return instance as hangulApi
        }
    }

    fun InitData() {

        try {
            hangulItemHashMap[0] = HangulItem("가", "GA", "ㄱ+ㅏ")
            hangulItemHashMap[1] = HangulItem("갸", "GYA", "ㄱ+ㅑ")
            hangulItemHashMap[2] = HangulItem("거", "GYEO", "ㄱ+ㅓ")
            hangulItemHashMap[3] = HangulItem("고", "GO", "ㄱ+ㅗ")
            hangulItemHashMap[4] = HangulItem("교", "GYO", "ㄱ+ㅛ")
            hangulItemHashMap[5] = HangulItem("구", "GU", "ㄱ+ㅜ")
            hangulItemHashMap[6] = HangulItem("규", "GYU", "ㄱ+ㅠ")
            hangulItemHashMap[7] = HangulItem("그", "GEU", "ㄱ+ㅡ")
            hangulItemHashMap[8] = HangulItem("기", "GI", "ㄱ+ㅣ")
            hangulItemHashMap[9] = HangulItem("나", "NA", "ㄴ+ㅏ")
            hangulItemHashMap[10] = HangulItem("냐", "NYA", "ㄴ+ㅑ")
            hangulItemHashMap[11] = HangulItem("너", "NEO", "ㄴ+ㅓ")
            hangulItemHashMap[12] = HangulItem("녀", "NYEO", "ㄴ+ㅕ")
            hangulItemHashMap[13] = HangulItem("노", "NO", "ㄴ+ㅗ")
            hangulItemHashMap[14] = HangulItem("뇨", "NYO", "ㄴ+ㅛ")
            hangulItemHashMap[15] = HangulItem("누", "NU", "ㄴ+ㅜ")
            hangulItemHashMap[16] = HangulItem("뉴", "NYU", "ㄴ+ㅠ")
            hangulItemHashMap[17] = HangulItem("느", "NEU", "ㄴ+ㅡ")
            hangulItemHashMap[18] = HangulItem("니", "NI", "ㄴ+ㅣ")
            hangulItemHashMap[19] = HangulItem("다", "DA", "ㄷ+ㅏ")
            hangulItemHashMap[20] = HangulItem("댜", "DYA", "ㄷ+ㅑ")
            hangulItemHashMap[21] = HangulItem("더", "DEO", "ㄷ+ㅓ")
            hangulItemHashMap[22] = HangulItem("뎌", "DYEO", "ㄷ+ㅕ")
            hangulItemHashMap[23] = HangulItem("도", "DO", "ㄷ+ㅗ")
            hangulItemHashMap[24] = HangulItem("됴", "DYO", "ㄷ+ㅛ")
            hangulItemHashMap[25] = HangulItem("두", "DU", "ㄷ+ㅜ")
            hangulItemHashMap[26] = HangulItem("듀", "DYU", "ㄷ+ㅠ")
            hangulItemHashMap[27] = HangulItem("드", "DEU", "ㄷ+ㅡ")
            hangulItemHashMap[28] = HangulItem("디", "DI", "ㄷ+ㅣ")
            hangulItemHashMap[29] = HangulItem("라", "RA", "ㄹ+ㅏ")
            hangulItemHashMap[30] = HangulItem("랴", "RYA", "ㄹ+ㅑ")
            hangulItemHashMap[31] = HangulItem("러", "REO", "ㄹ+ㅓ")
            hangulItemHashMap[32] = HangulItem("려", "RYEO", "ㄹ+ㅕ")
            hangulItemHashMap[33] = HangulItem("로", "RO", "ㄹ+ㅗ")
            hangulItemHashMap[34] = HangulItem("료", "RYO", "ㄹ+ㅛ")
            hangulItemHashMap[35] = HangulItem("루", "RU", "ㄹ+ㅜ")
            hangulItemHashMap[36] = HangulItem("류", "RYU", "ㄹ+ㅠ")
            hangulItemHashMap[37] = HangulItem("르", "REU", "ㄹ+ㅡ")
            hangulItemHashMap[38] = HangulItem("리", "RI", "ㄹ+ㅣ")
            hangulItemHashMap[39] = HangulItem("마", "MA", "ㅁ+ㅏ")
            hangulItemHashMap[40] = HangulItem("먀", "MYA", "ㅁ+ㅑ")
            hangulItemHashMap[41] = HangulItem("머", "MEO", "ㅁ+ㅓ")
            hangulItemHashMap[42] = HangulItem("며", "MYEO", "ㅁ+ㅕ")
            hangulItemHashMap[43] = HangulItem("모", "MO", "ㅁ+ㅗ")
            hangulItemHashMap[44] = HangulItem("묘", "MYO", "ㅁ+ㅛ")
            hangulItemHashMap[45] = HangulItem("무", "MU", "ㅁ+ㅜ")
            hangulItemHashMap[46] = HangulItem("뮤", "MYU", "ㅁ+ㅠ")
            hangulItemHashMap[47] = HangulItem("므", "MEU", "ㅁ+ㅡ")
            hangulItemHashMap[48] = HangulItem("미", "MI", "ㅁ+ㅣ")
            hangulItemHashMap[49] = HangulItem("바", "BA", "ㅂ+ㅏ")
            hangulItemHashMap[50] = HangulItem("뱌", "BYA", "ㅂ+ㅑ")
            hangulItemHashMap[51] = HangulItem("버", "BEO", "ㅂ+ㅓ")
            hangulItemHashMap[52] = HangulItem("벼", "BYEO", "ㅂ+ㅕ")
            hangulItemHashMap[53] = HangulItem("보", "BO", "ㅂ+ㅗ")
            hangulItemHashMap[54] = HangulItem("뵤", "BYO", "ㅂ+ㅛ")
            hangulItemHashMap[55] = HangulItem("부", "BU", "ㅂ+ㅜ")
            hangulItemHashMap[56] = HangulItem("뷰", "BYU", "ㅂ+ㅠ")
            hangulItemHashMap[57] = HangulItem("브", "BEU", "ㅂ+ㅡ")
            hangulItemHashMap[58] = HangulItem("비", "BI", "ㅂ+ㅣ")
            hangulItemHashMap[59] = HangulItem("사", "SA", "ㅅ+ㅏ")
            hangulItemHashMap[60] = HangulItem("샤", "SYA", "ㅅ+ㅑ")
            hangulItemHashMap[61] = HangulItem("서", "SEO", "ㅅ+ㅓ")
            hangulItemHashMap[62] = HangulItem("셔", "SYEO", "ㅅ+ㅕ")
            hangulItemHashMap[63] = HangulItem("소", "SO", "ㅅ+ㅗ")
            hangulItemHashMap[64] = HangulItem("쇼", "SYO", "ㅅ+ㅛ")
            hangulItemHashMap[65] = HangulItem("수", "SU", "ㅅ+ㅜ")
            hangulItemHashMap[66] = HangulItem("슈", "SYU", "ㅅ+ㅠ")
            hangulItemHashMap[67] = HangulItem("스", "SEU", "ㅅ+ㅡ")
            hangulItemHashMap[68] = HangulItem("시", "SI", "ㅅ+ㅣ")
            hangulItemHashMap[69] = HangulItem("아", "A", "ㅇ+ㅏ")
            hangulItemHashMap[70] = HangulItem("야", "YA", "ㅇ+ㅑ")
            hangulItemHashMap[71] = HangulItem("어", "EO", "ㅇ+ㅓ")
            hangulItemHashMap[72] = HangulItem("여", "YEO", "ㅇ+ㅕ")
            hangulItemHashMap[73] = HangulItem("오", "O", "ㅇ+ㅗ")
            hangulItemHashMap[74] = HangulItem("요", "YO", "ㅇ+ㅛ")
            hangulItemHashMap[75] = HangulItem("우", "U", "ㅇ+ㅜ")
            hangulItemHashMap[76] = HangulItem("유", "YU", "ㅇ+ㅠ")
            hangulItemHashMap[77] = HangulItem("으", "EU", "ㅇ+ㅡ")
            hangulItemHashMap[78] = HangulItem("이", "I", "ㅇ+ㅣ")
            hangulItemHashMap[79] = HangulItem("자", "JA", "ㅈ+ㅏ")
            hangulItemHashMap[80] = HangulItem("쟈", "JYA", "ㅈ+ㅑ")
            hangulItemHashMap[81] = HangulItem("저", "JEO", "ㅈ+ㅓ")
            hangulItemHashMap[82] = HangulItem("져", "JYEO", "ㅈ+ㅕ")
            hangulItemHashMap[83] = HangulItem("조", "JO", "ㅈ+ㅗ")
            hangulItemHashMap[84] = HangulItem("죠", "JYO", "ㅈ+ㅛ")
            hangulItemHashMap[85] = HangulItem("주", "JU", "ㅈ+ㅜ")
            hangulItemHashMap[86] = HangulItem("쥬", "JYU", "ㅈ+ㅠ")
            hangulItemHashMap[87] = HangulItem("즈", "JEU", "ㅈ+ㅡ")
            hangulItemHashMap[88] = HangulItem("지", "JI", "ㅈ+ㅣ")
            hangulItemHashMap[89] = HangulItem("차", "CHA", "ㅊ+ㅏ")
            hangulItemHashMap[90] = HangulItem("챠", "CHYA", "ㅊ+ㅑ")
            hangulItemHashMap[91] = HangulItem("처", "CHEO", "ㅊ+ㅓ")
            hangulItemHashMap[92] = HangulItem("쳐", "CHYEO", "ㅊ+ㅕ")
            hangulItemHashMap[93] = HangulItem("초", "CHO", "ㅊ+ㅗ")
            hangulItemHashMap[94] = HangulItem("쵸", "CHYO", "ㅊ+ㅛ")
            hangulItemHashMap[95] = HangulItem("추", "CHU", "ㅊ+ㅜ")
            hangulItemHashMap[96] = HangulItem("츄", "CHYU", "ㅊ+ㅠ")
            hangulItemHashMap[97] = HangulItem("츠", "CHEU", "ㅊ+ㅡ")
            hangulItemHashMap[98] = HangulItem("치", "CHI", "ㅊ+ㅣ")
            hangulItemHashMap[99] = HangulItem("카", "KA", "ㅋ+ㅏ")
            hangulItemHashMap[100] = HangulItem("캬", "KYA", "ㅋ+ㅑ")
            hangulItemHashMap[101] = HangulItem("커", "KEO", "ㅋ+ㅓ")
            hangulItemHashMap[102] = HangulItem("켜", "KYEO", "ㅋ+ㅕ")
            hangulItemHashMap[103] = HangulItem("코", "KO", "ㅋ+ㅗ")
            hangulItemHashMap[104] = HangulItem("쿄", "KYO", "ㅋ+ㅛ")
            hangulItemHashMap[105] = HangulItem("쿠", "KU", "ㅋ+ㅜ")
            hangulItemHashMap[106] = HangulItem("큐", "KYU", "ㅋ+ㅠ")
            hangulItemHashMap[107] = HangulItem("크", "KEU", "ㅋ+ㅡ")
            hangulItemHashMap[108] = HangulItem("키", "KI", "ㅋ+ㅣ")
            hangulItemHashMap[109] = HangulItem("타", "TA", "ㅌ+ㅏ")
            hangulItemHashMap[110] = HangulItem("탸", "TYA", "ㅌ+ㅑ")
            hangulItemHashMap[111] = HangulItem("터", "TEO", "ㅌ+ㅓ")
            hangulItemHashMap[112] = HangulItem("텨", "TYEO", "ㅌ+ㅕ")
            hangulItemHashMap[113] = HangulItem("토", "TO", "ㅌ+ㅗ")
            hangulItemHashMap[114] = HangulItem("툐", "TYO", "ㅌ+ㅛ")
            hangulItemHashMap[115] = HangulItem("투", "TU", "ㅌ+ㅜ")
            hangulItemHashMap[116] = HangulItem("튜", "TYU", "ㅌ+ㅠ")
            hangulItemHashMap[117] = HangulItem("트", "TEU", "ㅌ+ㅡ")
            hangulItemHashMap[118] = HangulItem("티", "TI", "ㅌ+ㅣ")
            hangulItemHashMap[119] = HangulItem("파", "PA", "ㅍ+ㅏ")
            hangulItemHashMap[120] = HangulItem("퍄", "PYA", "ㅍ+ㅑ")
            hangulItemHashMap[121] = HangulItem("퍼", "PEO", "ㅍ+ㅓ")
            hangulItemHashMap[122] = HangulItem("펴", "PYEO", "ㅍ+ㅕ")
            hangulItemHashMap[123] = HangulItem("포", "PO", "ㅍ+ㅗ")
            hangulItemHashMap[124] = HangulItem("표", "PYO", "ㅍ+ㅛ")
            hangulItemHashMap[125] = HangulItem("푸", "PU", "ㅍ+ㅜ")
            hangulItemHashMap[126] = HangulItem("퓨", "PYU", "ㅍ+ㅠ")
            hangulItemHashMap[127] = HangulItem("프", "PEU", "ㅍ+ㅡ")
            hangulItemHashMap[128] = HangulItem("피", "PI", "ㅍ+ㅣ")
            hangulItemHashMap[129] = HangulItem("하", "HA", "ㅎ+ㅏ")
            hangulItemHashMap[130] = HangulItem("햐", "HYA", "ㅎ+ㅑ")
            hangulItemHashMap[131] = HangulItem("허", "HEO", "ㅎ+ㅓ")
            hangulItemHashMap[132] = HangulItem("혀", "HYEO", "ㅎ+ㅕ")
            hangulItemHashMap[133] = HangulItem("호", "HO", "ㅎ+ㅗ")
            hangulItemHashMap[134] = HangulItem("효", "HYO", "ㅎ+ㅛ")
            hangulItemHashMap[135] = HangulItem("후", "HU", "ㅎ+ㅜ")
            hangulItemHashMap[136] = HangulItem("휴", "HYU", "ㅎ+ㅠ")
            hangulItemHashMap[137] = HangulItem("흐", "HEU", "ㅎ+ㅡ")
            hangulItemHashMap[138] = HangulItem("히", "HI", "ㅎ+ㅣ")
            hangulItemHashMap[139] = HangulItem("까", "KA", "ㄱ+ㄱ+ㅏ")
            hangulItemHashMap[140] = HangulItem("꺄", "KYA", "ㄱ+ㄱ+ㅑ")
            hangulItemHashMap[141] = HangulItem("꺼", "KEO", "ㄱ+ㄱ+ㅓ")
            hangulItemHashMap[142] = HangulItem("껴", "KYEO", "ㄱ+ㄱ+ㅕ")
            hangulItemHashMap[143] = HangulItem("꼬", "KO", "ㄱ+ㄱ+ㅗ")
            hangulItemHashMap[144] = HangulItem("꾜", "KYO", "ㄱ+ㄱ+ㅛ")
            hangulItemHashMap[145] = HangulItem("꾸", "KU", "ㄱ+ㄱ+ㅜ")
            hangulItemHashMap[146] = HangulItem("뀨", "KYU", "ㄱ+ㄱ+ㅠ")
            hangulItemHashMap[147] = HangulItem("끄", "KEU", "ㄱ+ㄱ+ㅡ")
            hangulItemHashMap[148] = HangulItem("끼", "KI", "ㄱ+ㄱ+ㅣ")
            hangulItemHashMap[149] = HangulItem("따", "TA", "ㄷ+ㄷ+ㅏ")
            hangulItemHashMap[150] = HangulItem("땨", "TYA", "ㄷ+ㄷ+ㅑ")
            hangulItemHashMap[151] = HangulItem("떠", "TEO", "ㄷ+ㄷ+ㅓ")
            hangulItemHashMap[152] = HangulItem("뗘", "TYEO", "ㄷ+ㄷ+ㅕ")
            hangulItemHashMap[153] = HangulItem("또", "TO", "ㄷ+ㄷ+ㅗ")
            hangulItemHashMap[154] = HangulItem("뚀", "TYO", "ㄷ+ㄷ+ㅛ")
            hangulItemHashMap[155] = HangulItem("뚜", "TU", "ㄷ+ㄷ+ㅜ")
            hangulItemHashMap[156] = HangulItem("뜌", "TYU", "ㄷ+ㄷ+ㅠ")
            hangulItemHashMap[157] = HangulItem("뜨", "TEU", "ㄷ+ㄷ+ㅡ")
            hangulItemHashMap[158] = HangulItem("띠", "TI", "ㄷ+ㄷ+ㅣ")
            hangulItemHashMap[159] = HangulItem("빠", "PA", "ㅂ+ㅂ+ㅏ")
            hangulItemHashMap[160] = HangulItem("뺘", "PYA", "ㅂ+ㅂ+ㅑ")
            hangulItemHashMap[161] = HangulItem("뻐", "PEO", "ㅂ+ㅂ+ㅓ")
            hangulItemHashMap[162] = HangulItem("뼈", "PYEO", "ㅂ+ㅂ+ㅕ")
            hangulItemHashMap[163] = HangulItem("뽀", "PO", "ㅂ+ㅂ+ㅗ")
            hangulItemHashMap[164] = HangulItem("뾰", "PYO", "ㅂ+ㅂ+ㅛ")
            hangulItemHashMap[165] = HangulItem("뿌", "PU", "ㅂ+ㅂ+ㅜ")
            hangulItemHashMap[166] = HangulItem("쀼", "PYU", "ㅂ+ㅂ+ㅠ")
            hangulItemHashMap[167] = HangulItem("쁘", "PEU", "ㅂ+ㅂ+ㅡ")
            hangulItemHashMap[168] = HangulItem("삐", "PI", "ㅂ+ㅂ+ㅣ")
            hangulItemHashMap[169] = HangulItem("싸", "SA", "ㅅ+ㅅ+ㅏ")
            hangulItemHashMap[170] = HangulItem("쌰", "SYA", "ㅅ+ㅅ+ㅑ")
            hangulItemHashMap[171] = HangulItem("써", "SEO", "ㅅ+ㅅ+ㅓ")
            hangulItemHashMap[172] = HangulItem("쎠", "SYEO", "ㅅ+ㅅ+ㅕ")
            hangulItemHashMap[173] = HangulItem("쏘", "SO", "ㅅ+ㅅ+ㅗ")
            hangulItemHashMap[174] = HangulItem("쑈", "SYO", "ㅅ+ㅅ+ㅛ")
            hangulItemHashMap[175] = HangulItem("쑤", "SU", "ㅅ+ㅅ+ㅜ")
            hangulItemHashMap[176] = HangulItem("쓔", "SYU", "ㅅ+ㅅ+ㅠ")
            hangulItemHashMap[177] = HangulItem("쓰", "SEU", "ㅅ+ㅅ+ㅡ")
            hangulItemHashMap[178] = HangulItem("씨", "SI", "ㅅ+ㅅ+ㅣ")
            hangulItemHashMap[179] = HangulItem("짜", "CHA", "ㅈ+ㅈ+ㅏ")
            hangulItemHashMap[180] = HangulItem("쨔", "CHYA", "ㅈ+ㅈ+ㅑ")
            hangulItemHashMap[181] = HangulItem("쩌", "CHEO", "ㅈ+ㅈ+ㅓ")
            hangulItemHashMap[182] = HangulItem("쪄", "CHYEO", "ㅈ+ㅈ+ㅕ")
            hangulItemHashMap[183] = HangulItem("쪼", "CHO", "ㅈ+ㅈ+ㅗ")
            hangulItemHashMap[184] = HangulItem("쬬", "CHYO", "ㅈ+ㅈ+ㅛ")
            hangulItemHashMap[185] = HangulItem("쭈", "CHU", "ㅈ+ㅈ+ㅜ")
            hangulItemHashMap[186] = HangulItem("쮸", "CHYU", "ㅈ+ㅈ+ㅠ")
            hangulItemHashMap[187] = HangulItem("쯔", "CHEU", "ㅈ+ㅈ+ㅡ")
            hangulItemHashMap[188] = HangulItem("찌", "CHI", "ㅈ+ㅈ+ㅣ")
        } catch (e: Exception) {
        }
    }

    operator fun get(nItem: Int): HangulItem? {
        return hangulItemHashMap[nItem]
    }

    fun size(): Int {
        return hangulItemHashMap.size
    }

    fun clear() {
        hangulItemHashMap.clear()
    }
}
