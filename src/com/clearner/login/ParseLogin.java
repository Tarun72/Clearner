package com.clearner.login;

import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Evil.clearner.Clearner;
import com.Evil.clearner.Introduction;
import com.Evil.clearner.R;
import com.clearner.utills.ConnectionDetector;
import com.clearner.utills.Preferences;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ParseLogin extends Clearner implements OnClickListener {
	private Button login_btn;
	private EditText  email_edt_txt, password_edt_txt;
	private TextView signup_txt;
	private ConnectionDetector detector;
	private Preferences preferences;
	private ProgressDialog bar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createuser);
		init();
		}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.signup_link_txtView:
			Clearner.openActivity(this, ParseSignup.class);
			break;
		case R.id.Login_btn:
			if(detector.isConnectingToInternet()){
				userLogin();
			}else{ 
			Clearner.toastShow("please connect to internet", getApplicationContext());
			}
			break;
		}
	}
	 private void loginUser(String username, String password) {
	        ParseUser.logInInBackground(username, password, new LogInCallback() {
	            public void done(ParseUser user, ParseException e) {
	            	if(bar.isShowing())
						bar.dismiss();
	            	if (user != null) {
	                    // Hooray! The user is logged in.

	               	 System.out.println("User is login");
	               	preferences.setEmail(user.getEmail());
					preferences.setName(user.getString("firstName"));
	                	navigateToHome();
	                } else {
	                    // Login failed!
	                    AlertDialog.Builder builder = new AlertDialog.Builder(ParseLogin.this);
	                    builder.setMessage(e.getMessage())
	                            .setTitle("Oops!")
	                            .setPositiveButton(android.R.string.ok, null);
	                    AlertDialog dialog = builder.create();
	                    dialog.show();
	                }
	            }
	        });
	    }

	
	private void navigateToHome() {
		// Let's go to the Introductions
		Intent intent = new Intent(ParseLogin.this, Introduction.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}

	private void userLogin() {
		final String email = email_edt_txt.getText().toString();
		final String password = password_edt_txt.getText().toString();
		if(onEmpty(email, password)){
		Clearner.toastShow("please fill all fields", getApplicationContext());
			return;
		}
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		query.whereEqualTo("username", email);
		showProgressDialog("Loading");

		query.findInBackground(new FindCallback<ParseUser>() {
			@Override
			public void done(List<ParseUser> parseUsers, ParseException e) {

				if (e == null) {
					// Successful Query

					// User already exists ? then login
					if (parseUsers.size() > 0) {
						loginUser(email, password);
					} else {
						Toast.makeText(getApplicationContext(),
								"You need to sign up", Toast.LENGTH_LONG)
								.show();
						if(bar != null)
						bar.dismiss();
					}
				} else {
					if(bar != null)
						bar.dismiss();
					// Shit happened!
					AlertDialog.Builder builder = new AlertDialog.Builder(
							ParseLogin.this);
					builder.setMessage(e.getMessage()).setTitle("Oops!")
							.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				}
			}
		});
	}

	private void showProgressDialog(String msg) {
		bar.setMessage(msg);
		bar.setCancelable(false);
		if (!bar.isShowing())
			bar.show();
	}
	private boolean onEmpty(String login, String password){
		
		if(login.isEmpty() || password.isEmpty() ){
		if(login.isEmpty()){
			email_edt_txt.setError("Can't be empty");
		}
		if(password.isEmpty()){
			password_edt_txt.setError("Can't be empty");
		}
		return true;
		}else{
		return false;
		}
	}
	
	@Override
	public void init() {
		super.init();
		detector = new ConnectionDetector(this);
		preferences = Preferences.getInstance(this);
		bar = new ProgressDialog(this);
		
		if (!preferences.getName().equalsIgnoreCase("")
				&& !preferences.getEmail().equalsIgnoreCase("")) {
			navigateToHome();
		}

		// All the views from our login form
		email_edt_txt = (EditText) findViewById(R.id.edit_email);
		password_edt_txt = (EditText) findViewById(R.id.password);
		
		signup_txt = (TextView) findViewById(R.id.signup_link_txtView);
		signup_txt.setPaintFlags(signup_txt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		signup_txt.setOnClickListener(this);
		login_btn = (Button) findViewById(R.id.Login_btn);
		login_btn.setOnClickListener(this);
	}
}
