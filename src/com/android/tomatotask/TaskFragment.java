// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.R.raw;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.doubao.tomatotask.R;
import com.android.tomatotask.Task.ChangeSqlite;
import com.android.tomatotask.Task.Date;
import com.android.tomatotask.Task.DrawLine;
import com.android.tomatotask.Task.Notepad;
import com.android.tomatotask.Task.NotepadAdapter;
import com.android.tomatotask.Task.SqliteHelper;

public class TaskFragment extends Fragment
{
    class ItemClick
        implements android.widget.AdapterView.OnItemClickListener
    {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            System.out.println("item----------click");
            Map map = (Map)itemList.get(i);
            if(((Boolean)map.get("EXPANDED")).booleanValue())
                map.put("EXPANDED", Boolean.valueOf(false));
            else
                map.put("EXPANDED", Boolean.valueOf(true));
            adapter.notifyDataSetChanged();
        }

//        final TaskFragment this$0;
//
//        ItemClick()
//        {
//            this$0 = TaskFragment.this;
//            super();
//        }
    }


    public TaskFragment()
    {
        EXPANDED = "EXPANDED";
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mMainView = getActivity().getLayoutInflater().inflate(R.layout.task_layout_main, (ViewGroup)getActivity().findViewById(R.id.viewpager), false);
        numberButton = (Button)mMainView.findViewById(R.id.number);
        topButton = (Button)mMainView.findViewById(R.id.topButton);
        listView = (ListView)mMainView.findViewById(R.id.listview);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Thin.ttf");
        numberButton.setTypeface(typeface);
        topButton.setTypeface(typeface);
        listView.setDivider(null);
        listView.setOnItemClickListener(new ItemClick());
        topButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                layout = (RelativeLayout)getActivity().getLayoutInflater().inflate(R.layout.writedown, null);
                textView = (TextView)layout.findViewById(R.id.writedate);
                editText = (DrawLine)layout.findViewById(R.id.edittext);
                getDate = new Date();
                date = getDate.getDate();
                textView.setText(date);
                (new android.app.AlertDialog.Builder(getActivity())).setView(layout).setPositiveButton("\u4FDD\u5B58", new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        android.database.sqlite.SQLiteDatabase sqlitedatabase = (new SqliteHelper(getActivity(), null, null, 0)).getWritableDatabase();
                        Notepad notepad = new Notepad();
                        ChangeSqlite changesqlite = new ChangeSqlite();
                        String s = editText.getText().toString();
                        if(s.equals(""))
                            Toast.makeText(getActivity(), "\u5185\u5BB9\u4E3A\u7A7A", 0).show();
                        String s1;
                        if(s.length() > 11)
                            s1 = (new StringBuilder(" ")).append(s.substring(0, 11)).toString();
                        else
                            s1 = s;
                        notepad.setContent(s);
                        notepad.setTitle(s1);
                        notepad.setdata(date);
                        changesqlite.add(sqlitedatabase, notepad);
                        showUpdate();
                    }

//                    final _cls1 this$1;
//
//                    
//                    {
//                        this$1 = _cls1.this;
//                        super();
//                    }
                }
).setNegativeButton("\u53D6\u6D88", new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                    }

//                    final _cls1 this$1;
//
//                    
//                    {
//                        this$1 = _cls1.this;
//                        super();
//                    }
                }
).show();
            }

//            final TaskFragment this$0;
//
//
//            
//            {
//                this$0 = TaskFragment.this;
//                super();
//            }
        }
);
        Log.i("MAIN", "^^^^^^^^TaskFragment^^^^^^^^onCreate^^^^^^^^");
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.i("MAIN", "^^^^^^^^TaskFragment^^^^^^^^onCreateView^^^^^^^^");
        ViewGroup viewgroup1 = (ViewGroup)mMainView.getParent();
        if(viewgroup1 != null)
        {
            viewgroup1.removeAllViewsInLayout();
            Log.i("MAIN", "^^^^^^^^TaskFragment^^^^^^^^removeAllViewsInLayout!!!!!!!!");
        }
        return mMainView;
    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.i("MAIN", "^^^^^^^^TaskFragment^^^^^^^^onDestroy Method^^^^^^^^");
    }

    public void onPause()
    {
        super.onPause();
        Log.i("MAIN", "^^^^^^^^TaskFragment^^^^^^^^onPause^^^^^^^^");
    }

    public void onResume()
    {
        super.onResume();
        showUpdate();
        Log.i("MAIN", "^^^^^^^^TaskFragment^^^^^^^^onResume^^^^^^^^");
    }

    public void onStart()
    {
        super.onStart();
        Log.i("MAIN", "^^^^^^^^TaskFragment^^^^^^^^onStart^^^^^^^^");
    }

    public void onStop()
    {
        super.onStop();
        Log.i("MAIN", "^^^^^^^^TaskFragment^^^^^^^^onStop^^^^^^^^");
    }

    public void showUpdate()
    {
        itemList = new ArrayList();
        android.database.sqlite.SQLiteDatabase sqlitedatabase = (new SqliteHelper(getActivity(), null, null, 0)).getReadableDatabase();
        Iterator iterator = (new ChangeSqlite()).query(sqlitedatabase).iterator();
        do
        {
            if(!iterator.hasNext())
            {
                Collections.reverse(itemList);
                adapter = new NotepadAdapter(getActivity(), this, itemList);
                listView.setAdapter(adapter);
                if(itemList.size() == 0)
                {
                    number = 0;
                    numberButton.setText("");
                }
                return;
            }
            Notepad notepad = (Notepad)iterator.next();
            HashMap hashmap = new HashMap();
            hashmap.put("titleItem", notepad.getTitle());
            hashmap.put("dateItem", notepad.getdata());
            hashmap.put("contentItem", notepad.getContent());
            hashmap.put("idItem", notepad.getid());
            hashmap.put("EXPANDED", Boolean.valueOf(true));
            itemList.add(hashmap);
            number = itemList.size();
            System.out.println((new StringBuilder("number----------number=")).append(number).toString());
            numberButton.setText((new StringBuilder("(")).append(number).append(")").toString());
        } while(true);
    }

    public String EXPANDED;
    public NotepadAdapter adapter;
    String date;
    EditText editText;
    Date getDate;
    public ArrayList itemList;
    RelativeLayout layout;
    public ListView listView;
    private View mMainView;
    public int number;
    public Button numberButton;
    TextView textView;
    public Button topButton;
}
