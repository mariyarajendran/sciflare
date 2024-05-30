package com.task.utils.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;


public class CustomFontEditTextRegular extends AppCompatEditText {

    public CustomFontEditTextRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomFontEditTextRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFontEditTextRegular(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/sourcesanspro_regular.ttf");
            setTypeface(tf);
        }
    }

}
