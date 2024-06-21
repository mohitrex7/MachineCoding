package com.phonepe.miniwallet.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.phonepe.miniwallet.entity.PaymentSource;
import com.phonepe.miniwallet.entity.Transaction;
import com.phonepe.miniwallet.entity.User;
import com.phonepe.miniwallet.entity.Wallet;
import com.phonepe.miniwallet.util.OfferEngine;

public class WalletService {
    private final Map<String, User> users;
    private final OfferEngine offerEngine;

    public WalletService() {
        this.users = new HashMap<>();
        this.offerEngine = new OfferEngine();
    }

    public void registerUser(String userId, String userName) {
        if (!users.containsKey(userId)) {
            users.put(userId, new User(userId, userName));
        } else {
            throw new IllegalArgumentException("User already registered.");
        }
    }

    public void topUpWallet(String userId, double amount, PaymentSource source) {
        User user = users.get(userId);
        if (user != null) {
            user.getWallet().topUp(amount, source);
        } else {
            throw new IllegalArgumentException("User not found.");
        }
    }

    public double fetchBalance(String userId) {
        User user = users.get(userId);
        if (user != null) {
            return user.getWallet().getBalance();
        } else {
            throw new IllegalArgumentException("User not found.");
        }
    }

    public void sendMoney(String senderId, String receiverId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount should be greater than 0.");
        }

        User sender = users.get(senderId);
        User receiver = users.get(receiverId);

        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Sender or receiver not found.");
        }

        Wallet senderWallet = sender.getWallet();
        Wallet receiverWallet = receiver.getWallet();

        if (senderWallet.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        senderWallet.deduct(amount);
        receiverWallet.topUp(amount,PaymentSource.WALLET_TRANSFER);

        String offerApplied = offerEngine.applyOffers(sender, receiver, amount);
        Transaction transaction = new Transaction(senderId, receiverId, amount, offerApplied);

        senderWallet.addTransaction(transaction);
        receiverWallet.addTransaction(transaction);
    }

    public void getTransactions(String userId, Comparator<Transaction> sorter, List<Predicate<Transaction>> filters) {
        User user = users.get(userId);
        if (user != null) {
            Predicate<Transaction> combinedFilter = filters.stream().reduce(x -> true, Predicate::and);
            List<Transaction> transactions = user.getWallet().getTransactions().stream().filter(combinedFilter).sorted(sorter).collect(Collectors.toList());
            System.out.println("Transaction for " + userId + ':');
            transactions.forEach(t -> System.out.println("Transaction: " + t.getTransactionId() + ", Amount: " + t.getAmount() + ", To: " + t.getReceiverId() + ", Date: " + t.getDate() + ", Offer: " + t.getOfferApplied()));
        } else {
            throw new IllegalArgumentException("User not found.");
        }
    }
}
