package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.Item;

import java.util.List;

public class BookingWithItems {
    @Embedded public Booking bookingList;
    @Relation(
            parentColumn = "bookingId",
            entityColumn = "itemId",
            associateBy = @Junction(BookingListItemCrossRef.class)
    )
    public List<Item> items;

    public BookingWithItems(Booking bookingList, List<Item> items) {
        this.bookingList = bookingList;
        this.items = items;
    }
}



