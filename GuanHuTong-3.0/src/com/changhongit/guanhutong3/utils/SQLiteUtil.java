package com.changhongit.guanhutong3.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.changhongit.guanhutong3.utils.spdata.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteUtil {

	private DBHelper dbhelper;
	private AtomicInteger mOpenCounter = new AtomicInteger();
	SQLiteDatabase mDatabase;
	public SQLiteUtil(Context context) {
		// TODO Auto-generated constructor stub
		this.dbhelper = new DBHelper(context);
	}
	
    public synchronized SQLiteDatabase openDatabase() {
        if(mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
        	mDatabase = dbhelper.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        if(mOpenCounter.decrementAndGet() == 0) {
            // Closing database
            mDatabase.close();
        }
    }
    
    public void addUser(User user){   //添加数据，如果userid已存在则转到update方法
    	if(!ifExists(user.getUserId())){
        	openDatabase();
    		ContentValues cv = new ContentValues();
      		cv.put("userid", user.getUserId());
    		cv.put("imei", user.getImei());
    		cv.put("nickname", user.getNickName());
    		mDatabase.insert(DBHelper.TABLE, null, cv);
    		closeDatabase();
    	}
    	else updateUser(user);
    }
    
    public void updateUser(User user) {  //更新数据 
    	openDatabase();
        ContentValues cv = new ContentValues();  
        cv.put("imei", user.getImei());
        cv.put("nickname", user.getNickName());
        mDatabase.update(DBHelper.TABLE, cv, "userid = ?", new String[]{user.getUserId()});
        closeDatabase();
    }  
    
    public void deleteUser(String userid) {  //删除数据
    	openDatabase();
    	mDatabase.delete(DBHelper.TABLE, "userid = ?", new String[]{userid});
    	closeDatabase();
    }
    
    public List<User> query() {  //请求全部数据，未找到时返回空列表
    	openDatabase();
        ArrayList<User> users = new ArrayList<User>();  
        Cursor c = mDatabase.rawQuery("SELECT * FROM user", null);  
        while (c.moveToNext()) {  
            User user = new User();  
            user.setUserId(c.getString(c.getColumnIndex("userid")));  
            user.setImei(c.getString(c.getColumnIndex("imei")));  
            user.setNickName(c.getString(c.getColumnIndex("nickname")));  
            users.add(user);  
        }  
        c.close();  
        closeDatabase();
        return users;  
    }
    
    public User queryUser(String userid){//查询单条记录，未找到时返回为null
    	openDatabase();
        Cursor c = mDatabase.rawQuery("SELECT * FROM user", null); 
		if (c != null && c.getCount() > 0) {
	        while (c.moveToNext()) {
	        	if(c.getString(c.getColumnIndex("userid")).equals(userid)){
	            	User user = new User();
	                user.setUserId(c.getString(c.getColumnIndex("userid")));  
	                user.setImei(c.getString(c.getColumnIndex("imei")));  
	                user.setNickName(c.getString(c.getColumnIndex("nickname")));
	                closeDatabase();
	                return user;
	        	}
	        }
		}
        closeDatabase();
        return null;//未找到时
    }
    
    public boolean ifExists(String userid){//查询单条记录，未找到时返回为null
    	openDatabase();
        Cursor c = mDatabase.rawQuery("SELECT * FROM user", null); 
		if (c != null && c.getCount() > 0) {
	        while (c.moveToNext()) {
	        	if(c.getString(c.getColumnIndex("userid")).equals(userid)){
	                closeDatabase();
	                return true;
	        	}
	        }
		}
        closeDatabase();
        return false;//未找到时
    }
    
    public class DBHelper extends SQLiteOpenHelper {  

        private static final String DATABASE_NAME = "user.db";  
        public static final String TABLE = "user";
        private static final int DATABASE_VERSION = 1;  
        
        public DBHelper(Context context) {  
            //CursorFactory设置为null,使用默认值  
            super(context, DATABASE_NAME, null, DATABASE_VERSION);  
        }  
      
        //数据库第一次被创建时onCreate会被调用  
        @Override  
        public void onCreate(SQLiteDatabase db) {  
            db.execSQL("CREATE TABLE IF NOT EXISTS user (userid INTEGER PRIMARY KEY, imei TEXT, nickname TEXT);");  
        } 
      
        //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade  
        @Override  
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
            db.execSQL("ALTER TABLE user ADD COLUMN other STRING");  
        }  
    }
	
}
