package com.clearner.login;

import java.util.List;

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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ParseSignup extends Activity implements OnClickListener {
	private Button signup_btn;
	private EditText firstNameView, email_view, phoneNumberView, passwordView;
	private String name, email, mobile, password;
	private TextView Signup;
	private ConnectionDetector detector;
	private Preferences preferences;
	private ProgressDialog bar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		detector = new ConnectionDetector(this);
		preferences = Preferences.getInstance(this);
		bar = new ProgressDialog(this);
		// All the views from our login form
		firstNameView = (EditText) findViewById(R.id.edit_name);
		email_view = (EditText) findViewById(R.id.edit_email);
		phoneNumberView = (EditText) findViewById(R.id.edit_mobile);
		passwordView = (EditText) findViewById(R.id.password);
		
		signup_btn = (Button) findViewById(R.id.Signup_btn);
		signup_btn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.Signup_btn){
			login();
		}
		
	}
	private void login() {
		name = firstNameView.getText().toString();
		email = email_view.getText().toString();
		mobile = phoneNumberView.getText().toString();
		password = passwordView.getText().toString();
		if (onEmpty(email, password, name, mobile)) {
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
                    	if(bar.isShowing())
    						bar.dismiss();
    	            
                    	// Shit happened!
                        AlertDialog.Builder builder = new AlertDialog.Builder(ParseSignup.this);
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
					preferences.setName(user.getUsername());
	                	navigateToHome();
	                } else {
	                    // Login failed!
	                    AlertDialog.Builder builder = new AlertDialog.Builder(ParseSignup.this);
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
							ParseSignup.this);
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
		Intent intent = new Intent(ParseSignup.this, Introduction.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}
	private void showProgressDialog(String msg) {
		bar.setMessage(msg);
		bar.setCancelable(false);
		if (!bar.isShowing())
			bar.show();
	}
	
private boolean onEmpty(String login, String password,String name, String number){
		
		if(login.isEmpty() || password.isEmpty()|| name.isEmpty() || number.isEmpty()){
		if(login.isEmpty()){
			email_view.setError("Can't be empty");
		}
		if(password.isEmpty()){
			System.out.println("password");
			passwordView.setError("Can't be empty");
			
		}
		if(number.isEmpty()){
			phoneNumberView.setError("Cant't be empty");
		}else if(number.length() != 10){
			phoneNumberView.setError("Number should be 10 digits");
		}
		if(name.isEmpty()){
			firstNameView.setError("Can't be empty");
		}
		
		return true;
		}else
		return false;
		
	}

}
