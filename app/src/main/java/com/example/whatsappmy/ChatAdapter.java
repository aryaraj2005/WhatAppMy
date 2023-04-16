package com.example.whatsappmy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappmy.Model.Messagemodel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

// tei diifernt virew holder so we can not directly use the chatadapert
public class ChatAdapter extends RecyclerView.Adapter {

   ArrayList<Messagemodel> list ;
   Context context;
   int SENDER_VIEW_TYPE =1;
   int RECIVER_VIEW_TYPE=2;
    public ChatAdapter(ArrayList<Messagemodel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sendersample , parent , false);
            return new senderViwholder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.reciversample, parent , false);
            return new recieverVieholder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //THER IS INLY SINGAL SENDER SO WE CAN EASIKY IDENTIFY IT BY ITS ID
        // HERE WE IDENTIFY THE SENDER LAYOUT
           if(list.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
               return SENDER_VIEW_TYPE;
           }else {
               return RECIVER_VIEW_TYPE;
           }
          // return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
          Messagemodel messagemodel = list.get(position);
          // here we identify the sender message from firebase
        // for it according to the sequence
          if(holder.getClass()==senderViwholder.class){
              ((senderViwholder)holder).sendertxt.setText(messagemodel.getMessage());
          }//now for the reciver
        else {
              ((recieverVieholder)holder).recivertxt.setText(messagemodel.getMessage());
          }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    //CREATING  TWO DIIF VIEW HOLDER FOR RECIVER AND SENDER
    public  class  recieverVieholder extends RecyclerView.ViewHolder{
           TextView recivertxt , recivertimetxt;

        public recieverVieholder(@NonNull View itemView) {
            super(itemView);

            recivertxt= itemView.findViewById(R.id.recievertxt);
            recivertimetxt= itemView.findViewById(R.id.recivertimetxt);
        }
    }
    public class senderViwholder extends RecyclerView.ViewHolder{
         TextView sendertxt , sendertimetxt;
        public senderViwholder(@NonNull View itemView) {
            super(itemView);
            sendertxt = itemView.findViewById(R.id.sedefrtxt);
            sendertimetxt = itemView.findViewById(R.id.sendettyimetxt);
        }
    }

}
