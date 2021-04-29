package com.lusiftech.todotasker.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("Select * from tasks_table where checked=0")
    public LiveData<List<Task>> getAllTasks();

    @Query("Select * from tasks_table where checked=1")
    public LiveData<List<Task>> getCheckedTask();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void addTask(Task task);

    @Query("Update tasks_table set title=:title where id=:id")
    public void updateTaskTitle(int id,String title);

    @Query("Update tasks_table set checked=:arg where id=:id")
    public void setChecked(int id,boolean arg);

    @Query("Update tasks_table set hourOfDay=:hr, minute=:min where id=:id")
    public void updateTaskTime(int id,int hr,int min);

    @Query("Delete from tasks_table where id=:id")
    public void deleteTask(int id);


}
