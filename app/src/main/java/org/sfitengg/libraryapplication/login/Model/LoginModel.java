package org.sfitengg.libraryapplication.login.Model;


import org.sfitengg.libraryapplication.main.Presenter.GoGoGadget;
import org.sfitengg.libraryapplication.main.MainActivity;

public class LoginModel implements LoginModelInterface {

    String password;
    static int pid;

    public LoginModel(int pid, String password){
        this.pid = pid;
        this.password = password;
    }

    @Override
    public boolean validatePid(int pid) {
        if(String.valueOf(pid).length() == 6){
            return true;
        }
        else return false;
    }

    @Override
    public boolean checkPassword(String pidA, String passwordA) {
        //check password from website
        MainActivity.pid=pidA;
        MainActivity.pwd=passwordA;
        if(GoGoGadget.resultCode1==GoGoGadget.ERROR_INCORRECT_PID_OR_PASSWORD)
        {
            return false;
        }
        return true;

    }
}
