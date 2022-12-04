package com.example.rental_services.Repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;


import com.example.rental_services.DataAccessObject.BookingSetupDao;
import com.example.rental_services.DataAccessObject.ItemDao;
import com.example.rental_services.DataAccessObject.UserItemsDao;
import com.example.rental_services.Database.BookingWithDetails;
import com.example.rental_services.Database.RentalServicesDataBase;
import com.example.rental_services.Entities.Address;
import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.BookingInfo;
import com.example.rental_services.Entities.Ent_Relations.UserWithItems;

import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.ItemInfo;
import com.example.rental_services.Entities.Payment;
import com.example.rental_services.Entities.Shipment;
import com.example.rental_services.Entities.User;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UserRepository {

    private UserItemsDao userItemsDao;
    private BookingSetupDao bookingDao;
    private LiveData<List<User>> allUsers;
    private ItemDao itemdao;
    private LiveData<List<UserWithItems>> usersWithItems;
    private ExecutorService executors;
    private Handler mainThreadHandler;
    public static UserRepository instance;
    private UserRepository(Application application){
        RentalServicesDataBase db = RentalServicesDataBase.getInstance(application);
        userItemsDao = db.userItemDao();
        bookingDao = db.bookingDao();
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
    public LiveData<List<BookingWithDetails>> getAllBookingWithDetails(long id){ return bookingDao.getBookingWithDetails(id);}
    public void addToWishLIst(Item item){executors.execute(()-> itemdao.insertItemInWishList(item));}
    public List<Item> userWithItems(long id)throws ExecutionException, InterruptedException {
        Callable<List<Item>> callable = new Callable<List<Item>>() {
            @Override
            public List<Item> call() throws Exception {
                return itemdao.getUserItems(id);
            }
        };
        Future<List<Item>> future = Executors.newSingleThreadExecutor().submit(callable);
        return future.get();


    }
//    public LiveData<Item> getItem(int itemId){return itemdao.getItem(itemId);}
    public Item getItem(long itemId) throws ExecutionException, InterruptedException{
        Callable<Item> callable = (Callable<Item>) () -> itemdao.getItem(itemId);
        Future<Item> future = Executors.newSingleThreadExecutor().submit(callable);
        return future.get();
    }
    public long insertBooking(Booking booking){
        Callable<Long> callable = (Callable<Long>) () ->  bookingDao.insertBooking(booking);
        long rowId = 0;
        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);
        try{
            rowId = future.get();
        } catch (InterruptedException e1){
            e1.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return rowId;
    }
    public long insertBookingWithDetails(BookingWithDetails bookingWithDetails){
        Callable<Long> callable = (Callable<Long>) () ->  bookingDao.insertBookingWithDetails(bookingWithDetails);
        long rowId = 0;
        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);
        try{
            rowId = future.get();
        } catch (InterruptedException e1){
            e1.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return rowId;

    }
    public long insertBookingInfo(BookingInfo bookingInfo){
        Callable<Long> callable = (Callable<Long>) () -> bookingDao.insertBookingSetup(bookingInfo);
        long rowId = 0;
        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);
        try{
            rowId = future.get();
        } catch (InterruptedException e1){
            e1.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return rowId;

    }
    public long insertShipment(Shipment shipment){
        Callable<Long> callable = (Callable<Long>) () -> bookingDao.insertShipment(shipment);
        long rowId = 0;
        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);
        try{
            rowId = future.get();
        } catch (InterruptedException e1){
            e1.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return rowId;

    }
    public long insertAddress(Address address){

        Callable<Long> callable = (Callable<Long>) () ->bookingDao.insertAddress(address);
        long rowId = 0;
        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);
        try{
            rowId = future.get();
        } catch (InterruptedException e1){
            e1.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return rowId;


    }
    public long insertPayment(Payment payment){

        Callable<Long> callable = (Callable<Long>) () -> bookingDao.insertPayment(payment);
        long rowId = 0;
        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);
        try{
            rowId = future.get();
        } catch (InterruptedException e1){
            e1.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return rowId;

    }
    public long insertItemInfo(ItemInfo itemInfo){
        Callable<Long> callable = (Callable<Long>) () -> bookingDao.insertItemInfo(itemInfo);
        long rowId = 0;
        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);
        try{
            rowId = future.get();
        } catch (InterruptedException e1){
            e1.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return rowId;
    }

    public LiveData<List<Booking>> getMyBooking(long id){return bookingDao.myBookings(id);}
    public LiveData<List<Booking>> getAllBookings(){return bookingDao.getBooking();}
    public LiveData<List<Item>> getuserAllItemsLive(long id){return itemdao.getUserItemsLive(id);};
//    public LiveData<List<UserWithItems>> getUsersWithItems(){
//        return userItemsDao.loadUserAndItems();
    public void deleteItem(Item item)
    {
        executors.execute(()-> itemdao.deleteItem(item));
    }
    public void updateItem(Item item){executors.execute(()-> itemdao.updateItem(item));}
    }





