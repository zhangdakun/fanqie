// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.*;
import android.widget.*;
import cn.doubao.tomatotask.R;

public class SettingFragment extends Fragment
{
    private class SeekBarListener
        implements android.widget.SeekBar.OnSeekBarChangeListener
    {

        public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
        {
            int j = i + StartTick;
            int k = j % TickStep;
            int l;
            int i1;
            if(TickStep % 2 == 0)
                l = TickStep - TickStep % 2;
            else
                l = 1 + (TickStep - TickStep % 2);
            if(k < l)
                i1 = j - k;
            else
                i1 = j + (TickStep - k);
            textView.setText((new StringBuilder(String.valueOf(i1))).append("min").toString());
        }

        public void onStartTrackingTouch(SeekBar seekbar)
        {
        }

        public void onStopTrackingTouch(SeekBar seekbar)
        {
            int i = seekbar.getProgress() + StartTick;
            int j = i % TickStep;
            int k;
            int l;
            if(TickStep % 2 == 0)
                k = TickStep - TickStep % 2;
            else
                k = 1 + (TickStep - TickStep % 2);
            if(j < k)
                l = i - j;
            else
                l = i + (TickStep - j);
            seekbar.setProgress(l - StartTick);
            textView.setText((new StringBuilder(String.valueOf(l))).append("min").toString());
        }

        private int StartTick;
        private int TickStep;
        private TextView textView;
//        final SettingFragment this$0;

        public SeekBarListener(TextView textview, int i, int j)
        {
//            this$0 = SettingFragment.this;
            super();
            textView = textview;
            TickStep = j;
            StartTick = i;
        }
    }


    public SettingFragment()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mMainView = getActivity().getLayoutInflater().inflate(0x7f030006, (ViewGroup)getActivity().findViewById(0x7f08002d), false);
        Log.i("MAIN", "********SettingFragment********onCreate********");
        chkShake = (CheckBox)mMainView.findViewById(R.id.chkShake);
        chkTick = (CheckBox)mMainView.findViewById(R.id.chkTick);
        sbTick = (SeekBar)mMainView.findViewById(R.id.sbTick);
        sbRest = (SeekBar)mMainView.findViewById(R.id.sbRest);
        sbLongRest = (SeekBar)mMainView.findViewById(R.id.sbLongRest);
        tvTick = (TextView)mMainView.findViewById(R.id.tvTick);
        tvRest = (TextView)mMainView.findViewById(R.id.tvRest);
        tvLongRest = (TextView)mMainView.findViewById(R.id.tvLongRest);
        sbTick.setOnSeekBarChangeListener(new SeekBarListener(tvTick, 20, 5));
        sbRest.setOnSeekBarChangeListener(new SeekBarListener(tvRest, 1, 1));
        sbLongRest.setOnSeekBarChangeListener(new SeekBarListener(tvLongRest, 5, 5));
        mySharedPreferences = getActivity().getSharedPreferences("test", 0);
        tick = -20 + mySharedPreferences.getInt("tick", 20);
        rest = -1 + mySharedPreferences.getInt("rest", 1);
        longrest = -5 + mySharedPreferences.getInt("longrest", 5);
        Log.i("MAIN", (new StringBuilder("********tick=")).append(tick).append("****rest=").append(rest).append("****longrest=").append(longrest).append("********").toString());
        sbTick.setProgress(1 + tick);
        sbRest.setProgress(1 + rest);
        sbLongRest.setProgress(1 + longrest);
        sbTick.setProgress(tick);
        sbRest.setProgress(rest);
        sbLongRest.setProgress(longrest);
        boolean flag = mySharedPreferences.getBoolean("showshake", true);
        boolean flag1 = mySharedPreferences.getBoolean("showtick", true);
        chkShake.setChecked(flag);
        chkTick.setChecked(flag1);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.i("MAIN", "********SettingFragment********onCreateView********");
        ViewGroup viewgroup1 = (ViewGroup)mMainView.getParent();
        if(viewgroup1 != null)
        {
            viewgroup1.removeAllViewsInLayout();
            Log.i("MAIN", "********SettingFragment********removeAllViewsInLayout!!!!!!!!");
        }
        return mMainView;
    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.i("MAIN", "********SettingFragment********onDestroy********");
    }

    public void onPause()
    {
        super.onPause();
        Log.i("MAIN", "********SettingFragment********onPause********");
    }

    public void onResume()
    {
        super.onResume();
        Log.i("MAIN", "********SettingFragment********onResume********");
    }

    public void onStart()
    {
        super.onStart();
        Log.i("MAIN", "********SettingFragment********onStart********");
    }

    public void onStop()
    {
        super.onStop();
        Log.i("MAIN", "********SettingFragment********onStop********");
        int i = 20 + sbTick.getProgress();
        int j = 1 + sbRest.getProgress();
        int k = 5 + sbLongRest.getProgress();
        boolean flag = chkShake.isChecked();
        boolean flag1 = chkTick.isChecked();
        Log.i("MAIN", (new StringBuilder("****tick=")).append(tick).append("****tick_Change = ").append(i).append("****rest=").append(rest).append(" ****rest_Change=").append(j).append("***longrest=").append(longrest).append("***longrest_Change=").append(k).append("**").toString());
        if(20 + tick != i || 1 + rest != j || 5 + longrest != k)
        {
            mySharedPreferences = getActivity().getSharedPreferences("test", 0);
            android.content.SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putInt("tick", i);
            editor.putInt("rest", j);
            editor.putInt("longrest", k);
            editor.putBoolean("showshake", flag);
            editor.putBoolean("showtick", flag1);
            editor.commit();
            Toast.makeText(getActivity(), "\u8BBE\u7F6E\u5DF2\u4FDD\u5B58\uFF01", 0).show();
            Log.i("MAIN", "********SettingFragment********\u8BBE\u7F6E\u5DF2\u4FDD\u5B58\uFF01********");
        }
    }

    private CheckBox chkShake;
    private CheckBox chkTick;
    private int longrest;
    private View mMainView;
    private SharedPreferences mySharedPreferences;
    private int rest;
    private SeekBar sbLongRest;
    private SeekBar sbRest;
    private SeekBar sbTick;
    private int tick;
    private TextView tvLongRest;
    private TextView tvRest;
    private TextView tvTick;
}
