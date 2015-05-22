package com.Evil.clearner;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearner.utills.FlipAnimation;
import com.database.clearner.KeyWordDatabase;

public class Card extends ActionBarActivity {
	private int width_screen, height_Screen;
	private ImageView Card_img;
	private KeyWordDatabase db;
	private String  j, k;
	private ArrayList<String> Data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card);
		Card_img = (ImageView) findViewById(R.id.imageView_card);
		
		getScreenSize();
//		setSizeOFView(width_screen, height_Screen);

		Intent in = getIntent();
		// ob=in.getClass();
		// setTitle("Keywords");
		Bundle b = in.getExtras();
		if (b != null) {
			j = (String) b.get("topic");
			k = (String) b.get("database");
			// upperClass= (Class<String>) b.get("currentIntent");
		}

		db = new KeyWordDatabase(Card.this);
		Data = db.getData(j, k);

	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	public void onCardClick(View view) {
		flipCard();
	}

	private void flipCard() {
		View rootLayout = findViewById(R.id.main_activity_root);
		View cardFace = findViewById(R.id.imageView_card);
		View cardBack = (TextView) findViewById(R.id.textView_card);
		FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);

		if (cardFace.getVisibility() == View.GONE) {
			flipAnimation.reverse();
		}
		rootLayout.startAnimation(flipAnimation);
		TextView cardBackss = (TextView) findViewById(R.id.textView_card);

		cardBackss.setText(Data.get(1));
		
	}

	private void getScreenSize() {
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		width_screen = metrics.widthPixels;
		height_Screen = metrics.heightPixels;
		System.out.println("width height" + width_screen + height_Screen);
	}
/*
	private void setSizeOFView(int width, int height) {
		Card_img.setLayoutParams(new LayoutParams(width, height));
	}
*/
}
