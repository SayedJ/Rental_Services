package com.example.rental_services.Entities;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName= "items_info")
public class Item  implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long itemId;
    private String name;
    private String model;
    private String brand;
    private Date yearOfPurchase;
    private String imagepath;
    private double price;
    private long userCreatorId;
    private String item_stand;

    public Item(String name, String model, String brand, Date yearOfPurchase, double price, long userCreatorId, String imagepath) {
        this.name = name;
        this.model = model;
        this.brand = brand;
        this.imagepath = imagepath;
        this.yearOfPurchase = yearOfPurchase;
        this.price = price;
        this.userCreatorId = userCreatorId;
    }
    @Ignore
    public Item(int position, String model, String brand) {

        this.name = model;
        this.brand = brand;
    }
    @Ignore
    public Item() {

    }


    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getYearOfPurchase() {
        return yearOfPurchase;
    }

    public void setYearOfPurchase(Date yearOfPurchase) {
        this.yearOfPurchase = yearOfPurchase;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public long getUserCreatorId() {
        return userCreatorId;
    }

    public void setUserCreatorId(long userCreatorId) {
        this.userCreatorId = userCreatorId;
    }

    public String getItem_stand() {
        return item_stand;
    }

    public void setItem_stand(String item_stand) {
        this.item_stand = item_stand;
    }
    public static Item[] populateData() {
        Date datetime = new Date(22, 2, 2020);

        return new Item[]{
                new Item("Laptop", "MacBook", "Apple", datetime , 300, 1, null),
                new Item("TV", "Android", "Samsung", datetime , 150, 2, null),
                new Item("Radio", "Hitachi", "Motachi", datetime , 50, 3, null),
                new Item("PS4", "Compact", "Sony", datetime , 300, 4, null),
                new Item("Car", "I30", "Apple", datetime , 999, 5, null),
                new Item("Gun", "Revolver", "Russian", datetime , 745, 2, null),

        };



    }
    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }




}
