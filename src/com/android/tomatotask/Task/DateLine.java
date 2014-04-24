// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask.Task;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class DateLine extends TextView
{

    public DateLine(Context context)
    {
        super(context);
        ePaint = new Paint();
    }

    public DateLine(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        ePaint = new Paint();
        ePaint.setColor(0xff000000);
        ePaint.setStyle(android.graphics.Paint.Style.STROKE);
    }

    protected void onDraw(Canvas canvas)
    {
        canvas.drawLine(0.0F, 40F, getWidth(), 40F, ePaint);
        super.onDraw(canvas);
    }

    private Paint ePaint;
}
