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

public class Introduction extends Clearner implements
		NavigationDrawerFragment.NavigationDrawerCallbacks, OnClickListener {

	private Button theo_btn, key_btn, prog_btn, test_btn, question_apn_btn, com_btn, instute_apn_btn, proj_btn;
	
	public static boolean showPDF = false;
	
	/** The view to show the ad. */
	private AdView AdView;
	

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
	private  Tracker t ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_introduction);

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
			Clearner.openActivity(Introduction.this, Resource.class);
			this.finish();
		}
		if (position == 1) {
			Clearner.openActivity(Introduction.this, ViewData.class);
			this.finish();
		}

		if (position == 2) {
			Clearner.openActivity(Introduction.this, MainActivity.class);
			this.finish();
		}

		
		if (position == 3) {
			copyFile(getApplicationContext(), "CodeConvention.pdf",  "/data/data/com.Evil.clearner/clearner/CodeConvention.pdf");
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
		super.onStart();
		init();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.theo_btn:
			Clearner.openActivity(this, Theory.class);
			break;
		case R.id.prog_btn:
			Clearner.openActivity(this, ProgramList.class);
			break;
		case R.id.key_btn:
			Clearner.openActivity(this, KeywordOption.class);
			break;
		case R.id.test_btn:
			Clearner.openActivity(this, Nik.class);
			break;
		case R.id.question_apn_btn:
			Clearner.openActivity(this, TestUrSkillActivity.class);
			break;
		case R.id.com_btn:
			Clearner.openActivity(this, YouTubeActivity.class);
			Toast.makeText(getApplicationContext(), " This is great tutorials,\n published on youtube by great Teachers\n Thanks them", Toast.LENGTH_LONG).show();
			break;
		case R.id.proj_btn:
			Clearner.openActivity(this, ProjectActivity.class);
			break;
		case R.id.instute_apn_btn:
			Clearner.openActivity(this, MapActivity.class);
			break;
		}
	}

	private boolean copyFile(Context context, String sourceFileName, String destFileName){
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
	
	@Override
	public void init() {
		super.init();
		setTitle("Introduction");
	
		theo_btn = (Button) findViewById(R.id.theo_btn);
		theo_btn.setOnClickListener(this);
		
		key_btn = (Button) findViewById(R.id.key_btn);
		key_btn.setOnClickListener(this);
		
		prog_btn = (Button) findViewById(R.id.prog_btn);
		prog_btn.setOnClickListener(this);
		
		test_btn = (Button) findViewById(R.id.test_btn);
		test_btn.setOnClickListener(this);
		
		question_apn_btn = (Button) findViewById(R.id.question_apn_btn);
		question_apn_btn.setOnClickListener(this);
		
		com_btn = (Button) findViewById(R.id.com_btn);
		com_btn.setOnClickListener(this);
		
		instute_apn_btn = (Button) findViewById(R.id.instute_apn_btn);
		instute_apn_btn.setOnClickListener(this);
		
		proj_btn = (Button) findViewById(R.id.proj_btn);
		proj_btn.setOnClickListener(this);

		// Create an ad.
	    AdView = new AdView(this);
	    AdView.setAdSize(AdSize.BANNER);
	    AdView.setAdUnitId(Clearner.AD_UNIT_ID);

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
}


