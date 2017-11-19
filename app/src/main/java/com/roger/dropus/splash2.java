package com.roger.dropus;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.attr.button;
import static com.roger.dropus.R.styleable.View;

public class splash2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        Button  start =  (Button)findViewById(R.id.imageButton);



    }
    public  void gettingFun(View v)

    {
        Intent intent = new Intent(this,splash3.class);
        startActivity(intent);

    }
}
