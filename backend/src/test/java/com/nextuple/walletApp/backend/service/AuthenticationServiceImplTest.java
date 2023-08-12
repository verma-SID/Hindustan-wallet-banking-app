package com.nextuple.walletApp.backend.service;

import com.nextuple.walletApp.backend.entity.request.*;

import com.nextuple.walletApp.backend.exceptions.UserAlreadyExists;
import com.nextuple.walletApp.backend.exceptions.UserNotFound;
import com.nextuple.walletApp.backend.exceptions.WrongPassword;

import com.nextuple.walletApp.backend.repository.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest

class AuthenticationServiceImplTest {
    @Mock
    private UsersInfoRepository usersInfoRepository;
    @Mock
    private TransactionHistoryRepository transactionHistoryRepository;
    @Mock
    private RewardsRepository rewardsRepository;
    @Mock
    private CurrentBalanceRepository currentBalanceRepository;
    @Mock
    private LinkedCardsRepository linkedCardsRepository;
    @Mock
    private EmailSenderService emailSenderService;
    private UserInfo userInfo=new UserInfo("tushartg600@gmail.com", "Tushar", "Gupta","12345678");
    private CurrentBalance currentBalance=new CurrentBalance("tushartg600@gmail.com",0);
    private TransactionHistory transactionHistory=new TransactionHistory("tushartg600@gmail.com",new ArrayList<>());
    private RewardHistory rewardHistory=new RewardHistory("tushartg600@gmail.com",0,new ArrayList<>());
    private LinkedCards linkedCards=new LinkedCards("tushartg600@gmail.com",new ArrayList<>());
    private LoginDetails loginDetails=new LoginDetails();
    @InjectMocks
    private AuthenticationServiceImpl authenticationService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    //Tests for addUser()
    //When User Already Exists
    @Test
    void addUserTest1(){
        when(usersInfoRepository.existsById(userInfo.getEmail())).thenReturn(true);
        assertThrows(UserAlreadyExists.class,()->authenticationService.addUser(userInfo));
    }

    //Successfully added
    @Test
    void addUserTest2(){
        when(usersInfoRepository.existsById(userInfo.getEmail())).thenReturn(false);
        when(usersInfoRepository.insert(userInfo)).thenReturn(userInfo);
        when(currentBalanceRepository.insert(currentBalance)).thenReturn(currentBalance);
        when(transactionHistoryRepository.insert(transactionHistory)).thenReturn(transactionHistory);
        when(rewardsRepository.insert(rewardHistory)).thenReturn(rewardHistory);
        when(linkedCardsRepository.insert(linkedCards)).thenReturn(linkedCards);
        when(emailSenderService.sendMail(userInfo.getEmail(), "XYZ","xyz")).thenReturn("Email Sent");
        assertEquals("User Registered Successfully", authenticationService.addUser(userInfo));
    }

    //Tests for findUser()

    //On Successful Login
    @Test
    void findUserTest1() {
        //Giving Correct Credentials
        loginDetails.setEmail("tushartg600@gmail.com");
        loginDetails.setPassword("12345678");
        when(usersInfoRepository.findById(loginDetails.getEmail())).thenReturn(Optional.of(userInfo));
        assertEquals("Successfully Logged In",authenticationService.findUser(loginDetails));
    }

    //On Wrong Password
    @Test
    void findUserTest2() {
        //Giving Wrong Password
        loginDetails.setEmail("tushartg600@gmail.com");
        loginDetails.setPassword("1234567");
        when(usersInfoRepository.findById(loginDetails.getEmail())).thenReturn(Optional.of(userInfo));
        assertThrows(WrongPassword.class,()->authenticationService.findUser(loginDetails));

    }
    //On Incorrect Email
    @Test
    void findUserTest3() {
        loginDetails.setEmail("tushartg600@gmail.com");
        loginDetails.setPassword("12345678");
        when(usersInfoRepository.findById(loginDetails.getEmail())).thenReturn(Optional.ofNullable(null));
        assertThrows(UserNotFound.class,()->authenticationService.findUser(loginDetails));
    }
}