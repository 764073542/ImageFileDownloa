package com.test.imagedownload;

/*
 * 1.从数据库中读取图片的路径
 * 2.通过listview显示图片对应的缩略图 / 文件名 / md5值
 * 3.点击下载按钮将下载图片，并插入一条记录进数据库
 * 4.下载完成后，将下载的图片缩略图显示到界面上
 * 5.添加图片 增 删 操作，主要是操作数据库 
 * 6.增加listview的排序功能
 * 7.添加刷新按钮 ！！！
 * 8.数据库插入异常！！！！ 重复项
 * 9.通过表格布局，在程序中实现动态调整
 * 
 */
import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.test.database.Image;
import com.test.database.ImageDao;
import com.test.dataset.NetImage;
import com.test.dataset.NetImageSet;
import com.test.sharing.NativeImageSet;
import com.test.utils.DownloadImage;
import com.test.utils.GetThumbNail;

/*******************************
 * 1.创建一个集合用于保存图片的url 2.创建一个数据库用于存储图片的路径、名称、指纹信息 3.提供一个获取图片md5的工具类（传递一个图片路径）
 * 4.提供一个下载文件的工具类（传递一个url） 5.获取文件缩略图工具类 6.各种操作的权限……
 *******************************/
public class MainActivity extends Activity {
	private EditText edt_url;
	private Context context;
	private LinearLayout line_Layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edt_url = (EditText) findViewById(R.id.edt_url);
		line_Layout = (LinearLayout) findViewById(R.id.line_layout);
		CheckPathExist(); // 有问题，需要再改改
		context = this.getApplicationContext();

	}

	/**
	 * 检查路径是否存在，不存在就创建
	 */
	private void CheckPathExist() {
		try {
			File file = new File(NativeImageSet.FilePath);
			if (!file.exists()) {
				file.mkdir();
			}
		} catch (Exception e) {
			Toast.makeText(this, "目录无法创建", 0).show();
			e.printStackTrace();
		}
	}

	/**
	 * 下载按钮点击事件
	 * 
	 * @param view
	 */
	public void DownloadImages(View view) {
		String url = edt_url.getText().toString().trim();
		if (!url.equals("")) {
			NetImageSet.getInstance().addImage(new NetImage(url));
			// Toast.makeText(this, "开始下载", 0).show();
			DownloadImage.downloadImage(NetImageSet.getInstance().getImage()
					.getUrl(), context);
			NetImageSet.getInstance().deleteImage();
		}
	}

	public void DownloadTest(View view) {

		NetImageSet
				.getInstance()
				.addImage(
						new NetImage(
								"http://img4.imgtn.bdimg.com/it/u=434281914,1810736344&fm=21&gp=0.jpg"));
		NetImageSet
				.getInstance()
				.addImage(
						new NetImage(
								"http://www.dianchuifeng.net/img/aHR0cDovL3BpYzEyLm5pcGljLmNvbS8yMDExMDIxNi80MDUwMjM4XzE3MTAzMzY1NTM3NV8yLmpwZw==.jpg"));

		NetImageSet
				.getInstance()
				.addImage(
						new NetImage(
								"http://pic11.nipic.com/20101216/2928847_193903062822_2.jpg"));

		NetImageSet
				.getInstance()
				.addImage(
						new NetImage(
								"http://img.61gequ.com/allimg/2011-4/201142614314278502.jpg"));

		NetImageSet.getInstance().addImage(
				new NetImage("http://img3.3lian.com/2013/s1/20/d/57.jpg"));
		int length = NetImageSet.getInstance().getLength();
		for (int i = 0; i < length; i++) {
			DownloadImage.downloadImage(NetImageSet.getInstance().getImage()
					.getUrl(), context);
			NetImageSet.getInstance().deleteImage();
		}
	}

	/**
	 * 跳转显示原图
	 */
	public void intent() {
		Intent intent = new Intent();
		intent.putExtra(
				"path",
				"/storage/emulated/0/Download/Image/5bafa40f4bfbfbed81fec7cb7df0f736aec31fda.jpg");
		intent.setClass(MainActivity.this, ImageShowActivity.class);
		startActivity(intent);
	}

	/**
	 * 刷新按钮点击事件
	 * 
	 * @param view
	 */
	public void Refresh(View view) {
		line_Layout.removeAllViews();
		ArrayList<Image> imageList = ReadDataFromSQLite();
		for (int i = 0; i < imageList.size(); i++) {
			ImageView img = new ImageView(context);

			try {
				// 可能人图片文件被删除了，而数据库还没有更新
				Bitmap bm = GetThumbNail.getImageThumbnail(imageList.get(i)
						.getImage_path(), 240, 240);
				img.setImageBitmap(bm);
				line_Layout.addView(img);
			} catch (Exception e) {
				int num = new ImageDao(context).delete(imageList.get(i)
						.getImage_md5());
				if (num != -1) {
					System.out.println("删除完成");
				}
				imageList.remove(i);
			}
		}

	}

	/**
	 * 将数据库中的元素读入集合
	 */
	private ArrayList<Image> ReadDataFromSQLite() {
		ArrayList<Image> imageList = null;
		ImageDao imageDao = new ImageDao(context);
		imageList = imageDao.findAll();
		return imageList;
	}
}
