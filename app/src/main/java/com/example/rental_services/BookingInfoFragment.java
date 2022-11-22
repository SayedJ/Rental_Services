package com.example.rental_services;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rental_services.Entities.Item;


public class BookingInfoFragment extends Fragment {
    Item recievedItem;
    Button button;
    TextView itemName;
    Intent intent = new Intent();
//    private ActionListener listener;

    public BookingInfoFragment() {
        // Required empty public constructor
    }
    public static BookingInfoFragment newInstance(){
        BookingInfoFragment fragment = new BookingInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        listener = (ActionListener)getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_booking_info, container, false);
        recievedItem = (Item)intent.getSerializableExtra("item");
//        button = rootView.findViewById(R.id.nextButton);
            itemName = rootView.findViewById(R.id.itemNameBook);
        recievedItem = ((BookingActivity)getActivity()).getItem();
            itemName.setText(recievedItem.getName() + " " + recievedItem.getBrand() + " " + recievedItem.getModel());
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentInside, new DatePickerFromFragment() );
        transaction.commit();


//        rootView.findViewById(R.id.datePikcerFrom).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              listener.onDatepickerFrom();
//            }
//        });


        return rootView;

    }
    public interface ActionListener{
        void onDatepickerFrom();

    }
}