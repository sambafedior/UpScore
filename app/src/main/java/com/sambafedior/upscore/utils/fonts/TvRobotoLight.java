package com.sambafedior.upscore.utils.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.Hashtable;


public class TvRobotoLight extends TextView {

    private static final String asset = "roboto_light.ttf";
    public TvRobotoLight(Context context) {
        super(context);
    }
    public TvRobotoLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, asset);
    }
    public TvRobotoLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, asset);
    }
    public boolean setCustomFont(Context ctx, String asset) {
        setTypeface(TypeFace.get(ctx, asset));
        return true;
    }
}

class TypeFace {
    private static final String TAG = "Typefaces";
    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();
    public static Typeface get(Context c, String assetPath) {
        synchronized (cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    Typeface t = Typeface.createFromAsset(c.getAssets(),
                            assetPath);
                    cache.put(assetPath, t);
                } catch (Exception e) {
                    Log.e(TAG, "Could not get typeface '" + assetPath
                            + "' because " + e.getMessage());
                    return null;
                }
            }
            return cache.get(assetPath);
        }
    }
}