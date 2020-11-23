package com.example.redo_todo;

import android.graphics.Paint;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {


    //List <String> itemlist;
    List <Task> itemlist;
    BigDogLongPressed bdlp;

    public interface BigDogLongPressed{
        void longpressed(int VHposition);
    }


    public TaskAdapter(List<Task> itemlist,BigDogLongPressed bdlp) {
        this.itemlist = itemlist;
        this.bdlp=bdlp;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View taskView = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkbox_item, parent,false);
        return new ViewHolder(taskView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task singleItem = itemlist.get(position);
        holder.bind(singleItem);
    }


    @Override
    public int getItemCount() {
        return itemlist.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate;
        CheckBox cbTask;
        RelativeLayout rlTask;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //tvItem=itemView.findViewById(android.R.id.text1);
            cbTask=itemView.findViewById(R.id.cbTask);
            tvDate=itemView.findViewById(R.id.tvDate);
            rlTask=itemView.findViewById(R.id.rlTask);

            rlTask.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    bdlp.longpressed(getAdapterPosition());
                    Log.i("TaskActivity", "onLongClick:xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx "+ getAdapterPosition());
                    return true;
                }
            });
            rlTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("TaskActivity", "onLongClick:yyyyyyyyyyyyyyyyyyyyyyy "+ getAdapterPosition());
                    return;
                }
            });


        }


        public void bind(Task singleItem) {
            //String taskPlusDate= singleItem.getTaskName()+"*****"+singleItem.getDueInString();

            //todo: why is it not listening to the whole container?
            cbTask.setText(singleItem.getTaskName());
            tvDate.setText(singleItem.getDueInString());

            cbTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        cbTask.setPaintFlags(cbTask.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    }else{
                        cbTask.setPaintFlags(cbTask.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                }
            });
        }



    }
}
