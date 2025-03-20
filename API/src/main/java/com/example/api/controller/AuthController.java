package com.example.api.controller;

import com.example.api.entity.User;
import com.example.api.service.AuthService;
import com.example.api.service.OTPService;
import com.example.api.utils.ErrorResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private OTPService otpService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user = authService.registerUser(user);
        if (user == null) {
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                    "User already exists",
                    "A user with this email already exists in the system.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        otpService.sendOTPToEmail(user.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration successful, OTP sent to email");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        System.out.println(user.toString());
        User loginUser = authService.login(user);

        if (loginUser==null) {
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                    "Login unsuccessful",
                    "Email or password is incorrect.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(loginUser, HttpStatus.CREATED);
    }
}
