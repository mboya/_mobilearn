package blackwidow.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {
	// this is stating the database name and version
	private static final String DB_NAME = "county_field_mobile";
	private static final int DB_VERSION = 1;

	// this are the variable to handle the table for users and it s columns
	private static final String TBL_USER = "user";
	private static final String KEY_ID = "user_id";
	private static final String KEY_NAME = "user_name";

	// constructor for the class
	public DbHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	/**
	 * this is the instance where my tables within the slite database are created
	 * this happens when the application is initialized
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_USER = "CREATE TABLE " + TBL_USER + " ("
				+ KEY_ID + " INTEGER,"
				+ KEY_NAME + " TEXT"
				+ " )";
		db.execSQL(CREATE_USER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TBL_USER);
		db.close();
	}

	/**
	 * this will add a new user into the database during logins
	 */
	public void loginUser(int _id, String name){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, _id);
		values.put(KEY_NAME, name);
		db.insert(TBL_USER, null, null);
		db.close();
	}

	/**
	 * getting the user login status
	 * it looks for count to determine login or not
	 */
	public int getRowCount(){
		String countQuery = "SELECT * FROM " + TBL_USER;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();
		return rowCount;
	}

}
