package com.example.sharedprefsig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sharedprefsig.databinding.ActivityLoginBinding;
import com.example.sharedprefsig.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefManager= PrefManager.getInstance(this);
        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username  = binding.edtUsername.getText().toString();
                String password =  binding.edtPassword.getText().toString();
                String confirmPassword = binding.edtPasswordConfirm.getText().toString();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Mohon isi semua data", Toast.LENGTH_SHORT).show();
                } else if (!password.equals (confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Password tidak sama", Toast.LENGTH_SHORT).show();
                }
                else {
                    prefManager.SaveUsername(username);
                    prefManager.SavePassword(password);
                    prefManager.setKeyIsLoggedIn(true);
                    checkLoginStatus();
                }
            }
        });
    }

    private void checkLoginStatus(){
        boolean isLoggedIn = prefManager.isLoggedIn();
        if (isLoggedIn){
            Toast.makeText(RegisterActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            finish();
        }
        else {
            Toast.makeText(RegisterActivity.this, "Registrasi gagal", Toast.LENGTH_SHORT).show();
        }
    }
}