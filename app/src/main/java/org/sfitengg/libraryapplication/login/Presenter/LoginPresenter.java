package org.sfitengg.libraryapplication.login.Presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.sfitengg.libraryapplication.login.Model.LoginModel;
import org.sfitengg.libraryapplication.login.View.LoginViewInterface;

public class LoginPresenter implements LoginPresenterInterface {
    private LoginViewInterface loginView;

    public LoginPresenter(LoginViewInterface loginView){
        this.loginView = loginView;
    }

    @Override
    public void onLogin(int pid, String password) {
        LoginModel loginModel = new LoginModel(pid, password);
        boolean isLoginSuccessful = loginModel.validatePid(pid);

        if(isLoginSuccessful){
            loginView.onLogin("Success");
            ((Activity)loginView).finish();
        }
        else{
            loginView.onLogin("Failure");
        }
    }

    @Override
    public int forgotPassword() {
        loginView.forgotPassword();
        return 0;
    }

    @Override
    public void handleEmptyFields(String editText) {
        loginView.handleEmptyFields(editText);
    }
}
