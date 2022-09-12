package com.xpf.android.performance.optimization

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.xpf.android.performance.optimization.utils.LaunchRecord
import java.util.concurrent.Executors
import kotlin.math.max

class App : Application() {

    private val CPU_COUNT = Runtime.getRuntime().availableProcessors()
    private val CORE_POOL_SIZE = max(2, (CPU_COUNT - 1).coerceAtMost(4))

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        val pool = Executors.newFixedThreadPool(CORE_POOL_SIZE);
        pool.submit {
            // init sdk1
        }
        pool.submit {
            // init sdk2
        }
        pool.submit {
            // init sdk3
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        LaunchRecord.start()
    }
}
