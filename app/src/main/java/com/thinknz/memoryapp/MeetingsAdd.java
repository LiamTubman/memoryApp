package com.thinknz.memoryapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by liamtubs on 22/07/2016.
 */
public class MeetingsAdd extends AppCompatActivity {
    DBHandler dbHandler;
    Meeting meeting;
    String meetingId;

    TextView nameText;
    TextView dateText;
    TextView timeText;
    TextView locationText;
    TextView notesText;

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };



    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateText.setText(sdf.format(myCalendar.getTime()));
    }

    public void save(View view){
        meeting = new Meeting(meetingId, "" + dateText.getText(), "" + nameText.getText(), "" + timeText.getText(),
                "" + locationText.getText(), "" + notesText.getText());

        dbHandler.insertMeeting(meeting);

        finish();
    }

    public void delete(View view){
        dbHandler.deleteMeeting(meetingId);
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings_add);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        dbHandler = new DBHandler(this, null, null, 1);

        Intent i = getIntent();
        meetingId = i.getExtras().getString("meetingId");

        Meeting meeting = dbHandler.selectMeeting(meetingId);

        nameText = (TextView)findViewById(R.id.nameText);
        dateText = (TextView)findViewById(R.id.dateText);
        timeText = (TextView)findViewById(R.id.timeText);
        locationText = (TextView)findViewById(R.id.locationText);
        notesText = (TextView)findViewById(R.id.notesText);

        if(meeting != null){
            nameText.setText(meeting.name);
            dateText.setText(meeting.date);
            timeText.setText(meeting.time);
            locationText.setText(meeting.location);
            notesText.setText(meeting.notes);
        }

        dateText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MeetingsAdd.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
}
