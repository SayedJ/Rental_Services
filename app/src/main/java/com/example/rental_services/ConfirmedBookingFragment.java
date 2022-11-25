package com.example.rental_services;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rental_services.Entities.Address;
import com.example.rental_services.Entities.BookingInfo;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.Shipment;
import com.example.rental_services.Entities.User;

public class ConfirmedBookingFragment extends Fragment {
    BookingInfo bookingInfo;
    Shipment shipment;
    Item item;
    Address address;
    User user;
    TextView confirmTitle, detailBooking;
    Button doneButton;

    public ConfirmedBookingFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ConfirmedBookingFragment newInstance(String param1, String param2) {
        ConfirmedBookingFragment fragment = new ConfirmedBookingFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_confirmed_booking, container, false);
        getDate();
        confirmTitle = rootView.findViewById(R.id.thankyoutitle);
        detailBooking = rootView.findViewById(R.id.bookingInfo);
        doneButton = rootView.findViewById(R.id.doneButton);
        detailBooking.setText("Dear " + user.getUsername() + ": \n" + "Item: " + item.getName() + "has been booked from \n" +  bookingInfo.getFromDate().toString() + " - \n" + bookingInfo.getToDate()
                + "\n - owner Delivery = " + shipment.isOwner_delivery() + " - \n" + address.getCity()
        );
        doneButton.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });

        return rootView;
    }


    public void getDate(){
        bookingInfo = (BookingInfo) getArguments().getSerializable("dateObj");
        shipment = (Shipment) getArguments().getSerializable("shipment");
        address = (Address) getArguments().getSerializable("address");
        user = ((BookingActivity)getActivity()).getUser();
        item = ((BookingActivity)getActivity()).getItem();
    }
}