package com.example.rental_services.Fragments;

import static com.example.rental_services.R.id.flFragment;
import static com.example.rental_services.R.id.fragmentContainerView;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rental_services.Adapters.ItemAdapters;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
import com.example.rental_services.ItemDetailsFragment;
import com.example.rental_services.MainActivity;
import com.example.rental_services.R;
import com.example.rental_services.UserInfoActivity;
import com.example.rental_services.ViewModels.ItemViewModel;
import com.example.rental_services.profile_With_info;

import java.util.ArrayList;
import java.util.List;

public class ListOfItemsFragment extends Fragment {

    public List<Item> items;
    public ItemViewModel itemviewModel;
    private RecyclerView.LayoutManager layoutManager = null;
    private ItemAdapters adapter = null;
    User user;
    public ListOfItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        user = ((MainActivity)getActivity()).getCurrentUser();
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new ItemAdapters.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                final ItemDetailsFragment detailsFragment = new ItemDetailsFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable("name", user);
                bundle.putSerializable("itemAdded", item);
                detailsFragment.setArguments(bundle);
                Intent i = new Intent(getContext(), UserInfoActivity.class);
                i.putExtras(bundle);
                i.putExtra("fragment", 1);
                startActivity(i);
//
//               NavHostFragment fragmentHost = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(flFragment);
//                NavController navController = fragmentHost.getNavController();
//                navController.navigateUp();
//                navController.navigate(R.id.itemDetailsFragment);


//                getParentFragmentManager().beginTransaction().replace(R.id.item_list,profileFragment).commit();
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
}