package com.nextuple.walletApp.backend.repository;

import com.nextuple.walletApp.backend.entity.request.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersInfoRepository extends MongoRepository<UserInfo, String> {
}
//Repository for connecting with usersInfo collection