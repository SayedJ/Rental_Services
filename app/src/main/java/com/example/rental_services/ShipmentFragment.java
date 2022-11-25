package com.example.rental_services;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rental_services.Entities.BookingInfo;
import com.example.rental_services.Entities.Shipment;
import com.google.android.material.imageview.ShapeableImageView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;


public class ShipmentFragment extends Fragment {
    Shipment shipCollection;
    ShapeableImageView delivery, collecting, shipment;
    TextView fromDatePicked, toDatePicked, betweenDate;
    BookingInfo bookingInfo = null;
    public static ShipmentFragment newInstance(String param1, String param2) {
        ShipmentFragment fragment = new ShipmentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_shipment, container, false);
        delivery = rootView.findViewById(R.id.deliveryIcon);
        collecting = rootView.findViewById(R.id.pickupIcon);
        shipment = rootView.findViewById(R.id.glsIcon);
        fromDatePicked = rootView.findViewById(R.id.shipmentFromDate);
        toDatePicked = rootView.findViewById(R.id.shipmentToDate);
        betweenDate = rootView.findViewById(R.id.betweenText);
        AddressFragment addressFragment = new AddressFragment();
        Bundle bundle = new Bundle();
        bookingInfo = (BookingInfo) getArguments().getSerializable("date");
        fromDatePicked.setText(String.valueOf(bookingInfo.getFromDate()));
        toDatePicked.setText(String.valueOf(bookingInfo.getToDate()));
        LocalDate fromDate = Instant.ofEpochMilli(bookingInfo.getFromDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate toDate = Instant.ofEpochMilli(bookingInfo.getToDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        int numDays = Period.between(fromDate, toDate).getDays();

        betweenDate.setText(String.valueOf(numDays));

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    delivery.setStrokeWidth(5);
                    Bundle bundle = new Bundle();
                    shipCollection = new Shipment(true, false, false, 0);
                    bundle.putSerializable("shipment", shipCollection);
                    bundle.putSerializable("date", bookingInfo);
                    FragmentManager manager = getParentFragmentManager();
                    addressFragment.setArguments(bundle);
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragmentInside, addressFragment,"fragmentShipment").addToBackStack("fragmentShipment");
                    transaction.commit();

            }
        });
        shipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    shipment.setStrokeWidth(5);
                    bundle.putSerializable("shipment", shipCollection);
//                    Toast.makeText(getContext(), "not fixed yet", Toast.LENGTH_SHORT).show();
                    shipCollection = new Shipment(false, false, false, 0);
                Toast.makeText(getActivity(), "This Button is not active at the moment", Toast.LENGTH_SHORT).show();

            }
        });
        collecting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    collecting.setStrokeWidth(5);
                    bundle.putSerializable("shipment", shipCollection);
                    shipCollection = new Shipment(false, true, false, 0);
                Toast.makeText(getActivity(), "This is Button pick up  Button", Toast.LENGTH_SHORT).show();

            }
        });
        return rootView;
    }
}