package com.example.ss12dark.paintgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddEvent extends AppCompatActivity {

    public String name;
    public String description;
    public float price;
    public int type=0;
    public int type2;
    public String location;

    public EditText etname,etdes,etprice,etlocation;
    public RadioGroup foodGroup,hangoutGroup;

    public MyDBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        etname = (EditText) findViewById(R.id.name);
        etdes = (EditText) findViewById(R.id.des);
        etprice = (EditText) findViewById(R.id.price);
        etlocation = (EditText) findViewById(R.id.location);

        foodGroup = (RadioGroup) findViewById(R.id.foodGroup);
        hangoutGroup = (RadioGroup) findViewById(R.id.hangoutGroup);

        db = new MyDBHandler(this);
    }

    public void openFood(View v){
        foodGroup.setVisibility(View.VISIBLE);
        hangoutGroup.setVisibility(View.GONE);
    }
    public void openHangout(View v){
        foodGroup.setVisibility(View.GONE);
        hangoutGroup.setVisibility(View.VISIBLE);
    }
    public void closeAll(View v){
        foodGroup.setVisibility(View.GONE);
        hangoutGroup.setVisibility(View.GONE);
        type=3; type2=1;
    }

    public void type(View v){
        switch (v.getId()){
            case R.id.both:{type=1; type2=1;break;}
            case R.id.vegan:{type=1; type2=2;break;}
            case R.id.meatlover:{type=1; type2=3;break;}
            case R.id.outside:{type=2; type2=1;break;}
            case R.id.inside:{type=2; type2=2;break;}
            case R.id.elsewhere:{type=2; type2=3;break;}



        }
    }

    public void finish(View v){
        if(etname.getText().toString().equals("")||etdes.getText().toString().equals("")||etlocation.getText().toString().equals("")||etprice.getText().toString().equals("")|| type==0){
            Toast.makeText(this,"you didnt fill all the fields properly",Toast.LENGTH_SHORT).show();
        }else{
            name = etname.getText().toString();
            description = etdes.getText().toString();
            price =  Float.parseFloat(etprice.getText().toString());
            location = etlocation.getText().toString();

            Event e = new Event(name,description,price,type,type2,location);
            db.addEvent(e);
            finish();
        }

    }
}
