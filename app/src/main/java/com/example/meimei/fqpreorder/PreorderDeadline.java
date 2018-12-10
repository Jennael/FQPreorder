package com.example.meimei.fqpreorder;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PreorderDeadline {

    private static String mn30 = "00:30:00";
    private static String am100 = "01:00:00";
    private static String am130 = "01:30:00";
    private static String am200 = "02:00:00";
    private static String am230 = "02:30:00";
    private static String am300 = "03:00:00";
    private static String am330 = "03:30:00";
    private static String am400 = "04:00:00";
    private static String am430 = "04:30:00";
    private static String am500 = "05:00:00";
    private static String am530 = "05:30:00";
    private static String am600 = "06:00:00";
    private static String am630 = "06:30:00";
    private static String am700 = "07:00:00";
    private static String am730 = "07:30:00";
    private static String am800 = "08:00:00";
    private static String am830 = "08:30:00";
    private static String am900 = "09:00:00";
    private static String am930 = "09:30:00";
    private static String am1000 = "10:00:00";
    private static String am1030 = "10:30:00";
    private static String am1100 = "11:00:00";
    private static String am1130 = "11:30:00";
    private static String nn = "12:00:00";
    private static String nn30 = "12:30:00";
    private static String pm100 = "13:00:00";
    private static String pm130 = "13:30:00";
    private static String pm200 = "14:00:00";
    private static String pm230 = "14:30:00";
    private static String pm300 = "15:00:00";
    private static String pm330 = "15:30:00";
    private static String pm400 = "16:00:00";
    private static String pm430 = "16:30:00";
    private static String pm500 = "17:00:00";
    private static String pm530 = "17:30:00";
    private static String pm600 = "18:00:00";
    private static String pm630 = "18:30:00";
    private static String pm700 = "19:00:00";
    private static String pm730 = "19:30:00";
    private static String pm800 = "20:00:00";
    private static String pm830 = "20:30:00";
    private static String pm900 = "21:00:00";
    private static String pm930 = "21:30:00";
    private static String pm1000 = "22:00:00";
    private static String pm1030 = "22:30:00";
    private static String pm1100 = "23:00:00";
    private static String pm1130 = "23:30:00";
    private static String mn = "23:59:59";

    private static String[] stringArray = {mn30, am100, am130, am200, am230, am300, am330, am400, am430, am500, am530, am600, am630, am700, am730,
            am800, am830, am900, am930, am1000, am1030, am1100, am1130, nn, nn30, pm100, pm130, pm200, pm230, pm300, pm330, pm400, pm430,
            pm500, pm530, pm600, pm630, pm700, pm730, pm800, pm830, pm900, pm930, pm1000, pm1030, pm1100, pm1130, mn};

    //convert time to Date with today's date
    private static String fullToday (String s){
        Date today = new Date();

        //get "yyyy:MM:dd:hh:mm:ss"
        String fullToday = dateToString(today)[0]+":"+s;

        return fullToday;
    }

    //convert time to Date with tomorrow's date
    private static String fullTomorrow (String s){

        //get tomorrow's Date
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, 1);
        Date tomorrow = cal.getTime();

        //get "yyyy:MM:dd:hh:mm:ss"
        String fullTomorrow = dateToString(tomorrow)[0]+":"+s;

        return fullTomorrow;
    }

    //convert from yyyy:MM:dd:hh:mm:ss to date format
    private static Date stringToDate (String s){ //s is in format "yyyy:MM:dd:hh:mm:ss"

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:hh:mm:ss");

        try{
            Date out = sdf.parse(s);
            return out;
        } catch (Exception e) {
            System.out.println("exception!");
            return new Date();
        }
    }

    //convert time string array to dates
    private static Date[] DateArray (String[] stringArray){

        int i;
        Date[] dateArray = new Date[48];

        for (i=0; i<=47; i++){
            dateArray[i] = stringToDate(fullToday(stringArray[i]));
        }
        return dateArray;
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

    //retrieve deadline
    public static Date getPreorderDeadline () {

        int i;
        Date date = new Date();

        Date[] dateArray = DateArray (stringArray);
        for (i = 0; i<=47; i++){
            if ( date.before(dateArray[i]) ) {
                Date deadline = dateArray[i];
                return deadline;
            }
        }
        // if time is 23:59:59:x
        Date deadline = stringToDate(fullTomorrow("00:30:00"));

        return deadline;

    }

    /*
    public static void main(String[] args) {

        Date deadline = getPreorderDeadline();
        System.out.println(dateToString(deadline)[1]);

    }
    */

}