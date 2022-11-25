package com.example.rental_services;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;

public class ItemDetailsFragment extends Fragment {
    TextView name, brand, model, dateOfPurchased, itemStand, price;
    private Item item;
    Button wishListButton, bookButton;
    ImageButton  deleteBtn, updateBtn;
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
        deleteBtn = rootView.findViewById(R.id.deleteButton);
        updateBtn = rootView.findViewById(R.id.update_btn);

        dateOfPurchased.setText("Today date is 2000");
        name.setText(item.getName());
        brand.setText(item.getBrand());
        model.setText(item.getModel());
//        dateOfPurchased.setText(String.valueOf(DateConverter.fromDate(item.getYearOfPurchase())));
        itemStand.setText(item.getItem_stand());
        price.setText(String.valueOf(item.getPrice()) + " kr/day");
        User user = ((MainActivity)getActivity()).getCurrentUser();
        if(user.getUserId() == item.getUserCreatorId()){
            bookButton.setVisibility(View.GONE);
            wishListButton.setVisibility(View.GONE);
        }
        else{
            deleteBtn.setVisibility(View.GONE);
            updateBtn.setVisibility(View.GONE);
        }

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
                intent.putExtra("currentUser", user);
                startActivity(intent);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user.getUserId() == item.getUserCreatorId()) {
                    new AlertDialog.Builder(getActivity()).setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Do you really want to delete " + item.getName())
                            .setMessage("Please note that once it is deleted, there is no way to get it back")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    ((UserInfoActivity) getActivity()).deleteItem(item);
                                    ((UserInfoActivity) getActivity()).moveToMainActivity();
                                }
                            }).setNegativeButton("No", null).show();
                }
                Toast.makeText(getActivity(), "You are not allowed to Delete this Item", Toast.LENGTH_SHORT).show();

            }
        });




        // Inflate the layout for this fragment
        return rootView;

    }
}