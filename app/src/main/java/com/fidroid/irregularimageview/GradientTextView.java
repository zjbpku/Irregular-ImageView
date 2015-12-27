package com.fidroid.irregularimageview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by jabin on 12/27/15.
 */
public class GradientTextView extends TextView {
    protected Shader shader = null;

    public GradientTextView(Context context) {
        super(context);
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            if (shader == null) {
                shader = new LinearGradient(0, 0, getWidth(), getHeight(), Color.YELLOW, Color.RED, Shader.TileMode.CLAMP);
            }
            getPaint().setShader(shader);
        }
    }
}
