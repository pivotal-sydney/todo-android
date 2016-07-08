package io.pivotal.todolistandroid.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import io.pivotal.todolistandroid.BuildConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class MainActivityTest {

    private MainActivity subject;

    @Before
    public void setup() {
        subject = Robolectric.setupActivity(MainActivity.class);
        subject.adapter = spy(subject.adapter);
    }

    @Test
    public void activity_hasListOfTasks() {
        RecyclerView recyclerView = subject.getTaskListRecyclerView();
        assertThat(recyclerView.getLayoutManager()).isOfAnyClassIn(LinearLayoutManager.class);
        assertThat(recyclerView.getAdapter().getItemCount()).isEqualTo(0);
    }

    @Test
    public void addTask_addsTheCurrentTextValueToAdapterItems() {
        RecyclerView recyclerView = subject.getTaskListRecyclerView();
        assertThat(recyclerView.getLayoutManager()).isOfAnyClassIn(LinearLayoutManager.class);
        assertThat(recyclerView.getAdapter().getItemCount()).isEqualTo(0);

        subject.getInput().setText("A New Task");
        subject.getAddTaskButton().callOnClick();
        assertThat(recyclerView.getAdapter().getItemCount()).isEqualTo(1);
        assertThat(subject.getInput().getText()).isEmpty();

        verify(subject.adapter).addTask(eq("A New Task"));
    }
}