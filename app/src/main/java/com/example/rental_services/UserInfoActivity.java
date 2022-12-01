package com.example.rental_services;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rental_services.Adapters.UserAdapter;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
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
import java.util.concurrent.ExecutionException;

public class UserInfoActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 22;
    UserAdapter userAdapter;
    User user;
    ItemViewModel itemviewm;
    NavController navController;
    Navigation navigation;
    NavHostFragment navhost;
    ItemViewModel viewModel;
    AddAProductFragment fragment;
    private Item itemSelect;
    int userId;
    private List<Item> userItems;
    private StorageReference storageReference;
    private Uri filePath;
    private FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        itemviewm = new ViewModelProvider(this).get(ItemViewModel.class);
        user = (User) getIntent().getSerializableExtra("name");
        userId = user.getUserId();
        fragment = new AddAProductFragment();
        try {
            getUserItems();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
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
            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
        }

        return userItems;
    }

    public int itemsCount(){
        return userItems.size();
    }
    public void moveToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", user);
        startActivity(intent);
    }

    public void deleteItem(Item item){

        viewModel.deleteItem(item);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_IMAGE_REQUEST
//                && resultCode == RESULT_OK
//                && data != null
//                && data.getData() != null) {
//
//            // Get the Uri of data
//            filePath = data.getData();
//            try {
//
//                // Setting image on image view using Bitmap
//                Bitmap bitmap = MediaStore
//                        .Images
//                        .Media
//                        .getBitmap(
//                                getContentResolver(),
//                                filePath);
//                fragment.binding.shapeableImageView3.setImageBitmap(bitmap);
//            }
//
//            catch (IOException e) {
//                // Log the exception
//                e.printStackTrace();
//            }
//        }
//    }
    private void sendData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        User user = getCurrentUser();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("currentUser", json);
        editor.commit();
    }

    @Override
    public void onResume(){
        super.onResume();

        Intent intent = getIntent();

        String frag = intent.getExtras().getString("frag");

        switch(frag){

            case "detailsFragment":
                //here you can set Fragment B to your activity as usual;
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, new ItemDetailsFragment()).commit();
                break;
        }
    }




}