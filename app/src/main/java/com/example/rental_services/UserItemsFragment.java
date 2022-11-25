package com.example.rental_services;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rental_services.Adapters.ItemAdapters;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.ViewModels.ItemViewModel;

import java.util.ArrayList;

public class UserItemsFragment extends Fragment {
    ItemViewModel viewModel;
    public UserItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_user_items, container, false);
        ItemAdapters adapter = new ItemAdapters(new ArrayList<>());
        RecyclerView recyclerView  = view.findViewById(R.id.userItemsRecyclerv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener(new ItemAdapters.OnItemClickListener() {
//            @Override
//            public void onItemClick(Item item) {
//                Intent intent = new Intent(getActivity(), Deta.class);
//                intent.putExtra("item", item);
//                startActivity(intent);
//                item.getName();
//                Toast.makeText(getContext(), "this item name is  " + item.getName(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDeleteClick(Item item) {
//
//            }
//
//            @Override
//            public void onUpdateClick(Item item) {
//
//            }
//        });
        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        viewModel.getAllItems().observe(
                getViewLifecycleOwner(), adapter::setTheItemList);
        return view;
    }
}