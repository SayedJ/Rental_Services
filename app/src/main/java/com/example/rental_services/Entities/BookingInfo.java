package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity(tableName = "booking_date_setup")
public class BookingInfo {
    @PrimaryKey(autoGenerate = true)
    private int booking_info_id;
    private Date fromDate;
    private Date toDate;
    private Date bookedDate;
    private String bookingId;

    public BookingInfo(int booking_info_id, Date fromDate, Date toDate, Date bookedDate, String bookingId) {
        this.booking_info_id = booking_info_id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.bookedDate = bookedDate;
        this.bookingId = bookingId;
    }

    public int getBooking_info_id() {
        return booking_info_id;
    }

    public void setBooking_info_id(int booking_info_id) {
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

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
