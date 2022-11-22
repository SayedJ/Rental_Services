package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.Item;

import java.util.List;

public class ItemWithBookings{
    @Embedded
    public Item item;
    @Relation(
            parentColumn = "itemId",
            entityColumn = "bookingId",
            associateBy = @Junction(BookingListItemCrossRef.class)
    )
    public List<Booking> bookingList;
}