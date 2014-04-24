// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class SeekBarPreference extends DialogPreference
{

    public SeekBarPreference(Context context1, AttributeSet attributeset)
    {
        super(context1, attributeset);
        sensitivityLevel = null;
        layout = null;
        context = context1;
        persistInt(10);
    }

    public SeekBarPreference(Context context1, AttributeSet attributeset, int i)
    {
        super(context1, attributeset, i);
        sensitivityLevel = null;
        layout = null;
    }

    protected void onDialogClosed(boolean flag)
    {
        if(flag)
            persistInt(sensitivityLevel.getProgress());
        super.onDialogClosed(flag);
    }

    protected void onPrepareDialogBuilder(android.app.AlertDialog.Builder builder)
    {
        layout = new LinearLayout(context);
        layout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
        layout.setMinimumWidth(400);
        layout.setPadding(20, 20, 20, 20);
        sensitivityLevel = new SeekBar(context);
        sensitivityLevel.setMax(100);
        sensitivityLevel.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -2));
        sensitivityLevel.setProgress(getPersistedInt(10));
        layout.addView(sensitivityLevel);
        builder.setView(layout);
    }

    private Context context;
    private LinearLayout layout;
    private SeekBar sensitivityLevel;
}
