package com.test.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DBNAME = "IMAGE_LOCATION.db";
	public static final int version = 1;

	/**
	 * 创建 运行次数的计数的字符串 这个表存放了软件的运行次数
	 * */
	/**
	 * 数据库表结构设置 1.自增ID 2.文件本机存储路径 image_path 3.给文件对应的信息指纹 image_md5
	 * 4.文件名，通过url可以得到 image_name 5.文件大小 image_size 6.文件缩略图路径 thumbNail_path
	 * <!-- 7.缩略图的文件名或者指纹信息thumbNail_name / thumbNail_md5
	 */
	// IF NOT EXISTS 'IMAGE_PATH'
	public static final String CREATE_TABLE_IMAGE = "CREATE TABLE IF NOT EXISTS 'IMAGE_PATH' ("
			+ "IMAGE_PATH VARCHAR(120), " + "IMAGE_MD5 VARCHAR(64) primary key,"
			+ "IMAGE_NAME VARCHAR(70)," + "IMAGE_SIZE INTEGER" + ")";

	/**
	 * 构造方法，初始化数据库并且创建一个需要的运行次数的表
	 * 
	 **/

	public DBHelper(Context context) {
		super(context, DBNAME, null, DBHelper.version);

	}

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_IMAGE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
