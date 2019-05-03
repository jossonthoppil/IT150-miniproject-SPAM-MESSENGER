package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class interestscon extends AppCompatActivity {
    CheckBox c1,c2,c3,c4,c5,c6,c7;
    DatabaseHelper db;
    Button b;
    String debate = "";
    String music = "";
    String dance = "";
    String literature = "";
    Spinner myspinner,myspinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interestscon);
        c1 = (CheckBox)findViewById(R.id.literature);
        c2 = (CheckBox)findViewById(R.id.music);
        c3 = (CheckBox)findViewById(R.id.dance);
        c4 = (CheckBox)findViewById(R.id.debate);
        myspinner = (Spinner)findViewById(R.id.clubspinner);
        ArrayAdapter<String> myadapter= new ArrayAdapter<String>(interestscon.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.clubs));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myadapter);
        myspinner2 = (Spinner)findViewById(R.id.sportsspinner);
        ArrayAdapter<String> myadapter2= new ArrayAdapter<String>(interestscon.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sports));
        myadapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner2.setAdapter(myadapter2);
        b = (Button)findViewById(R.id.register);
        Intent i = getIntent();
        final String username = i.getStringExtra("username");
        final String password = i.getStringExtra("password");
        final String studentorcr = i.getStringExtra("studentorcr");
        final String rollno = i.getStringExtra("rollno");
        final String branch = i.getStringExtra("branch");
        final String batch = i.getStringExtra("batch");
        db = new DatabaseHelper(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c1.isChecked())
                {
                    literature = "yes";
                }
                else
                {
                    literature = "no";
                }
                if(c2.isChecked())
                {
                    music = "yes";
                }
                else
                {
                    music = "no";
                }
                if(c3.isChecked())
                {
                    dance = "yes";
                }
                else
                {
                    dance = "no";
                }
                if(c4.isChecked())
                {
                    debate = "yes";
                }
                else
                {
                    debate = "no";
                }
                String club = myspinner.getSelectedItem().toString().trim();
                String sports = myspinner2.getSelectedItem().toString().trim();
                if(studentorcr.equals("student"))
                {
                    long res = db.addUser(username,password,rollno,branch,batch,sports,music,dance,club,debate,literature);
                    Toast.makeText(interestscon.this, "SUCCESSFULLY CREATED Student", Toast.LENGTH_SHORT).show();
                    Intent moveTologin = new Intent(interestscon.this, MainActivity.class);
                    startActivity(moveTologin);
                }
                else if (studentorcr.equals("con"))
                {
                    long res = db.addcr(username,password,null,null,null,sports,music,dance,club,debate,literature,studentorcr);
                    Toast.makeText(interestscon.this, "SUCCESSFULLY CREATED CONVENOR", Toast.LENGTH_SHORT).show();
                    Intent moveTologin = new Intent(interestscon.this, MainActivity.class);
                    startActivity(moveTologin);
                }
            }
        });
    }
}
