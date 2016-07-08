package io.pivotal.todolistandroid.googlelogin;

import android.app.Activity;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;

import java.io.IOException;

public class GoogleAuthUtilClient {
    public String getToken(Activity activity, String email, String scope) throws IOException, GoogleAuthException {
        return GoogleAuthUtil.getToken(activity, email, scope);
    }
}
