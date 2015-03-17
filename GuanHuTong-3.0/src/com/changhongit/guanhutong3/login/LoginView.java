package com.changhongit.guanhutong3.login;

public interface LoginView {
    public void showProgress();

    public void hideProgress();

    public void setUsernameError();

    public void setPasswordError();
    
    public void setNetWorkError();

    public void navigateToHome();
}
