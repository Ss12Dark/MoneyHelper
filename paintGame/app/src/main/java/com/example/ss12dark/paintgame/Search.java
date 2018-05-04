package com.example.ss12dark.paintgame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Search extends AppCompatActivity {
    MyDBHandler db;
    public List<Event> events;
    public RadioGroup foodGroup, hangoutGroup;
    public EditText money;
    public LinearLayout parent;

    public int type = 0, type2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        db = new MyDBHandler(this);
        money = (EditText) findViewById(R.id.money);
        events = db.getAllEventList();
        parent = (LinearLayout) findViewById(R.id.parent);

        foodGroup = (RadioGroup) findViewById(R.id.foodGroup);
        hangoutGroup = (RadioGroup) findViewById(R.id.hangoutGroup);
    }

    public void openFood(View v) {
        foodGroup.setVisibility(View.VISIBLE);
        hangoutGroup.setVisibility(View.GONE);
        type=5;
    }

    public void openHangout(View v) {
        foodGroup.setVisibility(View.GONE);
        hangoutGroup.setVisibility(View.VISIBLE);
        type = 2;
        type2 = 4;
    }

    public void closeAll(View v) {
        foodGroup.setVisibility(View.GONE);
        hangoutGroup.setVisibility(View.GONE);
        type = 3;
        type2 = 1;
    }

    public void type(View v) {
        switch (v.getId()) {
            case R.id.both: {
                type = 1;
                type2 = 1;
                break;
            }
            case R.id.vegan: {
                type = 1;
                type2 = 2;
                break;
            }
            case R.id.meatlover: {
                type = 1;
                type2 = 3;
                break;
            }
            case R.id.outside: {
                type = 2;
                type2 = 1;
                break;
            }
            case R.id.inside: {
                type = 2;
                type2 = 2;
                break;
            }
            case R.id.elsewhere: {
                type = 2;
                type2 = 3;
                break;
            }


        }
    }

    public void search(View v) {
        if(money.getText().toString().equals("")){
            Toast.makeText(this,"please fill the money value",Toast.LENGTH_SHORT).show();
        }else {
            parent.removeAllViews();
            Event border = new Event("שם","פירוט",0,0,0,"מיקום");
            eventLookMaker(border);
            if (type == 0) {
                for (int i = 0; i < events.size(); i++) {
                    Event e =events.get(i);
                    if(e.getPrice()<=Float.parseFloat(money.getText().toString())&& e.getPrice()>=Float.parseFloat(money.getText().toString())-15){
                        eventLookMaker(e);
                    }
                }
            } else {
                for (int i = 0; i < events.size(); i++) {
                    Event e =events.get(i);
                    if(type==1 && type2==1){
                        if (e.getType() == type) {
                            if ( e.getPrice() >= Float.parseFloat(money.getText().toString()) - 50 &&e.getPrice() <= Float.parseFloat(money.getText().toString())+15) {
                                eventLookMaker(e);
                            }
                        }
                    }else if(type==2 && type2==4){
                        if (e.getType() == type) {
                            if (e.getPrice() >= Float.parseFloat(money.getText().toString()) - 50 &&e.getPrice() <= Float.parseFloat(money.getText().toString())+15) {
                                eventLookMaker(e);
                            }
                        }
                    }else{
                        if (e.getType() == type && e.getType2() == type2) {
                            if ( e.getPrice() >= Float.parseFloat(money.getText().toString()) - 50 &&e.getPrice() <= Float.parseFloat(money.getText().toString())+15) {
                                eventLookMaker(e);
                            }
                        }
                    }
                }
            }
            if(parent.getChildCount()==1){
                Toast.makeText(this,"no events was found in those conditions",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void eventLookMaker(Event e) {
        LinearLayout ll = new LinearLayout(this);
        TextView title = new TextView(this);
        TextView des = new TextView(this);
        TextView price = new TextView(this);
        TextView location = new TextView(this);

        resizeElse(title,200);
        resizeElse(des,350);
        resizeElse(price,100);
        resizeElse(location,200);
        resizeLinearLayout(ll);

        final CustomDialogClass cdd = new CustomDialogClass(Search.this, e.getName(),e.getId());

        ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                cdd.show();
                return false;
            }
        });

        title.setText(e.getName());
        des.setText(e.getDescription());
        if(e.getType()==0){
            price.setText("מחיר");
        }else {
            price.setText(e.getPrice() + "");
        }
        location.setText(e.getLocation());

        ll.addView(title);
        ll.addView(des);
        ll.addView(price);
        ll.addView(location);
        parent.addView(ll);
    }

    public void resizeLinearLayout(LinearLayout ll) {
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(positionRules);
        positionRules.setMargins(25, 25, 25, 25);
        ll.setLayoutParams(positionRules);
        ll.setBackgroundColor(Color.GRAY);
    }

    public void resizeElse(TextView ll,int i) {
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(positionRules);
        positionRules.setMargins(3, 3, 12, 3);
        ll.setLayoutParams(positionRules);
        ll.setTextColor(Color.BLACK);
        ll.getLayoutParams().width = i;
    }



}
