package com.example.abhij.tictaktoe;

import android.content.Intent;
import android.content.SharedPreferences;
//import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class First extends AppCompatActivity {

    public static String Size="Size";
    public static String Player1="Player1";
    public static String Player2="Player2";
    public  int Player1Entry=0;
    public  int Player2Entry=0;

    EditText e1;
    EditText e2;

    RadioGroup r;
    RadioButton b;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        e1=findViewById(R.id.EditPlayerFirst);
        e2=findViewById(R.id.EditPlayerSecond);

        r=findViewById(R.id.RadioGroup);

        //filling names as per filled the previous time

        sharedPreferences=getSharedPreferences("Data",MODE_PRIVATE);
        e1.setText(sharedPreferences.getString(Player1,""));

        e2.setText(sharedPreferences.getString(Player2,""));


        int num=sharedPreferences.getInt(Size,3);

        if(num==3)
            b=findViewById(R.id.Radio3X3);
        else if(num==4)
            b=findViewById(R.id.Radio4X4);
        else if(num==5)
            b=findViewById(R.id.Radio5X5);

        b.setChecked(true);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.menu,menu);
    return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {

            //case R.id.bckcngeColor:


        }


        return super.onOptionsItemSelected(item);
    }

    public void StartGame(View view)
    {

        b=findViewById(r.getCheckedRadioButtonId());

        Intent intent =new Intent(this,Game.class);

        Bundle bundle=new Bundle();

       // Tag t= new Tag("size",Integer.parseInt(b.getText().toString().charAt(0)+""));
        Log.d("size",Integer.parseInt(b.getText().toString().charAt(0)+"")+"");
        bundle.putInt(Size,Integer.parseInt(b.getText().toString().substring(0,1)));


        bundle.putString(Player1,e1.getText().toString());
        bundle.putString(Player2,e2.getText().toString());

        intent.putExtras(bundle);

        SharedPreferences.Editor editor=sharedPreferences.edit();


        editor.putInt(Size,Integer.parseInt(b.getText().toString().charAt(0)+""));
        editor.putString(Player1,e1.getText().toString());
        editor.putString(Player2,e2.getText().toString());

        editor.commit();




        startActivity(intent);
        finish();



    }

    public void RemoveName1(View view) {
        EditText e=findViewById(view.getId());


        if(Player1Entry==0)
        {e.setText("");
        Player1Entry++;}
    }

    public void RemoveName2(View view) {
        EditText e=findViewById(view.getId());

        if(Player2Entry==0)
        {e.setText("");
        Player2Entry++;}

    }
}
