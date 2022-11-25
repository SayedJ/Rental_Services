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

import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
import com.example.rental_services.ViewModels.ItemViewModel;

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

    public void bookItem(Booking booking){
        viewModel.addBooking(booking);
    }

}