package io.pivotal.todolistandroid.task;

import android.app.Activity;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.UserRecoverableAuthException;

import org.junit.Test;

import java.io.IOException;

import io.pivotal.todolistandroid.googlelogin.GoogleAuthUtilClient;
import io.pivotal.todolistandroid.activity.MainActivity;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetUsernameTaskTest {

    @Test
    public void doInBackground_setsTheToken() throws Exception {
        MainActivity activity = mock(MainActivity.class);
        GoogleAuthUtilClient googleAuthUtilClient = mock(GoogleAuthUtilClient.class);
        when(googleAuthUtilClient.getToken(any(Activity.class), any(String.class), any(String.class))).thenReturn("the_token");
        GetUsernameTask getUsernameTask = new GetUsernameTask(activity, "user@example.com", "scope", googleAuthUtilClient);
        getUsernameTask.doInBackground();

        verify(activity).setToken("the_token");
    }

    @Test(expected = RuntimeException.class)
    public void doInBackground_throwsWhenIOExceptionHappens() throws Exception {
        MainActivity activity = mock(MainActivity.class);
        GoogleAuthUtilClient googleAuthUtilClient = mock(GoogleAuthUtilClient.class);
        when(googleAuthUtilClient.getToken(any(Activity.class), any(String.class), any(String.class)))
                .thenThrow(new IOException());
        GetUsernameTask getUsernameTask = new GetUsernameTask(activity, "user@example.com", "scope", googleAuthUtilClient);
        getUsernameTask.doInBackground();
    }

    @Test(expected = RuntimeException.class)
    public void doInBackground_throwsWhenGoogleAuthExceptionHappens() throws Exception {
        MainActivity activity = mock(MainActivity.class);
        GoogleAuthUtilClient googleAuthUtilClient = mock(GoogleAuthUtilClient.class);
        when(googleAuthUtilClient.getToken(any(Activity.class), any(String.class), any(String.class)))
                .thenThrow(new GoogleAuthException());
        GetUsernameTask getUsernameTask = new GetUsernameTask(activity, "user@example.com", "scope", googleAuthUtilClient);
        getUsernameTask.doInBackground();
    }

    @Test
    public void doInBackground_recoversFromUserRecoverableAuthException() throws Exception {
        MainActivity activity = mock(MainActivity.class);
        GoogleAuthUtilClient googleAuthUtilClient = mock(GoogleAuthUtilClient.class);
        UserRecoverableAuthException exception = mock(UserRecoverableAuthException.class);
        when(googleAuthUtilClient.getToken(any(Activity.class), any(String.class), any(String.class)))
                .thenThrow(exception);
        GetUsernameTask getUsernameTask = new GetUsernameTask(activity, "user@example.com", "scope", googleAuthUtilClient);
        getUsernameTask.doInBackground();

        verify(activity).handleException(exception);
    }
}