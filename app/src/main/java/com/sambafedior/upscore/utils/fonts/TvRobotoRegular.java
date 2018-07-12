package com.sambafedior.upscore.utils.fonts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;


public class TvRobotoRegular extends TextView {
    private static final String asset = "roboto_regular.ttf";

    public TvRobotoRegular(Context context) {
        super(context);
    }

    public TvRobotoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, asset);
    }

    public TvRobotoRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, asset);
    }

    public boolean setCustomFont(Context ctx, String asset) {
        setTypeface(TypeFace.get(ctx, asset));
        return true;
    }
}