package projet.trashyv15;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
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
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import projet.trashyv15.donneesPoly.ahunCV1;
import projet.trashyv15.donneesPoly.anjou1;
import projet.trashyv15.donneesPoly.cdnndg1;
import projet.trashyv15.donneesPoly.iBSG1;
import projet.trashyv15.donneesPoly.lachine1;
import projet.trashyv15.donneesPoly.lasalle1;
import projet.trashyv15.donneesPoly.mHM1;
import projet.trashyv15.donneesPoly.mn1;
import projet.trashyv15.donneesPoly.mtroyal1;
import projet.trashyv15.donneesPoly.outrmt1;
import projet.trashyv15.donneesPoly.pR1;
import projet.trashyv15.donneesPoly.pr2;
import projet.trashyv15.donneesPoly.pr3;
import projet.trashyv15.donneesPoly.pr4;
import projet.trashyv15.donneesPoly.pr5;
import projet.trashyv15.donneesPoly.pr6;
import projet.trashyv15.donneesPoly.pr7;
import projet.trashyv15.donneesPoly.pr8;
import projet.trashyv15.donneesPoly.rdppat1;
import projet.trashyv15.donneesPoly.rlpp1;
import projet.trashyv15.donneesPoly.stl1;
import projet.trashyv15.donneesPoly.stlau1;
import projet.trashyv15.donneesPoly.sudouest1;
import projet.trashyv15.donneesPoly.verdun1;
import projet.trashyv15.donneesPoly.verdun2;
import projet.trashyv15.donneesPoly.villeraypx1;
import projet.trashyv15.donneesPoly.vm1;
import projet.trashyv15.donneesPoly.vm2;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private Button accedCarte;
    private View view;
    private Thread t1;
    private Thread t2;
    private Thread t3;
    private Thread t4;
    private PolygonOptions polygonahunCV;
    private PolygonOptions polygonanjou;
    private PolygonOptions polygoncdnndg;
    private PolygonOptions polygoniBSG;
    private PolygonOptions polygonlachine;
    private PolygonOptions polygonlasalle;
    private PolygonOptions polygonmHM;
    private PolygonOptions polygonmn;
    private PolygonOptions polygonmtroyal;
    private PolygonOptions polygonoutrmt;
    private PolygonOptions polygonpR;
    private PolygonOptions polygonpR2;
    private PolygonOptions polygonpR3;
    private PolygonOptions polygonpR4;
    private PolygonOptions polygonpR5;
    private PolygonOptions polygonpR6;
    private PolygonOptions polygonpR7;
    private PolygonOptions polygonpR8;
    private PolygonOptions polygonrdppat;
    private PolygonOptions polygonrlpp;
    private PolygonOptions polygonstl;
    private PolygonOptions polygonstlau;
    private PolygonOptions polygonsudouest;
    private PolygonOptions polygonverdun1;
    private PolygonOptions polygonverdun2;
    private PolygonOptions polygonvilleraypx;
    private PolygonOptions polygonvm1;
    private PolygonOptions polygonvm2;


    //variables pour la carte
    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private ZoomControls zoom;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private double latitude;
    private double longitude;

    private LocationManager locationManager;
    private Location location;

    //variable pour localisation:
    LatLng loc;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

//        TextView texte = (TextView) view.findViewById(R.id.switchStatus);
//        texte.setText(R.string.nh);

        view = inflater.inflate(R.layout.fragment_maps, container, false);

        accedCarte = (Button) view.findViewById(R.id.accedCarte);
        accedCarte.setText(R.string.detNh);


