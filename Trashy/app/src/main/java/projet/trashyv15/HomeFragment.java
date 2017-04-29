package projet.trashyv15;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


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
        getActivity().setTitle("Home");
        Button trashButton = (Button) getView().findViewById(R.id.timeTrash);
        trashButton.setText("1 Annee");
        Button recycleButton = (Button) getView().findViewById(R.id.timeRecyc);
        recycleButton.setText("2 Annee");
        Button compostButton = (Button) getView().findViewById(R.id.timeCompost);
        compostButton.setText("3 Annee");
    }


    public int[] findNext(){
        int[] mjh = new int[3];
        /*
        methode exemple:
        IMPORTANT, Calendar.set doit etre a l'interieur d'une fonction sinon ca marche pas

        Calendar auj = Calendar.getInstance();
        int currentDay = auj.get(Calendar.DAY_OF_WEEK);

        Calendar next = Calendar.getInstance();
        next.add(Calendar.DAY_OF_YEAR, 1);

        if (currentDay == Calendar.SATURDAY)
        {
            next.add(Calendar.DAY_OF_YEAR, 1);
        }
        else if (currentDay == Calendar.FRIDAY)
        {
            next.add(Calendar.DAY_OF_YEAR, 2);
        }

        next.set(Calendar.HOUR_OF_DAY, 8);
        next.set(Calendar.MINUTE, 30);

        long millisLeft = next.getTimeInMillis() - auj.getTimeInMillis();
        long hoursLeft = millisLeft / (60 * 60 * 1000);
        long minutesLeft =  (millisLeft % (60 * 60 * 1000)) / (60 * 1000);
        */
        return mjh;
    };



}