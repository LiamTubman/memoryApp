package com.thinknz.memoryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by liamtubs on 22/07/2016.
 */
public class MeetingsListAdapter extends ArrayAdapter<Meeting>{
        private Activity activity;
        Meeting meeting;
        DBHandler dbHandler;

        public MeetingsListAdapter(Context context, Meeting[] meetings, Activity activity) {
            super(context, R.layout.meeting_row, meetings);

            this.activity = activity;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater myInflater = LayoutInflater.from(getContext());
            View customView = myInflater.inflate(R.layout.meeting_row, parent, false);

            meeting = getItem(position);
            dbHandler = new DBHandler(activity, null, null, 1);

            TextView titleText = (TextView) customView.findViewById(R.id.titleText);
            TextView locationText = (TextView) customView.findViewById(R.id.locationText);
            TextView dateText = (TextView) customView.findViewById(R.id.dateText);
            TextView timeText = (TextView) customView.findViewById(R.id.timeText);
            ImageView meetingIcon = (ImageView) customView.findViewById(R.id.meetingIcon);

            titleText.setText(meeting.name);
            locationText.setText(meeting.location);
            dateText.setText(meeting.date);
            timeText.setText(meeting.time);

            return customView;
        }

}
