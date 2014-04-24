// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

public class CircleProgressBar extends View
{

    public CircleProgressBar(Context context)
    {
        super(context);
        maxProgress = 10;
        progress = 0;
        progressStrokeWidth = 6;
    }

    public CircleProgressBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        maxProgress = 10;
        progress = 0;
        progressStrokeWidth = 6;
        oval = new RectF();
        paint = new Paint();
    }

    public CircleProgressBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        maxProgress = 10;
        progress = 0;
        progressStrokeWidth = 6;
    }

    public int getMaxProgress()
    {
        return maxProgress;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int i = getWidth() / 2;
        int j = i - progressStrokeWidth / 2;
//        paint.setColor(-1);
        paint.setColor(Color.rgb(11, 11, 11));
        paint.setStyle(android.graphics.Paint.Style.STROKE);
        paint.setStrokeWidth(progressStrokeWidth);
        paint.setAntiAlias(true);
        canvas.drawCircle(i, i, j, paint);
        paint.setStrokeWidth(progressStrokeWidth);
        paint.setColor(Color.rgb(87, 135, 182));
        RectF rectf = new RectF(i - j, i - j, i + j, i + j);
        paint.setStyle(android.graphics.Paint.Style.STROKE);
        canvas.drawArc(rectf, -90F, (360 * progress) / maxProgress, false, paint);
    }

    public void setMaxProgress(int i)
    {
        maxProgress = i;
    }

    public void setProgress(int i)
    {
        progress = i;
        invalidate();
    }

    public void setProgressNotInUiThread(int i)
    {
        progress = i;
        postInvalidate();
    }

    private int maxProgress;
    RectF oval;
    Paint paint;
    private int progress;
    private int progressStrokeWidth;
}
