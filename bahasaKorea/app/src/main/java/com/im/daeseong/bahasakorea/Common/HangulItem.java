package com.im.daeseong.bahasakorea.Common;

public class HangulItem {

    private String hangul1;
    private String hangul2;
    private String hangul3;

    public HangulItem(){
    }

    public HangulItem(String hangul1, String hangul2, String hangul3){
        this.hangul1 = hangul1;
        this.hangul2 = hangul2;
        this.hangul3 = hangul3;
    }

    public String getHangul1() {
        return hangul1;
    }

    public String getHangul2() {
        return hangul2;
    }

    public String getHangul3() {
        return hangul3;
    }

    public void setHangul1(String hangul1) {
        this.hangul1 = hangul1;
    }

    public void setHangul2(String hangul2) {
        this.hangul2 = hangul2;
    }

    public void setHangul3(String hangul3) {
        this.hangul3 = hangul3;
    }
}
