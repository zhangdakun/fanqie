// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask.Task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.*;
import java.io.PrintStream;

// Referenced classes of package com.android.tomatotask.Task:
//            BaseActivity, DrawLine, Date, SqliteHelper, 
//            Notepad, ChangeSqlite

public class EditActivity extends BaseActivity
{

    public EditActivity()
    {
        context = this;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030002);
        textView = (TextView)findViewById(0x7f080004);
        editText = (DrawLine)findViewById(0x7f080005);
        cancelButton = (Button)findViewById(0x7f080006);
        sureButton = (Button)findViewById(0x7f080007);
        date = getIntent().getStringExtra("dateItem");
        content = getIntent().getStringExtra("contentItem");
        id = getIntent().getStringExtra("idItem");
        System.out.println((new StringBuilder("-----idItem-----id=")).append(id).toString());
        editText.setSelection(editText.length());
        editText.setText(content);
        editText.setSelection(content.length());
        textView.setText(date);
        dateNow = new Date();
        date = dateNow.getDate();
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
                    notepad.setid(id);
                    System.out.println((new StringBuilder("-----id-----id=")).append(id).toString());
                    changesqlite.update(sqlitedatabase, notepad);
                    finish();
                }
            }

//            final EditActivity this$0;
//
//            
//            {
//                this$0 = EditActivity.this;
//                super();
//            }
        }
);
        cancelButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                finish();
            }

//            final EditActivity this$0;
//
//            
//            {
//                this$0 = EditActivity.this;
//                super();
//            }
        }
);
    }

    private Button cancelButton;
    private String content;
    private Context context;
    private String date;
    private Date dateNow;
    private EditText editText;
    private String id;
    private Button sureButton;
    private TextView textView;




}
