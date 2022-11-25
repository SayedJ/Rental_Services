package com.example.rental_services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rental_services.Entities.Item;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class profile_With_info extends Fragment {
    ImageButton imageItemsF, imageprofileUser,imageHomeF, imageBookedF;
    TextView infoUserUser, myItems;
    public List<Item> userItems;

    public profile_With_info() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile__with_info, container, false);
        imageItemsF = rootView.findViewById(R.id.imageItemsF);
        imageBookedF = rootView.findViewById(R.id.imageBookedF);
        imageprofileUser = rootView.findViewById(R.id.imageprofileUser);
        imageHomeF = rootView.findViewById(R.id.imageHomeF);
        infoUserUser = rootView.findViewById(R.id.infoUserUser);
        myItems = rootView.findViewById(R.id.infoItems);
        infoUserUser.setText(((UserInfoActivity)getActivity()).getCurrentUser().getUsername());


        try {
            myItems.setText(String.valueOf(getMyItems().size()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imageItemsF.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("userId", getUserId());

                Navigation.findNavController(view).navigate(R.id.action_profile_With_info_to_userItemsAddedFragment, bundle);
            }
        });

        imageBookedF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_profile_With_info_to_itemUserBooked);
            }
        });
        imageprofileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_profile_With_info_to_userIinfo);
            }
        });
        imageHomeF.setOnClickListener(view -> {
            ((UserInfoActivity)getActivity()).moveToMainActivity();
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }

    public List<Item> getMyItems() throws ExecutionException, InterruptedException {
        userItems = ((UserInfoActivity)getActivity()).getUserItems();
        return userItems;
    }
    public int getUserId(){
        int userid = ((UserInfoActivity)getActivity()).getCurrentUser().getUserId();
        return userid;
    }




}