// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask.Task;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.view.*;
import android.widget.*;
import com.android.tomatotask.TaskFragment;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.android.tomatotask.Task:
//            TextViewLine, SqliteHelper, ChangeSqlite, Notepad, 
//            Date, DrawLine

public class NotepadAdapter extends BaseAdapter
{
    class DeleteButtonListener
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            builder.setTitle("\u786E\u5B9A\u5220\u9664\uFF1F");
            builder.setPositiveButton("\u5220\u9664", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    android.database.sqlite.SQLiteDatabase sqlitedatabase = (new SqliteHelper(context, null, null, 0)).getWritableDatabase();
                    ChangeSqlite changesqlite = new ChangeSqlite();
                    Notepad notepad = new Notepad();
                    notepad.setid((String)((Map)list.get(position)).get("idItem"));
                    changesqlite.delete(sqlitedatabase, notepad);
                    taskFragment.showUpdate();
                }

//                final DeleteButtonListener this$1;
//
//                
//                {
//                    this$1 = DeleteButtonListener.this;
//                    super();
//                }
            }
);
            builder.setNegativeButton("\u53D6\u6D88", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

//                final DeleteButtonListener this$1;
//
//                
//                {
//                    this$1 = DeleteButtonListener.this;
//                    super();
//                }
            }
);
            builder.create();
            builder.show();
        }

        private int position;
//        final NotepadAdapter this$0;



        public DeleteButtonListener(int i)
        {
//            this$0 = NotepadAdapter.this;
            super();
            position = i;
        }
    }

    class SetEdit
    {

        public Button cancelButton;
        public String content;
        public String date;
        public Date dateNow;
        public EditText editText;
        public String id;
        public RelativeLayout layout;
        public Button sureButton;
        public TextView textView;
//        final NotepadAdapter this$0;

//        SetEdit()
//        {
//            this$0 = NotepadAdapter.this;
//            super();
//        }
    }

    class SetShow
    {

        public TextViewLine cContentView;
        public TextView cDateView;
        public TextView contentView;
        public TextView dateView;
        public Button showButtonDelete;
        public Button showButtonWrite;
        public Button styleButtonDelete;
        public Button styleButtonWrite;
//        final NotepadAdapter this$0;
//
//        SetShow()
//        {
//            this$0 = NotepadAdapter.this;
//            super();
//        }
    }

    class WriteButtonListener
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            setEdit.layout = (RelativeLayout)taskFragment.getActivity().getLayoutInflater().inflate(0x7f030002, null);
            setEdit.textView = (TextView)setEdit.layout.findViewById(0x7f080004);
            setEdit.editText = (DrawLine)setEdit.layout.findViewById(0x7f080005);
            setEdit.cancelButton = (Button)setEdit.layout.findViewById(0x7f080006);
            setEdit.sureButton = (Button)setEdit.layout.findViewById(0x7f080007);
            setEdit.date = (String)((Map)list.get(position)).get("dateItem");
            setEdit.content = (String)((Map)list.get(position)).get("contentItem");
            setEdit.id = (String)((Map)list.get(position)).get("idItem");
            setEdit.editText.setSelection(setEdit.editText.length());
            setEdit.editText.setText(setEdit.content);
            setEdit.editText.setSelection(setEdit.content.length());
            setEdit.textView.setText(setEdit.date);
            setEdit.dateNow = new Date();
            setEdit.date = setEdit.dateNow.getDate();
            setEdit.textView.setText(setEdit.date);
            (new android.app.AlertDialog.Builder(activity)).setView(setEdit.layout).setPositiveButton("\u4FDD\u5B58", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    android.database.sqlite.SQLiteDatabase sqlitedatabase = (new SqliteHelper(taskFragment.getActivity(), null, null, 0)).getWritableDatabase();
                    Notepad notepad = new Notepad();
                    ChangeSqlite changesqlite = new ChangeSqlite();
                    String s = setEdit.editText.getText().toString();
                    if(s.equals(""))
                    {
                        Toast.makeText(taskFragment.getActivity(), "\u5185\u5BB9\u4E3A\u7A7A", 0).show();
                    } else
                    {
                        String s1;
                        if(s.length() > 11)
                            s1 = (new StringBuilder(" ")).append(s.substring(0, 11)).toString();
                        else
                            s1 = s;
                        notepad.setContent(s);
                        System.out.println((new StringBuilder("-----strContent-----strContent=")).append(s).toString());
                        notepad.setTitle(s1);
                        notepad.setdata(setEdit.date);
                        notepad.setid(setEdit.id);
                        System.out.println((new StringBuilder("-----id-----id=")).append(setEdit.id).toString());
                        changesqlite.update(sqlitedatabase, notepad);
                        taskFragment.showUpdate();
                    }
                }

