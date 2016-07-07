package io.pivotal.todolistandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.pivotal.todolistandroid.R;

public class TaskListAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskViewHolder(View.inflate(parent.getContext(), R.layout.task, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TaskViewHolder taskViewHolder = (TaskViewHolder)holder;
        taskViewHolder.textView.setText("Task");
    }

    @Override
    public int getItemCount() {
        return 2;
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
