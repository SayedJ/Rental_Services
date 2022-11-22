package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;

import java.util.List;

public class UserWithItems {
    @Embedded public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "userCreatorId"
    )
    public List<Item> items;
}
