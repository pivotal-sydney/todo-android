package io.pivotal.todolistandroid;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public TodoRestService providesTodoRestService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://todo-rails.cfapps.io")
                .build();
        return retrofit.create(TodoRestService.class);
    }
}
