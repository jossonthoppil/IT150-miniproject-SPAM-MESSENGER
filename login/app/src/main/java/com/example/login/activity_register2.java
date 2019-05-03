package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class activity_register2 extends AppCompatActivity {
    EditText mTextroll_no;
    EditText mTextbranch;
    EditText mTextbatch;
    Button mButtonRegister;
    Spinner myspinner,myspinner2;
    TextView mTextViewLogin;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        Intent i = getIntent();
        final String username = i.getStringExtra("username");
        final String password = i.getStringExtra("password");
        final String studentorcr = i.getStringExtra("studentorcr");
        db = new DatabaseHelper(this);
        mTextroll_no= (EditText)findViewById(R.id.edittext_rollno);
        //mTextbranch = (EditText)findViewById(R.id.edittext_branch);
        //mTextbatch = (EditText)findViewById(R.id.edittext_batch);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.textview_login);
        myspinner = (Spinner) findViewById(R.id.branchspinner);
        ArrayAdapter<String> myadapter= new ArrayAdapter<String>(activity_register2.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.branches));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myadapter);
        myspinner2 = (Spinner) findViewById(R.id.batchspinner);
        ArrayAdapter<String> myadapter2= new ArrayAdapter<String>(activity_register2.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.batchs));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner2.setAdapter(myadapter2);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(activity_register2.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollno = mTextroll_no.getText().toString().trim();
                String branch = myspinner.getSelectedItem().toString().trim();
                //String branch = mTextbranch.getText().toString().trim();
                //String batch = mTextbatch.getText().toString().trim();
                String batch = myspinner2.getSelectedItem().toString().trim();
                if(studentorcr.equals("student"))
                {
                    //long val = db.addUser(username,password,rollno,branch,batch);
                    //Toast.makeText(activity_register2.this, "SUCCESSFULLY CREATED Student", Toast.LENGTH_SHORT).show();
                    Intent moveTointerests = new Intent(activity_register2.this, interests.class);
                    moveTointerests.putExtra("username",username);
                    moveTointerests.putExtra("password",password);
                    moveTointerests.putExtra("studentorcr",studentorcr);
                    moveTointerests.putExtra("rollno",rollno);
                    moveTointerests.putExtra("branch",branch);
                    moveTointerests.putExtra("batch",batch);
                    startActivity(moveTointerests);
                }
                else if(studentorcr.equals("cr"))
                {
                    long val = db.addcr(username,password,rollno,branch,batch,null,null,null,null,null,null,studentorcr);
                    Toast.makeText(activity_register2.this, "SUCCESSFULLY CREATED CR", Toast.LENGTH_SHORT).show();
                    Intent moveTologin = new Intent(activity_register2.this, MainActivity.class);
                    //moveTointerests.putExtra("username",username);
                    //moveTointerests.putExtra("password",password);
                    //moveTointerests.putExtra("studentorcr",studentorcr);
                    //moveTointerests.putExtra("rollno",rollno);
                    //moveTointerests.putExtra("branch",branch);
                    //moveTointerests.putExtra("batch",batch);
                    startActivity(moveTologin);
                }
                }
        });
    }
}
