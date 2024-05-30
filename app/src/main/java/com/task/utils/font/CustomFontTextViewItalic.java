package com.task.utils.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


public class CustomFontTextViewItalic extends AppCompatTextView {

    public CustomFontTextViewItalic(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomFontTextViewItalic(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFontTextViewItalic(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/sourcesans_mediumItalic.ttf");
            setTypeface(tf, Typeface.BOLD);
        }
    }

}
