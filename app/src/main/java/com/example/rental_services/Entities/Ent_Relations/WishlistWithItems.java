package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.WishList;

import java.util.List;

public class WishlistWithItems {

    @Embedded public WishList wishlist;

    @Relation(
            parentColumn = "id",
            entityColumn = "wishListId"
    )
    public List<Item> items;
}
