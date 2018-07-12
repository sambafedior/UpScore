package com.sambafedior.upscore.utils.fonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class TvFredokaRegular extends TextView {

    private static final String asset = "fredoka_one_regular.ttf";

    public TvFredokaRegular(Context context) {
        super(context);
    }

    public TvFredokaRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, asset);
    }

    public TvFredokaRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, asset);
    }

    public boolean setCustomFont(Context ctx, String asset) {
        setTypeface(TypeFace.get(ctx, asset));
        return true;
    }
}