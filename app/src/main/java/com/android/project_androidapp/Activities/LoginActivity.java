package com.android.project_androidapp.Activities;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private TextView registerPage;
    private EditText inputUsername;
    private EditText inputPassword;
    private AppCompatButton btnLogin;
    private DatabaseReference mDatabaseUser;
    private long backPressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.mDatabaseUser = FirebaseDatabase.getInstance().getReference("db_user");
        initView();
        RegisterPage();
        getLoginInfor();
    }

    @Override
    public void onBackPressed() {
        if(this.backPressedTime + 2000 > System.currentTimeMillis()){
            finishAffinity();
        }
        else Toast.makeText(LoginActivity.this, "Back lần nữa để thoát ứng dụng", Toast.LENGTH_SHORT).show();
        this.backPressedTime = System.currentTimeMillis();
    }

    private void getLoginInfor() {
        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = String.valueOf(LoginActivity.this.inputUsername.getText());
                String userPassword =  String.valueOf(LoginActivity.this.inputPassword.getText());
                LoginActivity.this.mDatabaseUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean success = false;
                        for(DataSnapshot ds : snapshot.getChildren()){
                            userDomain userTemp = new userDomain(ds.getValue(userDomain.class).getUserName(), ds.getValue(userDomain.class).getUserPassword());
                            if(userName.equals(userTemp.getUserName()) && userPassword.equals(userTemp.getUserPassword())){
                                success = true;
                                startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("user", new userDomain(userName, userPassword)).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                break;
                            }
                        }
                        if(success == false){
                            Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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