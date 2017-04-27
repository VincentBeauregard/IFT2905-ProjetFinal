package projet.trashyv15;

/**
 * Created by OlgaFad on 2017-04-26.
 */
import java.util.Locale;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AndroidLocalize extends Activity {
    Spinner spinnerctrl;
//    Button btn;
    Locale myLocale;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
        spinnerctrl = (Spinner) findViewById(R.id.lang);
        spinnerctrl.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                if (pos == 1) {

                    Toast.makeText(parent.getContext(),
                            "You have selected English", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("en");
                } else if (pos == 2) {

                    Toast.makeText(parent.getContext(),
                            "You have selected French", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("fr");
                }


            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });
    }

    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, AndroidLocalize.class);
        startActivity(refresh);
    }
}
