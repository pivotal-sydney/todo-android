package io.pivotal.todolistandroid.activity;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.common.AccountPicker;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.pivotal.todolistandroid.googlelogin.GoogleAuthUtilClient;
import io.pivotal.todolistandroid.googlelogin.GoogleLoginExceptionHandler;
import io.pivotal.todolistandroid.R;
import io.pivotal.todolistandroid.adapter.TaskListAdapter;
import io.pivotal.todolistandroid.task.GetUsernameTask;

public class MainActivity extends AppCompatActivity {

    String SCOPE = "oauth2:https://www.googleapis.com/auth/userinfo.email";
    static final int REQUEST_CODE_PICK_ACCOUNT = 1000;

    @BindView(R.id.list_view)
    RecyclerView taskListRecyclerView;

    @BindView(R.id.add_task)
    FloatingActionButton addTaskButton;

    @BindView(R.id.input)
    EditText input;

    @BindView(R.id.login_button)
    FloatingActionButton loginButton;

    TaskListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, R.layout.activity_main, null);
        setContentView(view);
        ButterKnife.bind(this);

        adapter = new TaskListAdapter(new ArrayList<String>());
        taskListRecyclerView.setAdapter(adapter);
        taskListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addTask(input.getText().toString());
                input.getText().clear();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] accountTypes = new String[]{"com.google"};
                Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                        accountTypes, false, null, null, null, null);
                startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_ACCOUNT) {
            new GetUsernameTask(this, data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME), SCOPE, new GoogleAuthUtilClient())
                    .execute();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public RecyclerView getTaskListRecyclerView() {
        return taskListRecyclerView;
    }

    public void setToken(String token) {
        System.out.println("token = " + token);
    }

    public void handleException(final Exception e) {
        runOnUiThread(new GoogleLoginExceptionHandler(this, e));
    }

    public EditText getInput() {
        return input;
    }

}
