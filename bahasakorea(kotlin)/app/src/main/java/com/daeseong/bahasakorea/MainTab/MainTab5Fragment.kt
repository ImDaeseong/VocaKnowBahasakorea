package com.daeseong.bahasakorea.MainTab

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.daeseong.bahasakorea.BahasaApplication
import com.daeseong.bahasakorea.MainActivity
import com.daeseong.bahasakorea.R

class MainTab5Fragment : Fragment() {

    companion object {
        private val tag = MainTab5Fragment::class.java.simpleName
    }

    private var mContext: Context? = null
    private var MainView: View? = null

    private var nSkinStyle = 0
    private var nSkinStyleResource = 0

    private var bActivityFragment = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mContext = container!!.context
        MainView = inflater.inflate(R.layout.fragment_main_tab5, container, false)

        MainView!!.setOnTouchListener { v, event ->
            try {
                (activity as MainActivity?)!!.setSwipeEnabled(true)
            } catch (e: Exception) {
            }
            false
        }

        return MainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        InitSkinStyle()
        InitData()
    }

    override fun onPause() {
        super.onPause()

        //Log.e(tag, "onPause")
    }

    override fun onResume() {
        super.onResume()

        //Log.e(tag, "onResume")
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun init() {
    }

    private fun InitData() {
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
        } catch (e: Exception) {
        }
    }

    fun setConfigInfo() {
    }

    fun setActivityFragment(bActivityFragment: Boolean) {

        try {
            this.bActivityFragment = bActivityFragment
        } catch (e: Exception) {
        }
    }
}
