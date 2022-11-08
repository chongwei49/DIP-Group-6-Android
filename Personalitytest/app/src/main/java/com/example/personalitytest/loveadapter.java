package com.example.personalitytest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalitytest.models.User;

import java.util.ArrayList;

public class loveadapter extends RecyclerView.Adapter<loveadapter.MyViewHolder>{
    private final personalityrecyclerinterface personalityRecyclerinterface;
    Context context;
    private static ArrayList<User> profileList = new ArrayList<User>();


    public loveadapter(Context context, ArrayList<User> arrayList, personalityrecyclerinterface personalityRecyclerinterface){
        this.context = context;
        this.profileList = arrayList;
        this.personalityRecyclerinterface = personalityRecyclerinterface;
    }
    @NonNull
    @Override
    public loveadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.loveprofile, parent,false);
        return new loveadapter.MyViewHolder(view, personalityRecyclerinterface);
    }

    @Override
    public void onBindViewHolder(@NonNull loveadapter.MyViewHolder holder, int position) {
        if(profileList.get(position).getProfilePic()!=null){
            holder.profilepic.setImageBitmap(receiveImage(profileList.get(position).getProfilePic()));
        }
        holder.name.setText(profileList.get(position).getName());
        holder.email.setText(profileList.get(position).getGender());


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView profilepic;
        TextView name, email;
        public MyViewHolder(@NonNull View itemView, personalityrecyclerinterface personalityRecyclerinterface){
            super(itemView);

            profilepic = itemView.findViewById(R.id.profilepic);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.age);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override

                public void onClick (View view) {
                    if (personalityRecyclerinterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            personalityRecyclerinterface.onItemClick(pos, profileList);

                        }
                    }
                }
            });

        }

    }

    public Bitmap receiveImage(byte[] input_byte){
        Bitmap bmp= BitmapFactory.decodeByteArray(input_byte,0,input_byte.length);
        return bmp;
    }
}
