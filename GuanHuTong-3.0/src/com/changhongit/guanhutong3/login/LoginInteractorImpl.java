package com.changhongit.guanhutong3.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.map.utils.DebugUtil;
import com.changhongit.guanhutong3.utils.HttpUtil;
import com.changhongit.guanhutong3.utils.SpUtils;
import com.changhongit.guanhutong3.utils.XMLMakerUtil;
import com.changhongit.guanhutong3.utils.XMLPullUtil;
import com.changhongit.guanhutong3.utils.spdata.Account;
import com.changhongit.guanhutong3.webservice.WebService;

public class LoginInteractorImpl implements LoginInteractor {
    public final int GET_DATA_OK = 0;
    public final int GET_DATA_ERROR = 1;
    public final int USERNAME_ERROR = 2;
    public final int PWD_ERROR = 3;
    OnLoginFinishedListener listener;

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
            case GET_DATA_OK:
                GhtApplication.mUserId = msg.getData().getString("result");
                
                listener.onSuccess();
                break;
            case GET_DATA_ERROR:
                listener.onNetWorkError();
                break;
            case USERNAME_ERROR:
                listener.onUsernameError();
                break;
            case PWD_ERROR:
                listener.onPasswordError();
                break;
            }

        };
    };

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        // TODO Auto-generated method stub
        this.listener = listener;
        new Thread() {
            public void run() {

                if (TextUtils.isEmpty(username)) {
                    handler.sendEmptyMessage(USERNAME_ERROR);
                }
                if (TextUtils.isEmpty(password)) {
                    handler.sendEmptyMessage(PWD_ERROR);
                } else {
                    String result = WebService.getInstance().postRequest(HttpUtil.HEADER + "login",
                            XMLMakerUtil.getInstance().login(username, password, "Abc1584"));
                    DebugUtil.Debug("result ==== " + result);
                    if (result != null) {
                        try {
                            Message msg = Message.obtain();
                            msg.what = GET_DATA_OK;
                            Bundle bundle = new Bundle();
                            bundle.putString("result", XMLPullUtil.getinstance().parseLogin(result));
                            msg.setData(bundle);
                            GhtApplication.mLoginUserName = username;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            handler.sendEmptyMessage(GET_DATA_ERROR);
                        }

                    } else {
                        handler.sendEmptyMessage(GET_DATA_ERROR);
                    }
                }

            };
        }.start();
    }

    @Override
    public Account getaccount(Context context) {
        // TODO Auto-generated method stub
        return SpUtils.getinstance().getAccount(context);
    }

    @Override
    public void saveaccount(Context context, String username, String password) {
        // TODO Auto-generated method stub
        SpUtils.getinstance().setAccount(context, new Account(username, password));
    }
}
