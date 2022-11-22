package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.BookingInfo;

public class BookingWithBookingInfo {

    @Embedded
    public Booking booking;

    @Relation(
            parentColumn = "bookingId",
            entityColumn = "booking_info_id"
    )
    public BookingInfo bookingInfo;
}
