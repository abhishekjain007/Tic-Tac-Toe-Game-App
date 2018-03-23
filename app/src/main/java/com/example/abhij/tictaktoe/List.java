package com.example.abhij.tictaktoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        listView=findViewById(R.id.listView);

        ArrayList<String> execution=new ArrayList<>();
        for(int i=0;i<20;i++)
        {
            execution.add("Execution "+i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,execution);

        listView.setAdapter(adapter);



    }


}
