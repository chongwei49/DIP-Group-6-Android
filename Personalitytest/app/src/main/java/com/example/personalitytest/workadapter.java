package com.example.personalitytest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class workadapter extends RecyclerView.Adapter<workadapter.MyViewHolder>{
    private final personalityrecyclerinterface personalityRecyclerinterface;
    Context context;
    ArrayList<Profile> profileList = new ArrayList<>();


    public workadapter(Context context,ArrayList<Profile> arrayList, personalityrecyclerinterface personalityRecyclerinterface){
        this.context = context;
        this.profileList = arrayList;
        this.personalityRecyclerinterface = personalityRecyclerinterface;
    }
    @NonNull
    @Override
    public workadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.workprofile, parent,false);
        return new workadapter.MyViewHolder(view, personalityRecyclerinterface);
    }

    @Override
    public void onBindViewHolder(@NonNull workadapter.MyViewHolder holder, int position) {
        Random random = new Random();
        int x = random.nextInt(profileList.size());
        holder.name.setText(profileList.get(x).getName());
        holder.profilepic.setImageResource(profileList.get(x).getImage());
        holder.age.setText(profileList.get(x).getAge());


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView profilepic;
        TextView name,  age;
        public MyViewHolder(@NonNull View itemView, personalityrecyclerinterface personalityRecyclerinterface){
            super(itemView);

            profilepic = itemView.findViewById(R.id.profilepic);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override

                public void onClick (View view) {
                    if (personalityRecyclerinterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            personalityRecyclerinterface.onItemClick(pos);

                        }
                    }
                }
            });

        }

    }
}
