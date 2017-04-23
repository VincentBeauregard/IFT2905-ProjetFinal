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
        for (String statement : TrashyDBContract.SQL_CREATE_TABLE_ARRAY) {
            database.execSQL(statement);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        for (String statement : TrashyDBContract.SQL_DELETE_TABLE_ARRAY) {
            database.execSQL(statement);
        }
        onCreate(database);
    }
}
