package com.example.a4pic.ui;

import com.example.a4pic.ui.model.UserModel;
import com.example.a4pic.ui.presenter.Presenter;

public class Injector {

    public static Presenter getPresenter(){
        return new Presenter();
    }

    public static UserModel getUserModel(){
        return new UserModel();
    }

}
