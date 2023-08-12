package com.nextuple.walletApp.backend.service;

public interface EmailSenderService {
    String sendMail(String toEmail, String subject, String body);
}

//Service for Email Sending