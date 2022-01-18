package com.example.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;



public class MainActivity extends AppCompatActivity{
    CalendarView calendarView;
    TextView Main;
    Context context ;
    Button button;
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context =getApplicationContext();
        calendarView= findViewById(R.id.calendarView);
        Main=findViewById(R.id.Main);
        button=findViewById(R.id.button);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String Date;
            if(month<=8 && dayOfMonth<=9){
                Date=year+"-0"+(month+1)+"-0"+dayOfMonth;
            }else if(month<=8 && dayOfMonth>9){
                Date=year+"-0"+(month+1)+"-"+dayOfMonth;
            }else if(month>8 && dayOfMonth<=9){
                Date=year+"-"+(month+1)+"-0"+dayOfMonth;
            }else {
                Date=year+"-"+(month+1)+"-"+dayOfMonth;
            }

            Main.setText(Date+"("+Eventcount.count(Date)+")");


            Main.setOnClickListener(v -> Toast.makeText(context,  Eventcount.getEvents(Date), Toast.LENGTH_SHORT).show());
        });
   button.setOnClickListener(v -> {
       Intent i=new Intent(getApplicationContext(),AddEvent.class);
       startActivity(i);
   });
        try {
        File file = new File(context.getFilesDir(),"Event.json");
         FileReader   fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null){
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
// This responce will have Json Format String
        String responce = stringBuilder.toString();
            JSONObject jsonObject  = new JSONObject(responce);

                Eventcount.list.add(new Event(Integer.parseInt(jsonObject.get("id").toString()),
                        LocalDate.parse(jsonObject.get("date").toString()),
                            jsonObject.get("event").toString()));


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }



    }
}