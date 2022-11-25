package com.example.rental_services;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatePickerFromFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatePickerFromFragment extends Fragment {
    DatePicker datePicker;

    public DatePickerFromFragment() {
        // Required empty public constructor
    }


    public static DatePickerFromFragment newInstance() {
        DatePickerFromFragment fragment = new DatePickerFromFragment();
       return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker_from, container, false);
        Button button = view.findViewById(R.id.fromDateButton);
        datePicker = view.findViewById(R.id.datePikcerFrom);
        long now = System.currentTimeMillis() - 1000;
        datePicker.setMinDate(now);
        datePicker.setMaxDate(now+(1000*60*60*24*7));
        DatePickerToFragment fragmentTo = new DatePickerToFragment();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getToDate();
                Bundle bundle = new Bundle();
//                String datePicked = day +"-"+ month+"-"+ year;
                bundle.putSerializable("datePicked", getToDate() );
                fragmentTo.setArguments(bundle);
                // display the values by using a toast
                FragmentManager manager = getParentFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentInside, fragmentTo, "fragment").addToBackStack("fragment");
                transaction.commit();
            }
        });
        return view;
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