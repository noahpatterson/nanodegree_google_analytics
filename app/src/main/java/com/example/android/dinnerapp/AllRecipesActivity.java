package com.example.android.dinnerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AllRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_receipes);

        String [] allDinners = getIntent().getStringArrayExtra("allDinners");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.all_recipes_list_item, allDinners);

        ListView listView = (ListView) findViewById(R.id.all_recipes_listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dinner =(String) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), OrderDinnerActivity.class);
                intent.putExtra("selectedDinner", dinner);
                startActivity(intent);
            }
        });

        listView.setAdapter(arrayAdapter);

    }
}
