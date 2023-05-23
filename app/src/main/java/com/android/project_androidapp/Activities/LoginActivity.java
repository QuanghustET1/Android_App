package com.android.project_androidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.project_androidapp.DB.Database;
import com.android.project_androidapp.Domain.userDomain;
import com.android.project_androidapp.R;

public class LoginActivity extends AppCompatActivity {
    private TextView registerPage;
    private EditText inputUsername;
    private EditText inputPassword;
    private AppCompatButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        RegisterPage();
        getLoginInfor();
    }

    private void getLoginInfor() {
        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = String.valueOf(LoginActivity.this.inputUsername.getText());
                String userPassword =  String.valueOf(LoginActivity.this.inputPassword.getText());
                Log.i("user", userName);
                Log.i("user", userPassword);
                Database db = new Database(LoginActivity.this);
                if(db.LoginUser(new userDomain(userName, userPassword)) == false){
                    Toast.makeText(LoginActivity.this, "User or Pass not found!", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });
    }

    private void RegisterPage() {
        this.registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    private void initView() {
        this.registerPage = findViewById(R.id.registerPage);
        this.inputUsername = findViewById(R.id.inputUsername);
        this.inputPassword = findViewById(R.id.inputPassword);
        this.btnLogin = findViewById(R.id.btnLogin);
    }
}