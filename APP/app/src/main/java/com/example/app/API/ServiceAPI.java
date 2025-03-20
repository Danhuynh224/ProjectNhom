package com.example.app.API;

import com.example.app.Model.Category;
import com.example.app.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceAPI {
    @GET("/all-categories")
    Call<List<Category>> getCategoriesAll();
    @GET("/api/product/last-products")
    Call<List<Product>> getLastProducts();
}
