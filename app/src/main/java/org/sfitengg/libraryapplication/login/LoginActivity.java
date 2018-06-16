package org.sfitengg.libraryapplication.login;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.login.Presenter.LoginPresenter;
import org.sfitengg.libraryapplication.login.View.LoginViewInterface;

public class LoginActivity extends AppCompatActivity implements LoginViewInterface {

    //gui components:
    private EditText pid_field;
    private EditText password_field;
    private Button login_button;
    private TextView forgot_password;
    private ConstraintLayout constraintLayout;

    //variables:
    private boolean themeChanged;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pid_field = findViewById(R.id.pid_field);
        password_field = findViewById(R.id.password_field);
        login_button = findViewById(R.id.login);
        forgot_password = findViewById(R.id.forgot_password);
        constraintLayout = findViewById(R.id.constrainLayout);
        themeChanged = false;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final LoginPresenter loginPresenter = new LoginPresenter(this);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    try{
                        int pid = Integer.parseInt(pid_field.getText().toString());
                        loginPresenter.onLogin(
                                Integer.parseInt(pid_field.getText().toString()),
                                password_field.getText().toString());
                    }
                    catch(Exception e){
                        //PID field empty.
                        loginPresenter.handleEmptyFields("PID field");
                    }
                }
                catch(Exception e){}
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.forgotPassword();
            }
        });


    }

    @Override
    public void onLogin(String message) {
        if(message == "Success"){
            //goto another page
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        }
        else{
            //prompt again
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void forgotPassword() {
        Toast.makeText(this, "forgot password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleEmptyFields(String editText) {
        //Toast.makeText(this, editText+" cannot be empty.", Toast.LENGTH_SHORT).show();
        Snackbar empty_field_alert = Snackbar.make(constraintLayout, editText+" cannot be empty.", Snackbar.LENGTH_SHORT);
        empty_field_alert.show();
    }
}