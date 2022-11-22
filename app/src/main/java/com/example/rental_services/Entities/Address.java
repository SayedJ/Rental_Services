package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "address")
public class Address {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String house_No;
    private String streetName;
    private int postalCode;
    private String city;
    private int userCreatorId;


    public Address(int id, String house_No, String streetName, int postalCode, String city, int userCreatorId) {
        this.id = id;
        this.house_No = house_No;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.city = city;
        this.userCreatorId = userCreatorId;
    }

    public int getUserCreatorId() {
        return userCreatorId;
    }

    public void setUserCreatorId(int userCreatorId) {
        this.userCreatorId = userCreatorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouse_No() {
        return house_No;
    }

    public void setHouse_No(String house_No) {
        this.house_No = house_No;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
