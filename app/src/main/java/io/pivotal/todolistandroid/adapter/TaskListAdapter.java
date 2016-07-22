package io.pivotal.todolistandroid.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.pivotal.todolistandroid.R;

public class TaskListAdapter extends RecyclerView.Adapter {

    private List<String> tasks;

    public TaskListAdapter(List<String> strings) {
        tasks = strings;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskViewHolder(View.inflate(parent.getContext(), R.layout.task, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final TaskViewHolder taskViewHolder = (TaskViewHolder) holder;
        taskViewHolder.textView.setText(tasks.get(position));
        taskViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View parent = View.inflate(v.getContext(), R.layout.view_editdialog, null);
                final EditText child = (EditText)parent.findViewById(R.id.edit_task);
                child.setText(taskViewHolder.textView.getText());

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tasks.set(position, child.getText().toString());
                        notifyDataSetChanged();
                    }
                });

                builder.setView(parent);
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void addTask(String task) {
        tasks.add(task);
        notifyDataSetChanged();
    }

    public List<String> getTasks() {
        return tasks;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        public TextView textView;

        public TaskViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
