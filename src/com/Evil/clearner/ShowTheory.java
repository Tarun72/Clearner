package com.Evil.clearner;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.clearner.Pager.TabsPagerAdapter;
import com.clearner.Pager.TabsPagerTheoryAdapter;
import com.database.clearner.TheroyDataBase;

public class ShowTheory extends ActionBarActivity implements OnGestureListener, OnTouchListener, GestureDetector.OnDoubleTapListener ,ActionBar.TabListener {
	ViewFlipper flipper;
	TextView textView_content, textView_deeper, textView_look, textView_summary,title_theory, title_theory_screen;
	String Topic, Deeper, tk_look, summary, content, database;
	int some;
	TheroyDataBase db;
	ArrayList<String> Content = new ArrayList<String>();
	private float lastX;
    ScrollView scrollview_flipper;
	GestureDetector detector; 
	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    enum Theory {Content, deeper, takealook, Summary}

    private ViewPager viewPager;
    private TabsPagerTheoryAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "CONTENT","DEEPER","LOOK","SUMMARY" };
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_theory);
/*		flipper = (ViewFlipper) findViewById(R.id.view_Flipper);
		title_theory= (TextView) findViewById(R.id.title_theory);
		title_theory_screen = (TextView) findViewById(R.id.title_theory_screen);
		textView_content = (TextView) findViewById(R.id.textView_Theory_show_content);
		textView_deeper = (TextView) findViewById(R.id.textView_Theory_show_deeper);
		textView_look = (TextView) findViewById(R.id.textView_Theory_show_look);
		textView_summary = (TextView) findViewById(R.id.textView_Theory_show_summary);
		scrollview_flipper = (ScrollView) findViewById(R.id.scroll_view_for_flipper);
*/		detector = new GestureDetector(this);
		Intent in = getIntent();
		// ob=in.getClass();
		Bundle b = in.getExtras();
		if (b != null) {
			some = (Integer) b.get("topic");
			database = (String) b.get("database");
			// upperClass= (Class<String>) b.get("currentIntent");
		}
		int number = some +1;
		//HEADING 1  CONTENT 2  DEEPER 3 LOOK 4  SUMMARY 5
		db = new TheroyDataBase(ShowTheory.this);
		Content = db.getData(some, database);
		initTabs(Content);
		System.out.println("Data" + Content.size());
		/*title_theory_screen.setText("Chapter "+number+"-"+Content.get(4));
		textView_content.setText(Content.get(0));
		textView_deeper.setText(Content.get(3));
		textView_look.setText(Content.get(2));
		textView_summary.setText(Content.get(1));
		scrollview_flipper.setOnTouchListener(this);
		System.out.println(Content.get(0)+Content.get(1)+Content.get(2)+Content.get(3));
		title_theory.setText("Introduction");
		title_theory.setPaintFlags(title_theory.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		flipper.indexOfChild(textView_content);
*/    }

	// Using the following method, we will handle all screen swaps.
