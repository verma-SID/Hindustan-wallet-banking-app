package com.nextuple.walletApp.backend.entity.response;

import com.nextuple.walletApp.backend.entity.utils.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactions {
    private List<Transaction> transactionList;
}
