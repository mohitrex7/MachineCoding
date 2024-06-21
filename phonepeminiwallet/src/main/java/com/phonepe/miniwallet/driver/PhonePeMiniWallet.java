package com.phonepe.miniwallet.driver;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.phonepe.miniwallet.entity.PaymentSource;
import com.phonepe.miniwallet.entity.Transaction;
import com.phonepe.miniwallet.service.WalletService;

public class PhonePeMiniWallet {
    private static final Scanner scanner = new Scanner(System.in);
    private static final WalletService walletService = new WalletService();
            public static void main(String[] args) {
                while (true) {
                    System.out.println("PhonePe Mini Wallet");
                    System.out.println("1. Register User");
                    System.out.println("2. Top Up Wallet");
                    System.out.println("3. Fetch Balance");
                    System.out.println("4. Send Money");
                    System.out.println("5. View Transactions");
                    System.out.println("6. Exit");
                    System.out.print("Choose an option: ");
        
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
        
                    try {
                        switch (choice) {
                            case 1:
                                registerUser();
                                break;
                            case 2:
                                topUpWallet();
                                break;
                            case 3:
                                fetchBalance();
                                break;
                            case 4:
                                sendMoney();
                                break;
                            case 5:
                                viewTransactions();
                                break;
                            case 6:
                                System.exit(0);
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
        
            private static void registerUser() {
                System.out.print("Enter User ID: ");
                String userId = scanner.nextLine();
                System.out.print("Enter User Name: ");
                String userName = scanner.nextLine();
                walletService.registerUser(userId, userName);
                System.out.println("User registered successfully.");
            }
        
            private static void topUpWallet() {
                System.out.print("Enter User ID: ");
                String userId = scanner.nextLine();
                System.out.print("Enter Amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                System.out.print("Enter Payment Source (CREDIT_CARD, DEBIT_CARD, UPI): ");
                String sourceStr = scanner.nextLine();
                PaymentSource source = PaymentSource.valueOf(sourceStr);
                walletService.topUpWallet(userId, amount, source);
                System.out.println("Wallet topped up successfully.");
            }
        
            private static void fetchBalance() {
                System.out.print("Enter User ID: ");
                String userId = scanner.nextLine();
                double balance = walletService.fetchBalance(userId);
                System.out.println("Wallet Balance: " + balance);
            }
        
            private static void sendMoney() {
                System.out.print("Enter Sender User ID: ");
                String senderId = scanner.nextLine();
                System.out.print("Enter Receiver User ID: ");
                String receiverId = scanner.nextLine();
                System.out.print("Enter Amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                walletService.sendMoney(senderId, receiverId, amount);
                System.out.println("Money sent successfully.");
            }
        
            private static void viewTransactions() {
                System.out.print("Enter User ID: ");
                String userId = scanner.nextLine();
        
                System.out.println("Apply Filters: ");
                List<Predicate<Transaction>> filters = new ArrayList<>();
                System.out.print("Filter by Specific User ID (leave blank for no filter): ");
                String specificUserId = scanner.nextLine();
                if (!specificUserId.isEmpty()) {
                    filters.add(t -> t.getSenderId().equals(specificUserId) || t.getReceiverId().equals(specificUserId));
                }
                System.out.print("Filter by Minimum Amount (leave blank for no filter): ");
                String minAmountStr = scanner.nextLine();
                if (!minAmountStr.isEmpty()) {
                    double minAmount = Double.parseDouble(minAmountStr);
                    filters.add(t -> t.getAmount() >= minAmount);
                }
        
                System.out.println("Sorting Options: ");
                System.out.println("1. Date");
                System.out.println("2. Amount");
                System.out.print("Choose a sorting option: ");
                int sortOption = scanner.nextInt();
                scanner.nextLine(); // consume newline
                Comparator<Transaction> sorter;
                if (sortOption == 1) {
                    sorter = Comparator.comparing(Transaction::getDate);
                } else if (sortOption == 2) {
                    sorter = Comparator.comparing(Transaction::getAmount);
                } else {
                    System.out.println("Invalid sorting option. Defaulting to date sorting.");
                    sorter = Comparator.comparing(Transaction::getDate);
                }
        
                walletService.getTransactions(userId, sorter, filters);
            }
        }
        // List<Predicate<Transaction>> filters = Arrays.asList(
        //         t -> t.getSenderId().equals(specificUserId) || t.getReceiverId().equals(specificUserId),
        //         t -> t.getAmount() >= minAmount
        // );

        // walletService.getTransactions("user1", Comparator.comparing(Transaction::getAmount).reversed(),t -> true);
        // walletService.getTransactions("user1", Comparator.comparing(Transaction::getAmount).reversed(),t -> t.getReceiverId().equals("user2"));

