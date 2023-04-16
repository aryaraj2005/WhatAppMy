package com.example.whatsappmy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappmy.Model.UserModel;
import com.squareup.picasso.Picasso;

import java.text.BreakIterator;
import java.util.ArrayList;

public class UserAdapters extends RecyclerView.Adapter<UserAdapters.Viewholder> {
    ArrayList<UserModel> list;
    Context context;

    public UserAdapters(ArrayList<UserModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // here we are trying to take all the sample data from the user
        //when ever any user entry take it will auto add;
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_user , parent , false);
        return  new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // talking image from the google signup if not present the use the placeholder image that is from the drawable
      UserModel users = list.get(position);
        Picasso.get().load(users.getProfilepic()).placeholder(R.drawable.profiles).into(holder.image);
        // for getting the username

           holder.userName.setText(users.getUserName());
            // FOR last msg wwe have to first to some chatting the after that it will show
         // for transfer the data from the chats activity to chats details activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , chatsDetailsAcitvity.class);
                // for solve the error of the get userid re create getter and setter
                intent.putExtra("userid" , users.getUserid());
                intent.putExtra("profilepic" , users.getProfilepic());
                intent.putExtra("userName" , users.getUserName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  Viewholder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView userName , txtlast;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            image= itemView.findViewById(R.id.profile_image);
            userName = itemView.findViewById(R.id.txtuser);
            txtlast= itemView.findViewById(R.id.txtlastmsg);
        }
    }
}
