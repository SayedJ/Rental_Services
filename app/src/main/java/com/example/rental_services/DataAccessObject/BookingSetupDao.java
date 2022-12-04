package com.example.rental_services.DataAccessObject;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.rental_services.Database.BookingWithDetails;
import com.example.rental_services.Entities.Address;
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
    LiveData<List<Booking>> myBookings(long id);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBooking(Booking booking);
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBookingSetup(BookingInfo bookingInfo);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPayment(Payment payment);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertShipment(Shipment shipment);
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertAddress(Address address);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertItemInfo(ItemInfo itemInfo);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertItem(Item item);

    @Transaction
    @Delete
    void deleteBooking(Booking booking);
//    From booking_date_setup, items_info, payment, shipment_info, item_availability_info

//    @Query("SELECT booking_info.bookingId as bookingId,  items_info.name AS itemName, items_info.brand as itemBrand, booking_date_setup.fromDate as fromDate, booking_date_setup.toDate as toDate, payment.creditCard as payment, shipment_info.owner_delivery as delivery, item_availability_info.rented as availibility "+
//            "From booking_info, items_info, booking_date_setup,payment, shipment_info, item_availability_info " +
//            "where booking_info.itemId= items_info.itemId AND booking_info.booking_info_id = booking_date_setup.booking_info_id And booking_info.paymentId = payment.paymentId and booking_info.shipmentId = shipment_info.shipmentId and booking_info.itemInfo = item_availability_info.itemInfoId and booking_info.userCreatorId = :id" )
    @Query("select * from bookingwithdetails where renterId = :id")
    LiveData<List<BookingWithDetails>> getBookingWithDetails(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBookingWithDetails(BookingWithDetails bookingWithDetails);



}
