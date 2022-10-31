package com.example.personalitytest;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private ImageView backimage;
    private Button button, googleBtn;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private TextView emailText, passText;
    private ArrayList<User> userInfo = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);


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
                ProgressDialog dialog = ProgressDialog.show(Login.this, "",
                        "Loading. Please wait...", true);
                Services.login(emailText.getText().toString(), passText.getText().toString(), Login.this, new Services.UserCallback() {
                    @Override
                    public void onSuccess(ArrayList<User> result) {
                        Log.d("Response result", String.valueOf(result.get(0).getName()));
                        dialog.cancel();
                        if(!result.isEmpty()){
                            Bundle userInformation = new Bundle();
                            userInfo=result;
//                            userInformation.putInt("userId", result.get(0).getUserId());
//                            userInformation.putString("name", result.get(0).getName());
//                            userInformation.putString("email", result.get(0).getEmail());
//                            userInformation.putString("gender", result.get(0).getGender());
//                            userInformation.putString("dob", result.get(0).getDob());

                            userInformation.putParcelableArrayList("userInfo",userInfo);
                            homeActivity(userInformation);
                            Log.d("Login","Successful");
                        }else{
                            Log.d("Else Response", "Multiple User Object Detected");
                        }
                    }
                });
            }
        });

        googleBtn = (Button) findViewById(R.id.googleBtn);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithGoogle();
            }
        });
    }


    public void loginWithGoogle() {
        Intent signInWithGoogleIntent = gsc.getSignInIntent();
        startActivityForResult(signInWithGoogleIntent, 1000);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);

                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
                if (acct != null) {
                    String personName = acct.getDisplayName();
                    String personEmail = acct.getEmail();
                    Services.getAllUsers( Login.this, new Services.UserCallback() {
                        @Override
                        public void onSuccess(ArrayList<User> result) {
                            Log.d("Response result", String.valueOf(result.get(0).getName()));
                            if(!result.isEmpty()){

                                boolean userExist = false;

                                //find if the user is already exist in the user db
                                for(int i=0;i<result.size();i++){
                                    Log.d("test getAllUsers",result.get(i).getName());
                                    if (result.get(i).getEmail().equals(personEmail)) {
                                        userInfo.add(result.get(i));

                                        Bundle userInformation = new Bundle();
                                        userInformation.putParcelableArrayList("userInfo",userInfo);
                                        homeActivity(userInformation);
                                        userExist = true;
                                    }
                                }

                                // post-request if the user not in the db
                               if (!userExist) {
                                   Services.signUp(personName, personEmail, null, null, null, Login.this, new Services.UserCallback() {
                                       @Override
                                       public void onSuccess(ArrayList<User> result) {
                                           Log.d("PostReq:", "Post Request Success");
                                           if(!result.isEmpty()){
                                               userInfo = result;

                                               Bundle userInformation = new Bundle();
                                               userInformation.putParcelableArrayList("userInfo",userInfo);
                                               homeActivity(userInformation);
                                               Log.d("userId Check", result.get(0).getUserId().toString());
                                           }else{
                                               Log.d("Else Response", "Multiple User Object Detected");
                                           }
                                       }
                                   });

                               }


                            }else{
                                Log.d("Else Response", "Multiple User Object Detected");
                            }
                        }
                    });

//                    Bundle userInformation = new Bundle();
//                    userInformation.putString("name", personName);
//                    userInformation.putString("email", personEmail);
//                    userInformation.putString("dob", "");
//                    homeactivity(userInformation);
                }
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void backmainactivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void homeActivity(Bundle bundle) {
        Intent intent = new Intent(this, Home.class);
        intent.putExtras(bundle);
        startActivity(intent);
        this.finish();

    }
    
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void login(String email, String password, Activity activity, final UserCallback callback){
//        String code = "Basic " +Base64.getEncoder().encodeToString((email + ":" + password).getBytes());
//        Log.i("Code", code);
//        RequestQueue queue = Volley.newRequestQueue(activity);
//        String url = "https://soma-app-be.herokuapp.com/api/v2/login";
//        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response) {
//                        // response
//                        try {
//                            // convert response to JSON object
//                            JSONObject userObject = new JSONObject(response);
//                            String userId = userObject.getString("userId");
//                            String name = userObject.getString("name");
//                            String email = userObject.getString("email");
//                            String gender = userObject.getString("gender");
//                            String dob = userObject.getString("dob");
//                            byte[] profilePic = (userObject.getString("profilePic")).getBytes(StandardCharsets.UTF_8);
//                            Log.i("Response", response);
//
//                            ArrayList<User> user_list = new ArrayList<User>();
//                            User user = new User();
//
//                            user.setUserId(Integer.valueOf(userId));
//                            user.setName(name);
//                            user.setEmail(email);
//                            user.setPassword(password);
//                            user.setDob(dob);
//                            user.setProfilePic(profilePic);
//
//                            user_list.add(user);
//
//                            callback.onSuccess(user_list);
//                        } catch (Throwable tx) {
//                            Log.e("Error:", "Error parsing JSON");
//                        }
//
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // TODO Auto-generated method stub
//                        Log.i("ERROR","error => "+error.toString());
//                    }
//                }
//        ) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("Authorization", code);
//
//                return params;
//            }
//        };
//        queue.add(getRequest);
//    }
//
//    public interface UserCallback{
//        void onSuccess(ArrayList<User> result);
//    }
//
//    public interface QuestionCallback{
//        void onSuccess(ArrayList<Question> result);
//    }
//
//    public interface TraitCallback{
//        void onSuccess(ArrayList<Trait> result);
//    }
//
//    public interface PersonalityCallback{
//        void onSuccess(ArrayList<Personality> result);
//    }
}