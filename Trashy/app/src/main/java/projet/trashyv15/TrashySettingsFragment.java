package projet.trashyv15;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;


public class TrashySettingsFragment extends Fragment{

    private View view;
    Switch notif;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_settings, container, false);


        notif = (Switch) view.findViewById(R.id.notif);
        if(notif.getShowText() == true){
            showNotif(view);

        }

        return view;

    }

    public void showNotif(View view){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
        builder.setSmallIcon(R.drawable.recyclage);
        builder.setContentTitle("NOTIFICATION");
        builder.setContentText("La poubelle arrive bro");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());

        stackBuilder.addParentStack(Notification.class);
        stackBuilder.addNextIntent(getActivity().getIntent());
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager NM = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        NM.notify(0,builder.build());
    }

//    NotificationCompat.Builder mBuilder =
//            new NotificationCompat.Builder(this)
//                    .setSmallIcon(R.drawable.notification_icon)
//                    .setContentTitle("My notification")
//                    .setContentText("Hello World!");
//    // Creates an explicit intent for an Activity in your app
//    Intent resultIntent = new Intent(this, ResultActivity.class);
//
//    // The stack builder object will contain an artificial back stack for the
//// started Activity.
//// This ensures that navigating backward from the Activity leads out of
//// your application to the Home screen.
//    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//// Adds the back stack for the Intent (but not the Intent itself)
//stackBuilder.addParentStack(ResultActivity.class);
//// Adds the Intent that starts the Activity to the top of the stack
//stackBuilder.addNextIntent(resultIntent);
//    PendingIntent resultPendingIntent =
//            stackBuilder.getPendingIntent(
//                    0,
//                    PendingIntent.FLAG_UPDATE_CURRENT
//            );
//mBuilder.setContentIntent(resultPendingIntent);
//    NotificationManager mNotificationManager =
//            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//// mId allows you to update the notification later on.
//mNotificationManager.notify(mId, mBuilder.build());



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.action_settings);
    }




}
