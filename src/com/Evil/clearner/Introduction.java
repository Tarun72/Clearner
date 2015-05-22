package com.Evil.clearner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.Evil.clearner.ClearnerApplication.TrackerName;
import com.PDF.clearner.PdfViewer;
import com.clearner.youtube.YouTubeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class Introduction extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks, OnClickListener {

	private Button B1, B2, B3, B4, B5, B6, B7, B8;
	private Intent df;
	
	public static boolean showPDF = false;
	/** The view to show the ad. */
	private AdView AdView;
	
	private LinearLayout linearLayout_1, linearLayout_2, linearLayout_3, linearLayout_4;

	/* Your ad unit id. Replace with your actual ad unit id. */
	private static final String AD_UNIT_ID = "ca-app-pub-3748442571221151/1824903024";
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	
	// Get tracker.
    Tracker t ;

//    Animation animation,animation2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_introduction);
	/*	animation2 = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
		animation2.setDuration(1000); // duration - half a second
		animation2.setInterpolator(new LinearInterpolator()); // do not alter animation rate
		animation2.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
		animation2.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
	*/ 
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		t = ((ClearnerApplication)Introduction.this.getApplication()).getTracker(
		        TrackerName.APP_TRACKER);
  
		// Set screen name.
        // Where path is a String representing the screen name.
        t.setScreenName("Introduction");// This event will also be sent with &cd=Home%20Screen.
     // Send a screen view.
        t.send(new HitBuilders.AppViewBuilder().build());
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		if (position == 0) {
			Intent df = new Intent(Introduction.this, Resource.class);
			startActivity(df);
			this.finish();
		}
		if (position == 1) {
			Intent df = new Intent(Introduction.this, ViewData.class);
			startActivity(df);
			this.finish();
		}

		if (position == 2) {
			Intent df = new Intent(Introduction.this, MainActivity.class);
			startActivity(df);
			this.finish();
		}

		
		if (position == 3) {
			Boolean yo = copyFile(getApplicationContext(), "CodeConvention.pdf",  "/data/data/com.Evil.clearner/clearner/CodeConvention.pdf");
			System.out.println("MainActivity.onCreate() file is save "+yo);
			Intent intent = new Intent(this, PdfViewer.class);
		    intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, "/data/data/com.Evil.clearner/clearner/CodeConvention.pdf");
		    startActivity(intent);
		    showPDF = true;
		    this.finish();
		}

		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();

	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			break;

		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.introduction, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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
		if(id == R.id.share){
			share();
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_introduction,
					container, false);

			return rootView;

		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((Introduction) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		setTitle("Introduction");
		B1 = (Button) findViewById(R.id.theo);
		B1.setOnClickListener(this);
//		B1.setAnimation(animation2);
		B2 = (Button) findViewById(R.id.key_bttn);
		B2.setOnClickListener(this);
//		B2.setAnimation(animation2);
		B3 = (Button) findViewById(R.id.prog_bttn);
		B3.setOnClickListener(this);
//		B3.setAnimation(animation2);
		B4 = (Button) findViewById(R.id.test_bttn);
		B4.setOnClickListener(this);
//		B4.setAnimation(animation2);
		B5 = (Button) findViewById(R.id.question_apn_bttn);
		B5.setOnClickListener(this);
//		B5.setAnimation(animation2);
		B6 = (Button) findViewById(R.id.com_bttn);
		B6.setOnClickListener(this);
//		B6.setAnimation(animation2);
		B7 = (Button) findViewById(R.id.Instute_apn_bttn);
		B7.setOnClickListener(this);
//		B7.setAnimation(animation2);
		B8 = (Button) findViewById(R.id.Proj_bttn);
		B8.setOnClickListener(this);
/*//		B8.setAnimation(animation2);
		animation =AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_slide_in_bottom);
		animation.setDuration(3000);*/

		/*
		linearLayout_1 = (LinearLayout) findViewById(R.id.Linearlayout_1);
		linearLayout_1.setAnimation(animation);
		
		linearLayout_2 = (LinearLayout) findViewById(R.id.Linearlayout_2);
		linearLayout_2.setAnimation(animation);

		linearLayout_3 = (LinearLayout) findViewById(R.id.Linearlayout_3);
		linearLayout_3.setAnimation(animation);

		linearLayout_4 = (LinearLayout) findViewById(R.id.Linearlayout_4);
		linearLayout_4.setAnimation(animation);
*/	
		// Create an ad.
	    AdView = new AdView(this);
	    AdView.setAdSize(AdSize.BANNER);
	    AdView.setAdUnitId(AD_UNIT_ID);

	    // Add the AdView to the view hierarchy. The view will have no size
	    // until the ad is loaded.
	    LinearLayout layout = (LinearLayout) findViewById(R.id.LineraLyout);
	    layout.addView(AdView);

	    // Create an ad request. Check logcat output for the hashed device ID to
	    // get test ads on a physical device.
	    AdRequest adRequest = new AdRequest.Builder()
	        .build();

	    // Start loading the ad in the background.
	    AdView.loadAd(adRequest);


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.theo:
//			B1.setAnimation(null);
			Intent i = new Intent(this, Theory.class);
			startActivity(i);
			break;
		case R.id.prog_bttn:
//			B3.setAnimation(null);
			Intent df = new Intent(Introduction.this, ProgramList.class);
			startActivity(df);
			break;
		case R.id.key_bttn:
//			B2.setAnimation(null);
			Intent ia = new Intent(this, KeywordOption.class);
			startActivity(ia);
			break;
		case R.id.test_bttn:
//			B4.setAnimation(null);
			Intent start = new Intent(this, Nik.class);
			startActivity(start);
			break;
		case R.id.question_apn_bttn:
//			B5.setAnimation(null);
			Intent d = new Intent(Introduction.this, TestUrSkillActivity.class);
			startActivity(d);

			break;
		case R.id.com_bttn:
//			B6.setAnimation(null);
			Toast.makeText(getApplicationContext(), " This is great tutorials,\n published on youtube by great Teachers\n Thanks them", Toast.LENGTH_LONG).show();
			Intent tar = new Intent(Introduction.this, YouTubeActivity.class);
			startActivity(tar);

			break;
		case R.id.Proj_bttn:
//			B8.setAnimation(null);
			Intent der = new Intent(Introduction.this, ProjectActivity.class);
			startActivity(der);
			break;
		case R.id.Instute_apn_bttn:

//			B7.setAnimation(null);
			Intent derr = new Intent(Introduction.this, MapActivity.class);
			startActivity(derr);
			break;
		}
	}

	private boolean copyFile(Context context, String sourceFileName, String destFileName)
	{
	    AssetManager assetManager = context.getAssets();

	    File destFile = new File(destFileName);

	    File destParentDir = destFile.getParentFile();
	    destParentDir.mkdir();

	    InputStream in = null;
	    OutputStream out = null;
	    try
	    {
	        in = assetManager.open(sourceFileName);
	        out = new FileOutputStream(destFile);

	        byte[] buffer = new byte[1024];
	        int read;
	        while ((read = in.read(buffer)) != -1)
	        {
	            out.write(buffer, 0, read);
	        }
	        in.close();
	        in = null;
	        out.flush();
	        out.close();
	        out = null;

	        return true;
	    }
	    catch (Exception e){
	        e.printStackTrace();
	    }
	    return false;
	}
private void share(){
	   // Launching Share Intent 
    String share = "Check out " + getResources().getString(R.string.app_name) + " " + getResources().getString(R.string.market) + " on the Play Store";
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT, share);
    sendIntent.setType("text/plain");
    startActivity(sendIntent);
}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	public void onResume() {
		super.onResume();

		// Resume the AdView.
		AdView.resume();
	}

	@Override
	public void onPause() {
		// Pause the AdView.
		AdView.pause();

		super.onPause();
	}

	@Override
	public void onDestroy() {
		// Destroy the AdView.
		AdView.destroy();

		super.onDestroy();
	}
}


