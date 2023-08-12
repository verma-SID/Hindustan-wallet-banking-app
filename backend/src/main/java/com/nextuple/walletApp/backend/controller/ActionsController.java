package com.nextuple.walletApp.backend.controller;

import com.nextuple.walletApp.backend.entity.request.MoneyTransfer;
import com.nextuple.walletApp.backend.entity.request.WalletRecharge;
import com.nextuple.walletApp.backend.entity.response.*;
import com.nextuple.walletApp.backend.entity.request.Card;
import com.nextuple.walletApp.backend.service.ActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin
@RestController
public class ActionsController {
    @Autowired
    private ActionsService actionsService;
    @GetMapping("/user/{id}/user-details")  //to find a user details by its id
    public ResponseEntity<UserDetails> getUserDetails(@PathVariable String id){
        return new ResponseEntity<>(actionsService.getUserDetails(id), HttpStatus.OK);
    }
    @GetMapping("/user/{id}/current-balance")  //to find a user current balance by its id
    public ResponseEntity<UserCurrentBalance> getCurrentBalance(@PathVariable String id){
        return new ResponseEntity<>(actionsService.getCurrentBalance(id), HttpStatus.OK);
    }
    @GetMapping("/user/{id}/transactions")  //to find a user transaction list by its id
    public ResponseEntity<UserTransactions> getTransactions(@PathVariable String id){
        return new ResponseEntity<>(actionsService.getTransactions(id), HttpStatus.OK);
    }
    @GetMapping("/user/{id}/rewards")  //to find a user rewards by its id
    public ResponseEntity<UserRewards> getRewards(@PathVariable String id){
        return new ResponseEntity<>(actionsService.getRewards(id), HttpStatus.OK);
    }
    @GetMapping("/user/{id}/cards")  //to find a user transaction list by its id
    public ResponseEntity<UserCards> getCards(@PathVariable String id){
        return new ResponseEntity<>(actionsService.getCards(id), HttpStatus.OK);
    }
    @PostMapping("/user/{id}/wallet-recharge")  //To make wallet recharge
    public ResponseEntity<String> walletRecharge(@PathVariable String id, @RequestBody @Valid WalletRecharge walletRecharge){
        return new ResponseEntity<>(actionsService.walletRecharge(id, walletRecharge), HttpStatus.OK);
    } //If you doing cashback return cashback entity

    @PostMapping("/user/{id}/money-transfer")  //To make money transfer
    public ResponseEntity<String> moneyTransfer(@PathVariable String id, @RequestBody @Valid MoneyTransfer moneyTransfer){
        return new ResponseEntity<>(actionsService.moneyTransfer(id, moneyTransfer), HttpStatus.OK);
    }
    @PostMapping("/user/{id}/add-card")  //To add card
    public ResponseEntity<String> addCard(@PathVariable String id, @RequestBody @Valid Card card){
        return new ResponseEntity<>(actionsService.addCard(id, card), HttpStatus.CREATED);
    }
    @DeleteMapping("/user/{id}/delete-card") //To Delete card
    public ResponseEntity<String> deleteCard(@PathVariable String id, @RequestBody @Valid Card card){
        return  new ResponseEntity<>(actionsService.deleteCard(id,card),HttpStatus.ACCEPTED);
    }
}

//These APIs are for Users
/*
    Important***
    Note: Every user have its own set of APIs based on its username
*/