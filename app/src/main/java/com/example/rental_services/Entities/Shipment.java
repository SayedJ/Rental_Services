package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "shipment_info")
public class Shipment implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int shipmentId;
    private boolean owner_delivery;
    private boolean pick_up;
    private boolean shipmentByPost;
    private int addressId;

    public Shipment( boolean owner_delivery, boolean pick_up, boolean shipmentByPost, int addressId) {
        this.owner_delivery = owner_delivery;
        this.pick_up = pick_up;
        this.shipmentByPost = shipmentByPost;
        this.addressId = addressId;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
