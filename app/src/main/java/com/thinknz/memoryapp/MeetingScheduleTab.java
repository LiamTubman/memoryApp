package com.thinknz.memoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by liamtubs on 26/07/2016.
 */
public class MeetingScheduleTab extends Fragment{
    public String date;
    DBHandler dbHandler;
    View view;

    public void updateList(){
        Log.d("LOG", "update");
        List<Meeting> meetingList = dbHandler.selectMeetingsByDate(date);
        Meeting[] meetings = meetingList.toArray(new Meeting[meetingList.size()]);

        ListAdapter myAdapter = new MeetingsListAdapter(view.getContext(), meetings, getActivity());
        ListView myList = (ListView)view.findViewById(R.id.meetingsList);
        //myList.setAdapter(null);
        myList.setAdapter(myAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Meeting meeting = (Meeting)adapter.getItemAtPosition(position);

                Intent i = new Intent(getActivity(), MeetingsAdd.class);
                i.putExtra("meetingId", meeting.id);
                startActivity(i);
            }
        });
    }

    @Override
    public void onResume() {
        updateList();

        super.onResume();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View tabView = inflater.inflate(R.layout.activity_meetings_schedule, container, false);

        dbHandler = new DBHandler(tabView.getContext(), null, null, 1);
        view = tabView;

        updateList();

        return tabView;
    }
}
