package com.database.clearner;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

	private static SharedPreferences sharedPreferences;

	private static final String PREFERENCE_FILE = "Prefs";

	private static Preferences preference;

	private static final String NAME = "NAME";
	
	private static final String EMAIL = "EMAIL"; 

	private static final String SCORE = "SCORE";		
 
	private static final String QUOTES = "QUOTES";
	
	private static final String FOLDER = "Folder";

	public static Preferences getInstance(final Context context) {
		if (preference == null) {
			preference = new Preferences();
		}
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE,
					Context.MODE_PRIVATE);
		}

		return preference;
	}

	public void setName(final String name) {
		System.out.println("Set Default fuel >> " + name);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(NAME, name);
		editor.commit();
	}

	public String getName() {

		return sharedPreferences.getString(NAME, "  ");
	}

	public void setScore(final String Score){

		System.out.println("Set Default fuel >> " + Score);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(SCORE, Score);
		editor.commit();
	}
	public  String getScore() {
		return sharedPreferences.getString(SCORE, "50");
	}

	public void setEmail(final String email) {
		System.out.println("Set Default fuel >> " + email);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(EMAIL, email);
		editor.commit();
	}

	public String getEmail() {

		return sharedPreferences.getString(EMAIL, " ");
	}
	public void setQuote(final String quote) {
		System.out.println("Set Default fuel >> " + quote);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(QUOTES, quote);
		editor.commit();
	}

	public String getQuote() {

		return sharedPreferences.getString(QUOTES, " ");
	}
		
	public void setFolder(final String Exits){
		System.out.println("Set Default fuel >> " + Exits);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(FOLDER, Exits);
		editor.commit();
	}	 
	
	public String getFolder(){
		return sharedPreferences.getString(FOLDER, "null");
	}
	
}
