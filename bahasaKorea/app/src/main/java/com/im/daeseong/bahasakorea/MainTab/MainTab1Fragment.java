package com.im.daeseong.bahasakorea.MainTab;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;//import android.support.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;//import android.support.design.widget.FloatingActionButton;
import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import com.im.daeseong.bahasakorea.BahasaApplication;
import com.im.daeseong.bahasakorea.Common.AlfabetItem;
import com.im.daeseong.bahasakorea.Controls.AnimatorUtil;
import com.im.daeseong.bahasakorea.Controls.OnSingleClickListener;
import com.im.daeseong.bahasakorea.R;
import com.im.daeseong.bahasakorea.Controls.TextToSpeechUtil;
import java.util.Timer;
import java.util.TimerTask;

public class MainTab1Fragment extends Fragment {

    private static final String TAG = MainTab1Fragment.class.getSimpleName();

    private Context mContext;
    private View MainView;

    private int nSkinStyle;
    private int nSkinStyleResource;

    private TextToSpeechUtil textToSpeechUtil;

    private Timer timer = null;

    private boolean bUseSound;

    private Handler handler = null;

    private final int numRows = 20;
    private final int numCols = 11;

    private ScrollView scrollView;
    private GridLayout gridLayout;
    private FloatingActionButton floatingActionButton;
    private int oldScrollYPostion = 0;

    private boolean bActivityFragment = false;

    private String[][] AlpabatList = new String[][]{
        { "", "ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ" },
        { "ㄱ", "가", "갸", "거", "겨", "고", "교", "구", "규", "그", "기"} ,
        { "ㄴ", "나", "냐", "너", "녀", "노", "뇨", "누", "뉴", "느", "니"} ,
        { "ㄷ", "다", "댜", "더", "뎌", "도", "됴", "두", "듀", "드", "디"} ,
        { "ㄹ", "라", "랴", "러", "려", "로", "료", "루", "류", "르", "리" },
        { "ㅁ", "마", "먀", "머", "며", "모", "묘", "무", "뮤", "므", "미"} ,
        { "ㅂ", "바", "뱌", "버", "벼", "보", "뵤", "부", "뷰", "브", "비"} ,
        { "ㅅ", "사", "샤", "서", "셔", "소", "쇼", "수", "슈", "스", "시"} ,
        { "ㅇ", "아", "야", "어", "여", "오", "요", "우", "유", "으", "이" },
        { "ㅈ", "자", "쟈", "저", "져", "조", "죠", "주", "쥬", "즈", "지"} ,
        { "ㅊ", "차", "챠", "처", "쳐", "초", "쵸", "추", "츄", "츠", "치" },
        { "ㅋ", "카", "캬", "커", "켜", "코", "쿄", "쿠", "큐", "크", "키"} ,
        { "ㅌ", "타", "탸", "터", "텨", "토", "툐", "투", "튜", "트", "티" },
        { "ㅍ", "파", "퍄", "퍼", "펴", "포", "표", "푸", "퓨", "프", "피"} ,
        { "ㅎ", "하", "햐", "허", "혀", "호", "효", "후", "휴", "흐", "히"} ,
        { "ㄲ", "까", "꺄", "꺼", "껴", "꼬", "꾜", "꾸", "뀨", "끄", "끼"} ,
        { "ㄸ", "따", "땨", "떠", "뗘", "또", "뚀", "뚜", "뜌", "뜨", "띠"} ,
        { "ㅃ", "빠", "뺘", "뻐", "뼈", "뽀", "뾰", "뿌", "쀼", "쁘", "삐" },
        { "ㅆ", "싸", "쌰", "써", "쎠", "쏘", "쑈", "쑤", "쓔", "쓰", "씨" },
        { "ㅉ", "짜", "쨔", "쩌", "쪄", "쪼", "쬬", "쭈", "쮸", "쯔", "찌"}
    };


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = container.getContext();
        return inflater.inflate(R.layout.fragment_main_tab1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainView = view;

        init();

        InitSkinStyle();

        InitData();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.e(TAG, "onResume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        try{
            handler.removeMessages(0);
            closeTimer();
        }catch (Exception e){
        }
    }

    private void InitSkinStyle()
    {
        try {

            nSkinStyle = BahasaApplication.getInstance().getSkinStyle();
            if (nSkinStyle == 1) {
                nSkinStyleResource = R.drawable.alfabet1_style;
            } else if (nSkinStyle == 2) {
                nSkinStyleResource = R.drawable.alfabet2_style;
            } else if (nSkinStyle == 3) {
                nSkinStyleResource = R.drawable.alfabet3_style;
            } else if (nSkinStyle == 4) {
                nSkinStyleResource = R.drawable.alfabet4_style;
            } else if (nSkinStyle == 5) {
                nSkinStyleResource = R.drawable.alfabet5_style;
            } else if (nSkinStyle == 6) {
                nSkinStyleResource = R.drawable.alfabet6_style;
            } else if (nSkinStyle == 7) {
                nSkinStyleResource = R.drawable.alfabet7_style;
            } else if (nSkinStyle == 8) {
                nSkinStyleResource = R.drawable.alfabet8_style;
            }

        }catch (Exception e){
        }
    }

