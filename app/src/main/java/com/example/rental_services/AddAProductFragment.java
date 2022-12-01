package com.example.rental_services;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
import com.example.rental_services.ViewModels.ItemViewModel;
import com.example.rental_services.ViewModels.UserViewModel;
import com.example.rental_services.databinding.FragmentAddAProductBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


public class AddAProductFragment extends Fragment {

    private final int PICK_IMAGE_REQUEST = 1;
    FragmentAddAProductBinding binding;
    Item item;
    User user;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date();
    Item foundItem;
    String imageUri;
    public AddAProductFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddAProductBinding.inflate(inflater, container, false);
        if(user == null){
            User userNew = new User();
            user = retriveUser(userNew);
            if(user == null){
                user = ((UserInfoActivity)getActivity()).getCurrentUser();

            }
        }

//        user = (User) getArguments().getSerializable("rentUser");
//        imageUri = getArguments().getString("imageUri");
        if (getArguments() == null) {
            binding.changeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.action_addAProductFragment_to_uploadImage);
                }
            });
//            item = new Item(null, null, null, null, 0, 0, null);
            binding.btnNewProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Item newItem = new Item(binding.productName.getText().toString(),
                            binding.addModel.getText().toString(),
                            binding.AddBrand.getText().toString(),
                            date,
                            Double.valueOf(String.valueOf(binding.itemPrice.getText())),
                            user.getUserId(), imageUri);
                    Toast.makeText(getActivity(), imageUri, Toast.LENGTH_SHORT).show();

                    UserViewModel vm = new ViewModelProvider(getActivity()).get(UserViewModel.class);
                    vm.insertItems(newItem);
                    Toast.makeText(getActivity(), "Item has been added.", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("itemAdded", newItem);
                    NavController navController = NavHostFragment.findNavController(getParentFragment());
                    navController.getPreviousBackStackEntry().getSavedStateHandle().set("itemAdded", newItem);
                    navController.popBackStack();
//                    Navigation.findNavController(view).navigate(R.id.action_addAProductFragment_to_itemDetailsFragment);

                }
            });
        } else if (getArguments() != null) {

//            item = (Item) getArguments().getSerializable("itemToEdit");
            ItemViewModel viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
            try {
                foundItem = viewModel.getItem(item.getItemId());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            binding.AddBrand.setText(item.getBrand());
            binding.addModel.setText(item.getModel());
            binding.itemPrice.setText(String.valueOf(item.getPrice()));
            binding.productName.setText(item.getName());
            binding.itemStand.setText(item.getItem_stand());
            binding.itemPurchased.setText(String.valueOf(item.getYearOfPurchase()));
            binding.changeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.action_addAProductFragment_to_uploadImage);
                }
            });



            binding.btnNewProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(imageUri == null) {
                        Toast.makeText(getActivity(), "Please upload an image", Toast.LENGTH_SHORT).show();
                    }
                    try {
                        date = dateFormat.parse(binding.itemPurchased.getText().toString());
                        Picasso.get()
                                .load(imageUri)
                                .into(binding.shapeableImageView3);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Item updateitem = upDateItem(foundItem,binding.productName.getText().toString(),
                            binding.addModel.getText().toString(),
                            binding.AddBrand.getText().toString(),
                            date,
                            Double.valueOf(String.valueOf(binding.itemPrice.getText())),
                            user.getUserId(), imageUri.toString());
                    ItemViewModel vm = new ViewModelProvider(getActivity()).get(ItemViewModel.class);
                    vm.updateItem(updateitem);
                    Toast.makeText(getActivity(), "Item has been updated.", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    ItemDetailsFragment fragment = new ItemDetailsFragment();
                    bundle.putSerializable("userItem", updateitem);
                    bundle.putSerializable("user", user);
                    Navigation.findNavController(view).popBackStack();

//                    Navigation.findNavController(view).navigate(R.id.action_addAProductFragment_to_itemDetailsFragment, bundle);
                }
            });
        }




//        inflater.inflate(R.layout.fragment_add_a_product, container, false);
        return binding.getRoot();
    }


    private Item upDateItem(Item item, String name, String model, String brand, Date date, double price, int userId, String uri )
    {
        item.setName(name);
        item.setModel(model);
        item.setBrand(brand);
        item.setYearOfPurchase(date);
        item.setPrice(price);
        item.setUserCreatorId(userId);
        item.setImagepath(uri);
        return item;
    }


    private User retriveUser(User user){
        Gson gson = new Gson();
        SharedPreferences myPrefs = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        String json = myPrefs.getString("currentUser", "");
        user  = gson.fromJson(json, User.class);
        return user;
    }
    private Item retreiveItem(Item item){
        Gson gson = new Gson();
        SharedPreferences myPrefs = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        String jsonItem = myPrefs.getString("currentItem", "");
        item = gson.fromJson(jsonItem, Item.class);
        return item;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = NavHostFragment.findNavController(this);
        MutableLiveData<String> liveData = navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("key");
        liveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                imageUri = s;
                Picasso.get()
                        .load(imageUri)
                        .into(binding.shapeableImageView3);
            }

        });

    }
}


