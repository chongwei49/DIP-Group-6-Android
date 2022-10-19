package com.example.personalitytest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.personalitytest.models.User;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private ImageView backimage;
    private Button button;
    private TextView emailText, passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.loginEmail);
        passText = findViewById(R.id.loginPass);

        backimage = (ImageView) findViewById(R.id.loginBck);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backmainactivity();
            }
        });

        button = (Button) findViewById(R.id.LogInBtn2);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //login(emailText.getText().toString(), passText.getText().toString());
                User user = Services.login(emailText.getText().toString(), passText.getText().toString(), Login.this);
                Log.d("userList", user.getName());
                /*if(userLogin != null) {
                    Bundle userInformation = new Bundle();
                    userInformation.putInt("userId", (userLogin.indexOf(0)).getUserId());
                    userInformation.putString("name", name);
                    userInformation.putString("email", email);
                    userInformation.putString("dob", dob);
                    homeactivity();
                }*/

                //homeactivity();
            }
        });

    }

    public void backmainactivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void homeActivity(Bundle bundle) {
        Intent intent = new Intent(this, Home.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
    
    /*@RequiresApi(api = Build.VERSION_CODES.O)
    public void login(String email, String password){
        String code = "Basic " +Base64.getEncoder().encodeToString((email + ":" + password).getBytes());
        Log.i("Code", code);
        RequestQueue queue = Volley.newRequestQueue(this);
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


                            Bundle userInformation = new Bundle();
                            userInformation.putString("userId", userId);
                            userInformation.putString("name", name);
                            userInformation.putString("email", email);
                            userInformation.putString("gender", gender);
                            userInformation.putString("dob", dob);

                            Log.i("Response", response);
                            if (response.startsWith("{")) {
                                homeActivity(userInformation);
                            }

                            Log.d("user name", userObject.getString("name"));
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
    }*/


}