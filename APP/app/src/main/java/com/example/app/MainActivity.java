package com.example.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.API.RetrofitClient;
import com.example.app.API.ServiceAPI;
import com.example.app.Model.Category;
import com.example.app.Model.Product;
import com.example.app.adapter.CategoryAdapter;
import com.example.app.adapter.ProductAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcCate;
    RecyclerView rcProduct;
    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;
    ServiceAPI apiService;
    List<Category> categoryList;
    List<Product> productList;

    SharedPreferences sharedPreferences;
    TextView tvName;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        AnhXa();
        GetCategory();
        GetProduct();
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        name = sharedPreferences.getString("user_name", "");
        tvName = findViewById(R.id.tvName);
        tvName.setText("Hi! " + name);
    }

    private void AnhXa() {
        rcCate = findViewById(R.id.recyclerCategories);
        rcProduct = findViewById(R.id.recyclerLastProducts);
    }

    private void GetCategory() {
        apiService = RetrofitClient.getClient().create(ServiceAPI.class);
        apiService.getCategoriesAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()) {
                    categoryList = response.body();

                    categoryAdapter = new CategoryAdapter(MainActivity.this, categoryList);
                    rcCate.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                            LinearLayoutManager.HORIZONTAL, false);
                    rcCate.setLayoutManager(layoutManager);
                    rcCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }

    private void GetProduct() {
        apiService = RetrofitClient.getClient().create(ServiceAPI.class);
        apiService.getLastProducts().enqueue(new Callback<List<Product>>(){
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()) {
                    productList = response.body();
                    productAdapter = new ProductAdapter(MainActivity.this, productList);
                    rcCate.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                            LinearLayoutManager.HORIZONTAL, false);
                    rcProduct.setLayoutManager(layoutManager);
                    rcProduct.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("logg product", t.getMessage());
            }
        });
    }
}