package com.example.personalitytest;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
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

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private String userDOB;
    private byte[] userProfilePic;
    private Boolean personality_16 = false, personality_love = false, personality_job = false;
    private CardView notdone_personalty, notdone_love, notdone_career;

    private ArrayList<User> userInfo = new ArrayList<User>();
    private ArrayList<Personality> personalityList = new ArrayList<Personality>();
    private ArrayList<Trait> traitsList = new ArrayList<Trait>();
    private ArrayList<String> personalityTraits = new ArrayList<String>();
    private ArrayList<String> loveTraits = new ArrayList<String>();
    private ArrayList<String> careerTraits = new ArrayList<String>();

    private TextView usernameText, emailText, settingsbtn, persTrait, loveTrait,careerTrait;

    private ImageView changeDPbtn;
    private ImageView userProfilePicture;

    public profileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static profileFragment newInstance(String param1, String param2) {
        profileFragment fragment = new profileFragment();
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
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);

            Bundle userInformation = this.getArguments();

            userInfo=userInformation.getParcelableArrayList("userInfo");
            userId = userInfo.get(0).getUserId();
            personalityList=userInformation.getParcelableArrayList("personality_information");
            traitsList=userInformation.getParcelableArrayList("traits4prof");
            Log.d("userInfo", userInfo.toString());

            for(int i =0;i<personalityList.size();i++){
                //Log.d("test", personalityList.get(i).getPersonalityType());
                if(personalityList.get(i).getQnCategory().contains("16Personalities")){
                    if (personalityList.get(i).getUserId().equals(userId)) {
                        personalityTraits.add(personalityList.get(i).getPersonalityType());
                        personality_16 = true;
                    }
                }
                else if(personalityList.get(i).getQnCategory().contains("Love")){
                    if (personalityList.get(i).getUserId().equals(userId)) {
                        loveTraits.add(personalityList.get(i).getPersonalityType());
                        personality_love = true;
                    }
                }
                else if(personalityList.get(i).getQnCategory().contains("Job")){
                    if (personalityList.get(i).getUserId().equals(userId)) {
                        careerTraits.add(personalityList.get(i).getPersonalityType());
                        personality_job = true;
                    }
                }
                Log.d("ConFragment Per_List:",personalityList.get(i).getUserId().toString()+" cat:"+personalityList.get(i).getQnCategory());
            }

//            userId = userInformation.getInt("userId");
//            userName = userInformation.getString("name");
//            userEmail = userInformation.getString("email");
//            userGender = userInformation.getString("gender");
//            userDOB = userInformation.getString("DOB");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile,container, false);

        Log.d("From fragment", "Current User: " + userInfo.get(0).getName());

        settingsbtn = view.findViewById(R.id.settingsBtn);
        userProfilePicture = view.findViewById(R.id.userProfilePicture);
        changeDPbtn = view.findViewById(R.id.changeDPbtn);

        notdone_personalty = view.findViewById(R.id.notdone_personality);
        notdone_love = view.findViewById(R.id.notdone_love);
        notdone_career = view.findViewById(R.id.notdone_career);
        changeDPbtn.setVisibility(View.GONE);
        changeDPbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

        usernameText = view.findViewById(R.id.username);
        emailText=view.findViewById(R.id.email);
        userName=userInfo.get(0).getName(); //not getting user from history activity to profile fragment
        userEmail=userInfo.get(0).getEmail();
        userProfilePic=userInfo.get(0).getProfilePic();

        if(userProfilePic!=null){
            userProfilePicture.setImageBitmap(receiveImage(userProfilePic));
        }else{
            userProfilePicture.setImageResource(R.drawable.user);
        }

        //userProfilePicture.setImageResource(R.drawable.user);

        usernameText.setText(userName);
        emailText.setText(userEmail);

        settingsbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bundle userInformation = new Bundle();
                userInformation.putParcelableArrayList("userInfo",userInfo);
                settingsActivity(userInformation);
            }

        });

        TextView personalityhistory = (TextView) view.findViewById(R.id.personalityHistory);
        personalityhistory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bundle userInformation = new Bundle();
                userInformation.putParcelableArrayList("userInfo",userInfo);
                histPersonality(userInformation);
            }

        });

        TextView lovehistory = (TextView) view.findViewById(R.id.loveHistory);
        lovehistory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bundle userInformation = new Bundle();
                userInformation.putParcelableArrayList("userInfo",userInfo);
                histLove(userInformation);
            }

        });

        TextView careerhistory = (TextView) view.findViewById(R.id.careerHistory);
        careerhistory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bundle userInformation = new Bundle();
                userInformation.putParcelableArrayList("userInfo",userInfo);
                histCareer(userInformation);
            }

        });

        //setting Traits for profileFragment

        persTrait = view.findViewById(R.id.persTrait);
        loveTrait = view.findViewById(R.id.loveTrait);
        careerTrait = view.findViewById(R.id.careerTrait);
        /*for(int i=0;i<personalityTraits.size();i++){
            Log.d("persTrait",personalityTraits.get(i));
        }*/

        setUpTraitDescription();
        setUpCardView();


        return view;
    }

    public void settingsActivity(Bundle bundle) {
        Intent intent = new Intent(getActivity(), usersettings.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void histPersonality(Bundle bundle) {
        Intent intent = new Intent(getActivity(), hist_personality.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void histLove(Bundle bundle) {
        Intent intent = new Intent(getActivity(), hist_love.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void histCareer(Bundle bundle) {
        Intent intent = new Intent(getActivity(), hist_career.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    public Bitmap receiveImage(byte[] input_byte){
        Bitmap bmp= BitmapFactory.decodeByteArray(input_byte,0,input_byte.length);
        return bmp;
    }


    public void setUpTraitDescription(){
        if (personality_16) {
            persTrait.setText(personalityTraits.get(personalityTraits.size()-1));
            for(int i =0;i<traitsList.size();i++){
                if(persTrait.getText().equals(traitsList.get(i).getPersonalityType())){
                    String temp = String.valueOf(persTrait.getText());
                    Log.d("testgetDesc",traitsList.get(i).getDescription());
                    persTrait.setText(traitsList.get(i).getTraitName()+" ("+temp+")");
                }
            }
        }

        if (personality_love) {
            loveTrait.setText(loveTraits.get(loveTraits.size()-1));
        }

        if (personality_job) {
            careerTrait.setText(careerTraits.get(careerTraits.size()-1));
        }

    }

    public void setUpCardView(){
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
}