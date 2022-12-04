package com.example.rental_services;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rental_services.Adapters.BookingAdapters;
import com.example.rental_services.Adapters.ItemAdapters;
import com.example.rental_services.Database.BookingWithDetails;
import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.User;
import com.example.rental_services.ViewModels.ItemViewModel;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ItemUserBooked extends Fragment {

    private long userId;
     RecyclerView rc;
    private TextView rentedItems;
    private User user;
    private BookingAdapters adapters;
    private ItemViewModel viewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        userId = ((UserInfoActivity)getActivity()).getCurrentUser().getUserId();
        ItemViewModel viewModel = new ViewModelProvider(getActivity()).get(ItemViewModel.class);
        View view = inflater.inflate(R.layout.fragment_item_user_booked, container, false);
        getActivity().setTitle("Rented Items");
        rentedItems = view.findViewById(R.id.rentedItems);
        rc = view.findViewById(R.id.rc);
        rc.hasFixedSize();
        user = ((UserInfoActivity)getActivity()).getCurrentUser();
        adapters = new BookingAdapters(new ArrayList<>());
        rc.setAdapter(adapters);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel.getAllMyBookings(userId).observe(getActivity(), new Observer<List<BookingWithDetails>>() {
            @Override
            public void onChanged(List<BookingWithDetails> bookingWithDetails) {

                adapters.setTheItemList(bookingWithDetails);
                rentedItems.setText(String.valueOf(bookingWithDetails.size()));
            }
        });
        adapters.setOnItemClickListener(new BookingAdapters.OnItemClickListener() {
            @Override
            public void onItemClick(BookingWithDetails item) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("itemAdded", (Serializable) item);
            }

            @Override
            public void onDeleteClick(BookingWithDetails item) {

            }

            @Override
            public void onUpdateClick(BookingWithDetails item) {

            }
        });

        return view;
    }
}