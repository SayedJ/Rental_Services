package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.BookingInfo;
import com.example.rental_services.Entities.ItemInfo;

public class BookingWithItemInfo {

    @Embedded
    public Booking bookingList;
    @Relation(
            parentColumn = "bookingId",
            entityColumn = "itemInfoId"
    )
    public ItemInfo itemInfo;
}
