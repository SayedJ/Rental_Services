package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Entity;

@Entity(primaryKeys = {"bookingId", "itemId"})
public class BookingListItemCrossRef {
    public int bookingId;
    public int itemId;
}