//                final WriteButtonListener this$1;
//
//                
//                {
//                    this$1 = WriteButtonListener.this;
//                    super();
//                }
            }
).setNegativeButton("\u53D6\u6D88", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                }

//                final WriteButtonListener this$1;
//
//                
//                {
//                    this$1 = WriteButtonListener.this;
//                    super();
//                }
            }
).show();
        }

        private int position;
        SetEdit setEdit;
//        final NotepadAdapter this$0;


        public WriteButtonListener(int i)
        {
//            this$0 = NotepadAdapter.this;
//            super();
            setEdit = new SetEdit();
            position = i;
        }
    }


    public NotepadAdapter(Activity activity1, TaskFragment taskfragment, ArrayList arraylist)
    {
        context = activity1;
        activity = activity1;
        taskFragment = taskfragment;
        list = arraylist;
        inflater = LayoutInflater.from(context);
    }

    public int getCount()
    {
        return list.size();
    }

    public Object getItem(int i)
    {
        return list.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        SetShow setshow = new SetShow();
        View view1;
        if(!((Boolean)((Map)list.get(i)).get("EXPANDED")).booleanValue())
        {
            view1 = inflater.inflate(0x7f030003, viewgroup, false);
            setshow.contentView = (TextView)view1.findViewById(0x7f080008);
            setshow.dateView = (TextView)view1.findViewById(0x7f080009);
            String s2 = (String)((Map)list.get(i)).get("titleItem");
            String s3 = (String)((Map)list.get(i)).get("dateItem");
            setshow.contentView.setText((new StringBuilder("   ")).append(s2).toString());
            setshow.dateView.setText(s3);
            setshow.showButtonWrite = (Button)view1.findViewById(0x7f08000b);
            setshow.showButtonDelete = (Button)view1.findViewById(0x7f08000a);
            setshow.showButtonWrite.setOnClickListener(new WriteButtonListener(i));
            setshow.showButtonDelete.setOnClickListener(new DeleteButtonListener(i));
        } else
        {
            view1 = inflater.inflate(0x7f030004, viewgroup, false);
            setshow.cContentView = (TextViewLine)view1.findViewById(0x7f08000d);
            setshow.cDateView = (TextView)view1.findViewById(0x7f08000c);
            String s = (String)((Map)list.get(i)).get("contentItem");
            String s1 = (String)((Map)list.get(i)).get("dateItem");
            setshow.cContentView.setText((new StringBuilder()).append(s).toString());
            setshow.cDateView.setText(s1);
            setshow.styleButtonWrite = (Button)view1.findViewById(0x7f08000e);
            setshow.styleButtonDelete = (Button)view1.findViewById(0x7f08000f);
            setshow.styleButtonWrite.setOnClickListener(new WriteButtonListener(i));
            setshow.styleButtonDelete.setOnClickListener(new DeleteButtonListener(i));
        }
        return view1;
    }

    public Activity activity;
    public Context context;
    public LayoutInflater inflater;
    public ArrayList list;
    public TaskFragment taskFragment;
}
