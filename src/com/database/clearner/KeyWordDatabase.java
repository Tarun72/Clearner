package com.database.clearner;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class KeyWordDatabase extends SQLiteAssetHelper {
	private final static String DATABASE_NAME = "techattip";
	private final static int DATABASE_VERSION = 1;

	public KeyWordDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public ArrayList<String> getData(String topic, String table) {

		SQLiteDatabase db = getReadableDatabase();
		String[] COL = { "id", "Keywordname", "summary" };
		String TABLE = table;

		Cursor c = db.rawQuery("SELECT id, Keywordname, summary FROM "
				+ TABLE + " WHERE Keywordname= '" + topic + "'", null);
		System.out.println("SELECT id, Keywordname, summary FROM "
				+ TABLE + " WHERE Keywordname= '" + topic + "'");
		c.moveToFirst();

		int id = c.getColumnIndex("id");
		// int top=c.getColumnIndex("TOPIC");
		int con = c.getColumnIndex("Keywordname");
		int summary = c.getColumnIndex("summary");
//		int quick = c.getColumnIndex("QUICKLOOK");
		String nik = c.getString(con);
		System.out.println("nik" + nik);
		ArrayList<String> Data = new ArrayList<String>();
		Data.add(c.getString(con));
		Data.add(c.getString(summary));
//		Data.add(c.getString(quick));

		return Data;
	}
}
