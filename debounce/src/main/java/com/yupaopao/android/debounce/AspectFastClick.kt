package com.yupaopao.android.debounce

import android.util.Log
import android.view.View
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature


@Aspect
class AspectFastClick {

    companion object {
        const val FAST_CLICK_INTERVAL = 1200L
        private const val ON_CLICK_POINTCUTS =
            "execution(* android.view.View.OnClickListener.onClick(..))"
        private const val ON_CLICK_IN_XML_POINTCUTS =
            "execution(* android.support.v7.app.AppCompatViewInflater.DeclaredOnClickListener.onClick(..))"
        private const val ON_CLICK_IN_BUTTER_KNIFE_POINTCUTS =
            "execution(@butterknife.OnClick * *(..))"

        private const val ON_CLICK_ANNO =
            "execution(@com.yupaopao.android.debounce.SingleClick * *(..))"
    }

    @Pointcut(ON_CLICK_POINTCUTS)
    fun onClickPointcuts() {
    }

    @Pointcut(ON_CLICK_IN_XML_POINTCUTS)
    fun onClickInXmlPointcuts() {
    }

    @Pointcut(ON_CLICK_IN_BUTTER_KNIFE_POINTCUTS)
    fun onClickInButterKnifePointcuts() {
    }

    @Pointcut(ON_CLICK_ANNO)
    fun methodAnnotated() {
    }


    @Around("onClickPointcuts()")
    fun around(joinPoint: ProceedingJoinPoint) {
        val signature = joinPoint.signature as MethodSignature
        val method = signature.method
        if (method.isAnnotationPresent(Except::class.java)) {
            joinPoint.proceed()
            return
        }

        var target: View? = try {
            joinPoint.args?.first { it is View } as View
        } catch (e: NoSuchElementException) {
            null
        }

        target?.run {
            if (!Utils.isFastClick(this, FAST_CLICK_INTERVAL)) {
                joinPoint.proceed()
            }
        } ?: run {
            joinPoint.proceed()
        }
    }

    @Around("methodAnnotated()")
    fun aroundAnnotation(joinPoint: ProceedingJoinPoint) {
        val signature = joinPoint.signature as MethodSignature
        val name = signature.name
        val declaringTypeName = signature.declaringTypeName
        if (!Utils.isFastClick(name + declaringTypeName, FAST_CLICK_INTERVAL)) {
            joinPoint.proceed()
        }
    }
}