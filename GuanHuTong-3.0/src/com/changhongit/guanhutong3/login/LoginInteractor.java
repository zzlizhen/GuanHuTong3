package com.changhongit.guanhutong3.login;

import android.content.Context;

import com.changhongit.guanhutong3.utils.spdata.Account;

public interface LoginInteractor {
    public void login(String username, String password, OnLoginFinishedListener listener);

    public Account getaccount(Context context);

    public void saveaccount(Context context, String username, String password);

}
