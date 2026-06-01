package com.tharun.authsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tharun.authsystem.entity.User;
import com.tharun.authsystem.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {

        return userService.loginUser(
                user.getEmail(),
                user.getPassword()
        );
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody User user) {

        return userService.forgotPassword(
                user.getEmail()
        );
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody User user) {

        return userService.resetPassword(
                user.getOtp(),
                user.getPassword()
        );
    }
}