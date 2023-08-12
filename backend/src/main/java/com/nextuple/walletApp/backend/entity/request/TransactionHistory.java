package com.nextuple.walletApp.backend.entity.request;

import com.nextuple.walletApp.backend.entity.utils.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transactionHistory")
public class TransactionHistory {
    @Id
    @Email
    @NotBlank
    private String email;

    private List<Transaction> transactionList;
}