//tous les thread sont pour l'optimisation

        t1 = new Thread() {
            public void run() {

                //colorier le polygone

                //ahunstic cv
                polygonahunCV = new PolygonOptions()
                        .add(ahunCV1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


// anjou
                polygonanjou = new PolygonOptions()
                        .add(anjou1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


//cdnndg
                polygoncdnndg = new PolygonOptions()
                        .add(cdnndg1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


//ibsg
                polygoniBSG = new PolygonOptions()
                        .add(iBSG1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonlachine = new PolygonOptions()
                        .add(lachine1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonlasalle = new PolygonOptions()
                        .add(lasalle1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


            }
        };

        t3 = new Thread() {
            public void run() {


                polygonmHM = new PolygonOptions()
                        .add(mHM1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonmn = new PolygonOptions()
                        .add(mn1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonmtroyal = new PolygonOptions()
                        .add(mtroyal1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonoutrmt = new PolygonOptions()
                        .add(outrmt1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonpR = new PolygonOptions()
                        .add(pR1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonpR2 = new PolygonOptions()
                        .add(pr2.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonpR3 = new PolygonOptions()
                        .add(pr3.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);

            }
        };


        t2 = new Thread() {
            public void run() {

                polygonpR4 = new PolygonOptions()
                        .add(pr4.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonpR5 = new PolygonOptions()
                        .add(pr5.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonpR6 = new PolygonOptions()
                        .add(pr6.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonpR7 = new PolygonOptions()
                        .add(pr7.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonpR8 = new PolygonOptions()
                        .add(pr8.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonrdppat = new PolygonOptions()
                        .add(rdppat1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonrlpp = new PolygonOptions()
                        .add(rlpp1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonstl = new PolygonOptions()
                        .add(stl1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);
            }
        };
        t4 = new Thread() {
            public void run() {


                polygonstlau = new PolygonOptions()
                        .add(stlau1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonsudouest = new PolygonOptions()
                        .add(sudouest1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonverdun1 = new PolygonOptions()
                        .add(verdun1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonverdun2 = new PolygonOptions()
                        .add(verdun2.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonvilleraypx = new PolygonOptions()
                        .add(villeraypx1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonvm1 = new PolygonOptions()
                        .add(vm1.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);


                polygonvm2 = new PolygonOptions()
                        .add(vm2.m)
                        .strokeColor(0x80DC143C)
                        .fillColor(0x500000FF);

            }
        };


        t1.start();
        t2.start();
        t3.start();
        t4.start();


        accedCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.


                    locationManager = (LocationManager)
                            getActivity().getSystemService(getContext().LOCATION_SERVICE);
                    Criteria criteria = new Criteria();

                    location = locationManager.getLastKnownLocation(locationManager
                            .getBestProvider(criteria, false));


                    if (location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();

                        loc = new LatLng(latitude, longitude);
                        CameraPosition current = CameraPosition.builder().target(loc).zoom(12).bearing(0).tilt(45).build();
                        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(current));

                        if (containsLocation(loc, ahunCV1.m)) {
                            Toast.makeText(getContext(), "Ahunstic-CartierVille", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, vm1.m) || containsLocation(loc, vm2.m)) {
                            Toast.makeText(getContext(), "Ville-Marie", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, anjou1.m)) {
                            Toast.makeText(getContext(), "Anjou", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, cdnndg1.m)) {
                            Toast.makeText(getContext(), "Cote des Neiges Notre Dame de Grace", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, iBSG1.m)) {
                            Toast.makeText(getContext(), "Île-Bizard–Sainte-Geneviève", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, lachine1.m)) {
                            Toast.makeText(getContext(), "Lachine", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, lasalle1.m)) {
                            Toast.makeText(getContext(), "LaSalle", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, mtroyal1.m)) {
                            Toast.makeText(getContext(), "Plateau-Mont-Royal", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, sudouest1.m)) {
                            Toast.makeText(getContext(), "Sud-Ouest", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, mHM1.m)) {
                            Toast.makeText(getContext(), "Mercier–Hochelaga-Maisonneuve", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, mn1.m)) {
                            Toast.makeText(getContext(), "Montréal-Nord", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, outrmt1.m)) {
                            Toast.makeText(getContext(), "Outremont", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, pR1.m) || containsLocation(loc, pr2.m) || containsLocation(loc, pr3.m) || containsLocation(loc, pr4.m) || containsLocation(loc, pr5.m) || containsLocation(loc, pr6.m) || containsLocation(loc, pr7.m) || containsLocation(loc, pr8.m)) {
                            Toast.makeText(getContext(), "Pierrefonds-Roxboro", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, rdppat1.m)) {
                            Toast.makeText(getContext(), "Rivière-des-Prairies–Pointe-aux-Trembles", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, rlpp1.m)) {
                            Toast.makeText(getContext(), "Rosemont–La Petite-Patrie", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, stlau1.m)) {
                            Toast.makeText(getContext(), "Saint-Laurent", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, stl1.m)) {
                            Toast.makeText(getContext(), "Saint-Léonard", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, verdun1.m) || containsLocation(loc, verdun2.m)) {
                            Toast.makeText(getContext(), "Verdun", Toast.LENGTH_LONG).show();
                        } else if (containsLocation(loc, villeraypx1.m)) {
                            Toast.makeText(getContext(), "Villeray–Saint-Michel–Parc-Extension", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(getContext(), R.string.zoneNS, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), R.string.toastNoLoc, Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
                    }
                }







            }
        });

        /*pos du array:
        0- Ahuntsic-Cartiervillewe
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


        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        //pour marc:trouver le iscurrent et faire:
        //spinner.setSelection(position du selected dans le array(int));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) return;

                TrashyDBHelper dbHelper = App.getDBHelper();
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String item = parent.getItemAtPosition(position).toString();

                // Execute UPDATE 'neighbourhoods' SET iscurrent = 'FALSE'  WHERE iscurrent = 'TRUE';
                ContentValues values = new ContentValues();
                values.put(TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_IS_CURRENT, 0);
                String selection = TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_IS_CURRENT + " LIKE ?";
                String[] selectionArgs = {"1"};

                int count = db.update(
                        TrashyDBContract.TrashyDBTableNeighbourhoods.TABLE_NAME,
                        values, selection, selectionArgs
                );

                if (count != 1)
                    System.out.println("(1) Updated an incorrect number of rows (" + count + ")");
                else System.out.println("(1) Updated old neighbourhood in database");

                // Execute UPDATE 'neighbourhoods' SET iscurrent = 'TRUE'  WHERE name = [item];
                values = new ContentValues();
                values.put(TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_IS_CURRENT, 1);
                String selection2 = TrashyDBContract.TrashyDBTableNeighbourhoods.COLUMN_NAME_NAME + " LIKE ?";
                String[] selectionArgs2 = {secteur_translate(item)};

                count = db.update(
                        TrashyDBContract.TrashyDBTableNeighbourhoods.TABLE_NAME,
                        values, selection2, selectionArgs2
                );

                if (count != 1)
                    System.out.println("(2) Updated an incorrect number of rows (" + count + ")");
                else System.out.println("(2) Updated new neighbourhood in database"+count);

                if (position != 0) {

                    Toast.makeText(getActivity(), item + " " + getContext().getResources().getString(R.string.toastLocSel), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), R.string.toastNoLocSel, Toast.LENGTH_SHORT).show();

                }
                for(int i = 0 ; i<999999;i++){}
                System.out.println(App.databaseGetCurrentNeighbourhoodID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
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
        String[] selectionArgs = {"1"};

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
        } else {
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
            int spinnerPosition = adapter.getPosition(secteur_translate_inverse(currentNeighbourhood));
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
        getActivity().setTitle(R.string.title_carte);

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

        CameraPosition current = CameraPosition.builder().target(new LatLng(45.5016889, -73.56725599999999)).zoom(11).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(current));


        while (t1.getState() != Thread.State.TERMINATED) {
        }

        //colorier le polygone

        //ahunstic cv
        final Polygon polygonahunCV = mGoogleMap.addPolygon(this.polygonahunCV);
        polygonahunCV.setClickable(true);


// anjou
        final Polygon polygonanjou = mGoogleMap.addPolygon(this.polygonanjou);
        polygonanjou.setClickable(true);


//cdnndg
        final Polygon polygoncdnndg = mGoogleMap.addPolygon(this.polygoncdnndg);

        polygoncdnndg.setClickable(true);


//ibsg
        final Polygon polygoniBSG = mGoogleMap.addPolygon(this.polygoniBSG);

        polygoniBSG.setClickable(true);


        final Polygon polygonlachine = mGoogleMap.addPolygon(this.polygonlachine);

        polygonlachine.setClickable(true);


        final Polygon polygonlasalle = mGoogleMap.addPolygon(this.polygonlasalle);

        polygonlasalle.setClickable(true);

        while (t3.getState() != Thread.State.TERMINATED) {
        }


        final Polygon polygonmHM = mGoogleMap.addPolygon(this.polygonmHM);

        polygonmHM.setClickable(true);


        final Polygon polygonmn = mGoogleMap.addPolygon(this.polygonmn);
        polygonmn.setClickable(true);


        final Polygon polygonmtroyal = mGoogleMap.addPolygon(this.polygonmtroyal);
        polygonmtroyal.setClickable(true);


        final Polygon polygonoutrmt = mGoogleMap.addPolygon(this.polygonoutrmt);

        polygonoutrmt.setClickable(true);


        final Polygon polygonpR = mGoogleMap.addPolygon(this.polygonpR);
        polygonpR.setClickable(true);


        final Polygon polygonpR2 = mGoogleMap.addPolygon(this.polygonpR2);
        polygonpR2.setClickable(true);


        final Polygon polygonpR3 = mGoogleMap.addPolygon(this.polygonpR3);
        polygonpR3.setClickable(true);

        while (t2.getState() != Thread.State.TERMINATED) {
        }

        final Polygon polygonpR4 = mGoogleMap.addPolygon(this.polygonpR4);
        polygonpR4.setClickable(true);


        final Polygon polygonpR5 = mGoogleMap.addPolygon(this.polygonpR5);
        polygonpR5.setClickable(true);


        final Polygon polygonpR6 = mGoogleMap.addPolygon(this.polygonpR6);
        polygonpR6.setClickable(true);


        final Polygon polygonpR7 = mGoogleMap.addPolygon(this.polygonpR7);
        polygonpR7.setClickable(true);


        final Polygon polygonpR8 = mGoogleMap.addPolygon(this.polygonpR8);
        polygonpR8.setClickable(true);


        final Polygon polygonrdppat = mGoogleMap.addPolygon(this.polygonrdppat);
        polygonrdppat.setClickable(true);


        final Polygon polygonrlpp = mGoogleMap.addPolygon(this.polygonrlpp);
        polygonrlpp.setClickable(true);


        final Polygon polygonstl = mGoogleMap.addPolygon(this.polygonstl);
        polygonstl.setClickable(true);

        while (t4.getState() != Thread.State.TERMINATED) {
        }


        final Polygon polygonstlau = mGoogleMap.addPolygon(this.polygonstlau);
        polygonstlau.setClickable(true);


        final Polygon polygonsudouest = mGoogleMap.addPolygon(this.polygonsudouest);
        polygonsudouest.setClickable(true);


        final Polygon polygonverdun1 = mGoogleMap.addPolygon(this.polygonverdun1);
        polygonverdun1.setClickable(true);


        final Polygon polygonverdun2 = mGoogleMap.addPolygon(this.polygonverdun2);
        polygonverdun2.setClickable(true);


        final Polygon polygonvilleraypx = mGoogleMap.addPolygon(this.polygonvilleraypx);
        polygonvilleraypx.setClickable(true);


        final Polygon polygonvm1 = mGoogleMap.addPolygon(this.polygonvm1);
        polygonvm1.setClickable(true);


        final Polygon polygonvm2 = mGoogleMap.addPolygon(this.polygonvm2);
        polygonvm2.setClickable(true);

        mGoogleMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            public void onPolygonClick(Polygon polygon) {


                if (polygon.equals(polygonahunCV))
                    Toast.makeText(getContext(), "Ahunstic-CartierVille", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonanjou))
                    Toast.makeText(getContext(), "Anjou", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygoncdnndg))
                    Toast.makeText(getContext(), "Côte-des-Neiges–Notre-Dame-de-Grâce", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygoniBSG))
                    Toast.makeText(getContext(), "Île-Bizard–Sainte-Geneviève", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonlachine))
                    Toast.makeText(getContext(), "Lachine", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonlasalle))
                    Toast.makeText(getContext(), "LaSalle", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonmHM))
                    Toast.makeText(getContext(), "Mercier–Hochelaga-Maisonneuve", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonmn))
                    Toast.makeText(getContext(), "Montréal-Nord", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonmtroyal))
                    Toast.makeText(getContext(), "Plateau-Mont-Royal", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonoutrmt))
                    Toast.makeText(getContext(), "Outremont", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonpR) || polygon.equals(polygonpR2) || polygon.equals(polygonpR3) || polygon.equals(polygonpR4) || polygon.equals(polygonpR5) || polygon.equals(polygonpR6) || polygon.equals(polygonpR7) || polygon.equals(polygonpR8))
                    Toast.makeText(getContext(), "Pierrefonds-Roxboro", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonrdppat))
                    Toast.makeText(getContext(), "Rivière-des-Prairies–Pointe-aux-Trembles", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonrlpp))
                    Toast.makeText(getContext(), "Rosemont–La Petite-Patrie", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonstl))
                    Toast.makeText(getContext(), "Saint-Léonard", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonstlau))
                    Toast.makeText(getContext(), "Saint-Laurent", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonsudouest))
                    Toast.makeText(getContext(), "Sud-Ouest", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonverdun1) || polygon.equals(polygonverdun2))
                    Toast.makeText(getContext(), "Verdun", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonvilleraypx))
                    Toast.makeText(getContext(), "Villeray–Saint-Michel–Parc-Extension", Toast.LENGTH_SHORT).show();
                else if (polygon.equals(polygonvm1) || polygon.equals(polygonvm2))
                    Toast.makeText(getContext(), "Ville-Marie", Toast.LENGTH_SHORT).show();


            }
        });


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
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);


            locationManager = (LocationManager)
                    getActivity().getSystemService(getContext().LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            location = locationManager.getLastKnownLocation(locationManager
                    .getBestProvider(criteria, false));
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                loc = new LatLng(latitude, longitude);
            }


        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }


    }

    private boolean containsLocation(LatLng loc, LatLng[] noeuds) {
        int intersectCount = 0;
        for (int j = 0; j < noeuds.length - 1; j++) {
            if (rayCastIntersect(loc, noeuds[j], noeuds[j + 1])) {
                intersectCount++;
            }
        }

        return ((intersectCount % 2) == 1); // odd = inside, even = outside;
    }

    private boolean rayCastIntersect(LatLng loc, LatLng nA, LatLng nB) {

        double aY = nA.latitude;
        double bY = nB.latitude;
        double aX = nA.longitude;
        double bX = nB.longitude;
        double pY = loc.latitude;
        double pX = loc.longitude;

        if ((aY > pY && bY > pY) || (aY < pY && bY < pY)
                || (aX < pX && bX < pX)) {
            return false; // a and b can't both be above or below pt.y, and a or
            // b must be east of pt.x
        }

        double m = (aY - bY) / (aX - bX); // Rise over run
        double bee = (-aX) * m + aY; // y = mx + b
        double x = (pY - bee) / m; // algebra is neat!

        return x > pX;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mGoogleMap.setMyLocationEnabled(true);
                        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                    }

                } else {
                    Toast.makeText(getContext(), R.string.toastLoc, Toast.LENGTH_LONG).show();
                    accedCarte.setVisibility(View.GONE);
                    mMapView.setVisibility(View.GONE);
                }
                break;
        }
    }

    public static String secteur_translate(String item) {
        System.out.println("-"+item+"-");
        if (item.equals("Ahunstic-Cartierville")) return "ahunCV1";
        if (item.equals("Anjou")) return "anjou1";
        if (item.equals("Côte-des-Neiges-Notre-Dame-de-Grâce")) return "cdnndg1";
        if (item.equals("Lachine")) return "lachine1";
        if (item.equals("LaSalle")) return "lasalle1";
        if (item.equals("Plateau Mont-Royal")) return "mtroyal1";
        if (item.equals("Sud-Ouest")) return "sudouest1";
        if (item.equals("Île-Bizard–Sainte-Geneviève")) return "iBSG1";
        if (item.equals("Mercier–Hochelaga-Maisonneuve")) return "mHM1";
        if (item.equals("Montréal-Nord")) return "mn1";
        if (item.equals("Outremont")) return "outrmt1";
        if (item.equals("Pierrefonds-Roxboro")) return "pR1";
        if (item.equals("Rivière-des-Prairies–Pointe-aux-Trembles")) return "rdppat1";
        if (item.equals("Rosemont–La Petite-Patrie")) return "rlpp1";
        if (item.equals("Saint-Laurent")) return "stlau1";
        if (item.equals("Saint-Léonard")) return "stl1";
        if (item.equals("Verdun")) return "verdun1";
        if (item.equals("Ville-Marie")) return "vm1";
        if (item.equals("Villeray–Saint-Michel–Parc-Extension")) return "vm2";
        else return "";
    }

    public static String secteur_translate_inverse(String item) {
        System.out.println("-"+item+"-");
        if (item.equals("ahunCV1")) return "Ahunstic-Cartierville";
        if (item.equals("anjou1")) return "Anjou";
        if (item.equals("cdnndg1")) return "Côte-des-Neiges-Notre-Dame-de-Grâce";
        if (item.equals("lachine1")) return "Lachine";
        if (item.equals("lasalle1")) return "LaSalle";
        if (item.equals("mtroyal1")) return "Plateau Mont-Royal";
        if (item.equals("sudouest1")) return "Sud-Ouest";
        if (item.equals("iBSG1")) return "Île-Bizard–Sainte-Geneviève";
        if (item.equals("mHM1")) return "Mercier–Hochelaga-Maisonneuve";
        if (item.equals("mn1")) return "Montréal-Nord";
        if (item.equals("outrmt1")) return "Outremont";
        if (item.equals("pR1")) return "Pierrefonds-Roxboro";
        if (item.equals("rdppat1")) return "Rivière-des-Prairies–Pointe-aux-Trembles";
        if (item.equals("rlpp1")) return "Rosemont–La Petite-Patrie";
        if (item.equals("stlau1")) return "Saint-Laurent";
        if (item.equals("stl1")) return "Saint-Léonard";
        if (item.equals("verdun1")) return "Verdun";
        if (item.equals("vm1")) return "Ville-Marie";
        if (item.equals("vm2")) return "Villeray–Saint-Michel–Parc-Extension";
        else return "";
    }

    }
