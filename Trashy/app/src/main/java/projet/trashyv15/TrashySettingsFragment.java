package projet.trashyv15;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Locale;


public class TrashySettingsFragment extends Fragment{


    private View view;
    Locale myLocale;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        view = inflater.inflate(R.layout.fragment_settings, container, false);


        Spinner language = (Spinner)view.findViewById(R.id.lang);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.settings_language, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language.setAdapter(adapter);

        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                if (pos == 1) {

                    Toast.makeText(parent.getContext(), "You have selected English", Toast.LENGTH_SHORT).show();
                    setLocale("en");

                }
//                else if (pos == 2) {
//
//                    Toast.makeText(parent.getContext(), "You have selected French", Toast.LENGTH_SHORT).show();
//                    setLocale("fr");
//                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }



        });
        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.action_settings);
    }

    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(myLocale);
        res.updateConfiguration(conf, dm);

//        createConfigurationContext(conf);
// Step 2. IMPORTANT! you must restart the app to make sure it works 100%

        Intent refresh = new Intent(getContext(), TrashySettingsFragment.class);
        startActivity(refresh);
    }

}
