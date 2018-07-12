package com.sambafedior.upscore.utils.fonts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class EdtRobotoRegular  extends EditText {
    private static final String asset = "roboto_regular.ttf";
    public EdtRobotoRegular(Context context) {
        super(context);
    }
    public EdtRobotoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, asset);
    }
    public EdtRobotoRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, asset);
    }
    public boolean setCustomFont(Context ctx, String asset) {
        setTypeface(TypeFace.get(ctx, asset));
        return true;
    }
}