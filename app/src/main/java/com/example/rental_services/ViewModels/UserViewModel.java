package com.example.rental_services.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
import com.example.rental_services.Repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repo;
    private LiveData<List<User>> users;
    public UserViewModel(@NonNull Application application) {
        super(application);
        repo = UserRepository.getInstance(application);
        users = repo.getAllUsers();

    }

    public void insert(User... user){ repo.insert(user);}
//    public User findUser(String email, String password){
//
//    }
    public void insertItems(Item... item){repo.insertItem(item);}
    public void delete(User user){repo.delete(user);}
    public User login (String email, String password){
        User loginUser = repo.login(email, password);
        return loginUser;
    }

    public LiveData<List<User>> getAllUsers(){return repo.getAllUsers();}
}
