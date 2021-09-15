package com.example.grabbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    TextView  signup1, forgotpassword;
    TextInputLayout username, password;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        hooks
        signup1 = findViewById(R.id.signup1);
        forgotpassword = findViewById(R.id.forgot);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signin =  findViewById(R.id.signin2);


        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateUsername() {
        String val = username.getEditText().getText().toString();

        if(val.isEmpty()) {
            username.setError("Username cannot be empty");
            return  false;
        }else  {
            username.setError("null");
            username.setErrorEnabled(false);
            return  true;
        }

    }

    public Boolean validatePassword() {
        String val = password.getEditText().getText().toString();

        if(val.isEmpty()) {
            password.setError("Password cannot be empty");
            return  false;
        }else  {
            password.setError("null");
            password.setErrorEnabled(false);
            return  true;
        }

    }

    public void loginUser(View view) {

        if (!validateUsername() || !validatePassword()) {
            return;
        }
        String username1 = username.getEditText().getText().toString();
        String password1 = password.getEditText().getText().toString();
    }


}