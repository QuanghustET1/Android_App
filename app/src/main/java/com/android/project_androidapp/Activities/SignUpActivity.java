package com.android.project_androidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.project_androidapp.DB.Database;
import com.android.project_androidapp.R;

public class SignUpActivity extends AppCompatActivity {
    private TextView loginPage;
    private EditText registerUsername;
    private EditText registerPassword;
    private EditText re_registerPassword;
    private AppCompatButton btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        LoginPage();
        signUpAccount();
    }

    private void signUpAccount() {
        this.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameSignUp = String.valueOf(SignUpActivity.this.registerUsername.getText());
                String passwordSignUp = String.valueOf(SignUpActivity.this.registerPassword.getText());
                String rePasswordSignUp = String.valueOf(SignUpActivity.this.re_registerPassword.getText());
                if(passwordSignUp.equals(rePasswordSignUp) == false){
                    Toast.makeText(SignUpActivity.this, "Password not match!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Database database = new Database(SignUpActivity.this);
                    database.SignUpAccount(usernameSignUp, passwordSignUp);
                    Toast.makeText(SignUpActivity.this, "Sign up Success!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                }
            }
        });
    }

    private void LoginPage() {
        this.loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }

    private void initView() {
        this.loginPage = findViewById(R.id.loginPage);
        this.registerUsername = findViewById(R.id.registerUsername);
        this.registerPassword = findViewById(R.id.registerPassword);
        this.re_registerPassword = findViewById(R.id.re_registerPassword);
        this.btnRegister = findViewById(R.id.btnRegister);
    }
}