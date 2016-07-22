package io.pivotal.todolistandroid;

import io.pivotal.todolistandroid.model.Tasks;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoRestService {
    @GET("/api/v1/tasks")
    Call<Tasks> getTasks();
}
