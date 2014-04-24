// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import cn.doubao.tomatotask.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// Referenced classes of package com.android.tomatotask:
//            MainActivity

public class TomatoFragment extends Fragment
{

    public TomatoFragment()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mMainView = getActivity().getLayoutInflater().inflate(R.layout.tomato_layout, (ViewGroup)getActivity().findViewById(R.id.viewpager), false);
        tomatoTxtView = (TextView)mMainView.findViewById(R.id.tomatoTxtView);
        todayTomatoCountTextView = (TextView)mMainView.findViewById(R.id.todayTomatoCount);
        allTomatoCountTextView = (TextView)mMainView.findViewById(R.id.allTomatoCount);
        imageView = (ImageView)mMainView.findViewById(R.id.imageTomato);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Thin.ttf");
        tomatoTxtView.setTypeface(typeface);
        todayTomatoCountTextView.setTypeface(typeface);
        allTomatoCountTextView.setTypeface(typeface);
        Log.i("MAIN", "++++++++TomatoFragment++++++++onCreate++++++++");
        imageView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }

//            final TomatoFragment this$0;
//
//            
//            {
//                this$0 = TomatoFragment.this;
//                super();
//            }
        }
);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.i("MAIN", "++++++++TomatoFragment++++++++onCreateView++++++++");
        ViewGroup viewgroup1 = (ViewGroup)mMainView.getParent();
        if(viewgroup1 != null)
        {
            viewgroup1.removeAllViewsInLayout();
            Log.i("MAIN", "++++++++TomatoFragment++++++++removeAllViewsInLayout!!!!!!!!");
        }
        return mMainView;
    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.i("MAIN", "++++++++TomatoFragment++++++++onDestroy++++++++");
    }

    public void onPause()
    {
        super.onPause();
        Log.i("MAIN", "++++++++TomatoFragment++++++++onPause++++++++");
    }

    public void onResume()
    {
        super.onResume();
        Log.i("MAIN", "++++++++TomatoFragment++++++++onResume++++++++");
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("TomatoCount", 0);
        String s = sharedpreferences.getString("date", "2001-01-01");
        todayTomatoCount = sharedpreferences.getInt("todayTomatoCount", 0);
        allTomatoCount = sharedpreferences.getInt("allTomatoCount", 0);
        if(!s.equals((new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())).format(new Date())))
        {
            todayTomatoCount = 0;
            android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putInt("todayTomatoCount", todayTomatoCount);
            editor.commit();
        }
        todayTomatoCountTextView.setText((new StringBuilder("\u4ECA\u65E5:")).append(todayTomatoCount).toString());
        allTomatoCountTextView.setText((new StringBuilder("\u603B\u8BA1:")).append(allTomatoCount).toString());
        Log.v("MAIN", (new StringBuilder("----TomatoFragment---todayTomatoCount---------")).append(todayTomatoCount).append("-------").toString());
        Log.v("MAIN", (new StringBuilder("----TomatoFragment---allTomatoCount---------")).append(allTomatoCount).append("-------").toString());
        Log.v("MAIN", (new StringBuilder("----TomatoFragment---dateStr---------")).append(s).append("-------").toString());
        myshPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        tick = Integer.parseInt(myshPreferences.getString("TomatoTime_value", "25"));
//        tick;
//        JVM INSTR lookupswitch 4: default 340
//    //                   25: 341
//    //                   35: 354
//    //                   45: 367
//    //                   60: 380;
//           goto _L1 _L2 _L3 _L4 _L5
//_L1:
//        return;
//_L2:
//        imageView.setImageResource(0x7f020016);
//        continue; /* Loop/switch isn't completed */
//_L3:
//        imageView.setImageResource(0x7f020017);
//        continue; /* Loop/switch isn't completed */
//_L4:
//        imageView.setImageResource(0x7f020018);
//        continue; /* Loop/switch isn't completed */
//_L5:
//        imageView.setImageResource(0x7f020019);
//        if(true) goto _L1; else goto _L6
//_L6:
        switch(tick) {
        case 25:
        	imageView.setImageResource(R.drawable.tomato_25);
        	break;
        case 35:
        	imageView.setImageResource(R.drawable.tomato_35);
        	break;
        case 45:
        	imageView.setImageResource(R.drawable.tomato_45);
        	break;
        case 60:
        	imageView.setImageResource(R.drawable.tomato_60);
        	break;
        	default:
        		
        }
    }

    public void onStart()
    {
        super.onStart();
        Log.i("MAIN", "++++++++TomatoFragment++++++++onStart++++++++");
    }

    public void onStop()
    {
        super.onStop();
        Log.i("MAIN", "++++++++TomatoFragment++++++++onStop++++++++");
    }

    private int allTomatoCount;
    private TextView allTomatoCountTextView;
    private ImageView imageView;
    private View mMainView;
    SharedPreferences myshPreferences;
    int tick;
    private int todayTomatoCount;
    private TextView todayTomatoCountTextView;
    private TextView tomatoTxtView;
    private Vibrator vibrator;
}
