package io.pivotal.todolistandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.pivotal.todolistandroid.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
