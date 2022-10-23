package com.example.personalitytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Calendar;

public class usersettings extends AppCompatActivity {

    private Button button;
    private TextView button2, connect;
    private ImageView DPbtn;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;



    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersettings);

        initDatePicker();
        dateButton=findViewById(R.id.settingsdatepicker);
        dateButton.setText(getTodaysDate());

        button = (Button) findViewById(R.id.logoutBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingslogoutactivity();
            }
        });

        button2 = (TextView) findViewById(R.id.resultsBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toresultssactivity();
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

        /*onnect = (TextView) findViewById(R.id.friendsBtn);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toconnectactivity();
            }
        });*/

    }

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
        return getMonthFormat(month) + " " + day + " " + year;
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
    public void toresultssactivity() {
        Intent intent = new Intent(this, profileFragment.class);
        startActivity(intent);
    }


    public void changeactivity() {
        Intent intent = new Intent(this, changeDP.class);
        startActivity(intent);
    }

    public void toconnectactivity() {
        Intent intent = new Intent(this, connectFragment.class);
        startActivity(intent);
    }
}