package com.test.netimageview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.database.Image;
import com.test.utils.GetThumbNail;

public class MyAdapter extends BaseAdapter{
	ArrayList<Image> list;
	LayoutInflater inflater;

	
	public MyAdapter(Context context, ArrayList<Image> list) {
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}

	public void onDateChange(ArrayList<Image> list) {
		this.list = list;
		this.notifyDataSetChanged();
	}
	
	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Image image = list.get(position);
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list, null);
			holder.path = (ImageView) convertView
					.findViewById(R.id.img_path);
			holder.name = (TextView) convertView
					.findViewById(R.id.edt_name);
			holder.size = (TextView) convertView
					.findViewById(R.id.edt_size);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			Bitmap bm = GetThumbNail.getImageThumbnail(image.getImage_path(), 120, 120);
			holder.path.setImageBitmap(bm);
		} catch (Exception e) {
			holder.path.setImageResource(R.drawable.file_delete);
		}
		holder.name.setText(image.getImage_name());
		holder.size.setText(image.getImage_size() / 1024 +"KB");
		return convertView;
	}
	class ViewHolder {
		ImageView path;
		TextView name;
		TextView size;
	}
}
