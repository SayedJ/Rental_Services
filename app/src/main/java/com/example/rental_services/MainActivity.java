package com.example.rental_services;
import android.os.Bundle;
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
    EditText email;
    EditText username;
    EditText name;
    EditText address;
    EditText mobile;
    EditText password;
    int userId;
    Button btn;
    List<Item> userItems = new ArrayList<>();
    TextView welcomeText;
    UserAdapter userAdapter;
    User user;
    ItemViewModel itemviewm;
    BottomNavigationView bottomNavigationView;
    ActivityMainBinding binding;
    ItemAdapters itemAdapter;
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
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.booked:
                    replaceFragment(new SearchFragment());
                    break;
            }

            return true;

        });
        itemviewm = new ViewModelProvider(this).get(ItemViewModel.class);
        RecyclerView rv = findViewById(R.id.recyclerView);
//        email = findViewById(R.id.email);
//        username = findViewById(R.id.username);
//        name = findViewById(R.id.name);
//        address = findViewById(R.id.address);
//        mobile = findViewById(R.id.mobile);
//        btn = findViewById(R.id.button);
//        password = findViewById(R.id.password);
        welcomeText = findViewById(R.id.welcomeText);
        user = (User) getIntent().getSerializableExtra("name");
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
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setSelectedItemId(R.id.person);
        List<User> users = new ArrayList<User>();
//

        uservm = new ViewModelProvider(this).get(UserViewModel.class);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(this));

        uservm.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if(users != null){
                    userAdapter.setTheUserList(users);
                }
            }
        });

        rv.setAdapter(userAdapter);

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
    private void replaceFragment(Fragment fragment){
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
            Toast.makeText(this, "useritem is null", Toast.LENGTH_SHORT).show();
        }
        return userItems;
    }
    public int itemsCount(){
        return userItems.size();
    }
}

