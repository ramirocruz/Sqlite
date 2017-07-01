package com.example.ohanna.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ohanna on 29-Jun-17.
 */

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context,"mydb.db", null,1);
    }
    public final static String Table_name="Employees";
    public final static String Id="Id";
    public final static String Firstname="Firstname";
    public final static String Lastname="Lastname";
    public final static String Salary="Salary";
    public final static String create_query="create table "+Table_name+"("+Id+" INTEGER PRIMARY KEY,"+Firstname+" TEXT,"+Lastname+" TEXT,"+Salary+" REAL);";
    SQLiteDatabase mydb;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_query);
    }
    public long insert(int id,String Fname,String Lname,Double sal){
        mydb=this.getWritableDatabase();
        ContentValues values =new ContentValues();
        if(id>=0){
        values.put(Id,id);
        values.put(Firstname,Fname);
        values.put(Lastname,Lname);
        values.put(Salary,sal);}
        return mydb.insert(Table_name,null,values);
    }

    public long update(int id,String Fname,String Lname,Double sal){
        mydb=this.getWritableDatabase();
        ContentValues values =new ContentValues();
        if(id>=0){
        values.put(Id,id);
        values.put(Firstname,Fname);
        values.put(Lastname,Lname);
        values.put(Salary,sal);}
        return mydb.update(Table_name,values,Id+" = "+id,null);
    }
    public long delete(int id){
        mydb=this.getWritableDatabase();
        return mydb.delete(Table_name,Id+" = "+id,null);
    }
    public Cursor loadall(){
        mydb=this.getReadableDatabase();
        Cursor cursor=mydb.rawQuery("select * from "+Table_name+";",null);
        return cursor;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }
}
