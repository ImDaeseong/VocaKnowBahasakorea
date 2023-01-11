package com.im.daeseong.bahasakorea.MainTab;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;//import android.support.annotation.Nullable;
import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import com.im.daeseong.bahasakorea.BahasaApplication;
import com.im.daeseong.bahasakorea.Database.KataItems;
import com.im.daeseong.bahasakorea.Database.KataManager;
import com.im.daeseong.bahasakorea.MainActivity;
import com.im.daeseong.bahasakorea.R;

import java.util.ArrayList;

public class MainTab4Fragment extends Fragment {

    private static final String TAG = MainTab4Fragment.class.getSimpleName();

    private Context mContext;
    private View MainView;

    private int nSkinStyle;
    private int nSkinStyleResource;

    private EditText searchET;
    private ListView lv1;
    private KataAdapter kataAdapter;

    private KataManager kataManager;

    private ArrayList<KataItems> items = new ArrayList<KataItems>();

    private Handler handler = new Handler();

    private boolean bActivityFragment = false;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mContext = container.getContext();
        //return inflater.inflate(R.layout.fragment_main_tab4, container, false);
        MainView = inflater.inflate(R.layout.fragment_main_tab4, container, false);
        /*
        MainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        */
        MainView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try{
                    ((MainActivity)getActivity()).setSwipeEnabled(true);
                }catch (Exception e){
                }
                return false;
            }
        });

        return MainView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //MainView = view;

        init();

        InitSkinStyle();

        InitData();
    }

    @Override
    public void onPause() {
        super.onPause();

        //Log.e(TAG, "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();

        //Log.e(TAG, "onResume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private void init(){

        lv1 = (ListView)MainView.findViewById(R.id.lv1);
        searchET = (EditText) MainView.findViewById(R.id.searchET);

        searchTextWatcher watcher = new searchTextWatcher();
        searchET.addTextChangedListener(watcher);

        kataManager = KataManager.getInstance(mContext);
    }

    private void InitData(){
        //items = kataManager.getKata();
        kataAdapter = new KataAdapter(items, mContext);
        lv1.setAdapter(kataAdapter);
    }

    private void InitSkinStyle()
    {
        try {

            nSkinStyle = BahasaApplication.getInstance().getSkinStyle();
            if (nSkinStyle == 1) {
                nSkinStyleResource = R.color.SkinStyle1;
            } else if (nSkinStyle == 2) {
                nSkinStyleResource = R.color.SkinStyle2;
            } else if (nSkinStyle == 3) {
                nSkinStyleResource = R.color.SkinStyle3;
            } else if (nSkinStyle == 4) {
                nSkinStyleResource = R.color.SkinStyle4;
            } else if (nSkinStyle == 5) {
                nSkinStyleResource = R.color.SkinStyle5;
            } else if (nSkinStyle == 6) {
                nSkinStyleResource = R.color.SkinStyle6;
            } else if (nSkinStyle == 7) {
                nSkinStyleResource = R.color.SkinStyle7;
            } else if (nSkinStyle == 8) {
                nSkinStyleResource = R.color.SkinStyle8;
            }

        }catch (Exception e){
        }
    }

    public void setConfigInfo(){

        try {

            //Log.e(TAG, "setConfigInfo4");
            searchET.setText("");
            searchET.clearFocus();

        }catch (Exception e){
        }

    }

    public void setActivityFragment(boolean bActivityFragment){

        try {

            //Log.e(TAG, "bActivityFragment:" + bActivityFragment);
            this.bActivityFragment = bActivityFragment;

        }catch (Exception e){
        }

    }

    class searchTextWatcher implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

            try {

                if (TextUtils.isEmpty(s.toString())) {
                    kataAdapter.clear();
                    items.clear();
                    kataAdapter.addAll(items);
                    lv1.setAdapter(kataAdapter);
                } else {
                    kataAdapter.clear();
                    items.clear();
                    items = kataManager.getSearchKata(s.toString());
                    kataAdapter.addAll(items);
                    lv1.setAdapter(kataAdapter);
                }

            } catch (Exception e){
            }

        }
    }

}
