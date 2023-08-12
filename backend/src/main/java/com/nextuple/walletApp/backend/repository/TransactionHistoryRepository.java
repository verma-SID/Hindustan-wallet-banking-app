package com.nextuple.walletApp.backend.repository;

import com.nextuple.walletApp.backend.entity.request.TransactionHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionHistoryRepository extends MongoRepository<TransactionHistory, String> {
}
//Repository for connecting with transactionHistory collection