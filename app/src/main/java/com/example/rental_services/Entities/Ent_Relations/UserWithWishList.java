package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.rental_services.Entities.User;
import com.example.rental_services.Entities.WishList;

public class UserWithWishList {

    @Embedded public User user;

    @Relation(
            parentColumn = "userId",
            entityColumn = "id"
    )
    public WishList wishList;
}
