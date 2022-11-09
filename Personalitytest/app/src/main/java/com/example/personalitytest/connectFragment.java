package com.example.personalitytest;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    private Boolean personality_16 = false, personality_love = false, personality_job = false;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Integer userId;
    RecyclerView personalityRecyclerView, loveRecyclerView, workRecyclerView;


    private Button personalitynext, lovenext, worknext;
    private Button notdone_personaltystart, notdone_lovestart, notdone_careerstart;
    private CardView notdone_personalty, notdone_love, notdone_career;

    private ArrayList<User> user_connect_personality_List =  new ArrayList<>();
    private ArrayList<User> user_connect_love_List =  new ArrayList<>();
    private ArrayList<User> user_connect_career_List =  new ArrayList<>();

    private ArrayList<User> userInfo = new ArrayList<User>();
    private ArrayList<User> allUsers = new ArrayList<User>();
    private ArrayList<Personality> personalityList = new ArrayList<Personality>();
    private ArrayList<Personality> personalityList_16 = new ArrayList<Personality>();
    private ArrayList<Personality> personalityList_job = new ArrayList<Personality>();
    private ArrayList<Personality> personalityList_love = new ArrayList<Personality>();
    private ArrayList<Trait> traitsList = new ArrayList<Trait>();


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
            userId = userInfo.get(0).getUserId();
            personalityList=userInformation.getParcelableArrayList("personality_information");
            allUsers=userInformation.getParcelableArrayList("all_users");
            traitsList=userInformation.getParcelableArrayList("traits4prof");
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
            setUpCatergoryUserList();
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

