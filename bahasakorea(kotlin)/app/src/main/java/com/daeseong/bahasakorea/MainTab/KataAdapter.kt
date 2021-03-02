package com.daeseong.bahasakorea.MainTab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.daeseong.bahasakorea.Database.KataItems
import com.daeseong.bahasakorea.R

class KataAdapter(context: Context, private val arraylist: ArrayList<KataItems>) : ArrayAdapter<KataItems>(context, R.layout.kata_list_item, arraylist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(context).inflate(R.layout.kata_list_item, parent, false)
        val tv1 = layout.findViewById<TextView>(R.id.tv1)
        val tv2 = layout.findViewById<TextView>(R.id.tv2)
        val tv3 = layout.findViewById<TextView>(R.id.tv3)

        val item = arraylist[position]
        tv1.text = item.kataKor
        tv2.text = item.kataIndo
        tv3.text = item.kataEng

        return layout
    }
}
