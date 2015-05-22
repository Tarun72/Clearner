package com.Evil.clearner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import com.Evil.clearner.ClearnerApplication.TrackerName;
import com.PDF.clearner.PdfViewer;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

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
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Theory extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks, OnItemClickListener {
	ListView list;
	private String[] Something = { "History", "Why to use C?",
			"Environment Setup", "Programming Basics", "Basic Bulidung Blocks",
			"The printf and scanf", "Data Types", "Token", "Variables",
			"Constant", "Storage Classes", "C Operator", "Decision Making",
			"loop", "Functions", "Arrays", "Pointers", "Strings",
			"Inputs and Outputs", "typedef", "preprocessors", "Type Casting",
			"Memory management", "More tutorials" };

	private String[] topic = { "Introduction",
			"Constants, Variables and Keywords",
			"Rules for constructing constants",
			"Rules for constructing variable names", "Data Types",
			"Understanding the program in C", "Instructions in C",
			"The if statement", "The if-else statement",
			"The Conditional operators", "The switch statement",
			"The goto keyword", "Loops", "while statement",
			"do-while statement", "for statement", "The continue statement",
			"The break statement", "Functions",
			"Function declaration and Prototype", "Prototype", "Pointers",
			"1-Dimensional Array", "2-Dimensional Array", "Strings in C",
			"String Handling Functions", "sscanf and sprintf functions",
			"Storage Classes in C", "Structures in C", "The C Preprocessor",
			"C File I/O and Binary File I/O", "Typecasting",
			"Command line arguments", "Linked Lists", "Recursion",
			"Variable argument lists", "Binary Trees", "C Programming Examples" };

	Integer[] imageId = { R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher, 
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher

	};

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
	Tracker t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_theory);
		setTitle("Theory");
		t = ((ClearnerApplication) Theory.this.getApplication())
				.getTracker(TrackerName.APP_TRACKER);

		// Set screen name.
		// Where path is a String representing the screen name.
		t.setScreenName("TestUrSkillActivity");// This event will also be sent
												// with
		// &cd=Home%20Screen.
		// Send a screen view.
		t.send(new HitBuilders.AppViewBuilder().build());

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	/*	Toast.makeText(getApplicationContext(), "String" + Something.length,
				Toast.LENGTH_LONG).show();*/
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		if (position == 0) {
			Intent df = new Intent(Theory.this, Resource.class);
			startActivity(df);
			this.finish();
		}
		if (position == 1) {
			Intent df = new Intent(Theory.this, ViewData.class);
			startActivity(df);
			this.finish();
		}

		if (position == 2) {
			Intent df = new Intent(Theory.this, MainActivity.class);
			startActivity(df);
			this.finish();
		}

		
		if (position == 3) {
			Boolean yo = copyFile(getApplicationContext(), "CodeConvention.pdf",  "/data/data/com.Evil.clearner/clearner/CodeConvention.pdf");
			System.out.println("MainActivity.onCreate() file is save "+yo);
			Intent intent = new Intent(this, PdfViewer.class);
		    intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, "/data/data/com.Evil.clearner/clearner/CodeConvention.pdf");
		    startActivity(intent);
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
			getMenuInflater().inflate(R.menu.theory, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_theory,
					container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((Theory) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Customlist adapter = new Customlist(Theory.this, Something, imageId);
		list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		AnimationSet set = new AnimationSet(true);

		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(300);
		set.addAnimation(animation);

		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				-1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(100);
		set.addAnimation(animation);

		LayoutAnimationController controller = new LayoutAnimationController(
				set, 0.5f);
		ListView listView = list;
		listView.setLayoutAnimation(controller);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		/*
		 * Intent i=new Intent(getApplicationContext(),BrowserActivity.class);
		 * i.putExtra("position", position); startActivity(i);
		 */
		/*
		 * String item= top ic[position];
		 * System.out.println(""+topic[position]); Intent in= new
		 * Intent(this,theoryShow.class); in.putExtra("topic", position);
		 * in.putExtra("database", "C_Theory_Table");
		 * //in.putExtra("currentIntent", "CProgramList.class");
		 */
		String item = Something[position];
		Intent intent = new Intent(this, ShowTheory.class);
		intent.putExtra("topic", position);
		intent.putExtra("database", "C_THEORY_NEW");

//		Theory.this.finish(); // finish this activity
		startActivity(intent);
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


}
