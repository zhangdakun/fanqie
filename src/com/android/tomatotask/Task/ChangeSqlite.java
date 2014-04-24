// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask.Task;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

// Referenced classes of package com.android.tomatotask.Task:
//            Notepad

public class ChangeSqlite
{

    public ChangeSqlite()
    {
    }

    public long add(SQLiteDatabase sqlitedatabase, Notepad notepad)
    {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("title", notepad.getTitle());
        contentvalues.put("date", notepad.getdata());
        contentvalues.put("content", notepad.getContent());
        long l = sqlitedatabase.insert(table, null, contentvalues);
        sqlitedatabase.close();
        return l;
    }

    public void delete(SQLiteDatabase sqlitedatabase, Notepad notepad)
    {
        sqlitedatabase.delete(table, (new StringBuilder("id=")).append(notepad.getid()).toString(), null);
        sqlitedatabase.close();
    }

    public ArrayList query(SQLiteDatabase sqlitedatabase)
    {
        ArrayList arraylist = new ArrayList();
        String s = table;
        String as[] = new String[4];
        as[0] = "id";
        as[1] = "title";
        as[2] = "content";
        as[3] = "date";
        Cursor cursor = sqlitedatabase.query(s, as, null, null, null, null, null);
        do
        {
            if(!cursor.moveToNext())
            {
                sqlitedatabase.close();
                return arraylist;
            }
            Notepad notepad = new Notepad();
            notepad.setid(cursor.getString(cursor.getColumnIndex("id")));
            notepad.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            notepad.setContent(cursor.getString(cursor.getColumnIndex("content")));
            notepad.setdata(cursor.getString(cursor.getColumnIndex("date")));
            arraylist.add(notepad);
        } while(true);
    }

    public void update(SQLiteDatabase sqlitedatabase, Notepad notepad)
    {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("title", notepad.getTitle());
        contentvalues.put("content", notepad.getContent());
        contentvalues.put("date", notepad.getdata());
        sqlitedatabase.update(table, contentvalues, (new StringBuilder("id=")).append(notepad.getid()).toString(), null);
        sqlitedatabase.close();
    }

    public static String table = "table_notepad";

}
