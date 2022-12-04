package com.example.rental_services.Database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.BookingInfo;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.Payment;
import com.example.rental_services.Entities.Shipment;
import com.example.rental_services.Entities.User;

import java.util.Date;
@Entity
public class BookingWithDetails {
    @PrimaryKey
    private long bookingId;
    private Date fromDate;
    private Date toDate;
    private String itemName;

    public long getRenterId() {
        return renterId;
    }

    public void setRenterId(long renterId) {
        this.renterId = renterId;
    }

    private long renterId;
    private String itemModel;
    private String itemBrand;
    private String shipment;
    private String payment;
    private String availibility;
    private String ownerName;
    private String itemPrice;
    private String itemImagePath;

    public String getItemImagePath() {
        return itemImagePath;
    }

    public void setItemImagePath(String itemImagePath) {
        this.itemImagePath = itemImagePath;
    }

    public String getItemPrice() {
        return String.valueOf(itemPrice);
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemModel() {
        return itemModel;
    }

    public void setItemModel(String itemModel) {
        this.itemModel = itemModel;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public BookingWithDetails(long bookingId, long renterId, Date fromDate, Date toDate, String itemName, String itemModel,
                              String itemBrand, String shipment, String payment,
                              String availibility, String ownerName,
                              String itemPrice, String itemImagePath){
        this.bookingId = bookingId;
        this.renterId = renterId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.itemName = itemName;
        this.itemModel = itemModel;
        this.itemBrand = itemBrand;
        this.shipment = shipment;
        this.payment = payment;
        this.availibility = availibility;
        this.ownerName = ownerName;
        this.itemPrice = itemPrice;
        this.itemImagePath = itemImagePath;

    }
    @Ignore
    public BookingWithDetails(){

    }


    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getShipment() {
        return shipment;
    }

    public void setShipment(String shipment) {
        this.shipment = shipment;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAvailibility() {
        return availibility;
    }

    public void setAvailibility(String availibility) {
        this.availibility = availibility;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
