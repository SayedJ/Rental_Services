package com.example.rental_services;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.Toast;

import com.example.rental_services.Adapters.UserAdapter;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
import com.example.rental_services.ViewModels.ItemViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserInfoActivity extends AppCompatActivity{
    UserAdapter userAdapter;
    User user;
    ItemViewModel itemviewm;
    NavController navController;
    Navigation navigation;
    NavHostFragment navhost;

    int userId;
    private List<Item> userItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        itemviewm = new ViewModelProvider(this).get(ItemViewModel.class);
        user = (User) getIntent().getSerializableExtra("name");
        userId = user.getUserId();
        try {
            getUserItems();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        navhost = new NavHostFragment();
      navhost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
      navController = navhost.getNavController();
      NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {

        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    public User getCurrentUser(){
        return user;
    }
    public List<Item> getUserItems() throws ExecutionException, InterruptedException {
        userItems = itemviewm.userWithItems(userId);
        if(userItems == null){
            Toast.makeText(this, "useritem is null", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "useritem is not null", Toast.LENGTH_SHORT).show();
        return userItems;
    }
    public int itemsCount(){
        return userItems.size();
    }



}