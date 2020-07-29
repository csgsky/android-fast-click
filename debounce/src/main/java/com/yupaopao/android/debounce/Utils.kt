package com.yupaopao.android.debounce

import android.view.View

class Utils {
    companion object {
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
    }
}