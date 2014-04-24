// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.util.Log;
import cn.doubao.tomatotask.R;
import java.util.ArrayList;

import com.umeng.analytics.MobclickAgent;

// Referenced classes of package com.android.tomatotask:
//            TomatoFragment, TaskFragment, SettingPreferenceFragment

public class ViewPagerActivity extends FragmentActivity
{
    public class MyViewPagerAdapter extends FragmentPagerAdapter
    {

        public int getCount()
        {
            return fragmentList.size();
        }

        public Fragment getItem(int i)
        {
            return (Fragment)fragmentList.get(i);
        }

        public CharSequence getPageTitle(int i)
        {
            return (CharSequence)titleList.get(i);
        }

//        final ViewPagerActivity this$0;
//
        public MyViewPagerAdapter(FragmentManager fragmentmanager)
        {
//            this$0 = ViewPagerActivity.this;
            super(fragmentmanager);
        }
    }


    public ViewPagerActivity()
    {
        titleList = new ArrayList();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.viewpager_layout);
        Log.i("MAIN", "--------ViewPagerActivity--------onCreate--------");
        m_vp = (ViewPager)findViewById(R.id.viewpager);
        pagerTabStrip = (PagerTabStrip)findViewById(R.id.pagertab);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(0x1060015));
        pagerTabStrip.setBackgroundColor(getResources().getColor(0x1060013));
        tomatoFragment = new TomatoFragment();
        taskFragment = new TaskFragment();
        settingPreferenceFragment = new SettingPreferenceFragment();
        fragmentList = new ArrayList();
        fragmentList.add(tomatoFragment);
        fragmentList.add(taskFragment);
        fragmentList.add(settingPreferenceFragment);
        titleList.add("\u756A\u8304 ");
        titleList.add("\u4EFB\u52A1");
        titleList.add("\u8BBE\u7F6E ");
        m_vp.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        
        
        MobclickAgent.onError(this);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Log.i("MAIN", "--------ViewPagerActivity--------onDestroy Method is executed");

    }

    protected void onPause()
    {
        super.onPause();
        Log.i("MAIN", "--------ViewPagerActivity--------onPause--------");
        MobclickAgent.onPause(this);
    }

    protected void onRestart()
    {
        super.onRestart();
        Log.i("MAIN", "--------ViewPagerActivity--------onRestart--------");
    }

    protected void onResume()
    {
        super.onResume();
        MobclickAgent.onResume(this);
        Log.i("MAIN", "--------ViewPagerActivity--------onResume--------");
    }

    protected void onStop()
    {
        super.onStop();
        Log.i("MAIN", "--------ViewPagerActivity--------onStop--------");
        
    }

    private ArrayList fragmentList;
    private ViewPager m_vp;
    private PagerTabStrip pagerTabStrip;
    private PagerTitleStrip pagerTitleStrip;
    private SettingPreferenceFragment settingPreferenceFragment;
    private TaskFragment taskFragment;
    ArrayList titleList;
    private TomatoFragment tomatoFragment;

}
