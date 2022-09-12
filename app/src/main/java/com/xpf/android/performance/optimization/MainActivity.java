package com.xpf.android.performance.optimization;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.xpf.android.performance.optimization.utils.LaunchRecord;

public class MainActivity extends AppCompatActivity {

    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTest = findViewById(R.id.btnTest);

        btnTest.setOnClickListener(v -> generateImageViews());

        btnTest.getViewTreeObserver().addOnDrawListener(() ->
                LaunchRecord.INSTANCE.end("onDraw")
        );
    }

    private void generateImageViews() {
        for (int i = 0; i < 1000; i++) {
            ImageView imageView = new ImageView(this);
        }
        // 手动 gc
        System.gc();
        // 强制回收 Finalizer 对象
        System.runFinalization();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LaunchRecord.INSTANCE.end("onWindowFocusChanged");
    }
}
