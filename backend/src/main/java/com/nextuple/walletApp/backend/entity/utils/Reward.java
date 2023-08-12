package com.nextuple.walletApp.backend.entity.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reward {
    private long id;
    private double amount;
    private String date;
}
