package com.nextuple.walletApp.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nextuple.walletApp.backend.entity.request.Card;
import com.nextuple.walletApp.backend.entity.request.MoneyTransfer;
import com.nextuple.walletApp.backend.entity.request.WalletRecharge;
import com.nextuple.walletApp.backend.entity.response.*;
import com.nextuple.walletApp.backend.exceptions.CardAlreadyExists;
import com.nextuple.walletApp.backend.exceptions.InsufficientBalance;
import com.nextuple.walletApp.backend.exceptions.UserNotFound;
import com.nextuple.walletApp.backend.service.ActionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ActionsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private ActionsService actionsService;
    @InjectMocks
    private ActionsController actionsController;
    private ObjectMapper objectMapper=new ObjectMapper();
    private ObjectWriter objectWriter=objectMapper.writer();

    //For testing we have path id
    private String id="tushartg600@gmail.com";

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(actionsController).build();
    }

    @Test
    void getUserDetails() throws Exception {
        UserDetails userDetails=new UserDetails("Tushar", "Gupta");
        when(actionsService.getUserDetails(id)).thenReturn(userDetails);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.get("/user/tushartg600@gmail.com/user-details");
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",is("Tushar")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",is("Gupta")));
    }

    @Test
    void getCurrentBalance()throws Exception {
        UserCurrentBalance userCurrentBalance=new UserCurrentBalance(1000);
        when(actionsService.getCurrentBalance(id)).thenReturn(userCurrentBalance);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.get("/user/tushartg600@gmail.com/current-balance");
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount",is(1000.0)));
    }

    @Test
    void getTransactions() throws Exception {
        UserTransactions userTransactions=new UserTransactions(new ArrayList<>());
        when(actionsService.getTransactions(id)).thenReturn(userTransactions);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.get("/user/tushartg600@gmail.com/transactions");
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionList",is(new ArrayList())));
    }

    @Test
    void getRewards() throws Exception {
        UserRewards userRewards=new UserRewards(0,new ArrayList<>());
        when(actionsService.getRewards(id)).thenReturn(userRewards);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.get("/user/tushartg600@gmail.com/rewards");
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount",is(0.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rewardList",is(new ArrayList())));
    }

    @Test
    void getCards() throws Exception {
        UserCards userCards=new UserCards(new ArrayList<>());
        when(actionsService.getCards(id)).thenReturn(userCards);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.get("/user/tushartg600@gmail.com/cards");
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardList",is(new ArrayList())));
    }

    @Test
    void walletRecharge() throws Exception {
        WalletRecharge walletRecharge=new WalletRecharge("HDFC Bank",100);
        when(actionsService.walletRecharge(id,walletRecharge)).thenReturn("Rs "+walletRecharge.getAmount()+" credited");
        String content=objectWriter.writeValueAsString(walletRecharge);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.post("/user/tushartg600@gmail.com/wallet-recharge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content);
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$",is("Rs 100.0 credited")));
    }

    //Money Successfully Sent
    @Test
    void moneyTransfer1() throws Exception {
        MoneyTransfer moneyTransfer=new MoneyTransfer("mohitmg700@gmail.com",100);
        when(actionsService.moneyTransfer(id,moneyTransfer)).thenReturn("Rs "+moneyTransfer.getAmount()+" successfully send to "+moneyTransfer.getReceiver());
        String content=objectWriter.writeValueAsString(moneyTransfer);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.post("/user/tushartg600@gmail.com/money-transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$",is("Rs 100.0 successfully send to mohitmg700@gmail.com")));
    }

    //Insufficient Balance
    @Test
    void moneyTransfer2() throws Exception {
        MoneyTransfer moneyTransfer=new MoneyTransfer("mohitmg700@gmail.com",100);
        when(actionsService.moneyTransfer(id,moneyTransfer)).thenThrow(InsufficientBalance.class);
        String content=objectWriter.writeValueAsString(moneyTransfer);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.post("/user/tushartg600@gmail.com/money-transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(202));
    }

    //User Not Found
    @Test
    void moneyTransfer3() throws Exception {
        MoneyTransfer moneyTransfer=new MoneyTransfer("mohitmg700@gmail.com",100);
        when(actionsService.moneyTransfer(id,moneyTransfer)).thenThrow(UserNotFound.class);
        String content=objectWriter.writeValueAsString(moneyTransfer);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.post("/user/tushartg600@gmail.com/money-transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    //When Card is successfully added
    @Test
    void addCard() throws Exception {
        Card card=new Card("1231 3213 3321 1213", "HDFC Bank","Tushar Gupta");
        when(actionsService.addCard(id,card)).thenReturn("Card Added Successfully");
        String content=objectWriter.writeValueAsString(card);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.post("/user/tushartg600@gmail.com/add-card")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$",is("Card Added Successfully")));
    }

    //When Card is Already Present
    @Test
    void addCard2() throws Exception {
        Card card=new Card("1231 3213 3321 1213", "HDFC Bank","Tushar Gupta");
        when(actionsService.addCard(id,card)).thenThrow(CardAlreadyExists.class);
        String content=objectWriter.writeValueAsString(card);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.post("/user/tushartg600@gmail.com/add-card")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(406));
    }

    @Test
    void deleteCard() throws Exception {
        Card card=new Card("1231 3213 3321 1213", "HDFC Bank","Tushar Gupta");
        when(actionsService.deleteCard(id,card)).thenReturn("Card Successfully Deleted");
        String content=objectWriter.writeValueAsString(card);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.delete("/user/tushartg600@gmail.com/delete-card")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(202))
                .andExpect(MockMvcResultMatchers.jsonPath("$",is("Card Successfully Deleted")));
    }
}