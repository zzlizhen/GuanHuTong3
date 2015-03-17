package com.changhongit.guanhutong3.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.main.MainActivity;
import com.changhongit.guanhutong3.map.utils.DebugUtil;
import com.changhongit.guanhutong3.utils.spdata.Account;

public class LoginActivity extends BaseActivity implements OnClickListener, OnCheckedChangeListener, LoginView {
    private EditText mUserNameEt, mPwdEt;
//    private CheckBox mSaveCb;
    private LoginPresenter presenter;
    private ImageView mPsd_del_iv,mUserName_del_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenterImpl(this);
        initView();
        loadaccount();  // 老逻辑自动设置用户名和密码
//        autoLogin();    //新逻辑自动登录
    }

    private void initView() {
        mUserNameEt = (EditText) findViewById(R.id.et_user_name);
        mPwdEt = (EditText) findViewById(R.id.et_pwd);
        ((Button) findViewById(R.id.btn_login)).setOnClickListener(this);
        mPsd_del_iv = (ImageView) findViewById(R.id.del_username_iv);
        mUserName_del_iv = (ImageView) findViewById(R.id.del_psd_iv);
        mPsd_del_iv.setOnClickListener(this);
        mUserName_del_iv.setOnClickListener(this);
        mUserNameEt.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus){
					mUserName_del_iv.setVisibility(View.GONE);
				}
				else{
					mUserName_del_iv.setVisibility(View.VISIBLE);
				}
			}
		});
        
        mPwdEt.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus){
					mPsd_del_iv.setVisibility(View.GONE);
				}
				else{
					
					mPsd_del_iv.setVisibility(View.VISIBLE);
				}
			}
		});
    }

    private void saveaccount() { // 作用是改变保存账户密码的状态，包括是否保存，用户名和密码
        presenter.saveaccount(this, mUserNameEt.getText().toString(), mPwdEt.getText().toString());
    }

    private void loadaccount() { // 作用是读取账户密码的状态，内容同上
        Account acc = presenter.loadaccount(this);
        mUserNameEt.setText(acc.getUserName());
        mPwdEt.setText(acc.getPassword());
    }
    
    public void autoLogin(){
    	Account acc = presenter.loadaccount(this);
    	if(!"".equals(acc.getUserName()) && !"".equals(acc.getPassword())){
    		presenter.validateCredentials(mUserNameEt.getText().toString(), mPwdEt.getText().toString());
    	}
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.btn_login:
            saveaccount();
            presenter.validateCredentials(mUserNameEt.getText().toString(), mPwdEt.getText().toString());
            break;
        case R.id.del_psd_iv:
        	mPwdEt.setText("");
        	break;
        case R.id.del_username_iv:
        	mUserNameEt.setText("");
        	break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton arg0, boolean checked) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
        case R.id.cb_save_account:
            saveaccount(); // 每次勾选状态变化时调用
            break;
        }

    }

    @Override
    public void showProgress() {
        // TODO Auto-generated method stub
        waitDialog(true);
    }

    @Override
    public void hideProgress() {
        // TODO Auto-generated method stub
        waitDialog(false);
    }

    @Override
    public void setUsernameError() {
        // TODO Auto-generated method stub
        mUserNameEt.setError(getResources().getString(R.string.prompt_username_empty));
    }

    @Override
    public void setPasswordError() {
        // TODO Auto-generated method stub
        mPwdEt.setError(getResources().getString(R.string.prompt_pwd_empty));
    }

    @Override
    public void navigateToHome() {
        // TODO Auto-generated method stub
        waitDialog(false);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void setNetWorkError() {
        // TODO Auto-generated method stub
        waitDialog(false);
        DebugUtil.show(this, getResources().getString(R.string.prompt_network_error));
    }
}
