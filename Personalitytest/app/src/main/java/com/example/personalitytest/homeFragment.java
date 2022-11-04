package com.example.personalitytest;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.personalitytest.models.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private String userDOB;

    private ArrayList<User> userInfo = new ArrayList<User>();
    private TextView nameText;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homeFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
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

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Log.d("From fragment", "Current User: " + userInfo.get(0).getName());

        nameText = view.findViewById(R.id.name);
        nameText.setText(userInfo.get(0).getName());

        TextView tests = (TextView) view.findViewById(R.id.startQuizBtn);
        tests.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bundle userInformation = new Bundle();
                userInformation.putParcelableArrayList("userInfo",userInfo);
                testActivity(userInformation);
            }

        });

        return view;
    }

    public void testActivity(Bundle bundle) {
        Intent intent = new Intent(getActivity(), Tests.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}