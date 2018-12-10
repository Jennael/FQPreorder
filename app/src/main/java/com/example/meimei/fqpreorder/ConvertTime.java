package com.example.meimei.fqpreorder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertTime {

    //convert from yyyy:MM:dd:hh:mm:ss to date format
    public static Date stringToDate (String s){ //s is in format "yyyy:MM:dd:hh:mm:ss"

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:hh:mm:ss");

        try{
            Date out = sdf.parse(s);
            return out;
        } catch (Exception e) {
            System.out.println("exception!");
            return new Date();
        }
    }

    //extract date information
    public static String[] dateToString (Date date){

        String[] out = new String[5];

        //date string
        String strDateFormat = "yyyy:MM:dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

        //time string
        String strTimeFormat = "HH:mm:ss";
        DateFormat timeFormat = new SimpleDateFormat(strTimeFormat);

        //hour string
        String strHourFormat = "HH";
        DateFormat hourFormat = new SimpleDateFormat(strHourFormat);

        //hour string
        String strMinFormat = "mm";
        DateFormat minFormat = new SimpleDateFormat(strMinFormat);

        //sec string
        String strSecFormat = "ss";
        DateFormat secFormat = new SimpleDateFormat(strSecFormat);

        String formattedDate= dateFormat.format(date);
        out[0] = formattedDate;

        String formattedTime= timeFormat.format(date);
        out[1] = formattedTime;

        String formattedHour= hourFormat.format(date);
        out[2] = formattedHour;

        String formattedMin= minFormat.format(date);
        out[3] = formattedMin;

        String formattedSec= secFormat.format(date);
        out[4] = formattedSec;

        return out;
    }








}
