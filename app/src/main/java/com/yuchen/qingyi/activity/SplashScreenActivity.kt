package com.yuchen.qingyi.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.yuchen.qingyi.R
import com.yuchen.qingyi.utils.SystemVersionUtils

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity: ComponentActivity() {
    /* --- --- --- 静态数据区 - 静态常量 / 宏 --- --- --- */
    companion object {
        private val CLASS_NAME = SplashScreenActivity::class.simpleName
    }

    /* --- --- --- 控件 / 类成员 --- --- --- */
    lateinit var handler: Handler


    /* --- --- --- 系统区 - 生命周期函数 / 系统方法 --- --- --- */
    /*
    *   一切的起点，生命周期的开始
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setOnExitAnimationListener { splashScreenView ->
                splashScreenView.remove()

                Log.d(CLASS_NAME, "正在释放 SplashScreen 资源")
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)

        Log.d(CLASS_NAME, "正在释放 SplashScreenActivity 资源")
    }



    /* --- --- --- 自定义区 - 类方法 --- --- --- */
    /*
    *   初始化控件/视口
    * */
    private fun initView() {
        handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            Log.d(CLASS_NAME, "3s后，跳转到首页")

            loadMainView()
        }, 1500)
    }


    @Suppress("DEPRECATION")
    private fun loadMainView() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()

        if (SystemVersionUtils.isAtLeastUpside()) {
            overrideActivityTransition(OVERRIDE_TRANSITION_OPEN, 0, 0)
        } else {
            overridePendingTransition(0, 0)
        }
    }
}