package com.firstapp.taskmaster;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// adapter role : convert an object at a position into a list row item
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
       private List<TaskModel> tasks =new ArrayList<TaskModel>();;
        private OnItemClickListener mlistener;

       public interface OnItemClickListener{
           void onItemClick(int position);
       }

       public void setOnItemClickListener(OnItemClickListener listener){
           mlistener = listener;
       }
    // act as a container for data that i will be using later
    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;

        public ViewHolder(View view){
            super(view);  // // parent class need view to do extra stuff
            textView = (TextView) view.findViewById(R.id.titleRV);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){

                    if( mlistener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mlistener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public View getTextView() {
            return textView;
        }
    }
    // Initialize the dataset of the Adapter.
    public TaskAdapter(List<TaskModel> list){
          tasks = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // new view to define UI
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( TaskAdapter.ViewHolder holder, int position) {
//       holder.getTextView().setText(titles[position]);
        String task =   "Title: "+ tasks.get(position).getTitle() + "\n" +
                        "Body: "+ tasks.get(position).getBody() + "\n" +
                        "Status: " + tasks.get(position).getState();
        holder.textView.setText(task);
    }

    @Override
    public int getItemCount() {
        return  tasks.size();
    }
}