    private void closeTimer(){
        try {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }catch (Exception e){
        }
    }

    private void startTimer(){
        closeTimer();

        timer = new Timer();
        timer.schedule(new TimerTask() {

            int nTimeCount = 0;

            @Override
            public void run() {

                try {
                    nTimeCount++;

                    if (nTimeCount > 2) {

                        closeTimer();

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                floatingActionButton.setVisibility(View.INVISIBLE);
                            }
                        });
                    }

                }catch (Exception e){
                }
            }
        }, 0, 1000);
    }

    private void init(){

        scrollView = (ScrollView)MainView.findViewById(R.id.sv1);
        gridLayout = (GridLayout)MainView.findViewById(R.id.gl1);
        floatingActionButton = (FloatingActionButton)MainView.findViewById(R.id.fab1);

        floatingActionButton.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                try {
                    scrollView.scrollTo(0, 0);
                }catch (Exception e){
                }
            }
        });

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                try {

                    if(scrollView != null){
                        if (scrollView.getChildAt(0).getBottom() <= (scrollView.getHeight() + scrollView.getScrollY())) {
                            floatingActionButton.setVisibility(View.VISIBLE);
                            startTimer();
                        } else {
                            floatingActionButton.setVisibility(View.INVISIBLE);
                            closeTimer();
                        }
                    }
                    /*
                    if (scrollView.getScrollY() > oldScrollYPostion) {
                        floatingActionButton.setVisibility(View.VISIBLE);
                    } else if (scrollView.getScrollY() < oldScrollYPostion || scrollView.getScrollY() <= 0) {
                        floatingActionButton.setVisibility(View.INVISIBLE);
                    }
                    oldScrollYPostion = scrollView.getScrollY();
                    */

                    /*
                    int scrollY = scrollView.getScrollY();
                    int bottom = scrollY + scrollView.getHeight();

                    if ( (scrollView.getScrollY() > 0) && (floatingActionButton.getVisibility() == View.INVISIBLE) ) {
                        floatingActionButton.setVisibility(View.VISIBLE);
                        startTimer();
                    } else {
                        floatingActionButton.setVisibility(View.INVISIBLE);
                    }
                    */
                }catch (Exception e){
                }
            }
        });

        handler = new Handler();

        textToSpeechUtil = new TextToSpeechUtil(mContext);

        //사운드 사용 여부
        bUseSound = BahasaApplication.getInstance().getAlfabetRepeatSound();
    }

    private void InitData(){

        try {

            Display display = getActivity().getWindowManager().getDefaultDisplay();
            final Point size = new Point();
            display.getSize(size);
            int screenWidth = size.x;
            int screenHeight = size.y;

            gridLayout.setColumnCount(11);
            gridLayout.setRowCount(20);

            int nResouce = R.drawable.alfabet1_style;

            for (int i = 0; i < numRows; i++) {
                for(int j=0; j < numCols; j++){

                    if(i == 0 || j == 0){
                        nResouce = R.drawable.alfabet_border;
                    }else {
                        nResouce = nSkinStyleResource;
                    }

                    AlfabetItem item = new AlfabetItem(mContext);
                    item.setResourceType(nResouce);
                    item.setText(AlpabatList[i][j]);
                    item.setOnClickListener(new OnSingleClickListener() {
                        @Override
                        public void onSingleClick(View view) {
                            try {
                                String sText = ((AlfabetItem) view).getText();
                                if (!TextUtils.isEmpty(sText)) {

                                    AnimatorUtil.AnimatoScaleXY(view);

                                    PlaySuaraSound(sText);
                                }
                            }catch (Exception e){
                            }
                        }
                    });
                    gridLayout.addView(item, (screenWidth / numCols), 60);
                }
            }

        }catch (Exception e){
        }
    }

    private void PlaySuaraSound(final String sText)
    {
        try
        {
            if (bUseSound)
            {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (textToSpeechUtil.isLanguageAvailable()) {
                            textToSpeechUtil.Speak(sText);
                        } else {
                            BahasaApplication.getInstance().Toast(mContext, "Tidak bisa Dengar Bahasa Korea.", false);
                        }
                    }
                }, 1000);
            }
        } catch(Exception e) {
        }
    }

    public void setConfigInfo(){

        try {

            //Log.e(TAG, "setConfigInfo");

            //사운드 사용 여부
            bUseSound = BahasaApplication.getInstance().getAlfabetRepeatSound();

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

}
