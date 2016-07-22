package io.pivotal.todolistandroid;

import android.app.Application;

public class TodoApplication extends Application {

    protected ApplicationComponent applicationComponent;

    static TodoApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponent();
        application = this;
    }

    public static TodoApplication getApplication() {
        return application;
    }

    protected void buildComponent() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule()).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
