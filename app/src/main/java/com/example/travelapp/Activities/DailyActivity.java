package com.example.travelapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelapp.Adapter.TaskAdapter;
import com.example.travelapp.Classes.Task;
import com.example.travelapp.Fragments.ScheduleFragment;
import com.example.travelapp.R;
import com.example.travelapp.TaskDBHelper;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyActivity extends AppCompatActivity {

    public static TaskDBHelper db_helper_instance;
    public static List<Task> task_collection;
    public static TaskAdapter ta_intance;

    Button btnBack, btnAdd;
    EditText etAddTask;
    TextView tvDaily;
    RecyclerView rvDaily;
    TaskAdapter.TaskLongPress tatlp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        btnAdd=findViewById(R.id.btnAdd);
        btnBack=findViewById(R.id.btnBack);
        etAddTask=findViewById(R.id.etAddTask);
        tvDaily=findViewById(R.id.tvDaily);
        rvDaily=findViewById(R.id.rvDaily);

        //formating date: long -> date -> string
        final long selected_date= getIntent().getExtras().getLong(ScheduleFragment.SELECTED_DATE);
        Date in_date= new Date(selected_date);
        Format df = new SimpleDateFormat("yyyy-MM-dd");
        final String date_in_string= df.format(in_date);
        tvDaily.setText(date_in_string);


        tatlp= new TaskAdapter.TaskLongPress() {
            @Override
            public void longPressPosition(int pos) {
                //remove task by id from database.
                db_helper_instance.removeTask(task_collection.get(pos));
                //reflect changes on app
                task_collection.remove(task_collection.get(pos));
                ta_intance.notifyDataSetChanged();
            }
        };

        db_helper_instance=new TaskDBHelper(DailyActivity.this);
        //populate tasks item by date.
        task_collection= new ArrayList<>();
        task_collection=db_helper_instance.getTaskByDate(date_in_string);
        //set up adapter.
        ta_intance=new TaskAdapter(task_collection,DailyActivity.this,tatlp);
        rvDaily.setAdapter(ta_intance);
        rvDaily.setLayoutManager(new LinearLayoutManager(DailyActivity.this));



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //going back to main calendar
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adding or writing new task to SQL Database.
                //1) create the task
                //2) ask db_helper instance to add the task item into the db.
                String taskName=etAddTask.getText().toString();
                Task add_task;
                if (taskName.isEmpty()){
                    Toast.makeText(DailyActivity.this, "Task Content Cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    try {
                        add_task = new Task(taskName, date_in_string, -1);
                        Toast.makeText(DailyActivity.this, add_task.getStringDate(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        //Toast.makeText(DailyActivity.this, "Error creating new task", Toast.LENGTH_SHORT).show();
                        add_task = new Task("error", date_in_string, -1);
                    }

                    boolean success = db_helper_instance.addTask(add_task);

                    etAddTask.setText("");
                    //update the data in app.
                    task_collection.add(add_task);
                    ta_intance.notifyItemInserted(task_collection.size() - 1);
                    //ta_intance.notify();
                }
            }
        });

    }
}