package com.daeseong.bahasakorea.MainTab

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.daeseong.bahasakorea.BahasaApplication
import com.daeseong.bahasakorea.Controls.AnimatorUtil.AnimatoSwipeLeft
import com.daeseong.bahasakorea.Controls.AnimatorUtil.AnimatoSwipeRight
import com.daeseong.bahasakorea.Controls.MarqueeTask
import com.daeseong.bahasakorea.Controls.OnSingleClickListener
import com.daeseong.bahasakorea.Controls.SwipeConstraintLayout
import com.daeseong.bahasakorea.Controls.SwipeConstraintLayout.OnSwipeConstraintListener
import com.daeseong.bahasakorea.Controls.TextToSpeechUtil
import com.daeseong.bahasakorea.Controls.UnitUtils.dip2px
import com.daeseong.bahasakorea.Database.KataItems
import com.daeseong.bahasakorea.Database.KataManager
import com.daeseong.bahasakorea.MainActivity
import com.daeseong.bahasakorea.R
import java.util.*


class MainTab3Fragment : Fragment() {

    companion object {
        private val tag = MainTab3Fragment::class.java.simpleName
    }

    private var mContext: Context? = null
    private var MainView: View? = null

    private var cLChange: View? = null
    private var ivChange: ImageView? = null

    private var cL1: View? = null
    private var tvPage: TextView? = null

    private var swipeConstraintLayout: SwipeConstraintLayout? = null
    private var tv1: TextView? = null
    private var tv2: TextView? = null
    private var tv3: TextView? = null

    private var cL2: View? = null
    private var tvDesc: TextView? = null

    private var CurrentPlayIndex = 0
    private var nTotalPageCount = 0
    private var item: KataItems? = null

    private var textToSpeechUtil: TextToSpeechUtil? = null

    private var task: MarqueeTask? = null
    private var timer: Timer? = null

    private var playtimer: Timer? = null

    private var nRepeatCount = 0
    private var nRepeatTime = 0
    private var nSkinStyle = 0
    private var nSkinStyleResource = 0
    private var nSwipeResource = 0
    private var bIsAutoPlay = false
    private var bUseSound = false

    private var handler: Handler? = null

    private var bActivityFragment = false

    private var kataManager: KataManager? = null

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {

        mContext = container!!.context
        MainView = inflater.inflate(R.layout.fragment_main_tab3, container, false)

        MainView!!.setOnTouchListener { v, event ->
            try {
                (activity as MainActivity?)!!.setSwipeEnabled(true)
            } catch (e: Exception) {
            }
            false
        }

        return MainView
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        changeDisplay()
        InitSkinStyle()
        DisplayMarquee()

        //DB
        kataManager = KataManager.getInstance(mContext!!)
        nTotalPageCount = kataManager!!.getTotalcount()

        CurrentKata()
    }

    override fun onPause() {
        super.onPause()

        try {

            if (bIsAutoPlay) {
                RunAutoPlay()
                DisplayMarquee()
            }
        } catch (e: Exception) {
        }
    }

