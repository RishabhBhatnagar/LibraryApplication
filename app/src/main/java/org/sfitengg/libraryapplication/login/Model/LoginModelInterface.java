package org.sfitengg.libraryapplication.login.Model;

public interface LoginModelInterface{
    boolean validatePid(int pid);
    boolean checkPassword(String pid, String password);

}