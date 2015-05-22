package com.Evil.clearner;

import java.util.Random;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.Evil.clearner.ClearnerApplication.TrackerName;
import com.clearner.utills.Preferences;
import com.database.clearner.Database;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class Nik extends Activity implements OnClickListener {

	private TextView question, answer, explanation, textView_Timer,
			textView_Score, textView_bagde;
	private RadioGroup rg;
	private RadioButton optA, optB, optC, optD;
	private Button ans, exp, next;
	private ScrollView scrollView;
	private ImageView imageView_for_cards;
	private Preferences preferences;
	private LinearLayout Linearlayout_For_RadioGroup, LineraLyout_Image_Cards,
			LineraLyout_for_Butons_next_previous;
	private int Counter = 1;
	private Database db;
	private int ID = 0;
	private String[] data;
	private CountDownTimer Count;
	private boolean Points = true;
	private int Score = 50;
	private int Attempt = 0;
	/** The view to show the ad. */
	private AdView adView;
	boolean is_Counter_Runnig = false;
	/* Your ad unit id. Replace with your actual ad unit id. */
	private static final String AD_UNIT_ID = "ca-app-pub-3748442571221151/1824903024";

	private Tracker t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nik);
		setTitle("Test Your Skills");
		preferences = Preferences.getInstance(Nik.this);
		t = ((ClearnerApplication) Nik.this.getApplication())
				.getTracker(TrackerName.APP_TRACKER);

		// Set screen name.
		// Where path is a String representing the screen name.
		t.setScreenName("Test Your Skills");// This event will also be sent with
											// &cd=Home%20Screen.
		// Send a screen view.
		t.send(new HitBuilders.AppViewBuilder().build());
		// **Linear Layout**//
		Linearlayout_For_RadioGroup = (LinearLayout) findViewById(R.id.Linearlayout_For_RadioGroup);
		LineraLyout_Image_Cards = (LinearLayout) findViewById(R.id.LineraLyout_Image_Cards);
		LineraLyout_for_Butons_next_previous = (LinearLayout) findViewById(R.id.LineraLyout_for_Butons_next_previous);

		scrollView = (ScrollView) findViewById(R.id.scrollView_Nik);
		ans = (Button) findViewById(R.id.button1);
		exp = (Button) findViewById(R.id.Button2);
		next = (Button) findViewById(R.id.Button3);

		ans.setOnClickListener(this);
		exp.setOnClickListener(this);
		next.setOnClickListener(this);

		question = (TextView) findViewById(R.id.textView1);
		// answer = (TextView) findViewById(R.id.textView2);
		explanation = (TextView) findViewById(R.id.textView3);
		textView_Timer = (TextView) findViewById(R.id.textView_Timer);
		textView_Score = (TextView) findViewById(R.id.textView_Score);
		textView_bagde = (TextView) findViewById(R.id.textView_badge);
		int previousScore = Integer.parseInt((preferences.getScore()));
		if (previousScore < Score) {
			textView_Score.setText("Score " + Score);
		} else {
			Score = previousScore;
			textView_Score.setText("Score " + Score);
		}
		imageView_for_cards = (ImageView) findViewById(R.id.imageView_for_cards);

		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		optA = (RadioButton) findViewById(R.id.radio0);
		optB = (RadioButton) findViewById(R.id.radio1);
		optC = (RadioButton) findViewById(R.id.radio2);
		optD = (RadioButton) findViewById(R.id.radio3);

		showImageCard(Score);
		System.out.println("Score " + Score);
		getQuestion(1);

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

	}

	private void getQuestion(int id) {
		
		timmerTask();
		ID = id;
		if (ID < 149 && ID > 0) {
			showImageCard(Score);
			db = new Database(this);
			data = db.getNextData(ID);
			db.close();
			question.setText(data[1]);
			System.out.println("sdhasjkh" + data[0]);
			// question.setAnimation(animation);
			optA.setText(data[2]);
			optB.setText(data[3]);
			optC.setText(data[4]);
			optD.setText(data[5]);
			// optA.setAnimation(animation);
			// optB.setAnimation(animation);
			// optC.setAnimation(animation);
			// optD.setAnimation(animation);
		} else {
			Intent in = new Intent(this, Introduction.class);
			startActivity(in);
			this.finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.introduction, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {

		case R.id.button1:
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);
			// set title
			alertDialogBuilder.setTitle("Answer");
			// set dialog message
			alertDialogBuilder.setMessage("" + data[5]);
			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();
			// show it
			alertDialog.show();
			alertDialog.setCancelable(true);

			break;
		case R.id.Button2:
			// previous
			try {
				scrollView.setBackground(getResources().getDrawable(
						R.drawable.theme));
				if (Counter > 0)
					--Counter;
				System.out.print("" + Counter);
				if (Counter <= 149) {
					answer.setText(null);
					explanation.setText(null);
					rg.clearCheck();
					System.out.println("Value----" + Counter);
					getQuestion(Counter);
					Count.start();
				} else {
					Toast.makeText(getApplicationContext(),
							"No previous question", Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(),
						"ley Kamini Crash handle", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.Button3:
			// next
			// setBackgroundRadioButtons();
			scrollView.setBackground(getResources().getDrawable(
					R.drawable.theme));
			Random r = new Random();
			Counter = r.nextInt(149 - 1) + 1;
			++Counter;
			if (Counter >= 0) {
				// getQuestion(Counter);
				System.out.println(data[6]);

				int checked = rg.getCheckedRadioButtonId();
				RadioButton checkedbutton = (RadioButton) findViewById(checked);
				if (!(checkedbutton == null)) {

					String text = checkedbutton.getText().toString();
					System.out.println(data[5]);
					if (checkedbutton.getId() == R.id.radio0) {
						String textA = "Ans:A";
						if (textA.equals(data[6])) {
							onCorrect();
							// rg.clearCheck();
							// getQuestion(Counter);
							// Count.start();
						} else {
							onIncorrect();
						}
					} else if (checkedbutton.getId() == R.id.radio1) {
						String textB = "Ans:B";
						System.out.println("B");
						if (textB.equals(data[6])) {
							onCorrect();
							// rg.clearCheck();
							// getQuestion(Counter);
							// Count.start();
							// System.out.println("B");
						} else {
							onIncorrect();
						}
					} else if (checkedbutton.getId() == R.id.radio2) {
						String textC = "Ans:C";
						if (textC.equals(data[6])) {
							onCorrect();
							// rg.clearCheck();
							// getQuestion(Counter);
							// Count.start();
						} else {
							onIncorrect();
						}
					} else if (checkedbutton.getId() == R.id.radio3) {
						String textD = "Ans:D";
						if (textD.equals(data[6])) {
							onCorrect();
						} else {
							onIncorrect();
						}
					}
				} else {
					Toast.makeText(this, "Select an Option", Toast.LENGTH_SHORT)
							.show();
				}
				break;
			}
		}
	}

	void onIncorrect() {
		Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
		Score = Score - 4;
		textView_Score.setText("Score " + Score);
	}

	void onCorrect() {
		Score = Score + 5;
		textView_Score.setText("Score " + Score);
		rg.clearCheck();
		getQuestion(Counter);
		Count.start();
	}

	/**
	 * @author Tarun Sharma
	 * @param Score
	 *            Shows images on increasing score
	 * @exception NullpointerException
	 *                or NumberFormatException
	 * */
	void showImageCard(int Score) {
		if (Score >= 60 && Score <= 100) {
			imageView_for_cards.setVisibility(View.VISIBLE);
			textView_bagde.setText("Beginner");
			imageView_for_cards.setBackgroundResource(R.drawable.baby);
			// Linearlayout_For_RadioGroup.setVisibility(View.INVISIBLE);
			LineraLyout_Image_Cards.setVisibility(View.VISIBLE);
			// LineraLyout_for_Butons_next_previous.setVisibility(View.INVISIBLE);
		}
		// 120
		if (Score >= 101 && Score <= 120) {
			imageView_for_cards.setVisibility(View.VISIBLE);
			// Linearlayout_For_RadioGroup.setVisibility(View.INVISIBLE);
			textView_bagde.setText("Teenager");
			imageView_for_cards.setBackgroundResource(R.drawable.teenager);
			LineraLyout_Image_Cards.setVisibility(View.VISIBLE);
			// LineraLyout_for_Butons_next_previous.setVisibility(View.INVISIBLE);
		}

		if (Score >= 121 && Score <= 150) {
			imageView_for_cards.setVisibility(View.VISIBLE);
			textView_bagde.setText("Advanced");
			imageView_for_cards.setBackgroundResource(R.drawable.iconexpert);
			// Linearlayout_For_RadioGroup.setVisibility(View.INVISIBLE);
			LineraLyout_Image_Cards.setVisibility(View.VISIBLE);
			// LineraLyout_for_Butons_next_previous.setVisibility(View.INVISIBLE);
		}
		if (Score >= 151 && Score <= 180) {
			imageView_for_cards.setVisibility(View.VISIBLE);
			textView_bagde.setText("Albert-Einstein");
			imageView_for_cards
					.setBackgroundResource(R.drawable.albert_einstein);
			// Linearlayout_For_RadioGroup.setVisibility(View.INVISIBLE);
			LineraLyout_Image_Cards.setVisibility(View.VISIBLE);
			// LineraLyout_for_Butons_next_previous.setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * @author Tarun Sharma timmerTask provide a Count down timer in
	 *         applications
	 * @param <code>null</code>
	 * */
	void timmerTask() {
		if (Count != null)
			Count.cancel();
		Count = new CountDownTimer(30000, 1000) {
			public void onTick(long millisUntilFinished) {
				textView_Timer.setText("" + millisUntilFinished / 1000);

				if (millisUntilFinished / 1000 <= 10) {
					is_Counter_Runnig = true;
					long seconds = millisUntilFinished / 1000;
					System.out.println("1Seconds in seconds" + seconds);
					if (seconds % 2 == 0)
						scrollView.setBackgroundColor(Color
								.parseColor("#FF0000"));
					else {
						scrollView.setBackgroundColor(Color
								.parseColor("#F5A9A9"));
					}
				}
			}

			public void onFinish() {

				scrollView.setBackground(getApplicationContext().getResources()
						.getDrawable(R.drawable.theme));
				textView_Timer.setText("Finished");
				System.out.println("Should be finished ");
				Score = Score - 5;
				textView_Score.setText("Score " + Score);
				is_Counter_Runnig = false;
			}
		};
		Count.start();
	}

	private void checkRadioButtonListner() {
		optA.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					optA.setBackgroundColor(getResources().getColor(
							R.color.Green));
				} else {
					optA.setBackgroundColor(getResources().getColor(
							R.color.OrangeRed));
				}
			}
		});
		optB.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					optB.setBackgroundColor(getResources().getColor(
							R.color.Green));
				} else {
					optB.setBackgroundColor(getResources().getColor(
							R.color.OrangeRed));
				}
			}
		});
		optC.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (isChecked) {
					optC.setBackgroundColor(getResources().getColor(
							R.color.Green));
				} else {
					optC.setBackgroundColor(getResources().getColor(
							R.color.OrangeRed));
				}
			}
		});

		optD.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					optD.setBackgroundColor(getResources().getColor(
							R.color.Green));
				} else {
					optD.setBackgroundColor(getResources().getColor(
							R.color.OrangeRed));
				}
			}
		});
	}

	private void setBackgroundRadioButtons() {
		optA.setBackgroundColor(getResources().getColor(R.color.OrangeRed));
		optB.setBackgroundColor(getResources().getColor(R.color.OrangeRed));
		optC.setBackgroundColor(getResources().getColor(R.color.OrangeRed));
		optD.setBackgroundColor(getResources().getColor(R.color.OrangeRed));

	}

	@Override
	public void onBackPressed() {
		preferences.setScore(String.valueOf(Score));
		super.onBackPressed();

	}
}