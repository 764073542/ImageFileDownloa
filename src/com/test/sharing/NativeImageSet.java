package com.test.sharing;

import java.util.ArrayList;
import java.util.List;

import android.os.Environment;

import com.test.database.Image;

public class NativeImageSet {
	public static List<Image> list = new ArrayList<Image>();
	public static String FilePath = Environment.getExternalStorageDirectory()+"/Download/";
	
}
