package com.Evil.clearner;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;

import com.Evil.clearner.ClearnerApplication.TrackerName;
import com.clearner.Pager.TabsKaywordPagerAdapter;
import com.database.clearner.KeyDatabase;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class KeywordActivity extends ActionBarActivity implements ActionBar.TabListener  {
	
	// TextView tv;
	KeyDatabase db;
	String topic, content, j, k;
	ArrayList<String> Data;
	// Get tracker.
	Tracker t;
//	ViewSwitcher switcher;
//	int screenWidth, screenHeight;
	// TextSwitcher textSwitcher;
	/** The view to show the ad. */
//	private AdView adView;
//	ScrollView scrollView;
	/* Your ad unit id. Replace with your actual ad unit id. */
	private static final String AD_UNIT_ID = "ca-app-pub-3748442571221151/1824903024";
	private ViewPager viewPager;
	private TabsKaywordPagerAdapter mAdapter;
	private ActionBar actionBar;
    // Tab titles
    private String[] tabs = {"Summary"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_programe_show);

		setTitle("Keywords");
		
		t = ((ClearnerApplication) KeywordActivity.this.getApplication())
				.getTracker(TrackerName.APP_TRACKER);

		// Set screen name.
		// Where path is a String representing the screen name.
		t.setScreenName("Introduction");// This event will also be sent with
										// &cd=Home%20Screen.
		// Send a screen view.
		t.send(new HitBuilders.AppViewBuilder().build());
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();

		/*// Create an ad.
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
*/		/*DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		System.out.println("screenWidth" + screenWidth + screenHeight);
		*//*
		 * tv = (TextView) findViewById(R.id.Keyword_Summary);
		 * tv.setCustomSelectionActionModeCallback(new Callback() {
		 * 
		 * @Override public boolean onPrepareActionMode(ActionMode mode, Menu
		 * menu) { // TODO Auto-generated method stub
		 * 
		 * return false; }
		 * 
		 * @Override public void onDestroyActionMode(ActionMode mode) { // TODO
		 * Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public boolean onCreateActionMode(ActionMode mode, Menu
		 * menu) { // TODO Auto-generated method stub MenuInflater inflater =
		 * mode.getMenuInflater(); inflater.inflate(R.menu.custom_menu, menu);
		 * return true;
		 * 
		 * }
		 * 
		 * @Override public boolean onActionItemClicked(ActionMode mode,
		 * MenuItem item) { // TODO Auto-generated method stub int
		 * startSelections=tv.getSelectionStart(); int
		 * endSelections=tv.getSelectionEnd(); String selectedText =
		 * tv.getText().toString().substring(startSelections, endSelections);
		 * 
		 * switch(item.getItemId()){ case R.id.share :
		 * System.out.println("selectedText startSelections endSelections"
		 * +startSelections+endSelections+selectedText);
		 * 
		 * Intent sendIntent = new Intent();
		 * sendIntent.setAction(Intent.ACTION_SEND);
		 * sendIntent.putExtra(Intent.EXTRA_TEXT, selectedText);
		 * sendIntent.setType("text/plain"); startActivity(sendIntent); break;
		 * case R.id.Search:
		 * 
		 * Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
		 * intent.putExtra(SearchManager.QUERY, selectedText); // query contains
		 * search string startActivity(intent); break; }
		 * 
		 * return true; } });
		 */
		
		Intent in = getIntent();
		// ob=in.getClass();
		// setTitle("Keywords");
		Bundle b = in.getExtras();
		if (b != null) {
			j = (String) b.get("topic");
			k = (String) b.get("database");
			// upperClass= (Class<String>) b.get("currentIntent");
		}

		db = new KeyDatabase(KeywordActivity.this);
		Data = db.getData(j, k);
		System.out.println("Content Length" + Data.size());
		 // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        mAdapter = new TabsKaywordPagerAdapter(getSupportFragmentManager(), Data);
 
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
//                actionBar.setSelectedNavigationItem(position);
            }
         
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
         
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

		// tv.setText(Data.get(1));

		/*
		 * try { designPagesc(content); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		// tryChance();
		// nothingLikeAnything(content);
		// visibleTex();
	}

	/*
	 * Algorithm Find word in String then, find line in String first line become
	 * Title and other become paragraphs
	 */
