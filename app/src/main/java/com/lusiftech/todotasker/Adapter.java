package com.lusiftech.todotasker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.lusiftech.todotasker.RoomDatabase.Task;

public class Adapter extends ListAdapter<Task, Adapter.TaskViewHolder> {
    private OnClickListener onClick;
    public Adapter(TaskDiffUtil diffUtil,OnClickListener onClickListener){
        super(diffUtil);
        onClick=onClickListener;
    }
    public Adapter(TaskDiffUtil diffUtil){
        super(diffUtil);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_todo,parent,false);
        return new TaskViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
            Task task=getItem(position);
            MaterialCheckBox checkbox=holder.taskCard.findViewById(R.id.checkBox);
            TextView textView=holder.taskCard.findViewById(R.id.tvTask);
            ImageView delete=holder.taskCard.findViewById(R.id.btnDelete);

            textView.setText(task.getTitle());

            if(task.isChecked())
                 checkbox.setChecked(task.isChecked());
            else checkbox.setChecked(false);

            delete.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    onClick.deleteTask(task);
                }
            });

            checkbox.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onClick.checked(task);
                }
            });
    }
    // Inner class define-------------------------------------


    public class TaskViewHolder extends RecyclerView.ViewHolder{
        CardView taskCard;
        public TaskViewHolder(CardView cardView){
            super(cardView);
            taskCard=cardView;
        }
    }
    public static class TaskDiffUtil extends DiffUtil.ItemCallback<Task>{

        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.isChecked()==newItem.isChecked();
        }
    }

    public interface OnClickListener{
        public void checked(Task task);
        public void openTask(Task task);
        public void deleteTask(Task task);
        public void addTask(String title);
    }


}
