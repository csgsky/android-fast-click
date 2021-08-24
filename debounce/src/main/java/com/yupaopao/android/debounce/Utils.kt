package com.yupaopao.android.debounce

import android.util.Log
import android.view.View

class Utils {
    companion object {
        val map = HashMap<String, Long>()
        fun isFastClick(view: View, interval: Long): Boolean {
            val key = R.id.view_click_time
            val currentClickTime = System.currentTimeMillis()
            if (null == view.getTag(key)) {
                view.setTag(key, currentClickTime)
                return false
            }
            val lastClickTime = view.getTag(key) as Long
            return if (currentClickTime - lastClickTime < interval) {
                true
            } else {
                view.setTag(key, currentClickTime)
                false
            }
        }

        fun isFastClick(tag: String, interval: Long): Boolean {
            val currentClickTime = System.currentTimeMillis()
            val lastClickTime = map[tag]
            if (lastClickTime == null) {
                Log.i("methodAnnotated","============0=============")
                map[tag] = currentClickTime
                return false
            }
            return if (currentClickTime - lastClickTime < interval) {
                Log.i("methodAnnotated","============1=============")
                true
            } else {
                Log.i("methodAnnotated","============2=============")
                map[tag] = currentClickTime
                false
            }
        }
    }
}