package projetfinal.ift2905_trashy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Calendar extends Fragment {


    public Calendar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

}
//
//
//menu class if needed:
//        package projetfinal.ift2905_trashy;
//
//        import android.app.Fragment;
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.Button;
//
//
///**
// * Created by OlgaFad on 2017-04-21.
// */
//
//public class MenuHandler extends android.app.Fragment {
//
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View v = inflater.inflate(R.layout.activity_main, container, false);
//
//        Button home = (Button) v.findViewById(R.id.nav_home);
//        Button calendar = (Button) v.findViewById(R.id.nav_calendar);
//        Button maps = (Button) v.findViewById(R.id.nav_map);
//        Button settings = (Button) v.findViewById(R.id.nav_settings);
//        Button about = (Button) v.findViewById(R.id.nav_about);
//        Button ecocenter = (Button) v.findViewById(R.id.nav_ecocenter);
//
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        calendar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), Calendar.class);
//                startActivity(intent);
//            }
//        });
//
////        maps.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(v.getContext(), MainActivity.class);
////                startActivity(intent);
////            }
////        });
//
//        settings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), Settings.class);
//                startActivity(intent);
//            }
//        });
//
////        about.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(v.getContext(), MainActivity.class);
////                startActivity(intent);
////            }
////        });
//
////        ecocenter.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(v.getContext(), MainActivity.class);
////                startActivity(intent);
////            }
////        });
//
//        return v;
//
//    }
//}
