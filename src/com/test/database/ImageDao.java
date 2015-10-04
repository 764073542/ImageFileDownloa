package com.test.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ImageDao {
	DBHelper dbHelper = null;

	public ImageDao(Context context) {
		super();
		this.dbHelper = new DBHelper(context);
	}

	public long Insert(Image image) {

		if (find(image.getImage_md5()) == false) { // 表示数据库中不存在该项数据
			SQLiteDatabase db = this.dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("IMAGE_PATH", image.getImage_path());
			values.put("IMAGE_NAME", image.getImage_name());
			values.put("IMAGE_MD5", image.getImage_md5());
			values.put("IMAGE_SIZE", image.getImage_size());
			long id = db.insert("IMAGE_PATH", null, values);
			db.close();
			return id; // -1表示插入异常
		} else {
			System.out.println("数据重复，不可插入");
			return -1;
		}

	}

	public boolean find(String md5) {
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		Cursor cursor = db.query("IMAGE_PATH", null, "IMAGE_MD5=?",
				new String[] { md5 }, null, null, null);
		boolean result = cursor.moveToNext();
		cursor.close();
		db.close();
		return result;
	}

	// public int update(String name, String newNumber) {
	// SQLiteDatabase db = this.dbHelper.getWritableDatabase();
	// ContentValues values = new ContentValues();
	// values.put("number", newNumber);
	// int number = db.update("person", values, "name=?",
	// new String[] { name });
	// db.close();
	// return number;
	// }

	public int delete(String md5) {
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();
		// db.execSQL("delete from person where name=?", new Object[] { name });
		int number = db.delete("IMAGE_PATH", "IMAGE_MD5=?",
				new String[] { md5 });
		db.close();
		return number;
	}

	public ArrayList<Image> findAll() {
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		ArrayList<Image> image = new ArrayList<Image>();
		Cursor cursor = db.query("IMAGE_PATH", null, null, null, null, null,
				null);

		while (cursor.moveToNext()) {
			String path = cursor.getString(cursor.getColumnIndex("IMAGE_PATH"));
			String md5 = cursor.getString(cursor.getColumnIndex("IMAGE_MD5"));
			String name = cursor.getString(cursor.getColumnIndex("IMAGE_NAME"));
			int size = cursor.getInt(cursor.getColumnIndex("IMAGE_SIZE"));
			Image img = new Image(path, name, md5, size);
			image.add(img);
		}
		cursor.close();
		db.close();
		return image;

	}

}
