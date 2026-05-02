package com.yuchen.qingyi

import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.AppCompatTextView

class MainActivity: ComponentActivity() {
    /* --- --- --- 控件 / 类成员 --- --- --- */


    /* --- --- --- 系统区 - 生命周期函数 / 系统方法 --- --- --- */
    /*
    *   一切的起点，生命周期的开始
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initView()
    }



    /* --- --- --- 自定义区 - 类方法 --- --- --- */
    /*
    *   初始化控件/视口
    * */
    private fun initView() {
        findViewById<AppCompatTextView>(R.id.tv_hello).apply {
            typeface = Typeface.create(typeface, 600, false)
        }
    }
}