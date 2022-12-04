package com.example.rental_services.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.rental_services.DataAccessObject.BookingSetupDao;
import com.example.rental_services.DataAccessObject.ItemDao;
import com.example.rental_services.DataAccessObject.UserItemsDao;
import com.example.rental_services.DateConverter;
import com.example.rental_services.Entities.*;
import com.example.rental_services.Entities.Ent_Relations.BookingWithAllInfo;

import java.util.concurrent.Executors;

@Database(entities = {User.class, Item.class, Address.class, Booking.class, BookingInfo.class, Category.class, ItemInfo.class, Payment.class, Shipment.class, WishList.class, BookingWithAllInfo.class, BookingWithDetails.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class })
public abstract class RentalServicesDataBase extends RoomDatabase{

    private static RentalServicesDataBase instance;
    public abstract BookingSetupDao bookingDao();
    public abstract ItemDao itemDao();
    public abstract UserItemsDao userItemDao();

    public static synchronized RentalServicesDataBase getInstance(Context context) {
        if(instance == null)
        {
//            instance = Room.databaseBuilder(context.getApplicationContext(),
//                    RentalServicesDataBase.class,
//                    "rental_services_database").build();
            instance = buildDatabase(context);
        }
        return instance;
    }
    private static RentalServicesDataBase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                        RentalServicesDataBase.class,
                        "rental_services_database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).userItemDao().insertUser(User.populateData());
                                getInstance(context).userItemDao().insertItems(Item.populateData());
                            }
                        });
                    }
                })
                .build();
    }


}
