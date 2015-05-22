package com.Evil.clearner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearner.login.ParseLogin;
import com.database.clearner.Preferences;

public class Splash extends Activity {

	ProgressDialog progress;
	final int totalProgressTime = 105;
	int jumpTime = 0;
	Preferences preferences;
	String[] files_to_save = { "Calendar.pdf", "CodeConvention.pdf",
			"ContactsManager.pdf", "Department store.pdf",
			"DepartmentStore.pdf", "library.pdf", "medicalstoredepartment.pdf" };;
	
	RotateAnimation r;
	Animation left_right;
//	private static final float ROTATE_FROM = 0.0f;
//	private static final float ROTATE_TO = -10.0f * 360.0f;// 3.141592654f *
	
	private static final float ROTATE_FROM = -10.0f * 360.0f;// 3.141592654f *;														// 32.0f;
	private static final float ROTATE_TO = 0.0f;// 3.141592654f *
	ImageView imageView;
	Thread t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_nothing);

		imageView = (ImageView) findViewById(R.id.imageView1_c_learner_splash);
		preferences = Preferences.getInstance(this);
		progress = new ProgressDialog(this);
		progress.setIcon(R.drawable.ic_launcher);
		progress.setTitle("CLearner");
		progress.setMessage("Saving Required Data");
		progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progress.setIndeterminate(false);
		progress.setCancelable(false);
		progress.setProgress(0);
		
		if (!preferences.getFolder().equals("Clearner")) {
			makeDirectory();
			progress.show();
		} else {
//			timePass();
			justRevolve();
		}
		r = new RotateAnimation(ROTATE_FROM, ROTATE_TO,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		r.setDuration((long) 6 * 1500);
		r.setRepeatCount(0);
		imageView.setAnimation(r);
		animationLeftRight();
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle bundle = msg.getData();
			int update = bundle.getInt("Update") + 1;// add 1 for 0 case
			System.out.println("Update " + update);
			update = update * 15; // multiple by 15
			progress.setProgress(update);
			if (progress.getProgress() >= 100) {
				progress.dismiss();
				try {
					t.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Handler task");
				Intent intent = new Intent(Splash.this, ParseLogin.class);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				startActivity(intent);
				finish();
			}
		}
	};

	void saveFiles() {
		t = new Thread() {

			@Override
			public void run() {
				for (int i = 0; i < files_to_save.length; i++) {

					AssetManager am = getAssets();
					File sourceFile = new File(
							Environment.getExternalStorageDirectory() + "/"
									+ "CLearner");
					if (!sourceFile.exists()) {
						sourceFile.mkdir();
					}
					sourceFile = new File(sourceFile.getAbsoluteFile() + "/"
							+ files_to_save[i]);

					OutputStream os;
					try {
						os = new FileOutputStream(sourceFile.getAbsolutePath());
						byte[] b = new byte[1024];
						int r;
						InputStream is = am.open(files_to_save[i]);
						int bytes_total = is.available();
						System.out.println("-- in task 1" + bytes_total);
						while ((r = is.read(b)) != -1) {
							os.write(b, 0, r);
						}
						is.close();
						os.close();
						// sleep for 10 seconds
						sleep(3000);
						Message message = handler.obtainMessage();
						Bundle bundle = new Bundle();
						bundle.putInt("Update", i);
						message.arg1 = 1;
						message.setData(bundle);
						handler.sendMessage(message);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}// for ends
			}// run ends
		};// thread ends
		t.start();
	}// savefiles end

	void makeDirectory() {
		File folder = new File(Environment.getExternalStorageDirectory()
				+ "/Clearner");
		boolean success = true;
		if (!folder.exists()) {
			success = folder.mkdir();
		}
		if (success) {
			// Do something on success
			preferences.setFolder("Clearner");
			saveFiles();
		} else {
			// Do something else on failure
			preferences.setFolder("null");
		}
		if (folder.exists())
			preferences.setFolder("Clearner");
	}

	void timePass() {

		final Thread t = new Thread() {

			@Override
			public void run() {

				while (jumpTime < totalProgressTime) {
					try {
						sleep(200);
						jumpTime += 5;
						progress.setProgress(jumpTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (progress.getProgress() == 100) {
					progress.dismiss();
					Intent intent = new Intent(Splash.this, ParseLogin.class);
					System.out.println("1The thread task in the timepass");
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					startActivity(intent);
					finish();
				}
			}
		};
		t.start();
	}

	void animationLeftRight() {
//		TextView textView = (TextView) findViewById(R.id.textView_developer);
		left_right = AnimationUtils.loadAnimation(Splash.this,
				R.anim.left_right);
		left_right.setRepeatMode(Animation.RESTART);
		left_right.setDuration(5000);
//		textView.setAnimation(left_right);

	}
	
	private void justRevolve(){
		final Thread thread = new Thread (){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				try {
					sleep(5000);

					Intent intent = new Intent(Splash.this, ParseLogin.class);
					System.out.println("1The thread task in the timepass");
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					startActivity(intent);
					finish();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}
}