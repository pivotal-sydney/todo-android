package io.pivotal.todolistandroid;

import javax.inject.Singleton;

import dagger.Component;
import io.pivotal.todolistandroid.activity.MainActivity;

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {
    void inject(MainActivity activity);
}
