package com.yupaopao.android.debounce

import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class FastClick(val value: Long = AspectFastClick.FAST_CLICK_INTERVAL)