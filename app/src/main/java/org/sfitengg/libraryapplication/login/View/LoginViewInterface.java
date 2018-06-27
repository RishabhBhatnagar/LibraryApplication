package org.sfitengg.libraryapplication.login.View;

import android.widget.EditText;

public interface LoginViewInterface {
    void onLogin(String message);
    void forgotPassword();
    void handleEmptyFields(String editText);
}