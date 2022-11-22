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
import android.widget.TextView;
import android.widget.Toast;


public class DatePickerToFragment extends Fragment {

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
        TextView textView = rootView.findViewById(R.id.pickedupDate);
        DatePicker datePicker = rootView.findViewById(R.id.datePikcerFrom);
        long now = System.currentTimeMillis() - 1000;
        datePicker.setMinDate(now);
        datePicker.setMaxDate(now+(1000*60*60*24*7));
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        String recievedDate = getArguments().getString("datePicked");
        textView.setText(recievedDate);
        ShipmentFragment fragmentShip = new ShipmentFragment();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getParentFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentInside, fragmentShip);
                transaction.commit();
            }
        });

        // display the values by using a toast

        // Inflate the layout for this fragment
        return rootView;
    }
}