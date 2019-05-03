package com.example.login;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class homepage extends AppCompatActivity {


    TextView welcometext,logout;
    //EditText mtextmsg;
    //EditText mtextgroup;
    //Button maddmsg;
    ListView listmsgs;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Intent i = getIntent();
        final String username = i.getStringExtra("username");
        welcometext = (TextView)findViewById(R.id.welcomeid);
        logout = (TextView)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(homepage.this,MainActivity.class);
                startActivity(i);
            }
        });
        welcometext.setText("WELCOME " + username);
        //mtextmsg=(EditText)findViewById(R.id.message);
        //mtextgroup=(EditText)findViewById(R.id.group);
        //maddmsg = (Button)findViewById(R.id.sendmessage);
        listmsgs = (ListView)findViewById(R.id.listViewmsgs);
        db = new DatabaseHelper(this);
        ArrayList grps = new ArrayList<>();
        Cursor data = db.getgroups(username);
        if(data.getCount()==0)
        {
            Toast.makeText(homepage.this,"NO GROUPS",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (data.moveToNext())
            {
                grps.add(data.getString(4));
                grps.add(data.getString(5));
                String sports = data.getString(6);
                String dance = data.getString(8);
                String music = data.getString(7);
                String debate = data.getString(10);
                String club = data.getString(9);
                String literature = data.getString(11);
                if(!sports.equals("NONE"))
                {
                    grps.add(sports);
                }
                if(!club.equals("NONE"))
                {
                    grps.add(club);
                }
                if(music.equals("yes"))
                {
                    grps.add("MUSIC");
                }
                if(dance.equals("yes"))
                {
                    grps.add("DANCE");
                }
                if(debate.equals("yes"))
                {
                    grps.add("DEBATE");
                }
                if(literature.equals("yes"))
                {
                    grps.add("LITERATURE");
                }
                grps.add("NITK");
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,grps);
                listmsgs.setAdapter(listAdapter);
                listmsgs.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(homepage.this,messages.class);
                        Object o = listmsgs.getItemAtPosition(position);
                        String str=(String)o;
                        i.putExtra("name",str);
                        i.putExtra("username",username);
                        startActivity(i);
                    }
                });
            }
        }
    }
}
