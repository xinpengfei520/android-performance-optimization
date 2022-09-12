package com.xpf.android.performance.optimization.utils

import android.util.Log

/**
 * LaunchRecord
 * @author Vance :)
 * @date 2022/9/12
 */
object LaunchRecord {

    private var start: Long = 0

    fun start() {
        start = System.currentTimeMillis()
    }

    fun end(process: String = "") {
        val cost = System.currentTimeMillis() - start
        Log.e("LaunchRecord", "====== $process: $cost ======")
    }
}