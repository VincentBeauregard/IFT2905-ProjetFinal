package projet.trashyv15;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;


public class TrashySettingsFragment extends Fragment{

    private View view;
    Switch notif;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_settings, container, false);


        char type = 'D';
        for(int i = 0;i<3;i++) {
            switch (i) {
                case 1:
                    type = 'R';
                    break;
                case 2:
                    type = 'V';
                    break;
            }
            //test de la fonction de temps restant
            int day[] = App.databaseGetNextDay(type);

            int jourRamassage = 0;
            switch (day[0]) {
                case 0:
                    jourRamassage = Calendar.MONDAY;
                    break;
                case 1:
                    jourRamassage = Calendar.TUESDAY;
                    break;
                case 2:
                    jourRamassage = Calendar.WEDNESDAY;
                    break;
                case 3:
                    jourRamassage = Calendar.THURSDAY;
                    break;
                case 4:
                    jourRamassage = Calendar.FRIDAY;
                    break;
                case 5:
                    jourRamassage = Calendar.SATURDAY;
                    break;
                case 6:
                    jourRamassage = Calendar.SUNDAY;
                    break;
            }

            int heureRamassage = day[1];

            int hoursLeft = getHowManyHoursLeft(jourRamassage, heureRamassage);
        }

        notif = (Switch) view.findViewById(R.id.notif);

        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                notif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            Toast.makeText(getContext(),"Notifications on",Toast.LENGTH_LONG).show();

//                          Il faut faire en sorte que une journee avant le ramassage il ya une notif qui indique c quoi qui va etre ramassee
//                          Sa serait nice si les notifs soient on par defaut ( a l<installation de l'app
                            showNotif(v);


                        }else{
                            Toast.makeText(getContext(),"Notifications off",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        return view;

    }

    public void showNotif(View view){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
        builder.setSmallIcon(R.drawable.recyclage);
        builder.setContentTitle("NOTIFICATION");
        builder.setContentText("La poubelle arrive bro");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());

        stackBuilder.addParentStack( MainActivity.class );
        stackBuilder.addNextIntent(getActivity().getIntent());
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager NM = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        NM.notify(0,builder.build());
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.action_settings);
    }


    public int getHowManyHoursLeft(int jourRamassage, int heureRamassage) {

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        int currentDay = cal.get(Calendar.DAY_OF_WEEK);

        int distanceInDays = jourRamassage - currentDay;
        while (distanceInDays < 0)
            distanceInDays += 7;

        return distanceInDays * 24 + heureRamassage;
    }


}
