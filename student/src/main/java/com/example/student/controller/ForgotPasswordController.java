package com.example.student.controller;



import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.dao.StudentDao;
import com.example.student.services.StudentService;

import jakarta.mail.MessagingException;

@RestController
public class ForgotPasswordController {

    @Autowired
    private StudentDao usersDao;

    @Autowired
    private StudentService usersServices;

    @PostMapping("/send-otp")
    public String forgotPassword(@RequestParam String email) throws MessagingException {
        return this.usersServices.forgotPassword(email);
    }


    @PutMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp){
        return this.usersServices.verifyOtp(email,otp);
    }

    @PutMapping("/set-password")
    public String setPassword(@RequestParam String email , @RequestHeader String newPassword){
        return this.usersServices.setPassword(email,newPassword);
    }

}
