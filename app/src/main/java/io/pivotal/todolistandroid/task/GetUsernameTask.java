package io.pivotal.todolistandroid.task;

import android.os.AsyncTask;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.UserRecoverableAuthException;

import java.io.IOException;

import io.pivotal.todolistandroid.googlelogin.GoogleAuthUtilClient;
import io.pivotal.todolistandroid.activity.MainActivity;

public class GetUsernameTask extends AsyncTask<Void, Void, Void> {
    MainActivity mActivity;
    String mScope;
    String mEmail;

    GoogleAuthUtilClient googleAuthUtilClient;

    public GetUsernameTask(MainActivity activity, String email, String scope, GoogleAuthUtilClient googleAuthUtilClient) {
        this.mActivity = activity;
        this.mScope = scope;
        this.mEmail = email;
        this.googleAuthUtilClient = googleAuthUtilClient;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            mActivity.setToken(googleAuthUtilClient.getToken(mActivity, mEmail, mScope));
        } catch (UserRecoverableAuthException userRecoverableException) {
            mActivity.handleException(userRecoverableException);
        } catch (GoogleAuthException | IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
