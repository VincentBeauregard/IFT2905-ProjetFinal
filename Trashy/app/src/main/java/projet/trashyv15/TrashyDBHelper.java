package projet.trashyv15;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TrashyDBHelper extends SQLiteOpenHelper {

    public TrashyDBHelper(Context context) {
        super(context, TrashyDBContract.DATABASE_NAME, null, TrashyDBContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        System.out.println("Creating database...");

        for (String statement : TrashyDBContract.SQL_CREATE_TABLE_ARRAY) {

            System.out.println("[SQL] " + statement);
            database.execSQL(statement);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        System.out.println("Changing database version from " + oldVersion + " to " + newVersion);

        // When the database gets updated, we simply delete the old one as it is only a cache for
        // online data (for the most part). The only thing that is not already online is the current
        // preferences of the user, but for now it's not a concern if they get reset.
        for (String statement : TrashyDBContract.SQL_DELETE_TABLE_ARRAY) {

            System.out.println("[SQL] " + statement);
            database.execSQL(statement);
        }
        onCreate(database);
    }

    @Override
    public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        onUpgrade(database, oldVersion, newVersion);
    }
}
