package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "user_info")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long userId;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String mobileNo;
    @Ignore
    public User() {

    }

    public User(String fullName, String username, String email, String password, String mobileNo) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;

        this.password = password;
        this.mobileNo = mobileNo;



    }



    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        mobileNo = mobileNo;
    }

    public static User[] populateData() {
        return new User[] {
                new User("Ahmad", "AhmadBirmn", "Ahmad@gmail.com", "Ahmad", "91775811" ),
                new User("Reza", "rezaJoo", "reza@gmail.com", "reza", "91774811" ),
                new User("Mohammad", "Mammadi", "moha@gmail.com", "moha", "91795811" ),
                new User("Jalal", "joojoo", "jal@gmail.com", "jalal", "91775821" ),
                new User("Maryam", "maryamjooni", "marij@gmail.com", "maryam", "01735811" ),

        };




    }
}
