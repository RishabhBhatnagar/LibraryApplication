package org.sfitengg.libraryapplication.main.Presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.navigation_fragments.IssuedBooksFragment;

import java.util.List;

import static android.speech.SpeechRecognizer.ERROR_SERVER;
import static org.sfitengg.libraryapplication.main.Presenter.GoGoGadget.ERROR_SERVER_UNREACHABLE;

public class MainActivity extends AppCompatActivity implements MyCallback {

    // Main Page where login Form is present
    static String urlMainPage = "http://115.248.171.105:82/webopac/";

    // Complete url to the form action attribute
    // where we send a POST
    static String urlLoginFormAction = urlMainPage + "opac.asp?m_firsttime=Y&m_memchk_flg=T";

    // Url of docs page
    static String urlOutDocsPage = "http://115.248.171.105:82/webopac/l_renew.asp";

    // Url where reissue form is sent
    // static String urlOutDocsFormAction = l_renew1.asp;

    Bundle bundleURLs;

    static String pid = "171001";
    static String pwd = "171001";

    TextView tv;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boolean testing = true;

        if (testing) {
            urlMainPage = " http://192.168.1.66:5000/";
            urlLoginFormAction = urlMainPage + "userpage";
            urlOutDocsPage = urlMainPage + "out_docs";
            pid = "4";
            pwd = "4";
        }

        // Create a bundle to pass in the URLs to the GoGoGadget object
        bundleURLs = new Bundle();
        bundleURLs.putString(GoGoGadget.keyMainPage, urlMainPage);
        bundleURLs.putString(GoGoGadget.keyLoginForm, urlLoginFormAction);
        bundleURLs.putString(GoGoGadget.keyOutDocs, urlOutDocsPage);


        // First login
        final GoGoGadget goGoGadget = new GoGoGadget((MyCallback) this,
                bundleURLs,
                GoGoGadget.LOGIN_AND_GET_COOKIES,
                handler);
        new Thread(goGoGadget).start();



    }



    @Override
    public void sendBooksToCaller(List<Book> books) {

        /*IssuedBooksFragment.setArgs("books",books);*/
        IssuedBooksFragment f = new IssuedBooksFragment();
        Bundle args = new Bundle();
        args.putList("books",books);
        f.setArguments(args);
        tv.setText(books.toString());

    }

    @Override
    public void sendStudentNameToCaller(String name) {
        tv.setText(name);

    }

    @Override
    public void passErrorsToCaller(int errorCode) {
        // TODO: Error handling here
        // Check the errorCode against the GoGoGadget constants
        IssuedBooksFragment f = new IssuedBooksFragment();
        Bundle args = new Bundle();
        args.put //Insert the below error//
        f.setArguments(args);
        /*IssuedBooksFragment.sendargs(ERROR_SERVER_UNREACHABLE);*/
        tv.setText("ERRORCODE: " + errorCode);
    }

    @Override
    public String getPid() {
        return pid;
    }

    @Override
    public String getPwd() {
        return pwd;
    }

    @Override
    public boolean isConnectedToInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    @Override
    public void userHasBorrowedNoBooks() {
        tv.setText("User has borrowed no books");
    }
}

