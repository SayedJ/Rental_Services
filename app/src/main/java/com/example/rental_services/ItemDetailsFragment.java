package com.example.rental_services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
import com.example.rental_services.Fragments.LoginFragment;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemDetailsFragment extends Fragment {
    TextView name, brand, model, dateOfPurchased, itemStand, price;
    private Item item;
    Button wishListButton, bookButton;
    ImageButton  deleteBtn, updateBtn;
    public ItemDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_details, container, false);
        item = (Item) getArguments().getSerializable("itemAdded");
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
        sendItemData();
//        dateOfPurchased.setText(item.getYearOfPurchase().toString());
        name.setText(item.getName());
        brand.setText(item.getBrand());
        model.setText(item.getModel());
        itemStand.setText(item.getItem_stand());
        price.setText(item.getPrice() + " kr/day");
        User user = (User) getArguments().getSerializable("user");

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
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAProductFragment update = new AddAProductFragment();
                Bundle bunle = new Bundle();
                bunle.putSerializable("itemToEdit", item);
                bunle.putSerializable("rentUser", user);
//                update.setArguments(bunle);

                Navigation.findNavController(view).navigate(R.id.action_itemDetailsFragment_to_addAProductFragment, bunle);
//                getParentFragmentManager().beginTransaction().replace(R.id.itemDetailsFragment, update);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user.getUserId() == item.getUserCreatorId()) {
                    new AlertDialog.Builder(getActivity()).setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Do you really want to delete " + item.getName()+"?")
                            .setMessage("Please note that once it is deleted, there is no way to get it back!")
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

    private void sendItemData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", getActivity().MODE_PRIVATE);
        Item userItem = item;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userItem);
        editor.putString("currentItem", json);
        editor.commit();
    }
    private Item retreiveItem(Item item){
        Gson gson = new Gson();
        SharedPreferences myPrefs = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        String jsonItem = myPrefs.getString("currentItem", "");
        item = gson.fromJson(jsonItem, Item.class);
        return item;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = NavHostFragment.findNavController(this);
        MutableLiveData<Item> liveData = navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("itemAdded");
        liveData.observe(getViewLifecycleOwner(), new Observer<Item>() {
            @Override
            public void onChanged(Item addedItem) {
                item = addedItem;
            }
        });
    }
}