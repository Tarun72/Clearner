package com.Evil.clearner;

import java.util.HashMap;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;

public class ClearnerApplication  extends Application{
	 // The following line should be changed to include the correct property id.
	  private static final String PROPERTY_ID = "UA-53890878-1";
	  /**
	   * Enum used to identify the tracker that needs to be used for tracking.
	   *
	   * A single tracker is usually enough for most purposes. In case you do need multiple trackers,
	   * storing them all in Application object helps ensure that they are created only once per
	   * application instance.
	   */
	  public enum TrackerName {
	    APP_TRACKER, // Tracker used only in this app.
	    GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
	    ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
	  }

	  HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

	public ClearnerApplication() {
		// TODO Auto-generated constructor stub
	super();
	}

	  synchronized Tracker getTracker(TrackerName trackerId) {
	    if (!mTrackers.containsKey(trackerId)) {

	      GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
	      Tracker t = (trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(PROPERTY_ID)
	          : (trackerId == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(R.xml.global_tracker)
	              : analytics.newTracker(R.xml.ecommerce_tracker);
	      mTrackers.put(trackerId, t);

	    }
	    return mTrackers.get(trackerId);
	  }
	  @Override
	    public void onCreate() {
	        super.onCreate();
	     // Enable Crash Reporting
	        ParseCrashReporting.enable(this);
	        
	        // Add your initialization code here
	        Parse.initialize(this, "TuhxnsZryiWFLu4nzYMaU1XOQuEP5huUCjYMFD1Q", "wx91nvxvxB9oJa1LmW59U3wA2niND2k8pYJyCpTM");	 
	        ParseUser.enableAutomaticUser();
	        ParseACL defaultACL = new ParseACL();
	        // If you would like all objects to be private by default, remove this
	        // line.
	        defaultACL.setPublicReadAccess(true);
	 
	        ParseACL.setDefaultACL(defaultACL, true);
	    }
}
