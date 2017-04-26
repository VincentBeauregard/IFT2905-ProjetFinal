package projet.trashyv15;

import android.app.Application;

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

    @Override
    public void onTerminate() {
        mDBHelper.close();
        super.onTerminate();
    }
}
