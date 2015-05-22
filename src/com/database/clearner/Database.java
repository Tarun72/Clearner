package com.database.clearner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class Database extends SQLiteAssetHelper {
	
	private final static String DATABASE_NAME="techattip";
	private final static int DATABASE_VERSION=1;

	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	public String[] getNextData(int iD) {
		// TODO Auto-generated method stub
		
		SQLiteDatabase db= getReadableDatabase();
		String[] COL={"ID","QUESTION","OPT_A","OPT_B","OPT_C","OPT_D","CORRECT"};
		String TABLE="C_MCQ_Table";
		
		Cursor c= db.query(TABLE, COL, "ID="+ iD, null, null, null, null);
		c.moveToFirst();
		
		int id=c.getColumnIndex("ID");
		int question=c.getColumnIndex("QUESTION");
		int opta=c.getColumnIndex("OPT_A");
		int optb=c.getColumnIndex("OPT_B");
		int optc=c.getColumnIndex("OPT_C");
		int optd=c.getColumnIndex("OPT_D");
		int cor=c.getColumnIndex("CORRECT");
		
		String[] nik={c.getString(id),c.getString(question),c.getString(opta),
				c.getString(optb),c.getString(optc),c.getString(optd),c.getString(cor)};
		
		
		return nik;
	}
}
