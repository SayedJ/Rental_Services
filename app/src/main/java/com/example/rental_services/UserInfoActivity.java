package com.example.rental_services;

import static android.content.ContentValues.TAG;
import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rental_services.Adapters.UserAdapter;
import com.example.rental_services.Database.BookingWithDetails;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
import com.example.rental_services.Interfaces.RentalInterfaces;
import com.example.rental_services.ViewModels.ItemViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UserInfoActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 22;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    UserAdapter userAdapter;
    User user;
    ItemViewModel itemviewm;
    NavController navController;
    Navigation navigation;
    NavHostFragment navhost;
    ItemViewModel viewModel;
    AddAProductFragment fragment;
    private Item itemSelect;
    long userId;
    private List<Item> userItems;
    private StorageReference storageReference;
    private Uri filePath;
    private FirebaseStorage storage;
    AddAProductFragment addAfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        itemviewm = new ViewModelProvider(this).get(ItemViewModel.class);
        user = (User) getIntent().getSerializableExtra("name");
        addAfragment = new AddAProductFragment();

        userId = user.getUserId();
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragment = new AddAProductFragment();
        try {
            getUserItems();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String fragmentTag = "FragmentDetails";
        int fragmentId = getIntent().getIntExtra("fragment", 0);
        itemSelect = (Item) getIntent().getSerializableExtra("itemAdded");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        if (fragmentId == 1) {
            ItemDetailsFragment fragment = new ItemDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("itemAdded", itemSelect);
            bundle.putSerializable("name", user);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();

        }else {
            viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
            navhost = new NavHostFragment();

//
            navhost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
            navController = navhost.getNavController();
            NavigationUI.setupActionBarWithNavController(this, navController);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {

        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    public User getCurrentUser() {
        return user;
    }

    public List<Item> getUserItems() throws ExecutionException, InterruptedException {
        userItems = itemviewm.userWithItems(userId);
        if (userItems == null) {
            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
        }

        return userItems;
    }

    public int itemsCount() {
        return userItems.size();
    }

    public void moveToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", user);
        startActivity(intent);
    }

    public void deleteItem(Item item) {

        viewModel.deleteItem(item);
    }

    private void sendData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        User user = getCurrentUser();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("currentUser", json);
        editor.commit();
    }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}