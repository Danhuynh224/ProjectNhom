package com.example.app.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import vn.iostar.signupsignindemo.Model.ApiResponse;
import vn.iostar.signupsignindemo.Model.ForgetRequest;
import vn.iostar.signupsignindemo.Model.OTPRequest;
import vn.iostar.signupsignindemo.Model.User;

public interface AuthAPI {
    Call<ApiResponse> register(@Body User user);

    @POST("/api/auth/login")
    Call<ApiResponse> login(@Body User user);

    @POST("/api/auth/forget")
    Call<ApiResponse> forget(@Body ForgetRequest request);

    @POST("/api/auth/active")
    Call<ApiResponse> activate(@Body OTPRequest request);
}
