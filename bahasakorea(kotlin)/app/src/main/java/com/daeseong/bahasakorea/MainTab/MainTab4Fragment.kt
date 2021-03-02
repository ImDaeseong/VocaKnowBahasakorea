package com.daeseong.bahasakorea.MainTab

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.ListView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.daeseong.bahasakorea.BahasaApplication
import com.daeseong.bahasakorea.Database.KataItems
import com.daeseong.bahasakorea.Database.KataManager
import com.daeseong.bahasakorea.MainActivity
import com.daeseong.bahasakorea.R


class MainTab4Fragment : Fragment() {

    companion object {
        private val tag = MainTab4Fragment::class.java.simpleName
    }

    private var mContext: Context? = null
    private var MainView: View? = null

    private var nSkinStyle = 0
    private var nSkinStyleResource = 0

    private var searchET: EditText? = null
    private var lv1: ListView? = null
    private var kataAdapter: KataAdapter? = null

    private var kataManager: KataManager? = null

    private var items: ArrayList<KataItems> = ArrayList()

    private val handler: Handler = Handler()

    private var bActivityFragment = false

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {

        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        mContext = container!!.context
        MainView = inflater.inflate(R.layout.fragment_main_tab4, container, false)

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

        lv1 = MainView!!.findViewById<ListView>(R.id.lv1)
        searchET = MainView!!.findViewById<EditText>(R.id.searchET)

        val watcher: searchTextWatcher = searchTextWatcher()
        searchET!!.addTextChangedListener(watcher)

        kataManager = KataManager.getInstance(mContext!!)
    }

    private fun InitData() {

        kataAdapter = KataAdapter(mContext!!, items)
        lv1!!.adapter = kataAdapter
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
        try {
            searchET!!.setText("")
            searchET!!.clearFocus()
        } catch (e: Exception) {
        }
    }

    fun setActivityFragment(bActivityFragment: Boolean) {

        try {
            this.bActivityFragment = bActivityFragment
        } catch (e: Exception) {
        }
    }

    internal inner class searchTextWatcher : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            try {
                if (TextUtils.isEmpty(s.toString())) {
                    kataAdapter!!.clear()
                    items!!.clear()
                    kataAdapter!!.addAll(items!!)
                    lv1!!.adapter = kataAdapter
                } else {
                    kataAdapter!!.clear()
                    items!!.clear()
                    items = kataManager!!.getSearchKata(s.toString())!!
                    kataAdapter!!.addAll(items!!)
                    lv1!!.adapter = kataAdapter
                }
            } catch (e: Exception) {
            }
        }
    }
}