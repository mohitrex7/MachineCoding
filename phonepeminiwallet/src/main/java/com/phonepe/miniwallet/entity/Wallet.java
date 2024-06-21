package com.phonepe.miniwallet.entity;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private double balance;
    private List<Transaction> transactions;

    public Wallet() {
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void topUp(double amount, PaymentSource source) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Wallet topped up with " + amount + " via " + source);

            //wallet topup can be added as transaction
        } else {
            throw new IllegalArgumentException("Amount should be greater than 0.");
        }
    }

    public void deduct(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance or invalid amount.");
        }
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}