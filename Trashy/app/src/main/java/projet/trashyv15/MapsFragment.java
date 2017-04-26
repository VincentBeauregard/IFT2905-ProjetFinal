package projet.trashyv15;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private Button accedCarte;
    private View view;

    //variables pour la carte
    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private ZoomControls zoom;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_maps, container, false);
        accedCarte = (Button) view.findViewById(R.id.accedCarte);

        accedCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bouton de localisation
            }
        });

        /*pos du array:
        0- Ahuntsic-Cartierville
        1- Anjou
        2- Côte-des-Neiges–Notre-Dame-de-Grâce
        3- Lachine
        4- LaSalle
        5- Le Plateau-Mont-Royal
        6- Le Sud-Ouest
        7- L’Île-Bizard–Sainte-Geneviève
        8- Mercier–Hochelaga-Maisonneuve
        9- Montréal-Nord
        10- Outremont
        11- Pierrefonds-Roxboro
        12- Rivière-des-Prairies–Pointe-aux-Trembles
        13- Rosemont–La Petite-Patrie
        14- Saint-Laurent
        15- Saint-Léonard
        16- Verdun
        17- Ville-Marie
        18- Villeray–Saint-Michel–Parc-Extension
         */

        Spinner spinner = (Spinner)view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TrashyDBHelper dbHelper = App.getDBHelper();
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String item = parent.getItemAtPosition(position).toString();

                // Execute UPDATE 'neighbourhoods' SET iscurrent = 'FALSE'  WHERE iscurrent = 'TRUE';
                ContentValues values = new ContentValues();
                values.put(TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_IS_CURRENT, 0);
                String selection = TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_IS_CURRENT + " LIKE ?";
                String[] selectionArgs = { "1" };

                int count = db.update(
                        TrashyDBContract.TrashyDBTableNeighbourhoods.TABLE_NAME,
                        values, selection, selectionArgs
                );

                if (count != 1) System.out.println("(1) Updated an incorrect number of rows (" + count + ")");
                else            System.out.println("(1) Updated old neighbourhood in database");

                // Execute UPDATE 'neighbourhoods' SET iscurrent = 'TRUE'  WHERE name = [item];
                values = new ContentValues();
                values.put(TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_IS_CURRENT, 1);
                String selection2 = TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_NAME + " LIKE ?";
                String[] selectionArgs2 = { item };

                count = db.update(
                        TrashyDBContract.TrashyDBTableNeighbourhoods.TABLE_NAME,
                        values, selection2, selectionArgs2
                );

                if (count != 1) System.out.println("(2) Updated an incorrect number of rows (" + count + ")");
                else            System.out.println("(2) Updated new neighbourhood in database");

                if (position != 0) {
                    Toast.makeText(getActivity(), item + " à été sélectionné comme arrondissement", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Vous n'avez pas encore sélectionné d'arrondissement", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        TrashyDBHelper dbHelper = App.getDBHelper();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                TrashyDBContract.TrashyDBTableNeighbourhoods._ID,
                TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_NAME,
                TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_IS_CURRENT
        };

        // Filter results WHERE "iscurrent" = 'TRUE'
        String selection = TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_IS_CURRENT + " = ?";
        String[] selectionArgs = { "1" };

        Cursor cursor = db.query(
                TrashyDBContract.TrashyDBTableNeighbourhoods.TABLE_NAME,
                projection,
                selection, selectionArgs,
                null, null,
                null,
                null
        );

        String currentNeighbourhood = "";
        if (cursor.getCount() == 0) {
            System.out.println("No neighbourhood selected!!!!");
        }
        else {
            cursor.moveToNext();
            currentNeighbourhood = cursor.getString(cursor.getColumnIndexOrThrow(
                    TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_NAME
            ));
        }
        cursor.close();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.arrondissements, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if (!"".equals(currentNeighbourhood)) {
            int spinnerPosition = adapter.getPosition(currentNeighbourhood);
            spinner.setSelection(spinnerPosition);
        }

        //zoom
        zoom = (ZoomControls) view.findViewById(R.id.zcZoom);
        zoom.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoogleMap.animateCamera(CameraUpdateFactory.zoomOut());

            }
        });
        zoom.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoogleMap.animateCamera(CameraUpdateFactory.zoomIn());

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Arrondissements");

        mMapView = (MapView) view.findViewById(R.id.frame_layout).findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    // Pour la carte
    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //add marker?

        CameraPosition current = CameraPosition.builder().target(new LatLng(45.5016889,-73.56725599999999)).zoom(11).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(current));


        //localisation
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mGoogleMap.setMyLocationEnabled(true);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(getContext(), "Permettre la localisation pour pouvoir déterminer votre arrondissement automatiquement", Toast.LENGTH_LONG).show();
                    accedCarte.setVisibility(View.GONE);
                    mMapView.setVisibility(View.GONE);
                }
                break;
        }
    }
}
