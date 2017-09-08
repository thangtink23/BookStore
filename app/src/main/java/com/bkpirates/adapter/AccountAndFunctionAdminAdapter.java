package com.bkpirates.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bkpirates.bookstore.R;

public class AccountAndFunctionAdminAdapter extends ArrayAdapter<String> {

	// Đưa ra danh sách các loại sách

	private Context context;
	private String[] arrList;
	private final String TAG;
	
	public AccountAndFunctionAdminAdapter(Context context, int resource, String[] objects, String tag) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.arrList = objects;
		this.TAG = tag;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_distribute_book, parent, false);
			// tạo view holder
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.name);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText(arrList[position]);
		if (TAG == "ADMIN"){
			holder.name.setTextColor(context.getResources().getColor(R.color.white));
		}
		return convertView;
	}
// tên các loại sách
	static class ViewHolder {
		TextView name;
	}

}
