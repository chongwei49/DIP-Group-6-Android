package com.example.personalitytest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.personalitytest.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Calendar;
import android.widget.Spinner;
import android.widget.Toast;

public class usersettings extends AppCompatActivity {

    private Button button;
    private TextView button2, connect, results;
    private ImageView DPbtn;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Spinner spinner;
    private TextView newEmail, newName, username, email, nameInput, emailInput;
    

    private String USER_INFORMATION;
    private Integer userId;
    private String userName, userEmail, userPassword, userGender, userDOB;
    private byte[] userPP;
    private Bundle userInformation = new Bundle();
    private ArrayList<User> userInfo = new ArrayList<User>();

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersettings);

        getUserInfo();
        userId=userInfo.get(0).getUserId();
        userName=userInfo.get(0).getName();
        userEmail=userInfo.get(0).getEmail();
        userPassword = userInfo.get(0).getPassword();
        userGender=userInfo.get(0).getGender();
        userDOB= userInfo.get(0).getDob();
        userPP = userInfo.get(0).getProfilePic();

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        nameInput = findViewById(R.id.changenameInput);
        emailInput = findViewById(R.id.changeemailInput);
        username.setText(userName);
        email.setText(userEmail);
        nameInput.setText(userName);
        emailInput.setText(userEmail);


        initDatePicker();
        dateButton=findViewById(R.id.settingsdatepicker);
        if (userDOB != null){
            dateButton.setText(getUserDOB(userDOB));
        }
        else{
            dateButton.setText(getTodaysDate());
        }


        spinner=findViewById(R.id.settingsgenderpicker);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (userGender.equals("Female")){
            spinner.setSelection(adapter.getPosition("Female"));
        }

        newEmail = findViewById(R.id.changeemailInput);
        newName = findViewById(R.id.changenameInput);
        if (newEmail!=null) {
            userEmail = newEmail.getText().toString();
        }
        if (newName!=null) {
            userName = newName.getText().toString();
        }

        button = (Button) findViewById(R.id.LogOutBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingslogoutactivity();
            }
        });

        results = findViewById(R.id.resultsBtn);
        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle userInformation = new Bundle();
                userInformation.putParcelableArrayList("userInfo",userInfo);
                toresultssactivity(userInformation);
            }
        });

        button2 = (TextView) findViewById(R.id.saveBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(nameInput.getText().toString())) {
                    nameInput.setError("Name field cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(emailInput.getText().toString())) {
                    emailInput.setError("Email field cannot be empty");
                    return;
                }
                if(!TextUtils.isEmpty(nameInput.getText().toString()) && !TextUtils.isEmpty(emailInput.getText().toString())){
                    String gender = spinner.getSelectedItem().toString();
                    ProgressDialog dialog = ProgressDialog.show(usersettings.this, "",
                            "Loading. Please wait...", true);

                    Services.editUser(userId, userName, userEmail, null, dateButton.getText().toString(), gender, userPP,
                            usersettings.this, new Services.UserCallback() {
                                @Override
                                public void onSuccess(ArrayList<User> result) {
                                    Log.d("Response password", String.valueOf(result.get(0).getPassword()));
                                    dialog.cancel();
                                    if(!result.isEmpty()){
                                        Log.d("result check", "Success!");

                                        userInfo = result;
                                        Bundle userInformation = new Bundle();
                                        userInformation.putParcelableArrayList("userInfo",userInfo);
                                        toresultssactivity(userInformation);

                                        Context context = getApplicationContext();
                                        CharSequence text = "Edit Saved!";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();

                                        Log.d("EditUser","Successful");
                                    }else{
                                        Log.d("Else Response", "Multiple User Object Detected");
                                    }

                                }

                                @Override
                                public void onFailure(String error) {
                                    dialog.cancel();
                                    Log.d("FailRes UserEdit", error);
                                }
                            });
                }

            }
        });
        /* DPbtn = (ImageView) findViewById(R.id.changeDPbtn);
        // relativeLayout = (RelativeLayout) findViewById(R.id.usersettings);
        // DPbtn.setClickable(true);
        // DPbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.activity_change_dp, null);

                popupWindow = new PopupWindow(container, 1000, 1000, true);
                popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY,15,300);

                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch (View view, MotionEvent motionEvent) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });
        }

        /*connect = (TextView) findViewById(R.id.friendsBtn);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toconnectactivity();
            }
        });*/

    }

    //To set date default to user's previously saved DOB
    private String getUserDOB(String userDOB) {
        int year = Integer.valueOf(userDOB.substring(0,4));
        int month = Integer.valueOf(userDOB.substring(5, 7));
        int day = Integer.valueOf(userDOB.substring(8));
        return makeDateString(day, month, year);
    }

    //If user's saved DOB is null
    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }


    public void settingslogoutactivity(){
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(Task<Void> task) {
                    finish();
                    mainactivity();
                }
            });
        } else {
            mainactivity();
        }

    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);

    }

    private String makeDateString (int day, int month, int year) {
        //return getMonthFormat(month) + " " + day + " " + year;
        return year + "/" + month + "/" + day;
    }

    private String getMonthFormat (int month) {
        if (month == 1)
            return "Jan";
        if (month == 2)
            return "Feb";
        if (month == 3)
            return "Mar";
        if (month == 4)
            return "Apr";
        if (month == 5)
            return "May";
        if (month == 6)
            return "Jun";
        if (month == 7)
            return "Jul";
        if (month == 8)
            return "Aug";
        if (month == 9)
            return "Sep";
        if (month == 10)
            return "Oct";
        if (month == 11)
            return "Nov";
        if (month == 12)
            return "Dec";

        return "Jan";
    }

    public void openDatePicker (View view) {
        datePickerDialog.show();
    }


    public void mainactivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void toresultssactivity(Bundle bundle) {
        Intent intent = new Intent(this, profileFragment.class);
        intent.putExtras(bundle);
        startActivity(intent);
        this.finish();
    }


    public void changeactivity() {
        Intent intent = new Intent(this, changeDP.class);
        startActivity(intent);
    }

    public void toconnectactivity() {
        Intent intent = new Intent(this, connectFragment.class);
        startActivity(intent);
    }
    
    public void getUserInfo(){
        if (getIntent().getExtras() != null) {
            Log.d("Bundle log", "Bundle not empty");

            userInformation = getIntent().getExtras();
            userInfo = userInformation.getParcelableArrayList("userInfo");
//            for(int i=0;i<userInfo.size();i++){
//                Log.d("TestsPage",userInfo.get(i).getName());
//            }
//            userId = userInformation.getInt("userId");
//            userName = userInformation.getString("name");
//            userEmail = userInformation.getString("email");
//            userGender = userInformation.getString("gender");
//            userDOB = userInformation.getString("dob");
        } else {
            Log.d("Error", "Bundle empty");

            SharedPreferences prefs = getSharedPreferences(USER_INFORMATION, MODE_PRIVATE);
            userId = prefs.getInt("userId", 0);
            userName = prefs.getString("name", "default");
            userEmail = prefs.getString("email", "default");
            userGender = prefs.getString("gender", "default");
            userDOB = prefs.getString("DOB", "default");

            Log.d("User name", "User Name, " + userName);

            userInformation.putInt("userId", userId);
            userInformation.putString("name", userName);
            userInformation.putString("email", userEmail);
            userInformation.putString("gender", userGender);
            userInformation.putString("dob", userDOB);

        }
    }
}