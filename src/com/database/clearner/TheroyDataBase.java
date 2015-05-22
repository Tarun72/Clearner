package com.database.clearner;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class TheroyDataBase extends SQLiteAssetHelper{

	private final static String DATABASE_NAME="techattip";
	private final static int DATABASE_VERSION= 1;
	
           public TheroyDataBase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	
	public ArrayList<String> getData(int topic,String table) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		SQLiteDatabase db= getReadableDatabase();
		String[] COL={"ID","HEADING","CONTENT","DEEPER","LOOK","SUMMARY"};
		String TABLE=table;
		int idd= topic+1;
		Cursor c= db.rawQuery("SELECT ID, HEADING, CONTENT, DEEPER, LOOK, SUMMARY FROM C_THEORY_NEW WHERE ID= '"+idd+"'", null);
		c.moveToFirst();
		
		//HEADING 1  CONTENT 2  DEEPER 3 LOOK 4  SUMMARY 5

		
		int id=c.getColumnIndex("ID");
		
		//int top=c.getColumnIndex("TOPIC");

		int hed=c.getColumnIndex("HEADING");
		
		int con=c.getColumnIndex("CONTENT");
		
		int deep = c.getColumnIndex("DEEPER");
		
		int look = c.getColumnIndex("LOOK");
		
		int summ = c.getColumnIndex("SUMMARY");
		
		String heading = c.getString(hed);
				
		String nik=c.getString(con);


		String deeper =  c.getString(deep);

		String takelook = c.getString(look);

		
		String summary = c.getString(summ);

		/*System.out.println("column CONTENT"+con);

		System.out.println("column CONTENT"+nik);

		System.out.println(" column DEEPER "+deep);

		System.out.println(" column DEEPER "+deeper);

		System.out.println(" column LOOK "+look);
		System.out.println(" column LOOK "+takelook);

		System.out.println(" column summary "+summ);
		System.out.println(" column summary "+summary);
		System.out.println(" column SUMMARY "+summ);
		System.out.println(" column LOOK "+look);
		System.out.println(" column DEEPER "+deep);
		System.out.println(" column CONTENT "+con);
		System.out.println("column HEADING"+hed);
		
		System.out.println("column HEADING"+hed);

		System.out.println("column HEADING"+heading);

*/		
		//HEADING 1  CONTENT 2  DEEPER 3 LOOK 4  SUMMARY 5

		// 
		list.add(nik); // CONTENT 
		list.add(deeper);// DEEPER
		list.add(takelook);// LOOK
		list.add(summary);//SUMMARY
		list.add(heading);//HEADING
		return list;
	}
 

}
