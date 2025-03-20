package com.example.api.request;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class OTPRequest {
    private String email;
    private String otp;


}
