package projet.trashyv15;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;


public class MapsFragment extends Fragment implements OnMapReadyCallback{

    //variables pour les boutons et le texte
    private Switch ahunCV,anjou,cdnndg,lachine,lasalle,mtRoyal,sudOuest,iBSG,mHM,mtlN,outrmt,pfrb,rdppat,rosemtlpp,stlau,stl,verdun,vm,villeraypx;
    private TextView selection;
    private Button accedCarte;

    //variables pour la carte
    GoogleMap mGoogleMap;
    MapView mMapView;



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
        CameraPosition current = CameraPosition.builder().target(new LatLng(45.5016889,-73.56725599999999)).zoom(16).bearing(0).tilt(45).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(current));
    }











}