/*
	public boolean onTouchEvent(MotionEvent touchevent) {

		switch (touchevent.getAction()) {
		
		
		case MotionEvent.ACTION_DOWN:

			lastX = touchevent.getX();

			break;

		case MotionEvent.ACTION_UP:

			float currentX = touchevent.getX();
			// Handling left to right screen swap.

			if (lastX < currentX) {

				// If there aren't any other children, just break.

				if (flipper.getDisplayedChild() == 0)

					break;

				// Next screen comes in from left.

				flipper.setInAnimation(this, R.anim.slide_in_from_left);

				// Current screen goes out from right.

				flipper.setOutAnimation(this, R.anim.slide_out_to_right);

				// Display next screen.

				flipper.showNext();

			}

			// Handling right to left screen swap.
			if (lastX > currentX) {
				// If there is a child (to the left), kust break.

				if (flipper.getDisplayedChild() == 1)

					break;

				// Next screen comes in from right.

				flipper.setInAnimation(this, R.anim.slide_in_from_right);

				// Current screen goes out from left.

				flipper.setOutAnimation(this, R.anim.slide_out_to_left);

				// Display previous screen.58
				flipper.showPrevious();

			}
			break;
		}
		return false;
	}
*/
	@Override
	public boolean onDoubleTap(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		 if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				flipper.setInAnimation(this, R.anim.slide_in_from_right);

				// Current screen goes out from left.

				flipper.setOutAnimation(this, R.anim.slide_out_to_left);
			    System.out.println("child index"+flipper.getChildCount());
				
			
				if (flipper.getCurrentView().getId() == R.id.textView_Theory_show_content) {
					title_theory.setText("Introduction");
					System.out.println("textView_Theory_show_content "+flipper.getDisplayedChild());
				}
				if (flipper.getCurrentView().getId() == R.id.textView_Theory_show_deeper) {
					title_theory.setText("Get More Details");
					System.out.println("textView_Theory_show_deeper "+flipper.getDisplayedChild());
				}
				if (flipper.getCurrentView().getId() == R.id.textView_Theory_show_look) {
					title_theory.setText("Also Take a look");
					System.out.println("textView_Theory_show_look "+flipper.getDisplayedChild());
				}
				if (flipper.getCurrentView().getId() == R.id.textView_Theory_show_summary) {
					title_theory.setText("What have you Learn");
					System.out.println("textView_Theory_show_summary "+flipper.getDisplayedChild());
				}
										// Display previous screen.58
					/*int index =	flipper.getDisplayedChild();
					System.out.println("Right index"+index);
					headingOfTutorial(index);*/
					
					int displayedChild = flipper.getDisplayedChild();
			        int childCount = flipper.getChildCount();

			        if (displayedChild == childCount - 1) {
			            flipper.stopFlipping();
			        }else{
					flipper.showNext();
					}
			        
					return true;	
		 }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				// Next screen comes in from left.

				flipper.setInAnimation(this, R.anim.slide_in_from_left);

				// Current screen goes out from right.

				flipper.setOutAnimation(this, R.anim.slide_out_to_right);
			
				
		if (flipper.getCurrentView().getId() == R.id.textView_Theory_show_content) {
			title_theory.setText("Introduction");
				System.out.println("textView_Theory_show_content " +flipper.getDisplayedChild());
			}
			if (flipper.getCurrentView().getId() == R.id.textView_Theory_show_deeper) {
				title_theory.setText("Get More Details");
				System.out.println("textView_Theory_show_deeper " +flipper.getDisplayedChild());
			}
			if (flipper.getCurrentView().getId() == R.id.textView_Theory_show_look) {
				title_theory.setText("Also Take a look");
				System.out.println("textView_Theory_show_look " +flipper.getDisplayedChild());
			}
			if (flipper.getCurrentView().getId() == R.id.textView_Theory_show_summary) {
				title_theory.setText("What have you Learn");
				System.out.println("textView_Theory_show_summary " +flipper.getDisplayedChild());
			}
			
				/*
				// Display next screen.
				int index =	flipper.getDisplayedChild();
				System.out.println("Left index"+index);
				headingOfTutorial(index); */
			int displayedChild = flipper.getDisplayedChild();
	        int childCount = flipper.getChildCount();

	        if (displayedChild == 0) {
	            flipper.stopFlipping();
	        }else{
	        	flipper.showPrevious();	
	        }
	     return true;   
		 } 
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	void headingOfTutorial(int index) {

		if(index == 0)
			title_theory.setText("Introduction");

		else if(index == 1)
			title_theory.setText("What have you Learn");

		else if(index == 2)
			title_theory.setText("Also Take a look");
		else
			title_theory.setText("Get More Details");
	}
	
	private void initTabs(ArrayList<String> content){
	    // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        mAdapter = new TabsPagerTheoryAdapter(getSupportFragmentManager(), content);
 
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
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
}
