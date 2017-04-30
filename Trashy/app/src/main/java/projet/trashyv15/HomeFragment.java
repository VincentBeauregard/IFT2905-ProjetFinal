package projet.trashyv15;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;


public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_home, container, false);



    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles

        getActivity().setTitle(R.string.title_home);
        Button trashButton = (Button) getView().findViewById(R.id.timeTrash);
        trashButton.setText(R.string.jours); /* si je mets "4" + R.id.timeTrash sa retourne des chiffres random */
        Button recycleButton = (Button) getView().findViewById(R.id.timeRecyc);
        recycleButton.setText(R.string.jour);
        Button compostButton = (Button) getView().findViewById(R.id.timeCompost);
        compostButton.setText(R.string.demain);


        //test de la fonction de temps restant
        int jourRamassage = Calendar.SUNDAY;

        int heureRamassage = 0;

        int[] tab = findNext(jourRamassage,heureRamassage);

        if(tab[1]!=-1){
            Toast.makeText(getContext(),tab[0] + " Jours " + tab[1] + " Heures",Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(getContext(),"Maintenant!!",Toast.LENGTH_LONG).show();

        //heure = 0 si plus de 23 heures, jours = 0 si moins de 24 heures

    }


    public int[] findNext(int jourRamassage, int heureRamassage){
        //recoit lheure et le jour du ramassage, retourne un tableau avec le nombre d'heures/jours restant
        //si jhm[1]!=0 on affiche jhm[1] sinon on affiche jhm[0]
        //si jhm[1]==-1, la collecte est maintenant

        int[] jhm = new int[2];
        /*
        methode exemple:
        IMPORTANT, Calendar.set doit etre a l'interieur d'une fonction sinon ca marche pas*/
        jhm[0] = 0;
        jhm[1] = 0;

        Calendar auj = Calendar.getInstance();
        int currentDay = auj.get(Calendar.DAY_OF_WEEK);




        while(currentDay!=jourRamassage){
            auj.add(Calendar.DAY_OF_WEEK, 1);
            currentDay = auj.get(Calendar.DAY_OF_WEEK);
            jhm[0]++;
        }

        if(jhm[0]==1){
            //heures





            if(currentDay>heureRamassage){
                jhm[1]=24;
                jhm[0]=0;
                while(currentDay!=heureRamassage){
                    auj.add(Calendar.HOUR_OF_DAY,-1);
                    currentDay = auj.get(Calendar.HOUR_OF_DAY);
                    jhm[1]--;
                }

            }

            else {
                jhm[0]=1;
            }




        }

        else if(jhm[0]==0){
            auj = Calendar.getInstance();
            currentDay = auj.get(Calendar.HOUR_OF_DAY);


            if(currentDay<heureRamassage){

                while(currentDay!=heureRamassage){
                    auj.add(Calendar.HOUR_OF_DAY,1);
                    currentDay = auj.get(Calendar.HOUR_OF_DAY);
                    jhm[1]++;
                }

            }

            else if(currentDay==heureRamassage)
                jhm[1]=-1;


            else{
                jhm[0]=7;
            }
        }




        return jhm;
    };



}