package projet.trashyv15;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.Locale;

public class CalendarFragment extends Fragment {

    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private CompactCalendarView mCalView;
    private TextView mMonthTextView;

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

        mCalView = (CompactCalendarView)this.getView().findViewById(R.id.calendarView);
        mCalView.setUseThreeLetterAbbreviation(false);
        mCalView.setFirstDayOfWeek(Calendar.SUNDAY);

        mMonthTextView = (TextView)this.getView().findViewById(R.id.monthTextView);
        mMonthTextView.setText(dateFormatForMonth.format(mCalView.getFirstDayOfCurrentMonth()));

        mCalView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {
                mMonthTextView.setText(dateFormatForMonth.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                mMonthTextView.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });
    }
}
