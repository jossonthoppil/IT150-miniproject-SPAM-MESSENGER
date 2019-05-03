package com.example.login;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class messagescr extends AppCompatActivity {

    EditText mtextmsg;
    Button maddmsg;
    TextView groupname;
    ListView messages;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagescr);

        mtextmsg = (EditText)findViewById(R.id.message);
        maddmsg = (Button)findViewById(R.id.send);
        Intent i = getIntent();
        final String name = i.getStringExtra("name");
        final String username = i.getStringExtra("username");
        groupname = (TextView)findViewById(R.id.groupname);
        groupname.setText(name);
        messages = (ListView)findViewById(R.id.messages);
        db = new DatabaseHelper(this);
        ArrayList msgs = new ArrayList<>();
        Cursor data = db.getmessages(name);
        if(data.getCount()==0)
        {
            Toast.makeText(messagescr.this,"NO MESSAGES",Toast.LENGTH_SHORT).show();
        }
        else {
            while (data.moveToNext()) {
                String str = data.getString(1) + " : " + data.getString(2);
                msgs.add(str);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, msgs);
                messages.setAdapter(listAdapter);
            }
        }
        maddmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mtextmsg.getText().toString().trim();
                String group = name;
                long val = db.addMsg(username,message,group);
                Toast.makeText(messagescr.this, "Message added", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(messagescr.this,homepagecr.class);
                i.putExtra("username",username);
                startActivity(i);
            }
        });
    }
}
