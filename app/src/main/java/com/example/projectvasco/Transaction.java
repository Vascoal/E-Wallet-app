package com.example.projectvasco;

public class Transaction {
    public final String type;   // "TOPUP" atau "KIRIM"
    public final String title;
    public final long amount;
    public final long createdAt;

    public Transaction(String type, String title, long amount, long createdAt) {
        this.type = type;
        this.title = title;
        this.amount = amount;
        this.createdAt = createdAt;
    }
}