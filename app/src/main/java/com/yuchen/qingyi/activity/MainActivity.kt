package com.yuchen.qingyi.activity

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.AppCompatTextView
import com.yuchen.qingyi.R
import com.yuchen.qingyi.utils.SystemVersionUtils

class MainActivity: ComponentActivity() {
    /* --- --- --- 静态数据区 - 静态常量 / 宏 --- --- --- */
    companion object {
        private val CLASS_NAME = MainActivity::class.simpleName
    }

    /* --- --- --- 控件 / 类成员 --- --- --- */


    /* --- --- --- 系统区 - 生命周期函数 / 系统方法 --- --- --- */
    /*
    *   一切的起点，生命周期的开始
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initCore()
        initUI()
    }

    /* --- --- --- 自定义区 - 类方法 --- --- --- */
    /*
    *   初始化非UI界面的相关数据 / 实例
    * */
    private fun initCore() {
        initActivityAnimation()
    }

    /*
    *   初始化 UI 相关组件
    * */
    private fun initUI() {
        findViewById<AppCompatTextView>(R.id.tv_hello).apply {
            typeface = Typeface.create(typeface, 600, false)
        }
    }

    /*
    *   初始化 activity 动画
    * */
    @Suppress("DEPRECATION")
    private fun initActivityAnimation() {
        if (SystemVersionUtils.isAtLeastUpside()) {
            overrideActivityTransition(OVERRIDE_TRANSITION_OPEN,
                R.anim.fade_in,
                R.anim.fade_out
            )

            Log.d(CLASS_NAME, "当前 Android OS 版本 ≥ 14，使用 overrideActivityTransition 处理动画")
        } else {
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

            Log.d(CLASS_NAME, "当前 Android OS 版本 ≤ 14，使用 overridePendingTransition 处理动画")
        }
    }
}