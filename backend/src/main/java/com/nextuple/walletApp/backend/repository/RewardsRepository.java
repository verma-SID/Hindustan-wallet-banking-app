package com.nextuple.walletApp.backend.repository;

import com.nextuple.walletApp.backend.entity.request.RewardHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RewardsRepository extends MongoRepository<RewardHistory,String>{
}
//Repository for connecting with rewards collection