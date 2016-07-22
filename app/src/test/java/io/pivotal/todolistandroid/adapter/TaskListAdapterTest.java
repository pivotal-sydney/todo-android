package io.pivotal.todolistandroid.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;

import java.util.ArrayList;
import java.util.Arrays;

import io.pivotal.todolistandroid.BuildConfig;
import io.pivotal.todolistandroid.R;

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
        taskListAdapter.addTask("This is a New Task");
        assertThat(taskListAdapter.getItemCount()).isEqualTo(1);
        assertThat(taskListAdapter.getTasks().get(0)).isEqualTo("This is a New Task");
    }

    @Test
    public void editTask_shouldDisplayDialog() {
        TaskListAdapter taskListAdapter = new TaskListAdapter(Arrays.asList("Task 1", "Task 2"));
        TaskListAdapter.TaskViewHolder viewHolder = (TaskListAdapter.TaskViewHolder) taskListAdapter.onCreateViewHolder(new LinearLayout(RuntimeEnvironment.application), 0);
        taskListAdapter.onBindViewHolder(viewHolder, 0);

        viewHolder.textView.performClick();

        AlertDialog latestAlertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertThat(latestAlertDialog).isNotNull();

        EditText editTask = (EditText) latestAlertDialog.findViewById(R.id.edit_task);
        assertThat(editTask).hasText("Task 1");

        Button saveButton = latestAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE);

        assertThat(saveButton).hasText("Save");

        String newTask = "Say hello to people";

        editTask.setText(newTask);
        saveButton.performClick();

        // Mimic notifyDataSetChanged by rebinding
        taskListAdapter.onBindViewHolder(viewHolder, 0);
        assertThat(viewHolder.textView).hasText(newTask);
        assertThat(taskListAdapter.getTasks().get(0)).isEqualTo(newTask);
    }

}