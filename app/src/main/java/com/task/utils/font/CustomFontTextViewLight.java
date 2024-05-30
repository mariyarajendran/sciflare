package com.task.utils.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

public class CustomFontTextViewLight extends AppCompatTextView {

    public CustomFontTextViewLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomFontTextViewLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFontTextViewLight(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/sourcesanspro_light.ttf");
            setTypeface(tf);
        }
    }

}
