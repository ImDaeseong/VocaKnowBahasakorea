package com.daeseong.bahasakorea

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.bahasakorea.Controls.RecycleUtil
import com.daeseong.bahasakorea.Controls.SharedPreferencesUtil
import com.daeseong.bahasakorea.Database.CopyDBfile

class SplashActivity : AppCompatActivity() {

    private val tag = SplashActivity::class.java.simpleName

    private var handler: Handler? = null

    private var mainView: View? = null
    private var nSkinStyle = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InitTitleBar()
        setContentView(R.layout.activity_splash)
        init()

        InitSkinStyle()

        InitData()
    }

    private fun init() {

        mainView = findViewById(android.R.id.content)

        //스킨 타입
        val nSkinStyle = SharedPreferencesUtil.getInstance().getValue("SkinStyle", 1) as Int
        BahasaApplication.getInstance().setSkinStyle(nSkinStyle)

        //가나다라... 자동 반복 시간
        val nAlfabetRepeatTime = SharedPreferencesUtil.getInstance().getValue("AlfabetRepeatTime", 10) as Int
        BahasaApplication.getInstance().setAlfabetRepeatTime(nAlfabetRepeatTime)

        //음성사용 여부
        val bAlfabetRepeatSound = SharedPreferencesUtil.getInstance().getValue("AlfabetRepeatSound", false) as Boolean
        BahasaApplication.getInstance().setAlfabetRepeatSound(bAlfabetRepeatSound)

        //단어장 자동 반복 시간
        val nIngatRepeatTime = SharedPreferencesUtil.getInstance().getValue("IngatRepeatTime", 10) as Int
        BahasaApplication.getInstance().setIngatRepeatTime(nIngatRepeatTime)

        //단어장 음성사용 여부
        val bIngatRepeatSound = SharedPreferencesUtil.getInstance().getValue("IngatRepeatSound", false) as Boolean
        BahasaApplication.getInstance().setIngatRepeatSound(bIngatRepeatSound)

        //스크린 화면 락 처리여부
        val bScreenLock = SharedPreferencesUtil.getInstance().getValue("ScreenLock", false) as Boolean
        BahasaApplication.getInstance().setScreenLock(bScreenLock)

        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    0 -> {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }
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
        } catch (e: Exception) {
        }
    }

    private fun InitData() {

        try {
            val bCopy = CopyDBTask().execute(this).get()
        } catch (e: java.lang.Exception) {
        }

        handler!!.postDelayed({
            try {

                val msg = handler!!.obtainMessage()
                msg.what = 0
                handler!!.sendMessage(msg)

            } catch (e: java.lang.Exception) {
            }
        }, 1000)
    }

    private fun InitTitleBar() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        //백버튼 기능 막음
        return
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()

        try {

            if (handler != null) {
                handler!!.removeCallbacksAndMessages(null)
                handler = null
            }

            RecycleUtil.recursiveRecycle(window.decorView)
            System.gc()
        } catch (e: java.lang.Exception) {
        }
    }

    inner class CopyDBTask : AsyncTask<Context?, Void?, Boolean>() {

        override fun doInBackground(vararg params: Context?): Boolean? {

            try {
                CopyDBfile(params[0]!!)
                return true
            } catch (e: java.lang.Exception) {
            }

            return false
        }
    }

}
