package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.BookingInfo;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.Shipment;

import java.util.List;

public class BookingWithShipment {

    @Embedded
    public Booking bookingInfo;

    @Relation(
            parentColumn = "bookingId",
            entityColumn = "shipmentId"
    )
    public Shipment shipment;


}

