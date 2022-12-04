package com.example.rental_services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
import com.example.rental_services.Interfaces.RentalInterfaces;
import com.example.rental_services.ViewModels.ItemViewModel;
import com.example.rental_services.ViewModels.UserViewModel;
import com.example.rental_services.databinding.FragmentAddAProductBinding;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;


public class AddAProductFragment extends Fragment{

    FragmentAddAProductBinding binding;
    Item item;
    User user;
    SimpleDateFormat dateFormat;
    Date date = new Date();
    Item foundItem;
    String imageUri;
    RentalInterfaces rentalInterfaces;

    FirebaseStorage imageStorageRef;
    StorageReference storageReference;
    StorageTask  uploadTask;
    Uri imageSavedUri;



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
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        binding = FragmentAddAProductBinding.inflate(inflater, container, false);
        imageStorageRef = FirebaseStorage.getInstance();
        binding.uploadingProgressBar.setVisibility(View.INVISIBLE);
        if(user == null){
            User userNew = new User();
            user = retriveUser(userNew);
            if(user == null){
                user = ((UserInfoActivity)getActivity()).getCurrentUser();

            }
        }

        binding.changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage.launch("image/*");
                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        uploadImage();
//                    }
//                }, 3000);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageUri = getDownloadUrl();
                    }
                }, 3000);

               Picasso.get().load(imageUri).into(binding.shapeableImageView3);
            }
        });

//        user = (User) getArguments().getSerializable("rentUser");
//        imageUri = getArguments().getString("imageUri");
        if (getArguments() == null) {

//            item = new Item(null, null, null, null, 0, 0, null);
            binding.btnNewProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(imageUri == null) {
                        imageUri = "https://vignette3.wikia.nocookie.net/lego/images/a/ac/No-Image-Basic.png/revision/latest?cb=20130819001030";
                    }
                    Item newItem = new Item(binding.productName.getText().toString(),
                            binding.addModel.getText().toString(),
                            binding.AddBrand.getText().toString(),
                            date,
                            Double.parseDouble(binding.itemPrice.getText().toString()),
                            user.getUserId(), binding.saveImageUrl.getText().toString());
//                    Toast.makeText(getActivity(), imageUri, Toast.LENGTH_SHORT).show();

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

            item = (Item) getArguments().getSerializable("itemToEdit");
            if(item == null){
                item = retreiveItem(new Item());
            }
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

            if(item.getImagepath() != null) {
            binding.shapeableImageView3.setImageURI(Uri.parse(item.getImagepath()));
            }

            binding.btnNewProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(imageUri == null) {
                        Toast.makeText(getActivity(), "Please Upload an image", Toast.LENGTH_SHORT).show();
                        imageUri = "https://vignette3.wikia.nocookie.net/lego/images/a/ac/No-Image-Basic.png/revision/latest?cb=20130819001030";
                        binding.shapeableImageView3.setImageURI(Uri.parse(imageUri));
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
                            Double.parseDouble(binding.itemPrice.getText().toString()),
                            user.getUserId(), imageUri.toString());
                    ItemViewModel vm = new ViewModelProvider(getActivity()).get(ItemViewModel.class);
                    vm.updateItem(updateitem);
                    Toast.makeText(getActivity(), "Item has been updated.", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    ItemDetailsFragment fragment = new ItemDetailsFragment();
                    bundle.putSerializable("userItem", updateitem);
                    bundle.putSerializable("user", user);
                    NavController navController = NavHostFragment.findNavController(getParentFragment());
//                    navController.getPreviousBackStackEntry().getSavedStateHandle().set("itemAdded", updateitem);
//                    navController.popBackStack();
                    Navigation.findNavController(view).getCurrentBackStackEntry().getSavedStateHandle();
                    navController.popBackStack();
                }
            });
        }

        return binding.getRoot();
    }


    private Item upDateItem(Item item, String name, String model, String brand, Date date, double price, long userId, String uri )
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
    ActivityResultLauncher<String> getImage = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                        binding.shapeableImageView3.setImageURI(result);
                        imageSavedUri = result;
                    }
                }
            });

    private void uploadImage(){

        if (imageSavedUri != null) {
          StorageReference storageReference = imageStorageRef.getReference().child("images/" + imageSavedUri.getLastPathSegment());
            storageReference.putFile(imageSavedUri).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double percent = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    binding.uploadingProgressBar.setVisibility(View.VISIBLE);
                    binding.uploadingProgressBar.setProgress((int) percent);


                }
            });

            uploadTask = storageReference.putFile(imageSavedUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {

                        Log.i("uri:", imageSavedUri.toString());

                        Toast.makeText(getActivity(), "Image has been saved Successfully", Toast.LENGTH_SHORT).show();
                        binding.uploadingProgressBar.setVisibility(View.GONE);
//                        Navigation.findNavController(view).navigate(R.id.action_uploadImage_to_addAProductFragment, bundle);

                    } else {
                        Toast.makeText(getActivity(), "Not Saved", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }


            });
//
        }
    }

    private String getDownloadUrl() {
        if (imageSavedUri == null) {
            Toast.makeText(getActivity(), "Please upload an image", Toast.LENGTH_SHORT).show();
        } else {
            final StorageReference ref = imageStorageRef.getReference().child("images/" + imageSavedUri.getLastPathSegment());
            uploadTask = ref.putFile(imageSavedUri);
            ref.putFile(imageSavedUri).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
              @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double percent = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    binding.uploadingProgressBar.setVisibility(View.VISIBLE);
                    binding.uploadingProgressBar.setProgress((int) percent);
                }
            });
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        imageUri = downloadUri.toString();
                        binding.saveImageUrl.setText(imageUri);

                        binding.uploadingProgressBar.setVisibility(View.INVISIBLE);
                    } else {
                        Toast.makeText(getContext(), "Upload was not completed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return imageUri;
        }
        return imageUri;
    }
}


