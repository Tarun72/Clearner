package com.Evil.clearner;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Customlist extends ArrayAdapter<String> {
	
	private final Activity context; //Context ,name , image variables than constructor 
	private final String[] web;
	private final Integer[] imageId;
	public Customlist(Activity context,String[] web, Integer[] imageId) {
	super(context, R.layout.list_theory, web);
	this.context = context;
	this.web = web;
	this.imageId = imageId;
	}
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.list_theory, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(web[position]);
		imageView.setImageResource(imageId[position]);
		return rowView;

	}
}
