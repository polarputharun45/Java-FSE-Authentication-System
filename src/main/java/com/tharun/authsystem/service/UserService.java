package com.tharun.authsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tharun.authsystem.entity.User;
import com.tharun.authsystem.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private String generatedOtp;
    private String resetEmail;

    public User registerUser(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }

    public String loginUser(String email, String password) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return "User Not Found";
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            return "Login Successful";
        }

        return "Invalid Password";
    }

    public String forgotPassword(String email) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return "Email Not Found";
        }

        generatedOtp =
                String.valueOf((int)(Math.random() * 900000) + 100000);

        resetEmail = email;

        System.out.println(
                "Generated OTP : " + generatedOtp
        );

        return "OTP Generated";
    }

    public String resetPassword(
            String otp,
            String newPassword
    ) {

        if (!otp.equals(generatedOtp)) {
            return "Invalid OTP";
        }

        User user =
                userRepository.findByEmail(resetEmail)
                        .orElse(null);

        if (user == null) {
            return "User Not Found";
        }

        user.setPassword(
                passwordEncoder.encode(newPassword)
        );

        userRepository.save(user);

        generatedOtp = null;
        resetEmail = null;

        return "Password Reset Successful";
    }
}