package com.im.daeseong.bahasakorea;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Build;
import androidx.constraintlayout.widget.ConstraintLayout;//import android.support.constraint.ConstraintLayout;
import com.google.android.material.tabs.TabLayout;//import android.support.design.widget.TabLayout;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.im.daeseong.bahasakorea.Controls.FloatingTextView;
import com.im.daeseong.bahasakorea.Controls.OnSingleClickListener;
import com.im.daeseong.bahasakorea.Controls.ScreenLockutil;
import com.im.daeseong.bahasakorea.Controls.SwipeViewPager;
import com.im.daeseong.bahasakorea.Controls.UnitUtils;
import com.im.daeseong.bahasakorea.MainTab.MainPagerAdapter;
import com.im.daeseong.bahasakorea.MainTab.MainTab1Fragment;
import com.im.daeseong.bahasakorea.MainTab.MainTab2Fragment;
import com.im.daeseong.bahasakorea.MainTab.MainTab3Fragment;
import com.im.daeseong.bahasakorea.MainTab.MainTab4Fragment;
import com.im.daeseong.bahasakorea.MainTab.MainTab5Fragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static MainActivity mainActivity;
    public static MainActivity getMainActivity(){
        return  mainActivity;
    }
    private static void setMainActivity(MainActivity mainActivity){
        MainActivity.mainActivity = mainActivity;
    }

    private View include;
    private TabLayout Main_tabLayout;
    private SwipeViewPager Main_viewPager;
    private MainPagerAdapter mainPagerAdapter = null;

    private int nSkinStyle;
    private int nSkinStyleResource;

    private FloatingTextView floatingTextView;

    private int nCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.setMainActivity(this);

        InitTitleBar();

        setContentView(R.layout.activity_main);

        init();

        //InitSkinStyle();

        setViewPager();
        setInitTabLayout();

        initfloatingTextView();

        getScreenLock();
    }

    private void InitTitleBar(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //window.setStatusBarColor(Color.rgb(27, 40, 54));
            window.setStatusBarColor(getResources().getColor(R.color.statusbar_bg));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void initfloatingTextView(){

        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        int screenLength = size.y;

        ViewGroup rootView = (ViewGroup)findViewById(R.id.clMain);
        floatingTextView = new FloatingTextView(rootView);
        floatingTextView.getFloatingview().setVisibility(View.VISIBLE);

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) floatingTextView.getFloatingview().getLayoutParams();
        layoutParams.setMargins(0, (screenLength - UnitUtils.dip2px(this, 60)),0,0);
        floatingTextView.getFloatingview().setLayoutParams(layoutParams);
        rootView.addView(floatingTextView.getFloatingview());
    }

    private void init(){

        //탭
        Main_tabLayout = (TabLayout)findViewById(R.id.maintabLayout);

        //하단 뷰 페이지
        Main_viewPager = (SwipeViewPager) findViewById(R.id.mainviewPager);

        include = findViewById(R.id.include_maintoolbar);

        View cLhangulmenu = (View) include.findViewById(R.id.cLhangulmenu);
        cLhangulmenu.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {

                try {
                    startActivity(new Intent(MainActivity.this, SettingActivity.class));
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
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

            include.setBackgroundResource(nSkinStyleResource);
            Main_tabLayout.setBackgroundResource(nSkinStyleResource);

        }catch (Exception e){
        }

    }

    private void setInitTabLayout(){

        Main_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                try {

                    nCurrentIndex = tab.getPosition();
                    Main_viewPager.setCurrentItem(nCurrentIndex);
                    selectFragment(nCurrentIndex, true);

                    setInitFragment(nCurrentIndex);
                    switch (nCurrentIndex) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            setSwipeEnabled(true);
                            break;
                    }
                }catch (Exception e){
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                //Log.e(TAG, "tab 미선택:" + tab.position)

                try {

                    nCurrentIndex = tab.getPosition();
                    selectFragment(nCurrentIndex, false);

                }catch (Exception e){
                    Log.e(TAG, e.getMessage().toString());
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                //Log.e(TAG, "tab 선택 상태에서 다시 선택:" + tab.position)

                try {

                    nCurrentIndex = tab.getPosition();
                    selectFragment(nCurrentIndex, true);

                }catch (Exception e){
                    Log.e(TAG, e.getMessage().toString());
                }

            }
        });


        SwipeViewPager.OnPageChangeListener viewPagerPageChangeListener  = new SwipeViewPager.OnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                //Log.e(TAG, "onPageSelected:" + position);
                hideKeyboard();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };
        Main_viewPager.addOnPageChangeListener(viewPagerPageChangeListener );

    }

    private void setViewPager(){
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        Main_viewPager.setAdapter(mainPagerAdapter);
        Main_tabLayout.setupWithViewPager(Main_viewPager);
    }

    public void setSwipeEnabled(boolean enabled){
        Main_viewPager.setPagingEnabled(enabled);
    }

    private void setInitFragment(int nPosition){

        try {

            if(nPosition == 0){
                MainTab1Fragment mainTab1Fragment = (MainTab1Fragment) mainPagerAdapter.getFragment(0);
                mainTab1Fragment.setConfigInfo();
            }else if(nPosition == 1){
                MainTab2Fragment mainTab2Fragment = (MainTab2Fragment) mainPagerAdapter.getFragment(1);
                mainTab2Fragment.setConfigInfo();
            }else if(nPosition == 2){
                MainTab5Fragment mainTab5Fragment = (MainTab5Fragment) mainPagerAdapter.getFragment(2);
                mainTab5Fragment.setConfigInfo();
            }else if(nPosition == 3){
                MainTab3Fragment mainTab3Fragment = (MainTab3Fragment) mainPagerAdapter.getFragment(3);
                mainTab3Fragment.setConfigInfo();
            }else if(nPosition == 4){
                MainTab4Fragment mainTab4Fragment = (MainTab4Fragment) mainPagerAdapter.getFragment(4);
                mainTab4Fragment.setConfigInfo();
            }

        }catch (Exception e){
        }
    }

    private void hideKeyboard() {

        try {
            if (getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }catch (Exception e){
        }

    }

    public void getScreenLock(){

        try {
            ScreenLockutil.Lock_Flag(getWindow(), BahasaApplication.getInstance().getScreenLock());
        }catch (Exception e){
        }

    }

    public void setReInitFragment(){

        try {

            if(nCurrentIndex == 0){
                MainTab1Fragment mainTab1Fragment = (MainTab1Fragment) mainPagerAdapter.getFragment(0);
                mainTab1Fragment.setConfigInfo();
            }else if(nCurrentIndex == 1){
                MainTab2Fragment mainTab2Fragment = (MainTab2Fragment) mainPagerAdapter.getFragment(1);
                mainTab2Fragment.setConfigInfo();
            }else if(nCurrentIndex == 2){
                MainTab5Fragment mainTab5Fragment = (MainTab5Fragment) mainPagerAdapter.getFragment(2);
                mainTab5Fragment.setConfigInfo();
            }else if(nCurrentIndex == 3){
                MainTab3Fragment mainTab3Fragment = (MainTab3Fragment) mainPagerAdapter.getFragment(3);
                mainTab3Fragment.setConfigInfo();
            }else if(nCurrentIndex == 4){
                MainTab4Fragment mainTab4Fragment = (MainTab4Fragment) mainPagerAdapter.getFragment(4);
                mainTab4Fragment.setConfigInfo();
            }

        }catch (Exception e){
        }
    }

    public void selectFragment(int nCurrentIndex, boolean bActivityFragment){
        try {

            if(nCurrentIndex == 0){
                MainTab1Fragment mainTab1Fragment = (MainTab1Fragment) mainPagerAdapter.getFragment(0);
                mainTab1Fragment.setActivityFragment(bActivityFragment);
            }else if(nCurrentIndex == 1){
                MainTab2Fragment mainTab2Fragment = (MainTab2Fragment) mainPagerAdapter.getFragment(1);
                mainTab2Fragment.setActivityFragment(bActivityFragment);
            }else if(nCurrentIndex == 2){
                MainTab5Fragment mainTab5Fragment = (MainTab5Fragment) mainPagerAdapter.getFragment(2);
                mainTab5Fragment.setActivityFragment(bActivityFragment);
            }else if(nCurrentIndex == 3){
                MainTab3Fragment mainTab3Fragment = (MainTab3Fragment) mainPagerAdapter.getFragment(3);
                mainTab3Fragment.setActivityFragment(bActivityFragment);
            }else if(nCurrentIndex == 4){
                MainTab4Fragment mainTab4Fragment = (MainTab4Fragment) mainPagerAdapter.getFragment(4);
                mainTab4Fragment.setActivityFragment(bActivityFragment);
            }

        }catch (Exception e){
        }
    }

}
