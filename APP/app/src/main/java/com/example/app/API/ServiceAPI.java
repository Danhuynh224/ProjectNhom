package com.example.app.API;

import com.example.app.Model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceAPI {
    @GET("categories")
    Call<List<Category>> getCategoriesAll();
}
