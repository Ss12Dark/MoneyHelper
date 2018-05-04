package com.example.ss12dark.paintgame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;//for the use of activity for result
    public Button delete, edit;
    public String NAME;
    public int ID;
    MyDBHandler db;//to change by my decision
    List<Event> names;// the list to get the names and to delete from

    public CustomDialogClass(Activity a,String name,int id) {
        super(a);
        NAME=name;
        ID=id;
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.edit_dialog);
        delete = (Button) findViewById(R.id.btn_delete);
        edit = (Button) findViewById(R.id.btn_edit);
        delete.setOnClickListener(this);
        edit.setOnClickListener(this);
        db= new MyDBHandler(c);
        names=db.getAllEventList();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                db.deleteEvent(ID);
                Toast.makeText(c,"event as been deleted",Toast.LENGTH_SHORT).show();
                c.recreate();//so the changes will appear right after the click
                break;
            case R.id.btn_edit:

                for(int i=0;i<names.size();i++){
                    if(NAME.equals(names.get(i).getName())){
                        String title =names.get(i).getName();
                        String des =names.get(i).getDescription();
                        String price =names.get(i).getPrice()+"";
                        String location = names.get(i).getLocation();
//send the edit page the exist movie details to edit
                        Intent editActivity = new Intent(c,AddEvent.class);
                        editActivity.putExtra("price",price);
                        editActivity.putExtra("name",title);
                        editActivity.putExtra("des",des);
                        editActivity.putExtra("location",location);
                        c.startActivity(editActivity);
                        break;
                    }
                }


            default:
                break;
        }
        dismiss();
    }
}