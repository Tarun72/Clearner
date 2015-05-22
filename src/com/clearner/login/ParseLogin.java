package com.clearner.login;

import java.util.List;

import android.app.Activity;
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

import com.Evil.clearner.Introduction;
import com.Evil.clearner.R;
import com.clearner.utills.ConnectionDetector;
import com.clearner.utills.Preferences;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class ParseLogin extends Activity implements OnClickListener {
	private Button login_btn;
	private EditText firstNameView, email_view, phoneNumberView, passwordView;
	private String name, email, mobile, password;
	private TextView Signup;
	private ConnectionDetector detector;
	private Preferences preferences;
	private ProgressDialog bar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createuser);
		detector = new ConnectionDetector(this);
		preferences = Preferences.getInstance(this);
		bar = new ProgressDialog(this);
		
		if (!preferences.getName().equalsIgnoreCase("")
				&& !preferences.getEmail().equalsIgnoreCase("")) {
			navigateToHome();
		}
		// All the views from our login form
//		firstNameView = (EditText) findViewById(R.id.edit_name);
		email_view = (EditText) findViewById(R.id.edit_email);
//		phoneNumberView = (EditText) findViewById(R.id.edit_mobile);
		passwordView = (EditText) findViewById(R.id.password);
		
//		signup_btn = (Button) findViewById(R.id.signup_btn);
//		signup_btn.setOnClickListener(this);
		Signup = (TextView) findViewById(R.id.textView_signup_link);
		Signup.setPaintFlags(Signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		Signup.setOnClickListener(this);
		login_btn = (Button) findViewById(R.id.Login_btn);
		login_btn.setOnClickListener(this);
/*		
		passwordLinear = (LinearLayout) findViewById(R.id.linear_password);
		nameLinear = (LinearLayout) findViewById(R.id.linear_name);
		mobileLinear = (LinearLayout) findViewById(R.id.linear_mobile);
		emailLinear = (LinearLayout) findViewById(R.id.linear_email);

		showLoginFeilds();
*/	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.textView_signup_link:
			Intent intent = new Intent(this,ParseSignup.class);
			startActivity(intent);
			break;
		case R.id.Login_btn:
			if(detector.isConnectingToInternet()){
				userLogin();
			}else{ 
			Toast.makeText(getApplicationContext(), "please connect to internet", Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}

	private void login() {
		name = firstNameView.getText().toString();
		email = email_view.getText().toString();
		mobile = phoneNumberView.getText().toString();
		password = passwordView.getText().toString();
		if (name.isEmpty() || email.isEmpty() || mobile.isEmpty()||password.isEmpty()) {
			Toast.makeText(getApplicationContext(), "Please enter the details",
					Toast.LENGTH_LONG).show();
		}else{
			showProgressDialog("Loading");
			// Create a ParseUser object to create a new user
            final ParseUser user = new ParseUser();

            user.setUsername(email);
            user.setPassword(password);
            user.put("firstName", name);
            user.put("mobile", mobile);
            user.put("email", email);
            // First query to check whether a ParseUser with
            // the given phone number(email id) already exists or not
            ParseQuery<ParseUser> query = ParseUser.getQuery();
            query.whereEqualTo("username", email);

            query.findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> parseUsers, ParseException e) {

                    if (e == null) {
                        // Successful Query

                        // User already exists ? then login
                        if (parseUsers.size() > 0) {
                            loginUser(email, password);
                        }
                        else {
                            // No user found, so signup
                            signupUser(user);
                        }
                    }
                    else {
                        // Shit happened!
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

	private void signupUser(ParseUser user) {
		user.signUpInBackground(new SignUpCallback() {
			@Override
			public void done(ParseException e) {
				if(bar.isShowing())
					bar.dismiss();
				if (e == null) {
					
					// Signup successful!
					System.out.println("sign up is successfull");
					Toast.makeText(getApplicationContext(),
							"Your account logged", Toast.LENGTH_SHORT).show();
					navigateToHome();
					preferences.setEmail(email);
					preferences.setName(name);
				} else {
					// Fail!
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

	private void navigateToHome() {
		// Let's go to the Introductions
		Intent intent = new Intent(ParseLogin.this, Introduction.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}

	/*private void showLoginFeilds() {
		nameLinear.setVisibility(View.GONE);
		mobileLinear.setVisibility(View.GONE);
		is_login_field_visble = true;
		is_signup_field_visble = false;
	}

	private void showSignUpFields() {
		is_login_field_visble = false;
		nameLinear.setVisibility(View.VISIBLE);
		mobileLinear.setVisibility(View.VISIBLE);
		is_signup_field_visble = true;
	}
*/
	private void userLogin() {
		final String email = email_view.getText().toString();
		final String password = passwordView.getText().toString();
		if(onEmpty(email, password)){
			Toast.makeText(getApplicationContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
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
			email_view.setError("Can't be empty");
		}
		if(password.isEmpty()){
			System.out.println("password");
			passwordView.setError("Can't be empty");
		}
		return true;
		}else{
		return false;
		}
	}
}
