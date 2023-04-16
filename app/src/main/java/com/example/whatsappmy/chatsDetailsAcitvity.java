package com.example.whatsappmy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.whatsappmy.Model.Messagemodel;
import com.example.whatsappmy.databinding.ActivityChatsDetailsAcitvityBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class chatsDetailsAcitvity extends AppCompatActivity {
   ActivityChatsDetailsAcitvityBinding binding;
   FirebaseDatabase database;
   FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatsDetailsAcitvityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getColor(R.color.Suppcolr)));
        }
        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
        auth =FirebaseAuth.getInstance();
        // those one login first in app
        // final make sensed id as global
         final String senderId = auth.getUid();
        // others , users who are present in the presents user phone like in whatsapp other user
        // taking reciver id from the user adapter or sender
        String receivedId = getIntent().getStringExtra("userid"  );
        String userName = getIntent().getStringExtra("userName");
        String UserProifle = getIntent().getStringExtra("profilepic");
        binding.nametxt.setText(userName);
        Picasso.get().load(UserProifle).placeholder(R.drawable.profiles).into(binding.profileImage);
        // ON BACK ARRAOW PRESS GO BACK TO MAIN ACTIVITY
        binding.backarrimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chatsDetailsAcitvity.this , MainActivity.class);
                startActivity(intent);
            }
        });
        final ArrayList<Messagemodel> list = new ArrayList<>();
        final ChatAdapter chatAdapter =new ChatAdapter(list ,this );
        // NOW SET THE ADAPTER TO THE CHAT RECYLERVIEW
        binding.chatrecycler.setAdapter(chatAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.chatrecycler.setLayoutManager(linearLayoutManager);
        final String senderchatroom = senderId+receivedId;
        final String reciverchatroom = receivedId + senderId;

        database.getReference().child("chats")
                        .child(senderchatroom)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        list.clear();
                                      for (DataSnapshot snapshot1 : snapshot.getChildren() ){
                                          Messagemodel messagemodel = snapshot1.getValue(Messagemodel.class);
                                          list.add(messagemodel);
                                      }
                                      chatAdapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });



        binding.sendimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String message =binding.writeedittxt.getText().toString();
                    // taking data frim the model class
                    final Messagemodel messagemodel = new Messagemodel(senderId , message);
                    //set the time
                    messagemodel.setTimestamp(new Date().getTime());
                    // after snding the message editxt get clear
                   binding.writeedittxt.setText(" ");
                   // now making hireachy of chats
                // oush is used to create the new node
                  database.getReference().child("chats")
                          .child(senderchatroom)
                          .push().
                          setValue(messagemodel)
                          .addOnSuccessListener
                          (new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {
                          // after success of sender now reiciver msg csn be get send
                            database.getReference().child("chats")
                                    .child(reciverchatroom)
                                    .push()
                                    .setValue(messagemodel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                        }
                                    });
                      }
                  });

            }
        });
    }
}