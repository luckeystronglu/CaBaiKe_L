package com.qf.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TeaSQLiteOpenHelper extends SQLiteOpenHelper {
	private static final int VERSION = 0X001;
	private static final String SQL_NAME = "teabk.db";
	private static final String CREATE_TABLE_COLLECTION = "create table collections(_id integer primary key,id,title,create_time,nickname,wap_thumb)";


	public TeaSQLiteOpenHelper(Context context) {
		super(context, SQL_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_COLLECTION);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
