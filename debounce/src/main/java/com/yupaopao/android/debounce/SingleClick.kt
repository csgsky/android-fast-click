package com.yupaopao.android.debounce

/**
 *  created by chenshaogang on 2021/8/24
 *  description:
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SingleClick( /* 点击间隔时间 */
    val value: Long = 1000
)
