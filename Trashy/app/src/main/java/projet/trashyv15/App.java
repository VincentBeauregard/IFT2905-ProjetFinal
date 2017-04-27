package projet.trashyv15;

import android.app.Application;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class App extends Application {

    private static TrashyDBHelper mDBHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mDBHelper = new TrashyDBHelper(getApplicationContext());
    }

    public static TrashyDBHelper getDBHelper() {
        return mDBHelper;
    }

    public static long databasePutSchedule(String sWeekday,
                                           String sCycle,
                                           String sOnce,
                                           String sHourin,
                                           String sHourout,
                                           String sDatein,
                                           String sDateout,
                                           String type) {

        int weekday = Integer.parseInt(sWeekday);
        int cycle = Integer.parseInt(sCycle);
        boolean once = Boolean.parseBoolean(sOnce);
        int hourIn = Integer.parseInt(sHourin);
        int hourOut = Integer.parseInt(sHourout);

        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        String selection = TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_WEEKDAY  + " = ? AND " +
                           TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_CYCLE    + " = ? AND " +
                           TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_ONCE     + " = ? AND " +
                           TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_HOUR_IN  + " = ? AND " +
                           TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_HOUR_OUT + " = ? AND " +
                           TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_DATE_IN  + " = ? AND " +
                           TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_DATE_OUT + " = ? AND " +
                           TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_TYPE     + " = ?";
        String[] selectionArgs = { sWeekday, sCycle, sOnce, sHourin, sHourout, sDatein, sDateout, type };

        Cursor cursor = db.query(
                TrashyDBContract.TrashyDBTableSchedules.TABLE_NAME,
                null,
                selection, selectionArgs,
                null, null, null
        );

        // If no rows match, then we must add it to the database. Else we just print but we could update.
        long scheduleID = 0;
        if (cursor.getCount() == 0) {

            db = mDBHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_WEEKDAY, weekday);
            values.put(TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_CYCLE, cycle);
            values.put(TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_ONCE, once);
            values.put(TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_HOUR_IN, hourIn);
            values.put(TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_HOUR_OUT, hourOut);
            values.put(TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_DATE_IN, sDatein);
            values.put(TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_DATE_OUT, sDateout);
            values.put(TrashyDBContract.TrashyDBTableSchedules.COLUMN_NAME_TYPE, type);

            scheduleID = db.insert(TrashyDBContract.TrashyDBTableSchedules.TABLE_NAME, null, values);
        }
        else {
            System.out.println("Row already exists");
        }
        cursor.close();

        return scheduleID;
    }

    public static long databasePutNeighbourhood(String name, boolean isCurrent) {

        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        String selection = TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_NAME       + " = ? AND " +
                           TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_IS_CURRENT + " = ?";
        String[] selectionArgs = { name, isCurrent ? "1" : "0" };

        Cursor cursor = db.query(
                TrashyDBContract.TrashyDBTableNeighbourhoods.TABLE_NAME,
                null,
                selection, selectionArgs,
                null, null, null
        );

        // If no rows match, then we must add it to the database. Else we just print but we could update.
        long neighbourdhoodID = 0;
        if (cursor.getCount() == 0) {

            db = mDBHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_NAME, name);
            values.put(TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_IS_CURRENT, isCurrent);

            neighbourdhoodID = db.insert(TrashyDBContract.TrashyDBTableNeighbourhoods.TABLE_NAME, null, values);
        }
        else {
            System.out.println("Row already exists");
        }
        cursor.close();

        return neighbourdhoodID;
    }

    public static void databaseLinkNeighbourNamehoodAndSchedule(String neighbourhoodName, long scheduleID) {

        // Get the corresponding neighbourhood
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String[] projection = {
                TrashyDBContract.TrashyDBTableNeighbourhoods._ID,
                TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_NAME
        };
        String selection = TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_NAME + " = ?";
        String[] selectionArgs = { neighbourhoodName };

        Cursor cursor = db.query(
                TrashyDBContract.TrashyDBTableNeighbourhoods.TABLE_NAME,
                projection, selection, selectionArgs,
                null, null, null
        );

        if (cursor.getCount() < 1) {
            System.out.println("No matching neighbourhood with name '" + neighbourhoodName + "' in database");
        }
        else {

            cursor.moveToNext();
            long neighbourhoodID = cursor.getLong(cursor.getColumnIndexOrThrow(
                    TrashyDBContract.TrashyDBTableNeighbourhoods._ID
            ));

            databaseLinkNeighbourhoodAndSchedule(neighbourhoodID, scheduleID);
        }
        cursor.close();
    }

    public static void databaseLinkNeighbourhoodAndSchedule(long neighbourhoodID, long scheduleID) {

        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TrashyDBContract.TrashyDBTableNeighbourhoodHasSchedule.COLUMN_NAME_NEIGHBOURHOOD_ID, neighbourhoodID);
        values.put(TrashyDBContract.TrashyDBTableNeighbourhoodHasSchedule.COLUMN_NAME_SCHEDULE_ID, scheduleID);

        db.insert(TrashyDBContract.TrashyDBTableNeighbourhoodHasSchedule.TABLE_NAME, null, values);
    }

    @Override
    public void onTerminate() {
        mDBHelper.close();
        super.onTerminate();
    }
}
