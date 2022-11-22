package com.example.rental_services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rental_services.Entities.Item;

public class BookingActivity extends AppCompatActivity{
    private FragmentManager fragmanag;
    NavController navController;
    Navigation navigation;
    Button buttonNext;
    NavHostFragment navhost;
    Item item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        buttonNext = findViewById(R.id.nextButtonAc);
        setContentView(R.layout.activity_booking);
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

//    @Override
//    public void onDatepickerFrom() {
//        changefrag(new BookingInfoFragment());
//
//    }
//
//    @Override
//    public void onBookingItemInfo() {
//        changefrag(new DatePickerFromFragment());
//    }
//    public void changefrag(Fragment frag) {
//
//        fragmanag = getSupportFragmentManager();
//
//        if (!fragmanag.beginTransaction().isEmpty()) {
//            fragmanag.beginTransaction().detach(frag);
//        }
//        fragmanag.beginTransaction()
//                .replace(R.id.childFragment, frag).commit();
//
//    }
}