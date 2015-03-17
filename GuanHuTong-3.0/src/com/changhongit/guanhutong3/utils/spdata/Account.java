package com.changhongit.guanhutong3.utils.spdata;

/**
 * 账户密码的信息
 */
public class Account {

    private String username = "";
    private String password = "";

    public Account(String username, String password) {
            this.username = username;
            this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
