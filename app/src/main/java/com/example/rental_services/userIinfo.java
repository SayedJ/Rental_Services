package com.example.rental_services;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rental_services.Entities.User;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;


public class userIinfo extends Fragment {

    private TextView itemsNo, rentedOutNo, rentedInNo, name, username, email, mobileNo, howManyItems , howManyRenting;
    private ShapeableImageView imageView;
    private User user;

    public userIinfo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        user = ((UserInfoActivity)getActivity()).getCurrentUser();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_iinfo, container, false);
        itemsNo = view.findViewById(R.id.items);
        rentedOutNo = view.findViewById(R.id.rentingoutItems);
        rentedInNo = view.findViewById(R.id.rentingItems);
        howManyRenting = view.findViewById(R.id.howManyRenting);
        howManyItems = view.findViewById(R.id.howManyItems);
        name = view.findViewById(R.id.profileName);
        username = view.findViewById(R.id.profileUsername);
        email = view.findViewById(R.id.profileEmail);
        mobileNo = view.findViewById(R.id.profileMobile);
        imageView = view.findViewById(R.id.profileUserImage);
        name.setText(user.getFullName());
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        mobileNo.setText(user.getMobileNo());
        int counts = ((UserInfoActivity)getActivity()).itemsCount();
        howManyItems.setText(String.valueOf(counts));
        howManyRenting.setText(String.valueOf(counts));


        return view;
    }
}