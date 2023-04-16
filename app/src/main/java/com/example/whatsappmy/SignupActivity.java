package com.example.whatsappmy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsappmy.Model.UserModel;
import com.example.whatsappmy.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import org.w3c.dom.ProcessingInstruction;

public class SignupActivity extends AppCompatActivity {
    // BINDING IS USED TO PREVENT FROM MULTIPLE TIME USED OF ID FINDING
   ActivitySignupBinding binding;
   // WE TAKE THIS OFFICIAL DOCS
    private FirebaseAuth auth;
    FirebaseDatabase database;
    // FOR DAILOG PRIOGRESS BAR
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        // get the instance of the authentication
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        // CREATING PROGRESS BAR
        progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setTitle("Account is under progress");
        progressDialog.setMessage("We are creating your Account");

        binding.btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // PROGRASS BAR SHOW WHEN USER CREATED
                progressDialog.show();
                //WE ARE PASSING TO PARA THAT S ARE EMAIL AND PASSWORD
                auth.createUserWithEmailAndPassword(binding.edtemail.getText().toString() ,
                        // USE IN COMPLETE LISTNER
                        binding.edtpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // AFTER COMPLETE OF SIGNUP PROGRASS BAR GET CLOSED
                        progressDialog.dismiss();
                        // WE USES TASK
                       if(task.isSuccessful()){
                           //  HERE PERFORMING FOR THE DATA STORAGE LIKE EMAIL PASS ETC IN REAL TIME DATABASE
                           // USE CONSTRUCTOR MODEL
                           UserModel user = new UserModel(binding.edtusername.getText().toString() , binding.edtemail.getText().toString() , binding.edtpass.getText().toString());
                           // NOW FIND THE PARTICULAR ID FOR UNIQUE INDENTIFICATION
                           String id = task.getResult().getUser().getUid();
                           // NOW TAKE REFERENCE OF DATABASE FOR MAKE THE CHILD
                           database.getReference().child("User").child(id).setValue(user);
                           Toast.makeText(SignupActivity.this, "User Signup successfully", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(SignupActivity.this , MainActivity.class);
                           startActivity(intent);

                        }
                       else {
                           Toast.makeText(SignupActivity.this ,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }
                    }
                });
                // NOW CREATE A USER MODEL OF USER DATA CREATE A CLASS MODEL FOR CONSTRUCTOR PURPOSE
            }
        });
        // FROM SINGUP ACTIVITY TO SIGN IN IF ALREADY ACCOUNT
        binding.txtalacnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this , SigninActivity.class);
                startActivity(intent);
            }
        });

    }
}