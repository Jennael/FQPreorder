package com.example.meimei.fqpreorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Map;

public class LoginLoading extends AppCompatActivity {
    private Map<String, String> user_map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_loading);

        Log.i("Current Activity", "LoginLoading"); /* For Debugging purpose! */

        String userID = getIntent().getStringExtra("id");

        Intent returnPage = new Intent(); // return to LoginPage Activity

        returnPage.putExtra("name", "Admin");
        returnPage.putExtra("result", "pass");
        setResult(RESULT_OK, returnPage);
        finish();




        /*
        // read a file, and put its contents into a HashMap
        Scanner dataBaseUsers = new Scanner(
                getResources().openRawResource(R.raw.sql)
        );
        user_map = new HashMap<>();
        // split the text by "\t" and even number indexes into Keys and odd number indexes into Values
        while (dataBaseUsers.hasNextLine()){
            String line = dataBaseUsers.nextLine();
            String[] parts = line.split("\t");
            user_map.put(parts[0],parts[1]);
        }
        dataBaseUsers.close();

        String userID = getIntent().getStringExtra("id");

        Intent returnPage = new Intent(); // return to LoginPage Activity
//        extract "Extra" data from the intent that loaded this class
        for (String i: user_map.keySet()){
            if (i.equals(userID)) {

                returnPage.putExtra("name", user_map.get(i));
                returnPage.putExtra("result","pass");
                setResult(RESULT_OK, returnPage);
                finish();
            }
        }
        */
        /* if the login ID was not found in database! */

        /*
        returnPage.putExtra("result", "fail");
        setResult(RESULT_OK, returnPage);
        finish();

        */

    }
}