package com.Evil.clearner;

import java.util.ArrayList;

import com.Evil.clearner.ClearnerApplication.TrackerName;
import com.clearner.fragment.Code;
import com.clearner.fragment.WhatIskeyword;
import com.database.clearner.KeyDatabase;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;

public class ShowKeywordInformation extends ActionBarActivity {
	private LinearLayout layout;
	KeyDatabase db;
	String topic, content, j, k;
	ArrayList<String> Data;
	// Get tracker.
	Tracker t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_keyword_info);
		layout = (LinearLayout) findViewById(R.id.fragment_container);
		t = ((ClearnerApplication) this.getApplication())
				.getTracker(TrackerName.APP_TRACKER);

		// Set screen name.
		// Where path is a String representing the screen name.
		t.setScreenName("Introduction");// This event will also be sent with
										// &cd=Home%20Screen.
		// Send a screen view.
		t.send(new HitBuilders.AppViewBuilder().build());

		Intent in = getIntent();
		// ob=in.getClass();
		// setTitle("Keywords");
		Bundle b = in.getExtras();
		if (b != null) {
			j = (String) b.get("topic");
			k = (String) b.get("database");
			// upperClass= (Class<String>) b.get("currentIntent");
		}

		db = new KeyDatabase(this);
		Data = db.getData("case", "C_Keywords_Table");
		System.out.println("Content Length" + Data.size());

		String summary = Data.get(2);
		addFragment(new WhatIskeyword(summary));
	}
	
	public void addFragment(Fragment fragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	    fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

}
