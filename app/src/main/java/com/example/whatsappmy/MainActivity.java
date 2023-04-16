package com.example.whatsappmy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.whatsappmy.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        // SET THE ACTION BAR COLOR
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getColor(R.color.Suppcolr)));
        }

        // WORKING FOR THE FRAGMENTS
        // WUTH THW WE ARE ABLE TO USE THE  FRAGMENTS OVER THE VIEWPAGER
         binding.viewpagermain.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));
         binding.tablayoutmain.setupWithViewPager(binding.viewpagermain);
    }
            // NOW WE ARE TAKE ALL THE MENU FROM MENU FILE TO MAIN ACTIVITY CLASS
            // MENU IS BUILD FOR THE LOGOUT PURPOSE AND OTHER ACTIVITY
            // BY USING THE  ONCREATEOPTIONMENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // USING THE INFLATER TO INFLATE THE FILE
        // INFLATER BASICALLY USED TO LAYOUT TRANSFER
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    // FOR WORK ON THE MENU WE USE ON OPTION MENU

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         // WORKING IN THE MENUS
         switch (item.getItemId()){
             case (R.id.setting):
                 break;
             case  (R.id.logout):
           // LOGOUT PROCESS
                 auth.signOut();
                  // AFTER LOGOUT REACH TO LOGIN ACTIVITY
                 Intent intent = new Intent(MainActivity.this , SigninActivity.class);
                 startActivity(intent);
                 break;

         }
        return true;
    }
}