package com.daeseong.bahasakorea.Common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.daeseong.bahasakorea.R

class AlfabetItem : ConstraintLayout {

    private var tv1: TextView? = null
    private var sText: String? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(context, attributeSet, defStyle) {
        init(context)
    }

    private fun init(context: Context) {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.alfabet_item, this, true)
        tv1 = findViewById<TextView>(R.id.tv1)
    }

    fun setResourceType(nResource: Int) {
        setBackgroundResource(nResource)
    }

    fun setText(sText: String?) {
        this.sText = sText
        tv1!!.text = sText
    }

    fun getText(): String? {
        return sText
    }
}
