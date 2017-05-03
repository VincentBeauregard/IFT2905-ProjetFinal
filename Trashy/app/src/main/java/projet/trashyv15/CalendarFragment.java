package projet.trashyv15;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarFragment extends Fragment {

    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private CompactCalendarView mCalView;
    private TextView mMonthTextView;
    private TextView mInfoView;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.title_activity_calendar);

        mInfoView = (TextView)this.getView().findViewById(R.id.eventInfoTextView);

        mCalView = (CompactCalendarView)this.getView().findViewById(R.id.calendarView);
        mCalView.setUseThreeLetterAbbreviation(false);
        mCalView.setFirstDayOfWeek(Calendar.SUNDAY);
        mCalView.setCurrentDate(Calendar.getInstance().getTime());

        mMonthTextView = (TextView)this.getView().findViewById(R.id.monthTextView);
        mMonthTextView.setText(dateFormatForMonth.format(mCalView.getFirstDayOfCurrentMonth()));

        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), 3, 15);
        Event horaireEte = new Event(R.color.darkBeige, cal.getTimeInMillis(), getResources().getString(R.string.horaire_ete));
        cal.set(cal.get(Calendar.YEAR), 9, 15);
        Event horaireHiver = new Event(R.color.darkBeige, cal.getTimeInMillis(), getResources().getString(R.string.horaire_hiver));

        mCalView.addEvent(horaireHiver, false);
        mCalView.addEvent(horaireEte, false);

        addMonthEvents(Calendar.getInstance().get(Calendar.MONTH));

        mCalView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {

                mMonthTextView.setText(dateFormatForMonth.format(dateClicked));

                List<Event> events = mCalView.getEvents(dateClicked);
                StringBuilder sb = new StringBuilder();

                for (Event e : events) {
                    sb.append("\u2022 ");
                    sb.append(e.getData().toString());
                    sb.append("\n");
                }

                mInfoView.setText(sb.toString());
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

                mMonthTextView.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                mInfoView.setText("");
            }
        });
    }

    private void addMonthEvents(int month) {

        Calendar cal = Calendar.getInstance();

        // AHHHHHHHHHHHHHHHHH
        // On ajoute les evenements par type. Cycle de 1 semaine.
        // Dechets (type='D')
        int[] dechetTab = App.databaseGetNextDay('D');
        int dechetDay = dechetTab[0];
        int dechetHour = dechetTab[1];

        int currentDay = dechetDay + 1;
        while (currentDay <= 31) {

            cal.set(cal.get(Calendar.YEAR), month, currentDay);
            String desc = getResources().getString(R.string.at) + " " + String.valueOf(dechetHour) + ":00 - " + getResources().getString(R.string.collecte_dechets);
            Event e = new Event(Color.GRAY, cal.getTimeInMillis(), desc);

            mCalView.addEvent(e, false);

            currentDay += 7;
        }

        // Recyclage (type='R')
        int[] recycleTab = App.databaseGetNextDay('R');
        int recycleDay = recycleTab[0];
        int recycleHour = recycleTab[1];

        currentDay = recycleDay + 1;
        while (currentDay <= 31) {

            cal.set(cal.get(Calendar.YEAR), month, currentDay);
            String desc = getResources().getString(R.string.at) + " " + String.valueOf(recycleHour) + ":00 - " + getResources().getString(R.string.collecte_recyclage);
            Event e = new Event(Color.parseColor("#246C36"), cal.getTimeInMillis(), desc);

            mCalView.addEvent(e, false);

            currentDay += 7;
        }

        // Compost (type='V')
        int[] compostTab = App.databaseGetNextDay('V');
        int compostDay = compostTab[0];
        int compostHour = compostTab[1];

        currentDay = compostDay + 1;
        while (currentDay <= 31) {

            cal.set(cal.get(Calendar.YEAR), month, currentDay);
            String desc = getResources().getString(R.string.at) + " " + String.valueOf(compostHour) + ":00 - " + getResources().getString(R.string.collecte_compost);
            Event e = new Event(Color.parseColor("#87512E"), cal.getTimeInMillis(), desc);

            mCalView.addEvent(e, false);

            currentDay += 7;
        }

        mCalView.postInvalidate();
    }
}
