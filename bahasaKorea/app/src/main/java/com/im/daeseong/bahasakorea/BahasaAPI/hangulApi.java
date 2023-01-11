package com.im.daeseong.bahasakorea.BahasaAPI;

import com.im.daeseong.bahasakorea.Common.HangulItem;
import java.util.HashMap;

public class hangulApi {

    private static hangulApi mApi = null;

    private HashMap<Integer, HangulItem> hangulItemHashMap;

    public hangulApi(){
        hangulItemHashMap = new HashMap<Integer, HangulItem>();
    }

    public static hangulApi getInstance() {
        if(mApi == null){
            mApi = new hangulApi();
        }
        return mApi;
    }

    public void InitData(){

        try {
            hangulItemHashMap.put(0, new HangulItem("가", "GA", "ㄱ+ㅏ"));
            hangulItemHashMap.put(1, new HangulItem("갸", "GYA", "ㄱ+ㅑ"));
            hangulItemHashMap.put(2, new HangulItem("거", "GYEO", "ㄱ+ㅓ"));
            hangulItemHashMap.put(3, new HangulItem("고", "GO", "ㄱ+ㅗ"));
            hangulItemHashMap.put(4, new HangulItem("교", "GYO", "ㄱ+ㅛ"));
            hangulItemHashMap.put(5, new HangulItem("구", "GU", "ㄱ+ㅜ"));
            hangulItemHashMap.put(6, new HangulItem("규", "GYU", "ㄱ+ㅠ"));
            hangulItemHashMap.put(7, new HangulItem("그", "GEU", "ㄱ+ㅡ"));
            hangulItemHashMap.put(8, new HangulItem("기", "GI", "ㄱ+ㅣ"));
            hangulItemHashMap.put(9, new HangulItem("나", "NA", "ㄴ+ㅏ"));
            hangulItemHashMap.put(10, new HangulItem("냐", "NYA", "ㄴ+ㅑ"));
            hangulItemHashMap.put(11, new HangulItem("너", "NEO", "ㄴ+ㅓ"));
            hangulItemHashMap.put(12, new HangulItem("녀", "NYEO", "ㄴ+ㅕ"));
            hangulItemHashMap.put(13, new HangulItem("노", "NO", "ㄴ+ㅗ"));
            hangulItemHashMap.put(14, new HangulItem("뇨", "NYO", "ㄴ+ㅛ"));
            hangulItemHashMap.put(15, new HangulItem("누", "NU", "ㄴ+ㅜ"));
            hangulItemHashMap.put(16, new HangulItem("뉴", "NYU", "ㄴ+ㅠ"));
            hangulItemHashMap.put(17, new HangulItem("느", "NEU", "ㄴ+ㅡ"));
            hangulItemHashMap.put(18, new HangulItem("니", "NI", "ㄴ+ㅣ"));
            hangulItemHashMap.put(19, new HangulItem("다", "DA", "ㄷ+ㅏ"));
            hangulItemHashMap.put(20, new HangulItem("댜", "DYA", "ㄷ+ㅑ"));
            hangulItemHashMap.put(21, new HangulItem("더", "DEO", "ㄷ+ㅓ"));
            hangulItemHashMap.put(22, new HangulItem("뎌", "DYEO", "ㄷ+ㅕ"));
            hangulItemHashMap.put(23, new HangulItem("도", "DO", "ㄷ+ㅗ"));
            hangulItemHashMap.put(24, new HangulItem("됴", "DYO", "ㄷ+ㅛ"));
            hangulItemHashMap.put(25, new HangulItem("두", "DU", "ㄷ+ㅜ"));
            hangulItemHashMap.put(26, new HangulItem("듀", "DYU", "ㄷ+ㅠ"));
            hangulItemHashMap.put(27, new HangulItem("드", "DEU", "ㄷ+ㅡ"));
            hangulItemHashMap.put(28, new HangulItem("디", "DI", "ㄷ+ㅣ"));
            hangulItemHashMap.put(29, new HangulItem("라", "RA", "ㄹ+ㅏ"));
            hangulItemHashMap.put(30, new HangulItem("랴", "RYA", "ㄹ+ㅑ"));
            hangulItemHashMap.put(31, new HangulItem("러", "REO", "ㄹ+ㅓ"));
            hangulItemHashMap.put(32, new HangulItem("려", "RYEO", "ㄹ+ㅕ"));
            hangulItemHashMap.put(33, new HangulItem("로", "RO", "ㄹ+ㅗ"));
            hangulItemHashMap.put(34, new HangulItem("료", "RYO", "ㄹ+ㅛ"));
            hangulItemHashMap.put(35, new HangulItem("루", "RU", "ㄹ+ㅜ"));
            hangulItemHashMap.put(36, new HangulItem("류", "RYU", "ㄹ+ㅠ"));
            hangulItemHashMap.put(37, new HangulItem("르", "REU", "ㄹ+ㅡ"));
            hangulItemHashMap.put(38, new HangulItem("리", "RI", "ㄹ+ㅣ"));
            hangulItemHashMap.put(39, new HangulItem("마", "MA", "ㅁ+ㅏ"));
            hangulItemHashMap.put(40, new HangulItem("먀", "MYA", "ㅁ+ㅑ"));
            hangulItemHashMap.put(41, new HangulItem("머", "MEO", "ㅁ+ㅓ"));
            hangulItemHashMap.put(42, new HangulItem("며", "MYEO", "ㅁ+ㅕ"));
            hangulItemHashMap.put(43, new HangulItem("모", "MO", "ㅁ+ㅗ"));
            hangulItemHashMap.put(44, new HangulItem("묘", "MYO", "ㅁ+ㅛ"));
            hangulItemHashMap.put(45, new HangulItem("무", "MU", "ㅁ+ㅜ"));
            hangulItemHashMap.put(46, new HangulItem("뮤", "MYU", "ㅁ+ㅠ"));
            hangulItemHashMap.put(47, new HangulItem("므", "MEU", "ㅁ+ㅡ"));
            hangulItemHashMap.put(48, new HangulItem("미", "MI", "ㅁ+ㅣ"));
            hangulItemHashMap.put(49, new HangulItem("바", "BA", "ㅂ+ㅏ"));
            hangulItemHashMap.put(50, new HangulItem("뱌", "BYA", "ㅂ+ㅑ"));
            hangulItemHashMap.put(51, new HangulItem("버", "BEO", "ㅂ+ㅓ"));
            hangulItemHashMap.put(52, new HangulItem("벼", "BYEO", "ㅂ+ㅕ"));
            hangulItemHashMap.put(53, new HangulItem("보", "BO", "ㅂ+ㅗ"));
            hangulItemHashMap.put(54, new HangulItem("뵤", "BYO", "ㅂ+ㅛ"));
            hangulItemHashMap.put(55, new HangulItem("부", "BU", "ㅂ+ㅜ"));
            hangulItemHashMap.put(56, new HangulItem("뷰", "BYU", "ㅂ+ㅠ"));
            hangulItemHashMap.put(57, new HangulItem("브", "BEU", "ㅂ+ㅡ"));
            hangulItemHashMap.put(58, new HangulItem("비", "BI", "ㅂ+ㅣ"));
            hangulItemHashMap.put(59, new HangulItem("사", "SA", "ㅅ+ㅏ"));
            hangulItemHashMap.put(60, new HangulItem("샤", "SYA", "ㅅ+ㅑ"));
            hangulItemHashMap.put(61, new HangulItem("서", "SEO", "ㅅ+ㅓ"));
            hangulItemHashMap.put(62, new HangulItem("셔", "SYEO", "ㅅ+ㅕ"));
            hangulItemHashMap.put(63, new HangulItem("소", "SO", "ㅅ+ㅗ"));
            hangulItemHashMap.put(64, new HangulItem("쇼", "SYO", "ㅅ+ㅛ"));
            hangulItemHashMap.put(65, new HangulItem("수", "SU", "ㅅ+ㅜ"));
            hangulItemHashMap.put(66, new HangulItem("슈", "SYU", "ㅅ+ㅠ"));
            hangulItemHashMap.put(67, new HangulItem("스", "SEU", "ㅅ+ㅡ"));
            hangulItemHashMap.put(68, new HangulItem("시", "SI", "ㅅ+ㅣ"));
            hangulItemHashMap.put(69, new HangulItem("아", "A", "ㅇ+ㅏ"));
            hangulItemHashMap.put(70, new HangulItem("야", "YA", "ㅇ+ㅑ"));
            hangulItemHashMap.put(71, new HangulItem("어", "EO", "ㅇ+ㅓ"));
            hangulItemHashMap.put(72, new HangulItem("여", "YEO", "ㅇ+ㅕ"));
            hangulItemHashMap.put(73, new HangulItem("오", "O", "ㅇ+ㅗ"));
            hangulItemHashMap.put(74, new HangulItem("요", "YO", "ㅇ+ㅛ"));
            hangulItemHashMap.put(75, new HangulItem("우", "U", "ㅇ+ㅜ"));
            hangulItemHashMap.put(76, new HangulItem("유", "YU", "ㅇ+ㅠ"));
            hangulItemHashMap.put(77, new HangulItem("으", "EU", "ㅇ+ㅡ"));
            hangulItemHashMap.put(78, new HangulItem("이", "I", "ㅇ+ㅣ"));
            hangulItemHashMap.put(79, new HangulItem("자", "JA", "ㅈ+ㅏ"));
            hangulItemHashMap.put(80, new HangulItem("쟈", "JYA", "ㅈ+ㅑ"));
            hangulItemHashMap.put(81, new HangulItem("저", "JEO", "ㅈ+ㅓ"));
            hangulItemHashMap.put(82, new HangulItem("져", "JYEO", "ㅈ+ㅕ"));
            hangulItemHashMap.put(83, new HangulItem("조", "JO", "ㅈ+ㅗ"));
            hangulItemHashMap.put(84, new HangulItem("죠", "JYO", "ㅈ+ㅛ"));
            hangulItemHashMap.put(85, new HangulItem("주", "JU", "ㅈ+ㅜ"));
            hangulItemHashMap.put(86, new HangulItem("쥬", "JYU", "ㅈ+ㅠ"));
            hangulItemHashMap.put(87, new HangulItem("즈", "JEU", "ㅈ+ㅡ"));
            hangulItemHashMap.put(88, new HangulItem("지", "JI", "ㅈ+ㅣ"));
            hangulItemHashMap.put(89, new HangulItem("차", "CHA", "ㅊ+ㅏ"));
            hangulItemHashMap.put(90, new HangulItem("챠", "CHYA", "ㅊ+ㅑ"));
            hangulItemHashMap.put(91, new HangulItem("처", "CHEO", "ㅊ+ㅓ"));
            hangulItemHashMap.put(92, new HangulItem("쳐", "CHYEO", "ㅊ+ㅕ"));
            hangulItemHashMap.put(93, new HangulItem("초", "CHO", "ㅊ+ㅗ"));
            hangulItemHashMap.put(94, new HangulItem("쵸", "CHYO", "ㅊ+ㅛ"));
            hangulItemHashMap.put(95, new HangulItem("추", "CHU", "ㅊ+ㅜ"));
            hangulItemHashMap.put(96, new HangulItem("츄", "CHYU", "ㅊ+ㅠ"));
            hangulItemHashMap.put(97, new HangulItem("츠", "CHEU", "ㅊ+ㅡ"));
            hangulItemHashMap.put(98, new HangulItem("치", "CHI", "ㅊ+ㅣ"));
            hangulItemHashMap.put(99, new HangulItem("카", "KA", "ㅋ+ㅏ"));
            hangulItemHashMap.put(100, new HangulItem("캬", "KYA", "ㅋ+ㅑ"));
            hangulItemHashMap.put(101, new HangulItem("커", "KEO", "ㅋ+ㅓ"));
            hangulItemHashMap.put(102, new HangulItem("켜", "KYEO", "ㅋ+ㅕ"));
            hangulItemHashMap.put(103, new HangulItem("코", "KO", "ㅋ+ㅗ"));
            hangulItemHashMap.put(104, new HangulItem("쿄", "KYO", "ㅋ+ㅛ"));
            hangulItemHashMap.put(105, new HangulItem("쿠", "KU", "ㅋ+ㅜ"));
            hangulItemHashMap.put(106, new HangulItem("큐", "KYU", "ㅋ+ㅠ"));
            hangulItemHashMap.put(107, new HangulItem("크", "KEU", "ㅋ+ㅡ"));
            hangulItemHashMap.put(108, new HangulItem("키", "KI", "ㅋ+ㅣ"));
            hangulItemHashMap.put(109, new HangulItem("타", "TA", "ㅌ+ㅏ"));
            hangulItemHashMap.put(110, new HangulItem("탸", "TYA", "ㅌ+ㅑ"));
            hangulItemHashMap.put(111, new HangulItem("터", "TEO", "ㅌ+ㅓ"));
            hangulItemHashMap.put(112, new HangulItem("텨", "TYEO", "ㅌ+ㅕ"));
            hangulItemHashMap.put(113, new HangulItem("토", "TO", "ㅌ+ㅗ"));
            hangulItemHashMap.put(114, new HangulItem("툐", "TYO", "ㅌ+ㅛ"));
            hangulItemHashMap.put(115, new HangulItem("투", "TU", "ㅌ+ㅜ"));
            hangulItemHashMap.put(116, new HangulItem("튜", "TYU", "ㅌ+ㅠ"));
            hangulItemHashMap.put(117, new HangulItem("트", "TEU", "ㅌ+ㅡ"));
            hangulItemHashMap.put(118, new HangulItem("티", "TI", "ㅌ+ㅣ"));
            hangulItemHashMap.put(119, new HangulItem("파", "PA", "ㅍ+ㅏ"));
            hangulItemHashMap.put(120, new HangulItem("퍄", "PYA", "ㅍ+ㅑ"));
            hangulItemHashMap.put(121, new HangulItem("퍼", "PEO", "ㅍ+ㅓ"));
            hangulItemHashMap.put(122, new HangulItem("펴", "PYEO", "ㅍ+ㅕ"));
            hangulItemHashMap.put(123, new HangulItem("포", "PO", "ㅍ+ㅗ"));
            hangulItemHashMap.put(124, new HangulItem("표", "PYO", "ㅍ+ㅛ"));
            hangulItemHashMap.put(125, new HangulItem("푸", "PU", "ㅍ+ㅜ"));
            hangulItemHashMap.put(126, new HangulItem("퓨", "PYU", "ㅍ+ㅠ"));
            hangulItemHashMap.put(127, new HangulItem("프", "PEU", "ㅍ+ㅡ"));
            hangulItemHashMap.put(128, new HangulItem("피", "PI", "ㅍ+ㅣ"));
            hangulItemHashMap.put(129, new HangulItem("하", "HA", "ㅎ+ㅏ"));
            hangulItemHashMap.put(130, new HangulItem("햐", "HYA", "ㅎ+ㅑ"));
            hangulItemHashMap.put(131, new HangulItem("허", "HEO", "ㅎ+ㅓ"));
            hangulItemHashMap.put(132, new HangulItem("혀", "HYEO", "ㅎ+ㅕ"));
            hangulItemHashMap.put(133, new HangulItem("호", "HO", "ㅎ+ㅗ"));
            hangulItemHashMap.put(134, new HangulItem("효", "HYO", "ㅎ+ㅛ"));
            hangulItemHashMap.put(135, new HangulItem("후", "HU", "ㅎ+ㅜ"));
            hangulItemHashMap.put(136, new HangulItem("휴", "HYU", "ㅎ+ㅠ"));
            hangulItemHashMap.put(137, new HangulItem("흐", "HEU", "ㅎ+ㅡ"));
            hangulItemHashMap.put(138, new HangulItem("히", "HI", "ㅎ+ㅣ"));
            hangulItemHashMap.put(139, new HangulItem("까", "KA", "ㄱ+ㄱ+ㅏ"));
            hangulItemHashMap.put(140, new HangulItem("꺄", "KYA", "ㄱ+ㄱ+ㅑ"));
            hangulItemHashMap.put(141, new HangulItem("꺼", "KEO", "ㄱ+ㄱ+ㅓ"));
            hangulItemHashMap.put(142, new HangulItem("껴", "KYEO", "ㄱ+ㄱ+ㅕ"));
            hangulItemHashMap.put(143, new HangulItem("꼬", "KO", "ㄱ+ㄱ+ㅗ"));
            hangulItemHashMap.put(144, new HangulItem("꾜", "KYO", "ㄱ+ㄱ+ㅛ"));
            hangulItemHashMap.put(145, new HangulItem("꾸", "KU", "ㄱ+ㄱ+ㅜ"));
            hangulItemHashMap.put(146, new HangulItem("뀨", "KYU", "ㄱ+ㄱ+ㅠ"));
            hangulItemHashMap.put(147, new HangulItem("끄", "KEU", "ㄱ+ㄱ+ㅡ"));
            hangulItemHashMap.put(148, new HangulItem("끼", "KI", "ㄱ+ㄱ+ㅣ"));
            hangulItemHashMap.put(149, new HangulItem("따", "TA", "ㄷ+ㄷ+ㅏ"));
            hangulItemHashMap.put(150, new HangulItem("땨", "TYA", "ㄷ+ㄷ+ㅑ"));
            hangulItemHashMap.put(151, new HangulItem("떠", "TEO", "ㄷ+ㄷ+ㅓ"));
            hangulItemHashMap.put(152, new HangulItem("뗘", "TYEO", "ㄷ+ㄷ+ㅕ"));
            hangulItemHashMap.put(153, new HangulItem("또", "TO", "ㄷ+ㄷ+ㅗ"));
            hangulItemHashMap.put(154, new HangulItem("뚀", "TYO", "ㄷ+ㄷ+ㅛ"));
            hangulItemHashMap.put(155, new HangulItem("뚜", "TU", "ㄷ+ㄷ+ㅜ"));
            hangulItemHashMap.put(156, new HangulItem("뜌", "TYU", "ㄷ+ㄷ+ㅠ"));
            hangulItemHashMap.put(157, new HangulItem("뜨", "TEU", "ㄷ+ㄷ+ㅡ"));
            hangulItemHashMap.put(158, new HangulItem("띠", "TI", "ㄷ+ㄷ+ㅣ"));
            hangulItemHashMap.put(159, new HangulItem("빠", "PA", "ㅂ+ㅂ+ㅏ"));
            hangulItemHashMap.put(160, new HangulItem("뺘", "PYA", "ㅂ+ㅂ+ㅑ"));
            hangulItemHashMap.put(161, new HangulItem("뻐", "PEO", "ㅂ+ㅂ+ㅓ"));
            hangulItemHashMap.put(162, new HangulItem("뼈", "PYEO", "ㅂ+ㅂ+ㅕ"));
            hangulItemHashMap.put(163, new HangulItem("뽀", "PO", "ㅂ+ㅂ+ㅗ"));
            hangulItemHashMap.put(164, new HangulItem("뾰", "PYO", "ㅂ+ㅂ+ㅛ"));
            hangulItemHashMap.put(165, new HangulItem("뿌", "PU", "ㅂ+ㅂ+ㅜ"));
            hangulItemHashMap.put(166, new HangulItem("쀼", "PYU", "ㅂ+ㅂ+ㅠ"));
            hangulItemHashMap.put(167, new HangulItem("쁘", "PEU", "ㅂ+ㅂ+ㅡ"));
            hangulItemHashMap.put(168, new HangulItem("삐", "PI", "ㅂ+ㅂ+ㅣ"));
            hangulItemHashMap.put(169, new HangulItem("싸", "SA", "ㅅ+ㅅ+ㅏ"));
            hangulItemHashMap.put(170, new HangulItem("쌰", "SYA", "ㅅ+ㅅ+ㅑ"));
            hangulItemHashMap.put(171, new HangulItem("써", "SEO", "ㅅ+ㅅ+ㅓ"));
            hangulItemHashMap.put(172, new HangulItem("쎠", "SYEO", "ㅅ+ㅅ+ㅕ"));
            hangulItemHashMap.put(173, new HangulItem("쏘", "SO", "ㅅ+ㅅ+ㅗ"));
            hangulItemHashMap.put(174, new HangulItem("쑈", "SYO", "ㅅ+ㅅ+ㅛ"));
            hangulItemHashMap.put(175, new HangulItem("쑤", "SU", "ㅅ+ㅅ+ㅜ"));
            hangulItemHashMap.put(176, new HangulItem("쓔", "SYU", "ㅅ+ㅅ+ㅠ"));
            hangulItemHashMap.put(177, new HangulItem("쓰", "SEU", "ㅅ+ㅅ+ㅡ"));
            hangulItemHashMap.put(178, new HangulItem("씨", "SI", "ㅅ+ㅅ+ㅣ"));
            hangulItemHashMap.put(179, new HangulItem("짜", "CHA", "ㅈ+ㅈ+ㅏ"));
            hangulItemHashMap.put(180, new HangulItem("쨔", "CHYA", "ㅈ+ㅈ+ㅑ"));
            hangulItemHashMap.put(181, new HangulItem("쩌", "CHEO", "ㅈ+ㅈ+ㅓ"));
            hangulItemHashMap.put(182, new HangulItem("쪄", "CHYEO", "ㅈ+ㅈ+ㅕ"));
            hangulItemHashMap.put(183, new HangulItem("쪼", "CHO", "ㅈ+ㅈ+ㅗ"));
            hangulItemHashMap.put(184, new HangulItem("쬬", "CHYO", "ㅈ+ㅈ+ㅛ"));
            hangulItemHashMap.put(185, new HangulItem("쭈", "CHU", "ㅈ+ㅈ+ㅜ"));
            hangulItemHashMap.put(186, new HangulItem("쮸", "CHYU", "ㅈ+ㅈ+ㅠ"));
            hangulItemHashMap.put(187, new HangulItem("쯔", "CHEU", "ㅈ+ㅈ+ㅡ"));
            hangulItemHashMap.put(188, new HangulItem("찌", "CHI", "ㅈ+ㅈ+ㅣ"));
        }catch (Exception e){
        }
    }


    public HangulItem get(int nItem){
        return hangulItemHashMap.get(nItem);
    }

    public int size(){
        return hangulItemHashMap.size();
    }

    public void clear(){
        hangulItemHashMap.clear();
    }
}
