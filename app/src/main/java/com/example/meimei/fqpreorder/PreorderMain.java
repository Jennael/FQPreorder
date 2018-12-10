package com.example.meimei.fqpreorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;

import java.util.Date;

public class PreorderMain extends AppCompatActivity {
    private String user_name;
    private String preorderCount;
    private String preorderDeadline;
    private Date preorderDeadlineTime;
    Button preorderGo;

    RelativeLayout pre_main;
    LinearLayout pre_prog;
    LinearLayout pre_time;
    LinearLayout pre_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preorder_main);

        //layouts
        pre_main = findViewById(R.id.preorder_main); //activity_preorder_main.xml
        pre_prog = findViewById(R.id.preorder_inprogress);
        pre_time = findViewById(R.id.preorder_timereached);
        pre_count = findViewById(R.id.preorder_countreached);
        //visibility
        pre_main.setVisibility(View.VISIBLE);
        pre_prog.setVisibility(View.INVISIBLE);
        pre_time.setVisibility(View.INVISIBLE);
        pre_count.setVisibility(View.INVISIBLE);

        Log.i("Current Activity", "PreorderMain"); /* For Debugging purpose! */

        //user_name = getIntent().getStringExtra("name");
        //Toast.makeText(this, "Welcome " + user_name, Toast.LENGTH_SHORT).show();

        //get the spinner from the xml.
        Spinner preorderCountMenu = findViewById(R.id.preorder_count_menu);

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
        Spinner preorderDeadlineMenu = findViewById(R.id.preorder_deadline_menu);

        final Date nextDeadline = PreorderDeadline.getPreorderDeadline();
        final String nextDeadlineText = PreorderDeadline.dateToString(nextDeadline)[1];

        String[] deadlineOfOrders = {nextDeadlineText};

        ArrayAdapter<String> adapter_preorderDeadline = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, deadlineOfOrders);
        //ArrayAdapter<CharSequence> adapter_preorderDeadline =
        //        ArrayAdapter.createFromResource(this, R.array.deadlineOfOrders, android.R.layout.simple_spinner_dropdown_item);

        //set the spinners adapter to the previously created one.
        preorderDeadlineMenu.setAdapter(adapter_preorderDeadline);
        preorderDeadlineMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                preorderDeadline = String.valueOf(parent.getItemAtPosition(position));
                preorderDeadlineTime = nextDeadline;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                preorderDeadline = nextDeadlineText;
                preorderDeadlineTime = nextDeadline;
            }
        });

        preorderGo = findViewById(R.id.preorder_button);
        preorderGo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //interact with sql server, 2 important values are strings preorderDeadline, preorderCount
                //change activity visibility level

                pre_main.setVisibility(View.INVISIBLE);
                pre_prog.setVisibility(View.VISIBLE);
                pre_time.setVisibility(View.INVISIBLE);
                pre_count.setVisibility(View.INVISIBLE);

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
