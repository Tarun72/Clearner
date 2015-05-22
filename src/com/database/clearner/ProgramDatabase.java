/*package com.Evil.clearner;

import java.util.Iterator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class ProgramDatabase extends SQLiteAssetHelper{

	private final static String DATABASE_NAME="techattip";
	private final static int DATABASE_VERSION= 1;

	public ProgramDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		// TODO Auto-generated constructor stub
	}

	
        
	
	public String getTopicListData(int j,String table) {
		// TODO Auto-generated method stub
		//String[] nik= null;
		int i=0;
		SQLiteDatabase db= getReadableDatabase();
	    String[] COL={"TOPIC"};
		String TABLE=table;
		System.out.println("table name"+table+j);		
		int idd= j+1;
		Cursor c= db.rawQuery("SELECT ID, CONTENT FROM "+TABLE+" WHERE ID= '"+idd+"'", null);
		
    /*		Cursor c= db.rawQuery("Select * from "+ TABLE, null);
		c.moveToFirst();
     */
		/*String Qurey="SELECT ID, CONTENT FROM "+TABLE+" WHERE ID= '"+idd+"'";
		System.out.println("Qurey passed"+Qurey);
		System.out.println("Working");*/
	/*	for(;!c.isAfterLast();c.moveToNext()){
		*	String toinsert;*/
 /*        int id=c.getColumnIndex("ID");
		System.out.println("Working id"+id);
		
		int top=c.getColumnIndex("TOPIC");
		System.out.println("Working topic"+top);
		
		int con=c.getColumnIndex("CONTENT");
		System.out.println("Working content"+con);
		
		String nik=c.getString(top);
		System.out.println(""+nik);
/*		
			int in= c.getColumnIndex("TOPIC");
			toinsert = c.getString(in);
			nik[i]= toinsert;
			i++;
		}*/
/*		
		return nik;
	}
 

}*/package com.database.clearner;

import java.util.Iterator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class ProgramDatabase extends SQLiteAssetHelper{

	private final static String DATABASE_NAME="techattip";
	private final static int DATABASE_VERSION= 1;

	public ProgramDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	
        
	
	public String getData(int j,String table ,boolean get_code) {
		
		System.out.println("in get data class");
		SQLiteDatabase db= getReadableDatabase();
		String TABLE=table;
		System.out.println("table name "+table + "ID is " + j);
		
		Cursor c= db.rawQuery("SELECT ID, TOPIC, CONTENT,OUTPUT FROM "+TABLE+" WHERE ID= '"+j+"'", null);
		c.moveToFirst();
		int index= c.getColumnIndex("CONTENT");
		int output = c.getColumnIndex("OUTPUT");
		System.out.println("DATA data is : " + c.getString(index));
		System.out.println("Output"+c.getString(output));
		if(get_code)
		return c.getString(index);
		else
		return c.getString(output);
 	}
 

}

