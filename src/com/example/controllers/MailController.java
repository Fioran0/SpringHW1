package com.example.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.example.mail.MailConfig.FRIEND_EMAIL;

@Controller
public class MailController {

    @Autowired
    public JavaMailSender emailSender;


    @RequestMapping("/sendMail")
    public String searcingForm(){
        return "email";
    }

    @ResponseBody
    @GetMapping("/send")
    public String sendSimpleEmail(@RequestParam String text, @RequestParam String subject) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(FRIEND_EMAIL);
        message.setSubject(subject);
        message.setText(text);

        this.emailSender.send(message);

        return "Email Sent!";
    }

}
