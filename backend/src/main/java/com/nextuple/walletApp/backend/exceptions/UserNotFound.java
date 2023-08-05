package com.nextuple.walletApp.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "User Not Found")
public class UserNotFound extends RuntimeException{
}

//User not found exception