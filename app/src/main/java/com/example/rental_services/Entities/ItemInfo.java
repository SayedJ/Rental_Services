package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "item_availability_info")
public class ItemInfo {
    @PrimaryKey(autoGenerate = true)
    private int itemInfoId;
    private boolean available;
    private boolean rented;
    private Date nextAvailableDate;



    private int booking_info_id;

    public ItemInfo(int itemInfoId, boolean available, boolean rented, Date nextAvailableDate, int booking_info_id) {
        this.itemInfoId = itemInfoId;
        this.available = available;
        this.rented = rented;
        this.nextAvailableDate = nextAvailableDate;
        this.booking_info_id = booking_info_id;
    }

    public int getItemInfoId() {
        return itemInfoId;
    }

    public void setItemInfoId(int itemInfoId) {
        this.itemInfoId = itemInfoId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public Date getNextAvailableDate() {
        return nextAvailableDate;
    }

    public void setNextAvailableDate(Date nextAvailableDate) {
        this.nextAvailableDate = nextAvailableDate;
    }

    public int getBooking_info_id() {
        return booking_info_id;
    }

    public void setBooking_info_id(int booking_info_id) {
        this.booking_info_id = booking_info_id;
    }
}
