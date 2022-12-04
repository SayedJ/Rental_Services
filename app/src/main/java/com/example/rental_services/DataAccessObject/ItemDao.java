package com.example.rental_services.DataAccessObject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;


import com.example.rental_services.Entities.Ent_Relations.WishlistWithItems;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.WishList;

import java.util.List;


@Dao
public interface ItemDao {
//    @Transaction
//    @Query("SELECT * FROM wish_list JOIN items_info ON id = wishListId")
//    LiveData<WishlistWithItems> loadWishlistWithItems();

//    @Transaction
//    @Query("SELECT * FROM wish_list")
//    LiveData<WishList> loadWishList();

    @Transaction
    @Insert
    void insertWishList(WishList wishList);

    @Transaction
    @Insert
    void insertItemInWishList(Item... item);

    @Transaction
    @Query("Select * from items_info where userCreatorId = :userId")
    List<Item> getUserItems(long userId);

    @Transaction
    @Query("Select * from items_info where userCreatorId = :userId")
    LiveData<List<Item>> getUserItemsLive(long userId);

    @Delete
    void deleteItem(Item item);

    @Transaction
    @Query("select * from items_info where itemId = :itemId")
    Item getItem(long itemId);

    @Update
    void updateItem(Item item);












}
