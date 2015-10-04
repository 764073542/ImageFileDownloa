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
		list.add(image); //新添加的默认放在最后面
	}
	public Image getImage(){
		return list.get(0); //返回最前面的那个元素
	}
	public void deleteImage(){
		list.remove(0); //删除最前面的元素
	}
	public int getLength(){
		return list.size();
	}
	public String getAll(){
		return list.get(0).getImage_path()+" "+list.get(1).getImage_path()+" "+list.get(2).getImage_path();
	}
	
}
