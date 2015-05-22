package com.Evil.clearner;

import com.Evil.clearner.ClearnerApplication.TrackerName;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.os.Build;

public class ProjectShow extends ActionBarActivity {
WebView wert;
/** The view to show the ad. */
private AdView adView;
Tracker t;
/* Your ad unit id. Replace with your actual ad unit id. */
private static final String AD_UNIT_ID = "ca-app-pub-3748442571221151/1824903024";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project_show);
		setTitle("Sample Projects");	
		
		t = ((ClearnerApplication) ProjectShow.this.getApplication())
				.getTracker(TrackerName.APP_TRACKER);

		// Set screen name.
		// Where path is a String representing the screen name.
		t.setScreenName("Project show");// This event will also be sent with
										// &cd=Home%20Screen.
		// Send a screen view.
		t.send(new HitBuilders.AppViewBuilder().build());

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_project_show,
					container, false);
					return rootView;
		}
	}

	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
//		wert=(WebView)findViewById(R.id.ProjectWebView);

		Intent in = getIntent();
		// ob=in.getClass();
		Bundle b = in.getExtras();
		int yo = b.getInt("Passed");
		System.out.println("Yo" + yo);
		if (b != null) {
	if(yo==0){
//	    	wert.loadUrl("file:///android_asset/calculator/abc.html");
	    	System.out.println("1Yo"+yo);    

		}
		if(yo==1){
//	    	wert.loadUrl("file:///android_asset/library/library.html");
	    	System.out.println("2Yo"+yo);    

		}
		if(yo==2){	
//			wert.loadUrl("file:///android_asset/medical/Medical.html");
			System.out.println("3Yo"+yo);    

		} 
		if(yo==3)
//	    	wert.loadUrl("file:///android_asset/contacts.html");
		    System.out.println("4Yo"+yo);    

	    }
		
//		WebSettings gh=wert.getSettings();
//		gh.setJavaScriptEnabled(true);
//		gh.setLoadWithOverviewMode(true);
//		gh.setBuiltInZoomControls(true);
//		wert.setWebViewClient(new you());
//		// Create an ad.
	    adView = new AdView(this);
	    adView.setAdSize(AdSize.BANNER);
	    adView.setAdUnitId(AD_UNIT_ID);

	    // Add the AdView to the view hierarchy. The view will have no size
	    // until the ad is loaded.
	    LinearLayout layout = (LinearLayout) findViewById(R.id.LineraLyout);
	    layout.addView(adView);

	    // Create an ad request. Check logcat output for the hashed device ID to
	    // get test ads on a physical device.
	    AdRequest adRequest = new AdRequest.Builder()
	        .build();

	    // Start loading the ad in the background.
	    adView.loadAd(adRequest);

	
	}
}
