package com.example.android.dinnerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowDailySpecialActivity extends Activity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_daily_special);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Set the heading of the info box
        TextView heading_tv = (TextView) findViewById(R.id.textView_info_heading);
        heading_tv.setText("Your daily special:");

        // Get the text view
        tv = (TextView) findViewById(R.id.textView_info);

        tv.setText("Pudding");
    }
}
