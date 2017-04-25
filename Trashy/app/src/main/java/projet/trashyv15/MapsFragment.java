package projet.trashyv15;

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
import com.google.android.gms.maps.model.Polygon;


public class MapsFragment extends Fragment implements OnMapReadyCallback{

    //variables pour les boutons et le texte

    private Button accedCarte;

    //variables pour la carte
    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private ZoomControls zoom;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;

    //Polygone + Point
    private Point loc;
    private Polygon ahunCV,anjou,cdnndg,lachine,lasalle,mtRoyal,sudOuest,iBSG,mHM,mtlN,outrmt,pfrb,rdppat,rosemtlpp,stlau,stl,verdun,vm,villeraypx;



    View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_maps, container, false);
        accedCarte = (Button) view.findViewById(R.id.accedCarte);



        accedCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bouton de localisation


            }
        });



        /*//permet de modifier l'etat des boutons
        ahunCV = (Switch) view.findViewById(R.id.ahunCV);
        anjou = (Switch) view.findViewById(R.id.anjou);
        cdnndg = (Switch) view.findViewById(R.id.cdnndg);
        lachine = (Switch) view.findViewById(R.id.lachine);
        lasalle = (Switch) view.findViewById(R.id.lasalle);
        mtRoyal = (Switch) view.findViewById(R.id.mtRoyal);
        sudOuest = (Switch) view.findViewById(R.id.sudOuest);
        iBSG = (Switch) view.findViewById(R.id.iBSG);
        mHM = (Switch) view.findViewById(R.id.mHM);
        mtlN = (Switch) view.findViewById(R.id.mtlN);
        outrmt = (Switch) view.findViewById(R.id.outrmt);
        pfrb = (Switch) view.findViewById(R.id.pfrb);
        rdppat = (Switch) view.findViewById(R.id.rdppat);
        rosemtlpp = (Switch) view.findViewById(R.id.rosemtlpp);
        stlau = (Switch) view.findViewById(R.id.stlau);
        stl = (Switch) view.findViewById(R.id.stl);
        verdun = (Switch) view.findViewById(R.id.verdun);
        vm = (Switch) view.findViewById(R.id.vm);
        villeraypx = (Switch) view.findViewById(R.id.villeraypx);*/


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


            Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {



                    String sql = "UPDATE 'neighbourhoods' SET iscurrent = 'FALSE'  WHERE iscurrent = 'TRUE';";
                    //executer sql
                    String item = parent.getItemAtPosition(position).toString();



                    sql = "UPDATE 'neighbourhoods' SET iscurrent = 'TRUE'  WHERE name = '"+ item +"';";
                    //executer sql


                    // An item was selected. You can retrieve the selected item using
                    // parent.getItemAtPosition(pos)



                    if(!item.equals("Sélectionnez un arrondissement"))
                        Toast.makeText(getActivity(),item + " à été sélectionné comme arrondissement", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(getActivity(),"Vous n'avez pas encore sélectionné d'arrondissement", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        //verifier lequel est selectionne
        String sql = "SELECT name FROM 'neighbourhoods' WHERE iscurrent = 'TRUE'";
        //executer sql



        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.arrondissements, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


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

        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        //return inflater.inflate(R.layout.fragment_maps, container, false);
    }
        












    /*FAIRE LA CONNEXION A LA BASE DE DONNEES DANS CHECKSWITCH
    //////////////////////////
    /////////////////////////////////////

    ////////////////////////////////////////////////////
     */




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Arrondissements");

        //pour la carte
        mMapView = (MapView) view.findViewById(R.id.frame_layout).findViewById(R.id.map);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync((OnMapReadyCallback) this);
        }

        //Partie ou on affiche la carte:





    }




    //pour la carte
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //add marker?

        //position de depart
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
