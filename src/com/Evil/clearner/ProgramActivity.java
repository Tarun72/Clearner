package com.Evil.clearner;

import com.Evil.clearner.ClearnerApplication.TrackerName;
import com.database.clearner.ProgramDatabase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProgramActivity extends Activity {
	TextView tv;
	ProgramDatabase db;
	String topic,content,k;
	int j;
	/** The view to show the ad. */
	private AdView adView;

	/* Your ad unit id. Replace with your actual ad unit id. */
	private static final String AD_UNIT_ID = "ca-app-pub-3748442571221151/1824903024";	
	
	Tracker t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_program);
		tv=(TextView)findViewById(R.id.Keyword_Summary);
		setTitle(getResources().getString(R.string.pro_name));
		t = ((ClearnerApplication)ProgramActivity.this.getApplication()).getTracker(
		        TrackerName.APP_TRACKER);
  
		// Set screen name.
        // Where path is a String representing the screen name.
        t.setScreenName("ProgramActivity");// This event will also be sent with &cd=Home%20Screen.
     // Send a screen view.
        t.send(new HitBuilders.AppViewBuilder().build());
		Intent in= getIntent();
		//ob=in.getClass();
		Bundle b = in.getExtras();
	    if(b!=null)
	    {
	        j =(int) b.getInt("topic");
	        k=(String) b.get("database");
	        //upperClass= (Class<String>) b.get("currentIntent");
	    }
	    
	   /* db= new ProgramDatabase(ProgramActivity.this);
	    content=db.getData(j,k);
	    tv.setText(content);
	*/	

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
			    AdRequest adRequest = new AdRequest.Builder()
			        .build();

			    // Start loading the ad in the background.
			    adView.loadAd(adRequest);

	}

}
