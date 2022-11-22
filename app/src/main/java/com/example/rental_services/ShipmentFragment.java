package com.example.rental_services;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rental_services.Entities.Shipment;
import com.google.android.material.imageview.ShapeableImageView;


public class ShipmentFragment extends Fragment {
    Shipment shipCollection;
    ShapeableImageView delivery, collecting, shipment;
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
        AddressFragment addressFragment = new AddressFragment();
        Bundle bundle = new Bundle();

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.findViewById(R.id.deliveryIcon) == delivery){
                    delivery.setStrokeWidth(5);
                    Bundle bundle = new Bundle();
                    shipCollection = new Shipment(true, false, false, 0);
                    bundle.putSerializable("shipment", shipCollection);
                    FragmentManager manager = getParentFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragmentInside, addressFragment);
                    transaction.commit();
                }
                else if(view == collecting) {
                    collecting.setStrokeWidth(5);
                    bundle.putSerializable("shipment", shipCollection);
                    shipCollection = new Shipment(false, true, false, 0);
                }
                    else if( view == shipment){
                        
                        shipment.setStrokeWidth(5);
                    bundle.putSerializable("shipment", shipCollection);
                        Toast.makeText(getContext(), "not fixed yet", Toast.LENGTH_SHORT).show();
                    shipCollection = new Shipment(false, false, false, 0);
                    }
                    else{
                    Toast.makeText(getActivity(), String.valueOf(view.getId()), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }
}