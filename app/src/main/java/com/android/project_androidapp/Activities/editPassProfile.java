package com.android.project_androidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.project_androidapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class editPassProfile extends AppCompatActivity {
    private EditText inputOldPass;
    private EditText inputNewPass;
    private EditText re_inputNewPass;
    private AppCompatButton changePasswordBtn;
    private DatabaseReference db_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass_profile);
        this.db_user = FirebaseDatabase.getInstance().getReference("db_user");
        initView();
        changePassword();
    }

    private void changePassword() {
        this.changePasswordBtn.setOnClickListener(view -> {
            String oldPass = String.valueOf(editPassProfile.this.inputOldPass.getText());
            String newPass = String.valueOf(editPassProfile.this.inputNewPass.getText());
            String reNewPass = String.valueOf(editPassProfile.this.re_inputNewPass.getText());
            if(!oldPass.equals(MainActivity.user.getUserPassword())){
                Toast.makeText(editPassProfile.this, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
            }
            else{
                if(!newPass.equals(reNewPass)){
                    Toast.makeText(editPassProfile.this, "Mật khẩu mới phải trùng nhau", Toast.LENGTH_SHORT).show();
                }
                else{
                    editPassProfile.this.db_user.child(MainActivity.user.getUserName()+"_"+MainActivity.user.getUserPassword()).child("userPassword").setValue(newPass);
                    Toast.makeText(editPassProfile.this, "Thay đổi mật khẩu thành công, hãy đăng nhập lại", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(editPassProfile.this, LoginActivity.class));
                }
            }
        });
    }

    private void initView() {
        this.inputOldPass = findViewById(R.id.inputOldPass);
        this.inputNewPass = findViewById(R.id.inputNewPass);
        this.re_inputNewPass = findViewById(R.id.re_inputNewPass);
        this.changePasswordBtn = findViewById(R.id.changePasswordBtn);
    }
}