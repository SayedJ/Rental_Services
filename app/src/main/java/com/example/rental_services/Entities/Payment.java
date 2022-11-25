package com.example.rental_services.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "payment")
public class Payment implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int paymentId;
    private boolean creditCard;
    private boolean cash;
    private boolean mobilePay;
    private int bInfoId;

    public Payment( boolean creditCard, boolean cash, boolean mobilePay, int bInfoId) {
        this.creditCard = creditCard;
        this.cash = cash;
        this.mobilePay = mobilePay;
        this.bInfoId = bInfoId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
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

    public int getBInfoId() {
        return bInfoId;
    }

    public void setBInfoId(int bInfoId) {
        this.bInfoId = bInfoId;
    }
}
