package com.my.project.android;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class IconFontSample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_font_sample);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        TextView t1 = findViewById(R.id.tv_200);
        t1.setTypeface(typeface);
        t1.setText("\ue9c5");
        TextView t2 = findViewById(R.id.tv_100);
        t2.setTypeface(typeface);
        TextView t3 = findViewById(R.id.tv_50);
        t3.setTypeface(typeface);
        TextView t4 = findViewById(R.id.tv_30);
        t4.setTypeface(typeface);
    }
}