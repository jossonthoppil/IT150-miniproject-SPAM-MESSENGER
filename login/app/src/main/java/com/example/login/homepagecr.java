package com.example.login;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.DatabaseHelper;
import com.example.login.R;
import com.example.login.messages;

import java.util.ArrayList;

public class homepagecr extends AppCompatActivity {


    TextView welcometext;
    //EditText mtextmsg;
    //EditText mtextgroup;
    //Button maddmsg;
    ListView listmsgs;
    TextView logout;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepagecr);

        Intent i = getIntent();
        final String username = i.getStringExtra("username");
        welcometext = (TextView)findViewById(R.id.welcomeid);
        welcometext.setText("WELCOME " + username);
        logout = (TextView)findViewById(R.id.logout);
        //mtextmsg=(EditText)findViewById(R.id.message);
        //mtextgroup=(EditText)findViewById(R.id.group);
        //maddmsg = (Button)findViewById(R.id.sendmessage);
        listmsgs = (ListView)findViewById(R.id.listViewmsgs);
        db = new DatabaseHelper(this);
        ArrayList grps = new ArrayList<>();
        Cursor data = db.getgroupscr(username);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homepagecr.this,MainActivity.class);
                startActivity(i);
            }
        });
        if(data.getCount()==0)
        {
            Toast.makeText(com.example.login.homepagecr.this,"NO GROUPS",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (data.moveToNext())
            {
                String studentorcr = data.getString(12);
                if(studentorcr.equals("cr"))
                {
                    grps.add(data.getString(4));
                    grps.add(data.getString(5));
                    grps.add("NITK");
                    //String sports = data.getString(6);
                    //String dance = data.getString(8);
                    //String music = data.getString(7);
                    //if(sports.equals("yes"))
                    {
                        //grps.add("SPORTS");
                    }
                    //if(music.equals("yes"))
                    {
                        //grps.add("MUSIC");
                    }
                    //if(dance.equals("yes"))
                    {
                        //grps.add("DANCE");
                    }
                    //grps.add(data.getString(9));
                    ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, grps);
                    listmsgs.setAdapter(listAdapter);
                    listmsgs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(com.example.login.homepagecr.this, messagescr.class);
                            Object o = listmsgs.getItemAtPosition(position);
                            String str = (String) o;
                            i.putExtra("name", str);
                            i.putExtra("username", username);
                            startActivity(i);
                        }
                    });
                }
                else
                {
                    String sports = data.getString(6);
                    String dance = data.getString(8);
                    String music = data.getString(7);
                    String debate = data.getString(10);
                    String literature = data.getString(11);
                    String club = data.getString(9);
                    if(!sports.equals("NONE"))
                    {
                        grps.add(sports);
                    }
                    if(!club.equals("NONE"))
                    {
                        grps.add(club);
                    }
                    //grps.add(sports);
                    //grps.add(club);
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
                    //grps.add(data.getString(9));
                    grps.add("NITK");
                    ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, grps);
                    listmsgs.setAdapter(listAdapter);
                    listmsgs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(com.example.login.homepagecr.this, messagescr.class);
                            Object o = listmsgs.getItemAtPosition(position);
                            String str = (String) o;
                            i.putExtra("name", str);
                            i.putExtra("username", username);
                            startActivity(i);
                        }
                    });
                }
            }
        }
    }
}
