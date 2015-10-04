package com.test.junit;

import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.widget.Toast;

import com.test.database.DBHelper;
import com.test.database.Image;
import com.test.database.ImageDao;

public class TestPersonDB extends AndroidTestCase {

	public void testCreatePersonDB() throws Exception {
		DBHelper db = new DBHelper(getContext());
		db.getWritableDatabase();
	}

	public void testAdd() {
		ImageDao pd = new ImageDao(getContext());
		pd.Insert(new Image("1","2","3",40));
		pd.Insert(new Image("1","2","3",80));
	}

//	public void testFind() {
//		ImageDao pd = new ImageDao(getContext());
//		boolean result = pd.find("weijie");
//		assertEquals(true, result);
//	}

//	public void testUpdate() {
//		PersonDao2 pd = new PersonDao2(getContext());
//		pd.update("weijie", "3213545");
//	}

//	public void testDelete() {
//		PersonDao2 pd = new PersonDao2(getContext());
//		pd.delete("weijie");
//	}

//	public void testFindAll() {
//		PersonDao2 pd = new PersonDao2(getContext());
//		ArrayList<Person> persons = pd.findAll();
//		for (Person person : persons) {
//			System.out.println(person);
//		}
//	}

	public void testTransaction() {
		DBHelper helper = new DBHelper(getContext());
		SQLiteDatabase db = helper.getWritableDatabase();
		db.beginTransaction();
		try {
			db.execSQL(
					"update person set account = account + 1000 where name=?",
					new Object[] { "tom" });
			int i = 1 / 0;
			db.execSQL(
					"update person set account = account - 1000 where name=?",
					new Object[] { "jerry" });
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}

	}
}
