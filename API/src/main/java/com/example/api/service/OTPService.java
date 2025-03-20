package com.example.api.service;

import com.example.api.entity.OTP;
import com.example.api.repository.OTPRepository;
import com.example.api.utils.OTPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OTPService {
    @Autowired
    private OTPRepository otpRepository;
    @Autowired
    private JavaMailSender emailSender;
    public void sendOTPToEmail(String email) {
        String otp = OTPUtils.generateOTP();
        OTP existingOTP = otpRepository.findByEmail(email);
        if(existingOTP == null) {
            existingOTP = new OTP();
        }
        existingOTP.setEmail(email);
        existingOTP.setValue(otp);
        otpRepository.save(existingOTP);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);
        emailSender.send(message);
    }
}
