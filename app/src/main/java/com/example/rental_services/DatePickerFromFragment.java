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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatePickerFromFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatePickerFromFragment extends Fragment {

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
        DatePicker datePicker = view.findViewById(R.id.datePikcerFrom);
        long now = System.currentTimeMillis() - 1000;
        datePicker.setMinDate(now);
        datePicker.setMaxDate(now+(1000*60*60*24*7));
        DatePickerToFragment fragmentTo = new DatePickerToFragment();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                Bundle bundle = new Bundle();
                String datePicked = "Your pickup date is: \n" + day + "-" + month + "-" + year;

                bundle.putString("datePicked", datePicked );
                fragmentTo.setArguments(bundle);

                // display the values by using a toast
                Toast.makeText(getContext(), day + "\n" + month + "\n" + year, Toast.LENGTH_LONG).show();

                FragmentManager manager = getParentFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentInside, fragmentTo);
                transaction.commit();
            }
        });
        return view;
    }

}