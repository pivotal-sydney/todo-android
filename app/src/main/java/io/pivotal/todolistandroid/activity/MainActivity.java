package io.pivotal.todolistandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.pivotal.todolistandroid.R;
import io.pivotal.todolistandroid.adapter.TaskListAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_view)
    RecyclerView taskListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, R.layout.activity_main, null);
        setContentView(view);
        ButterKnife.bind(this);

        taskListRecyclerView.setAdapter(new TaskListAdapter());
        taskListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public RecyclerView getTaskListRecyclerView() {
        return taskListRecyclerView;
    }
}
