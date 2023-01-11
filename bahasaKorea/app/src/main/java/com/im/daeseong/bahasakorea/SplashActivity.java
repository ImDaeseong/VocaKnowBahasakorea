package com.im.daeseong.bahasakorea;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.im.daeseong.bahasakorea.Controls.RecycleUtil;
import com.im.daeseong.bahasakorea.Controls.SharedPreferencesUtil;
import com.im.daeseong.bahasakorea.Database.CopyDBfile;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    private Handler handler = null;

    private View mainView;
    private int nSkinStyle;

    //private KataManager kataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InitTitleBar();
        setContentView(R.layout.activity_splash);
        init();

        InitSkinStyle();

        InitData();
    }

    private void init(){

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        SplashActivity.this.finish();
                        break;
                }
            }
        };

        mainView = findViewById(android.R.id.content);

    }

    private void InitSkinStyle()
    {
        try {

            nSkinStyle = BahasaApplication.getInstance().getSkinStyle();
            if (nSkinStyle == 1) {
                mainView.setBackgroundResource(R.color.SkinStyle1);//mainView.setBackgroundColor(Color.parseColor("#1B2836"));
            } else if (nSkinStyle == 2) {
                mainView.setBackgroundResource(R.color.SkinStyle2);//mainView.setBackgroundColor(Color.parseColor("#33A7D6"));
            } else if (nSkinStyle == 3) {
                mainView.setBackgroundResource(R.color.SkinStyle3);//mainView.setBackgroundColor(Color.parseColor("#493335"));
            } else if (nSkinStyle == 4) {
                mainView.setBackgroundResource(R.color.SkinStyle4);//mainView.setBackgroundColor(Color.parseColor("#FF80AB"));
            } else if (nSkinStyle == 5) {
                mainView.setBackgroundResource(R.color.SkinStyle5);//mainView.setBackgroundColor(Color.parseColor("#4CAF50"));
            } else if (nSkinStyle == 6) {
                mainView.setBackgroundResource(R.color.SkinStyle6);//mainView.setBackgroundColor(Color.parseColor("#3F51B5"));
            } else if (nSkinStyle == 7) {
                mainView.setBackgroundResource(R.color.SkinStyle7);//mainView.setBackgroundColor(Color.parseColor("#B71C1C"));
            } else if (nSkinStyle == 8) {
                mainView.setBackgroundResource(R.color.SkinStyle8);//mainView.setBackgroundColor(Color.parseColor("#37474F"));
            }

        }catch (Exception e){
        }
    }

    private void InitData(){

        //kataManager = KataManager.getInstance(this);

        try{

            boolean bCopy = new CopyDBTask().execute(SplashActivity.this).get();

        }catch (Exception e){
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                try{

                    //스킨 타입
                    int nSkinStyle = (int) SharedPreferencesUtil.getInstance().getValue("SkinStyle", 1);
                    BahasaApplication.getInstance().setSkinStyle(nSkinStyle);

                    //가나다라... 자동 반복 시간
                    int nAlfabetRepeatTime = (int) SharedPreferencesUtil.getInstance().getValue("AlfabetRepeatTime", 10);
                    BahasaApplication.getInstance().setAlfabetRepeatTime(nAlfabetRepeatTime);

                    //음성사용 여부
                    boolean bAlfabetRepeatSound = (boolean) SharedPreferencesUtil.getInstance().getValue("AlfabetRepeatSound", false);
                    BahasaApplication.getInstance().setAlfabetRepeatSound(bAlfabetRepeatSound);

                    //단어장 자동 반복 시간
                    int nIngatRepeatTime = (int) SharedPreferencesUtil.getInstance().getValue("IngatRepeatTime", 10);
                    BahasaApplication.getInstance().setIngatRepeatTime(nIngatRepeatTime);

                    //단어장 음성사용 여부
                    boolean bIngatRepeatSound = (boolean) SharedPreferencesUtil.getInstance().getValue("IngatRepeatSound", false);
                    BahasaApplication.getInstance().setIngatRepeatSound(bIngatRepeatSound);

                    //스크린 화면 락 처리여부
                    boolean bScreenLock = (boolean) SharedPreferencesUtil.getInstance().getValue("ScreenLock", false);
                    BahasaApplication.getInstance().setScreenLock(bScreenLock);


                    //API 이용시만 사용
                    /*
                    //MainTab2Fragment 데이터
                    hangulApi.getInstance().clear();
                    hangulApi.getInstance().InitData();
                    */

                    //API 이용시만 사용
                    /*
                    //MainTab3Fragment 데이터
                    kataApi.getInstance().clear();
                    kataApi.getInstance().InitData(kataManager.getKata());
                    */

                    Message msg = handler.obtainMessage();
                    msg.what = 0;
                    handler.sendMessage(msg);
                    /*
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            SplashActivity.this.finish();
                        }
                    });
                    */
                }catch (Exception e){
                }
            }
        }, 1000);
    }

    private void InitTitleBar(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //백버튼 기능 막음
        return;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {

            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                handler = null;
            }

            RecycleUtil.recursiveRecycle(getWindow().getDecorView());
            System.gc();

        }catch (Exception e){
        }
    }

    private static class CopyDBTask extends AsyncTask<Context, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Context... params) {
            try {

                new CopyDBfile(params[0]);
                return true;

            } catch (Exception e) {
            }
            return false;
        }
    }

}
