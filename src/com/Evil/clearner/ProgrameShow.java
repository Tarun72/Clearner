package com.Evil.clearner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.Evil.clearner.ClearnerApplication.TrackerName;
import com.clearner.Pager.TabsPagerAdapter;
import com.database.clearner.ProgramDatabase;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.syntax.highlighter.PrettifyHighlighter;

public class ProgrameShow extends ActionBarActivity implements ActionBar.TabListener {
	static TextView tv;
	ProgramDatabase db;
	String topic,code, k, output;
	int j;
	boolean get_program_data = true;
	/** The log tag. */
	  private static final String LOG_TAG = "ProgrameShow";

	  /** Your ad unit id. Replace with your actual ad unit id. */
	  private static final String AD_UNIT_ID = "ca-app-pub-3748442571221151/5620817420";

	  /** The interstitial ad. */
	  private InterstitialAd interstitialAd;
	  // Get tracker
	  Tracker t;

	    private ViewPager viewPager;
	    private TabsPagerAdapter mAdapter;
	    private ActionBar actionBar;
	    // Tab titles
	    private String[] tabs = { "Program", "Output" };
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_programe_show);
		setTitle(getResources().getString(R.string.pro_name));
		t = ((ClearnerApplication)ProgrameShow.this.getApplication()).getTracker(
		        TrackerName.APP_TRACKER);
  
		// Set screen name.
        // Where path is a String representing the screen name.
        t.setScreenName("Programe Show");// This event will also be sent with &cd=Home%20Screen.
     // Send a screen view.
        t.send(new HitBuilders.AppViewBuilder().build());
		
		 // Create an ad.
	    interstitialAd = new InterstitialAd(this);
	    interstitialAd.setAdUnitId(AD_UNIT_ID);

	    // Check the logcat output for your hashed device ID to get test ads on a physical device.
	    AdRequest adRequest = new AdRequest.Builder()
	        .build();

	    // Load the interstitial ad.
	    interstitialAd.loadAd(adRequest);
	    
	    // Set the AdListener.
	    interstitialAd.setAdListener(new AdListener() {
	    
	    	
	    	@Override
	      public void onAdLoaded() {
	        Log.d(LOG_TAG, "onAdLoaded");
	      }

	      @Override
	      public void onAdFailedToLoad(int errorCode) {
	        String message = String.format("onAdFailedToLoad (%s)", getErrorReason(errorCode));
	        Log.d(LOG_TAG, message);
	        Toast.makeText(ProgrameShow.this, message, Toast.LENGTH_SHORT).show();

	      }
	    }); 
		Intent in= getIntent();
		//ob=in.getClass();
		Bundle b = in.getExtras();
	    if(b!=null)
	    {
	        j =(Integer) b.get("ID");
	        k=(String) b.get("database");
	        //upperClass= (Class<String>) b.get("currentIntent");
	    }
	    
	    db= new ProgramDatabase(ProgrameShow.this);
	    code=db.getData(j+1,k ,true);
	    System.out.println("Code at Program show class "+code);
	    output = db.getData(j+1, k, false);
	    System.out.println("Output at Program show class "+output);
	    // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), code, output);
 
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
         
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
         
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();/*
		Intent in= getIntent();
		//ob=in.getClass();
		Bundle b = in.getExtras();
	    if(b!=null)
	    {
	        j =(Integer) b.get("ID");
	        k=(String) b.get("database");
	        //upperClass= (Class<String>) b.get("currentIntent");
	    }
	    
	    db= new ProgramDatabase(ProgrameShow.this);
	    content=db.getData(j+1,k);
	    System.out.println(content);*/
	 // code is a String with source code to highlight
	 // myTextView is a TextView component
//	 PrettifyHighlighter highlighter = new PrettifyHighlighter();
//	 String highlighted = highlighter.highlight("java", content);
//	 tv.setText(Html.fromHtml(highlighted));
//	    tv.setText(db.getData(j+1, k));
	    try{
//	    displayProgram(db.getData(j+1, k));
	    }catch(Exception e){
	    	System.out.println("Exception e"+e);
	    }
	    displayInterstitial();
	}
	 /** Gets a string error reason from an error code. */
	  private String getErrorReason(int errorCode) {
	    String errorReason = "";
	    switch(errorCode) {
	      case AdRequest.ERROR_CODE_INTERNAL_ERROR:
	        errorReason = "Internal error";
	        break;
	      case AdRequest.ERROR_CODE_INVALID_REQUEST:
	        errorReason = "Invalid request";
	        break;
	      case AdRequest.ERROR_CODE_NETWORK_ERROR:
	        errorReason = "Network Error";
	        break;
	      case AdRequest.ERROR_CODE_NO_FILL:
	        errorReason = "No fill";
	        break;
	    }
	    return errorReason;
	  }
	// Invoke displayInterstitial() when you are ready to display an interstitial.
	    public void displayInterstitial() {
	    
	    if (interstitialAd.isLoaded()) {
	        interstitialAd.show();
	      } else {
	        Log.d(LOG_TAG, "Interstitial ad was not ready to be shown.");
		}
	    
	}

	private void displayProgram(String s) throws IOException {

		BufferedReader rdr = new BufferedReader(new StringReader(s));
		List<String> lines = new ArrayList<String>();
		for (String line = rdr.readLine(); line != null; line = rdr.readLine()) {
			lines.add(line);
			System.out.println("The buffer reader line" + line);
		}
		rdr.close();
        String[] highlighted = {""};
		PrettifyHighlighter highlighter = new PrettifyHighlighter();

        int i = 0 ;
		StringBuilder builder = new StringBuilder();
		for (String details : lines) {
		   builder.append(details + "\n");
		highlighted[i] = highlighter.highlight("cpp", details);
		tv.append(Html.fromHtml(highlighted[i]));
		tv.append("\n");
		i = i++;
		}
		
//		for(int j =0 ; j<=highlighted.length;j++){
//			tv.append(Html.fromHtml(highlighted[j]));
//			tv.append("\n");
//	}
//		String Good = builder.toString();
//		System.out.println("The good number" + builder.toString());
//		String Noithing = highlighter.highlight("java", Good);
//		
		
		// tv.setText(Html.fromHtml(highlighted));
	
		
//		tv.setText(nothong);
		 
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		System.out.println("@ arg0"+arg0.toString());
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
	    // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
        System.out.println(tab.getText());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
	