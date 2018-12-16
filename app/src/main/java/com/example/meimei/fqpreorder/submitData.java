package com.example.meimei.fqpreorder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class submitData {

    //Website link to the 'php' relevant page
    //private static String WEBSERVICE ="";
    private static String DATASERVER = "http://fastqueue.000webhostapp.com/store/preorderrequirements.php/insert.php?";
    private static String STORESERVER = "http://fastqueue.000webhostapp.com/store/chinese/collectedfood.php";

    //After reading through the php, pick out the tags for data to send
    private static String nPreorders = "num_of_preorder="; //'num_of_preorder'
    private static String dPreorders = "&preorder_ready_time="; //'preorder_ready_time'

    /*
    public static void main(String[] args) throws IOException {
        String server = STORESERVER;
        String encodedServer = URLEncoder.encode(server, "UTF-8");
        String json = returnData(server);
        System.out.println(json);
    }
    */

    public static String submitPreorder (String n, String yyyymmdd, String hhmmss ) throws IOException {

        //change the yearmonthday formatting outputted from PreorderDealine.java (only relevant to MeiMei)
        String YMD = yyyymmdd.replace(":", "-");

        //put the URL together, with 'space' as '%20'
        //it should look something like this: http://fastqueue.000webhostapp.com/store/chinese/collectedfood.php
        String server = DATASERVER + nPreorders + n + dPreorders + YMD + "%20" + hhmmss + ".000000";

        //encode the url using utf-8, not sure if this is necessary
        String encodedServer = URLEncoder.encode(server, "UTF-8");

        //submit it and get the response!
        String json = returnData(server);
        return json;
    }

    public static String dataOrders (String store) throws IOException {

        //put the URL together, with 'space' as '%20'
        //it should look something like this: http://fastqueue.000webhostapp.com/store/preorderrequirements.php/insert.php?num_of_preorder=9&preorder_ready_time=2018-02-10%2009:30:00.000000
        String server = STORESERVER.replace("chinese", store);

        //encode the url using utf-8, not sure if this is necessary
        String encodedServer = URLEncoder.encode(server, "UTF-8");

        //submit it and get the response!
        String json = returnData(server);
        return json;
    }

    public static String returnData (String server) throws IOException {

        URL url = null;
        HttpURLConnection con = null;

        try {
            url = new URL(server); //create a new URL type object
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            con = (HttpURLConnection) url.openConnection(); //create a new URLConnection type object

        } catch (IOException e) {
            e.printStackTrace();
        }

        //get JSON output from the connection
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String json;

        while ((json = bufferedReader.readLine()) != null){
            sb.append(json + "\n");
        }

        bufferedReader.close();
        con.disconnect();

        return sb.toString();
    }
}