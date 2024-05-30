package com.task.utils.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;


public class CustomFontButtonBold extends AppCompatButton {

    public CustomFontButtonBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomFontButtonBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFontButtonBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/sourcesanspro_bold.ttf");
            setTypeface(tf);
        }
    }

}
