package com.example.rental_services.DataAccessObject;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.rental_services.Entities.Address;
import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.Category;
import com.example.rental_services.Entities.Ent_Relations.UserAndAddress;
import com.example.rental_services.Entities.Ent_Relations.UserWithItems;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.User;
import java.util.List;
@Dao
public interface UserItemsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User... user);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItems(Item... items);



    @Transaction
    @Query("SELECT * FROM user_info")
    LiveData<UserAndAddress> getUserAndAddress();

    @Transaction
    @Insert
    void insertAddress(Address address);

    @Transaction
    @Delete
    void deleteUser(User user);

    @Transaction
    @Delete
    void deleteItem(User user);

    @Transaction
    @Insert
    void insertBooking(Booking booking);

    @Transaction
    @Insert
    void insertItemWithCategory(Category category);

//    @Transaction
//    @Query("SELECT * FROM user_info JOIN items_info ON userId = userCreatorId")
//    LiveData<List<UserWithItems>> loadUserAndItems();

    @Transaction
    @Query("SELECT * FROM user_info WHERE userId = :id")
    LiveData<User> loadUserById(long id);

    @Transaction
    @Query("SELECT * FROM user_info")
    LiveData<List<User>> loadAllUser();

    @Transaction
    @Query("SELECT * FROM items_info")
    LiveData<List<Item>> loadAllItems();
    @Transaction
    @Query("SELECT * FROM user_info WHERE email = :email AND password = :password")
    User login(String email, String password);

//    @Query("SELECT * FROM user_info Where username == email  password = password")
//    void login(String email, String password);
}
