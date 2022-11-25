package com.example.rental_services.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rental_services.Database.RentalServicesDataBase;
import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
import com.example.rental_services.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ItemViewModel extends AndroidViewModel {
    private UserRepository repo;
    private final LiveData<List<Item>> items;
    private LiveData<List<Booking>> bookings;
    private LiveData<List<Booking>> myBookings;
    public ItemViewModel(@NonNull Application application) {
        super(application);
        items = RentalServicesDataBase.getInstance(getApplication()).userItemDao().loadAllItems();
        repo = UserRepository.getInstance(application);
        bookings = RentalServicesDataBase.getInstance(getApplication()).bookingDao().getBooking();



    }


    public LiveData<List<Item>> getAllItems(){return repo.getAllItems();}
    public List<Item> userWithItems(int id) throws ExecutionException, InterruptedException {

        List<Item> items = new ArrayList<>();
        items = repo.userWithItems(id);
        return items;
    }
    public LiveData<List<Item>> userWithItemsLiveData(int id){
        return repo.getuserAllItemsLive(id);
    }

    public LiveData<List<Booking>> getMyBookings(int id){
        return repo.getMyBooking(id);
    }
    public void addToWishLIst(Item item){
        repo.addToWishLIst(item);
    }

    public void deleteItem(Item item){
        repo.deleteItem(item);
    }

    public void addBooking(Booking booking){
        repo.insertBooking(booking);
    }
    public LiveData<List<Booking>> allBookings(){
        return repo.getAllBookings();
    }

}
