package com.example.personalitytest;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personalitytest.models.Personality;
import com.example.personalitytest.models.Trait;
import com.example.personalitytest.models.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link connectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class connectFragment extends Fragment implements personalityrecyclerinterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView personalityRecyclerView, loveRecyclerView, workRecyclerView;
    ArrayList<Profile> arrayList =  new ArrayList<>();
    ArrayList<Profile> profileList =  new ArrayList<>();
    ArrayList<String> dataSource;

    private Button personalitynext, lovenext, worknext;
    private Button notdone_personaltystart, notdone_lovestart, notdone_careerstart;
    private CardView notdone_personalty, notdone_love, notdone_career;

    private ArrayList<User> userInfo = new ArrayList<User>();
    private ArrayList<User> allUsers = new ArrayList<User>();
    private ArrayList<Personality> personalityList = new ArrayList<Personality>();
    private ArrayList<Personality> personalityList_16 = new ArrayList<Personality>();
    private ArrayList<Personality> personalityList_job = new ArrayList<Personality>();
    private ArrayList<Personality> personalityList_love = new ArrayList<Personality>();


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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            Bundle userInformation = this.getArguments();
            userInfo=userInformation.getParcelableArrayList("userInfo");
            personalityList=userInformation.getParcelableArrayList("personality_information");
            allUsers=userInformation.getParcelableArrayList("all_users");
            for(int i =0;i<personalityList.size();i++){
                if(personalityList.get(i).getQnCategory().contains("16Personalities")){
                    personalityList_16.add(personalityList.get(i));
                }
                else if(personalityList.get(i).getQnCategory().contains("Love")){
                    personalityList_love.add(personalityList.get(i));
                }
                else if(personalityList.get(i).getQnCategory().contains("Job")){
                    personalityList_job.add(personalityList.get(i));
                }
                Log.d("ConFragment Per_List:",personalityList.get(i).getUserId().toString());
            }
            for(int i =0;i<allUsers.size();i++){
                Log.d("ConFragment Per_List:",allUsers.get(i).getName());
            }

            setUpUserListCat();

            //LocalDate.parse(indepdate, DateTimeFormatter.ofPattern("M/d/u")
        }


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_connect,container, false);

        Log.d("From fragment", "Current User: " + userInfo.get(0).getName());

        personalityRecyclerView = view.findViewById(R.id.personalityRecyclerView);
        loveRecyclerView = view.findViewById(R.id.loveRecyclerView);
        workRecyclerView = view.findViewById(R.id.workRecyclerView);

        arrayList.add( new Profile(R.drawable.profile_pic_1, "Sam", "18"));
        arrayList.add( new Profile(R.drawable.profile_pic_2, "Anthony", "17"));
        arrayList.add( new Profile(R.drawable.profile_pic_1, "David", "21"));
        arrayList.add( new Profile(R.drawable.profile_pic_2, "Max", "25"));
//        arrayList.add( new Profile(R.drawable.profile_pic_1, "Love", "31"));
//        arrayList.add( new Profile(R.drawable.profile_pic_2, "Judy", "35"));

        profileList.add( new Profile(R.drawable.profile_pic_1, "Sam", "18"));
        profileList.add( new Profile(R.drawable.profile_pic_2, "Anthony", "17"));
        profileList.add( new Profile(R.drawable.profile_pic_1, "David", "21"));
        profileList.add( new Profile(R.drawable.profile_pic_2, "Max", "25"));
//        profileList.add( new Profile(R.drawable.profile_pic_1, "Love", "31"));
//        profileList.add( new Profile(R.drawable.profile_pic_2, "Judy", "35"));

        personalityadapter Personalityadapter = new personalityadapter(getContext(),arrayList, this);
        personalityRecyclerView.setAdapter(Personalityadapter);
        personalityRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        loveadapter Loveadapter = new loveadapter(getContext(), profileList,  this);
        loveRecyclerView.setAdapter(Loveadapter);
        loveRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        workadapter Workadapter = new workadapter(getContext(), profileList,  this);
        workRecyclerView.setAdapter(Workadapter);
        workRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        //Setting the data source
        //dataSource = new ArrayList<>();


        /*dataSource.add("Sam");
        dataSource.add("Anthony");
        dataSource.add("Lucy");
        dataSource.add("Max");
        dataSource.add("Andy");*/


        notdone_personalty = view.findViewById(R.id.notdone_personality);
        notdone_love = view.findViewById(R.id.notdone_love);
        notdone_career = view.findViewById(R.id.notdone_career);



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
                /*Intent in = new Intent(getActivity(), QuizLove.class);
                startActivity(in);*/
                connectFragment fg = new connectFragment();
                fg.setArguments(getArguments());

                getFragmentManager()  // or getSupportFragmentManager() if your fragment is part of support library
                        .beginTransaction()
                        .replace(R.id.container, fg)
                        .commit();
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


        setUpCardView();

        return view;
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(),connect_person.class);
        intent.putExtra("Profile Pic", arrayList.get(position).getImage());
        intent.putExtra("Name", arrayList.get(position).getName());
        intent.putExtra("Age", arrayList.get(position).getAge());
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setUpUserListCat(){
        Map<Integer, Personality> personalityMap_16 = personalityList_16.stream()
                .collect(
                        Collectors.groupingBy(Personality::getUserId,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparing(Personality::getPriId)),
                                        Optional::get)));

        for (Map.Entry<Integer, Personality> set :
                personalityMap_16.entrySet()) {

            // Printing all elements of a Map
            System.out.println(set.getKey() + " = "
                    + "ID: " + set.getValue().getUserId() + " Time: " + set.getValue().getDateTime()+ " Cat: " + set.getValue().getQnCategory());
        }

        Map<Integer, Personality> personalityMap_love = personalityList_love.stream()
                .collect(
                        Collectors.groupingBy(Personality::getUserId,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparing(Personality::getPriId)),
                                        Optional::get)));

        for (Map.Entry<Integer, Personality> set :
                personalityMap_love.entrySet()) {

            // Printing all elements of a Map
            System.out.println(set.getKey() + " = "
                    + "ID: " + set.getValue().getUserId() + " Time: " + set.getValue().getDateTime()+ " Cat: " + set.getValue().getQnCategory());
        }

        Map<Integer, Personality> personalityMap_job = personalityList_job.stream()
                .collect(
                        Collectors.groupingBy(Personality::getUserId,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparing(Personality::getPriId)),
                                        Optional::get)));

        for (Map.Entry<Integer, Personality> set :
                personalityMap_job.entrySet()) {

            // Printing all elements of a Map
            System.out.println(set.getKey() + " = "
                    + "ID: " + set.getValue().getUserId() + " Time: " + set.getValue().getDateTime()+ " Cat: " + set.getValue().getQnCategory());
        }
    }


    public void setUpCardView(){
        if(personalityList_16.size()>=4){
            notdone_personalty.setVisibility(View.GONE);
        }else{
            notdone_personalty.setVisibility(View.VISIBLE);
        }

        if(personalityList_love.size()>=4){
            notdone_love.setVisibility(View.GONE);
        }else{
            notdone_love.setVisibility(View.VISIBLE);
        }

        if(personalityList_job.size()>=4){
            notdone_career.setVisibility(View.GONE);
        }else{
            notdone_career.setVisibility(View.VISIBLE);
        }


    }

}