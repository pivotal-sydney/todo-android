package io.pivotal.todolistandroid.googlelogin;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.auth.UserRecoverableAuthException;

public class GoogleLoginExceptionHandler implements Runnable {

    private static final int REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR = 1001;

    private Activity activity;
    private Exception exception;

    public GoogleLoginExceptionHandler(Activity activity, Exception exception) {
        this.activity = activity;
        this.exception = exception;
    }

    @Override
    public void run() {
        if (exception instanceof UserRecoverableAuthException) {
            Intent intent = ((UserRecoverableAuthException) exception).getIntent();
            activity.startActivityForResult(intent,
                    REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR);
        } else {
            throw new RuntimeException(exception);
        }
    }
}
