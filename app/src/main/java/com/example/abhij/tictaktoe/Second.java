package com.example.abhij.tictaktoe;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.example.abhij.tictaktoe.MainActivity.Key_age;
import static com.example.abhij.tictaktoe.MainActivity.Key_gender;
import static com.example.abhij.tictaktoe.MainActivity.Key_name;
import static com.example.abhij.tictaktoe.MainActivity.Key_phone;

public class Second extends AppCompatActivity implements View.OnClickListener{


    SharedPreferences sharedPreferences;

    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;

    public final static int Player_0=0;
    public final static int Player_1=1;
    public final static int Player_No=-1;

    LinearLayout linearLayout;

    TTTButton board[][];
    Boolean gameOver;
    int size=3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        sharedPreferences = getSharedPreferences("abc",MODE_PRIVATE);

//
//        linearLayout=findViewById(R.id.rootLayout);
//        board=new TTTButton[size][size];
//        initGame();
//        gameOver=false;


        t1=findViewById(R.id.text5);
        t2=findViewById(R.id.text6);
        t3=findViewById(R.id.text7);
        t4=findViewById(R.id.text8);

        t1.setText(sharedPreferences.getString(MainActivity.Key_name,""));
        t2.setText(sharedPreferences.getInt(MainActivity.Key_age,0)+"");
        t3.setText(sharedPreferences.getInt(MainActivity.Key_phone,0)+"");
        t4.setText(sharedPreferences.getString(MainActivity.Key_gender,""));


       // Intent intent=getIntent();

//        Bundle bundle=intent.getExtras();
//
//
//        t1.setText(bundle.getString(Key_name));
//        t2.setText(bundle.getInt("key_age") + "");
//
//        t3.setText(bundle.getInt(Key_phone) + "");
//        t4.setText(bundle.getString("key_gender"));
//




    }

    public void Edit(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this,"hello",Toast.LENGTH_LONG).show();
        if(requestCode==1) {
            if(resultCode==1) {
                Bundle bundle = data.getExtras();


                t1.setText(bundle.getString(Key_name));
                t2.setText(bundle.getInt("key_age") + "");

                t3.setText(bundle.getInt(Key_phone) + "");
                t4.setText(bundle.getString("key_gender"));


                SharedPreferences.Editor editor =sharedPreferences.edit();

                editor.putString(MainActivity.Key_name,bundle.getString(Key_name));
                editor.putString(MainActivity.Key_gender,bundle.getString(Key_gender));
                editor.putInt(MainActivity.Key_age,bundle.getInt(Key_age));
                editor.putInt(MainActivity.Key_phone,bundle.getInt(Key_phone));



                editor.commit();

//
//                finish();
            }
        }
    }

    public void initGame()
    {


    }

    public void buildBoard()
    {

        for(int i=0;i<size;i++)
        {
            LinearLayout row=new LinearLayout(this);
            LinearLayout.LayoutParams params=
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);

            row.setLayoutParams(params);
            row.setOrientation(LinearLayout.HORIZONTAL);
            for(int j=0;j<size;j++)
            {
                TTTButton button=new TTTButton(this);
                LinearLayout.LayoutParams buttonParams=
                        new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                button.setLayoutParams(buttonParams);

                button.setOnClickListener(this);
                button.setLayoutParams(buttonParams);
                board[i][j]=button;
  //              int f= Random;
            }

        }
    }


    @Override
    public void onClick(View view) {

        TTTButton b=findViewById(view.getId());
       if(!gameOver) {
           checkIfFinished();
//           togglePlayer


       }
    }

    private void checkIfFinished() {





    }

}
