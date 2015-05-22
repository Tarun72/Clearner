package com.clearner.utills;

/*
 * *
 * *
 * *  Author ONGRAPH TECH.PVT LTD 
 * *
 * */
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Ongraph Class Save User Name , Image URl & Token
 * */
public class Preferences {

	private static SharedPreferences sharedPreferences;

	private static final String PREFERENCE_FILE = "Prefs";

	private static Preferences preference;

	private static final String NAME = "NAME";
	
	//*Save Tokens of Facebook , Twitter, Linkedin*//
	private static final String EMAIL = "EMAIL";

	private static final String Score = "Score";

	private static final String LNTOKEN = "LNTOKEN";

	private static final String PROFILE_PIC = "URL";
	
	private static final String ProviderId = "Id";
  
	/*Facbook, Linkedin, Twitter Profile information of User(name and image) */
	private static final String FBNAME = "FBNAME";

	private static final String FBIMAGEURL = "FBIMAGEURL";

	private static final String LNNAME = "LNNAME";

	private static final String LNIMAGEURL = "LNIMAGEURL";
	
	private static final String TWNAME = "TWNAME";

	private static final String TWIMAGEURL = "TWIMAGEURL";

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

		return sharedPreferences.getString(NAME, "");
	}

	public void setEmail(final String email) {
		System.out.println("The token" + email);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(EMAIL, email);
		editor.commit();
	}

	public String getEmail() {
		return sharedPreferences.getString(EMAIL, "");
	}

	public void setProfileUrl(final String url) {
		System.out.println("The URL" + url);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(PROFILE_PIC, url);
		editor.commit();
	}

	public String getProfileUrl() {
		return sharedPreferences.getString(PROFILE_PIC, null);
	}

	public void setLNToken(final String token) {
		System.out.println("The token" + token);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(LNTOKEN, token);
		editor.commit();
	}

	public String getLntoken() {
		return sharedPreferences.getString(LNTOKEN, "");
	}

	public void setScore(final String token) {
		System.out.println("The token" + token);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(Score, token);
		editor.commit();
	}

	public String getScore() {
		return sharedPreferences.getString(Score, "0");
	}

	public void setFbname(final String token) {
		System.out.println("The token" + token);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(FBNAME, token);
		editor.commit();
	}

	public String getFbname() {
		return sharedPreferences.getString(FBNAME, ""); 
	}

	public void setFbimageurl(final String token) {
		System.out.println("The token" + token);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(FBIMAGEURL, token);
		editor.commit();
	}

	public String getFbimageurl() {
		return sharedPreferences.getString(FBIMAGEURL, "");
	}

	public void setLnname(final String token) {
		System.out.println("The token" + token);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(LNNAME, token);
		editor.commit();
	}

	public String getLnname() {
		return sharedPreferences.getString(LNNAME, "");
	}

	public void setLnimageurl(final String token) {
		System.out.println("The token" + token);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(LNIMAGEURL, token);
		editor.commit();
	}

	public  String getLnimageurl() {
		return sharedPreferences.getString(LNIMAGEURL, "") ;
	}

	public void setTwname(final String token) {
		System.out.println("The token" + token);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(TWNAME, token);
		editor.commit();
	}

	public String getTwname() {
		return sharedPreferences.getString(TWNAME, "") ;
	}

	public void setTwimageurl(final String token) {
		System.out.println("The token" + token);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(TWIMAGEURL, token);
		editor.commit();
	}

	public String getTwimageurl() {
		return sharedPreferences.getString(TWIMAGEURL, "") ;
	}

	public void setProviderId(final String token) {
		System.out.println("The token" + token);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(ProviderId, token);
		editor.commit();
	}
	
	public String getProviderId() {
		return sharedPreferences.getString(ProviderId, "") ;
	}
}
