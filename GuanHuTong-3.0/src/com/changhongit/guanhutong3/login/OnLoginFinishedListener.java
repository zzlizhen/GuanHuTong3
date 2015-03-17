package com.changhongit.guanhutong3.login;

public interface OnLoginFinishedListener {

    public void onUsernameError();

    public void onPasswordError();

    public void onSuccess();
    
    public void onNetWorkError();
}
