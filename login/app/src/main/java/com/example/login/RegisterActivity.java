package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    EditText getTextCnfPassword;
    EditText mTextstudentcr;
    Spinner myspinner;
    Button mButtonRegister;
    RadioButton r1,r2,r3;
    TextView mTextViewLogin;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        myspinner = (Spinner) findViewById(R.id.branchspinner);
        /*ArrayAdapter<String> myadapter= new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.branches));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myadapter);
        */
        getTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        r1 = (RadioButton)findViewById(R.id.radiostudent);
        r2 = (RadioButton)findViewById(R.id.radiocr);
        r3 = (RadioButton)findViewById(R.id.radiocon);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentorcr = "";
                if(r1.isChecked())
                {
                    studentorcr = "student";
                }
                else if(r2.isChecked())
                {
                    studentorcr = "cr";
                }
                else if(r3.isChecked())
                {
                    studentorcr = "con";
                }
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                int flag;
                //String studentorcr = mTextstudentcr.getText().toString().trim();
                String cnf_pwd = getTextCnfPassword.getText().toString().trim();
                flag=0;
                if(user.equals(""))
                {
                    flag=1;
                    Toast.makeText(RegisterActivity.this,"FILL IN USERNAME",Toast.LENGTH_SHORT).show();
                }
                else if(!pwd.equals(cnf_pwd))
                {
                    flag=1;
                    Toast.makeText(RegisterActivity.this,"PASSWORDS DO NOT MATCH",Toast.LENGTH_SHORT).show();
                }
                else if(pwd.equals(""))
                {
                    flag=1;
                    Toast.makeText(RegisterActivity.this,"FILL IN PASSWORD",Toast.LENGTH_SHORT).show();
                }
                if(flag==0)
                {
                    long val2 = db.checkUserExists(user);
                    if(val2 == 0)
                    {
                        //long val = db.addUser(user,pwd);
                        //Toast.makeText(RegisterActivity.this,"SUCCESSFULLY CREATED USER",Toast.LENGTH_SHORT).show();
                        if(studentorcr.equals("con"))
                        {
                            Intent moveTointerests = new Intent(RegisterActivity.this,interestscon.class);
                            moveTointerests.putExtra("username",user);
                            moveTointerests.putExtra("password",pwd);
                            moveTointerests.putExtra("studentorcr",studentorcr);
                            startActivity(moveTointerests);
                        }
                        else {
                            Intent moveTodetails = new Intent(RegisterActivity.this, activity_register2.class);
                            moveTodetails.putExtra("username", user);
                            moveTodetails.putExtra("password", pwd);
                            moveTodetails.putExtra("studentorcr", studentorcr);
                            startActivity(moveTodetails);
                        }
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this,"USER ALREADY EXISTS",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
