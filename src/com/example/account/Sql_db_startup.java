package com.example.account;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Sql_db_startup 
{
	public static final String FIRST_NAME="first_name";
	public static final String LAST_NAME="last_name";
	public static final String USER_NAME="user_name";
	public static final String USER_TYPE="user_type";
	public static final String PWD="password";
	
	public static final String KEY_ROWID = "_id";
	public static final String ACC_NAME="account_name";
	public static final String ACC_NUMBER ="account_number";
	public static final String ACC_ORGNAME = "account_orgname";
	public static final String BALANCE = "account_balance";
	
	
	private static final String DATABASE_NAME = "qf_db";
	private static final String DATABASE_TABLE = "users";
	private static final String DATABASE_TABLE2 = "account";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper userHelper;
	private final Context userContext;
	private SQLiteDatabase userDatabase;
	
	private String first_name;
	private String last_name;
	private String user_name;
	private int user_type;
	private String password;
	
	
	private static class DbHelper extends SQLiteOpenHelper
	{
		public DbHelper(Context context) {
			super(context, DATABASE_NAME,null,DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			
			//Create all the table that in the app
			
			//Creating user table
			db.execSQL("CREATE TABLE "+ DATABASE_TABLE + "("  +
					FIRST_NAME + " TEXT, " +
					LAST_NAME + " TEXT, " +
					USER_NAME + " TEXT, " +
					USER_TYPE + " INTEGER, " +
					PWD + " TEXT " + ")"
		    );
			
			//Creating Account table
			db.execSQL("CREATE TABLE "+ DATABASE_TABLE2 + "(" 
					+ KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					ACC_NAME + " TEXT, " +
					ACC_NUMBER + " INTEGER, " +
					ACC_ORGNAME + " TEXT, " +
					BALANCE + " INTEGER" + ")"
		    );
			
			
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);
			onCreate(db);
			
		}		
	}
	
	public Sql_db_startup(Context c)
	{
		userContext = c;
	}
	
	public Sql_db_startup open() throws SQLException
	{
		userHelper = new DbHelper(userContext);
		userDatabase = userHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		userHelper.close();
	}
	
	public long createEntry(String db_first_name, String db_last_name, String db_user_name,int db_user_type, String db_password) 
	{
		
		ContentValues cv = new ContentValues();
		cv.put(FIRST_NAME, db_first_name);
		cv.put(LAST_NAME, db_last_name);
		cv.put(USER_NAME, db_user_name);
		cv.put(USER_TYPE, db_user_type);
		cv.put(PWD, db_password);
		return userDatabase.insert(DATABASE_TABLE, null, cv);
		
	}
	
	public void getData() 
	{	
		String[] columns = new String[] {FIRST_NAME,LAST_NAME,USER_NAME,USER_TYPE,PWD};
		Cursor c = userDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		
		int iFirst_name = c.getColumnIndex(FIRST_NAME);
		int iLast_number = c.getColumnIndex(LAST_NAME);
		int iUser_name = c.getColumnIndex(USER_NAME);
		int iUser_type = c.getColumnIndex(USER_TYPE);
		int iPwd = c.getColumnIndex(PWD);
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
			setFirst_Name(c.getString(iFirst_name));
			setLast_Name(c.getString(iLast_number));
			setUser_Name(c.getString(iUser_name));
			setUser_Type(c.getInt(iUser_type));
			setPwd(c.getString(iPwd));	
	    }
	}
	
	
	public String getFirst_Name() {
		return first_name;
	}

	public void setFirst_Name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_Name() {
		return last_name;
	}

	public void setLast_Name(String last_name) {
		this.last_name = last_name;
	}

	public String getUser_Name() {
		return user_name;
	}

	public void setUser_Name(String user_name) {
		this.user_name = user_name;
	}
	
	public int getUser_Type() {
		return user_type;
	}

	public void setUser_Type(int user_type) {
		this.user_type = user_type;
	}

	public String getPassword() {
		return password;
	}

	public void setPwd(String password) {
		this.password = password;
	}

}
