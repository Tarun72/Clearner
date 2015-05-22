package com.Evil.clearner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class InterviewQuesDatabase extends SQLiteAssetHelper {
	
	private final static String DATABASE_NAME="techattip";
	private final static int DATABASE_VERSION=1;

	public InterviewQuesDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	public String[] getNextData(int iD) {
		// TODO Auto-generated method stub
		SQLiteDatabase db= getReadableDatabase();
		String[] COL={"ID","QUESTION","ANSWER"};
		String TABLE="C_Interview_Questions_Table";
		
		Cursor c= db.query(TABLE, COL, "ID="+ iD, null, null, null, null);
		c.moveToFirst();
		
		int id=c.getColumnIndex("ID");
		int question=c.getColumnIndex("QUESTION");
		int ans=c.getColumnIndex("ANSWER");
		
		System.out.println(c.getString(question) +" "+ c.getString(ans));
		
		String[] nik={c.getString(id)+". \t"+c.getString(question),c.getString(ans)};
		
		return nik;
	}
	
	
}
