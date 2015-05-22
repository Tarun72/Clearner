package com.Evil.clearner;

import com.Evil.clearner.ClearnerApplication.TrackerName;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestUrSkillActivity extends Activity implements OnClickListener {

	TextView QuestionTxt, AnswerTxt;
	Button AnswerBut, NextBut;

	private int Counter = 1;
	private InterviewQuesDatabase db;
	private int ID = 0;
	String[] data;
	String LooData;

	/** The view to show the ad. */
	private AdView adView;

	/* Your ad unit id. Replace with your actual ad unit id. */
	private static final String AD_UNIT_ID = "ca-app-pub-3748442571221151/1824903024";
Tracker t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_ur_skill);
		setTitle("Interview Questions");
		t = ((ClearnerApplication) TestUrSkillActivity.this.getApplication())
				.getTracker(TrackerName.APP_TRACKER);

		// Set screen name.
		// Where path is a String representing the screen name.
		t.setScreenName("TestUrSkillActivity");// This event will also be sent with
										// &cd=Home%20Screen.
		// Send a screen view.
		t.send(new HitBuilders.AppViewBuilder().build());

		QuestionTxt = (TextView) findViewById(R.id.questionTextview);
		 AnswerTxt= (TextView) findViewById(R.id.AnswerTextview);
		AnswerBut = (Button) findViewById(R.id.answerButton);
		NextBut = (Button) findViewById(R.id.nextButton);
		NextBut.setOnClickListener(this);
		AnswerBut.setOnClickListener(this);
		// Create an ad.
		adView = new AdView(this);
		adView.setAdSize(AdSize.BANNER);
		adView.setAdUnitId(AD_UNIT_ID);

		// Add the AdView to the view hierarchy. The view will have no size
		// until the ad is loaded.
		LinearLayout layout = (LinearLayout) findViewById(R.id.LineraLyout);
		layout.addView(adView);

		// Create an ad request. Check logcat output for the hashed device ID to
		// get test ads on a physical device.
		AdRequest adRequest = new AdRequest.Builder().build();

		// Start loading the ad in the background.
		adView.loadAd(adRequest);
		getQuestion(1);
	}

	private void getQuestion(int id) {
		// TODO Auto-generated method stub
		ID = id;

		if (ID < 300) {
			db = new InterviewQuesDatabase(this);
			data = db.getNextData(ID);
			db.close();
			/*
			 * System.out.println(data);
			 */
			/*
			 * for(String a: data){ System.out.println(a); }
			 */
			LooData = data[0];
			System.out.println("In oncreate method" + data[1]);
			QuestionTxt.setText("Question. "+data[0]);
			 AnswerTxt.setText("Answer. "+data[1]);

		} else {

			Intent in = new Intent(this, Introduction.class);
			startActivity(in);
			// this.finish();
		}

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
		case R.id.answerButton:
			System.out.println("answer is " + data.length);
			// System.out.println("Value----"+Counter);
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);

			// set title
			alertDialogBuilder.setTitle("Answer");
			// set dialog message
			alertDialogBuilder.setMessage("" + data[1]);

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
			alertDialog.setCancelable(true);
			break;

		case R.id.nextButton:
			++ID;
			getQuestion(ID);
			break;
		}

	}

}
