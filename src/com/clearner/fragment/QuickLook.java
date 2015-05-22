package com.clearner.fragment;

import com.Evil.clearner.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuickLook extends Fragment {
	String quicklock;
	TextView textView_quick;

	public QuickLook(String quicklock) {
		this.quicklock = quicklock;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_programe_show,
				container, false);
		return rootView;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		textView_quick = (TextView) getView().findViewById(
				R.id.programShowTextView);

		textView_quick.setText(quicklock);
	}
}
