package com.wx.demo.fallswall;

import java.util.ArrayList;
import java.util.List;

import com.wx.demo.R;

import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/** 
 * @author browserwang 
 * @version 2015年3月3日 上午10:01:35 
 * 类说明 
 */
public class FWAdapter extends BaseAdapter {

	private Context mContext;
	private List<DataCell> dataCells = new ArrayList<DataCell>();
	
	public FWAdapter(Context context) {
		mContext = context;
	}
	
	public void loadData() {
		for (int i=0; i<Images.imageUrls.length; i++) {
			DataCell cell = new DataCell("name"+i, i, Images.imageUrls[i]);
			dataCells.add(cell);
		}
	}
	
	@Override
	public int getCount() {
		return dataCells.size();
	}

	@Override
	public Object getItem(int position) {
		return dataCells.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {

			convertView = LayoutInflater.from(mContext).inflate(R.layout.sample_item, null, false);
			viewHolder = new ViewHolder();
			viewHolder.iv = (ImageView) convertView.findViewById(R.id.thumbnail);
			viewHolder.tv1 = (TextView) convertView.findViewById(R.id.text1);
			viewHolder.tv2 = (TextView) convertView.findViewById(R.id.text2);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		DataCell cell = (DataCell) getItem(position);
		viewHolder.tv1.setText(cell.mName);
		viewHolder.tv2.setText(cell.mRank + "");
		
		return convertView;
	}

	static class ViewHolder {
		ImageView iv;
		TextView tv1;
		TextView tv2;
	}
}
