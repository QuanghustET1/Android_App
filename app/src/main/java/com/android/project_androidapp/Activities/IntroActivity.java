package com.android.project_androidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.project_androidapp.R;

public class IntroActivity extends AppCompatActivity {
    private ConstraintLayout startBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        connectStartBtn();
    }

    //Ham nay de set Intent ket noi sang Activity khac(Main)
    private void connectStartBtn() {
        this.startBtn = findViewById(R.id.startBtn);
        this.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Khoi chay mot Intent bang cach goi ham startActivity va truyen vao Intent
                //Intent se gom Activity hien tai va Activity can forward
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }
        });
    }
}