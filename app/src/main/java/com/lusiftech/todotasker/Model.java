package com.lusiftech.todotasker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lusiftech.todotasker.RoomDatabase.Task;
import com.lusiftech.todotasker.RoomDatabase.TaskRoomDatabase;

import java.util.List;

public class Model extends AndroidViewModel {

   private DataRepository dataRepository;
    public Model(Application application){
        super(application);
        dataRepository=new DataRepository(application);
    }

    public void addTask(Task task){
        TaskRoomDatabase.getExecutorService().execute(()->{
            dataRepository.addTask(task);
        });
    }
    public void updateTaskTitle(int id, String title){
        TaskRoomDatabase.getExecutorService().execute(()->{
            dataRepository.updateTaskTitle(id,title);
        });
    }
    public void updateTaskTime(int id,int hr, int min){
        TaskRoomDatabase.getExecutorService().execute(()->{
           dataRepository.updateTaskTime(id,hr,min);
        });
    }
    public void setChecked(int id,boolean arg){
        TaskRoomDatabase.getExecutorService().execute(()->{
            dataRepository.setChecked(id,arg);
        });
    }
    public LiveData<List<Task>> getAllTaskList(){
        return dataRepository.getAllTaskList();
    }
    public void deleteTask(int id){
        dataRepository.deleteTask(id);
    }

    public LiveData<List<Task>> getCheckedList(){
        return dataRepository.getCheckedList();
    }
}
