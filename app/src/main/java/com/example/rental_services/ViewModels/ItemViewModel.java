package com.example.rental_services.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rental_services.Database.RentalServicesDataBase;
import com.example.rental_services.Entities.Address;
import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.BookingInfo;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.ItemInfo;
import com.example.rental_services.Entities.Payment;
import com.example.rental_services.Entities.Shipment;
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
    public Item getItem(int itemId) throws ExecutionException, InterruptedException {
        Item item;
        item = repo.getItem(itemId);
        return item;
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

    public void addBooking(Booking booking) {
        repo.insertBooking(booking);
    }
    public LiveData<List<Booking>> allBookings(){
        return repo.getAllBookings();
    }
    public void addAddress(Address address){
        repo.insertAddress(address);
    }
    public void addPayment(Payment payment){
        repo.insertPayment(payment);
    }
    public void addItemInfo(ItemInfo itemInfo){
        repo.insertItemInfo(itemInfo);
    }
    public void addBookingInfo(BookingInfo bookingInfo){
        repo.insertBookingInfo(bookingInfo);
    }
    public void addShipment(Shipment shipment){
        repo.insertShipment(shipment);
    }
    public void updateItem(Item item){repo.updateItem(item);}

}
