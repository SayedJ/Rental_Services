package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
@Entity(tableName = "booking_date_setup")
public class BookingInfo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long booking_info_id;
    private Date fromDate;
    private Date toDate;
    private Date bookedDate;

    public BookingInfo(Date fromDate, Date toDate, Date bookedDate) {

        this.fromDate = fromDate;
        this.toDate = toDate;
        this.bookedDate = bookedDate;

    }
    @Ignore
    public BookingInfo() {

    }

    public long getBooking_info_id() {
        return booking_info_id;
    }

    public void setBooking_info_id(long booking_info_id) {
        this.booking_info_id = booking_info_id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

}
