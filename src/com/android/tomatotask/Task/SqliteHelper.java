// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.tomatotask.Task;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper
{

    public SqliteHelper(Context context, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i)
    {
        super(context, INFONAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL((new StringBuilder("create table ")).append(NAME).append("(id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,date TEXT,content TEXT)").toString());
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
    }

    private static String INFONAME = "notepad.db";
    private static String NAME = " table_notepad";
    private static int VERSION = 1;

}
