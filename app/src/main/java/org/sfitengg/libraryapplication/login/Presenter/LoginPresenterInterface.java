package org.sfitengg.libraryapplication.login.Presenter;

public interface LoginPresenterInterface {
    void onLogin(int pid, String password);
    int forgotPassword(int pid);
}