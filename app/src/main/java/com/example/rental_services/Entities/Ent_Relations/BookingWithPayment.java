package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Relation;

import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.Payment;

public class BookingWithPayment {

    public Booking bookingList;
    @Relation(
            parentColumn = "bookingId",
            entityColumn = "paymentId"
    )
    public Payment payment;
}
