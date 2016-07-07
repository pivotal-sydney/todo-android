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

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class MainActivityTest {

    private MainActivity subject;

    @Before
    public void setup() {
        subject = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void activity_hasListOfTasks() {
        RecyclerView recyclerView = subject.getTaskListRecyclerView();
        assertThat(recyclerView.getLayoutManager()).isOfAnyClassIn(LinearLayoutManager.class);
        assertThat(recyclerView.getAdapter().getItemCount()).isEqualTo(2);
    }
}