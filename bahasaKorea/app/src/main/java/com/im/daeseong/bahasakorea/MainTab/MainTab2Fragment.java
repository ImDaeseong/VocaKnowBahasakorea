package com.im.daeseong.bahasakorea.MainTab;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;//import android.support.annotation.Nullable;
import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.im.daeseong.bahasakorea.BahasaApplication;
import com.im.daeseong.bahasakorea.Controls.AnimatorUtil;
import com.im.daeseong.bahasakorea.Controls.OnSingleClickListener;
import com.im.daeseong.bahasakorea.Controls.UnitUtils;
import com.im.daeseong.bahasakorea.Database.HangulItems;
import com.im.daeseong.bahasakorea.Database.KataManager;
import com.im.daeseong.bahasakorea.MainActivity;
import com.im.daeseong.bahasakorea.Controls.MarqueeTask;
import com.im.daeseong.bahasakorea.R;
import com.im.daeseong.bahasakorea.Controls.SwipeConstraintLayout;
import com.im.daeseong.bahasakorea.Controls.TextToSpeechUtil;
import java.util.Timer;
import java.util.TimerTask;

public class MainTab2Fragment extends Fragment{

    private static final String TAG = MainTab2Fragment.class.getSimpleName();

    private Context mContext;
    private View MainView;

    private View cLChange;
    private ImageView ivChange;

    private View cL1;
    private TextView tvPage;

    private SwipeConstraintLayout swipeConstraintLayout;
    private TextView tv1, tv2, tv3;

    private View cL2;
    private TextView tvDesc;

    private int CurrentPlayIndex;
    private int nTotalPageCount;
    //private HangulItem item;
    private HangulItems item;

    private TextToSpeechUtil textToSpeechUtil;

    private MarqueeTask task;
    private Timer timer = null;

    private Timer playtimer = null;

    private int nRepeatCount;
    private int nRepeatTime = 0;
    private int nSkinStyle;
    private int nSkinStyleResource;
    private int nSwipeResource;
    private boolean bIsAutoPlay;
    private boolean bUseSound;

    private Handler handler = null;

    private boolean bActivityFragment = false;

