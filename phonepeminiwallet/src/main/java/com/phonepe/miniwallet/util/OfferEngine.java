package com.phonepe.miniwallet.util;

import com.phonepe.miniwallet.entity.PaymentSource;
import com.phonepe.miniwallet.entity.User;

public class OfferEngine {
    public String applyOffers(User sender, User receiver, double amount) {
        String offerApplied = "No offer applied";

        // Offer 1: 10% cashback on first transaction greater than 100
        if (amount > 100) {
            double cashback = amount * 0.10;
            sender.getWallet().topUp(cashback, PaymentSource.UPI);
            offerApplied = "10% cashback of " + cashback;
        }

        // Offer 2: 50% of transfer amount if both balances are equal after the transaction
        if (sender.getWallet().getBalance() == receiver.getWallet().getBalance()) {
            double bonus = amount * 0.50;
            sender.getWallet().topUp(bonus, PaymentSource.UPI);
            offerApplied = "Balance match offer: 50% of " + amount;
        }

        return offerApplied;
    }
}