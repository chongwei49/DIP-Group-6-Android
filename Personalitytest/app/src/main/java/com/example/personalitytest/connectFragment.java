package com.example.personalitytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Random;

import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link connectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class connectFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView personalityRecyclerView, loveRecyclerView, workRecyclerView;
    ArrayList<String> dataSource;
    connectFragment.MyPersonalityAdapter myPersonalityAdapter;
    connectFragment.MyLoveAdapter myLoveAdapter;
    connectFragment.MyWorkAdapter myWorkAdapter;
    private TextView connect, profile;
    private ImageView backimage;
    private Button personalitynext, lovenext, worknext;
    private Button notdone_personaltystart, notdone_lovestart, notdone_careerstart;

    public connectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment connectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static connectFragment newInstance(String param1, String param2) {
        connectFragment fragment = new connectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_connect,container, false);

        personalityRecyclerView = view.findViewById(R.id.personalityRecyclerView);
        loveRecyclerView = view.findViewById(R.id.loveRecyclerView);
        workRecyclerView = view.findViewById(R.id.workRecyclerView);

        //Setting the data source
        dataSource = new ArrayList<>();
        dataSource.add("Sam");
        dataSource.add("Anthony");
        dataSource.add("Lucy");
        dataSource.add("Max");
        dataSource.add("Andy");

        myPersonalityAdapter = new MyPersonalityAdapter(dataSource);
        personalityRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        personalityRecyclerView.setAdapter(myPersonalityAdapter);
        myLoveAdapter = new MyLoveAdapter(dataSource);
        loveRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        loveRecyclerView.setAdapter(myLoveAdapter);

        myWorkAdapter = new MyWorkAdapter(dataSource);
        workRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        workRecyclerView.setAdapter(myWorkAdapter);

        personalitynext = (Button) view.findViewById(R.id.personalitynext);
        personalitynext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(getActivity(), connect_person.class);
                startActivity(in);
            }

        });

        notdone_personaltystart = (Button) view.findViewById(R.id.notdone_personaltystart);
        notdone_personaltystart.setClickable(true);
        notdone_personaltystart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), QuizPersonality.class);
                startActivity(in);
            }
        });
        notdone_lovestart = (Button) view.findViewById(R.id.notdone_lovestart);
        notdone_lovestart.setClickable(true);
        notdone_lovestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), QuizLove.class);
                startActivity(in);
            }
        });

        notdone_careerstart = (Button) view.findViewById(R.id.notdone_careerstart);
        notdone_careerstart.setClickable(true);
        notdone_careerstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), QuizCareer.class);
                startActivity(in);
            }
        });

        lovenext = (Button) view.findViewById(R.id.lovenext);
        lovenext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(getActivity(), connect_person.class);
                startActivity(in);
            }

        });

        worknext = (Button) view.findViewById(R.id.worknext);
        worknext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(getActivity(), connect_person.class);
                startActivity(in);
            }

        });

        return view;
    }

    class MyPersonalityAdapter extends RecyclerView.Adapter<connectFragment.MyPersonalityAdapter.Personality>{
        ArrayList<String> data;
        public MyPersonalityAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public connectFragment.MyPersonalityAdapter.Personality onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accountprofile, parent, false);
            return new connectFragment.MyPersonalityAdapter.Personality(view);
        }

        @Override
        public void onBindViewHolder(@NonNull connectFragment.MyPersonalityAdapter.Personality holder, int position) {
            holder.name.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        class Personality extends RecyclerView.ViewHolder{
            TextView name;
            public Personality(@NonNull View itemView){
                super(itemView);

                name = itemView.findViewById(R.id.name);
            }
        }
    }
    class MyLoveAdapter extends RecyclerView.Adapter<connectFragment.MyLoveAdapter.Love>{
        ArrayList<String> data;
        public MyLoveAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public connectFragment.MyLoveAdapter.Love onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accountprofile, parent, false);
            return new connectFragment.MyLoveAdapter.Love(view);
        }

        @Override
        public void onBindViewHolder(@NonNull connectFragment.MyLoveAdapter.Love holder, int position) {
            Random random = new Random();
            int x = random.nextInt(data.size());
            holder.name.setText(data.get(x));
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        public class Love extends RecyclerView.ViewHolder{
            TextView name;
            public Love(@NonNull View itemView){
                super(itemView);

                name = itemView.findViewById(R.id.name);
            }
        }

    }
    class MyWorkAdapter extends RecyclerView.Adapter<connectFragment.MyWorkAdapter.Work>{
        ArrayList<String> data;
        public MyWorkAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Work onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accountprofile, parent, false);
            return new connectFragment.MyWorkAdapter.Work(view);
        }

        @Override
        public void onBindViewHolder(@NonNull connectFragment.MyWorkAdapter.Work holder, int position) {
            Random random = new Random();
            int x = random.nextInt(data.size());
            holder.name.setText(data.get(x));
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        public class Work extends RecyclerView.ViewHolder{
            TextView name;
            public Work(@NonNull View itemView){
                super(itemView);

                name = itemView.findViewById(R.id.name);
            }
        }

    }

}