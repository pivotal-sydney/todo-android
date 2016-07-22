package io.pivotal.todolistandroid;

import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.pivotal.todolistandroid.model.Task;
import io.pivotal.todolistandroid.model.Tasks;
import retrofit2.mock.Calls;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
public class TestApplicationModule {

    @Provides
    @Singleton
    public TodoRestService mockTodoRestService() {
        TodoRestService todoRestService = mock(TodoRestService.class);
        Tasks tasks = new Tasks();
        Task task = new Task();
        task.description = "Hello World";
        task.id = 1;
        List<Task> taskList = Arrays.asList(task);
        tasks.tasks = taskList;
        when(todoRestService.getTasks()).thenReturn(Calls.response(tasks));
        return todoRestService;
    }
}
