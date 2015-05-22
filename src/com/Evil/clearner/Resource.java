package com.Evil.clearner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Resource extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resources);
	}

	@Override
	public void onBackPressed() {
		openActivity();
		finish();
	}
	
	private void openActivity(){
		Intent intent = new Intent(this, Introduction.class);
		startActivity(intent);
	}
}
