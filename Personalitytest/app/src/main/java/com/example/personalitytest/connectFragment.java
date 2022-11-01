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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personalitytest.models.Personality;
import com.example.personalitytest.models.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link connectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class connectFragment extends Fragment implements personalityrecyclerinterface{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView personalityRecyclerView, loveRecyclerView, workRecyclerView;
    ArrayList<Profile> arrayList =  new ArrayList<>();
    ArrayList<String> dataSource;
    private Button personalitynext, lovenext, worknext;
    private Button notdone_personaltystart, notdone_lovestart, notdone_careerstart;

    private ArrayList<User> userInf = new ArrayList<User>();

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

            Bundle usersInfo = this.getArguments();
            userInf=usersInfo.getParcelableArrayList("user_information");
            for(int i =0;i<userInf.size();i++){
                Log.d("Connect Fragment Test ",userInf.get(i).getName());
            }


        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_connect,container, false);

        personalityRecyclerView = view.findViewById(R.id.personalityRecyclerView);



        arrayList.add( new Profile(R.drawable.profile_pic_1, "Sam", "18"));
        arrayList.add( new Profile(R.drawable.profile_pic_2, "Anthony", "17"));
        arrayList.add( new Profile(R.drawable.profile_pic_1, "David", "21"));
        arrayList.add( new Profile(R.drawable.profile_pic_2, "Max", "25"));
        arrayList.add( new Profile(R.drawable.profile_pic_1, "Love", "31"));
        arrayList.add( new Profile(R.drawable.profile_pic_2, "Judy", "35"));

        personalityadapter Personalityadapter = new personalityadapter(getContext(),arrayList, this);
        personalityRecyclerView.setAdapter(Personalityadapter);
        personalityRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        //Setting the data source
        dataSource = new ArrayList<>();


        dataSource.add("Sam");
        dataSource.add("Anthony");
        dataSource.add("Lucy");
        dataSource.add("Max");
        dataSource.add("Andy");


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



        return view;
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(),connect_person.class);
        intent.putExtra("Profile Pic", arrayList.get(position).getImage());
        intent.putExtra("Name", arrayList.get(position).getName());
        intent.putExtra("Age", arrayList.get(position).getAge());
        startActivity(intent);
    }
}