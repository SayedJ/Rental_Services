package com.example.rental_services;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;


public class ProfileFragment extends Fragment {

    ImageView profile, items, home, booked;
    TextView infoUser, infoItems, infoBooked;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        profile = rootView.findViewById(R.id.imageprofile);
        items = rootView.findViewById(R.id.imageItems);
        home = rootView.findViewById(R.id.imageHome);
        booked = rootView.findViewById(R.id.imageBooked);
        infoUser = rootView.findViewById(R.id.infoUser);
        infoItems = rootView.findViewById(R.id.infoItems);
        long userId = ((MainActivity)getActivity()).getCurrentUser().getUserId();
        infoUser.setText(((MainActivity)getActivity()).getCurrentUser().getUsername());
        int items= 0;
        try {
            items = ((MainActivity)getActivity()).getUserItems().size();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        infoItems.setText(String.valueOf(items));

        return rootView;
    }
}