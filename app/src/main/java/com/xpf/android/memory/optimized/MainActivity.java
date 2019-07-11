package com.xpf.android.memory.optimized;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTest = findViewById(R.id.btnTest);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateImageViews();
            }
        });
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
}
