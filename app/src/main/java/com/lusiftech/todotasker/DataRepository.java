package com.lusiftech.todotasker;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.lusiftech.todotasker.RoomDatabase.Task;
import com.lusiftech.todotasker.RoomDatabase.TaskDao;
import com.lusiftech.todotasker.RoomDatabase.TaskRoomDatabase;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DataRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>>  allTaskList;
    private LiveData<List<Task>> checkedTaskList;
    public DataRepository(Application application){
        TaskRoomDatabase taskRoomDatabase=TaskRoomDatabase.getDatabaseInstance(application);
        taskDao=taskRoomDatabase.taskDao();
        allTaskList=taskDao.getAllTasks();
        checkedTaskList=taskDao.getCheckedTask();
    }

    public void addTask(Task task){
        TaskRoomDatabase.getExecutorService().execute(()->{
            taskDao.addTask(task);
        });
    }
    public void updateTaskTitle(int id, String title){
        TaskRoomDatabase.getExecutorService().execute(()->{
            taskDao.updateTaskTitle(id,title);
        });
    }
    public void updateTaskTime(int id,int hr, int min){
        TaskRoomDatabase.getExecutorService().execute(()->{
            taskDao.updateTaskTime(id,hr,min);
        });
    }
    public void setChecked(int id,boolean arg){
        TaskRoomDatabase.getExecutorService().execute(()->{
            taskDao.setChecked(id,arg);
        });
    }
    public LiveData<List<Task>> getAllTaskList(){
        return allTaskList;
    }

    public void deleteTask(int id){
        TaskRoomDatabase.getExecutorService().execute(()->{
            taskDao.deleteTask(id);
        });
    }
    public LiveData<List<Task>> getCheckedList(){

        return checkedTaskList;
    }
}
