package org.sfitengg.libraryapplication.login.Presenter;

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
        }
        else{
            loginView.onLogin("Failure");
        }
    }

    @Override
    public int forgotPassword(int pid) {
        return 0;
    }
}
