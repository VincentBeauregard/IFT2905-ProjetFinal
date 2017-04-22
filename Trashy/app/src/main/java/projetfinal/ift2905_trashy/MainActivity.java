package projetfinal.ift2905_trashy;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StyleRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout navDrawerLayout;
    private ActionBarDrawerToggle navToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainTxtReduct.redux();
        Button trash = (Button) findViewById(R.id.trash);
        Button recycle = (Button) findViewById(R.id.recycle);
        Button compost = (Button) findViewById(R.id.compost);

        navDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navToggle = new ActionBarDrawerToggle(this, navDrawerLayout, R.string.open, R.string.close);

        navDrawerLayout.addDrawerListener(navToggle);
        navToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }



    //si on clique sur le menu, il va s'ouvrir
    public boolean onOptionsItemSelected(MenuItem item){

        if(navToggle.onOptionsItemSelected(item)){
            return true;

        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){


        switch (item.getItemId()){
            case R.id.nav_home:
                startActivity(new Intent());
                return true;
            case R.id.nav_calendar:
                startActivity(new Intent(getApplicationContext(), Calendar.class));
                return true;
            case R.id.nav_map:
                return true;
            case R.id.nav_ecocenter:
                return true;
            case R.id.nav_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            case R.id.nav_about:
                return true;
        }

        navDrawerLayout.closeDrawer(GravityCompat.START);

        return true;

    }


}
