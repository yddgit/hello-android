package com.my.project.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //取消严格模式FileProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy( builder.build() );
        }
    }

    public void startLinearLayoutSample(View view) {
        Intent intent = new Intent(this, LinearLayoutSample.class);
        startActivity(intent);
    }

    public void startRelativeLayoutSample(View view) {
        Intent intent = new Intent(this, RelativeLayoutSample.class);
        startActivity(intent);
    }

    public void startTableLayoutSample(View view) {
        Intent intent = new Intent(this, TableLayoutSample.class);
        startActivity(intent);
    }

    public void startFrameLayoutSample(View view) {
        Intent intent = new Intent(this, FrameLayoutSample.class);
        startActivity(intent);
    }

    public void startGridLayoutSample(View view) {
        Intent intent = new Intent(this, GridLayoutSample.class);
        startActivity(intent);
    }

    public void startViewSample(View view) {
        Intent intent = new Intent(this, ViewSample.class);
        startActivity(intent);
    }

    public void startThreadSample(View view) {
        Intent intent = new Intent(this, ThreadSample.class);
        startActivity(intent);
    }

    public void startIntentSample(View view) {
        Intent intent = new Intent("com.my.project.android.IntentSample.ENTER");
        startActivity(intent);
    }

    public void startIconFontSample(View view) {
        Intent intent = new Intent(this, IconFontSample.class);
        startActivity(intent);
    }

    public void startLoginSample(View view) {
        Intent intent = new Intent(this, LoginSample.class);
        startActivity(intent);
    }
}