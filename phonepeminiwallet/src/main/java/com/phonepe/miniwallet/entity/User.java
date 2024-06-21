package com.phonepe.miniwallet.entity;

public class User {
    private final String userId;
    private final String userName;
    private Wallet wallet;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.wallet = new Wallet();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Wallet getWallet() {
        return wallet;
    }
}

