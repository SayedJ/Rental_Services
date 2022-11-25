package com.example.rental_services.DataAccessObject;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.BookingInfo;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.ItemInfo;
import com.example.rental_services.Entities.Payment;
import com.example.rental_services.Entities.Shipment;

import java.util.List;

@Dao
public interface BookingSetupDao {

    @Transaction
    @Query("SELECT * FROM booking_info")
    LiveData<List<Booking>> getBooking();

    @Transaction
    @Query("select * from booking_info where userCreatorId == :id")
    LiveData<List<Booking>> myBookings(int id);

    @Transaction
    @Insert
    void insertBooking(Booking booking);

    @Insert
    void insertBookingSetup(BookingInfo bookingInfo);

    @Transaction
    @Insert
    void insertPayment(Payment payment);

    @Transaction
    @Insert
    void insertShipment(Shipment shipment);

    @Transaction
    @Insert
    void insertItemInfo(ItemInfo itemInfo);

    @Transaction
    @Insert
    void insertItem(Item item);

    @Transaction
    @Delete
    void deleteBooking(Booking booking);







}
