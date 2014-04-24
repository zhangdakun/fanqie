// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask;

import android.app.Activity;
import android.content.*;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.*;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import cn.doubao.tomatotask.R;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.android.tomatotask:
//            BreakActivity, CircleProgressBar, SettingFragment

public class MainActivity extends Activity
    implements android.view.View.OnClickListener
{
    class TimeCount extends CountDownTimer
    {

        public void onFinish()
        {
//            flag;
//            JVM INSTR tableswitch 2 2: default 24
//        //                       2 25;
//               goto _L1 _L2
//_L1:
//            return;
//_L2:
        	if(2 != flag) 
        		return;
            flag = 3;
            if(flagHide)
            {
                textView.setVisibility(View.VISIBLE);
                flagHide = false;
                int i1 = (new Random()).nextInt(8);
                animation = AnimationUtils.loadAnimation(MainActivity.this, ID[i1]);
                textView.startAnimation(animation);
            }
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
                    player.setDataSource(MainActivity.this, uri);
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
                    SharedPreferences sharedpreferences;
                    int i;
                    int j;
                    String s;
                    android.content.SharedPreferences.Editor editor;
                    int k;
                    int l;
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
            
            SharedPreferences sharedpreferences;
            int i;
            int j;
            String s;
            android.content.SharedPreferences.Editor editor;
            int k;
            int l;
            
            sharedpreferences = getSharedPreferences("TomatoCount", 0);
            i = sharedpreferences.getInt("todayTomatoCount", 0);
            j = sharedpreferences.getInt("allTomatoCount", 0);
            s = (new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())).format(new Date());
            editor = sharedpreferences.edit();
            k = i + 1;
            l = j + 1;
            editor.putInt("todayTomatoCount", k);
            editor.putInt("allTomatoCount", l);
            editor.putString("date", s);
            editor.commit();
            Log.v("MAIN", (new StringBuilder("-------todayTomatoCount---------")).append(k).append("-------").toString());
            Log.v("MAIN", (new StringBuilder("-------allTomatoCount---------")).append(l).append("-------").toString());
            Log.v("MAIN", (new StringBuilder("-------dateNowString---------")).append(s).append("-------").toString());
            Toast.makeText(MainActivity.this, getResources().getString(0x7f050006), 0).show();
            textView.setText("\u70B9\u6B64\u4F11\u606F\uFF01");
//            if(true) goto _L1; else goto _L3
//_L3:
        }

        public void onTick(long l)
        {
            String s = (new SimpleDateFormat("mm:ss")).format(new Date(l));
            textView.setText(s);
            progressBar.setProgressNotInUiThread((int)(l / 1000L));
        }

//        final MainActivity this$0;

        public TimeCount(long l, long l1)
        {
//            this$0 = MainActivity.this;
            super(l, l1);
        }
    }


    public MainActivity()
    {
        flag = 1;
        count = 0;
        percentTime = 0;
        flagHide = true;
        tick = 0;
        rest = 0;
        longrest = 0;
        exitTime = 0L;
        showShake = true;
    }

    private void setReader()
    {
        myshPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.v("MAIN", (new StringBuilder("-------SharedPreferences--------")).append(myshPreferences.getString("TomatoTime_value", "25")).append("-------").toString());
        tick = Integer.parseInt(myshPreferences.getString("TomatoTime_value", "25"));
        rest = Integer.parseInt(myshPreferences.getString("BreakTime_value", "25"));
        showShake = myshPreferences.getBoolean("startShake", true);
        showRing = myshPreferences.getString("pref_ringtone", "");
        Log.v("MAIN", (new StringBuilder("-------showRing--------")).append(myshPreferences.getString("pref_ringtone", "")).append("-------").toString());
        if(myshPreferences.getBoolean("developer_Mode", false))
        {
            tick = 1;
            rest = 1;
        }
    }

    private void stateFlag()
    {
//        flag;
//        JVM INSTR tableswitch 1 3: default 32
//    //                   1 33
//    //                   2 32
//    //                   3 127;
//           goto _L1 _L2 _L1 _L3
//_L1:
//        return;
//_L2:
//        timeSpan = 1000 * (60 * tick);
//        percentTime = 60 * tick;
//        time = new TimeCount(timeSpan, 1000L);
//        int i = (new Random()).nextInt(arrCharSequences.length);
//        Toast.makeText(this, arrCharSequences[i], 0).show();
//        flag = 2;
//        time.start();
//        continue; /* Loop/switch isn't completed */
//_L3:
//        Log.i("MAIN", (new StringBuilder("--intent-->>")).append(rest).toString());
//        Intent intent = new Intent(getApplicationContext(), com/android/tomatotask/BreakActivity);
//        intent.putExtra("rest", rest);
//        intent.putExtra("showShake", showShake);
//        intent.putExtra("showRing", showRing);
//        flag = 4;
//        startActivity(intent);
//        if(true) goto _L1; else goto _L4
//_L4:
    	switch (flag) {
		case 1:
	        timeSpan = 1000 * (60 * tick);
	        percentTime = 60 * tick;
	        time = new TimeCount(timeSpan, 1000L);
	        int i = (new Random()).nextInt(arrCharSequences.length);
	        Toast.makeText(this, arrCharSequences[i], 0).show();
	        flag = 2;
	        time.start();
			break;
		case 2:
			break;
		case 3:
	        Log.i("MAIN", (new StringBuilder("--intent-->>")).append(rest).toString());
	        Intent intent = new Intent(getApplicationContext(), BreakActivity.class);
	        intent.putExtra("rest", rest);
	        intent.putExtra("showShake", showShake);
	        intent.putExtra("showRing", showRing);
	        flag = 4;
	        startActivity(intent);
			break;

		default:
			break;
		}
    }

    public void onClick(View view)
    {
//        view.getId();
//        JVM INSTR tableswitch 2131230743 2131230743: default 24
//    //                   2131230743 25;
//           goto _L1 _L2
//_L1:
//        return;
//_L2:
//      stateFlag();
//      if(flagHide)
//      {
//          textView.setVisibility(0);
//          flagHide = false;
//          int i = (new Random()).nextInt(8);
//          animation = AnimationUtils.loadAnimation(this, ID[i]);
//          textView.startAnimation(animation);
//      } else
//      {
//          textView.setVisibility(4);
//          flagHide = true;
//      }
//        if(true) goto _L1; else goto _L3
//_L3:
    	switch (view.getId()) {
		case R.id.circleProgressbar:
	        stateFlag();
	        if(flagHide)
	        {
	            textView.setVisibility(View.VISIBLE);
	            flagHide = false;
	            int i = (new Random()).nextInt(8);
	            animation = AnimationUtils.loadAnimation(this, ID[i]);
	            textView.startAnimation(animation);
	        } else
	        {
	        	int a = View.GONE;
	            textView.setVisibility(View.INVISIBLE);
	            flagHide = true;
	        }
			break;

		default:
			break;
		}
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        Log.v("MAIN", "-------MainActivity---------onCreate-------");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        textView = (TextView)findViewById(R.id.txtView);
        textView.setTypeface(typeface);
        progressBar = (CircleProgressBar)findViewById(R.id.circleProgressbar);
        tomatocharSequence = getResources().getString(R.string.tomatocharSequence);
        breakcharSequence = getResources().getString(R.string.breakcharSequence);
        arrCharSequences = getResources().getStringArray(R.array.TomatoStart_String);
        setReader();
        progressBar.setMaxProgress(60 * tick);
        progressBar.setOnClickListener(this);
        int ai[] = new int[8];
        ai[0] = R.anim.my_alpha_action;
        ai[1] = R.anim.my_scale_action;
        ai[2] = R.anim.my_rotate_action;
        ai[3] = R.anim.alpha_scale;
        ai[4] = R.anim.alpha_rotate;
        ai[5] = R.anim.scale_rotate;
        ai[6] = R.anim.alpha_scale_rotate;
        ai[7] = R.anim.myown_design;
        ID = ai;
        stateFlag();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(0x7f070000, menu);
        return true;
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Log.v("MAIN", "-------MainActivity---------onDestroy-------");
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag1 = true;
        if(flag == 2)
        {
            if(i == 4 && keyevent.getRepeatCount() == 0)
            {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                builder.setTitle("\u653E\u5F03\uFF1F").setMessage("\u662F\u5426\u653E\u5F03\u8FD9\u4E2A\u756A\u8304\u5E76\u9000\u51FA\u5417\uFF1F").setPositiveButton("\u786E\u5B9A", new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        time.cancel();
                        finish();
                    }

//                    final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
                }
).setNegativeButton("\u53D6\u6D88", new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        dialoginterface.cancel();
                    }

//                    final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
                }
).create();
                builder.show();
            }
        } else
        if(i == 4 && keyevent.getAction() == 0)
        {
            if(System.currentTimeMillis() - exitTime > 2000L)
            {
                Toast.makeText(getApplicationContext(), "\u518D\u6309\u4E00\u6B21\u9000\u5230\u4E3B\u754C\u9762", 0).show();
                exitTime = System.currentTimeMillis();
            } else
            {
                time.cancel();
                finish();
                System.exit(0);
            }
        } else
        {
            flag1 = super.onKeyDown(i, keyevent);
        }
        return flag1;
    }

    public boolean onMenuItemSelected(int i, MenuItem menuitem)
    {
//        menuitem.getItemId();
//        JVM INSTR tableswitch 2131230740 2131230740: default 24
//    //                   2131230740 31;
//           goto _L1 _L2
//_L1:
//        return super.onMenuItemSelected(i, menuitem);
//_L2:
//        menuitem.setIntent(new Intent(this, com/android/tomatotask/SettingFragment));
//        if(true) goto _L1; else goto _L3
//_L3:
    	switch(menuitem.getItemId()) {
    	case R.id.action_settings:
    		menuitem.setIntent(new Intent(this, SettingFragment.class));
    		break;
    		default:
    			
    	}
    	return super.onMenuItemSelected(i, menuitem);
    }

    protected void onPause()
    {
        super.onPause();
        if(player != null && player.isPlaying())
            player.stop();
        Log.v("MAIN", "-------MainActivity---------onPause-------");
    }

    protected void onRestart()
    {
        super.onRestart();
        Log.v("MAIN", "-------MainActivity---------onRestart----Do Something----");
        setReader();
        progressBar.setMaxProgress(60 * tick);
        if(flag == 4)
        {
            flag = 1;
            if(flagHide)
            {
                textView.setVisibility(View.VISIBLE);
                flagHide = false;
                int i = (new Random()).nextInt(8);
                animation = AnimationUtils.loadAnimation(this, ID[i]);
                textView.startAnimation(animation);
            }
            stateFlag();
        }
    }

    protected void onResume()
    {
        super.onResume();
        Log.v("MAIN", "-------MainActivity---------onResume-------");
    }

    protected void onStop()
    {
        super.onStop();
        Log.v("MAIN", "-------MainActivity---------onStop-------");
    }

    private int ID[];
    private final String TAG = "MAIN";
    protected Animation animation;
    private CharSequence arrCharSequences[];
    private CharSequence breakcharSequence;
    private int count;
    private long exitTime;
    private int flag;
    private boolean flagHide;
    private int longrest;
    private int maxProgress;
    SharedPreferences myshPreferences;
    private int percentTime;
    MediaPlayer player;
    private CircleProgressBar progressBar;
    private int rest;
    private String showRing;
    private boolean showShake;
    private TextView textView;
    private int tick;
    private TimeCount time;
    private int timeSpan;
    private CharSequence tomatocharSequence;
    private Vibrator vibrator;












}
