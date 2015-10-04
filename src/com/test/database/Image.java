package com.test.database;

/**
 * 数据库表结构设置 1.自增ID 2.文件本机存储路径 image_path 3.给文件对应的信息指纹 image_md5 4.文件名，通过url可以得到
 * image_name 5.文件大小 image_size 6.文件缩略图路径 thumbNail_path 7.缩略图的文件名或者指纹信息
 * thumbNail_name / thumbNail_md5
 */
public class Image {
	private String image_path;
	private String image_name;

	private String image_md5;
	private int image_size;

	/**
	 * 无参构造方法
	 */
	public Image() {
	}

	public String getImage_name() {
		return image_name;
	}

	/**
	 * 带参数构造方法
	 * 
	 * @param image_path
	 * @param image_name
	 * @param image_md5
	 * @param image_size
	 */
	public Image(String image_path, String image_name, String image_md5,
			int image_size) {
		super();
		this.image_path = image_path;
		this.image_name = image_name;
		this.image_md5 = image_md5;
		this.image_size = image_size;

	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getImage_md5() {
		return image_md5;
	}

	public void setImage_md5(String image_md5) {
		this.image_md5 = image_md5;
	}

	public int getImage_size() {
		return image_size;
	}

	public void setImage_size(int image_size) {
		this.image_size = image_size;
	}

}
