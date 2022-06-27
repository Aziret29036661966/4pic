package com.example.a4pic.ui.presenter;

import android.graphics.Color;

import com.example.a4pic.ui.Injector;
import com.example.a4pic.ui.model.UserModel;

public class Presenter {

    private UserModel user = Injector.getUserModel();
    private View view;


    public void space(String space){
        if (space.equals(user.getSpace())){
             view.toast();
        }else{
            view.wrongToast(Color.RED);
        }
    }

    public void poetry(String poetry){
        if (poetry.equals(user.getPoetry())){
            view.toast();
        }else{
            view.wrongToast(Color.RED);
        }
    }

    public void it(String it){
        if (it.equals(user.getIt())){
            view.toast();
        }else{
            view.wrongToast(Color.RED);
        }
    }

    public void attachView(View view){
        this.view = view;
    }

    public interface View{

        void toast();
        void wrongToast(int red);

    }

}
