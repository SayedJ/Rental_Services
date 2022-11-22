package com.example.rental_services;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rental_services.Entities.Item;

public class ItemDetailsFragment extends Fragment {
    TextView name, brand, model, dateOfPurchased, itemStand, price;
    private Item item;
    Button wishListButton, bookButton;
    CardView cardView;
    ConstraintLayout constraintLayout, constraintLayoutChild;

    public ItemDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_details, container, false);
        item = (Item) getArguments().getSerializable("userItem");
        name = rootView.findViewById(R.id.detailName);
        brand = rootView.findViewById(R.id.detailBrandF);
        model = rootView.findViewById(R.id.detailModelF);
        dateOfPurchased = rootView.findViewById(R.id.detailYearF);
        itemStand = rootView.findViewById(R.id.detailStandF);
        wishListButton = rootView.findViewById(R.id.detailAddToWishList);
        bookButton = rootView.findViewById(R.id.detailBookItem);
        price = rootView.findViewById(R.id.detailPriceF);
        dateOfPurchased.setText("Today date is 2000");
        name.setText(item.getName());
        brand.setText(item.getBrand());
        model.setText(item.getModel());
//        dateOfPurchased.setText(String.valueOf(DateConverter.fromDate(item.getYearOfPurchase())));
        itemStand.setText(item.getItem_stand());
        price.setText(String.valueOf(item.getPrice()) + " kr/day");

        wishListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BookingActivity.class);
                intent.putExtra("item", item );
                startActivity(intent);
            }
        });



        // Inflate the layout for this fragment
        return rootView;

    }
}