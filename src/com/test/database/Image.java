package com.test.database;

/**
 * ���ݿ��ṹ���� 1.����ID 2.�ļ������洢·�� image_path 3.���ļ���Ӧ����Ϣָ�� image_md5 4.�ļ�����ͨ��url���Եõ�
 * image_name 5.�ļ���С image_size 6.�ļ�����ͼ·�� thumbNail_path 7.����ͼ���ļ�������ָ����Ϣ
 * thumbNail_name / thumbNail_md5
 */
public class Image {
	private String image_path;
	private String image_name;

	private String image_md5;
	private int image_size;

	/**
	 * �޲ι��췽��
	 */
	public Image() {
	}

	public String getImage_name() {
		return image_name;
	}

	/**
	 * ���������췽��
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
