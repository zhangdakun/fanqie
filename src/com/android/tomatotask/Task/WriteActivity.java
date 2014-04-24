// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask.Task;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.*;

// Referenced classes of package com.android.tomatotask.Task:
//            BaseActivity, DrawLine, Date, SqliteHelper, 
//            Notepad, ChangeSqlite

public class WriteActivity extends BaseActivity
{

    public WriteActivity()
    {
        context = this;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03000b);
        textView = (TextView)findViewById(0x7f080010);
        editText = (DrawLine)findViewById(0x7f080011);
        cancelButton = (Button)findViewById(0x7f080012);
        sureButton = (Button)findViewById(0x7f080013);
        getDate = new Date();
        date = getDate.getDate();
        textView.setText(date);
        sureButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                android.database.sqlite.SQLiteDatabase sqlitedatabase = (new SqliteHelper(context, null, null, 0)).getWritableDatabase();
                Notepad notepad = new Notepad();
                ChangeSqlite changesqlite = new ChangeSqlite();
                String s = editText.getText().toString();
                if(s.equals(""))
                {
                    Toast.makeText(context, "\u5185\u5BB9\u4E3A\u7A7A", 0).show();
                } else
                {
                    String s1;
                    if(s.length() > 11)
                        s1 = (new StringBuilder(" ")).append(s.substring(0, 11)).toString();
                    else
                        s1 = s;
                    notepad.setContent(s);
                    notepad.setTitle(s1);
                    notepad.setdata(date);
                    changesqlite.add(sqlitedatabase, notepad);
                    finish();
                }
            }

//            final WriteActivity this$0;
//
//            
//            {
//                this$0 = WriteActivity.this;
//                super();
//            }
        }
);
        cancelButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                finish();
            }

//            final WriteActivity this$0;
//
//            
//            {
//                this$0 = WriteActivity.this;
//                super();
//            }
        }
);
    }

    private Button cancelButton;
    private Context context;
    private String date;
    private EditText editText;
    private Date getDate;
    private Button sureButton;
    private TextView textView;



}
