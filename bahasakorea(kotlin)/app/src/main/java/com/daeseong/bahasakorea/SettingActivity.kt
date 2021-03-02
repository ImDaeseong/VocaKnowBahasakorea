package com.daeseong.bahasakorea

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.daeseong.bahasakorea.Controls.OnSingleClickListener
import com.daeseong.bahasakorea.Controls.RecycleUtil
import com.daeseong.bahasakorea.Controls.SharedPreferencesUtil

class SettingActivity : AppCompatActivity() {

    private val tag = SettingActivity::class.java.simpleName

    private var mainView: View? = null
    private var include: View? = null
    private var cLbtn_arrow: View? = null
    private var nSkinStyle = 0

    private var et1: EditText? = null
    private var et2: EditText? = null
    private var sw1: Switch? = null
    private var sw2: Switch? = null
    private var sw3: Switch? = null

    private var color_skin_item1: View? = null
    private var color_skin_item2: View? = null
    private var color_skin_item3: View? = null
    private var color_skin_item4: View? = null
    private var color_skin_item5: View? = null
    private var color_skin_item6: View? = null
    private var color_skin_item7: View? = null
    private var color_skin_item8: View? = null
    private var vColor1: View? = null
    private var vColor2: View? = null
    private var vColor3: View? = null
    private var vColor4: View? = null
    private var vColor5: View? = null
    private var vColor6: View? = null
    private var vColor7: View? = null
    private var vColor8: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InitTitleBar()
        setContentView(R.layout.activity_setting)
        init()

        InitSkinStyle()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()

