// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask.Task;

import java.util.Calendar;

public class Date
{

    public Date()
    {
    }

    public String getDate()
    {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int j = 1 + calendar.get(2);
        int k = calendar.get(5);
        int l = calendar.get(11);
        int i1 = calendar.get(12);
        int j1 = calendar.get(10);
        String s;
        if(l >= 13)
        {
            if(j1 == 0)
                j1 = 12;
            if(i1 < 10)
                s = (new StringBuilder(String.valueOf(i))).append("-").append(j).append("-").append(k).append("           ").append("\u4E0B\u5348").append(" ").append(j1).append(":").append("0").append(i1).toString();
            else
                s = (new StringBuilder(String.valueOf(i))).append("-").append(j).append("-").append(k).append("           ").append("\u4E0B\u5348").append(" ").append(j1).append(":").append(i1).toString();
        } else
        {
            if(j1 == 0)
                j1 = 12;
            if(i1 < 10)
                s = (new StringBuilder(String.valueOf(i))).append("-").append(j).append("-").append(k).append("           ").append("\u4E0B\u5348").append(" ").append(j1).append(":").append("0").append(i1).toString();
            else
                s = (new StringBuilder(String.valueOf(i))).append("-").append(j).append("-").append(k).append("           ").append("\u4E0A\u5348").append(" ").append(j1).append(":").append(i1).toString();
        }
        return s;
    }
}
