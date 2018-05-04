package com.example.ss12dark.paintgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    public void search(View v){
        Intent i = new Intent(this,Search.class);
        startActivity(i);

    }

    public void add(View v){
        Intent i = new Intent(this,AddEvent.class);
        startActivity(i);

    }
}
