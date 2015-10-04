package com.test.dataset;

import java.util.ArrayList;
import java.util.List;

public class NetImageSet {
	private static NetImageSet imageSet = new NetImageSet();
	private List<NetImage> list = new ArrayList<NetImage>();
	private NetImageSet(){
		
	}
	/**
	 * 单利,保证NetImageSet 对象唯一，从而保证外部都是操作的List<NetImage>这一个集合
	 */
	public static NetImageSet getInstance() {
		return imageSet;
	}

	/**
	 * 增
	 * 
	 * @param image
	 */
	public void addImage(NetImage image) {
		list.add(image);
	}

	/**
	 * 删
	 */
	public void deleteImage() {
		list.remove(0);
	}

	/**
	 * 查
	 * 
	 * @return
	 */
	public NetImage getImage() {
		return list.get(0);
	}

	/**
	 * 返回长度
	 * 
	 * @return
	 */
	public int getLength() {
		return list.size();
	}
}