//        arrayList.add( new Profile(R.drawable.profile_pic_1, "Sam", "18"));
//        arrayList.add( new Profile(R.drawable.profile_pic_2, "Anthony", "17"));
//        arrayList.add( new Profile(R.drawable.profile_pic_1, "David", "21"));
//        arrayList.add( new Profile(R.drawable.profile_pic_2, "Max", "25"));
//        arrayList.add( new Profile(R.drawable.profile_pic_1, "Love", "31"));
//        arrayList.add( new Profile(R.drawable.profile_pic_2, "Judy", "35"));
//
//        profileList.add( new Profile(R.drawable.profile_pic_1, "Sam", "18"));
//        profileList.add( new Profile(R.drawable.profile_pic_2, "Anthony", "17"));
//        profileList.add( new Profile(R.drawable.profile_pic_1, "David", "21"));
//        profileList.add( new Profile(R.drawable.profile_pic_2, "Max", "25"));
//        profileList.add( new Profile(R.drawable.profile_pic_1, "Love", "31"));
//        profileList.add( new Profile(R.drawable.profile_pic_2, "Judy", "35"));

        personalityadapter Personalityadapter = new personalityadapter(getContext(), user_connect_personality_List, this);
        personalityRecyclerView.setAdapter(Personalityadapter);
        personalityRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        loveadapter Loveadapter = new loveadapter(getContext(), user_connect_love_List,  this);
        loveRecyclerView.setAdapter(Loveadapter);
        loveRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        workadapter Workadapter = new workadapter(getContext(), user_connect_career_List,  this);
        workRecyclerView.setAdapter(Workadapter);
        workRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

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
    public void onItemClick(int position) { //Default Function, IGNORE

    }

    @Override
    public void onItemClick(int position, ArrayList<User> arrayList) { //Overloaded function
        Intent intent = new Intent(getActivity(),connect_person.class);
        intent.putExtra("UserInfo", arrayList.get(position));
//        Bundle bundle = new Bundle();
//        bundle.put
//        bundle.putParcelableArrayList("traits4prof",traitsList);
//        intent.putExtras(bundle);
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
        //Clear arraylist before storing filtered latest quiz result
        personalityList_16.clear();

        for (Map.Entry<Integer, Personality> set :
                personalityMap_16.entrySet()) {
            //Store each personality into list
            personalityList_16.add(set.getValue());
            // Printing all elements of a Map
            System.out.println(set.getKey() + " = "
                    + "ID: " + set.getValue().getUserId() + " Time: " + set.getValue().getDateTime()+ " Cat: " + set.getValue().getQnCategory());
        }
        //----------------------------------------------------------------------------------------------------------------------------------------------//
        Map<Integer, Personality> personalityMap_love = personalityList_love.stream()
                .collect(
                        Collectors.groupingBy(Personality::getUserId,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparing(Personality::getPriId)),
                                        Optional::get)));
        //Clear arraylist before storing filtered latest quiz result
        personalityList_love.clear();

        for (Map.Entry<Integer, Personality> set :
                personalityMap_love.entrySet()) {
            //Store each personality into list
            personalityList_love.add(set.getValue());
            // Printing all elements of a Map
            System.out.println(set.getKey() + " = "
                    + "ID: " + set.getValue().getUserId() + " Time: " + set.getValue().getDateTime()+ " Cat: " + set.getValue().getQnCategory());
        }
        //----------------------------------------------------------------------------------------------------------------------------------------------//
        Map<Integer, Personality> personalityMap_job = personalityList_job.stream()
                .collect(
                        Collectors.groupingBy(Personality::getUserId,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparing(Personality::getPriId)),
                                        Optional::get)));
        //Clear arraylist before storing filtered latest quiz result
        personalityList_job.clear();

        for (Map.Entry<Integer, Personality> set :
                personalityMap_job.entrySet()) {
            //Store each personality into list
            personalityList_job.add(set.getValue());
            // Printing all elements of a Map
            System.out.println(set.getKey() + " = "
                    + "ID: " + set.getValue().getUserId() + " Time: " + set.getValue().getDateTime()+ " Cat: " + set.getValue().getQnCategory());
        }
    }


    public void setUpCardView(){
        for (int i=0; i < personalityList_16.size(); i++) {
            Log.d("usercheck 16Personality", Integer.toString(personalityList_16.get(i).getUserId()));
            if (personalityList_16.get(i).getUserId().equals(userId)) {
                personality_16 = true;
            }
        }
        for (int i=0; i < personalityList_love.size(); i++) {
            if (personalityList_love.get(i).getUserId().equals(userId)) {
                personality_love = true;
            }
        }
        for (int i=0; i < personalityList_job.size(); i++) {
            if(personalityList_job.get(i).getUserId().equals(userId)){
                personality_job = true;
            }
        }
        Log.d("16Personality check: ", personality_16.toString());
        Log.d("LovePersonality check: ", personality_love.toString());
        Log.d("JobPersonality check: ", personality_job.toString());
        if (personality_16) {
            notdone_personalty.setVisibility(View.GONE);
        }else{
            notdone_personalty.setVisibility(View.VISIBLE);
        }
        if (personality_love) {
            notdone_love.setVisibility(View.GONE);
        }else{
            notdone_love.setVisibility(View.VISIBLE);
        }
        if (personality_job) {
            notdone_career.setVisibility(View.GONE);
        }else{
            notdone_career.setVisibility(View.VISIBLE);
        }
    }

    public void setUpCatergoryUserList(){
        for(int i=0;i<allUsers.size();i++){
            if(!allUsers.get(i).getUserId().equals(userId)){
                for(int j=0;j<personalityList_16.size();j++){
                    if(personalityList_16.get(j).getUserId().equals(allUsers.get(i).getUserId())){
                        user_connect_personality_List.add(allUsers.get(i));
                    }
                }

                for(int j=0;j<personalityList_love.size();j++){
                    if(personalityList_love.get(j).getUserId().equals(allUsers.get(i).getUserId())){
                        user_connect_love_List.add(allUsers.get(i));
                    }
                }

                for(int j=0;j<personalityList_job.size();j++){
                    if(personalityList_job.get(j).getUserId().equals(allUsers.get(i).getUserId())){
                        user_connect_career_List.add(allUsers.get(i));
                    }
                }
            }
        }

        //Shuffle list for all 3 user list
        Collections.shuffle(user_connect_personality_List);
        Collections.shuffle(user_connect_love_List);
        Collections.shuffle(user_connect_career_List);

    }

}