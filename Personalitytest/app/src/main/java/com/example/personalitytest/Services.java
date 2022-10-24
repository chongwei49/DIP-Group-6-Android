package com.example.personalitytest;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.personalitytest.models.Personality;
import com.example.personalitytest.models.Question;
import com.example.personalitytest.models.Trait;
import com.example.personalitytest.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
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
        String url = baseURL + "login";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            // convert response to JSON object
                            JSONObject userObject = new JSONObject(response);
                            Log.i("Response", response);

                            ArrayList<User> user_list = new ArrayList<User>();

                            user_list.add(new User(
                                    userObject.getInt("userId"),
                                    userObject.getString("name"),
                                    userObject.getString("email"),
                                    userObject.getString("password"),
                                    userObject.getString("dob"),
                                    userObject.getString("gender"),
                                    (userObject.getString("profilePic")).getBytes(StandardCharsets.UTF_8)));

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
    public static void getAllUsers(Activity activity, final UserCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = baseURL + "users";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            // convert response to JSON object
                            JSONArray array = new JSONArray(response);

                            ArrayList<User> userList = new ArrayList<>();

                            for(int i=0; i < array.length(); i++){
                                JSONObject userObject = array.getJSONObject(i);

                                userList.add(new User(
                                        userObject.getInt("userId"),
                                        userObject.getString("name"),
                                        userObject.getString("email"),
                                        userObject.getString("password"),
                                        userObject.getString("dob"),
                                        userObject.getString("gender"),
                                        (userObject.getString("profilePic")).getBytes(StandardCharsets.UTF_8)));
                            }
                            callback.onSuccess(userList);

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
    }


    //---------------------------------------------SignUp Function--------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void signUp(String name, String email, String password, String dob, String gender, Activity activity, final UserCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = baseURL + "signup";
        JSONObject js = new JSONObject();
        try {
            js.put("name",name);
            js.put("email", email);
            js.put("password", password);
            js.put("dob", dob);
            js.put("gender", gender);
            Log.d("js inputs", js.toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
            e.printStackTrace();
        }
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("LOG_VOLLEY", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                Log.d("Response res", res);
                                // Now you can use any deserializer to make sense of data
                                //JSONObject obj = new JSONObject(res);
                                //Log.d("Response obj", obj.toString());
                            } catch (UnsupportedEncodingException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                                Log.e("e1 error", "couldn't properly decode data to string");
                            } /*catch (JSONException e2) {
                                // returned data is not JSONObject?
                                e2.printStackTrace();
                                Log.e("e2 error", "returned data not JSONObject?");
                            }*/
                        }
                        // TODO Auto-generated method stub
                        Log.i("ERROR","error => "+error.toString());
                    }
                }
        ) {
            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        // Adding request to request queue
        Volley.newRequestQueue(activity).add(postRequest);
    }


    //-----------------------------------------EditUser Function---------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void editUser(Integer userId, String name, String email, String password, String dob, String gender, Activity activity, final UserCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = baseURL + "users/" + userId;
        JSONObject js = new JSONObject();
        try {
            js.put("userId", userId);
            js.put("name",name);
            js.put("email", email);
            js.put("password", password);
            js.put("dob", dob);
            js.put("gender", gender);
            Log.d("js inputs", js.toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
            e.printStackTrace();
        }
        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("LOG_VOLLEY", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                Log.d("Response res", res);
                                // Now you can use any deserializer to make sense of data
                                //JSONObject obj = new JSONObject(res);
                                //Log.d("Response obj", obj.toString());
                            } catch (UnsupportedEncodingException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                                Log.e("e1 error", "couldn't properly decode data to string");
                            } /*catch (JSONException e2) {
                                // returned data is not JSONObject?
                                e2.printStackTrace();
                                Log.e("e2 error", "returned data not JSONObject?");
                            }*/
                        }
                        // TODO Auto-generated method stub
                        Log.i("ERROR","error => "+error.toString());
                    }
                }
        ) {
            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        // Adding request to request queue
        Volley.newRequestQueue(activity).add(putRequest);
    }



    //------------------------------------GetAllPersonalities Function----------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void getAllPersonalities(Activity activity, final PersonalityCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = baseURL + "personalities";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            // convert response to JSON object
                            JSONArray array = new JSONArray(response);

                            ArrayList<Personality> personalityList = new ArrayList<>();

                            for(int i=0; i < array.length(); i++){
                                JSONObject personalityObject = array.getJSONObject(i);

                                personalityList.add(new Personality(
                                        personalityObject.getInt("priId"),
                                        personalityObject.getInt("userId"),
                                        personalityObject.getString("qnCategory"),
                                        personalityObject.getString("personalityType"),
                                        personalityObject.getString("dateTime")));
                            }
                            callback.onSuccess(personalityList);

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
    }



    //-------------------------------------GetAllTraits Function-------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void getAllTraits(Activity activity, final TraitCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = baseURL + "traits";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            // convert response to JSON object
                            JSONArray array = new JSONArray(response);
                            Log.d("TRAITS", response);
                            ArrayList<Trait> traitsList = new ArrayList<>();

                            for(int i=0; i < array.length(); i++){
                                JSONObject traitsObject = array.getJSONObject(i);

                                traitsList.add(new Trait(
                                        traitsObject.getInt("priId"),
                                        traitsObject.getString("quizCategory"),
                                        traitsObject.getString("personalityType"),
                                        traitsObject.getString("traitName"),
                                        traitsObject.getString("description")));
                            }
                            callback.onSuccess(traitsList);

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
    }



    //------------------------------------GetAllQuestions Function-----------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void getAllQuestions(Activity activity, final QuestionCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = baseURL + "questions";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            // convert response to JSON object
                            JSONArray array = new JSONArray(response);

                            ArrayList<Question> questionsList = new ArrayList<>();

                            for(int i=0; i < array.length(); i++){
                                JSONObject questionObject = array.getJSONObject(i);

                                questionsList.add(new Question(
                                        questionObject.getInt("priId"),
                                        questionObject.getInt("qnId"),
                                        questionObject.getString("qnCategory"),
                                        questionObject.getString("qns"),
                                        questionObject.getBoolean("answer"),
                                        questionObject.getString("traits")));
                            }
                            callback.onSuccess(questionsList);

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

