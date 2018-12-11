package com.example.meimei.fqpreorder;

import android.util.Log;

import java.util.Date;
import static com.example.meimei.fqpreorder.ConvertTime.*;

public class DeadlineCheck {

    private final Date PREORDER_DEADLINE;

    public DeadlineCheck(Date preorder_deadline) {
        PREORDER_DEADLINE = preorder_deadline;
    }
    public Date current;

    public Boolean stopPreorder() {

        current = new Date();
        if (current.before(PREORDER_DEADLINE)){
            return false;
        }
        return true;
    }

    public Date current(){
        current = new Date();
        return current;
    }

    public Date deadline(){
        return PREORDER_DEADLINE;
    }

    public String timeLeft(){

        current = new Date();
        long diff = PREORDER_DEADLINE.getTime() - current.getTime();
        long seconds = (diff / 1000);
        int seconds_left = (int) seconds % 60 ;
        int minutes_left = (int) (seconds - seconds_left) / (60);

        String min = Integer.toString(minutes_left);
        String sec = Integer.toString(seconds_left);

        return min + ":" + sec;

    }

}
