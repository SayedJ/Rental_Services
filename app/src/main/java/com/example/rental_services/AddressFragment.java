package com.example.rental_services;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rental_services.Entities.Address;
import com.example.rental_services.Entities.BookingInfo;
import com.example.rental_services.Entities.Shipment;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Date;


public class AddressFragment extends Fragment {
    EditText houseNo, streetNo, postalCode, city, country;
    Button confirmBtn;
    Address address;
    int userId;

    public AddressFragment() {
        // Required empty public constructor
    }

    public static AddressFragment newInstance(String param1, String param2) {
        AddressFragment fragment = new AddressFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_address, container, false);
        houseNo = rootView.findViewById(R.id.houseNo);
        streetNo = rootView.findViewById(R.id.streetNoEdit);
        postalCode = rootView.findViewById(R.id.postalcodeEdit);
        city = rootView.findViewById(R.id.cityEdit);
        country = rootView.findViewById(R.id.countryEdit);
        confirmBtn = rootView.findViewById(R.id.btn_address);
        BookingInfo bookingInfo = (BookingInfo) getArguments().getSerializable("date");
        Shipment shipment = (Shipment) getArguments().getSerializable("shipment");

//        String house_No, String streetName, int postalCode, String city, String country, int userCreatorId)
        
        userId = ((BookingActivity)getActivity()).getUser().getUserId();
        if(userId == 0){
            Toast.makeText(getActivity(), "userid is null", Toast.LENGTH_SHORT).show();
        }
        PaymentFragment paymentFragment = new PaymentFragment();
        confirmBtn.setOnClickListener(view -> {
            address = new Address(houseNo.getText().toString(), streetNo.getText().toString(), Integer.parseInt(postalCode.getText().toString()), city.getText().toString(), country.getText().toString(), userId);
            Bundle bundle = new Bundle();
            bundle.putSerializable("address", address);
            bundle.putSerializable("dateObj", bookingInfo);
            bundle.putSerializable("shipment", shipment);
            paymentFragment.setArguments(bundle);
            FragmentManager manager = getParentFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentInside, paymentFragment, "addressFrag").addToBackStack("addressFrag");
            transaction.commit();


        });


        // Inflate the layout for this fragment
        return rootView;
    }
}