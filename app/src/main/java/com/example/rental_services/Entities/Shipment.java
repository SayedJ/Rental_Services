package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "shipment_info")
public class Shipment implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long shipmentId;
    private boolean owner_delivery;
    private boolean pick_up;
    private boolean shipmentByPost;
    private long addressId;

    public Shipment( boolean owner_delivery, boolean pick_up, boolean shipmentByPost, long addressId) {
        this.owner_delivery = owner_delivery;
        this.pick_up = pick_up;
        this.shipmentByPost = shipmentByPost;
        this.addressId = addressId;
    }
    @Ignore
    public Shipment() {

    }

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public boolean isOwner_delivery() {
        return owner_delivery;
    }

    public void setOwner_delivery(boolean owner_delivery) {
        this.owner_delivery = owner_delivery;
    }

    public boolean isPick_up() {
        return pick_up;
    }

    public void setPick_up(boolean pick_up) {
        this.pick_up = pick_up;
    }

    public boolean isShipmentByPost() {
        return shipmentByPost;
    }

    public void setShipmentByPost(boolean shipmentByPost) {
        this.shipmentByPost = shipmentByPost;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
}
