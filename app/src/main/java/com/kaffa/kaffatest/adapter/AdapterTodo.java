package com.kaffa.kaffatest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kaffa.kaffatest.R;
import com.kaffa.kaffatest.model.TODOTask;

import java.util.List;

public class AdapterTodo extends RecyclerView.Adapter<AdapterTodo.MyViewHolder> {

    private List<TODOTask> listTodo;
    private Context context;

    public AdapterTodo(List<TODOTask> listTodo, Context context) {
        this.listTodo = listTodo;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_todo, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TODOTask todoTask = listTodo.get(position);
        holder.name.setText(todoTask.getName());
        holder.date.setText(todoTask.getDate());
        holder.todo.setText(todoTask.getTodo());
    }

    @Override
    public int getItemCount() {
        return listTodo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, date, todo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textName);
            date = itemView.findViewById(R.id.textDate);
            todo = itemView.findViewById(R.id.textTodo);
        }
    }
}
