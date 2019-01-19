package com.ovi.videocutter.dao;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ovi.videocutter.bean.AttachsBeen;
import com.ovi.videocutter.bean.RecordDetail;

import java.util.ArrayList;

public class RecordDao {
	private SQLHelper helper;
	public RecordDao(Context context){
		helper=SQLHelper.getInstance(context);
	}
	public void add(RecordDetail bean){
		Gson gson=new Gson();
		SQLiteDatabase db = helper.getReadableDatabase();
		db.execSQL("insert into records  (name,path,format,tips,flags) values (?,?,?,?,?)",new Object[]{bean.getName(),bean.getPath(),bean.getFormat(),gson.toJson(bean.getMarks()),gson.toJson(bean.getFlags())});
		db.close();
	}
	/**
	 * Determine whether a video is recorded by this app, and if yes, return the information of the detected video.
	 * @param bean
	 * @return
	 */
	public AttachsBeen isExist(AttachsBeen bean){
		boolean flag=false;
		Gson gson=null;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select flags,tips from records where path =?", new String[]{bean.getAchsPath()});
		
		while(cursor.moveToNext()){
			if(gson==null){
				gson=new Gson();
			}
			flag=true;
			String flags = cursor.getString(cursor.getColumnIndex("flags"));
			String tips=cursor.getString(cursor.getColumnIndex("tips"));
			ArrayList<Integer> flagList=gson.fromJson(flags, new TypeToken<ArrayList<Integer>>(){}.getType());
			ArrayList<Integer> tipList=gson.fromJson(tips, new TypeToken<ArrayList<Integer>>(){}.getType());
			bean.setFlags(flagList);
			bean.setTips(tipList);
		}
		return bean;
	}

}
