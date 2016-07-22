package io.pivotal.todolistandroid;

import javax.inject.Singleton;

import dagger.Component;
import io.pivotal.todolistandroid.activity.MainActivityTest;

@Singleton
@Component(modules = TestApplicationModule.class )
public interface TestApplicationComponent extends ApplicationComponent {
    void inject(MainActivityTest activity);
}
