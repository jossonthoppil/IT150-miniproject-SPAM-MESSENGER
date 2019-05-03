package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    Button mButtonLoginascr;
    TextView mTextViewRegister;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button)findViewById(R.id.button_login);
        mButtonLoginascr = (Button)findViewById(R.id.button_loginascr);
        mTextViewRegister = (TextView)findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                 startActivity(registerIntent);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(user,pwd);
                if(res == true)
                {
                    Intent i = new Intent(MainActivity.this,homepage.class);
                    i.putExtra("username",user);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"INCORRECT USERNAME OR PASSWORD",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mButtonLoginascr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkcr(user,pwd);
                if(res==true)
                    {
                        //Toast.makeText(MainActivity.this,"SUCCESSFULLY LOGGED IN",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this,homepagecr.class);
                        i.putExtra("username",user);
                        startActivity(i);
                    }
                else
                    {
                        Toast.makeText(MainActivity.this,"INCORRECT USERNAME OR PASSWORD",Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
