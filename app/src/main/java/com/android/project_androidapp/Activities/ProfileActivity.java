package com.android.project_androidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.project_androidapp.R;

public class ProfileActivity extends AppCompatActivity {
    private LinearLayout profile_signout;
    private LinearLayout historyOrder;
    private LinearLayout editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
    }

    private void initView() {
        this.profile_signout = findViewById(R.id.profile_signout);
        this.profile_signout.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this, LoginActivity.class)));
        this.historyOrder = findViewById(R.id.historyOrder);
        this.historyOrder.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this, OrderHistory.class)));
        this.editPassword = findViewById(R.id.editPassword);
        this.editPassword.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this, editPassProfile.class)));
    }
}