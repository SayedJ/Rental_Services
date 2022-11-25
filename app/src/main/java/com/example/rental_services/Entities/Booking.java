package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "booking_info")
public class Booking implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int bookingId;
    private int userCreatorId;
    private String rentalRules;
    private String paymentRules;
    private int booking_info_id;
    private int itemId;
    private int paymentId;
    private int shipmentId;
    private int itemInfo;

    public Booking(int userCreatorId, String rentalRules, String paymentRules, int booking_info_id, int itemId, int paymentId, int shipmentId) {
        this.userCreatorId = userCreatorId;
        this.rentalRules = rentalRules;
        this.paymentRules = paymentRules;
        this.booking_info_id = booking_info_id;
        this.itemId = itemId;
        this.paymentId = paymentId;
        this.shipmentId = shipmentId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserCreatorId() {
        return userCreatorId;
    }

    public void setUserCreatorId(int userCreatorId) {
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



    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }


    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public int getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(int itemInfo) {
        this.itemInfo = itemInfo;
    }

    public int getBooking_info_id() {
        return booking_info_id;
    }

    public void setBooking_info_id(int booking_info_id) {
        this.booking_info_id = booking_info_id;
    }
}