package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        calendar = new ArrayList<>();

    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }
    public class CustomComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting t1, Meeting t2) {
            return (t1.getStartTime().compareTo(t2.getEndTime()));
        }
    }
    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int count = 0;
        calendar.sort(new CustomComparator());


        for (int i = 1; i < calendar.size(); i++)
        {
            LocalTime prevEnd = calendar.get(i-1).getEndTime();

            LocalTime currStart = calendar.get(i).getStartTime();
            if(prevEnd.compareTo(currStart) > 0){
                count++;

            }
            //System.out.println(calendar.get(i).getStartTime()+" "+calendar.get(i).getEndTime());
        }

        return count;
    }
}