    private KataManager kataManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Log.e(TAG, "onActivityCreated");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = container.getContext();
        //return inflater.inflate(R.layout.fragment_main_tab2, container, false);
        MainView = inflater.inflate(R.layout.fragment_main_tab2, container, false);
        /*
        MainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ((MainActivity)getActivity()).setSwipeEnabled(true);
                }catch (Exception e){
                }
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

        //Log.e(TAG, "onCreateView");

        return MainView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //MainView = view;

        init();

        changeDisplay();

        InitSkinStyle();

        DisplayMarquee();

        //InitData();

        //DB
        kataManager = KataManager.getInstance(mContext);
        nTotalPageCount = kataManager.getHangulTotalcount();

        //API
        //nTotalPageCount = hangulApi.getInstance().size();

        CurrentKata();

        //Log.e(TAG, "onViewCreated");
    }

    @Override
    public void onPause() {
        super.onPause();

        try {

            if (bIsAutoPlay) {
                RunAutoPlay();
                DisplayMarquee();
            }
        }catch (Exception e){
        }

        //Log.e(TAG, "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();

        try {

            if (bIsAutoPlay) {
                RunAutoPlay();
                DisplayMarquee();
            }
        }catch (Exception e){
        }

        //Log.e(TAG, "onResume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        try {
            handler.removeMessages(0);
            closeTimer();
            closePlayTimer();
        }catch (Exception e){
        }

        //Log.e(TAG, "onDestroyView");
    }

    private void changeDisplay(){

        try {
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            final Point size = new Point();
            display.getSize(size);
            int screenLength = size.y;

            int introViewheight = (screenLength / 3) + UnitUtils.dip2px(mContext, 30);
            if (screenLength > 0) {
                swipeConstraintLayout.getLayoutParams().height = introViewheight;
            }
        }catch (Exception e){
        }
    }

    private void init(){

        cLChange = (View) MainView.findViewById(R.id.cLChange);
        ivChange = (ImageView) cLChange.findViewById(R.id.ivChange);
        ivChange.setBackgroundColor(Color.parseColor("#66000000"));
        cLChange.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                try {

                    if (!bIsAutoPlay) {
                        bIsAutoPlay = true;
                        ivChange.setBackgroundResource(nSkinStyleResource);//ivChange.setBackgroundColor(Color.parseColor("#1B2836"));
                        RunAutoPlay();
                    } else {
                        bIsAutoPlay = false;
                        ivChange.setBackgroundColor(Color.parseColor("#66000000"));
                        RunAutoPlay();
                    }

                    DisplayMarquee();

                }catch (Exception e){
                }
            }
        });

        cL1 = (View) MainView.findViewById(R.id.cL1);
        tvPage = (TextView) cL1.findViewById(R.id.tvPage);

        swipeConstraintLayout = (SwipeConstraintLayout)MainView.findViewById(R.id.swCL);
        tv1 = (TextView) swipeConstraintLayout.findViewById(R.id.tv1);
        tv2 = (TextView) swipeConstraintLayout.findViewById(R.id.tv2);
        tv3 = (TextView) swipeConstraintLayout.findViewById(R.id.tv3);

        cL2 = (View) MainView.findViewById(R.id.cL2);
        tvDesc = (TextView) cL2.findViewById(R.id.tvDesc);

        swipeConstraintLayout.setOnSwipeListener(new SwipeConstraintLayout.OnSwipeConstraintListener() {
            @Override
            public void swipeLeft() {
                try {
                    PreKata();
                }catch (Exception e){
                }
            }

            @Override
            public void swipeRight() {
                try {
                    NextKata();
                }catch (Exception e){
                }
            }

            @Override
            public void swipeUp() {
            }

            @Override
            public void swipeDown() {
            }
        });

        handler = new Handler();

        textToSpeechUtil = new TextToSpeechUtil(mContext);

        //사운드 사용 여부
        bUseSound = BahasaApplication.getInstance().getAlfabetRepeatSound();

        //가나다라... 자동 반복 시간
        nRepeatTime = BahasaApplication.getInstance().getAlfabetRepeatTime();

        //스킨 타입
        nSkinStyle = BahasaApplication.getInstance().getSkinStyle();
    }

    private void InitSkinStyle()
    {
        try {

            nSkinStyle = BahasaApplication.getInstance().getSkinStyle();
            if (nSkinStyle == 1) {
                nSkinStyleResource = R.color.SkinStyle1;
                nSwipeResource = R.drawable.alfabet1_style;
            } else if (nSkinStyle == 2) {
                nSkinStyleResource = R.color.SkinStyle2;
                nSwipeResource = R.drawable.alfabet2_style;
            } else if (nSkinStyle == 3) {
                nSkinStyleResource = R.color.SkinStyle3;
                nSwipeResource = R.drawable.alfabet3_style;
            } else if (nSkinStyle == 4) {
                nSkinStyleResource = R.color.SkinStyle4;
                nSwipeResource = R.drawable.alfabet4_style;
            } else if (nSkinStyle == 5) {
                nSkinStyleResource = R.color.SkinStyle5;
                nSwipeResource = R.drawable.alfabet5_style;
            } else if (nSkinStyle == 6) {
                nSkinStyleResource = R.color.SkinStyle6;
                nSwipeResource = R.drawable.alfabet6_style;
            } else if (nSkinStyle == 7) {
                nSkinStyleResource = R.color.SkinStyle7;
                nSwipeResource = R.drawable.alfabet7_style;
            } else if (nSkinStyle == 8) {
                nSkinStyleResource = R.color.SkinStyle8;
                nSwipeResource = R.drawable.alfabet8_style;
            }

            swipeConstraintLayout.setBackgroundResource(nSwipeResource);
            tvPage.setTextColor(getResources().getColor(nSkinStyleResource));//tvPage.setTextColor(Color.parseColor("#1B2836"));
            tvDesc.setTextColor(getResources().getColor(nSkinStyleResource));

        }catch (Exception e){
        }

    }

    private void CurrentKata()
    {
        try
        {
            //DB
            if (CurrentPlayIndex < 0 || nTotalPageCount == 0) return;

            int nIndex = CurrentPlayIndex + 1;
            item = kataManager.getHangul(nIndex);

            tv1.setText(item.getKataFirst());
            tv2.setText(item.getKataSecond());
            tv3.setText(item.getKataEnd());

            CounterTimerLabel();

            //현재 프레그멘트 활성화시만 음성 출력
            if(bActivityFragment){
                PlaySuaraSound(item.getKataFirst());
            }


            //API
            /*
            if (CurrentPlayIndex < 0 || hangulApi.getInstance().size() == 0) return;

            item = hangulApi.getInstance().get(CurrentPlayIndex);
            tv1.setText(item.getHangul1());
            tv2.setText(item.getHangul2());
            tv3.setText(item.getHangul3());

            CounterTimerLabel();
            //PlaySuaraSound(item.getHangul1());

            //현재 프레그멘트 활성화시만 음성 출력
            if(bActivityFragment){
                PlaySuaraSound(item.getHangul1());
            }
            */

        }catch (Exception e){
        }
    }

    private  void PreKata()
    {
        try
        {
            //DB
            if (nTotalPageCount == 0) return;

            AnimatorUtil.AnimatoSwipeRight(swipeConstraintLayout);//await SwipeAnimateEffect(true);

            CurrentPlayIndex--;
            if (CurrentPlayIndex < 0)
                CurrentPlayIndex = nTotalPageCount - 1;

            int nIndex = CurrentPlayIndex + 1;
            item = kataManager.getHangul(nIndex);

            tv1.setText(item.getKataFirst());
            tv2.setText(item.getKataSecond());
            tv3.setText(item.getKataEnd());

            if (!bIsAutoPlay)
                CounterTimerLabel();

            PlaySuaraSound(item.getKataFirst());


            //API
            /*
            if (hangulApi.getInstance().size() == 0) return;

            AnimatorUtil.AnimatoSwipeRight(swipeConstraintLayout);//await SwipeAnimateEffect(true);

            CurrentPlayIndex--;

            if (CurrentPlayIndex < 0)
                CurrentPlayIndex = hangulApi.getInstance().size() - 1;

            item = hangulApi.getInstance().get(CurrentPlayIndex);
            tv1.setText(item.getHangul1());
            tv2.setText(item.getHangul2());
            tv3.setText(item.getHangul3());

            if (!bIsAutoPlay)
                CounterTimerLabel();

            PlaySuaraSound(item.getHangul1());
            */

        } catch(Exception e) {
        }
    }

    private void NextKata()
    {
        try
        {
            //DB
            if (nTotalPageCount == 0) return;

            AnimatorUtil.AnimatoSwipeLeft(swipeConstraintLayout);

            CurrentPlayIndex++;
            if (CurrentPlayIndex > (nTotalPageCount - 1))
                CurrentPlayIndex = 0;

            int nIndex = CurrentPlayIndex + 1;
            item = kataManager.getHangul(nIndex);

            tv1.setText(item.getKataFirst());
            tv2.setText(item.getKataSecond());
            tv3.setText(item.getKataEnd());

            if (!bIsAutoPlay)
                CounterTimerLabel();

            PlaySuaraSound(item.getKataFirst());


            //API
            /*
            if (hangulApi.getInstance().size() == 0) return;

            AnimatorUtil.AnimatoSwipeLeft(swipeConstraintLayout);

            CurrentPlayIndex++;

            if (CurrentPlayIndex > (hangulApi.getInstance().size() - 1))
                CurrentPlayIndex = 0;

            item = hangulApi.getInstance().get(CurrentPlayIndex);
            tv1.setText(item.getHangul1());
            tv2.setText(item.getHangul2());
            tv3.setText(item.getHangul3());

            if (!bIsAutoPlay)
                CounterTimerLabel();

            PlaySuaraSound(item.getHangul1());
            */

        } catch(Exception e) {
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
        try {
            closeTimer();
            task = new MarqueeTask(tvDesc);
            timer = new Timer();
            timer.schedule(task, 0, 20000);
        }catch (Exception e){
        }
    }

    private void DisplayMarquee()
    {
        if (bIsAutoPlay)
        {
            String ContentMarquee = String.format("Sekarang sedang berjalan secara otomatis setiap %d detik.", nRepeatTime);
            tvDesc.setText(ContentMarquee);
        }
        else
        {
            String ContentMarquee = String.format("Klik tombol play menjalankan secara otomatis setiap %d detik.", nRepeatTime);
            tvDesc.setText(ContentMarquee);
        }

        startTimer();
    }

    private void CounterTimerLabel()
    {
        try {
            int nDisplayIndex = CurrentPlayIndex + 1;
            String sPage = String.format("Halaman saat ini (%d/%d)", nDisplayIndex, nTotalPageCount);
            tvPage.setTextColor(getResources().getColor(nSkinStyleResource));//tvPage.setTextColor(Color.parseColor("#1B2836"));
            tvPage.setText(sPage);
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

    private void closePlayTimer(){
        try {
            if (playtimer != null) {
                playtimer.cancel();
                playtimer = null;
            }
        }catch (Exception e){
        }
    }

    private void RunAutoPlay()
    {
        closePlayTimer();

        playtimer = new Timer();
        playtimer.schedule(new TimerTask() {
            @Override
            public void run() {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            if (!bIsAutoPlay)
                            {
                                nRepeatCount = 0;
                                CounterTimerLabel();
                                return;
                            }

                            nRepeatCount++;

                            if (nRepeatCount % 2 == 0)
                            {
                                tvPage.setTextColor(getResources().getColor(nSkinStyleResource));//tvPage.setTextColor(Color.parseColor("#ff9900"));
                            }
                            else
                            {
                                tvPage.setTextColor(Color.TRANSPARENT);
                            }

                            int nDisplayIndex = CurrentPlayIndex + 1;
                            String sPage = String.format("%d detik berlalu (%d/%d)", nRepeatCount, nDisplayIndex, nTotalPageCount);
                            tvPage.setText(sPage);

                            //사운드 사용시
                            if (bUseSound)
                            {
                                if (textToSpeechUtil.IsSpeaking()) return;
                            }

                            if (nRepeatTime <= nRepeatCount)
                            {
                                nRepeatCount = 0;
                                NextKata();
                            }

                        }catch (Exception e){
                        }
                    }
                });
            }
        }, 0, 1000);

    }

    public void setConfigInfo(){

        try {

            //Log.e(TAG, "setConfigInfo");

            //사운드 사용 여부
            bUseSound = BahasaApplication.getInstance().getAlfabetRepeatSound();

            //가나다라... 자동 반복 시간
            nRepeatTime = BahasaApplication.getInstance().getAlfabetRepeatTime();

            //스킨 타입
            nSkinStyle = BahasaApplication.getInstance().getSkinStyle();

            //closeTimer();
            closePlayTimer();

            bIsAutoPlay = false;
            ivChange.setBackgroundColor(Color.parseColor("#66000000"));
            RunAutoPlay();

            DisplayMarquee();

        }catch (Exception e){
        }

    }

    public void setActivityFragment(final boolean bActivityFragment){

        try {

            closePlayTimer();
            //Log.e(TAG, "bActivityFragment:" + bActivityFragment);
            this.bActivityFragment = bActivityFragment;

            /*
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(bActivityFragment){
                        ivChange.setVisibility(View.VISIBLE);
                    }else {
                        ivChange.setVisibility(View.GONE);
                    }
                }
            }, 500);
            */

            if(bActivityFragment){
                ivChange.setVisibility(View.VISIBLE);
            }else {
                ivChange.setVisibility(View.GONE);
            }

        }catch (Exception e){
        }

    }

}
