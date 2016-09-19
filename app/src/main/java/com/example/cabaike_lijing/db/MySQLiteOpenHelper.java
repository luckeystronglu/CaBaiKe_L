package com.example.cabaike_lijing.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	private static final int VERSION = 2;
	private static final String SQL_NAME = "chabaikelj.db";
	private static final String CREATE_TABLE_COLLECTION = "create table collection(_id varchar,title varchar,source varchar,wap_thumb varchar,create_time varchar,nickname)";
	private static final String CREATE_TABLE_HISTORY = "create table history(_id varchar,title varchar,source varchar,wap_thumb varchar,create_time varchar,nickname)";
	private static final String CREATE_TABLE_CACHE = "create table cache(_id varchar,title varchar,source varchar,wap_thumb varchar,create_time varchar,nickname)";
	private static final String CREATE_TABLE_SUGGESTION = "create table suggestion(_id integer,title varchar,content varchar)";
	
	public MySQLiteOpenHelper(Context context) {
		super(context, SQL_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_COLLECTION);
		db.execSQL(CREATE_TABLE_HISTORY);
		db.execSQL(CREATE_TABLE_SUGGESTION);
		db.execSQL(CREATE_TABLE_CACHE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
