package org.sfitengg.libraryapplication.login.Presenter;

import android.widget.EditText;

public interface LoginPresenterInterface {
    void onLogin(int pid, String password);
    int forgotPassword();
    void handleEmptyFields(String editText);
}