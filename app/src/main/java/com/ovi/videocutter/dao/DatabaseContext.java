package com.ovi.videocutter.dao;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import java.io.File;
import java.io.IOException;

public class DatabaseContext extends ContextWrapper {

	public DatabaseContext(Context base) {
		super(base);
		// TODO Auto-generated constructor stub
	}

    /**
     * Get the database path, if not, create an object
     * @param name
     * @return
     */
    @Override
    public File getDatabasePath(String name) {
        boolean sdExist = android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment.getExternalStorageState());
        if(!sdExist){
            return null;
        } 
        else{
            String dbDir=android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
            dbDir += "/database";
            String dbPath = dbDir+"/"+name;

            File dirFile = new File(dbDir);
            if(!dirFile.exists())
                dirFile.mkdirs();
            

            boolean isFileCreateSuccess = false; 

            File dbFile = new File(dbPath);
            if(!dbFile.exists()){
                try {                    
                    isFileCreateSuccess = dbFile.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else     
                isFileCreateSuccess = true;

            if(isFileCreateSuccess)
                return dbFile;
            else 
                return null;
        }
    }
 
    /**
     * Open or create DB method used in android 2.3 and below versions
     * 
     * @param    name
     * @param    mode
     * @param    factory
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, 
            CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
        return result;
    }
    
    /**
     * Open or create DB method used in android 4.0 and above versions
     * 
     * @see ContextWrapper#openOrCreateDatabase(String, int,
     *              CursorFactory,
     *              DatabaseErrorHandler)
     * @param    name
     * @param    mode
     * @param    factory
     * @param     errorHandler
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory,
            DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
        return result;
    }

}
