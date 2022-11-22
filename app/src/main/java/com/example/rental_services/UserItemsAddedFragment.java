package com.example.rental_services;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rental_services.Adapters.ItemAdapters;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.ViewModels.ItemViewModel;

import java.util.ArrayList;
import java.util.List;


public class UserItemsAddedFragment extends Fragment {

    LiveData<Item> userItems;
    private RecyclerView recyclerView;
    int userId;
    private  ItemAdapters adapters;;
    TextView userItemCounted;
    String item = " ";
    public Item recieviedItem;
    public UserItemsAddedFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        userId = getArguments().getInt("userId");
        ItemViewModel viewModel = new ViewModelProvider(getActivity()).get(ItemViewModel.class);
         View rootView = inflater.inflate(R.layout.fragment_user_items_added, container, false);
        getActivity().setTitle("My Items");
        userItemCounted = rootView.findViewById(R.id.userItemCounted);
        recyclerView = rootView.findViewById(R.id.recyclerView2);
        recyclerView.hasFixedSize();
        adapters = new ItemAdapters(new ArrayList<>());
        recyclerView.setAdapter(adapters);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.userWithItemsLiveData(userId).observe(getActivity(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {

                adapters.setTheItemList(items);
                item = getRightItem(items.size());
                userItemCounted.setText("You have "+ String.valueOf(items.size()) + item);

            }
        });

        adapters.setOnItemClickListener(new ItemAdapters.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("userItem", item);
                Navigation.findNavController(rootView).navigate(R.id.action_userItemsAddedFragment_to_itemDetailsFragment, bundle);

            }

            @Override
            public void onDeleteClick(Item item) {

            }

            @Override
            public void onUpdateClick(Item item) {

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    public String getRightItem(int size){
        if(size > 1)
            item = " items";
        else item = " item";
        return item;

    }
}