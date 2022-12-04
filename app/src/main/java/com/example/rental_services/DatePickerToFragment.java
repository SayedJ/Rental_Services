package com.example.rental_services;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rental_services.Entities.BookingInfo;

import java.util.Calendar;
import java.util.Date;


public class DatePickerToFragment extends Fragment {
    BookingInfo bookingInfo = null;
    TextView textView2, textView3;
    Date dateTo = null;
    String s;
    DatePicker datePicker;
    public DatePickerToFragment() {
        // Required empty public constructor
    }
    public static DatePickerToFragment newInstance(String param1, String param2) {
        DatePickerToFragment fragment = new DatePickerToFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_date_picker_to, container, false);
        Button button = rootView.findViewById(R.id.toDateButton);
//        TextView textView = rootView.findViewById(R.id.pickedupDate);
        datePicker = rootView.findViewById(R.id.dateTo);
        long now = System.currentTimeMillis() - 1000;
        datePicker.setMinDate(now);
        datePicker.setMaxDate(now+(1000*60*60*24*7));

        Date recievedDate = (Date) getArguments().getSerializable("datePicked");
        ShipmentFragment fragmentShip = new ShipmentFragment();
            button.setOnClickListener(view -> {
                Date date = new Date();
            Bundle bundle = new Bundle();
            bookingInfo = new BookingInfo(recievedDate, getToDate(),date);
            bundle.putSerializable("date", bookingInfo);
            fragmentShip.setArguments(bundle);
            FragmentManager manager = getParentFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentInside, fragmentShip, "fragmentFrom").addToBackStack("fragmentFrom");
            transaction.commit();
        });

        // display the values by using a toast

        // Inflate the layout for this fragment
        return rootView;
    }
    public Date getToDate(){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar cln = Calendar.getInstance();
//        cln.set(Calendar.DAY_OF_MONTH, day);
//        cln.set(Calendar.MONTH, month);
//        cln.set(Calendar.YEAR, year);
        cln.set(year, month, day);
        Date dateTo = cln.getTime();
        return dateTo;
    }


}