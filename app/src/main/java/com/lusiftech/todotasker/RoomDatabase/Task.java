package com.lusiftech.todotasker.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id=0;

    @NonNull
    private String title;
    private boolean checked=false;
    private int hourOfDay;
    private int minute;

    public void setId(int id){
        this.id=id;
    }
    public void setTitle(String arg){
        title=arg;
    }
    public void setChecked(boolean arg){
        checked=arg;
    }
    public void setHourOfDay(int arg){
        hourOfDay=arg;
    }
    public void setMinute(int arg){
        minute=arg;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public int getId() {
        return id;
    }

    public int getMinute() {
        return minute;
    }

    public boolean isChecked() {
        return checked;
    }
}
