package com.my.project.android.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class IconFontView extends androidx.appcompat.widget.AppCompatTextView {
    public IconFontView(@NonNull Context context) {
        super(context);
        setFont(context);
    }

    public IconFontView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public IconFontView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }

    private void setFont(Context context) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "iconfont.ttf");
        setTypeface(type);
    }
}
