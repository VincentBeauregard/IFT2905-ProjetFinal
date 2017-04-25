package projet.trashyv15;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public TrashyDBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Montre le menu1 lorsque l'activite est chargee
        displaySelectedScreen(R.id.nav_home);

        // Create the database handler, which is closed when this activity is closed.
        mDBHelper = new TrashyDBHelper(getApplicationContext());

        // Load the database. If the database doesn't exists, it will be created at this point so this
        // operation may be 'expensive'. We do this so we can determine if the user has opened the
        // application before. If they didn't, then the database is going to be empty or every neighbourhood
        // in the database is going to be deselected as the active neighbourhood.
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        String[] projection = {
                TrashyDBContract.TrashyDBTableNeighbourhoods._ID,
                TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_IS_CURRENT
        };

        Cursor cursor = db.query(
                TrashyDBContract.TrashyDBTableNeighbourhoods.TABLE_NAME,
                projection,
                null, null,
                null, null,
                null,
                null
        );

        boolean hasCurrentNeighbourhood = false;
        while (cursor.moveToNext()) {

            boolean isCurrent = cursor.getInt(cursor.getColumnIndexOrThrow(
                    TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_IS_CURRENT
            )) != 0;

            // If the used marked one of the neighbourhoods in the database as their current
            // neighbourhood, then we break out of the loop since we just wanted to detect if
            // the user had used the application before (and has therefore already set a neighbourhood).
            if (isCurrent) {
                hasCurrentNeighbourhood = true;
                break;
            }
        }
        cursor.close();

        if (!hasCurrentNeighbourhood) {
            System.out.println("User has no associated neighbourhood in database!!!!!!!");
        }
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will automatically handle clicks on
        // the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        // Creating fragment object
        Fragment fragment = null;

        // Initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_map:
                fragment = new MapsFragment();
                break;
            case R.id.nav_calendar:
                fragment = new CalendarFragment();
                break;
            case R.id.nav_ecocenter:
                //fragment = new EcoFragment();
                break;
            case R.id.nav_about:
                //fragment = new AboutFragment();
                break;
        }

        // Replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onDestroy() {

        mDBHelper.close();
        super.onDestroy();
    }
}
