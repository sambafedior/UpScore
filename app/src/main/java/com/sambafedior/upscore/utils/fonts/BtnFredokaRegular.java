package com.sambafedior.upscore.utils.fonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class BtnFredokaRegular extends Button {

    private static final String asset = "fredoka_one_regular.ttf";

    public BtnFredokaRegular(Context context) {
        super(context);
    }

    public BtnFredokaRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, asset);
    }

    public BtnFredokaRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, asset);
    }

    public boolean setCustomFont(Context ctx, String asset) {
        setTypeface(TypeFace.get(ctx, asset));
        return true;
    }
}