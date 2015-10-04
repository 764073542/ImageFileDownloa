package com.test.dataset;

import java.util.ArrayList;
import java.util.List;

public class NetImageSet {
	private static NetImageSet imageSet = new NetImageSet();
	private List<NetImage> list = new ArrayList<NetImage>();
	private NetImageSet(){
		
	}
	/**
	 * ����,��֤NetImageSet ����Ψһ���Ӷ���֤�ⲿ���ǲ�����List<NetImage>��һ������
	 */
	public static NetImageSet getInstance() {
		return imageSet;
	}

	/**
	 * ��
	 * 
	 * @param image
	 */
	public void addImage(NetImage image) {
		list.add(image);
	}

	/**
	 * ɾ
	 */
	public void deleteImage() {
		list.remove(0);
	}

	/**
	 * ��
	 * 
	 * @return
	 */
	public NetImage getImage() {
		return list.get(0);
	}

	/**
	 * ���س���
	 * 
	 * @return
	 */
	public int getLength() {
		return list.size();
	}
}
