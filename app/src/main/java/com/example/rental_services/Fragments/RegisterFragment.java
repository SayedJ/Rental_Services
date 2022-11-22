package com.example.rental_services.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rental_services.R;
import com.example.rental_services.ViewModels.ItemViewModel;

public class RegisterFragment extends Fragment {
    ItemViewModel viewModel;
    EditText name, email, password, rePassword;
    Button registerButton;
    public RegisterFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        name = rootView.findViewById(R.id.et_name);
        email = rootView.findViewById(R.id.et_email);
        password = rootView.findViewById(R.id.et_password);
        rePassword = rootView.findViewById(R.id.et_repassword);
        registerButton = rootView.findViewById(R.id.btn_register);
        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerButton.setText(name.getText().toString());
                Toast.makeText(getContext(), "button in fragment 2", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }



}