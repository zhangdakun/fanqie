// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask;


import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;

import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import android.support.v4.preference.PreferenceFragment;
import android.util.Log;
import android.widget.Toast;
import cn.doubao.tomatotask.R;

public class SettingPreferenceFragment extends PreferenceFragment
    implements android.content.SharedPreferences.OnSharedPreferenceChangeListener
{

    public SettingPreferenceFragment()
    {
    }

    private void alertDialogShow()
    {
        (new android.app.AlertDialog.Builder(getActivity())).setTitle("\u6E05\u9664\uFF1F").setMessage("\u662F\u5426\u6E05\u9664\u8BA1\u6570\uFF1F\n\u6CE8\uFF1A\u8BE5\u64CD\u4F5C\u4E0D\u53EF\u9006\uFF01").setPositiveButton("\u6E05\u9664", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                android.content.SharedPreferences.Editor editor = getActivity().getSharedPreferences("TomatoCount", 0).edit();
                editor.putInt("todayTomatoCount", 0);
                editor.putInt("allTomatoCount", 0);
                editor.commit();
                Toast.makeText(getActivity(), "\u6E05\u9664\u6210\u529F\uFF01", 0).show();
            }

//            final SettingPreferenceFragment this$0;
//
//            
//            {
//                this$0 = SettingPreferenceFragment.this;
//                super();
//            }
        }
).setNegativeButton("\u53D6\u6D88", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

//            final SettingPreferenceFragment this$0;
//
//            
//            {
//                this$0 = SettingPreferenceFragment.this;
//                super();
//            }
        }
).create().show();
    }

    private void doPickRingtone()
    {
        String s = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("pref_ringtone", null);
        Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
        intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", true);
        intent.putExtra("android.intent.extra.ringtone.TYPE", 2);
        intent.putExtra("android.intent.extra.ringtone.DEFAULT_URI", RingtoneManager.getDefaultUri(2));
        intent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", true);
        if(s != null)
        {
            intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", Uri.parse(s));
            Log.v("MAIN", "aaaaa");
        } else
        {
            intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", RingtoneManager.getDefaultUri(7));
            Log.v("MAIN", "ccccc");
        }
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        SharedPreferences sharedpreferences;
        android.content.SharedPreferences.Editor editor;
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedpreferences.edit();
        getActivity();
//        if(j == -1) goto _L2; else goto _L1
//_L1:
//        return;
//_L2:
//        i;
//        JVM INSTR tableswitch 1 1: default 48
//    //                   1 75;
//           goto _L3 _L4
//_L3:
//        mSoundsPref.setSummary(sharedpreferences.getString("pref_ringtone_name", null));
//        super.onActivityResult(i, j, intent);
//          goto _L5
//_L4:
//        Uri uri = (Uri)intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
//        if(uri == null)
//        {
//            editor.putString("pref_ringtone_name", "\u9759\u97F3");
//            editor.putString("pref_ringtone", null);
//            editor.commit();
//        } else
//        {
//            editor.putString("pref_ringtone_name", RingtoneManager.getRingtone(getActivity(), uri).getTitle(getActivity()));
//            editor.putString("pref_ringtone", uri.toString());
//            editor.commit();
//        }
//        if(true) goto _L3; else goto _L5
//_L5:
//        if(true) goto _L1; else goto _L6
//_L6:
        if(Activity.RESULT_OK == resultCode) {
        	switch (requestCode) {
			case 1:
		        Uri uri = (Uri)data.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
		        if(uri == null)
		        {
		            editor.putString("pref_ringtone_name", "\u9759\u97F3");
		            editor.putString("pref_ringtone", null);
		            editor.commit();
		        } else
		        {
		            editor.putString("pref_ringtone_name", RingtoneManager.getRingtone(getActivity(), uri).getTitle(getActivity()));
		            editor.putString("pref_ringtone", uri.toString());
		            editor.commit();
		        }
				break;

			default:
				break;
			}
        	
          mSoundsPref.setSummary(sharedpreferences.getString("pref_ringtone_name", null));
//          super.onActivityResult(i, j, intent);
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sharedpreferences.registerOnSharedPreferenceChangeListener(this);
        mSoundsPref = findPreference("pref_ringtone");
        lstPre_TomatoTime_value = (ListPreference)findPreference("TomatoTime_value");
        lstPre_BreakTime_value = (ListPreference)findPreference("BreakTime_value");
        lstPre_TomatoTime_value.setSummary(lstPre_TomatoTime_value.getEntry());
        lstPre_BreakTime_value.setSummary(lstPre_BreakTime_value.getEntry());
        mSoundsPref.setSummary(sharedpreferences.getString("pref_ringtone_name", null));
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferencescreen, Preference preference)
    {
        if(preference.getKey().equals("clearCount"))
            alertDialogShow();
        if(preference.getKey().equals("aboutTomatoTask"))
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://baike.baidu.com/link?url=b7rlhS6YssFup2xqAjnw9__6VsQnyhtVT8Gx_-qwckUE4IZ-ns6i_jw9w_aKH-C_sjWheb9NFR_GZcfUII0bV_")));
        if(preference.getKey().equals("supportAuthor")) {
//            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://m.alipay.com/personal/payment.htm?userId=2088302435546204&reason=%E6%94%AF%E6%8C%81%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91&weChat=true")));
        	Context context = getActivity();
        	String value = context.getString(R.string.app_name);
        	String qpEncoded="";
			try {
				qpEncoded = QuotedPrintable.encode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	String uri = "https://m.alipay.com/personal/payment.htm?userId=2088412432282130&reason="
        			+qpEncoded+"&weChat=true";
        	startActivity(new Intent("android.intent.action.VIEW", Uri.parse(uri)));
//        	startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://m.alipay.com/personal/payment.htm?userId=2088102827150732&reason=%E6%94%AF%E6%8C%81%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91&weChat=true")));
        }
        if(mSoundsPref == preference)
            doPickRingtone();
        return super.onPreferenceTreeClick(preferencescreen, preference);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s)
    {
        if(s.equals("TomatoTime_value"))
            lstPre_TomatoTime_value.setSummary(lstPre_TomatoTime_value.getEntry());
        if(s.equals("BreakTime_value"))
            lstPre_BreakTime_value.setSummary(lstPre_BreakTime_value.getEntry());
        if(s.equals("pref_ringtone"))
            mSoundsPref.setSummary(sharedpreferences.getString("pref_ringtone_name", null));
    }

    public static final String RINGTONE = "pref_ringtone";
    public static final String RINGTONE_TITLE_NAME = "pref_ringtone_name";
    ListPreference lstPre_BreakTime_value;
    ListPreference lstPre_TomatoTime_value;
    private Preference mSoundsPref;
}
