package io.pivotal.todolistandroid.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.pivotal.todolistandroid.R;
import io.pivotal.todolistandroid.adapter.TaskListAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_view)
    RecyclerView taskListRecyclerView;

    @BindView(R.id.add_task)
    FloatingActionButton addTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, R.layout.activity_main, null);
        setContentView(view);
        ButterKnife.bind(this);

        final TaskListAdapter adapter = new TaskListAdapter(new ArrayList<String>());
        taskListRecyclerView.setAdapter(adapter);
        taskListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addTask("New Task");
            }
        });
    }

    public RecyclerView getTaskListRecyclerView() {
        return taskListRecyclerView;
    }

    public FloatingActionButton getAddTaskButton() {
        return addTaskButton;
    }
}
