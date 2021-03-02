package com.im.daeseong.bahasakorea.BahasaAPI;

import com.im.daeseong.bahasakorea.Database.KataItems;

import java.util.HashMap;
import java.util.List;

public class kataApi {

    private static kataApi mApi = null;

    private HashMap<Integer, KataItems> hangulItemHashMap;

    public kataApi(){
        hangulItemHashMap = new HashMap<Integer, KataItems>();
    }

    public static kataApi getInstance() {
        if(mApi == null){
            mApi = new kataApi();
        }
        return mApi;
    }

    public void InitData(List<KataItems> items){

        try {

            //List<KataItems> items = kataManager.getKata();
            for(int i=0; i<items.size(); i++){
                KataItems item = items.get(i);
                int rIndex = item.getrIndex();
                String KataKor = item.getKataKor();
                String KataIndo = item.getKataIndo();
                String KataEng = item.getKataEng();
                hangulItemHashMap.put(i, new KataItems(rIndex, KataKor, KataIndo, KataEng));
            }
            items.clear();

        }catch (Exception e){
        }
    }

    public KataItems get(int nItem){
        return hangulItemHashMap.get(nItem);
    }

    public int size(){
        return hangulItemHashMap.size();
    }

    public void clear(){
        hangulItemHashMap.clear();
    }

}
