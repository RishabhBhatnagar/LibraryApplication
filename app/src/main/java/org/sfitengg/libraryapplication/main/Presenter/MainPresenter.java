package org.sfitengg.libraryapplication.main.Presenter;

import org.sfitengg.libraryapplication.main.View.MainViewInterface;

public class MainPresenter implements MainPresenterInterface {
    private MainViewInterface mainView;

    private MainPresenter(MainViewInterface mainView){
        this.mainView = mainView;
    }

}
