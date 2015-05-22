package com.Evil.clearner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class GridCustom extends BaseAdapter{
	private Context context;
	private String[] projectname;
	private int[] ImageID;
	
	public GridCustom(Context context, String[] projectname , int[] ImageID) {
		// TODO Auto-generated constructor stub
	this.context =     context;
	this.ImageID =     ImageID;
	this.projectname = projectname;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return projectname.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View grid;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			grid = new View(context);
			grid = inflater.inflate(R.layout.costom_adapter, null);
			TextView textView = (TextView) grid
					.findViewById(R.id.textView_grid);
			ImageView imageView = (ImageView) grid
					.findViewById(R.id.imageView_grid);
			textView.setText(projectname[position]);
			imageView.setImageResource(ImageID[position]);

		} else {
			grid = (View) convertView;
		}
		return grid;
	}
		
}
