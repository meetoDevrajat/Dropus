package com.roger.dropus;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     Button log  =(Button)findViewById(R.id.login);
        EditText email=(EditText)findViewById(R.id.email);

    }

    public  void loginPressed(View v)

    {
        Intent intent = new Intent(this,mai.class);
        startActivity(intent);

    }
}
