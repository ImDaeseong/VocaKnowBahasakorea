package com.im.daeseong.bahasakorea.Database;

public class KataItems {
    private int rIndex;
    private String KataKor;
    private String KataIndo;
    private String KataEng;

    public KataItems(int rIndex, String KataKor, String KataIndo, String KataEng){
        this.rIndex = rIndex;
        this.KataKor = KataKor;
        this.KataIndo = KataIndo;
        this.KataEng = KataEng;
    }

    public int getrIndex() {
        return rIndex;
    }

    public void setrIndex(int rIndex) {
        this.rIndex = rIndex;
    }

    public String getKataKor() {
        return KataKor;
    }

    public void setKataKor(String kataKor) {
        KataKor = kataKor;
    }

    public String getKataIndo() {
        return KataIndo;
    }

    public void setKataIndo(String kataIndo) {
        KataIndo = kataIndo;
    }

    public String getKataEng() {
        return KataEng;
    }

    public void setKataEng(String kataEng) {
        KataEng = kataEng;
    }

    @Override
    public String toString() {
        return "KataItems " +
                "rIndex=" + rIndex +
                ", KataKor='" + KataKor + '\'' +
                ", KataIndo='" + KataIndo + '\'' +
                ", KataEng='" + KataEng + '\'' ;
    }
}
