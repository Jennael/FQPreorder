package com.example.meimei.fqpreorder;

import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.TextView;

import java.util.Date;

public class PreorderMain extends AppCompatActivity {

    private String user_name;
    private String preorderCount;
    private String preorderDeadlineTime;
    private Date preorderDeadline;

    Button preorderGo;
    Button preorderEndButton1;
    Button preorderEndButton2;
    Spinner preorderDeadlineMenu;
    Spinner preorderCountMenu;

    RelativeLayout pre_main;
    LinearLayout pre_prog;
    LinearLayout pre_time;
    LinearLayout pre_count;

    TextView text_preordercount;
    TextView text_timeleft;

    DeadlineCheck currentDeadline;
    CountCheck currentCount;

    Date nextDeadline;
    String nextDeadlineText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preorder_main);

        //layouts
        pre_main = findViewById(R.id.preorder_main);
        pre_prog = findViewById(R.id.preorder_inprogress);
        pre_time = findViewById(R.id.preorder_timereached);
        pre_count = findViewById(R.id.preorder_countreached);

        //init visibility
        pre_main.setVisibility(View.VISIBLE);
        pre_prog.setVisibility(View.INVISIBLE);
        pre_time.setVisibility(View.INVISIBLE);
        pre_count.setVisibility(View.INVISIBLE);

        preorderGo = findViewById(R.id.preorder_button);
        preorderEndButton1 = findViewById(R.id.preorder_closeButton_timereached);
        preorderEndButton2 = findViewById(R.id.preorder_closeButton_countreached);

        text_preordercount = (TextView) findViewById(R.id.preorder_ordercount);
        text_timeleft = (TextView) findViewById(R.id.preorder_timeleft);

        nextDeadline = PreorderDeadline.getPreorderDeadline();
        nextDeadlineText = ConvertTime.dateToString(nextDeadline)[1];

        Log.i("Preorder", "Main page init"); /* For Debugging purpose! */

        //spinner1
        preorderCountMenu = findViewById(R.id.preorder_count_menu);
        ArrayAdapter<CharSequence> adapter_preorderCount =
                ArrayAdapter.createFromResource(this, R.array.numOfOrders, android.R.layout.simple_spinner_dropdown_item);
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

        //spinner2
        preorderDeadlineMenu = findViewById(R.id.preorder_deadline_menu);
        String[] deadlineOfOrders = {nextDeadlineText};
        ArrayAdapter<String> adapter_preorderDeadline = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, deadlineOfOrders);
        preorderDeadlineMenu.setAdapter(adapter_preorderDeadline);

        preorderDeadlineMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                preorderDeadlineTime = String.valueOf(parent.getItemAtPosition(position));
                preorderDeadline = nextDeadline;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                preorderDeadlineTime = nextDeadlineText;
                preorderDeadline = nextDeadline;
            }
        });

        preorderGo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //interact with sql server, 2 important values are strings preorderDeadline, preorderCount
                //TODO send preorderDeadline to database: Date preorderDeadline or String preorderDeadlineTime

                pre_main.setVisibility(View.INVISIBLE);
                pre_prog.setVisibility(View.VISIBLE);
                pre_time.setVisibility(View.INVISIBLE);
                pre_count.setVisibility(View.INVISIBLE);

                Log.i("Preorder", "In Progress Init");

                currentDeadline = new DeadlineCheck(nextDeadline);
                currentCount = new CountCheck(preorderCount);

                new PreorderAsyncTask().execute(currentDeadline.stopPreorder(), currentCount.stopPreorder());

            }
        });

        preorderEndButton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Preorder", "Main page");
                pre_main.setVisibility(View.VISIBLE);
                pre_prog.setVisibility(View.INVISIBLE);
                pre_time.setVisibility(View.INVISIBLE);
                pre_count.setVisibility(View.INVISIBLE);

            }
        });

        preorderEndButton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Preorder", "Main page");
                pre_main.setVisibility(View.VISIBLE);
                pre_prog.setVisibility(View.INVISIBLE);
                pre_time.setVisibility(View.INVISIBLE);
                pre_count.setVisibility(View.INVISIBLE);

            }
        });

        // create toolbar!
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

    public class PreorderAsyncTask extends AsyncTask<Boolean, Void, Void> {

        @Override
        protected Void doInBackground(Boolean... params) {

            while (!params[0] && !params[1]){
                Log.i("Preorder", "In Progress");

                if(isCancelled()){
                    break;
                }
            }

            if (currentCount.stopPreorder()){
                Log.i("Preorder", "Limit reached");
                pre_main.setVisibility(View.INVISIBLE);
                pre_prog.setVisibility(View.INVISIBLE);
                pre_time.setVisibility(View.INVISIBLE);
                pre_count.setVisibility(View.VISIBLE);

            } else if (currentDeadline.stopPreorder()){ //assume currentDeadline.stopPreorder() == true
                Log.i("Preorder", "Time's up");
                pre_main.setVisibility(View.INVISIBLE);
                pre_prog.setVisibility(View.INVISIBLE);
                pre_time.setVisibility(View.VISIBLE);
                pre_count.setVisibility(View.INVISIBLE);
            } else if (isCancelled()){
                Log.i("Preorder", "Cancelled");
            }
            Log.i("Preorder", "Ended for no reason");
            return null;
        }

        protected Void onProgressUpdate(){
            text_preordercount.setText(currentCount.getCurrent());
            text_timeleft.setText(currentDeadline.timeLeft());
            return null;
        }
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
