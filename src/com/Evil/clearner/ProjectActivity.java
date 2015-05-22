package com.Evil.clearner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import com.PDF.clearner.PdfViewer;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.os.Build;

public class ProjectActivity extends ActionBarActivity implements
		OnClickListener {
	Button Documentations;
	RadioButton project1, project2, project3, project4;
	/** The view to show the ad. */
	private AdView adView;

	/* Your ad unit id. Replace with your actual ad unit id. */
	private static final String AD_UNIT_ID = "ca-app-pub-3748442571221151/1824903024";
	Intent intent;
	GridView gridView;
	private String[] projectnameStrings = {"Department Store","Medical Store","Library","Contact"};
	private int[] ImageID = {
			R.drawable.ic_shop,
			R.drawable.ic_caduceus8,
			R.drawable.ic_library_lib,
			R.drawable.ic_calllog
	};
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		setTitle("Sample Projects");
		intent = new Intent(this, PdfViewer.class);
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
			View rootView = inflater.inflate(R.layout.fragment_project,
					container, false);
			return rootView;
		}
	}

	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		gridView = (GridView) findViewById(R.id.gridView_project);
		gridView.setAdapter(new GridCustom(ProjectActivity.this, projectnameStrings, ImageID));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int poistion,
					long arg3) {
				// TODO Auto-generated method stub
//				Toast.makeText(ProjectActivity.this, "Grid View", Toast.LENGTH_LONG).show();
			System.out.println("poistion number"+poistion);
			int decider = 0;

			if(poistion == 1){
				decider = 2;
				System.out.println("Decider" + decider);
				System.out.println("Decider" + decider);
				Boolean yo = copyFile(getApplicationContext(),
						"medicalstoredepartment.pdf",
						"/data/data/com.Evil.clearner/clearner/medicalstoredepartment.pdf");
				System.out.println("MainActivity.onCreate() file is save " + yo);

			 Intent intent = new Intent(ProjectActivity.this, PdfViewer.class);
				intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME,
						"/data/data/com.Evil.clearner/clearner/medicalstoredepartment.pdf");
				startActivity(intent);
			}
			if(poistion == 2){


				decider = 3;
				System.out.println("Decider" + decider);
				Boolean yo = copyFile(getApplicationContext(), "library.pdf",
						"/data/data/com.Evil.clearner/clearner/library.pdf");
				System.out.println("MainActivity.onCreate() file is save " + yo);

				 Intent intent = new Intent(ProjectActivity.this, PdfViewer.class);
				intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME,
						"/data/data/com.Evil.clearner/clearner/library.pdf");
				startActivity(intent);
				
			}
			if(poistion == 3){
				decider = 4;
				System.out.println("Decider" + decider);
				Boolean yo = copyFile(getApplicationContext(),
						"ContactsManager.pdf",
						"/data/data/com.Evil.clearner/clearner/ContactsManager.pdf");
				System.out.println("MainActivity.onCreate() file is save " + yo);

				 Intent intent = new Intent(ProjectActivity.this, PdfViewer.class);
				intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME,
						"/data/data/com.Evil.clearner/clearner/ContactsManager.pdf");
				startActivity(intent);

				
			}if(poistion == 0){
				decider = 1;
				System.out.println("Decider" + decider);
				Boolean yo = copyFile(getApplicationContext(),
						"DepartmentStore.pdf",
						"/data/data/com.Evil.clearner/clearner/DepartmentStore.pdf");
				System.out.println("MainActivity.onCreate() file is save " + yo);

				 Intent intent = new Intent(ProjectActivity.this, PdfViewer.class);
				intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME,
						"/data/data/com.Evil.clearner/clearner/DepartmentStore.pdf");
				 startActivity(intent);
				
			}
			}
		});
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

	@Override
	public void onClick(View v) {}
		// TODO Auto-generated method stub
		/*int SelectRadioButton = rg.getCheckedRadioButtonId();
		int decider = 0;
		if (SelectRadioButton == R.id.CalculatorProject) {
			decider = 1;
			System.out.println("Decider" + decider);
			Boolean yo = copyFile(getApplicationContext(),
					"DepartmentStore.pdf",
					"/data/data/com.Evil.clearner/clearner/DepartmentStore.pdf");
			System.out.println("MainActivity.onCreate() file is save " + yo);

			// Intent intent = new Intent(this, PdfViewer.class);
			intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME,
					"/data/data/com.Evil.clearner/clearner/DepartmentStore.pdf");
			// startActivity(intent);

		}

		if (SelectRadioButton == R.id.MedicalProjects) {
			decider = 2;
			System.out.println("Decider" + decider);
			System.out.println("Decider" + decider);
			Boolean yo = copyFile(getApplicationContext(),
					"medicalstoredepartment.pdf",
					"/data/data/com.Evil.clearner/clearner/medicalstoredepartment.pdf");
			System.out.println("MainActivity.onCreate() file is save " + yo);

			// Intent intent = new Intent(this, PdfViewer.class);
			intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME,
					"/data/data/com.Evil.clearner/clearner/medicalstoredepartment.pdf");

		}

		if (SelectRadioButton == R.id.LibraryProjects) {
			decider = 3;
			System.out.println("Decider" + decider);
			Boolean yo = copyFile(getApplicationContext(), "library.pdf",
					"/data/data/com.Evil.clearner/clearner/library.pdf");
			System.out.println("MainActivity.onCreate() file is save " + yo);

			// Intent intent = new Intent(this, PdfViewer.class);
			intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME,
					"/data/data/com.Evil.clearner/clearner/library.pdf");
			// startActivity(intent);

		}

		if (SelectRadioButton == R.id.contactsprojects) {
			decider = 4;
			System.out.println("Decider" + decider);
			Boolean yo = copyFile(getApplicationContext(),
					"ContactsManager.pdf",
					"/data/data/com.Evil.clearner/clearner/ContactsManager.pdf");
			System.out.println("MainActivity.onCreate() file is save " + yo);

			// Intent intent = new Intent(this, PdfViewer.class);
			intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME,
					"/data/data/com.Evil.clearner/clearner/ContactsManager.pdf");
			// startActivity(intent);

		}
		switch (v.getId()) {

		case R.id.Documentations_btton:

			intent.putExtra("Passed", decider);
			System.out.println("Decider" + decider);
			startActivity(intent);
			break;
		}
	}*/

	private boolean copyFile(Context context, String sourceFileName,
			String destFileName) {
		AssetManager assetManager = context.getAssets();

		File destFile = new File(destFileName);

		File destParentDir = destFile.getParentFile();
		destParentDir.mkdir();

		InputStream in = null;
		OutputStream out = null;
		try {
			in = assetManager.open(sourceFileName);
			out = new FileOutputStream(destFile);

			byte[] buffer = new byte[1024];
			int read;
			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
