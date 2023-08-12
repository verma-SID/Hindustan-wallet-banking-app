package com.nextuple.walletApp.backend.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @NotBlank
    @NotNull
    @Size(min=19,max = 19) //card number should be 19 chars long
    private String cardNumber;
    @NotBlank
    @NotNull
    private String bankName;
    @NotBlank
    @NotNull
    private String cardHolder;
}
