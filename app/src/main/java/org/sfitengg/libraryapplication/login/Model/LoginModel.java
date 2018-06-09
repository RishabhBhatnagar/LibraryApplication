package org.sfitengg.libraryapplication.login.Model;


public class LoginModel implements LoginModelInterface {

    String password;
    int pid;

    public LoginModel(int pid, String password){
        this.pid = pid;
        this.password = password;
    }

    private String getPassword(){
        return password;
    }
    private int getPid(){
        return pid;
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
