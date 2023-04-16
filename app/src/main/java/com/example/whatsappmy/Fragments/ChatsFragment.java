package com.example.whatsappmy.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.whatsappmy.Model.UserModel;
import com.example.whatsappmy.R;
import com.example.whatsappmy.UserAdapters;
import com.example.whatsappmy.databinding.FragmentChatsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatsFragment extends Fragment {


    public ChatsFragment() {
        // Required empty public constructor
    }
    FragmentChatsBinding binding;
    ArrayList<UserModel> list = new ArrayList<>();
    FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentChatsBinding.inflate(inflater, container, false);
        database = FirebaseDatabase.getInstance();
        UserAdapters userAdapters = new UserAdapters(list , getContext());
        // NOW CREATE A RECYCLER VIEW;
        // use the recycler view
        binding.chatrecycler.setAdapter(userAdapters);
        // LAYOUT MANAGING
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.chatrecycler.setLayoutManager(linearLayoutManager);
        // NOW UPDATING THE MSG
        // use the same user for each part
        database.getReference().child("User").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
              // here taking the snapshot of the data;
                // for every user data
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    UserModel users = dataSnapshot.getValue(UserModel.class);

                    assert users != null;
                    users.setUserid(dataSnapshot.getKey());
                    // data from database add on list which show  0n the fragments
                    list.add(users);
                }
                userAdapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return  binding.getRoot();
    }
}