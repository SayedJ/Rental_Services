package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Relation;

import com.example.rental_services.Entities.Address;
import com.example.rental_services.Entities.Shipment;

public class ShipmentWithAddress {

    public Shipment shipment;
    @Relation(
            parentColumn = "shipmentId",
            entityColumn = "addressId"

    )
    public Address address;

}
