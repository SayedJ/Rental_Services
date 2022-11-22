package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName= "items_info")
public class Item  implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int itemId;
    private String name;
    private String model;
    private String brand;
    private Date yearOfPurchase;
    private double price;
    private int userCreatorId;
    private int categoryId;
    private int wishListId;
    private int bookingId;
    private String item_stand;

    public Item(String name, String model, String brand, Date yearOfPurchase, double price, int userCreatorId) {
        this.name = name;
        this.model = model;
        this.brand = brand;
        this.yearOfPurchase = yearOfPurchase;
        this.price = price;
        this.userCreatorId = userCreatorId;
    }

    public Item(int position, String model, String brand) {

        this.name = model;
        this.brand = brand;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
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
    public int getUserCreatorId() {
        return userCreatorId;
    }

    public void setUserCreatorId(int userCreatorId) {
        this.userCreatorId = userCreatorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
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
                new Item("Laptop", "MacBook", "Apple", datetime , 300, 1),
                new Item("TV", "Android", "Samsung", datetime , 150, 2),
                new Item("Radio", "Hitachi", "Motachi", datetime , 50, 3),
                new Item("PS4", "Compact", "Sony", datetime , 300, 4),
                new Item("Car", "I30", "Apple", datetime , 999, 5),
                new Item("Gun", "Revolver", "Russian", datetime , 745, 2),

        };



    }



}
