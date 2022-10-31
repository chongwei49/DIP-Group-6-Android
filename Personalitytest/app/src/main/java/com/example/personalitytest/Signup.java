package com.example.personalitytest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personalitytest.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.widget.Spinner;

public class Signup extends AppCompatActivity {

    private ImageView backimage;
    private Button button;
    private TextView nameText, emailText, passText;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Spinner spinner;
    private ArrayList<User> userInfo = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initDatePicker();
        dateButton=findViewById(R.id.signupdatepicker);
        dateButton.setText(getTodaysDate());

        spinner=findViewById(R.id.signupgenderpicker);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        String gender = spinner.getSelectedItem().toString();

        emailText = findViewById(R.id.emailInput);
        passText = findViewById(R.id.passwordInput);
        nameText = findViewById(R.id.nameInput);

        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.

        backimage = (ImageView) findViewById(R.id.signUpBck);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainactivity();
            }
        });

        button = (Button) findViewById(R.id.signupBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = ProgressDialog.show(Signup.this, "",
                        "Loading. Please wait...", true);
                Services.signUp(nameText.getText().toString(), emailText.getText().toString(), passText.getText().toString(), dateButton.getText().toString(), gender,
                        Signup.this, new Services.UserCallback() {
                    @Override
                    public void onSuccess(ArrayList<User> result) {
                        Log.d("Response result", String.valueOf(result.get(0).getName()));
                        dialog.cancel();
                        if(!result.isEmpty()){
                            userInfo = result;

                            Bundle userInformation = new Bundle();
                            userInformation.putParcelableArrayList("userInfo",userInfo);
                            homeactivity(userInformation);
                            Log.d("userId Check", result.get(0).getUserId().toString());
                        }else{
                            Log.d("Else Response", "Multiple User Object Detected");
                        }
                    }
                });
                //homeactivity();
            }
        });
    }

    public void mainactivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);

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

    public void homeactivity(Bundle bundle) {
        Intent intent = new Intent(this, Home.class);
        intent.putExtras(bundle);
        startActivity(intent);
        this.finish();
    }
}