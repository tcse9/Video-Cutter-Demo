package com.ovi.videocutter.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "caibian.db";
	public static final int VERSION = 1;
	private static final String SQL_CREATE_RECORD="create table records (_id integer primary key autoincrement,name text,format text,path text,tips text,flags text)";
	/**
	 * Create a recorded video with only a section of the video path of the database table built statement
	 */
	private static final String SQL_CREATE_SINGLE="create table singles (_id integer primary key autoincrement,path text)";

	private Context context;
	private static SQLHelper sSQLHelper=null;
	public SQLHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		this.context = context;
	}
	public static SQLHelper getInstance(Context context){
		if (sSQLHelper == null)
			sSQLHelper = new SQLHelper(context);
		return sSQLHelper;
		
	}

	public Context getContext(){
		return context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(SQL_CREATE_RECORD);
		db.execSQL(SQL_CREATE_SINGLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}

}
