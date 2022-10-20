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
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.personalitytest.models.Personality;
import com.example.personalitytest.models.Question;
import com.example.personalitytest.models.Trait;
import com.example.personalitytest.models.User;

import org.json.JSONArray;
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
    /*@RequiresApi(api = Build.VERSION_CODES.O)
    public static void signUp(String name, String email, String password, String dob, String gender, Activity activity, final UserCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = baseURL + "signup";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("name", name);
        jsonBody.put("email", email);
        jsonBody.put("password", password);
        jsonBody.put("dob", dob);
        jsonBody.put("gender", gender);
        final String requestBody = jsonBody.toString();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("LOG_VOLLEY", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.i("ERROR","error => "+error.toString());
                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        queue.add(postRequest);
    }*/


    //-----------------------------------------EditUser Function---------------------------------------------


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

