package io.pivotal.todolistandroid.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import javax.inject.Inject;

import io.pivotal.todolistandroid.BuildConfig;
import io.pivotal.todolistandroid.TestApplicationComponent;
import io.pivotal.todolistandroid.TodoApplication;
import io.pivotal.todolistandroid.TodoRestService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class MainActivityTest {

    private MainActivity subject;

    @Inject
    TodoRestService mockTodoRestService;

    @Before
    public void setup() {
        subject = Robolectric.setupActivity(MainActivity.class);
        subject.adapter = spy(subject.adapter);

        ((TestApplicationComponent) TodoApplication.getApplication().getApplicationComponent()).inject(this);
    }

    @Test
    public void activity_hasListOfTasks() {
        RecyclerView recyclerView = subject.getTaskListRecyclerView();
        assertThat(recyclerView.getLayoutManager()).isOfAnyClassIn(LinearLayoutManager.class);
        assertThat(recyclerView.getAdapter().getItemCount()).isEqualTo(1);
    }

    @Test
    public void addTask_addsTheCurrentTextValueToAdapterItems() {
        RecyclerView recyclerView = subject.getTaskListRecyclerView();
        assertThat(recyclerView.getLayoutManager()).isOfAnyClassIn(LinearLayoutManager.class);
        assertThat(recyclerView.getAdapter().getItemCount()).isEqualTo(1);

        subject.getInput().setText("A New Task");
        subject.addTaskButton.performClick();
        assertThat(recyclerView.getAdapter().getItemCount()).isEqualTo(2);
        assertThat(subject.getInput().getText()).isEmpty();

        verify(subject.adapter).addTask(eq("A New Task"));
    }


    @Test
    public void login_addsTheCurrentTextValueToAdapterItems() {
        RecyclerView recyclerView = subject.getTaskListRecyclerView();
        assertThat(recyclerView.getLayoutManager()).isOfAnyClassIn(LinearLayoutManager.class);
        assertThat(recyclerView.getAdapter().getItemCount()).isEqualTo(1);

        subject.loginButton.performClick();
        ShadowActivity.IntentForResult intent = shadowOf(subject).getNextStartedActivityForResult();
        assertThat(intent.requestCode).isEqualTo(1000);
    }

    @Test
    public void onCreate_shouldFetchTasks_andUpdateAdapter() {
        assertThat(subject.todoRestService).isEqualTo(mockTodoRestService);
        verify(mockTodoRestService).getTasks();
        assertThat(subject.taskListRecyclerView.getAdapter().getItemCount()).isEqualTo(1);
    }
}