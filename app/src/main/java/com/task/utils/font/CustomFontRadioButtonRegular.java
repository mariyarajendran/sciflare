package com.task.utils.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class CustomFontRadioButtonRegular extends androidx.appcompat.widget.AppCompatRadioButton {

    public CustomFontRadioButtonRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomFontRadioButtonRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFontRadioButtonRegular(Context context) {
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
