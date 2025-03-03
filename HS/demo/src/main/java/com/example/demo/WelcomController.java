package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomController {

    @Autowired
    private JavaMailSender emailSender;

    @GetMapping
    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nik0609tak@gmail.com");
        message.setTo("nbelokrinitskiy@ukr.net");
        message.setSubject("Test");
        message.setText("Hello from Spring Boot Application");
        emailSender.send(message);
    }
}
