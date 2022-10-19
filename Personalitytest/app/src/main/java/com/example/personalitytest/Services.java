package com.example.personalitytest;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.personalitytest.models.Personality;
import com.example.personalitytest.models.Question;
import com.example.personalitytest.models.Trait;
import com.example.personalitytest.models.User;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Services {
    static String baseURL = "https://soma-app-be.herokuapp.com/api/v2/";

    //-------------------------------------------- Login Function -----------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void login(String email, String password, Activity activity, final UserCallback callback){
        String code = "Basic " +Base64.getEncoder().encodeToString((email + ":" + password).getBytes());
        Log.i("Code", code);
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = "https://soma-app-be.herokuapp.com/api/v2/login";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            // convert response to JSON object
                            JSONObject userObject = new JSONObject(response);
                            String userId = userObject.getString("userId");
                            String name = userObject.getString("name");
                            String email = userObject.getString("email");
                            String gender = userObject.getString("gender");
                            String dob = userObject.getString("dob");
                            byte[] profilePic = (userObject.getString("profilePic")).getBytes(StandardCharsets.UTF_8);
                            Log.i("Response", response);

                            ArrayList<User> user_list = new ArrayList<User>();
                            User user = new User();

                            user.setUserId(Integer.valueOf(userId));
                            user.setName(name);
                            user.setEmail(email);
                            user.setPassword(password);
                            user.setDob(dob);
                            user.setProfilePic(profilePic);

                            user_list.add(user);

                            callback.onSuccess(user_list);
                        } catch (Throwable tx) {
                            Log.e("Error:", "Error parsing JSON");
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.i("ERROR","error => "+error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", code);

                return params;
            }
        };
        queue.add(getRequest);
    }


    //-------------------------------------------Get All Users Function----------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<User> getAllUsers(Activity activity) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = baseURL + "users";
        ArrayList<User> userList = new ArrayList<>();
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            // convert response to JSON object
                            JSONObject userObject = new JSONObject(response);
                            Integer count = userObject.length();
                            for(int i=0; i < count; i++){
                                Integer userId = userObject.getInt("userId");
                                String name = userObject.getString("name");
                                String email = userObject.getString("email");
                                String password = userObject.getString("password");
                                String dob = userObject.getString("dob");
                                byte[] profilePic =  (userObject.getString("profilePic")).getBytes(StandardCharsets.UTF_8);

                                User user = new User(userId, name, email, password, dob, profilePic);
                                userList.add(user);
                            }


                            Log.i("Response", response);
                            Log.d("user name", userObject.getString("name"));
                        } catch (Throwable tx) {
                            Log.e("Error:", "Error parsing JSON");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.i("ERROR", "error => " + error.toString());
                    }
                }
        );
        queue.add(getRequest);
        return userList;
    }

    public interface UserCallback{
        void onSuccess(ArrayList<User> result);
    }

    public interface QuestionCallback{
        void onSuccess(ArrayList<Question> result);
    }

    public interface TraitCallback{
        void onSuccess(ArrayList<Trait> result);
    }

    public interface PersonalityCallback{
        void onSuccess(ArrayList<Personality> result);
    }
}

