package com.example.rental_services.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
import com.example.rental_services.MainActivity;
import com.example.rental_services.R;
import com.example.rental_services.UserInfoActivity;
import com.example.rental_services.ViewModels.UserViewModel;

import java.io.Serializable;
import java.util.List;

public class LoginFragment extends Fragment {


    EditText email, password;
    Button btn;
    UserViewModel viewModel;

    User user;

    public LoginFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        Button clickButton = rootView.findViewById(R.id.btn_login);
        email = rootView.findViewById(R.id.et_email);
        password = rootView.findViewById(R.id.et_password);
        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);


        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User newUser = null;
                String enteredEmail = email.getText().toString();
                String enteredPassword = password.getText().toString();
                if(enteredEmail.isEmpty() || enteredPassword.isEmpty()){
                    Toast.makeText(getContext(), "fill all the fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User newUser = viewModel.login(enteredEmail, enteredPassword);
                            if (newUser == null) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(), "user is null", Toast.LENGTH_SHORT).show();
                                    }
                                });

//                        Intent intent = new Intent(getActivity(), MainActivity.class);
////                        intent.putExtra("loginUser", newUser);
//                        startActivity(intent);
                            } else {
//                                String name = newUser.getFullName();
                                startActivity(new Intent(getActivity(), UserInfoActivity.class).putExtra("name", newUser));
                            }
                        }
                    }).start();



//                    Toast.makeText(getContext(), "button in fragment 1" + enteredEmail, Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        return rootView;

//        email = container.findViewById(R.id.email);
//        password = container.findViewById(R.id.password);
//        btn = container.findViewById(R.id.btn_login);

    }

//    public LiveData<User> login(String email, String password){
//       LiveData<User> user = viewModel.login(email, password);
//        if(user != null)
//        {
//            return user;
//        }
//        return null;
//    }
}
