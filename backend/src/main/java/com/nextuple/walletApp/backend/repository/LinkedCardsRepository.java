package com.nextuple.walletApp.backend.repository;

import com.nextuple.walletApp.backend.entity.request.LinkedCards;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkedCardsRepository extends MongoRepository<LinkedCards,String> {
}

//Repository for connecting with linkedCards collections
