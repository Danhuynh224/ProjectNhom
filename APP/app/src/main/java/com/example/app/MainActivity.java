package com.example.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView tvName;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        name = sharedPreferences.getString("user_name", "");

        tvName = findViewById(R.id.tvName);
        tvName.setText("Hi! " + name);
    }
}