package com.example.rental_services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.example.rental_services.Adapters.ItemAdapters;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Fragments.ListOfItemsFragment;

public class DetailItemActivity extends AppCompatActivity {
    Fragment fragment;
    Item item;
    TextView name, brand, model, user, desc, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
        fragment = new UserItemsAddedFragment();
        name = findViewById(R.id.detailName);
        brand = findViewById(R.id.detailBrand);
        model = findViewById(R.id.detailModel);
        user = findViewById(R.id.detailUser);
        desc = findViewById(R.id.descriptionTitle);
        price = findViewById(R.id.detailPrice);

        item = (Item) getIntent().getSerializableExtra("userItem");

        name.setText(item.getName());
        brand.setText(item.getBrand());
        model.setText(item.getModel());
        user.setText(String.valueOf(item.getUserCreatorId()));
        desc.setText(String.valueOf(item.getItem_stand()));
        price.setText(String.valueOf(item.getPrice()));


    }
}