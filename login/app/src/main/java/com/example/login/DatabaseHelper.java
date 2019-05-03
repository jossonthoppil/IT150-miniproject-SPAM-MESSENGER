package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "registercr";
    public static final String TABLE_NAME2 = "registerstudent";
    public static final String TABLE_NAME3 = "messages";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME2 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT,password TEXT,rollno TEXT,branch TEXT,batch TEXT,sports TEXT,music TEXT,dance TEXT,club TEXT,debate TEXT,literature TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT,password TEXT,rollno TEXT,branch TEXT,batch TEXT,sports TEXT,music TEXT,dance TEXT,club TEXT,debate TEXT,literature TEXT,post TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME3 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, sender TEXT,message TEXT,grp TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password, String rollno, String branch, String batch,String sports,String music,String dance,String club,String debate,String literature) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);
        contentValues.put("rollno", rollno);
        contentValues.put("branch", branch);
        contentValues.put("batch", batch);
        contentValues.put("sports",sports);
        contentValues.put("music",music);
        contentValues.put("dance",dance);
        contentValues.put("club",club);
        contentValues.put("debate",debate);
        contentValues.put("literature",literature);
        long res = db.insert(TABLE_NAME2, null, contentValues);
        db.close();
        return res;
    }

    public long addMsg(String sender, String message, String grp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sender", sender);
        contentValues.put("message", message);
        contentValues.put("grp", grp);
        long res = db.insert(TABLE_NAME3, null, contentValues);
        db.close();
        return res;
    }

    public long addcr(String user, String password, String rollno, String branch, String batch,String sports,String music,String dance,String club,String debate,String literature,String post) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("username", user);
        content.put("password", password);
        content.put("rollno", rollno);
        content.put("branch", branch);
        content.put("batch", batch);
        content.put("post",post);
        content.put("sports", sports);
        content.put("music", music);
        content.put("dance", dance);
        content.put("club", club);
        content.put("debate",debate);
        content.put("literature",literature);
        //content.put("sports",sports);
        //content.put("music",music);
        //content.put("dance",dance);
        //content.put("club",club);
        long res = db.insert(TABLE_NAME, null, content);
        return res;
    }

    public boolean checkUser(String username, String password) {
        String[] columns = {COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + "and " + COL_3 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME2, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        if (count > 0) {
            return true;
        } else
            return false;
    }

    public boolean checkcr(String username, String password) {
        String[] columns = {COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + "and " + COL_3 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        if (count > 0) {
            return true;
        } else
            return false;
    }

    public long checkUserExists(String username) {
        int count = 0;
        String[] columns = {COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(TABLE_NAME2, columns, selection, selectionArgs, null, null, null);
        count = cursor.getCount();
        cursor.close();
        return count;
    }

    public long checkcrExists(String username) {
        int count = 0;
        String[] columns = {COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        count = cursor.getCount();
        cursor.close();
        return count;
    }

    public Cursor getgroups(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE username = ?",new String[] {username});
        return data;
    }
    public Cursor getgroupscr(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE username = ?",new String[] {username});
        return data;
    }
    public Cursor getmessages(String groupname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME3 + " WHERE grp = ?",new String[] {groupname});
        return data;
    }
}
