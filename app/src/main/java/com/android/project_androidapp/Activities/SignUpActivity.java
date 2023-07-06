package com.android.project_androidapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
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

public class SignUpActivity extends AppCompatActivity {
    private TextView loginPage;
    private EditText registerUsername;
    private EditText registerPassword;
    private EditText re_registerPassword;
    private AppCompatButton btnRegister;
    private DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.databaseUser = FirebaseDatabase.getInstance().getReference("db_user");
        setContentView(R.layout.activity_sign_up);
        initView();
        LoginPage();
        signUpAccount();
    }

    private void signUpAccount() {
        this.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int[] checkCountState = {1};
                String usernameSignUp = String.valueOf(SignUpActivity.this.registerUsername.getText());
                String passwordSignUp = String.valueOf(SignUpActivity.this.registerPassword.getText());
                String rePasswordSignUp = String.valueOf(SignUpActivity.this.re_registerPassword.getText());
                if(passwordSignUp.equals(rePasswordSignUp) == false){
                    Toast.makeText(SignUpActivity.this, "Password not match!", Toast.LENGTH_SHORT).show();
                }
                else{
                    SignUpActivity.this.databaseUser.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean isExistsUser = false;
                            for(DataSnapshot ds : snapshot.getChildren()){
                                if(ds.getValue(userDomain.class).getUserName().equals(usernameSignUp)){
                                    isExistsUser = true;
                                }
                            }
                            if(isExistsUser == false){
                                SignUpActivity.this.databaseUser.child(usernameSignUp+"_"+passwordSignUp).setValue(new userDomain(usernameSignUp, passwordSignUp));
                                Toast.makeText(SignUpActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                checkCountState[0] += 1;
                                finish();
                            }
                            else if(isExistsUser == true && checkCountState[0] == 1){
                                Toast.makeText(SignUpActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
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