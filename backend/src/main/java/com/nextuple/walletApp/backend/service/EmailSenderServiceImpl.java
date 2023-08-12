package com.nextuple.walletApp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public String sendMail(String toEmail, String subject, String body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("tushartg800@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        return "Email Sent";
    }
}
