package com.nextuple.walletApp.backend.repository;

import com.nextuple.walletApp.backend.entity.request.CurrentBalance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrentBalanceRepository extends MongoRepository<CurrentBalance, String> {
}
//Repository for connecting with currentBalance collection