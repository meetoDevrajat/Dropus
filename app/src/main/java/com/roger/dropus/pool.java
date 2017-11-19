package com.roger.dropus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class pool extends Fragment {


    public pool() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.pool, container, false);
       Button pooler =(Button)v.findViewById(R.id.pooler);
        Button log  =(Button)v.findViewById(R.id.poolin);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), MapsActivity.class);
                startActivity(in);
            }
        });
        pooler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), MapsActivityDriver.class);
                startActivity(in);
            }
        });
        return  v;
    }




}