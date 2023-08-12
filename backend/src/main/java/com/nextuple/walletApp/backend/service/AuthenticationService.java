package com.nextuple.walletApp.backend.service;

import com.nextuple.walletApp.backend.entity.request.LoginDetails;
import com.nextuple.walletApp.backend.entity.request.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;


public interface AuthenticationService {
    String addUser(UserInfo user);
    String findUser(LoginDetails user);
}
//Service for Authentication