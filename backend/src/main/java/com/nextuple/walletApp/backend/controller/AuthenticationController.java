package com.nextuple.walletApp.backend.controller;

import com.nextuple.walletApp.backend.entity.request.LoginDetails;
import com.nextuple.walletApp.backend.entity.request.UserInfo;
import com.nextuple.walletApp.backend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")  //To sign up a User
    public ResponseEntity<String> addUser(@RequestBody @Valid UserInfo user) {
        //.CREATED means 201 response code
        return new ResponseEntity<>(authenticationService.addUser(user), HttpStatus.CREATED);
    }
    @PostMapping("/login")  //To log in the user
    public ResponseEntity<String> findUser(@RequestBody @Valid LoginDetails user){
        return new ResponseEntity<>(authenticationService.findUser(user), HttpStatus.OK);
    }
}

//These APIs are for authentication and commonly accessible for all users
