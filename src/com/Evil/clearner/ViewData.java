package com.Evil.clearner;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.clearner.login.ParseLogin;
import com.clearner.utills.Preferences;
import com.parse.ParseUser;

public class ViewData extends Activity implements 
		OnClickListener {
	private static final int REAUTH_ACTIVITY_CODE = 100;
	TextView name, mail, number;
	Button B;
	LocationManager locationManager;
	double longitude, latitude;
	Preferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_data);
		setTitle("Account");
		name = (TextView) findViewById(R.id.textname);
		mail = (TextView) findViewById(R.id.textemail);
		B = (Button) findViewById(R.id.imagedone);
		B.setOnClickListener(this);
		preferences=Preferences.getInstance(ViewData.this);
		setTitle("User Detail");
		name.setText(""+preferences.getName());
		mail.setText(""+preferences.getEmail());
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.introduction, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imagedone:
			preferences.setEmail("");
			preferences.setName("");
			ParseUser.logOut();
			finish();
			
			Intent i = new Intent(this, ParseLogin.class);
			startActivity(i);
			finish();
			break;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i = new Intent(this, Introduction.class);
		startActivity(i);
		ViewData.this.finish();
	}
	@Override
	public void onResume() {
	    super.onResume();
	}

	@Override
	public void onSaveInstanceState(Bundle bundle) {
	    super.onSaveInstanceState(bundle);
	}

	@Override
	public void onPause() {
	    super.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	}
}
