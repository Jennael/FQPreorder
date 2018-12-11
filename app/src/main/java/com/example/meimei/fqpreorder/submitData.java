package com.example.meimei.fqpreorder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class submitData {

    private static String WEBSERVICE ="store/preorderrequirements.php/insert.php?";
    private static String SERVER = "http://fastqueue.000webhostapp.com/";
    private static String nPreorders = "num_of_preorder=";
    private static String dPreorders = "&preorder_ready_time=";

    /*
    public static void main(String[] args) throws IOException {
        String count = "5";
        String timing = "2018-12-10 09:30:00.000000";
        String server = SERVER + WEBSERVICE + "num_of_preorder=6&preorder_ready_time=2018-12-10%2009:30:00.000000";
        String encodedServer = URLEncoder.encode(server, "UTF-8");
        String json = submitData(server);
        System.out.println(json);
    }
    */

    public static String submit (String n, String yyyymmdd, String hhmmss ) throws IOException {

        String YMD = yyyymmdd.replace(":", "-");

        String server = SERVER + WEBSERVICE + nPreorders + n + dPreorders + YMD + "%20" + hhmmss + ".000000";
        String encodedServer = URLEncoder.encode(server, "UTF-8");
        String json = submitData(server);
        return json;
    }

    public static String submitData (String server) throws IOException {
        URL url = null;
        URLConnection con = null;
        try {
            url = new URL(server);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            con = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String json;
        while ((json = bufferedReader.readLine()) != null){
            sb.append(json + "\n");
        }
        return json;
    }

}