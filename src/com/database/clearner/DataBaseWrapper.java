package com.database.clearner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseWrapper {// table column names
	static final String KEY_ID = "_id";
	static final String KEY_NAME = "name";
	static final String KEY_EMAIL = "email";
	static final String KEY_NUMBER = "number";

	// table information
	static final String DATABASE_NAME = "Usersss";
	static final String DATABASE_TABLE = "somethimg";
	static final int DATABASE_VERSION = 1;

	// variable required for context ,etc.
	final Context context;
	private  Dthelpq b;
	private  SQLiteDatabase ab;

	public DataBaseWrapper(Context ctx) {
		context = ctx;
	}

	private static class Dthelpq extends SQLiteOpenHelper {

		public Dthelpq(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		// creation of table
		public void onCreate(SQLiteDatabase ab) {
			ab.execSQL("create table " + DATABASE_TABLE + "(" + KEY_ID
					+ " integer primary key autoincrement, " + KEY_NAME
					+ " text not null," + KEY_EMAIL + " text not null,"
					+ KEY_NUMBER + " text not null);");
			System.out.println("Table is created");
		}

		/*
		 * @Override public void onCreate(SQLiteDatabase ab) {
		 * ab.execSQL("create table" + DATABASE_TABLE+ "(" + KEY_ID +
		 * " integer primary key , " + KEY_NAME + "text not null," + KEY_EMAIL +
		 * "text not null," + KEY_NUMBER + "text not null);" ); }
		 */
		// update the table and table version
		@Override
		public void onUpgrade(SQLiteDatabase ab, int oldversion, int newversion) {
			ab.execSQL("DROP IF TABLE EXITS" + DATABASE_TABLE);
			onCreate(ab);
		}

	}

	public DataBaseWrapper open() throws SQLException {
		b = new Dthelpq(context);
		ab = b.getWritableDatabase();
		System.out.println("getWritableDatabase(); is executed");
		return this;
	}

	public void close() {
		// TODO Auto-generated method stub
		b.close();
	}

	public long insertdata(String name, String email, String number) {

		System.out.println("Data is inserted");
		ContentValues cm = new ContentValues();
		cm.put(KEY_NAME, name);
		cm.put(KEY_EMAIL, email);
		cm.put(KEY_NUMBER, number);
		return ab.insert(DATABASE_TABLE, null, cm);
	}

	public Cursor getAllInfo() {
		// TODO Auto-generated method stub
		return ab.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_NAME,
				KEY_EMAIL, KEY_NUMBER }, null, null, null, null, null);
	}

	public Boolean deleteInfo(long rowId) {
		return ab.delete(DATABASE_TABLE, KEY_ID + "=" + rowId, null) > 0;
	}

	public Cursor getInfo(long rowId) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Data is fetched");
		Cursor mCursor = ab.query(DATABASE_TABLE, new String[] { KEY_ID,
				KEY_NAME, KEY_EMAIL, KEY_NUMBER }, KEY_ID + "=" + rowId, null,
				null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

}