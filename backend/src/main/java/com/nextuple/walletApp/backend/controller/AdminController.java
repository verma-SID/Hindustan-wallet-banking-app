package com.nextuple.walletApp.backend.controller;

import com.nextuple.walletApp.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @DeleteMapping("/delete/{id}")  //To delete the user from Database
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        return new ResponseEntity<>(adminService.deleteUser(id), HttpStatus.ACCEPTED);
    }
}

//APIs in the AdminController are not for the use of consumers should be confidential.