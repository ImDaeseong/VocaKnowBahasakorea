package com.daeseong.bahasakorea

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.daeseong.bahasakorea.Controls.FloatingTextView
import com.daeseong.bahasakorea.Controls.OnSingleClickListener
import com.daeseong.bahasakorea.Controls.ScreenLockutil.Lock_Flag
import com.daeseong.bahasakorea.Controls.SwipeViewPager
import com.daeseong.bahasakorea.Controls.UnitUtils.dip2px
import com.daeseong.bahasakorea.MainTab.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var mainActivity: MainActivity? = null
    fun getMainActivity(): MainActivity? {
        return mainActivity
    }

    private fun setMainActivity(mainActivity: MainActivity) {
        this.mainActivity = mainActivity
    }

    private var include: View? = null
    private var Main_tabLayout: TabLayout? = null
    private var Main_viewPager: SwipeViewPager? = null
    private var mainPagerAdapter: MainPagerAdapter? = null

    private var nSkinStyle = 0
    private var nSkinStyleResource = 0

    private var floatingTextView: FloatingTextView? = null

    private var nCurrentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setMainActivity(this)

        InitTitleBar()

        setContentView(R.layout.activity_main)

        init()

        setViewPager()
        setInitTabLayout()

        initfloatingTextView()

        getScreenLock()
    }

    private fun InitTitleBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.statusbar_bg)
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun initfloatingTextView() {

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val screenLength: Int = size.y

        val rootView = findViewById<ViewGroup>(R.id.clMain)
        floatingTextView = FloatingTextView(rootView)
        floatingTextView!!.getFloatingview()!!.visibility = View.VISIBLE

        val layoutParams = floatingTextView!!.getFloatingview()!!.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.setMargins(0, screenLength - dip2px(this, 60f), 0, 0)
        floatingTextView!!.getFloatingview()!!.layoutParams = layoutParams
        rootView.addView(floatingTextView!!.getFloatingview())
    }

    private fun init() {

        //탭
        Main_tabLayout = findViewById<TabLayout>(R.id.maintabLayout)

        //하단 뷰 페이지
        Main_viewPager = findViewById<SwipeViewPager>(R.id.mainviewPager)

        include = findViewById(R.id.include_maintoolbar)

        val cLhangulmenu = include!!.findViewById(R.id.cLhangulmenu) as View
        cLhangulmenu.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(view: View) {
                try {
                    startActivity(Intent(this@MainActivity, SettingActivity::class.java))
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                } catch (e: Exception) {
                }
            }
        })
    }

    private fun InitSkinStyle() {

        try {

            nSkinStyle = BahasaApplication.getInstance().getSkinStyle()
            when (nSkinStyle) {
                1 -> {
                    nSkinStyleResource = R.color.SkinStyle1
                }
                2 -> {
                    nSkinStyleResource = R.color.SkinStyle2
                }
                3 -> {
                    nSkinStyleResource = R.color.SkinStyle3
                }
                4 -> {
                    nSkinStyleResource = R.color.SkinStyle4
                }
                5 -> {
                    nSkinStyleResource = R.color.SkinStyle5
                }
                6 -> {
                    nSkinStyleResource = R.color.SkinStyle6
                }
                7 -> {
                    nSkinStyleResource = R.color.SkinStyle7
                }
                8 -> {
                    nSkinStyleResource = R.color.SkinStyle8
                }
            }

            include!!.setBackgroundResource(nSkinStyleResource)
            Main_tabLayout!!.setBackgroundResource(nSkinStyleResource)

        } catch (e: Exception) {
        }
    }

    private fun setInitTabLayout() {

        Main_tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                //Log.e(tag, "tab 선택:" + tab.position)

                try {

                    nCurrentIndex = tab.position
                    Main_viewPager!!.currentItem = nCurrentIndex
                    selectFragment(nCurrentIndex, true)
                    setInitFragment(nCurrentIndex)

                    when (nCurrentIndex) {
                        0 -> setSwipeEnabled(true)
                        1 -> setSwipeEnabled(true)
                        2 -> setSwipeEnabled(true)
                        3 -> setSwipeEnabled(true)
                        4 -> setSwipeEnabled(true)
                    }
                } catch (e: Exception) {
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

                //Log.e(tag, "tab 미선택:" + tab.position)

                supportFragmentManager.beginTransaction().apply {
                    selectFragment(tab.position, false)
                }.commitAllowingStateLoss()
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

                //Log.e(tag, "tab 선택 상태에서 다시 선택:" + tab.position)

                supportFragmentManager.beginTransaction().apply {
                    selectFragment(tab.position, true)
                }.commitAllowingStateLoss()
            }
        })

        val viewPagerPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
            override fun onPageSelected(position: Int) {

                hideKeyboard()
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}
            override fun onPageScrollStateChanged(state: Int) {}
        }

        Main_viewPager!!.addOnPageChangeListener(viewPagerPageChangeListener)
    }

    private fun setViewPager() {
        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        Main_viewPager!!.adapter = mainPagerAdapter
        Main_tabLayout!!.setupWithViewPager(Main_viewPager)
    }

    fun setSwipeEnabled(enabled: Boolean) {
        Main_viewPager!!.setPagingEnabled(enabled)
    }

    private fun setInitFragment(nPosition: Int) {
        try {
            when (nPosition) {
                0 -> {
                    val mainTab1Fragment = mainPagerAdapter!!.getFragment(0) as MainTab1Fragment?
                    mainTab1Fragment!!.setConfigInfo()
                }
                1 -> {
                    val mainTab2Fragment = mainPagerAdapter!!.getFragment(1) as MainTab2Fragment?
                    mainTab2Fragment!!.setConfigInfo()
                }
                2 -> {
                    val mainTab5Fragment = mainPagerAdapter!!.getFragment(2) as MainTab5Fragment?
                    mainTab5Fragment!!.setConfigInfo()
                }
                3 -> {
                    val mainTab3Fragment = mainPagerAdapter!!.getFragment(3) as MainTab3Fragment?
                    mainTab3Fragment!!.setConfigInfo()
                }
                4 -> {
                    val mainTab4Fragment = mainPagerAdapter!!.getFragment(4) as MainTab4Fragment?
                    mainTab4Fragment!!.setConfigInfo()
                }
            }
        } catch (e: Exception) {
        }
    }

    private fun hideKeyboard() {

        try {
            if (currentFocus != null) {
                val inputMethodManager: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
        } catch (e: java.lang.Exception) {
        }
    }

    fun getScreenLock() {

        try {
            Lock_Flag(window, BahasaApplication.getInstance().getScreenLock())
        } catch (e: java.lang.Exception) {
        }
    }

    fun setReInitFragment() {

        try {
            when (nCurrentIndex) {
                0 -> {
                    val mainTab1Fragment = mainPagerAdapter!!.getFragment(0) as MainTab1Fragment?
                    mainTab1Fragment!!.setConfigInfo()
                }
                1 -> {
                    val mainTab2Fragment = mainPagerAdapter!!.getFragment(1) as MainTab2Fragment?
                    mainTab2Fragment!!.setConfigInfo()
                }
                2 -> {
                    val mainTab5Fragment = mainPagerAdapter!!.getFragment(2) as MainTab5Fragment?
                    mainTab5Fragment!!.setConfigInfo()
                }
                3 -> {
                    val mainTab3Fragment = mainPagerAdapter!!.getFragment(3) as MainTab3Fragment?
                    mainTab3Fragment!!.setConfigInfo()
                }
                4 -> {
                    val mainTab4Fragment = mainPagerAdapter!!.getFragment(4) as MainTab4Fragment?
                    mainTab4Fragment!!.setConfigInfo()
                }
            }
        } catch (e: java.lang.Exception) {
        }
    }

    fun selectFragment(nCurrentIndex: Int, bActivityFragment: Boolean) {

        try {
            when (nCurrentIndex) {
                0 -> {
                    val mainTab1Fragment = mainPagerAdapter!!.getFragment(0) as MainTab1Fragment?
                    mainTab1Fragment!!.setActivityFragment(bActivityFragment)
                }
                1 -> {
                    val mainTab2Fragment = mainPagerAdapter!!.getFragment(1) as MainTab2Fragment?
                    mainTab2Fragment!!.setActivityFragment(bActivityFragment)
                }
                2 -> {
                    val mainTab5Fragment = mainPagerAdapter!!.getFragment(2) as MainTab5Fragment?
                    mainTab5Fragment!!.setActivityFragment(bActivityFragment)
                }
                3 -> {
                    val mainTab3Fragment = mainPagerAdapter!!.getFragment(3) as MainTab3Fragment?
                    mainTab3Fragment!!.setActivityFragment(bActivityFragment)
                }
                4 -> {
                    val mainTab4Fragment = mainPagerAdapter!!.getFragment(4) as MainTab4Fragment?
                    mainTab4Fragment!!.setActivityFragment(bActivityFragment)
                }
            }
        } catch (e: java.lang.Exception) {
        }
    }

}
