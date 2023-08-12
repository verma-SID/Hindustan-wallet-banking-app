package com.nextuple.walletApp.backend.service;

import com.nextuple.walletApp.backend.exceptions.UserNotFound;
import com.nextuple.walletApp.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private UsersInfoRepository usersInfoRepository;
    @Autowired
    private CurrentBalanceRepository currentBalanceRepository;
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;
    @Autowired
    private RewardsRepository rewardsRepository;
    @Autowired
    private LinkedCardsRepository linkedCardsRepository;
    @Override
    public String deleteUser(String id) {
        if(usersInfoRepository.existsById(id)){
            usersInfoRepository.deleteById(id);
            currentBalanceRepository.deleteById(id);
            transactionHistoryRepository.deleteById(id);
            rewardsRepository.deleteById(id);
            linkedCardsRepository.deleteById(id);
            return "User Successfully Deleted";
        }
        else {
            throw new UserNotFound();
        }
    }
}
