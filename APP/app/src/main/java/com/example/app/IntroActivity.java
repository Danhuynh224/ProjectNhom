package com.example.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        Button btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(v -> {
            Intent intent;
            SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
            String username = sharedPreferences.getString("email", "");
            String password = sharedPreferences.getString("password", "");
            if (!username.isEmpty() && !password.isEmpty())
            {
                intent = new Intent(IntroActivity.this, MainActivity.class);
            }
            else
            {
                intent = new Intent(IntroActivity.this, LoginActivity.class);
            }
            startActivity(intent);
        });
    }
}
