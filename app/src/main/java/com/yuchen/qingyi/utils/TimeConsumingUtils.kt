package com.yuchen.qingyi.utils

object TimeConsumingUtils {
    private val curTimeThreadLocal = ThreadLocal<Long>()

    /**
     * 开始计时
     */
    fun start() {
        curTimeThreadLocal.set(System.nanoTime())
    }

    /**
     * 获取耗时（纳秒），线程安全
     * @return 耗时纳秒数，未调用 start() 返回 -1
     */
    fun timeConsumingNs(): Long {
        val startTime = curTimeThreadLocal.get() ?: return -1

        return System.nanoTime() - startTime
    }

    /**
     * 获取耗时（毫秒，保留小数），线程安全
     * @return 耗时毫秒数，未调用 start() 返回 -1.0
     */
    fun timeConsumingMs(): Double {
        val ns = timeConsumingNs()

        return if (ns != -1L) ns / 1_000_000.0 else -1.0
    }

    /**
     * 重置当前线程的计时状态（避免内存泄漏）
     */
    fun remove() {
        curTimeThreadLocal.remove()
    }
}