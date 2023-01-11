package com.im.daeseong.bahasakorea;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.im.daeseong.bahasakorea.Controls.SharedPreferencesUtil;

public class BahasaApplication extends Application {

    private static final String TAG = BahasaApplication.class.getSimpleName();


    //스킨 타입
    private static int mSkinStyle = 1;
    public static int getSkinStyle(){ return  mSkinStyle; }
    public static void setSkinStyle(int nSkinStyle) { mSkinStyle = nSkinStyle;}

    //가나다라... 자동 반복 시간
    private static int mAlfabetRepeatTime = 10;
    public static int getAlfabetRepeatTime(){ return  mAlfabetRepeatTime; }
    public static void setAlfabetRepeatTime(int nAlfabetRepeatTime) { mAlfabetRepeatTime = nAlfabetRepeatTime;}

    //음성사용 여부
    private static boolean mbAlfabetRepeatSound = false;
    public static boolean getAlfabetRepeatSound(){ return  mbAlfabetRepeatSound; }
    public static void setAlfabetRepeatSound(boolean bAlfabetRepeatSound) { mbAlfabetRepeatSound = bAlfabetRepeatSound;}

    //단어장 자동 반복 시간
    private static int mIngatRepeatTime = 10;
    public static int getIngatRepeatTime(){ return  mIngatRepeatTime; }
    public static void setIngatRepeatTime(int nIngatRepeatTime) { mIngatRepeatTime = nIngatRepeatTime;}

    //단어장 음성사용 여부
    private static boolean mbIngatRepeatSound = false;
    public static boolean getIngatRepeatSound(){ return  mbIngatRepeatSound; }
    public static void setIngatRepeatSound(boolean bIngatRepeatSound) { mbIngatRepeatSound = bIngatRepeatSound;}

    //스크린 화면 락 처리여부
    private static boolean mbScreenLock = false;
    public static boolean getScreenLock(){ return  mbScreenLock; }
    public static void setScreenLock(boolean bScreenLock) { mbScreenLock = bScreenLock;}


    private static BahasaApplication mInstance;
    public static synchronized BahasaApplication getInstance() {
        return mInstance;
    }

    private static Context mContext;
    public static Context getAppContext(){
        return mContext;
    }

    private static Toast toast;

    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    public static void Toast(Context context, String sMsg, boolean bLengthLong) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.toast_layout, null);

        TextView tvtoast = view.findViewById(R.id.tvtoast);

        //최대 1줄까지
        tvtoast.setMaxLines(1);
        tvtoast.setTextColor(Color.parseColor("#000000"));
        tvtoast.setText(sMsg);

        toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM, 0, 0);//toast.setGravity(Gravity.CENTER, 0, 0);

        if(bLengthLong) {
            toast.setDuration(Toast.LENGTH_LONG);
        }else {
            toast.setDuration(Toast.LENGTH_SHORT);
        }

        toast.setView(view);
        toast.show();
    }

    public static void Toastcancel(){
        try {
            toast.cancel();
        }catch (Exception e){
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        mContext = getApplicationContext();

        try {

            //DbHandler.getInstance().init(this);
            SharedPreferencesUtil.getInstance().init(this);
        }catch (Exception e){
        }
    }

}
