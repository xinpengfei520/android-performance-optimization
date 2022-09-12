package com.xpf.android.performance.optimization

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.xpf.android.performance.optimization.databinding.ActivityMainBinding
import com.xpf.android.performance.optimization.utils.LaunchRecord.end

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnTest.setOnClickListener { generateImageViews() }
        binding.btnTest.viewTreeObserver.addOnDrawListener { end("onDraw") }
    }

    private fun generateImageViews() {
        for (i in 0..999) {
            val imageView = ImageView(this)
        }
        // 手动 gc
        System.gc()
        // 强制回收 Finalizer 对象
        System.runFinalization()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        end("onWindowFocusChanged")
    }
}