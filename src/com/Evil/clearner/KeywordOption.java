package com.Evil.clearner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class KeywordOption extends ActionBarActivity {

	private AdView adView;

	/* Your ad unit id. Replace with your actual ad unit id. */
	private static final String AD_UNIT_ID = "ca-app-pub-3748442571221151/1824903024";
	private Intent intent;
	private GridView gridView;
	private String[] projectnameStrings = {"Keywords","Summary","Flashcards"};
	private int[] ImageID = {
			R.drawable.ic_key_pop,
			R.drawable.ic_caduceus8,
			R.drawable.ic_heart
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_project);
		gridView = (GridView) findViewById(R.id.gridView_project);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int poistion,
					long arg3) {
				
				if(poistion == 0 ){
					Intent intent = new Intent(KeywordOption.this , ShowKeywordInformation.class);
					intent.putExtra("needtoshow", "");
					startActivity(intent);
				}
				else if(poistion == 1 ){
					Intent intent = new Intent(KeywordOption.this , Keywords.class);
					intent.putExtra("needtoshow", "");
					startActivity(intent);
				}
				else if(poistion == 2 ){
					Intent intent = new Intent(KeywordOption.this , Keywords.class);
					intent.putExtra("needtoshow", "yo");
					startActivity(intent);
				}
			}
		});
		gridView.setAdapter(new GridCustom(KeywordOption.this, projectnameStrings, ImageID));
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

	/**
	 * A placeholder fragment containing a simple view.
	 *//*
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_project,
					container, false);
			return rootView;
		}
	}
*/

	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();


	}
}
