package com.example.cabaike_lijing.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteUtils {
	Context context;
	SQLiteDatabase db;

	MySQLiteOpenHelper helper;

	public SQLiteUtils(Context context) {
		super();
		this.context = context;
		helper = new MySQLiteOpenHelper(context);
		db = helper.getWritableDatabase();
	}

	// 添加数据到收藏中
	public void addCollection(ContentValues values) {
		db.insert("collection", null, values);
	}

	// 查询收藏
	public Cursor queryCollection() {
		return db.query("collection", null, null, null, null, null, null);
	}

	// 删除某条收藏
	public void delOneCollection(String _id) {
		db.delete("collection", "_id=?", new String[] { _id });
	}

	// 删除所有的收藏
	public void delAllCollection() {
		db.delete("collection", null, null);
	}

	// 添加数据到历史记录中
	public void addHistory(ContentValues values) {
		db.insert("history", null, values);
	}

	// 查询历史记录
	public Cursor queryHistory() {
		return db.query("history", null, null, null, null, null, null);
	}

	// 删除某条历史记录
	public void delOneHistory(String _id) {
		db.delete("history", "_id=?", new String[] { _id });
	}

	// 清除所有的历史记录
	public void delAllHistory() {
		db.delete("history", null, null);
	}

	// 添加数据到反馈意见中
	public void addSuggestion(ContentValues values) {
		db.insert("suggestion", null, values);
	}

	// 添加离线缓存到数据库中
	public void addCache(ContentValues values) {
		db.insert("cache", null, values);
	}

	// 查询离线缓存
	public Cursor queryCache() {
		return db.query("cache", null, null, null, null, null, null);
	}

}
