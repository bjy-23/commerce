package com.wondersgroup.commerce.teamwork.memorandum;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.model.Memo;

import java.util.ArrayList;

/**
 * Created by 1229 on 2016/3/21.
 */
public class DbHelper extends SQLiteOpenHelper {
    //数据库名
    private static final String TABLE_NAME = "commerce.db3";

    //数据库版本
    private static final int versionCode = 1;

    public static DbHelper create(){
        return new DbHelper(RootAppcation.getInstance(),TABLE_NAME,null,versionCode);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        builder.append("create table memo_table(");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,");
        builder.append("user_id VARCHAR(50) NOT NULL,");
        builder.append("memo_content VARCHAR(500) NOT NULL,");
        builder.append("memo_time VARCHAR(30),");
        builder.append("memo_time0 VARCHAR(30),");
        builder.append("remind_time VARCHAR(30)");
        builder.append(");");

        db.execSQL(builder.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //增加一条备忘
    public void insertMemoTable(Memo memo){
        SQLiteDatabase db = getWritableDatabase();
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("insert into memo_table (user_id,memo_content,memo_time,memo_time0,remind_time)"+
                "values (?,?,?,?,?);");

        db.execSQL(
                strBuf.toString(),
                new Object[]{memo.getUser_id(), memo.getMemo_content(), memo.getMemo_time(),memo.getMemo_time0(),
                        memo.getRemind_time()
                });
        db.close();
    }

    public ArrayList<Memo> query_memo(String user_id){
        Cursor cursor = getReadableDatabase().rawQuery(
                "select * from memo_table where user_id = ?", new String[]{user_id});
        
        ArrayList<Memo> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Memo memo = new Memo();
            memo.setUser_id(cursor.getString(1));
            memo.setMemo_content(cursor.getString(2));
            memo.setMemo_time(cursor.getString(3));
            memo.setMemo_time0(cursor.getString(4));
            memo.setRemind_time(cursor.getString(5));

            list.add(memo);
        }
        return list;
    }

    public void update_memo(Memo memo){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("update memo_table set memo_content =?,memo_time = ?,remind_time = ? where memo_time0 = ?", new String[]{memo.getMemo_content(),memo.getMemo_time(),memo.getRemind_time(),memo.getMemo_time0()});
        db.close();
    }

    public void delete_memo(String memo_time0){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from memo_table where memo_time0 = ?",new String[]{memo_time0});
        db.close();
    }
}

