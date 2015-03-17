package com.changhongit.guanhutong3.login;

import android.content.Context;

import com.changhongit.guanhutong3.utils.spdata.Account;

public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        loginView.showProgress();
        loginInteractor.login(username, password, this);
    }

    @Override
    public Account loadaccount(Context context) {
        // TODO Auto-generated method stub
        return loginInteractor.getaccount(context);
    }

    @Override
    public void saveaccount(Context context, String username, String password) {
        // TODO Auto-generated method stub
        loginInteractor.saveaccount(context, username, password);
    }

    @Override
    public void onUsernameError() {
        loginView.setUsernameError();
        loginView.hideProgress();
    }

    @Override
    public void onPasswordError() {
        loginView.setPasswordError();
        loginView.hideProgress();
    }

    @Override
    public void onSuccess() {
        loginView.navigateToHome();
        loginView.hideProgress();
    }

    @Override
    public void onNetWorkError() {
        // TODO Auto-generated method stub
        loginView.setNetWorkError();
        loginView.hideProgress();
    }


}
