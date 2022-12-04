package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "item_availability_info")
public class ItemInfo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long itemInfoId;
    private boolean available;
    private boolean rented;
    private Date nextAvailableDate;

    public ItemInfo(boolean available, boolean rented, Date nextAvailableDate) {
        this.available = available;
        this.rented = rented;
        this.nextAvailableDate = nextAvailableDate;
    }

    public long getItemInfoId() {
        return itemInfoId;
    }

    public void setItemInfoId(long itemInfoId) {
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
}
