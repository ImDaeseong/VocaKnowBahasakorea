package com.daeseong.bahasakorea

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.daeseong.bahasakorea.Controls.SharedPreferencesUtil

class BahasaApplication : Application() {

    private val tag: String = BahasaApplication::class.java.simpleName

    companion object {
        private lateinit var mContext: Context
        private lateinit var mInstance: BahasaApplication

        fun getContext(): Context {
            return mContext.applicationContext
        }

        fun getInstance(): BahasaApplication {
            return mInstance
        }

        private var toast: Toast? = null
    }

    override fun onCreate() {
        super.onCreate()

        mContext = this
        mInstance = this

        try {
            SharedPreferencesUtil.getInstance().init(this)
        } catch (e: java.lang.Exception) {
        }
    }

    fun Toast(context: Context, sMsg: String?, bLengthLong: Boolean) {

        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.toast_layout, null)
        val tvtoast = view.findViewById<TextView>(R.id.tvtoast)

        //최대 1줄까지
        tvtoast.maxLines = 1
        tvtoast.setTextColor(Color.parseColor("#000000"))
        tvtoast.text = sMsg
        toast = Toast(context)
        toast!!.setGravity(Gravity.BOTTOM, 0, 0)

        if (bLengthLong) {
            toast!!.duration = Toast.LENGTH_LONG
        } else {
            toast!!.duration = Toast.LENGTH_SHORT
        }

        toast!!.view = view
        toast!!.show()
    }

    fun Toastcancel() {
        try {
            toast!!.cancel()
        } catch (e: Exception) {
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    //스킨 타입
    private var mSkinStyle = 1
    fun getSkinStyle(): Int {
        return mSkinStyle
    }

    fun setSkinStyle(nSkinStyle: Int) {
        mSkinStyle = nSkinStyle
    }

    //가나다라... 자동 반복 시간
    private var mAlfabetRepeatTime = 10
    fun getAlfabetRepeatTime(): Int {
        return mAlfabetRepeatTime
    }

    fun setAlfabetRepeatTime(nAlfabetRepeatTime: Int) {
        mAlfabetRepeatTime = nAlfabetRepeatTime
    }

    //음성사용 여부
    private var mbAlfabetRepeatSound = false
    fun getAlfabetRepeatSound(): Boolean {
        return mbAlfabetRepeatSound
    }

    fun setAlfabetRepeatSound(bAlfabetRepeatSound: Boolean) {
        mbAlfabetRepeatSound = bAlfabetRepeatSound
    }

    //단어장 자동 반복 시간
    private var mIngatRepeatTime = 10
    fun getIngatRepeatTime(): Int {
        return mIngatRepeatTime
    }

    fun setIngatRepeatTime(nIngatRepeatTime: Int) {
        mIngatRepeatTime = nIngatRepeatTime
    }

    //단어장 음성사용 여부
    private var mbIngatRepeatSound = false
    fun getIngatRepeatSound(): Boolean {
        return mbIngatRepeatSound
    }

    fun setIngatRepeatSound(bIngatRepeatSound: Boolean) {
        mbIngatRepeatSound = bIngatRepeatSound
    }

    //스크린 화면 락 처리여부
    private var mbScreenLock = false
    fun getScreenLock(): Boolean {
        return mbScreenLock
    }

    fun setScreenLock(bScreenLock: Boolean) {
        mbScreenLock = bScreenLock
    }

}
