package com.example.nikitadeveloper.hs26022025.controller;

import com.example.nikitadeveloper.hs26022025.model.EmailRequest;
import com.example.nikitadeveloper.hs26022025.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final MailService mailService;

    public EmailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send-text")
    public ResponseEntity<String> sendText(@RequestBody EmailRequest request) {
        mailService.sendTextEmail(request.to(), request.subject(), request.body());
        return ResponseEntity.ok("Text email sent");
    }

    @PostMapping("/send-html")
    public ResponseEntity<String> sendHtml(@RequestBody EmailRequest request) throws MessagingException {
        mailService.sendHtmlEmail(request.to(), request.subject(), request.body());
        return ResponseEntity.ok("HTML email sent");
    }

    @PostMapping("/send-attachment")
    public ResponseEntity<String> sendAttachment(@RequestParam String to,
                                                 @RequestParam String subject,
                                                 @RequestParam String text,
                                                 @RequestPart MultipartFile file) throws Exception {
        File tempFile = File.createTempFile("temp-", file.getOriginalFilename());
        file.transferTo(tempFile);
        mailService.sendEmailWithAttachment(to, subject, text, tempFile);
        return ResponseEntity.ok("Email with attachment sent");
    }

    @PostMapping("/send-template")
    public ResponseEntity<String> sendTemplate(@RequestParam String to,
                                               @RequestParam String name) throws MessagingException {
        mailService.sendTemplateEmail(to, "Welcome!", name);
        return ResponseEntity.ok("Template email sent");
    }
}