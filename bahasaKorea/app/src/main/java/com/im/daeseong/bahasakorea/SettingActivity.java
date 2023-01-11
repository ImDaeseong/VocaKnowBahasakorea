package com.im.daeseong.bahasakorea;

import android.content.pm.ActivityInfo;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import com.im.daeseong.bahasakorea.Controls.OnSingleClickListener;
import com.im.daeseong.bahasakorea.Controls.RecycleUtil;
import com.im.daeseong.bahasakorea.Controls.SharedPreferencesUtil;

public class SettingActivity extends AppCompatActivity {

    private static final String TAG = SettingActivity.class.getSimpleName();

    private View mainView;
    private View include;
    private View cLbtn_arrow;
    private int nSkinStyle;

    private EditText et1, et2;
    private Switch sw1, sw2, sw3;

    private View color_skin_item1, color_skin_item2, color_skin_item3, color_skin_item4,
            color_skin_item5, color_skin_item6, color_skin_item7, color_skin_item8;
    private View vColor1,vColor2, vColor3, vColor4, vColor5, vColor6, vColor7, vColor8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InitTitleBar();
        setContentView(R.layout.activity_setting);
        init();

        InitSkinStyle();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {

            RecycleUtil.recursiveRecycle(getWindow().getDecorView());
            System.gc();

        }catch (Exception e){
        }
    }

    //타이틀바 숨기기/가로보기 고정/풀스크린
    private void InitTitleBar(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbar_bg));//window.setStatusBarColor(Color.rgb(27, 40, 54));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public void init(){

        mainView = findViewById(android.R.id.content);
        include = findViewById(R.id.setting_toolbar);

        //왼쪽 백이미지 버튼
        cLbtn_arrow = (View) include.findViewById(R.id.cLbtn_arrow);
        cLbtn_arrow.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });


        //가나다라... 자동 반복 시간
        et1 = (EditText)findViewById(R.id.et1);
        //et1.getBackground().setColorFilter(Color.parseColor("#AFAFAF"), PorterDuff.Mode.SRC_IN);
        int nAlfabetRepeatTime = (int) SharedPreferencesUtil.getInstance().getValue("AlfabetRepeatTime", 10);
        String sAlfabetRepeatTime = String.format("%d", nAlfabetRepeatTime);
        et1.setText(sAlfabetRepeatTime);
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {

                    if (!TextUtils.isEmpty(s.toString())) {
                        int nAlfabetRepeatTime = Integer.parseInt(s.toString());
                        SharedPreferencesUtil.getInstance().setValue("AlfabetRepeatTime", nAlfabetRepeatTime);
                        BahasaApplication.getInstance().setAlfabetRepeatTime(nAlfabetRepeatTime);
                        MainActivity.getMainActivity().setReInitFragment();
                    }

                } catch (Exception e){
                }
            }
        });


        //단어장 자동 반복 시간
        et2 = (EditText)findViewById(R.id.et2);
        //et2.getBackground().setColorFilter(Color.parseColor("#AFAFAF"), PorterDuff.Mode.SRC_IN);
        int nIngatRepeatTime = (int) SharedPreferencesUtil.getInstance().getValue("IngatRepeatTime", 10);
        String sIngatRepeatTime = String.format("%d", nIngatRepeatTime);
        et2.setText(sIngatRepeatTime);
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {

                    if (!TextUtils.isEmpty(s.toString())) {
                        int nIngatRepeatTime = Integer.parseInt(s.toString());
                        SharedPreferencesUtil.getInstance().setValue("IngatRepeatTime", nIngatRepeatTime);
                        BahasaApplication.getInstance().setIngatRepeatTime(nIngatRepeatTime);
                        MainActivity.getMainActivity().setReInitFragment();
                    }

                } catch (Exception e){
                }
            }
        });


        //음성사용 여부
        sw1 = (Switch)findViewById(R.id.sw1);
        boolean bAlfabetRepeatSound = (boolean) SharedPreferencesUtil.getInstance().getValue("AlfabetRepeatSound", false);
        sw1.setChecked(bAlfabetRepeatSound);
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                try {

                    if (isChecked) {
                        SharedPreferencesUtil.getInstance().setValue("AlfabetRepeatSound", true);
                        BahasaApplication.getInstance().setAlfabetRepeatSound(true);
                    } else {
                        SharedPreferencesUtil.getInstance().setValue("AlfabetRepeatSound", false);
                        BahasaApplication.getInstance().setAlfabetRepeatSound(false);
                    }
                    MainActivity.getMainActivity().setReInitFragment();

                }catch (Exception e){
                }

            }
        });

        //단어장 음성사용 여부
        sw2 = (Switch)findViewById(R.id.sw2);
        boolean bIngatRepeatSound = (boolean) SharedPreferencesUtil.getInstance().getValue("IngatRepeatSound", false);
        sw2.setChecked(bIngatRepeatSound);
        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                try {

                    if (isChecked) {
                        SharedPreferencesUtil.getInstance().setValue("IngatRepeatSound", true);
                        BahasaApplication.getInstance().setIngatRepeatSound(true);
                    } else {
                        SharedPreferencesUtil.getInstance().setValue("IngatRepeatSound", false);
                        BahasaApplication.getInstance().setIngatRepeatSound(false);
                    }
                    MainActivity.getMainActivity().setReInitFragment();

                }catch (Exception e){
                }

            }
        });

        //스크린 화면 락 처리여부
        sw3 = (Switch)findViewById(R.id.sw3);
        boolean bScreenLock = (boolean) SharedPreferencesUtil.getInstance().getValue("ScreenLock", false);
        sw3.setChecked(bScreenLock);
        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                try {

                    if (isChecked) {
                        SharedPreferencesUtil.getInstance().setValue("ScreenLock", true);
                        BahasaApplication.getInstance().setScreenLock(true);
                    } else {
                        SharedPreferencesUtil.getInstance().setValue("ScreenLock", false);
                        BahasaApplication.getInstance().setScreenLock(false);
                    }
                    MainActivity.getMainActivity().getScreenLock();

                }catch (Exception e){
                }

            }
        });


        //여기서부터는 스킨 스타일 선택
        color_skin_item1 = (View)findViewById(R.id.color_skin_item1);
        vColor1 = (View) color_skin_item1.findViewById(R.id.vColor);
        vColor1.setBackgroundResource(R.color.SkinStyle1);
        vColor1.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                try {

                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 1);
                    BahasaApplication.getInstance().setSkinStyle(1);
                    MainActivity.getMainActivity().setReInitFragment();

                }catch (Exception e){
                }

            }
        });

        color_skin_item2 = (View)findViewById(R.id.color_skin_item2);
        vColor2 = (View) color_skin_item2.findViewById(R.id.vColor);
        vColor2.setBackgroundResource(R.color.SkinStyle2);
        vColor2.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                try {

                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 2);
                    BahasaApplication.getInstance().setSkinStyle(2);
                    MainActivity.getMainActivity().setReInitFragment();

                }catch (Exception e){
                }

            }
        });

        color_skin_item3 = (View)findViewById(R.id.color_skin_item3);
        vColor3 = (View) color_skin_item3.findViewById(R.id.vColor);
        vColor3.setBackgroundResource(R.color.SkinStyle3);
        vColor3.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 3);
                    BahasaApplication.getInstance().setSkinStyle(3);
                    MainActivity.getMainActivity().setReInitFragment();

                }catch (Exception e){
                }

            }
        });

        color_skin_item4 = (View)findViewById(R.id.color_skin_item4);
        vColor4 = (View) color_skin_item4.findViewById(R.id.vColor);
        vColor4.setBackgroundResource(R.color.SkinStyle4);
        vColor4.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 4);
                    BahasaApplication.getInstance().setSkinStyle(4);
                    MainActivity.getMainActivity().setReInitFragment();

                }catch (Exception e){
                }

            }
        });

        color_skin_item5 = (View)findViewById(R.id.color_skin_item5);
        vColor5 = (View) color_skin_item5.findViewById(R.id.vColor);
        vColor5.setBackgroundResource(R.color.SkinStyle5);
        vColor5.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 5);
                    BahasaApplication.getInstance().setSkinStyle(5);
                    MainActivity.getMainActivity().setReInitFragment();

                }catch (Exception e){
                }

            }
        });

        color_skin_item6 = (View)findViewById(R.id.color_skin_item6);
        vColor6 = (View) color_skin_item6.findViewById(R.id.vColor);
        vColor6.setBackgroundResource(R.color.SkinStyle6);
        vColor6.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 6);
                    BahasaApplication.getInstance().setSkinStyle(6);
                    MainActivity.getMainActivity().setReInitFragment();

                }catch (Exception e){
                }

            }
        });

        color_skin_item7 = (View)findViewById(R.id.color_skin_item7);
        vColor7 = (View) color_skin_item7.findViewById(R.id.vColor);
        vColor7.setBackgroundResource(R.color.SkinStyle7);
        vColor7.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 7);
                    BahasaApplication.getInstance().setSkinStyle(7);
                    MainActivity.getMainActivity().setReInitFragment();

                }catch (Exception e){
                }

            }
        });

        color_skin_item8 = (View)findViewById(R.id.color_skin_item8);
        vColor8 = (View) color_skin_item8.findViewById(R.id.vColor);
        vColor8.setBackgroundResource(R.color.SkinStyle8);
        vColor8.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 8);
                    BahasaApplication.getInstance().setSkinStyle(8);
                    MainActivity.getMainActivity().setReInitFragment();

                }catch (Exception e){
                }

            }
        });
    }

    private void InitSkinStyle()
    {
        try {

            nSkinStyle = BahasaApplication.getInstance().getSkinStyle();
            if (nSkinStyle == 1) {
                mainView.setBackgroundResource(R.color.SkinStyle1);
                //include.setBackgroundResource(R.color.SkinStyle1);
            } else if (nSkinStyle == 2) {
                mainView.setBackgroundResource(R.color.SkinStyle2);
                //include.setBackgroundResource(R.color.SkinStyle2);
            } else if (nSkinStyle == 3) {
                mainView.setBackgroundResource(R.color.SkinStyle3);
                //include.setBackgroundResource(R.color.SkinStyle3);
            } else if (nSkinStyle == 4) {
                mainView.setBackgroundResource(R.color.SkinStyle4);
                //include.setBackgroundResource(R.color.SkinStyle4);
            } else if (nSkinStyle == 5) {
                mainView.setBackgroundResource(R.color.SkinStyle5);
                //include.setBackgroundResource(R.color.SkinStyle5);
            } else if (nSkinStyle == 6) {
                mainView.setBackgroundResource(R.color.SkinStyle6);
                //include.setBackgroundResource(R.color.SkinStyle6);
            } else if (nSkinStyle == 7) {
                mainView.setBackgroundResource(R.color.SkinStyle7);
                //include.setBackgroundResource(R.color.SkinStyle7);
            } else if (nSkinStyle == 8) {
                mainView.setBackgroundResource(R.color.SkinStyle8);
                //include.setBackgroundResource(R.color.SkinStyle8);
            }

        }catch (Exception e){
        }

    }

}
