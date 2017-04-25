package projet.trashyv15;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


public class MapsFragment extends Fragment{

    //variables pour les boutons et le texte
    private Switch ahunCV,anjou,cdnndg,lachine,lasalle,mtRoyal,sudOuest,iBSG,mHM,mtlN,outrmt,pfrb,rdppat,rosemtlpp,stlau,stl,verdun,vm,villeraypx;
    private TextView selection;
    private Button accedCarte;

    View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_maps, container, false);
        accedCarte = (Button) view.findViewById(R.id.accedCarte);

        accedCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment= new MapsAutoFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, fragment); // fragmen container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        });
        

        selection = (TextView) view.findViewById(R.id.switchStatus);//permet de modifier le text view
        //permet de modifier l'etat des boutons
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
        villeraypx = (Switch) view.findViewById(R.id.villeraypx);


        checkSwitch(R.id.ahunCV);//regarde lequel des toggle est activé
        checkSwitch(R.id.anjou);
        checkSwitch(R.id.cdnndg);
        checkSwitch(R.id.lachine);
        checkSwitch(R.id.lasalle);
        checkSwitch(R.id.mtRoyal);
        checkSwitch(R.id.sudOuest);
        checkSwitch(R.id.iBSG);
        checkSwitch(R.id.mHM);
        checkSwitch(R.id.mtlN);
        checkSwitch(R.id.outrmt);
        checkSwitch(R.id.pfrb);
        checkSwitch(R.id.rdppat);
        checkSwitch(R.id.rosemtlpp);
        checkSwitch(R.id.stlau);
        checkSwitch(R.id.stl);
        checkSwitch(R.id.verdun);
        checkSwitch(R.id.vm);
        checkSwitch(R.id.villeraypx);



        ahunCV.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {//on click
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){//change le text lors d'un toggle
                    selection.setText("AhunCV is currently ON");//test
                }else{
                    selection.setText("AhunCV is currently OFF");
                }
                //is checked = true si le bouton est active
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

    //checkswitch verifie quel bouton est active
    public void checkSwitch(int id){//le id est un int, on peut le recuperer en utilisant le R.id.nom du quarter dans fragment_maps.xml
        //TrashyDBContract db = TrashyDBHelper.
        String sql = "SELECT * FROM neighbourhoods WHERE name = '" + id + "' AND iscurrent ='TRUE'";

        if(id == R.id.ahunCV) {//test
            ahunCV.setChecked(true);
            selection.setText("Votre Arrondissement: Ahunstic-CartierVille");
        }

        else if (id == R.id.anjou) {
            anjou.setChecked(true);
        }
        else {
            cdnndg.setChecked(true);
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Arrondissements");



    }












}
