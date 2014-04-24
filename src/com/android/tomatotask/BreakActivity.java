// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import cn.doubao.tomatotask.R;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BreakActivity extends Activity
    implements android.view.View.OnClickListener
{
    class TimeCount extends CountDownTimer
    {

        public void onFinish()
        {
            if(showShake)
            {
                vibrator = (Vibrator)getSystemService("vibrator");
                long al[] = new long[8];
                al[0] = 200L;
                al[1] = 500L;
                al[2] = 200L;
                al[3] = 500L;
                al[4] = 1200L;
                al[5] = 500L;
                al[6] = 200L;
                al[7] = 500L;
                vibrator.vibrate(al, -1);
            }
            if(showRing != "")
            {
                Log.v("MAIN", "\u6211\u8FDB\u6765\u4E86\u6211\u8FDB\u6765\u4E86");
                player = new MediaPlayer();
                Uri uri = Uri.parse(showRing);
                try
                {
                    player.setDataSource(BreakActivity.this, uri);
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
                if(((AudioManager)getSystemService("audio")).getStreamVolume(2) != 0)
                {
                    player.setAudioStreamType(2);
                    Log.v("MAIN", "2");
                    player.setLooping(false);
                    try
                    {
                        player.prepare();
                    }
                    catch(Exception exception1)
                    {
                        exception1.printStackTrace();
                    }
                    player.start();
                }
            }
            returnbButton.setVisibility(0);
        }

        public void onTick(long l)
        {
            String s = (new SimpleDateFormat("mm:ss")).format(new Date(l));
            breakTextView.setText(s);
            int i = (int)((long)(60 * rest) - l / 1000L);
            breakProgressBar.setProgress(i);
        }

//        final BreakActivity this$0;

        public TimeCount(long l, long l1)
        {
//            this$0 = BreakActivity.this;
            super(l, l1);
        }
    }


    public BreakActivity()
    {
        rest = 0;
        exitTime = 0L;
        showShake = true;
    }

    public void onClick(View view)
    {
        finish();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030001);
        Log.v("MAIN", "-------BreakActivity---------onCreate-------");
        breakProgressBar = (ProgressBar)findViewById(R.id.breakBar);
        breakTextView = (TextView)findViewById(R.id.breakTxtView);
        returnbButton = (Button)findViewById(R.id.ReturnButton);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        breakTextView.setTypeface(typeface);
        Intent intent = getIntent();
        rest = intent.getIntExtra("rest", 5);
        showShake = intent.getBooleanExtra("showShake", true);
        showRing = intent.getStringExtra("showRing");
        Log.v("MAIN", (new StringBuilder(String.valueOf(showRing))).append("----------------showRing----").toString());
        timeSpan = 1000 * (60 * rest);
        breakProgressBar.setMax(60 * rest);
        returnbButton.setOnClickListener(this);
        time = new TimeCount(timeSpan, 1000L);
        time.start();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Log.v("MAIN", "-------BreakActivity---------onDestroy-------");
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i == 4 && keyevent.getRepeatCount() == 0)
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setTitle("\u756A\u8304\uFF1F").setMessage("\u653E\u5F03\u4F11\u606F\u7EE7\u7EED\u4E0B\u4E00\u4E2A\u756A\u8304\uFF1F").setPositiveButton("\u756A\u8304", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    time.cancel();
                    finish();
                }

//                final BreakActivity this$0;
//
//            
//            {
//                this$0 = BreakActivity.this;
//                super();
//            }
            }
).setNegativeButton("\u4F11\u606F", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    dialoginterface.cancel();
                }

//                final BreakActivity this$0;
//
//            
//            {
//                this$0 = BreakActivity.this;
//                super();
//            }
            }
).create();
            builder.show();
        }
        return super.onKeyDown(i, keyevent);
    }

    protected void onPause()
    {
        super.onPause();
        if(player != null && player.isPlaying())
            player.stop();
        Log.v("MAIN", "-------BreakActivity---------onPause-------");
    }

    protected void onRestart()
    {
        super.onRestart();
        Log.v("MAIN", "-------BreakActivity---------onRestart-------");
    }

    protected void onResume()
    {
        super.onResume();
        Log.v("MAIN", "-------BreakActivity---------onResume-------");
    }

    protected void onStop()
    {
        super.onStop();
        Log.v("MAIN", "-------BreakActivity---------onStop-------");
    }

    private ProgressBar breakProgressBar;
    private TextView breakTextView;
    private long exitTime;
    MediaPlayer player;
    private int rest;
    private Button returnbButton;
    private String showRing;
    private boolean showShake;
    private TimeCount time;
    private int timeSpan;
    private Vibrator vibrator;









}
