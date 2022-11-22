package com.example.rental_services.Entities.Ent_Relations;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.rental_services.Entities.BookingInfo;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.ItemInfo;
import com.example.rental_services.Entities.Payment;
import com.example.rental_services.Entities.Shipment;
import com.example.rental_services.Entities.User;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = BookingInfo.class,
                parentColumns = "booking_info_id",
                childColumns = "booking_info_id"
        ),
        @ForeignKey(
                entity = ItemInfo.class,
                parentColumns = "itemInfoId",
                childColumns = "itemInfoId"
        ),
        @ForeignKey(
                entity = Payment.class,
                parentColumns = "paymentId",
                childColumns = "paymentId"
        ),
        @ForeignKey(
                entity = Shipment.class,
                parentColumns = "shipmentId",
                childColumns = "shipmentId"
        ),
        @ForeignKey(
                entity = Item.class,
                parentColumns = "itemId",
                childColumns = "itemId"
        ),
        @ForeignKey(
                entity = User.class,
                parentColumns = "userId",
                childColumns = "bookerId"
        )

})
public class BookingWithAllInfo {
    @PrimaryKey
    public int bookingId;
    @ColumnInfo(name = "booking_info_id", index = true)
    public int booking_info_id;
    @ColumnInfo(name = "itemInfoId", index = true)
    public int itemInfoId;
    @ColumnInfo(name = "paymentId", index = true)
    public int paymentId;
    @ColumnInfo(name = "shipmentId", index = true)
    public int shipmentId;
    @ColumnInfo(name = "itemId", index = true)
    public int itemId;
    @ColumnInfo(name = "bookerId", index = true)
    public int bookerId;
    public String rentalRules;
    public String paymentRules;
}
