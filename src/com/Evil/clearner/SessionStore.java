package com.Evil.clearner;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionStore {

    public static final String INSTRUCTIONCHECKED = "checkedid";
	private static String TEMP = "key";

    public static final String INSTRUCTIONCHECKEDDELETE = "checkediddelete";

	    public static boolean saveInstructioncheck(Context context,String userName) {
	    	System.out.println("In Session Store Class");
	    	System.out.println("saveInstructioncheck()Method and context,username is parameter"+userName);
	    	Editor editor = context.getSharedPreferences(TEMP, Context.MODE_PRIVATE).edit();
	        editor.putString(INSTRUCTIONCHECKED, userName);
	        return editor.commit();
	    }
	    public static String getInstructioncheck(Context context){

	    	System.out.println("get instructions method");
	    	SharedPreferences savedSession =
	                context.getSharedPreferences(TEMP, Context.MODE_PRIVATE);
	    	System.out.println("The End of Class");
	    	System.out.println("Performing similar as demanded");
	    	return savedSession.getString(INSTRUCTIONCHECKED, null);
	    	
	    }
}