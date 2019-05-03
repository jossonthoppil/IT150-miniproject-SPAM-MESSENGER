package com.example.login;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class messages extends AppCompatActivity {

    TextView groupname;
    ListView messages;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        Intent i = getIntent();
        final String name = i.getStringExtra("name");
        groupname = (TextView)findViewById(R.id.groupname);
        groupname.setText(name);
        messages = (ListView)findViewById(R.id.messages);
        db = new DatabaseHelper(this);
        ArrayList msgs = new ArrayList<>();
        Cursor data = db.getmessages(name);
        if(data.getCount()==0)
        {
            Toast.makeText(messages.this,"NO MESSAGES",Toast.LENGTH_SHORT).show();
        }
        else {
            while (data.moveToNext()) {
                String str = data.getString(1) + " : " + data.getString(2);
                msgs.add(str);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, msgs);
                messages.setAdapter(listAdapter);
            }
        }
    }
}
