package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName= "categories")
public class Category implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long categoryId;
    private String name;


    public Category(long categoryId, String name){
        this.categoryId = categoryId;
        this.name = name;

    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
