package com.test.netimageview;

/*
 * 1.�����ݿ��ж�ȡͼƬ��·��
 * 2.ͨ��listview��ʾͼƬ��Ӧ������ͼ / �ļ��� / md5ֵ
 * 3.������ذ�ť������ͼƬ��������һ����¼�����ݿ�
 * 4.������ɺ󣬽����ص�ͼƬ����ͼ��ʾ��������
 * 5.���ͼƬ �� ɾ ��������Ҫ�ǲ������ݿ� 
 * 6.����listview��������
 * 7.���ˢ�°�ť ������
 * 8.���ݿ�����쳣�������� �ظ���
 * 9.ͨ����񲼾֣��ڳ�����ʵ�ֶ�̬����
 * 
 */
import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.test.database.Image;
import com.test.database.ImageDao;
import com.test.dataset.NetImage;
import com.test.dataset.NetImageSet;
import com.test.sharing.NativeImageSet;
import com.test.utils.DownloadImage;
import com.test.utils.StatueCode;

/*
 * ��ǰ����
 * 1.���ˢ�°�ť���������ݿ�̫Ƶ��
 * 2.ż�������
 * 3.������ɺ����ز�����
 * 
 */
/*******************************
 * 1.����һ���������ڱ���ͼƬ��url 2.����һ�����ݿ����ڴ洢ͼƬ��·�������ơ�ָ����Ϣ 3.�ṩһ����ȡͼƬmd5�Ĺ����ࣨ����һ��ͼƬ·����
 * 4.�ṩһ�������ļ��Ĺ����ࣨ����һ��url�� 5.��ȡ�ļ�����ͼ������ 6.���ֲ�����Ȩ�ޡ���
 *******************************/
public class MainActivity extends Activity {
	private EditText edt_url;
	private Context context;
	private ListView listview;
	private MyAdapter adapter;
	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				break;
			case StatueCode.STATUE_OK:
				Toast.makeText(MainActivity.this, "�������", 0).show();
				RefreshListView();
				break;
			case StatueCode.STATUE_ERROR:
				Toast.makeText(MainActivity.this, "����ʧ��", 0).show();
				break;
			case StatueCode.STATUE_REPEAT:
				Toast.makeText(MainActivity.this, "�ļ��Ѵ���", 0).show();
				break;
			case StatueCode.STATUE_EXCEPT:
				Toast.makeText(MainActivity.this, "����ʧ�ܣ�״̬��Ϊ" + msg.obj, 0)
						.show();
				break;

			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edt_url = (EditText) findViewById(R.id.edt_url);

		listview = (ListView) findViewById(R.id.list_show_image);
		CheckPathExist(); // �����⣬��Ҫ�ٸĸ�
		context = this.getApplicationContext();
		try {
			adapter = new MyAdapter(this, ReadDataFromSQLite());
			listview.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				Image image = null;
				image = (Image) adapter.getItem(position);
				Intent intent = new Intent();
				intent.putExtra("path", image.getImage_path());
				intent.setClass(MainActivity.this, ImageShowActivity.class);
				startActivity(intent);
			}

		});

	}

	/**
	 * ���·���Ƿ���ڣ������ھʹ���
	 */
	private void CheckPathExist() {
		try {
			File file = new File(NativeImageSet.FilePath);
			if (!file.exists()) {
				file.mkdir();
			}
		} catch (Exception e) {
			Toast.makeText(this, "Ŀ¼�޷�����", 0).show();
			e.printStackTrace();
		}
	}

	/**
	 * ���ذ�ť����¼�
	 * 
	 * @param view
	 */
	public void DownloadImages(View view) {
		String url = edt_url.getText().toString().trim();
		if (!url.equals("")) {
			NetImageSet.getInstance().addImage(new NetImage(url));
			// Toast.makeText(this, "��ʼ����", 0).show();
			DownloadImage.downloadImage(NetImageSet.getInstance().getImage()
					.getUrl(), context, handler);
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
					.getUrl(), context, handler);
			NetImageSet.getInstance().deleteImage();
		}
	}

	/**
	 * ��ת��ʾԭͼ
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
	 * ˢ�°�ť����¼�
	 * 
	 * @param view
	 */
	public void Refresh(View view) {

		try {
			adapter = new MyAdapter(this, ReadDataFromSQLite());
			listview.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void RefreshListView() {

		try {
			adapter = new MyAdapter(this, ReadDataFromSQLite());
			listview.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * �����ݿ��е�Ԫ�ض��뼯��
	 */
	private ArrayList<Image> ReadDataFromSQLite() {
		ArrayList<Image> imageList = null;
		ImageDao imageDao = new ImageDao(context);
		imageList = imageDao.findAll();
		return imageList;
	}

	private ArrayList<Image> check() {
		ArrayList<Image> imageList = ReadDataFromSQLite();
		for (Image image : imageList) {
			if (!new File(image.getImage_path()).exists()) {
				ImageDao imageDao = new ImageDao(context);
				imageDao.delete(image.getImage_md5());
				imageList.remove(image);
			}
		}
		return imageList;
	}
}
