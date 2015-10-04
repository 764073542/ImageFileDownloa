package com.test.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.util.Log;

import com.test.database.Image;
import com.test.database.ImageDao;
import com.test.sharing.NativeImageSet;

public class DownloadImage {
	/**
	 * 文件下载
	 * 
	 * @param url
	 */
	public static void downloadImage(final String path,final Context context) {
		/**
		 * 文件下载线程
		 */
		new Thread() {

			public void run() {
				int FileSize = 0;
				String FilePath;
				String FileName;
				String FileMd5;
				InputStream is = null;
				OutputStream os = null;
				File file = null;

				try {
					FileName = path.substring(path.lastIndexOf("/") + 1);
					FilePath = NativeImageSet.FilePath + FileName;

					file = new File(FilePath);					

					// 连接服务器获取一个文件，在本地创建一个和它相同大小的临时文件
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5000);
					int code = conn.getResponseCode();
					if (code == 200) {
						// 获取文件长度
						FileSize = conn.getContentLength();
						// 输入流
						is = conn.getInputStream();
						// 1K的数据缓冲
						byte[] bs = new byte[1024];
						// 读取到的数据长度
						int len;
						// 输出的文件流
						os = new FileOutputStream(file);
						// 开始读取
						while ((len = is.read(bs)) != -1) {
							os.write(bs, 0, len);
						}						
						FileMd5 = GetMd5.getMd5(FilePath);
						String newFileName =FileMd5 + FileName.substring(FileName.lastIndexOf("."));							
								
								
						FilePath = NativeImageSet.FilePath + newFileName;
								
						file.renameTo(new File(FilePath));				
						
						long id;
						try {
							ImageDao imageDao = new ImageDao(context);
							id = imageDao.Insert(new Image(FilePath, newFileName, FileMd5,
									FileSize));
							if(id == -1){
								Log.e("Image_SQL", "数据插入失败");
							}
						} catch (Exception e) {
							Log.e("SQL_Insert_ERROR",e.toString());
						}
						
					}
					else{
						Log.d("Image_Download_Code", code+"");
					}
				} catch (Exception ex) {
					Log.e("Image_Download_Error", ex.toString());
				} finally {
					// 完毕，关闭所有链接
					if (is != null) {
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (os != null) {
						try {
							os.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			};
		}.start();

	}
}
