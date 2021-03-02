package com.daeseong.bahasakorea.BahasaAPI

import com.daeseong.bahasakorea.Database.KataItems
import java.util.*

class kataApi {
    
    private val hangulItemHashMap: HashMap<Int, KataItems> = HashMap<Int, KataItems>()

    companion object {
        private var instance: kataApi? = null
        fun getInstance(): kataApi {
            if (instance == null) {
                instance = kataApi()
            }
            return instance as kataApi
        }
    }
        
    fun InitData(items: MutableList<KataItems>) {

        try {
            
            for (i in items.indices) {
                val item: KataItems = items[i]
                val rIndex: Int = item.rIndex
                val KataKor: String = item.kataKor
                val KataIndo: String = item.kataIndo
                val KataEng: String = item.kataEng
                hangulItemHashMap[i] = KataItems(rIndex, KataKor, KataIndo, KataEng)
            }
            items.clear()            
        } catch (e: Exception) {
        }
    }

    operator fun get(nItem: Int): KataItems? {
        return hangulItemHashMap[nItem]
    }

    fun size(): Int {
        return hangulItemHashMap.size
    }

    fun clear() {
        hangulItemHashMap.clear()
    }
}
