package com.daeseong.bahasakorea.MainTab

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ScrollView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.daeseong.bahasakorea.BahasaApplication
import com.daeseong.bahasakorea.Common.AlfabetItem
import com.daeseong.bahasakorea.Controls.AnimatorUtil.AnimatoScaleXY
import com.daeseong.bahasakorea.Controls.OnSingleClickListener
import com.daeseong.bahasakorea.Controls.TextToSpeechUtil
import com.daeseong.bahasakorea.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class MainTab1Fragment : Fragment() {

    companion object {
        private val tag = MainTab1Fragment::class.java.simpleName
    }

    private var mContext: Context? = null
    private var MainView: View? = null

    private var nSkinStyle = 0
    private var nSkinStyleResource = 0

    private var textToSpeechUtil: TextToSpeechUtil? = null

    private var timer: Timer? = null

    private var bUseSound = false

    private var handler: Handler? = null

    private val numRows = 20
    private val numCols = 11

    private var scrollView: ScrollView? = null
    private var gridLayout: GridLayout? = null
    private var floatingActionButton: FloatingActionButton? = null
    private var bActivityFragment = false

    private val AlpabatList = arrayOf(
        arrayOf("", "ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ"),
        arrayOf("ㄱ", "가", "갸", "거", "겨", "고", "교", "구", "규", "그", "기"),
        arrayOf("ㄴ", "나", "냐", "너", "녀", "노", "뇨", "누", "뉴", "느", "니"),
        arrayOf("ㄷ", "다", "댜", "더", "뎌", "도", "됴", "두", "듀", "드", "디"),
        arrayOf("ㄹ", "라", "랴", "러", "려", "로", "료", "루", "류", "르", "리"),
        arrayOf("ㅁ", "마", "먀", "머", "며", "모", "묘", "무", "뮤", "므", "미"),
        arrayOf("ㅂ", "바", "뱌", "버", "벼", "보", "뵤", "부", "뷰", "브", "비"),
        arrayOf("ㅅ", "사", "샤", "서", "셔", "소", "쇼", "수", "슈", "스", "시"),
        arrayOf("ㅇ", "아", "야", "어", "여", "오", "요", "우", "유", "으", "이"),
        arrayOf("ㅈ", "자", "쟈", "저", "져", "조", "죠", "주", "쥬", "즈", "지"),
        arrayOf("ㅊ", "차", "챠", "처", "쳐", "초", "쵸", "추", "츄", "츠", "치"),
        arrayOf("ㅋ", "카", "캬", "커", "켜", "코", "쿄", "쿠", "큐", "크", "키"),
        arrayOf("ㅌ", "타", "탸", "터", "텨", "토", "툐", "투", "튜", "트", "티"),
        arrayOf("ㅍ", "파", "퍄", "퍼", "펴", "포", "표", "푸", "퓨", "프", "피"),
        arrayOf("ㅎ", "하", "햐", "허", "혀", "호", "효", "후", "휴", "흐", "히"),
        arrayOf("ㄲ", "까", "꺄", "꺼", "껴", "꼬", "꾜", "꾸", "뀨", "끄", "끼"),
        arrayOf("ㄸ", "따", "땨", "떠", "뗘", "또", "뚀", "뚜", "뜌", "뜨", "띠"),
        arrayOf("ㅃ", "빠", "뺘", "뻐", "뼈", "뽀", "뾰", "뿌", "쀼", "쁘", "삐"),
        arrayOf("ㅆ", "싸", "쌰", "써", "쎠", "쏘", "쑈", "쑤", "쓔", "쓰", "씨"),
        arrayOf("ㅉ", "짜", "쨔", "쩌", "쪄", "쪼", "쬬", "쭈", "쮸", "쯔", "찌")
    )

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {

        mContext = container!!.context
        return inflater.inflate(R.layout.fragment_main_tab1, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MainView = view
        init()
        InitSkinStyle()
        InitData()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        try {
            handler!!.removeMessages(0)
            closeTimer()
        } catch (e: Exception) {
        }
    }

    private fun InitSkinStyle() {

        try {

            nSkinStyle = BahasaApplication.getInstance().getSkinStyle()
            when (nSkinStyle) {
                1 -> {
                    nSkinStyleResource = R.drawable.alfabet1_style
                }
                2 -> {
                    nSkinStyleResource = R.drawable.alfabet2_style
                }
                3 -> {
                    nSkinStyleResource = R.drawable.alfabet3_style
                }
                4 -> {
                    nSkinStyleResource = R.drawable.alfabet4_style
                }
                5 -> {
                    nSkinStyleResource = R.drawable.alfabet5_style
                }
                6 -> {
                    nSkinStyleResource = R.drawable.alfabet6_style
                }
                7 -> {
                    nSkinStyleResource = R.drawable.alfabet7_style
                }
                8 -> {
                    nSkinStyleResource = R.drawable.alfabet8_style
                }
            }
        } catch (e: Exception) {
        }
    }

    private fun closeTimer() {

        try {
            if (timer != null) {
                timer!!.cancel()
                timer = null
            }
        } catch (e: Exception) {
        }
    }

    private fun startTimer() {

        closeTimer()

        timer = Timer()
        timer!!.schedule(object : TimerTask() {

            var nTimeCount = 0

            override fun run() {
                try {

                    nTimeCount++

                    if (nTimeCount > 2) {

                        closeTimer()
                        activity!!.runOnUiThread {
                            floatingActionButton!!.hide()
                        }
                    }
                } catch (e: Exception) {
                }
            }
        }, 0, 1000)
    }

    private fun init() {

        scrollView = MainView!!.findViewById<ScrollView>(R.id.sv1)
        gridLayout = MainView!!.findViewById<GridLayout>(R.id.gl1)
        floatingActionButton = MainView!!.findViewById<FloatingActionButton>(R.id.fab1)

        floatingActionButton!!.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                try {
                    scrollView!!.scrollTo(0, 0)
                } catch (e: Exception) {
                }
            }
        })

        scrollView!!.viewTreeObserver.addOnScrollChangedListener {

            try {
                if (scrollView != null) {
                    if (scrollView!!.getChildAt(0).bottom <= scrollView!!.height + scrollView!!.scrollY) {
                        floatingActionButton!!.show()
                        startTimer()
                    } else {
                        floatingActionButton!!.hide()
                        closeTimer()
                    }
                }

            } catch (e: Exception) {
            }
        }

        handler = Handler()

        textToSpeechUtil = TextToSpeechUtil(mContext)

        //사운드 사용 여부
        bUseSound = BahasaApplication.getInstance().getAlfabetRepeatSound()
    }

    private fun InitData() {

        try {

            val display = activity!!.windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            val screenWidth = size.x
            val screenHeight = size.y

            gridLayout!!.columnCount = 11
            gridLayout!!.rowCount = 20

            var nResouce: Int = R.drawable.alfabet1_style

            for (i in 0 until numRows) {
                for (j in 0 until numCols) {

                    nResouce = if (i == 0 || j == 0) {
                        R.drawable.alfabet_border
                    } else {
                        nSkinStyleResource
                    }

                    val item = AlfabetItem(mContext!!)
                    item.setResourceType(nResouce)
                    item.setText(AlpabatList[i][j])
                    item.setOnClickListener(object : OnSingleClickListener() {
                        override fun onSingleClick(view: View) {
                            try {
                                val sText = (view as AlfabetItem).getText()
                                if (!TextUtils.isEmpty(sText)) {
                                    AnimatoScaleXY(view)
                                    PlaySuaraSound(sText)
                                }
                            } catch (e: Exception) {
                            }
                        }
                    })
                    gridLayout!!.addView(item, screenWidth / numCols, 60)
                }
            }
        } catch (e: Exception) {
        }
    }

    private fun PlaySuaraSound(sText: String?) {

        try {

            if (bUseSound) {

                handler!!.postDelayed({

                    if (textToSpeechUtil!!.isLanguageAvailable()) {
                        textToSpeechUtil!!.Speak(sText)
                    } else {
                        BahasaApplication.getInstance().Toast(mContext!!, "Tidak bisa Dengar Bahasa Korea.", false)
                    }
                }, 1000)
            }
        } catch (e: Exception) {
        }
    }

    fun setConfigInfo() {

        try {

            //사운드 사용 여부
            bUseSound = BahasaApplication.getInstance().getAlfabetRepeatSound()
        } catch (e: Exception) {
        }
    }

    fun setActivityFragment(bActivityFragment: Boolean) {

        try {
            this.bActivityFragment = bActivityFragment
        } catch (e: Exception) {
        }
    }
}


