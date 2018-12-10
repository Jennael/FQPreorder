package com.example.meimei.fqpreorder;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;


import java.util.Queue;

public class PreorderMain extends AppCompatActivity {
    private String user_name;
    private String preorderCount;
    private String preorderDuration;
    Button preorderGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final LinearLayout pre_main = findViewById(R.id.preorder_main);
        final LinearLayout pre_prog = findViewById(R.id.preorder_inprogress);
        final LinearLayout pre_time = findViewById(R.id.preorder_timereached);
        final LinearLayout pre_count = findViewById(R.id.preorder_countreached);

        setContentView(R.layout.activity_preorder);

        pre_main.setVisibility(View.VISIBLE);
        pre_prog.setVisibility(View.GONE);
        pre_time.setVisibility(View.GONE);
        pre_count.setVisibility(View.GONE);

        Log.i("Current Activity", "PreorderMain"); /* For Debugging purpose! */

        //user_name = getIntent().getStringExtra("name");
        //Toast.makeText(this, "Welcome " + user_name, Toast.LENGTH_SHORT).show();

        //get the spinner from the xml.
        Spinner preorderCountMenu = findViewById(R.id.preorder_count_menu);

        //create a list of items for the spinner.
        //String[] numOfOrders = new String[]{"5", "10", "15", "20"};
        // now replaced with string-array in strings.xml

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        //ArrayAdapter<String> adapter_preorderCount = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, numOfOrders);

        ArrayAdapter<CharSequence> adapter_preorderCount =
                ArrayAdapter.createFromResource(this, R.array.numOfOrders, android.R.layout.simple_spinner_dropdown_item);

        //set the spinners adapter to the previously created one.
        preorderCountMenu.setAdapter(adapter_preorderCount);
        preorderCountMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                preorderCount = String.valueOf(parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                preorderCount = "10";
            }
        });

        //get the spinner from the xml.
        Spinner preorderDurationMenu = findViewById(R.id.preorder_duration_menu);

        //create a list of items for the spinner.
        //String[] durationOfOrders = new String[]{"10", "20", "30"};
        // now replaced with string-array in strings.xml

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        //ArrayAdapter<String> adapter_preorderDuration = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, durationOfOrders);

        ArrayAdapter<CharSequence> adapter_preorderDuration =
                ArrayAdapter.createFromResource(this, R.array.durationOfOrders, android.R.layout.simple_spinner_dropdown_item);

        //set the spinners adapter to the previously created one.
        preorderDurationMenu.setAdapter(adapter_preorderDuration);
        preorderDurationMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                preorderDuration = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                preorderDuration = "30";
            }
        });

        preorderGo = findViewById(R.id.preorder_button);
        preorderGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //interact with sql server, 2 important values are strings preorderDuration, preorderCount
                //change activity visibility level

                //use while loops?
                pre_main.setVisibility(View.GONE);
                pre_prog.setVisibility(View.VISIBLE);
                pre_time.setVisibility(View.GONE);
                pre_count.setVisibility(View.GONE);

            }
        });

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
//        do nothing
    }

    public void gotoSettings(View view) {
        Intent settings = new Intent(this,SettingsMain.class);
        settings.putExtra("name", user_name);
        startActivity(settings);
        finish();
    }
}
