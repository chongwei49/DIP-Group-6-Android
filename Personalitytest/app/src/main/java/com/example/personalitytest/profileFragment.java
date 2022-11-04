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
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personalitytest.models.User;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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

    private ArrayList<User> userInfo = new ArrayList<User>();

    private TextView usernameText, emailText, settingsbtn;

    private ImageView changeDPbtn;
    private CircleImageView userProfilePicture;

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            Bundle userInformation = this.getArguments();

            userInfo=userInformation.getParcelableArrayList("userInfo");

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
        usernameText.setText(userName);
        emailText.setText(userEmail);
        settingsbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bundle userInformation = new Bundle();
//                userInformation.putInt("userId", userId);
//                userInformation.putString("name", userName);
//                userInformation.putString("email", userEmail);
//                userInformation.putString("gender", userGender);
//                userInformation.putString("dob", userDOB);
                userInformation.putParcelableArrayList("userInfo",userInfo);
                settingsActivity(userInformation);
            }

        });

        TextView personalityhistory = (TextView) view.findViewById(R.id.personalityHistory);
        personalityhistory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(getActivity(), hist_personality.class);
                startActivity(in);
            }

        });

        TextView lovehistory = (TextView) view.findViewById(R.id.loveHistory);
        lovehistory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(getActivity(), hist_love.class);
                startActivity(in);
            }

        });

        TextView careerhistory = (TextView) view.findViewById(R.id.careerHistory);
        careerhistory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(getActivity(), hist_career.class);
                startActivity(in);
            }

        });


        return view;
    }

    public void settingsActivity(Bundle bundle) {
        Intent intent = new Intent(getActivity(), usersettings.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            userProfilePicture.setImageURI(selectedImage);
        }
    }

    public Bitmap receiveImage(byte[] input_byte){
        Bitmap bmp= BitmapFactory.decodeByteArray(input_byte,0,input_byte.length);
        return bmp;
    }

    public byte[] sentImage(Bitmap input_bmp){
        Bitmap bmp = input_bmp;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bmp.recycle();

        return  byteArray;
    }
}