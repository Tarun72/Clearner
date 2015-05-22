package com.Evil.clearner;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;

import com.Evil.clearner.ClearnerApplication.TrackerName;
import com.database.clearner.KeyDatabase;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class KeywordActivity extends ActionBarActivity   {
	
	// TextView tv;
	KeyDatabase db;
	String topic, content, j, k;
	ArrayList<String> Data;
	Tracker t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_programe_show);

		setTitle("Keywords");
		
		t = ((ClearnerApplication) KeywordActivity.this.getApplication())
				.getTracker(TrackerName.APP_TRACKER);

		// Set screen name.
		// Where path is a String representing the screen name.
		t.setScreenName("Introduction");// This event will also be sent with
										// &cd=Home%20Screen.
		// Send a screen view.
		t.send(new HitBuilders.AppViewBuilder().build());
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		Intent in = getIntent();
		// ob=in.getClass();
		// setTitle("Keywords");
		Bundle b = in.getExtras();
		if (b != null) {
			j = (String) b.get("topic");
			k = (String) b.get("database");
			// upperClass= (Class<String>) b.get("currentIntent");
		}

		db = new KeyDatabase(KeywordActivity.this);
		Data = db.getData(j, k);
		System.out.println("Content Length" + Data.size());
	}

}
