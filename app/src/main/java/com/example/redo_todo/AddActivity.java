package com.example.redo_todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddActivity extends AppCompatActivity {

    CalendarView cvAddTask;
    EditText etAddTask;
    Button btnAddNewTask;

    String taskName;
    long recored_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        cvAddTask=findViewById(R.id.cvAddTask);
        etAddTask=findViewById(R.id.etAddTask);
        btnAddNewTask=findViewById(R.id.btnAddNewTask);

        cvAddTask.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar c2= new GregorianCalendar();
                c2.set(year,month,dayOfMonth);
                recored_date=c2.getTime().getTime();
            }
        });

        btnAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: can we directly add/create task here?
                //change save changes to Back4 app here
                // but still need to ship new Task info to Main (so the task can be added to list)
                taskName= etAddTask.getText().toString();
                Intent ix= new Intent();
                ix.putExtra("tkName",taskName);
                //Can we parcel date? -yes, convert it to long.
                ix.putExtra("day",recored_date);
                setResult(RESULT_OK,ix);
                finish();
            }
        });


    }
}