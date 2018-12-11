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
import java.util.ArrayList;
import java.util.HashMap;

public class submitData {

    private static String WEBSERVICE ="store/preorder.php";
    private static String SERVER = "http://fastqueue.000webhostapp.com/";
    private int GET_DATA = 0;
    private String urlWebService;
    //private String loginID;
    //private TextView loginError;
    ArrayList objects;

    public static void main(String[] args) throws IOException {

        String server = SERVER + WEBSERVICE;
        String json = submitData(server);
        System.out.println(json);
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