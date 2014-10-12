
package islamic.buzz.db.helper;

import islamic.buzz.app.BuzzApplication;
import islamic.buzz.util.Logger;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class will be used as a Database Helper for high level database
 * functions. i.e. Create Database, Create Tables, etc
 */
public final class DBHelper extends SQLiteOpenHelper {

    @SuppressWarnings("unused")
    private static final String TAG = DBHelper.class.getSimpleName();

    // Database name.
    private static final String DATABASE_NAME = "KohlsPhone.db";

    // Version of database
    private static final int DATABASE_VERSION = 1;

    private static DBHelper mInstance;

    private DBHelper() {
        super(BuzzApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
        Logger.debug(this.getClass().getSimpleName(), "Database Helper instantiated");
    }

    /**
     * Get instance of database helper.
     * 
     * @return instance of database helper
     */
    public static DBHelper getInstance() {
        if (mInstance == null) {
            mInstance = new DBHelper();
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // Creating Tables
        database.execSQL(DBQueries.CREATE_TABLE_SHOPPING_BAG.getQuery());
        database.execSQL(DBQueries.CREATE_TABLE_SEARCHED_PLACES.getQuery());
        database.execSQL(DBQueries.CREATE_TABLE_SEARCH_SUGGESTION.getQuery());
        database.execSQL(DBQueries.CREATE_TABLE_DICT_SEARCHWORDS.getQuery());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
            int oldVersion,
            int newVersion) {
        deleteTablesInUpgrade(db);
        onCreate(db);
    }

    private void deleteTablesInUpgrade(SQLiteDatabase db) {
        db.execSQL(DBQueries.DROP_TABLE_SHOPPING_BAG.getQuery());
        db.execSQL(DBQueries.DROP_TABLE_SEARCH_PLACES.getQuery());
        db.execSQL(DBQueries.DROP_TABLE_SEARCH_SUGGESTIONS.getQuery());
    }

}
