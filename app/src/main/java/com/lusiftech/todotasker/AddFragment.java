package com.lusiftech.todotasker;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddFragment extends DialogFragment {

    Adapter.OnClickListener onClickListener;
    public AddFragment(Adapter.OnClickListener listener) {
        // Required empty public constructor
        onClickListener=listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_add,container,false);
        Button btn=view.findViewById(R.id.btnCancel);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                getDialog().dismiss();
            }
        });

        EditText editText=view.findViewById(R.id.edTitle);


        Button addBtn=view.findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String title= editText.getText().toString();
                if(!TextUtils.isEmpty(title)){
                    onClickListener.addTask(title);
                    getDialog().dismiss();
                }
                else{
                    Toast.makeText(getContext(),"Task Field is empty!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}