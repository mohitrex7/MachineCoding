package com.phonepe.miniwallet.entity;

import java.util.Date;

public class Transaction {
    private static int counter = 0;
    private final int transactionId;
    private final String senderId;
    private final String receiverId;
    private final double amount;
    private final Date date;
    private final String offerApplied;

    public Transaction(String senderId, String receiverId, double amount, String offerApplied) {
        this.transactionId = ++counter;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.date = new Date();
        this.offerApplied = offerApplied;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getOfferApplied() {
        return offerApplied;
    }
}