package com.example.grabbers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginRider extends AppCompatActivity {

    TextView forgotPassword;
    TextInputLayout username, password;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_rider);

        //        hooks
        forgotPassword = findViewById(R.id.forgot);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signIn =  findViewById(R.id.signin2);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateUsername() || !validatePassword()) {
                    return;
                }else {
                    isUser();
                }
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

    private void isUser() {
        String username1 = username.getEditText().getText().toString().trim();
        String password1 = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        Query chekUser = reference.orderByChild("username").equalTo(username1);
        chekUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {

                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordwordFromdb = dataSnapshot.child(username1).child("password").getValue(String.class);

                    if(passwordwordFromdb.equals(password1)) {
                        String usernameFromDb = dataSnapshot.child(username1).child("username").getValue(String.class);
                        String emailFromDb = dataSnapshot.child(username1).child("email").getValue(String.class);
                        String passwordFromDb = dataSnapshot.child(username1).child("password").getValue(String.class);
                        String phoneFromDb = dataSnapshot.child(username1).child("phonenumber").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),UserProfile.class);

                        intent.putExtra("username", usernameFromDb);
                        intent.putExtra("email", emailFromDb);
                        intent.putExtra("phonenumber", phoneFromDb);
                        intent.putExtra("password", passwordFromDb);

                        startActivity(intent);
                    }else {
                        password.setError("Incorrect passsword");
                        password.requestFocus();
                    }
                }else {
                    username.setError("Such username doesn't exist");
                    username.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}