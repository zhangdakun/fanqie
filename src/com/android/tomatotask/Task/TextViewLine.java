// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask.Task;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewLine extends TextView
{

    public TextViewLine(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        ePaint = new Paint();
        ePaint.setColor(0xff000000);
        ePaint.setStyle(android.graphics.Paint.Style.STROKE);
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int i = getLineCount();
        int j = 0;
        do
        {
            if(j >= i)
            {
                super.onDraw(canvas);
                return;
            }
            float af[] = new float[4];
            af[0] = 15F;
            af[1] = (j + 1) * getLineHeight();
            af[2] = -20 + getWidth();
            af[3] = (j + 1) * getLineHeight();
            canvas.drawLines(af, ePaint);
            j++;
        } while(true);
    }

    private Paint ePaint;
}
