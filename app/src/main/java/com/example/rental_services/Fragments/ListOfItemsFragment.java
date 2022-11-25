package com.example.rental_services.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rental_services.Adapters.ItemAdapters;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.ItemDetailsFragment;
import com.example.rental_services.MainActivity;
import com.example.rental_services.R;
import com.example.rental_services.ViewModels.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListOfItemsFragment extends Fragment {

    public List<Item> items;
    public ItemViewModel itemviewModel;
    private RecyclerView.LayoutManager layoutManager = null;
    private ItemAdapters adapter = null;
    public ListOfItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Date datetime = new Date(22, 2, 2020);
//        items = new ArrayList<Item>();
//        Item item1 = new Item("Laptop", "MacBook", "Apple", datetime, 300, 1);
//        Item item2 = new Item("TV", "Android", "Samsung", datetime, 150, 2);
//        Item item3 = new Item("Radio", "Hitachi", "Motachi", datetime, 50, 30);
//        Item item4 = new Item("PS4", "Compact", "Sony", datetime, 300, 4);
//        Item item5 = new Item("Car", "I30", "Apple", datetime, 999, 5);
//        Item item6 = new Item("Gun", "Revolver", "Russian", datetime, 745, 2);
//        items.add(item1);
//        items.add(item2);
//        items.add(item3);
//        items.add(item4);
//        items.add(item5);
//        items.add(item6);
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_list_of_items, container, false);
        ItemAdapters adapter = new ItemAdapters(new ArrayList<>());
        RecyclerView rv = view.findViewById(R.id.recycler_view);
        ItemDetailsFragment fragmentDetails = new ItemDetailsFragment();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new ItemAdapters.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                ((MainActivity)getActivity()).ToDetailsFragment(item);
            }

            @Override
            public void onDeleteClick(Item item) {

            }

            @Override
            public void onUpdateClick(Item item) {

            }
        });
        itemviewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemviewModel.getAllItems().observe(
                getViewLifecycleOwner(), adapter::setTheItemList);
        return view;
    }
    private List<Item> loadData(List<Item> items){

        return items;
    }
}