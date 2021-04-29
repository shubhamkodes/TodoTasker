package com.lusiftech.todotasker.RoomDatabase;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class},version = 1,exportSchema = false)
public abstract class TaskRoomDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();
    private static TaskRoomDatabase instance;
    private static final ExecutorService executorService= Executors.newFixedThreadPool(4);

    public static TaskRoomDatabase getDatabaseInstance(Application application){
        if(instance==null){
            synchronized (TaskRoomDatabase.class){
                if(instance==null){
                    instance=Room.databaseBuilder(application.getApplicationContext(),TaskRoomDatabase.class,"TaskDatabase").build();
                }
            }
        }
        return instance;
    }
    public static ExecutorService getExecutorService(){
        return executorService;
    }
}
