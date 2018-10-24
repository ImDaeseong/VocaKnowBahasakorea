package com.im.daeseong.bahasakorea.Database;

public class HangulItems {
    private int rIndex;
    private String KataFirst;
    private String KataSecond;
    private String KataEnd;

    public HangulItems(int rIndex, String KataFirst, String KataSecond, String KataEnd){
        this.rIndex = rIndex;
        this.KataFirst = KataFirst;
        this.KataSecond = KataSecond;
        this.KataEnd = KataEnd;
    }

    public int getrIndex() {
        return rIndex;
    }

    public void setrIndex(int rIndex) {
        this.rIndex = rIndex;
    }

    public String getKataFirst() {
        return KataFirst;
    }

    public void setKataFirst(String KataFirst) {
        KataFirst = KataFirst;
    }

    public String getKataSecond() {
        return KataSecond;
    }

    public void setKataSecond(String KataSecond) {
        KataSecond = KataSecond;
    }

    public String getKataEnd() {
        return KataEnd;
    }

    public void setKataEnd(String KataEnd) {
        KataEnd = KataEnd;
    }

    @Override
    public String toString() {
        return "KataItems " +
                "rIndex=" + rIndex +
                ", KataFirst='" + KataFirst + '\'' +
                ", KataSecond='" + KataSecond + '\'' +
                ", KataEnd='" + KataEnd + '\'' ;
    }
}