package com.changhongit.guanhutong3.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.changhongit.guanhutong3.utils.spdata.Account;

/**
 * 未完成
 */
public class SpUtils {

    public static SpUtils instance = null;

    public static SpUtils getinstance() {
        if (instance == null)
            instance = new SpUtils();
        return instance;
    }

    /**
     * 勾选保存账号密码时调用(安全性欠妥)
     */
    public void setAccount(Context context, Account acc) {

        SharedPreferences sp = context.getSharedPreferences("account", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("username", acc.getUserName());
        editor.putString("password", acc.getPassword());

        editor.commit();
    }

    public Account getAccount(Context context) {
    	Account account;
        SharedPreferences sp = context.getSharedPreferences("account", Activity.MODE_PRIVATE);
        account = new Account(sp.getString("username", ""), sp.getString("password", ""));
        return account;
    }
    
    
}
