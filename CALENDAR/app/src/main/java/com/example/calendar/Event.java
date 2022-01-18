package com.example.calendar;

import android.os.Build;

import android.support.annotation.RequiresApi;

import java.time.LocalDate;


@RequiresApi(api = Build.VERSION_CODES.O)
public  class Event {
   private  int id;
   private LocalDate date;
   private  String event;



    public Event(int id, LocalDate date, String event) {
        this.id = id;
        this.date = date;
        this.event = event;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", event='" + event + '\'' +
                '}';
    }
}
