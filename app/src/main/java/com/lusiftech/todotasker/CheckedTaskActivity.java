package com.lusiftech.todotasker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lusiftech.todotasker.RoomDatabase.Task;

import java.util.List;

public class CheckedTaskActivity extends AppCompatActivity implements Adapter.OnClickListener {
    Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked_task);

        Toolbar toolbar=findViewById(R.id.checkedToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Checked Task List");
        getSupportActionBar().setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.checked,null));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView=findViewById(R.id.rvChecked);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter adapter=new Adapter(new Adapter.TaskDiffUtil(),this);
        recyclerView.setAdapter(adapter);

        model=new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(Model.class);
        LiveData<List<Task>> checkedList=model.getCheckedList();
        checkedList.observe(this,(list)->{
            adapter.submitList(list);
        });

    }

    @Override
    public void checked(Task task) {
            model.setChecked(task.getId(),!task.isChecked());
    }

    @Override
    public void openTask(Task task) {
    }

    @Override
    public void deleteTask(Task task) {
            model.deleteTask(task.getId());
    }

    @Override
    public void addTask(String title) {

    }
}