package com.nextuple.walletApp.backend.entity.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private long transId;
    private String type;
    private String destination;
    private double amount;
    private String date;
}

//Model for a transaction
