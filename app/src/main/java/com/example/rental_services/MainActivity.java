package com.example.rental_services;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.rental_services.Adapters.ItemAdapters;
import com.example.rental_services.Adapters.UserAdapter;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
import com.example.rental_services.Fragments.ListOfItemsFragment;
import com.example.rental_services.Fragments.LoginFragment;
import com.example.rental_services.Fragments.RegisterFragment;
import com.example.rental_services.ViewModels.ItemViewModel;
import com.example.rental_services.ViewModels.UserViewModel;
import com.example.rental_services.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    UserViewModel uservm;
    long userId;
    Button btn;
    List<Item> userItems = new ArrayList<>();
    TextView welcomeText;
    UserAdapter userAdapter;
    User user;
    ItemViewModel itemviewm;
    ItemDetailsFragment detailsFragment;
    ActivityMainBinding binding;
    ListOfItemsFragment listOfItemsFragment;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ListOfItemsFragment());
        Fragment fragment = new UserItemsFragment();

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.item_list:
                    replaceFragment(new ListOfItemsFragment());
                    break;
                case R.id.person:
                    replaceFragment(new profile_With_info());
                    break;
                case R.id.booked:
                    replaceFragment(new SearchFragment());
                    break;
            }

            return true;

        });
        itemviewm = new ViewModelProvider(this).get(ItemViewModel.class);
        RecyclerView rv = findViewById(R.id.recyclerView);
        welcomeText = findViewById(R.id.welcomeText);
        user = (User) getIntent().getSerializableExtra("name");


        listOfItemsFragment = (ListOfItemsFragment) getSupportFragmentManager().findFragmentById(R.id.listofAllItems);
        userId = user.getUserId();
        try {
            itemviewm.userWithItems(userId);
            getUserItems();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        welcomeText.setText(user.getUsername());
        userAdapter = new UserAdapter(MainActivity.this);
        List<User> users = new ArrayList<User>();
        uservm = new ViewModelProvider(this).get(UserViewModel.class);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(this));

//        uservm.getAllUsers().observe(this, new Observer<List<User>>() {
//            @Override
//            public void onChanged(List<User> users) {
//                if(users != null){
//                    userAdapter.setTheUserList(users);
//                }
//            }
//        });

//        rv.setAdapter(userAdapter);

//        btn.setOnClickListener(View -> {
//            user = new User(name.getText().toString(), username.getText().toString(), email.getText().toString(), password.getText().toString(), mobile.getText().toString());
//            uservm.insert(user);
//            setToEmpty();
//
//            Toast.makeText(this, "User " + username.getText().toString()+ " been added.", Toast.LENGTH_SHORT).show();
//        });

    }

//    public void setToEmpty(){
//
//
//        email.setText("");
//        username.setText("");
//        name.setText("");
//        address.setText("");
//        mobile.setText("");
//        password.setText("");
//    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }
    public User getCurrentUser(){
        return user;
    }
    public List<Item> getUserItems() throws ExecutionException, InterruptedException {
        userItems = itemviewm.userWithItems(userId);
        if(userItems == null){
        }
        return userItems;
    }
    public int itemsCount(){
        return userItems.size();
    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        detailsFragment = new ItemDetailsFragment();
//        switch (item.getItemId()) {
//
//            case R.id.person:
//                Intent intent = new Intent(this, UserInfoActivity.class);
//                intent.putExtra("frag","profile") ;
//                startActivity(intent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }

    }

//    public void ToDetailsFragment(Item item){
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("userItem", item);
//        ItemDetailsFragment fragmentdetas = new ItemDetailsFragment();
//        fragmentdetas.setArguments(bundle);
//        fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.flFragment, fragmentdetas, "listOfItems").addToBackStack("listOfItems");
//        transaction.commit();
//    }

