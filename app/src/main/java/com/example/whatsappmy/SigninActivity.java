package com.example.whatsappmy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.whatsappmy.Model.UserModel;
import com.example.whatsappmy.databinding.ActivitySigninBinding;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.internal.zzx;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class SigninActivity extends AppCompatActivity {
    ActivitySigninBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    // GOOGLE SIGN IN
    GoogleSignInClient  mGoogleSignInClient;
    // data taking
//    FirebaseDatabase database;
    FirebaseDatabase database ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(SigninActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("we are created your account");
       database = FirebaseDatabase.getInstance();
//         FOR GOOGLE SIGN IN AUTHEN
//         SUING FIREBASE DOCS
//         MANUAL WAY NOTS FROM DOCS
//         FIRST GET SIGNIN OPTION
//             GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
//                     // FOR SOLVE THE ERROR OF CLIENT GO TO  GRADLE PROJECT LEVEL CHANGE  DEPENDENCI 10 TO 15
//                     .requestIdToken(getString(R.string.default_web_client_id))
//                     .requestEmail()
//                             .build();
//
//             mGoogleSignInClient = GoogleSignIn.getClient(SigninActivity.this , gso);
//        binding.btngoogle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Sigin();
//            }
//        });
           // manual Sigin process
            binding.btnsignin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 progressDialog.show();
                auth.signInWithEmailAndPassword(binding.edtemail.getText().toString() ,
                        binding.edtpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Intent intent = new Intent(SigninActivity.this, MainActivity.class) ;
                            Toast.makeText(SigninActivity.this, "User Sign in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(SigninActivity.this , task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
             }
         });
         // FROM SIUNG IN ACTVITY TO SIGNUP ACTIVITY USE TEXTVIEW
        binding.txtsigact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
         // HERE WE ARE TRYING THAT IF ONCE USER SIGIN THE IT ALWAYS STAY WITH MAIN ACTIVITY
        if(auth.getCurrentUser()!=null){
            Intent intent = new Intent(SigninActivity.this ,MainActivity.class);
            startActivity(intent);
        }

    }
//   int REC_SIGN_IN = 2;
//    private void Sigin() {
//
//        Intent intent =mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(intent , REC_SIGN_IN);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == REC_SIGN_IN){
//            Task<GoogleSignInAccount>tasks = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount account = tasks.getResult(ApiException.class);
//                firebaseAuth(account.getIdToken());
//            } catch (ApiException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    private void firebaseAuth(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken , null);
//        auth.signInWithCredential(credential)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> tasks) {
//                        if(tasks.isSuccessful()){
//                   //       FirebaseUser  user = auth.getCurrentUser();
////                          UserModel users = new UserModel();
////                          users.setUserid(user.getUid());
////                          users.setUserName(user.getDisplayName());
////                          users.setProfilepic(user.getPhotoUrl().toString());
////                          database.getReference().child("Users").child(user.getUid()).setValue(users);
//                          Intent intent = new Intent(SigninActivity.this , MainActivity.class);
//                          startActivity(intent);
//                        }else {
//                            Toast.makeText(SigninActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//
}