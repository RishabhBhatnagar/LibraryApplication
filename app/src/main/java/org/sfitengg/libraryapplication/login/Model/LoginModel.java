package org.sfitengg.libraryapplication.login.Model;


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
    public boolean checkPassword(String password) {
        //check password from database
        return true;
    }
}
