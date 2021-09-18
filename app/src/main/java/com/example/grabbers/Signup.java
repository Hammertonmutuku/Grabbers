package com.example.grabbers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    FirebaseDatabase rootnode;
    DatabaseReference reference;
    //variables
    TextInputLayout usernameS, emailS, phoneS, passwordS, confirmsS;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Hooks
        usernameS =findViewById(R.id.usernameA);
        emailS = findViewById(R.id.emailA);
        phoneS = findViewById(R.id.phoneA);
        passwordS =findViewById(R.id.passwordA);
        confirmsS = findViewById(R.id.confrim_passwordA);
        signup = findViewById(R.id.signupA);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName() | !validateEmail() | !validatePhone() | !validatePassword() | !validateConfrimPassword()) {
                    return;
                }else {
                    rootnode = FirebaseDatabase.getInstance();
                    reference = rootnode.getReference("Users");

                    //Get all the values
                    String username =  usernameS.getEditText().getText().toString();
                    String email = emailS.getEditText().getText().toString();
                    String phone = phoneS.getEditText().getText().toString();
                    String password =  passwordS.getEditText().getText().toString();

                    Users user = new Users(username, email, phone,password, "rider");
                    reference.child(phone).setValue(user);
                }

//                if (!validateName() |  !validatePhone() | !validatePassword() | !validateConfrimPassword()) {
////            firebaseDatabase.getReference().child("Users").child(username,email,password);
//                    rootnode = FirebaseDatabase.getInstance();
//                    reference = rootnode.getReference("Users");
//
//                    //Get all the values
//                    String username =  usernameS.getEditText().getText().toString();
//                    String email = emailS.getEditText().getText().toString();
//                    String phone = phoneS.getEditText().getText().toString();
//                    String password =  passwordS.getEditText().getText().toString();
//                    String confirm = confirmsS.getEditText().getText().toString();
//
//                    Users user = new Users(username, email, phone,password);
//                    reference.setValue("first class");
//
//                    return;
//                }
            }
        });

    }

    private Boolean validateName() {
        String val =  usernameS.getEditText().getText().toString();
        String noWhiteSpace = "()?=\\s+$";

        if(val.isEmpty()) {
            usernameS.setError("Field cannot be empty");
            return  false;
        }
        else if(val.length()>=16) {
            usernameS.setError(("username is too long"));
            return false;
        }
        else if(val.matches(noWhiteSpace)) {
            usernameS.setError("Field cannot allow white space");
            return false;
        }else {
            usernameS.setError(null);
            usernameS.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val =  emailS.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if(val.isEmpty()) {
            emailS.setError("Field cannot be empty");
            return  false;
        }else if(!val.matches(emailPattern)) {
            emailS.setError("Invalid email");
            return false;
        }
        else {
            emailS.setError(null);
            emailS.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePhone() {
        String val =  phoneS.getEditText().getText().toString();

        if(val.isEmpty()) {
            phoneS.setError("Field cannot be empty");
            return  false;
        } else {
            phoneS.setError(null);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val =  passwordS.getEditText().getText().toString();
        String passwordval = "^" +
                //"(?=.*[0-9) " +    //at least 1 digit
                //"(?=.*[a-z]" +     //at least 1 uppercase
                //"(?=.[A-Z]"  +         //at least 1 lowercase
                "(?=.*[a-zA-Z])" +        //any letter
                "(?=.*[@#$%^&+=])" +    //  //at least 1 special character
                "(?=\\S+$)" +         //nowhitespace
                ".{4,}" +                 //at least 4 characters
                "$";

        if(val.isEmpty()) {
            passwordS.setError("Field cannot be empty");
            return  false;
        }else if(!val.matches(passwordval)) {
            passwordS.setError("Password is weak");
            return  false;
        }
        else{
            passwordS.setError(null);
            confirmsS.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateConfrimPassword() {
        String val =  passwordS.getEditText().getText().toString();
        String con = confirmsS.getEditText().getText().toString();

        if(con.isEmpty()) {
            confirmsS.setError("Field Cannot be empty");
            return false;
        }else if (!con.matches(val)) {
            confirmsS.setError("Does not match password");
            return  false;
        }else {
            confirmsS.setError(null);
            confirmsS.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser (View view) {


        if (!validateName() | !validateEmail() | !validatePhone() | !validatePassword() | !validateConfrimPassword()) {
//            firebaseDatabase.getReference().child("Users").child(username,email,password);
            rootnode = FirebaseDatabase.getInstance();
            reference = rootnode.getReference("Users");

            //Get all the values
            String username =  usernameS.getEditText().getText().toString();
            String email = emailS.getEditText().getText().toString();
            String phone = phoneS.getEditText().getText().toString();
            String password =  passwordS.getEditText().getText().toString();
            String confirm = confirmsS.getEditText().getText().toString();

//            Users user = new Users(username, email, phone,password);
//            reference.setValue(user);

            return;
        }


    }
}