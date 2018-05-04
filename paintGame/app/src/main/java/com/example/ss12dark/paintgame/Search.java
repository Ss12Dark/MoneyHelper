package com.example.ss12dark.paintgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class Search extends AppCompatActivity {
MyDBHandler db;
public List<Event> events;

public TextView tvname,tvdes,tvprice,tvlocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        db = new MyDBHandler(this);
        tvname = (TextView) findViewById(R.id.name);
        tvdes = (TextView) findViewById(R.id.des);
        tvprice = (TextView) findViewById(R.id.price);
        tvlocation = (TextView) findViewById(R.id.location);

        events = db.getAllEventList();

        for(int i =0; i<events.size();i++){
            tvname.setText(tvname.getText().toString()+events.get(i).getName()+"\n");
            tvdes.setText(tvdes.getText().toString()+events.get(i).getDescription()+"\n");
            tvprice.setText(tvprice.getText().toString()+events.get(i).getPrice()+"\n");
            tvlocation.setText(tvlocation.getText().toString()+events.get(i).getLocation()+"\n");
        }
    }
}
