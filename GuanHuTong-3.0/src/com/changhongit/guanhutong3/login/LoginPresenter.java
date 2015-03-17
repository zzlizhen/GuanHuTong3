package com.changhongit.guanhutong3.login;

import android.content.Context;

import com.changhongit.guanhutong3.utils.spdata.Account;

public interface LoginPresenter {
    public void validateCredentials(String username, String password);

    public Account loadaccount(Context context);

    public void saveaccount(Context context, String username, String password);
}
