package io.pivotal.todolistandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Arrays;

import io.pivotal.todolistandroid.BuildConfig;

import static org.assertj.android.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class TaskListAdapterTest {

    @Test
    public void onCreateAndBindViewHolder_bindsATextView() {
        TaskListAdapter taskListAdapter = new TaskListAdapter(Arrays.asList("Task 1", "Task 2"));
        TaskListAdapter.TaskViewHolder viewHolder = (TaskListAdapter.TaskViewHolder) taskListAdapter.onCreateViewHolder(new LinearLayout(RuntimeEnvironment.application), 0);
        taskListAdapter.onBindViewHolder(viewHolder, 0);
        TextView textView1 = viewHolder.getTextView();
        assertThat(textView1).hasText("Task 1");

        taskListAdapter.onBindViewHolder(viewHolder, 1);
        TextView textView2 = viewHolder.getTextView();
        assertThat(textView2).hasText("Task 2");

    }

    @Test
    public void addTask_addsTheTaskToTheTasks() {
        TaskListAdapter taskListAdapter = new TaskListAdapter(new ArrayList<String>());
        taskListAdapter.addTask("New Task");
        assertThat(taskListAdapter.getItemCount()).isEqualTo(1);
        assertThat(taskListAdapter.getTasks().get(0)).isEqualTo("New Task");
    }

}