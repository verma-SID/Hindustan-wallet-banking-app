package com.nextuple.walletApp.backend.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "currentBalance")
public class CurrentBalance {
    @Id
    @Email
    @NotBlank
    private String email;

    @PositiveOrZero
    @NegativeOrZero
    private double amount;
}
