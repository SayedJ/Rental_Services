package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity(tableName= "wish_list")
public class WishList {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userCreatorId;
    private int itemId;


    public WishList(int id, int itemId, int userCreatorId) {
        this.id = id;
        this.itemId = itemId;
        this.userCreatorId = userCreatorId;
    }

    @Ignore
    public WishList() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserCreatorId() {
        return userCreatorId;
    }

    public void setUserCreatorId(int userCreatorId) {
        this.userCreatorId = userCreatorId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