/*
	private void designPagesc(String content) throws IOException {
		int number_of_line = getLineCount(content);
		System.out.println("number of line " + number_of_line);
		String title = getTitle(content);
		System.out.println("Title" + title);
		String[] paragraph = getParagraph(content);
		System.out.println("paragraph" + paragraph.length);
		for (int i = 0; i < paragraph.length; i++) {
			System.out.println("Paragraph number " + i);
			System.out.println(paragraph[i]);
		}

		BufferedReader rdr = new BufferedReader(new StringReader(content));
		List<String> lines = new ArrayList<String>();
		for (String line = rdr.readLine(); line != null; line = rdr.readLine()) {
			lines.add(line);
			System.out.println("The buffer reader line" + line);
		}
		rdr.close();

		System.out.println("Number of paragraph" + lines.size());
		// lines now contains all the strings between line breaks
	}

	*//**
	 * getLineCount
	 * 
	 * @author Tarun
	 * @return number of line
	 * @param text
	 *            String String data coming from database
	 * *//*
	public static int getLineCount(String text) {
		return text.split("[\n|\r]").length;
	}

	*//**
	 * getTitle
	 * 
	 * @author Tarun
	 * @param String
	 *            data coming from database return String Title
	 * *//*
	public String getTitle(String Content) {
		String[] Totalline = content.split("[\n|\r]");
		String title = Totalline[0];
		return title;
	}

	*//**
	 * getParagraph
	 * 
	 * @author Tarun
	 * @param String
	 *            Content pass from data
	 * @return String array of paragraph
	 *//*
	private String[] getParagraph(String content) {
		String[] paragraph = content.split(".");
		System.out.println("paragraph" + paragraph.length);
		return paragraph;
	}

	
	 * public void tryChance(){ tv.measure(0, 0); int scrollY = tv.getScrollY();
	 * 
	 * int textview_height = tv.getLayout().getHeight(); int number_of_lines =
	 * tv.getLayout().getLineCount(); int textview_width =
	 * tv.getLayout().getWidth(); Layout layout = tv.getLayout(); int
	 * firstVisibleLineNumber = layout.getLineForVertical(scrollY); int
	 * lastVisibleLineNumber =
	 * layout.getLineForVertical(scrollY+textview_height);
	 * System.out.println("textview_height  "
	 * +textview_height+"Number of lines "+
	 * number_of_lines+" textview_width"+textview_width);
	 * System.out.println("firstVisibleLineNumber "+firstVisibleLineNumber);
	 * System.out.println("last Visable line number "+lastVisibleLineNumber);
	 * System.out.println("Scrolly "+scrollY);
	 * System.out.println("the nothing is done on it ");
	 * 
	 * } void visibleTex(){ int start = tv.getLayout().getLineStart(0); int end
	 * = tv.getLayout().getLineEnd(tv.getLineCount() - 1); String displayed =
	 * tv.getText().toString().substring(start, end);
	 * System.out.println("The String displayed = "+displayed); }
	 
	void nothingLikeAnything(String contentString) {
		int totalPages = 1;
		// obtaining screen dimensions
		Display display = getWindowManager().getDefaultDisplay();
		int screenWidth = display.getWidth();
		int screenHeight = display.getHeight();

		// contentString is the whole string of the book

		while (contentString != null && contentString.length() != 0) {
			totalPages++;

			// creating new textviews for every page
			TextView contentTextView = new TextView(this);
			contentTextView.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
			contentTextView.setHeight(ViewGroup.LayoutParams.FILL_PARENT);
			contentTextView.setMaxHeight(screenHeight);
			contentTextView.setMaxWidth(screenWidth);

			float textSize = contentTextView.getTextSize();
			Paint paint = new Paint();
			paint.setTextSize(textSize);

			int numChars = 0;
			int lineCount = 0;
			int maxLineCount = screenHeight / contentTextView.getLineHeight();
			contentTextView.setLines(maxLineCount);

			while ((lineCount < maxLineCount)
					&& (numChars < contentString.length())) {
				numChars = numChars
						+ paint.breakText(contentString.substring(numChars),
								true, screenWidth, null);
				lineCount++;
				System.out.println("line count " + lineCount);
				System.out.println("maxcount" + maxLineCount);
				System.out.println("string length  " + contentString.length());
			}

			// retrieve the String to be displayed in the current textbox
			String toBeDisplayed = contentString.substring(0, numChars);
			contentString = contentString.substring(numChars);
			contentTextView.setText(toBeDisplayed);
			System.out.println("total pages " + totalPages);
			System.out.println("String to be displayed " + toBeDisplayed);

			numChars = 0;
			lineCount = 0;
		}*/

	

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		 viewPager.setCurrentItem(tab.getPosition());
	        System.out.println(tab.getText());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
}
