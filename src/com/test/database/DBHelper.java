package com.test.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DBNAME = "IMAGE_LOCATION.db";
	public static final int version = 1;

	/**
	 * ���� ���д����ļ������ַ��� �����������������д���
	 * */
	/**
	 * ���ݿ��ṹ���� 1.����ID 2.�ļ������洢·�� image_path 3.���ļ���Ӧ����Ϣָ�� image_md5
	 * 4.�ļ�����ͨ��url���Եõ� image_name 5.�ļ���С image_size 6.�ļ�����ͼ·�� thumbNail_path
	 * <!-- 7.����ͼ���ļ�������ָ����ϢthumbNail_name / thumbNail_md5
	 */
	// IF NOT EXISTS 'IMAGE_PATH'
	public static final String CREATE_TABLE_IMAGE = "CREATE TABLE IF NOT EXISTS 'IMAGE_PATH' ("
			+ "IMAGE_PATH VARCHAR(120), " + "IMAGE_MD5 VARCHAR(64) primary key,"
			+ "IMAGE_NAME VARCHAR(70)," + "IMAGE_SIZE INTEGER" + ")";

	/**
	 * ���췽������ʼ�����ݿⲢ�Ҵ���һ����Ҫ�����д����ı�
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
