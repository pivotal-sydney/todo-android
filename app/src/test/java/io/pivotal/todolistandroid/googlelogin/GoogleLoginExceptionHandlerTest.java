package io.pivotal.todolistandroid.googlelogin;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.auth.UserRecoverableAuthException;

import org.junit.Test;

import io.pivotal.todolistandroid.googlelogin.GoogleLoginExceptionHandler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GoogleLoginExceptionHandlerTest {

    @Test
    public void testRun_recoversFromUserRecoverableAuthException() throws Exception {
        Activity activity = mock(Activity.class);
        UserRecoverableAuthException exception = mock(UserRecoverableAuthException.class);

        Intent intent = new Intent();
        when(exception.getIntent()).thenReturn(intent);

        GoogleLoginExceptionHandler handler = new GoogleLoginExceptionHandler(activity, exception);
        handler.run();

        verify(activity).startActivityForResult(intent, 1001);
    }

    @Test(expected = RuntimeException.class)
    public void testRun_throwsForOtherException() throws Exception {
        Activity activity = mock(Activity.class);
        Exception exception = new Exception();

        GoogleLoginExceptionHandler handler = new GoogleLoginExceptionHandler(activity, exception);
        handler.run();
    }
}