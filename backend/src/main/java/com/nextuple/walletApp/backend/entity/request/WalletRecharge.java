package com.nextuple.walletApp.backend.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletRecharge {
    @NotBlank @NotNull
    private String card;
    @Positive
    private double amount;
}