        try {
            RecycleUtil.recursiveRecycle(window.decorView)
            System.gc()
        } catch (e: Exception) {
        }
    }

    //타이틀바 숨기기/가로보기 고정/풀스크린
    private fun InitTitleBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.statusbar_bg)
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    fun init() {

        mainView = findViewById(android.R.id.content)
        include = findViewById(R.id.setting_toolbar)

        //왼쪽 백이미지 버튼
        cLbtn_arrow = include!!.findViewById(R.id.cLbtn_arrow) as View
        cLbtn_arrow!!.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(view: View) {
                finish()
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
            }
        })

        //가나다라... 자동 반복 시간
        et1 = findViewById<EditText>(R.id.et1)
        val nAlfabetRepeatTime = SharedPreferencesUtil.getInstance().getValue("AlfabetRepeatTime", 10) as Int
        val sAlfabetRepeatTime = String.format("%d", nAlfabetRepeatTime)
        et1!!.setText(sAlfabetRepeatTime)
        et1!!.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {

                try {
                    if (!TextUtils.isEmpty(s.toString())) {
                        val nAlfabetRepeatTime = s.toString().toInt()
                        SharedPreferencesUtil.getInstance().setValue("AlfabetRepeatTime", nAlfabetRepeatTime)
                        BahasaApplication.getInstance().setAlfabetRepeatTime(nAlfabetRepeatTime)
                        MainActivity().getMainActivity()!!.setReInitFragment()
                    }
                } catch (e: java.lang.Exception) {
                }
            }
        })

        //단어장 자동 반복 시간
        et2 = findViewById<EditText>(R.id.et2)
        val nIngatRepeatTime = SharedPreferencesUtil.getInstance().getValue("IngatRepeatTime", 10) as Int
        val sIngatRepeatTime = String.format("%d", nIngatRepeatTime)
        et2!!.setText(sIngatRepeatTime)
        et2!!.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {

                try {
                    if (!TextUtils.isEmpty(s.toString())) {
                        val nIngatRepeatTime = s.toString().toInt()
                        SharedPreferencesUtil.getInstance().setValue("IngatRepeatTime", nIngatRepeatTime)
                        BahasaApplication.getInstance().setIngatRepeatTime(nIngatRepeatTime)
                        MainActivity().getMainActivity()!!.setReInitFragment()
                    }
                } catch (e: java.lang.Exception) {
                }
            }
        })

        //음성사용 여부
        sw1 = findViewById<Switch>(R.id.sw1)
        val bAlfabetRepeatSound = SharedPreferencesUtil.getInstance().getValue("AlfabetRepeatSound", false) as Boolean
        sw1!!.isChecked = bAlfabetRepeatSound
        sw1!!.setOnCheckedChangeListener { buttonView, isChecked ->

            try {
                if (isChecked) {
                    SharedPreferencesUtil.getInstance().setValue("AlfabetRepeatSound", true)
                    BahasaApplication.getInstance().setAlfabetRepeatSound(true)
                } else {
                    SharedPreferencesUtil.getInstance().setValue("AlfabetRepeatSound", false)
                    BahasaApplication.getInstance().setAlfabetRepeatSound(false)
                }
                MainActivity().getMainActivity()!!.setReInitFragment()
            } catch (e: java.lang.Exception) {
            }
        }

        //단어장 음성사용 여부
        sw2 = findViewById<Switch>(R.id.sw2)
        val bIngatRepeatSound = SharedPreferencesUtil.getInstance().getValue("IngatRepeatSound", false) as Boolean
        sw2!!.isChecked = bIngatRepeatSound
        sw2!!.setOnCheckedChangeListener { buttonView, isChecked ->

            try {
                if (isChecked) {
                    SharedPreferencesUtil.getInstance().setValue("IngatRepeatSound", true)
                    BahasaApplication.getInstance().setIngatRepeatSound(true)
                } else {
                    SharedPreferencesUtil.getInstance().setValue("IngatRepeatSound", false)
                    BahasaApplication.getInstance().setIngatRepeatSound(false)
                }
                MainActivity().getMainActivity()!!.setReInitFragment()
            } catch (e: java.lang.Exception) {
            }
        }

        //스크린 화면 락 처리여부
        sw3 = findViewById<Switch>(R.id.sw3)
        val bScreenLock = SharedPreferencesUtil.getInstance().getValue("ScreenLock", false) as Boolean
        sw3!!.isChecked = bScreenLock
        sw3!!.setOnCheckedChangeListener { buttonView, isChecked ->

            try {
                if (isChecked) {
                    SharedPreferencesUtil.getInstance().setValue("ScreenLock", true)
                    BahasaApplication.getInstance().setScreenLock(true)
                } else {
                    SharedPreferencesUtil.getInstance().setValue("ScreenLock", false)
                    BahasaApplication.getInstance().setScreenLock(false)
                }
                MainActivity().getMainActivity()!!.getScreenLock()
            } catch (e: java.lang.Exception) {
            }
        }

        //여기서부터는 스킨 스타일 선택
        color_skin_item1 = findViewById<View>(R.id.color_skin_item1)
        vColor1 = color_skin_item1!!.findViewById(R.id.vColor) as View
        vColor1!!.setBackgroundResource(R.color.SkinStyle1)
        vColor1!!.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(view: View) {
                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 1)
                    BahasaApplication.getInstance().setSkinStyle(1)
                    MainActivity().getMainActivity()!!.setReInitFragment()
                } catch (e: java.lang.Exception) {
                }
            }
        })

        color_skin_item2 = findViewById<View>(R.id.color_skin_item2)
        vColor2 = color_skin_item2!!.findViewById(R.id.vColor) as View
        vColor2!!.setBackgroundResource(R.color.SkinStyle2)
        vColor2!!.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(view: View) {
                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 2)
                    BahasaApplication.getInstance().setSkinStyle(2)
                    MainActivity().getMainActivity()!!.setReInitFragment()
                } catch (e: java.lang.Exception) {
                }
            }
        })

        color_skin_item3 = findViewById<View>(R.id.color_skin_item3)
        vColor3 = color_skin_item3!!.findViewById(R.id.vColor) as View
        vColor3!!.setBackgroundResource(R.color.SkinStyle3)
        vColor3!!.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(view: View) {
                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 3)
                    BahasaApplication.getInstance().setSkinStyle(3)
                    MainActivity().getMainActivity()!!.setReInitFragment()
                } catch (e: java.lang.Exception) {
                }
            }
        })

        color_skin_item4 = findViewById<View>(R.id.color_skin_item4)
        vColor4 = color_skin_item4!!.findViewById(R.id.vColor) as View
        vColor4!!.setBackgroundResource(R.color.SkinStyle4)
        vColor4!!.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(view: View) {
                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 4)
                    BahasaApplication.getInstance().setSkinStyle(4)
                    MainActivity().getMainActivity()!!.setReInitFragment()
                } catch (e: java.lang.Exception) {
                }
            }
        })

        color_skin_item5 = findViewById<View>(R.id.color_skin_item5)
        vColor5 = color_skin_item5!!.findViewById(R.id.vColor) as View
        vColor5!!.setBackgroundResource(R.color.SkinStyle5)
        vColor5!!.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(view: View) {
                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 5)
                    BahasaApplication.getInstance().setSkinStyle(5)
                    MainActivity().getMainActivity()!!.setReInitFragment()
                } catch (e: java.lang.Exception) {
                }
            }
        })

        color_skin_item6 = findViewById<View>(R.id.color_skin_item6)
        vColor6 = color_skin_item6!!.findViewById(R.id.vColor) as View
        vColor6!!.setBackgroundResource(R.color.SkinStyle6)
        vColor6!!.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(view: View) {
                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 6)
                    BahasaApplication.getInstance().setSkinStyle(6)
                    MainActivity().getMainActivity()!!.setReInitFragment()
                } catch (e: java.lang.Exception) {
                }
            }
        })

        color_skin_item7 = findViewById<View>(R.id.color_skin_item7)
        vColor7 = color_skin_item7!!.findViewById(R.id.vColor) as View
        vColor7!!.setBackgroundResource(R.color.SkinStyle7)
        vColor7!!.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(view: View) {
                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 7)
                    BahasaApplication.getInstance().setSkinStyle(7)
                    MainActivity().getMainActivity()!!.setReInitFragment()
                } catch (e: java.lang.Exception) {
                }
            }
        })

        color_skin_item8 = findViewById<View>(R.id.color_skin_item8)
        vColor8 = color_skin_item8!!.findViewById(R.id.vColor) as View
        vColor8!!.setBackgroundResource(R.color.SkinStyle8)
        vColor8!!.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(view: View) {
                try {
                    SharedPreferencesUtil.getInstance().setValue("SkinStyle", 8)
                    BahasaApplication.getInstance().setSkinStyle(8)
                    MainActivity().getMainActivity()!!.setReInitFragment()
                } catch (e: java.lang.Exception) {
                }
            }
        })
    }

    private fun InitSkinStyle() {

        try {

            nSkinStyle = BahasaApplication.getInstance().getSkinStyle()
            when (nSkinStyle) {
                1 -> {
                    mainView!!.setBackgroundResource(R.color.SkinStyle1)
                }
                2 -> {
                    mainView!!.setBackgroundResource(R.color.SkinStyle2)
                }
                3 -> {
                    mainView!!.setBackgroundResource(R.color.SkinStyle3)
                }
                4 -> {
                    mainView!!.setBackgroundResource(R.color.SkinStyle4)
                }
                5 -> {
                    mainView!!.setBackgroundResource(R.color.SkinStyle5)
                }
                6 -> {
                    mainView!!.setBackgroundResource(R.color.SkinStyle6)
                }
                7 -> {
                    mainView!!.setBackgroundResource(R.color.SkinStyle7)
                }
                8 -> {
                    mainView!!.setBackgroundResource(R.color.SkinStyle8)
                }
            }
        } catch (e: java.lang.Exception) {
        }
    }

}
