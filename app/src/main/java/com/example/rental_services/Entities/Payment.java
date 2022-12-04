package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "payment")
public class Payment implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long paymentId;
    private boolean creditCard;
    private boolean cash;
    private boolean mobilePay;


    public Payment( boolean creditCard, boolean cash, boolean mobilePay) {
        this.creditCard = creditCard;
        this.cash = cash;
        this.mobilePay = mobilePay;

    }
    @Ignore
    public Payment() {
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public boolean isCreditCard() {
        return creditCard;
    }

    public void setCreditCard(boolean creditCard) {
        this.creditCard = creditCard;
    }

    public boolean isCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

    public boolean isMobilePay() {
        return mobilePay;
    }

    public void setMobilePay(boolean mobilePay) {
        this.mobilePay = mobilePay;
    }

}
