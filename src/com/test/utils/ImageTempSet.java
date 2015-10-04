package com.test.utils;

import java.util.ArrayList;
import java.util.List;

import com.test.database.Image;

public class ImageTempSet {
	private static ImageTempSet imageSet = new ImageTempSet();
	private static List<Image> list = new ArrayList<Image>(); 
	private ImageTempSet(){
		
	}
	public static ImageTempSet getInstance(){
		return imageSet;
	}	
	public void addImage(Image image){
		list.add(image); //����ӵ�Ĭ�Ϸ��������
	}
	public Image getImage(){
		return list.get(0); //������ǰ����Ǹ�Ԫ��
	}
	public void deleteImage(){
		list.remove(0); //ɾ����ǰ���Ԫ��
	}
	public int getLength(){
		return list.size();
	}
	public String getAll(){
		return list.get(0).getImage_path()+" "+list.get(1).getImage_path()+" "+list.get(2).getImage_path();
	}
	
}
