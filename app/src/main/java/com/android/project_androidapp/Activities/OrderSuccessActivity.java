package com.android.project_androidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.project_androidapp.R;

public class OrderSuccessActivity extends AppCompatActivity {
    private AppCompatButton backToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);
        initView();
    }

    private void initView() {
        this.backToHome = findViewById(R.id.backToHome);
        this.backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderSuccessActivity.this, MainActivity.class).putExtra("user", MainActivity.user));
            }
        });
    }
}