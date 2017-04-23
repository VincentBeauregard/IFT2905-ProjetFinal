package projet.trashyv15;

import android.provider.BaseColumns;

public final class TrashyDBContract {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "trashy_local.db";

    private static final String TYPE_INTEGER       = " INT";
    private static final String TYPE_BOOL          = " TINYINT(1)";
    private static final String TYPE_DATE          = " DATE";
    private static final String TYPE_DATETIME      = " DATETIME";
    private static final String TYPE_TEXTNAME      = " VARCHAR(100)";
    private static final String TYPE_TEXTDESC      = " VARCHAR(512)";
    private static final String TYPE_POINT         = " POINT";
    private static final String TYPE_POLYGON       = " POLYGON";

    // The constructor is set private to prevent someone from accidentally instantiating the Contract.
    private TrashyDBContract() {}

    // Every following internal class represents a table in the database, with each internal string
    // referring to the name of the table or the name of the column in the table.
    public static class TrashyDBTableSchedules implements BaseColumns {
        public static final String TABLE_NAME = "schedules";
        public static final String COLUMN_NAME_WEEKDAY = "weekday";
        public static final String COLUMN_NAME_CYCLE = "cycle";
        public static final String COLUMN_NAME_ONCE = "once";
        public static final String COLUMN_NAME_HOUR_IN = "hourin";
        public static final String COLUMN_NAME_HOUR_OUT = "hourout";
        public static final String COLUMN_NAME_DATE_IN = "datein";
        public static final String COLUMN_NAME_DATE_OUT = "dateout";
        public static final String COLUMN_NAME_EXPIRES = "expires";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_WEEKDAY + TYPE_INTEGER + " NOT NULL," +
                COLUMN_NAME_CYCLE + TYPE_INTEGER + " NOT NULL DEFAULT 1," +
                COLUMN_NAME_ONCE + TYPE_BOOL + " NOT NULL DEFAULT 0," +
                COLUMN_NAME_HOUR_IN + TYPE_INTEGER + " NOT NULL," +
                COLUMN_NAME_HOUR_OUT + TYPE_INTEGER + " NOT NULL," +
                COLUMN_NAME_DATE_IN + TYPE_DATE + " NOT NULL," +
                COLUMN_NAME_DATE_OUT + TYPE_DATE + " NOT NULL," +
                COLUMN_NAME_EXPIRES + TYPE_DATETIME + " NULL )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class TrashyDBTableNeighbourhoods implements BaseColumns {
        public static final String TABLE_NAME = "neighbourhoods";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_IS_CURRENT = "iscurrent";
        public static final String COLUMN_NAME_ZONE = "zone";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_NAME + TYPE_TEXTNAME + " NOT NULL," +
                COLUMN_NAME_IS_CURRENT + TYPE_BOOL + " NOT NULL DEFAULT 0," +
                COLUMN_NAME_ZONE + TYPE_POLYGON + " NULL )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class TrashyDBTableCalendarEvents implements BaseColumns {
        public static final String TABLE_NAME = "calendarevents";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESC = "desc";
        public static final String COLUMN_NAME_SCHEDULE_ID = "schedule_id";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_NAME + TYPE_TEXTNAME + " NOT NULL," +
                COLUMN_NAME_DESC + TYPE_TEXTDESC + " NULL," +
                COLUMN_NAME_SCHEDULE_ID + TYPE_INTEGER + " NOT NULL," +
                "FOREIGN KEY (" + COLUMN_NAME_SCHEDULE_ID + ") REFERENCES " +
                    TrashyDBTableSchedules.TABLE_NAME + "(" + TrashyDBTableSchedules._ID + ") " +
                    "ON UPDATE NO ACTION " +
                    "ON DELETE NO ACTION )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class TrashyDBTableEcocenters implements BaseColumns {
        public static final String TABLE_NAME = "ecocenters";
        public static final String COLUMN_NAME_LOCATION = "location";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_LOCATION + TYPE_POINT + " NOT NULL)";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class TrashyDBTableNeighbourhoodHasSchedule {
        public static final String TABLE_NAME = "neighbourhood_has_schedule";
        public static final String COLUMN_NAME_NEIGHBOURHOOD_ID = "neighbourhood_id";
        public static final String COLUMN_NAME_SCHEDULE_ID = "schedule_id";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                COLUMN_NAME_NEIGHBOURHOOD_ID + TYPE_INTEGER + " NOT NULL," +
                COLUMN_NAME_SCHEDULE_ID + TYPE_INTEGER + " NOT NULL," +
                "FOREIGN KEY (" + COLUMN_NAME_NEIGHBOURHOOD_ID + ") REFERENCES " +
                    TrashyDBTableNeighbourhoods.TABLE_NAME + "(" + TrashyDBTableNeighbourhoods._ID + ") " +
                    "ON UPDATE NO ACTION " +
                    "ON DELETE NO ACTION, " +
                "FOREIGN KEY (" + COLUMN_NAME_SCHEDULE_ID + ") REFERENCES " +
                    TrashyDBTableSchedules.TABLE_NAME + "(" + TrashyDBTableSchedules._ID + ") " +
                    "ON UPDATE NO ACTION " +
                    "ON DELETE NO ACTION )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
