package com.lusiftech.todotasker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.snackbar.Snackbar;
import com.lusiftech.todotasker.RoomDatabase.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.OnClickListener {
    private Model model;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){

        Toolbar toolbar=findViewById(R.id.topToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getColor(R.color.white));

        model=new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(Model.class);
        Adapter adapter=new Adapter(new Adapter.TaskDiffUtil(),this);
        RecyclerView recyclerView=findViewById(R.id.rvTasks);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        model.getAllTaskList().observe(this,(task)->{
                adapter.submitList(task);
        });

        recyclerView.setAdapter(adapter);


    }


    public void openTask(Task task){

    }
    public void checked(Task task){

        model.setChecked(task.getId(),true);
        handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                View view=findViewById(R.id.rvTasks);
                Snackbar snackBar= Snackbar.make(view,"Task Completed!!", Snackbar.LENGTH_LONG);
                snackBar.setAction("Undo",new View.OnClickListener(){
                    public void onClick(View view){
                        model.setChecked(task.getId(),false);
                    }
                });
                snackBar.show();
            }
        });
    }


    public void deleteTask(Task task){
        model.deleteTask(task.getId());
    }

    public void fabAddClick(View view){
        AddFragment addFragment=new AddFragment(this);
        addFragment.show(getSupportFragmentManager(),"Add Task");
    }
    public void addTask(String title){
        Task task=new Task();
        task.setTitle(title);
        model.addTask(task);
        Toast.makeText(this,"Task Set!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent=new Intent(this,CheckedTaskActivity.class);
        startActivity(intent);
        return true;
    }
}