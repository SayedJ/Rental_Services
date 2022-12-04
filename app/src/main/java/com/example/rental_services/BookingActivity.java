package com.example.rental_services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rental_services.Database.BookingWithDetails;
import com.example.rental_services.Entities.Address;
import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.BookingInfo;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.ItemInfo;
import com.example.rental_services.Entities.Payment;
import com.example.rental_services.Entities.Shipment;
import com.example.rental_services.Entities.User;
import com.example.rental_services.ViewModels.ItemViewModel;

import java.util.concurrent.ExecutionException;

public class BookingActivity extends AppCompatActivity{
    private FragmentManager fragmanag;
    NavController navController;
    Navigation navigation;
    Button buttonNext;
    ItemViewModel viewModel;
    NavHostFragment navhost;
    User user;
    Item item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        buttonNext = findViewById(R.id.nextButtonAc);
        setContentView(R.layout.activity_booking);
        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        item = (Item) getIntent().getSerializableExtra("item");
        navhost = new NavHostFragment();
        navhost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentBookingNav);
        navController = navhost.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController);
//        buttonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("item", item);
//                Navigation.findNavController(view).navigate(R.id.action_bookingInfoFragment2_to_datePickerFromFragment, bundle);
//
//            }
//        });




    }
    @Override
    public boolean onSupportNavigateUp() {

        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    public Item getItem(){
        return item;
    }

    public User getUser(){
        user = new User();
        user = (User) getIntent().getSerializableExtra("currentUser");
        return user;
    }

    public long bookItem(Booking booking) {
       return viewModel.addBooking(booking);
    }
    public long insertAllBookingInfo(Shipment shipment, ItemInfo itemInfo, BookingInfo bookingInfo, Payment payment, Address address, Booking booking, BookingWithDetails bookingWithDetails){
        long addressId = viewModel.addAddress(address);
        shipment.setAddressId(addressId);
       long shipmentId = viewModel.addShipment(shipment);
        long paymentId = viewModel.addPayment(payment);
        long bookingInforId = viewModel.addBookingInfo(bookingInfo);
        long itemInfoId = viewModel.addItemInfo(itemInfo);
       booking.setShipmentId(shipmentId);
       booking.setPaymentId(paymentId);

       booking.setBooking_info_id(bookingInforId);
       booking.setItemInfo(itemInfoId);
      long bookedId = bookItem(booking);
      bookingWithDetails.setBookingId(bookedId);
      long bookingWithDetailsId = viewModel.insertBookingWithDetails(bookingWithDetails);
        return bookedId;
    }

}