package com.example.sharedprefsig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sharedprefsig.databinding.ActivityLoginBinding;
import com.example.sharedprefsig.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefManager= PrefManager.getInstance(this);

        binding.txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.edtUsername.getText().toString();
                String password = binding.edtPassword.getText().toString();
                if (username.isEmpty()|| password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Mohon isi semua data", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (isValidUsernamePassword(username,password)){
                        prefManager.setKeyIsLoggedIn(true);
                        checkLoginStatus();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Username atau password salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private boolean isValidUsernamePassword(String inputUsername, String inputPassword){
        String username = prefManager.getUsername();
        String password = prefManager.getPassword();

        return username.equals(inputUsername) && password.equals(inputPassword);
    }

    private void checkLoginStatus(){
        boolean isLoggedIn = prefManager.isLoggedIn();
        if (isLoggedIn){
            Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        else {
            Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
        }
    }
}