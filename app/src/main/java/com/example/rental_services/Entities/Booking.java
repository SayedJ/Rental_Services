package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "booking_info")
public class Booking implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long bookingId;
    private long userCreatorId;
    private String rentalRules;
    private String paymentRules;
    private long booking_info_id;
    private long itemId;
    private long paymentId;
    private long shipmentId;
    private long itemInfo;
    private long ownerId;

    public Booking(long userCreatorId, String rentalRules, String paymentRules, long booking_info_id, long itemId, long paymentId, long shipmentId, long ownerId) {
        this.userCreatorId = userCreatorId;
        this.rentalRules = rentalRules;
        this.paymentRules = paymentRules;
        this.booking_info_id = booking_info_id;
        this.itemId = itemId;
        this.paymentId = paymentId;
        this.shipmentId = shipmentId;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public long getUserCreatorId() {
        return userCreatorId;
    }

    public void setUserCreatorId(long userCreatorId) {
        this.userCreatorId = userCreatorId;
    }

    public String getRentalRules() {
        return rentalRules;
    }

    public void setRentalRules(String rentalRules) {
        this.rentalRules = rentalRules;
    }

    public String getPaymentRules() {
        return paymentRules;
    }

    public void setPaymentRules(String paymentRules) {
        this.paymentRules = paymentRules;
    }



    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }


    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public long getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(long itemInfo) {
        this.itemInfo = itemInfo;
    }

    public long getBooking_info_id() {
        return booking_info_id;
    }

    public void setBooking_info_id(long booking_info_id) {
        this.booking_info_id = booking_info_id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}