    override fun onResume() {
        super.onResume()

        try {

            if (bIsAutoPlay) {
                RunAutoPlay()
                DisplayMarquee()
            }
        } catch (e: Exception) {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        try {
            handler!!.removeMessages(0)
            closeTimer()
            closePlayTimer()
        } catch (e: Exception) {
        }
    }

    private fun changeDisplay() {

        try {
            val display = activity!!.windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            val screenLength: Int = size.y

            val introViewheight = (screenLength / 3) + dip2px(mContext!!, 30f)
            if (screenLength > 0) {
                swipeConstraintLayout!!.layoutParams.height = introViewheight
            }
        } catch (e: Exception) {
        }
    }

    private fun init() {
        cLChange = MainView!!.findViewById(R.id.cLChange) as View
        ivChange = cLChange!!.findViewById<View>(R.id.ivChange) as ImageView
        ivChange!!.setBackgroundColor(Color.parseColor("#66000000"))
        cLChange!!.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                try {

                    if (!bIsAutoPlay) {
                        bIsAutoPlay = true
                        ivChange!!.setBackgroundResource(nSkinStyleResource)
                        RunAutoPlay()
                    } else {
                        bIsAutoPlay = false
                        ivChange!!.setBackgroundColor(Color.parseColor("#66000000"))
                        RunAutoPlay()
                    }

                    DisplayMarquee()
                } catch (e: Exception) {
                }
            }
        })

        cL1 = MainView!!.findViewById(R.id.cL1) as View
        tvPage = cL1!!.findViewById<View>(R.id.tvPage) as TextView

        swipeConstraintLayout = MainView!!.findViewById<View>(R.id.swCL) as SwipeConstraintLayout
        tv1 = swipeConstraintLayout!!.findViewById<View>(R.id.tv1) as TextView
        tv2 = swipeConstraintLayout!!.findViewById<View>(R.id.tv2) as TextView
        tv3 = swipeConstraintLayout!!.findViewById<View>(R.id.tv3) as TextView

        cL2 = MainView!!.findViewById(R.id.cL2) as View
        tvDesc = cL2!!.findViewById<View>(R.id.tvDesc) as TextView
        swipeConstraintLayout!!.setOnSwipeListener(object : OnSwipeConstraintListener {
            override fun swipeLeft() {
                try {
                    PreKata()
                } catch (e: Exception) {
                }
            }

            override fun swipeRight() {
                try {
                    NextKata()
                } catch (e: Exception) {
                }
            }

            override fun stateChange(bclcik: Boolean) {

                //swipe 기능 사용을 위해 내 부모의 swipe 기능 중지
                (activity as MainActivity?)!!.setSwipeEnabled(bclcik)
            }

            override fun swipeUp() {}
            override fun swipeDown() {}
        })

        handler = Handler()

        textToSpeechUtil = TextToSpeechUtil(mContext)

        //사운드 사용 여부
        bUseSound = BahasaApplication.getInstance().getAlfabetRepeatSound()

        //가나다라... 자동 반복 시간
        nRepeatTime = BahasaApplication.getInstance().getAlfabetRepeatTime()

        //스킨 타입
        nSkinStyle = BahasaApplication.getInstance().getSkinStyle()
    }

    private fun InitSkinStyle() {

        try {

            nSkinStyle = BahasaApplication.getInstance().getSkinStyle()
            when (nSkinStyle) {
                1 -> {
                    nSkinStyleResource = R.color.SkinStyle1
                    nSwipeResource = R.drawable.alfabet1_style
                }
                2 -> {
                    nSkinStyleResource = R.color.SkinStyle2
                    nSwipeResource = R.drawable.alfabet2_style
                }
                3 -> {
                    nSkinStyleResource = R.color.SkinStyle3
                    nSwipeResource = R.drawable.alfabet3_style
                }
                4 -> {
                    nSkinStyleResource = R.color.SkinStyle4
                    nSwipeResource = R.drawable.alfabet4_style
                }
                5 -> {
                    nSkinStyleResource = R.color.SkinStyle5
                    nSwipeResource = R.drawable.alfabet5_style
                }
                6 -> {
                    nSkinStyleResource = R.color.SkinStyle6
                    nSwipeResource = R.drawable.alfabet6_style
                }
                7 -> {
                    nSkinStyleResource = R.color.SkinStyle7
                    nSwipeResource = R.drawable.alfabet7_style
                }
                8 -> {
                    nSkinStyleResource = R.color.SkinStyle8
                    nSwipeResource = R.drawable.alfabet8_style
                }
            }

            swipeConstraintLayout!!.setBackgroundResource(nSwipeResource)
            tvPage!!.setTextColor(resources.getColor(nSkinStyleResource))
            tvDesc!!.setTextColor(resources.getColor(nSkinStyleResource))

        } catch (e: Exception) {
        }
    }

    private fun CurrentKata() {

        try {

            //DB
            if (CurrentPlayIndex < 0 || nTotalPageCount == 0) return

            val nIndex = CurrentPlayIndex + 1
            item = kataManager!!.getKata(nIndex)

            tv1!!.text = item!!.kataKor
            tv2!!.text = item!!.kataIndo
            tv3!!.text = item!!.kataEng

            CounterTimerLabel()

            //현재 프레그멘트 활성화시만 음성 출력
            if (bActivityFragment) {
                PlaySuaraSound(item!!.kataKor)
            }

        } catch (e: Exception) {
        }
    }

    private fun PreKata() {

        try {

            //DB
            if (nTotalPageCount == 0) return

            AnimatoSwipeRight(swipeConstraintLayout!!)

            CurrentPlayIndex--

            if (CurrentPlayIndex < 0) CurrentPlayIndex = nTotalPageCount - 1

            val nIndex = CurrentPlayIndex + 1
            item = kataManager!!.getKata(nIndex)

            tv1!!.text = item!!.kataKor
            tv2!!.text = item!!.kataIndo
            tv3!!.text = item!!.kataEng

            if (!bIsAutoPlay) CounterTimerLabel()

            PlaySuaraSound(item!!.kataKor)

        } catch (e: Exception) {
        }
    }

    private fun NextKata() {

        try {

            //DB
            if (nTotalPageCount == 0) return

            AnimatoSwipeLeft(swipeConstraintLayout!!)

            CurrentPlayIndex++

            if (CurrentPlayIndex > nTotalPageCount - 1) CurrentPlayIndex = 0

            val nIndex = CurrentPlayIndex + 1
            item = kataManager!!.getKata(nIndex)

            tv1!!.text = item!!.kataKor
            tv2!!.text = item!!.kataIndo
            tv3!!.text = item!!.kataEng

            if (!bIsAutoPlay) CounterTimerLabel()

            PlaySuaraSound(item!!.kataKor)

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

        try {
            closeTimer()
            task = MarqueeTask(tvDesc!!)
            timer = Timer()
            timer!!.schedule(task, 0, 20000)
        } catch (e: Exception) {
        }
    }

    private fun DisplayMarquee() {

        if (bIsAutoPlay) {

            val ContentMarquee = String.format("Sekarang sedang berjalan secara otomatis setiap %d detik.", nRepeatTime)
            tvDesc!!.text = ContentMarquee
        } else {

            val ContentMarquee = String.format("Klik tombol play menjalankan secara otomatis setiap %d detik.", nRepeatTime)
            tvDesc!!.text = ContentMarquee
        }

        startTimer()
    }

    private fun CounterTimerLabel() {

        try {
            val nDisplayIndex = CurrentPlayIndex + 1
            val sPage = String.format("Halaman saat ini (%d/%d)", nDisplayIndex, nTotalPageCount)
            tvPage!!.setTextColor(resources.getColor(nSkinStyleResource))
            tvPage!!.text = sPage
        } catch (e: Exception) {
        }
    }

    private fun PlaySuaraSound(sText: String) {

        try {

            if (bUseSound) {
                handler!!.postDelayed(Runnable {
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

    private fun closePlayTimer() {

        try {
            if (playtimer != null) {
                playtimer!!.cancel()
                playtimer = null
            }
        } catch (e: Exception) {
        }
    }

    private fun RunAutoPlay() {

        closePlayTimer()

        playtimer = Timer()
        playtimer!!.schedule(object : TimerTask() {
            override fun run() {

                activity!!.runOnUiThread(Runnable {
                    try {

                        if (!bIsAutoPlay) {
                            nRepeatCount = 0
                            CounterTimerLabel()
                            return@Runnable
                        }

                        nRepeatCount++

                        if (nRepeatCount % 2 == 0) {
                            tvPage!!.setTextColor(resources.getColor(nSkinStyleResource))
                        } else {
                            tvPage!!.setTextColor(Color.TRANSPARENT)
                        }

                        val nDisplayIndex = CurrentPlayIndex + 1
                        val sPage = String.format("%d detik berlalu (%d/%d)", nRepeatCount, nDisplayIndex, nTotalPageCount)
                        tvPage!!.text = sPage

                        //사운드 사용시
                        if (bUseSound) {
                            if (textToSpeechUtil!!.IsSpeaking()) return@Runnable
                        }

                        if (nRepeatTime <= nRepeatCount) {
                            nRepeatCount = 0
                            NextKata()
                        }

                    } catch (e: Exception) {
                    }
                })
            }
        }, 0, 1000)
    }

    fun setConfigInfo() {

        try {

            //사운드 사용 여부
            bUseSound = BahasaApplication.getInstance().getIngatRepeatSound()

            //단어장 자동 반복 시간
            nRepeatTime = BahasaApplication.getInstance().getIngatRepeatTime()

            //스킨 타입
            nSkinStyle = BahasaApplication.getInstance().getSkinStyle()

            closePlayTimer()

            bIsAutoPlay = false
            ivChange!!.setBackgroundColor(Color.parseColor("#66000000"))
            RunAutoPlay()

            DisplayMarquee()

        } catch (e: Exception) {
        }
    }

    fun setActivityFragment(bActivityFragment: Boolean) {

        try {
            closePlayTimer()

            this.bActivityFragment = bActivityFragment

            if (bActivityFragment) {
                ivChange!!.visibility = View.VISIBLE
            } else {
                ivChange!!.visibility = View.GONE
            }
        } catch (e: Exception) {
        }
    }
}