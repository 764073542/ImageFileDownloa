package com.test.imagedownload;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageShowActivity extends Activity{
	private ImageView img_Show;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageshow);
		img_Show = (ImageView) findViewById(R.id.img_show);
		
		Intent intent = getIntent();
		String pathName = intent.getStringExtra("path");
		
		Bitmap bm = BitmapFactory.decodeFile(pathName);
		img_Show.setImageBitmap(bm);
	}
}
