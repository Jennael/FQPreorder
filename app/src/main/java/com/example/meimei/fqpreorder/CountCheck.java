package com.example.meimei.fqpreorder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class CountCheck {

    private final int PREORDER_LIMIT;
    private int database_count;

    public CountCheck(String count){
        PREORDER_LIMIT = Integer.parseInt(count);
    }

    private void retrieval() throws IOException, JSONException {
        JSONArray json = (JSONArray) new JSONArray(submitData.dataOrders("chinese"));

        database_count = json.length();
    }

    public Boolean stopPreorder() throws IOException, JSONException {
        retrieval();
        if (database_count >= PREORDER_LIMIT){
            return true;
        }
        return false;
    }

    public int getCurrent() throws IOException, JSONException {
        retrieval();
        return database_count;
    }

}
