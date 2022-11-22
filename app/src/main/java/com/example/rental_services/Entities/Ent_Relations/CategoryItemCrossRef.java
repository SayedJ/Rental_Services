package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Entity;

@Entity(primaryKeys = {"categoryId", "itemId"})
public class CategoryItemCrossRef {
    public int categoryId;
    public int itemId;
}
