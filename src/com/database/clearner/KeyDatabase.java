package com.database.clearner;

import java.util.ArrayList;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class KeyDatabase extends SQLiteAssetHelper{

	private final static String DATABASE_NAME = "techattip";
	private final static int DATABASE_VERSION = 1;
	
	 public KeyDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	
	public ArrayList<String> getData(String topic,String table) {
		// TODO Auto-generated method stub
		
		SQLiteDatabase db= getReadableDatabase();
		String[] COL={"ID","MEANING", "SUMMARY","QUICKLOOK"};
		String TABLE=table;
		
		
		Cursor c= db.rawQuery("SELECT ID, MEANING, SUMMARY, QUICKLOOK FROM "+TABLE+" WHERE KEYWORDS= '"+topic+"'", null);
		c.moveToFirst();
		
		int id = c.getColumnIndex("ID");
		//int top=c.getColumnIndex("TOPIC");
		int con = c.getColumnIndex("MEANING");
		int summary = c.getColumnIndex("SUMMARY");
		int quick = c.getColumnIndex("QUICKLOOK");
		String nik = c.getString(con);
		System.out.println("nik"+nik);
		ArrayList<String> Data = new ArrayList<String>();
		Data.add( c.getString(con));
		Data.add( c.getString(summary));
		Data.add(c.getString(quick));
		
		return Data;
	}
}

