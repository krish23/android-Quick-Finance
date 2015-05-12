package com.example.account;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Sql_db {

	public static final String KEY_ROWID = "_id";
	public static final String ACC_NAME="account_name";
	public static final String ACC_NUMBER ="account_number";
	public static final String ACC_ORGNAME = "account_orgname";
	public static final String BALANCE = "account_balance";
	
	private static final String DATABASE_NAME = "qf_db";
	private static final String DATABASE_TABLE = "account";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper accHelper;
	private final Context accContext;
	private SQLiteDatabase accDatabase;
	
	private int account_number;
	private String account_name;
	private String accont_orgname;
	private int balance;

	private static class DbHelper extends SQLiteOpenHelper
	{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME,null,DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);
			onCreate(db);
			
		}
	}
	
	public Sql_db(Context c)
	{
		accContext = c;
	}
	
	public Sql_db open() throws SQLException
	{
		accHelper = new DbHelper(accContext);
		accDatabase = accHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		accHelper.close();
	}

	public long createEntry(String db_account_name,
			String db_account_number, String db_account_balance,String db_orgName) 
	{
		
		ContentValues cv = new ContentValues();
		cv.put(ACC_NAME, db_account_name);
		cv.put(ACC_NUMBER, db_account_number);
		cv.put(ACC_ORGNAME, db_orgName);
		cv.put(BALANCE, db_account_balance);
		return accDatabase.insert(DATABASE_TABLE, null, cv);
		
	}

	public void getData() 
	{	
		String[] columns = new String[] {ACC_NAME,ACC_NUMBER,ACC_ORGNAME,BALANCE};
		Cursor c = accDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iAcc_name = c.getColumnIndex(ACC_NAME);
		int iAcc_number = c.getColumnIndex(ACC_NUMBER);
		int iBalance = c.getColumnIndex(BALANCE);
		int iAcc_orgname = c.getColumnIndex(ACC_ORGNAME);
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
			setAccount_number(c.getInt(iAcc_number));
			setAccount_name(c.getString(iAcc_name));
			setAccont_orgname(c.getString(iAcc_orgname));
			setBalance(c.getInt(iBalance));	
	    }
	}
	
	public int getsize()
	{
		String[] columns = new String[] {ACC_NAME,ACC_NUMBER,ACC_ORGNAME,BALANCE};
		Cursor c = accDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iAcc_name = c.getColumnIndex(ACC_NAME);
		int iAcc_number = c.getColumnIndex(ACC_NUMBER);
		int iBalance = c.getColumnIndex(BALANCE);
		int iAcc_orgname = c.getColumnIndex(ACC_ORGNAME);
		int count=0;
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
			count=count+1;
	    }
		int count2=count;
		count=0;
		return count2;
	}
	
	public String getAccname(long L)
	{
		String[] columns = new String[] {KEY_ROWID, ACC_NAME, ACC_NUMBER, ACC_ORGNAME,BALANCE};
		Cursor c = accDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + L, null, null, null, null);
			
		if(c != null)
		{
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}
	public String getAccbalance(long L)
	{
		String[] columns = new String[] {KEY_ROWID, ACC_NAME, ACC_NUMBER, ACC_ORGNAME,BALANCE};
		Cursor c = accDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + L, null, null, null, null);
		if(c != null)
		{
			c.moveToFirst();
			String income = c.getString(4);
			return income;
		}
		return null;
	}
	public String Accnumber(long L)
	{
		String[] columns = new String[] {KEY_ROWID, ACC_NAME, ACC_NUMBER, ACC_ORGNAME,BALANCE};
		Cursor c = accDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + L, null, null, null, null);
		if(c != null)
		{
			c.moveToFirst();
			String number = c.getString(2);
			return number;
		}
		return null;
	}
	public String AccOrgname(long L)
	{
		String[] columns = new String[] {KEY_ROWID, ACC_NAME, ACC_NUMBER, ACC_ORGNAME,BALANCE};
		Cursor c = accDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + L, null, null, null, null);
		if(c != null)
		{
			c.moveToFirst();
			String number = c.getString(3);
			return number;
		}
		return null;
	}
	
	public void updateEntry(long lRow, String aName,
			String aNum, String aBal,String aOname) 
	{
		
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(ACC_NAME, aName);
		cvUpdate.put(ACC_NUMBER, aNum);
		cvUpdate.put(BALANCE, aBal);
		cvUpdate.put(ACC_ORGNAME, aOname);
		
		accDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
		
	}

	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccont_orgname() {
		return accont_orgname;
	}

	public void setAccont_orgname(String accont_orgname) {
		this.accont_orgname = accont_orgname;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
