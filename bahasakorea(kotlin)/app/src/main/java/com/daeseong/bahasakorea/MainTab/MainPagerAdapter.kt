package com.daeseong.bahasakorea.MainTab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val tag = MainPagerAdapter::class.java.simpleName

    companion object {
        private const val TAB_COUNT = 5
    }

    private val titles = arrayOf("Hangul", "huruf", "Kalimat", "kata", "Kamus")
    private val viewFragment = arrayOfNulls<Fragment>(5)

    override fun getItem(position: Int): Fragment {

        when (position) {
            0 -> {
                viewFragment[0] = MainTab1Fragment()
                return viewFragment[0]!!
            }
            1 -> {
                viewFragment[1] = MainTab2Fragment()
                return viewFragment[1]!!
            }
            2 -> {
                viewFragment[2] = MainTab5Fragment()
                return viewFragment[2]!!
            }
            3 -> {
                viewFragment[3] = MainTab3Fragment()
                return viewFragment[3]!!
            }
            4 -> {
                viewFragment[4] = MainTab4Fragment()
                return viewFragment[4]!!
            }
        }
        return null!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getCount(): Int {
        return TAB_COUNT
    }

    fun getFragment(position: Int): Fragment? {
        return viewFragment[position]
    }
}
