package com.example.abhij.tictaktoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {


 public final  static  String Key_name="key_name";
    public final  static String Key_age="key_age";
    public final  static String Key_phone="key_phone";
    public final static String Key_gender="key_gender";


    EditText e1;
    EditText e2;
    EditText e3;
    RadioButton r1;
    RadioGroup radioGroup1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=findViewById(R.id.edit1);
        e2=findViewById(R.id.edit2);
        e3=findViewById(R.id.edit3);
       radioGroup1=findViewById(R.id.group1);







//        int buttonPressed = radioGroup1.getCheckedRadioButtonId();
//        if (buttonPressed == -1) {
//            Log.d("TAG", "no button selected");
//        } else {
//            r1 = findViewById(buttonPressed);
//        }


    }
//
//    public void helloWorld(View view) {
//        RadioGroup group = findViewById(R.id.group1);
//        int id = group.getCheckedRadioButtonId();
//        switch(id)
//        {
//            case R.id.male:
//                Key_gender = "Male";
//                break;
//            case R.id.female:
//                Key_gender = "Female";
//                break;
//            case R.id.others:
//                Key_gender = "Other";
//                break;
//
//        }
//    }

    public void OnButtonClick(View view) {

        r1=findViewById(radioGroup1.getCheckedRadioButtonId());
        Intent intent=new Intent(this,Second.class);

        Bundle bundle=new Bundle();
        bundle.putString(MainActivity.Key_name,e1.getText().toString());
        bundle.putInt(MainActivity.Key_age,Integer.parseInt(e2.getText().toString()));
        bundle.putInt(MainActivity.Key_phone,Integer.parseInt(e3.getText().toString()));
        bundle.putString(MainActivity.Key_gender,r1.getText().toString());

        intent.putExtras(bundle);

        startActivity(intent);


    }
    public void NewOnButtonClick(View view) {

        r1=findViewById(radioGroup1.getCheckedRadioButtonId());
        Intent intent=new Intent();

        Bundle bundle=new Bundle();
        bundle.putString(MainActivity.Key_name,e1.getText().toString());
        if(e2.getText().toString()!=null)
        bundle.putInt(MainActivity.Key_age,Integer.parseInt(e2.getText().toString()));
        else
            bundle.putInt(MainActivity.Key_age,0);

        if(e3.getText().toString()!=null)
        bundle.putInt(MainActivity.Key_phone,Integer.parseInt(e3.getText().toString()));
        else
            bundle.putInt(MainActivity.Key_phone,0);

        if(r1!=null)
        bundle.putString(MainActivity.Key_gender,r1.getText().toString());
        else
            bundle.putString(MainActivity.Key_gender,"others");

        intent.putExtras(bundle);


        setResult(1,intent);

        finish();
    }
}
