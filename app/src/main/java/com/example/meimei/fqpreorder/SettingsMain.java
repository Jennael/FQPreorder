package com.example.meimei.fqpreorder;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SettingsMain extends AppCompatActivity {
    private String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Log.i("Current Activity", "SettingsMain"); /* For Debugging purpose! */

        user_name = getIntent().getStringExtra("name");
        Toast.makeText(this, "Welcome " + user_name, Toast.LENGTH_SHORT).show();

        // create toolbar!
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

    public void gotoQueue(View view) {
        Intent queue = new Intent(this, QueueMain.class);
        queue.putExtra("name", user_name);
        startActivity(queue);
        finish();
    }

    public void gotoPreorder(View view) {
        Intent preorder = new Intent(this,PreorderMain.class);
        preorder.putExtra("name", user_name);
        startActivity(preorder);
        finish();
    }

    public void gotoSettings(View view) {
//        do nothing
    }
}