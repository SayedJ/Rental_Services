package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Entity;

@Entity(primaryKeys = {"bookingId", "itemId"})
public class BookingListItemCrossRef {
    public long bookingId;
    public long itemId;
}
