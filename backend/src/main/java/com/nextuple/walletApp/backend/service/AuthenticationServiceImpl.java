package com.nextuple.walletApp.backend.service;

import com.nextuple.walletApp.backend.entity.request.*;
import com.nextuple.walletApp.backend.exceptions.UserAlreadyExists;
import com.nextuple.walletApp.backend.exceptions.UserNotFound;
import com.nextuple.walletApp.backend.exceptions.WrongPassword;
import com.nextuple.walletApp.backend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.StringValueExp;
import java.util.ArrayList;
@Service
public class AuthenticationServiceImpl implements AuthenticationService{
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
    @Autowired
    private EmailSenderService emailSenderService;
    @Override
    public String addUser(UserInfo user) {
        String userId=user.getEmail();

        //Setting the subject and body of the mail
        String subject="Welcome to Hindustan Pe";
        String body="Your account is successfully created. Pay your friends, bills, rent, etc instantly and enjoy our services.";

        //if user already exists throw the UserAlreadyExists error
        if(usersInfoRepository.existsById(userId)){
            throw new UserAlreadyExists();
        }
        else {
            usersInfoRepository.insert(user);
            CurrentBalance currentBalance=new CurrentBalance(user.getEmail(),0.0);
            currentBalanceRepository.insert(currentBalance);
            TransactionHistory transactionHistory=new TransactionHistory(user.getEmail(),new ArrayList<>());
            transactionHistoryRepository.insert(transactionHistory);
            RewardHistory rewardHistory=new RewardHistory(user.getEmail(),0.0,new ArrayList<>());
            rewardsRepository.insert(rewardHistory);
            LinkedCards linkedCards=new LinkedCards(user.getEmail(),new ArrayList<>());
            linkedCardsRepository.insert(linkedCards);

            //send mail
            emailSenderService.sendMail(userId,subject,body);

            return "User Registered Successfully";
        }
    }

    @Override
    public String findUser(LoginDetails user) {
        String userId=user.getEmail();
        UserInfo userInfo=usersInfoRepository.findById(userId).orElse(null);
        //if user not exists throw the UserNotFound error
        if(userInfo==null){
            throw new UserNotFound();
        }
        else {
            if(userInfo.getPassword().equals(user.getPassword())){
                return "Successfully Logged In";
            }
            //if password not matches throw the WrongPassword error
            else {
                throw new WrongPassword();
            }
        }
    }
}
