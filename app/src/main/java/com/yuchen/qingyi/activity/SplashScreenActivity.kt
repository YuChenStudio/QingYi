package com.yuchen.qingyi.activity

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieCompositionFactory
import com.yuchen.qingyi.R
import com.yuchen.qingyi.utils.TimeConsumingUtils

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity: ComponentActivity() {
    /* --- --- --- 静态数据区 - 静态常量 / 宏 --- --- --- */
    companion object {
        private val CLASS_NAME = SplashScreenActivity::class.simpleName

        private const val LOTTIE_LOGO_JSON_PATH = "logo/lottie/data.json"
        private const val LOTTIE_LOGO_IMAGES_FOLDER_PATH = "logo/lottie/images"
    }

    /* --- --- --- 控件 / 类成员 --- --- --- */
    lateinit var lottieAnimationView: LottieAnimationView
    lateinit var animationListener: Animator.AnimatorListener

    private val lifecycleCallback = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(p0: Activity, p1: Bundle?) { }
        override fun onActivityStarted(p0: Activity) { }

        override fun onActivityResumed(activity: Activity) {
            if (activity is MainActivity) {
                if (!isFinishing && !isDestroyed) {
                    finish()

                    Log.d(CLASS_NAME, "Main 界面加载完毕，准备释放 SplashScreen 界面...")
                }
            }
        }

        override fun onActivityPaused(p0: Activity) { }
        override fun onActivityStopped(p0: Activity) { }
        override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) { }
        override fun onActivityDestroyed(p0: Activity) { }
    }

    /* --- --- --- 系统区 - 生命周期函数 / 系统方法 --- --- --- */
    /*
    *   一切的起点，生命周期的开始
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setOnExitAnimationListener { splashScreenView ->
                splashScreenView.remove()
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        initCore()
        initUI()
    }

    override fun onDestroy() {
        super.onDestroy()

        lottieAnimationView.cancelAnimation()
        lottieAnimationView.removeAnimatorListener(animationListener)
        application.unregisterActivityLifecycleCallbacks(lifecycleCallback)
        Log.d(CLASS_NAME, "正在释放 SplashScreen 资源")
    }

    /* --- --- --- 自定义区 - 类方法 --- --- --- */
    /*
    *   初始化非UI界面的相关数据 / 实例
    * */
    private fun initCore() {
        application.registerActivityLifecycleCallbacks(lifecycleCallback)

        animationListener = object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
                Log.d(CLASS_NAME, "Logo 动画正在播放...")
//                ObjectAnimator.ofFloat(lottieAnimationView, "alpha", 0f, 1f).apply {
//                    setDuration(1800)
//                    start()
//                }
            }

            override fun onAnimationEnd(p0: Animator) {
                Log.d(CLASS_NAME, "Logo 动画播放完毕, 预计时长: ${lottieAnimationView.duration}, " +
                        "实际耗时: ${TimeConsumingUtils.timeConsumingMs()}")
                Log.d(CLASS_NAME, "准备跳转到 Main 界面，并执行动画: fade_in...")

                TimeConsumingUtils.remove()
                loadMainView()
            }

            override fun onAnimationCancel(p0: Animator) { }
            override fun onAnimationRepeat(p0: Animator) { }
        }
    }

    /*
    *   初始化 UI 相关组件
    * */
    private fun initUI() {
        findViewById<LottieAnimationView>(R.id.lav_logo).apply {
            lottieAnimationView = this
            LottieCompositionFactory.fromAsset(this@SplashScreenActivity, LOTTIE_LOGO_JSON_PATH)
                .addListener { composition ->
                    TimeConsumingUtils.start()
                    lottieAnimationView.setComposition(composition)
                    lottieAnimationView.imageAssetsFolder = LOTTIE_LOGO_IMAGES_FOLDER_PATH
                    lottieAnimationView.addAnimatorListener(animationListener)
                    lottieAnimationView.playAnimation()
                }

        }
    }

    @Suppress("DEPRECATION")
    private fun loadMainView() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}