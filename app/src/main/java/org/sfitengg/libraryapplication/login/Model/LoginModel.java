package org.sfitengg.libraryapplication.login.Model;


import org.sfitengg.libraryapplication.main.Presenter.MainActivity;

public class LoginModel implements LoginModelInterface {

    String password;
    static int pid;
    MainActivity m = new MainActivity();


    public LoginModel(int pid, String password) {
        this.pid = pid;
        this.password = password;
    }

    @Override
    public boolean validatePid(int pid) {
        if (String.valueOf(pid).length() == 6) {
            return true;
        } else return false;
    }

    @Override
    public boolean checkPassword(String pid, String password) {
        //check password from website


//need to discuss before proceeding with this code which is obviosly wrong lol
      /* if((m.getPid()==pid )&&(m.getPwd()==password)){
        return true;
        }
    }*/
        return true;
    }
}