package com.example.rental_services;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rental_services.databinding.FragmentUploadImageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class uploadImage extends Fragment {
    Uri imageUrl;
    FirebaseStorage imageStorage;
    StorageReference storageReference;
    FragmentUploadImageBinding binding;
    private StorageTask uploadTask;
    private StorageTask progressTask;

    public uploadImage() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUploadImageBinding.inflate(inflater, container, false);
        imageStorage = FirebaseStorage.getInstance();
        storageReference = imageStorage.getInstance().getReference("images");
        binding.uploadProgressbar.setVisibility(View.INVISIBLE);
        binding.chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");

            }
        });
        binding.uploadSelectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uploadTask != null && uploadTask.isInProgress()){
                    Toast.makeText(getActivity(), "Upload in progress", Toast.LENGTH_SHORT).show();
                }
                else{
                    uploadImage(view);
                }
                
            }
        });
        return binding.getRoot();
    }



    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                        binding.selectedImage.setImageURI(result);
                        imageUrl = result;
                    }
                }
            });

    private void uploadImage(View view) {

        if (imageUrl != null && binding.chooeNameButton !=null) {
            storageReference = imageStorage.getReference().child("images/"+UUID.randomUUID().toString());
           progressTask = storageReference.putFile(imageUrl).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double percent =  (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount() );
                    binding.uploadProgressbar.setVisibility(View.VISIBLE);
                    binding.uploadProgressbar.setProgress((int) percent);
//                    Toast.makeText(getActivity(), "Saving...", Toast.LENGTH_SHORT).show();

                }
            });

                uploadTask = storageReference.putFile(imageUrl).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {

                            Log.d("uri:", imageUrl.toString());

                            Toast.makeText(getActivity(), "Image has been saved Successfully", Toast.LENGTH_SHORT).show();

//                        Navigation.findNavController(view).navigate(R.id.action_uploadImage_to_addAProductFragment, bundle);

                        } else {
                            Toast.makeText(getActivity(), "Not Saved", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Toast.makeText(getActivity(), uri.toString(), Toast.LENGTH_SHORT).show();
                    imageUrl = uri;

                }
            });
            uploadTask.addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                    Bundle bundle = new Bundle();
                    bundle.putString("imageUri", imageUrl.toString());
                    Toast.makeText(getActivity(), imageUrl.toString() + "This is the real Url", Toast.LENGTH_SHORT).show();
                    NavController navController = NavHostFragment.findNavController(getParentFragment());
                    navController.getPreviousBackStackEntry().getSavedStateHandle().set("key", imageUrl.toString());
                    navController.popBackStack();
                }
            });

        }

    }
    private String imageName(String name, String getText){
        if(name == null){
            return getText;
        }
        return name;
    }


}