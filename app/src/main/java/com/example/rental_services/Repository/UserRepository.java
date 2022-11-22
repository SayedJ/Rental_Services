package com.example.rental_services.Repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.rental_services.DataAccessObject.ItemDao;
import com.example.rental_services.DataAccessObject.UserItemsDao;
import com.example.rental_services.Database.RentalServicesDataBase;
import com.example.rental_services.Entities.Ent_Relations.UserWithItems;

import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;

import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UserRepository {

    private UserItemsDao userItemsDao;
    private LiveData<List<User>> allUsers;
    private ItemDao itemdao;
    private LiveData<List<UserWithItems>> usersWithItems;
    private ExecutorService executors;
    private Handler mainThreadHandler;
    public static UserRepository instance;
    private UserRepository(Application application){
        RentalServicesDataBase db = RentalServicesDataBase.getInstance(application);
        userItemsDao = db.userItemDao();
        itemdao = db.itemDao();
        allUsers = userItemsDao.loadAllUser();
//        usersWithItems = userItemsDao.loadUserAndItems();
        executors = Executors.newFixedThreadPool(2);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());


    }
    public static synchronized UserRepository getInstance(Application application)
    {
        if(instance == null)
        {
            instance = new UserRepository(application);
        }
        return instance;
    }

    public void insert(User... user) {
        executors.execute(() -> userItemsDao.insertUser(user));
    }
    public void insertItem(Item... item){ executors.execute(() -> userItemsDao.insertItems(item));}

    public void delete(User user){
        executors.execute(() -> userItemsDao.deleteUser(user));
    }
    public User login(String email, String password){
        return userItemsDao.login(email, password);
    }
    public LiveData<List<User>> getAllUsers(){
     return  userItemsDao.loadAllUser();
    }
    public LiveData<List<Item>> getAllItems(){return userItemsDao.loadAllItems();}
    public void addToWishLIst(Item item){executors.execute(()-> itemdao.insertItemInWishList(item));}
    public List<Item> userWithItems(int id)throws ExecutionException, InterruptedException {
        Callable<List<Item>> callable = new Callable<List<Item>>() {
            @Override
            public List<Item> call() throws Exception {
                return itemdao.getUserItems(id);
            }
        };
        Future<List<Item>> future = Executors.newSingleThreadExecutor().submit(callable);
        return future.get();


    }
    public LiveData<List<Item>> getuserAllItemsLive(int id){return itemdao.getUserItemsLive(id);};
//    public LiveData<List<UserWithItems>> getUsersWithItems(){
//        return userItemsDao.loadUserAndItems();
    }


