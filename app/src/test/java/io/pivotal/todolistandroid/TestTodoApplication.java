package io.pivotal.todolistandroid;

public class TestTodoApplication extends TodoApplication {
    @Override
    protected void buildComponent() {
        applicationComponent = DaggerTestApplicationComponent.builder().testApplicationModule(new TestApplicationModule()).build();
    }
}
