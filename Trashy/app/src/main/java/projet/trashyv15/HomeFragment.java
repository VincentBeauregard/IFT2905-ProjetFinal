package projet.trashyv15;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.*;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.*;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;


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
    }

}