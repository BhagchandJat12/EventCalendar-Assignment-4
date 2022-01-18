package com.example.calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;


import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;

import java.util.Calendar;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AddEvent extends AppCompatActivity {
    TextView Id;
    TextView Event;
    TextView Date;
    EditText id,event,date;
    Context context;
    Button b;
   final Calendar calendar=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent);
        context = getApplicationContext();
        Id= findViewById(R.id.Id);
        Event= findViewById(R.id.event);
        Date= findViewById(R.id.date);
        id= findViewById(R.id.Idin);
        event= findViewById(R.id.Eventin);
        date=findViewById(R.id.Datein);
        b=findViewById(R.id.button2);

      b.setOnClickListener(v -> {
          int i= Integer.parseInt(id.getText().toString());
          LocalDate da=LocalDate.parse(date.getText().toString());
          String s= event.getText().toString();
          try {
          JSONObject jsonObject=new JSONObject();
          jsonObject.put("id",i);
          jsonObject.put("date",da);
          jsonObject.put("event",s);

          String userString = jsonObject.toString();

              File file = new File(context.getFilesDir(),"Event.json");
              FileWriter fileWriter = new FileWriter(file);
              BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
              bufferedWriter.write(userString);
              bufferedWriter.close();
          } catch (IOException | JSONException e) {
              e.printStackTrace();
          }

          Intent a=new Intent(getApplicationContext(),MainActivity.class);
          startActivity(a);
      });
    }


}