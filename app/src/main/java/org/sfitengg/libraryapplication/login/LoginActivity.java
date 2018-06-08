package org.sfitengg.libraryapplication.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.login.Presenter.LoginPresenter;
import org.sfitengg.libraryapplication.login.View.LoginViewInterface;

public class LoginActivity extends AppCompatActivity implements LoginViewInterface {

    private TextView pid_field;
    private TextView password_field;
    private Button login_button;
    private Button forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pid_field = findViewById(R.id.pid_field);
        password_field = findViewById(R.id.password_field);
        login_button = findViewById(R.id.login);
        forgot_password = findViewById(R.id.forgot_password);

        final LoginPresenter loginPresenter = new LoginPresenter(this);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onLogin(
                        Integer.parseInt(pid_field.getText().toString()),
                        password_field.getText().toString());
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.forgotPassword(Integer.parseInt(pid_field.getText().toString()));
            }
        });
    }

    @Override
    public void onLogin(String message) {
        if(message == "Success"){
            //goto another page
            Toast.makeText(this, "Login SuccessFul", Toast.LENGTH_SHORT).show();
        }
        else{
            //prompt again
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void forgotPassword(String message) {
        //I don't know. I am feeling sleepy.
    }
}