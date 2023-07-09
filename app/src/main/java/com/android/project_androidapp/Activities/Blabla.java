package com.android.project_androidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.project_androidapp.R;

public class Blabla extends AppCompatActivity {
    private AppCompatButton backToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blabla);
        backToHomePage();
    }

    private void backToHomePage() {
        this.backToHome = findViewById(R.id.backToHomeBla);
        this.backToHome.setOnClickListener(view -> startActivity(new Intent(Blabla.this, MainActivity.class).putExtra("user", MainActivity.user)));
    }
}