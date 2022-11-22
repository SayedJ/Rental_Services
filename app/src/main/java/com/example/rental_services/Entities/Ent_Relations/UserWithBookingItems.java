package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.User;

import java.util.List;

public class UserWithBookingItems {
    @Embedded public User user;
    @Relation(
            entity = Booking.class,
            parentColumn = "userId",
            entityColumn = "userCreatorId"

    )
    public List<BookingWithItems> bookingLists;

}
