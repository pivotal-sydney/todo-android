package io.pivotal.todolistandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import io.pivotal.todolistandroid.BuildConfig;

import static org.assertj.android.api.Assertions.assertThat;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class TaskListAdapterTest {

    @Test
    public void onCreateAndBindViewHolder_bindsATextView() {
        TaskListAdapter taskListAdapter = new TaskListAdapter();
        TaskListAdapter.TaskViewHolder viewHolder = (TaskListAdapter.TaskViewHolder) taskListAdapter.onCreateViewHolder(new LinearLayout(RuntimeEnvironment.application), 0);
        taskListAdapter.onBindViewHolder(viewHolder, 0);
        TextView textView = viewHolder.getTextView();
        assertThat(textView).hasText("Task");

    }

}