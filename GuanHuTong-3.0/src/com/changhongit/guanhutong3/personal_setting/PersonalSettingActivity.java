package com.changhongit.guanhutong3.personal_setting;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.utils.pulldata.UserInfo;

public class PersonalSettingActivity extends BaseActivity implements onLoadFinishedListener, OnClickListener{
	private EditText mNameEt,mMailET,mAddressEt,mNoteEt,mPhoneNumEt,mBrithdayEt;
	private RadioGroup mSexRg;
	private RadioButton mFemaleRb,mMaleRb;
	private PersonalSettingPresenter presenter;
	private LinearLayout mReturnLayout;
	private Button mSaveBtn;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_personal_setting);
		presenter = new PersonalSettingPresenterImpl(this);
		initView();
	}
	public void initView(){
		 mNameEt = (EditText) findViewById(R.id.name_et);
		 mMailET = (EditText) findViewById(R.id.email_et);
		 mAddressEt= (EditText) findViewById(R.id.address_et);
		 mNoteEt= (EditText) findViewById(R.id.note_et);
		 mPhoneNumEt= (EditText) findViewById(R.id.phone_num_et);
		 mSexRg = (RadioGroup) findViewById(R.id.sex_rg);
		 mFemaleRb = (RadioButton) findViewById(R.id.femail_rb);
		 mMaleRb = (RadioButton) findViewById(R.id.male_rb);
		 mBrithdayEt = (EditText) findViewById(R.id.birthday_et);
		 
		 mReturnLayout = (LinearLayout) findViewById(R.id.return_layout);
		 mReturnLayout.setOnClickListener(this);
		 
		 mSaveBtn = (Button) findViewById(R.id.save_btn);
		 mSaveBtn.setOnClickListener(this);
		 
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		presenter.loadUserInfo();
	}
	@Override
	public void onLoadFinished(UserInfo info) {
		// TODO Auto-generated method stub
		mNameEt.setText(info.getUserName());
		mMailET.setText(info.getEMail());
		mAddressEt.setText(info.getAddress());
//		mNoteEt.setText(info.get)       //备注目前接口没有提供数据。
		mPhoneNumEt.setText(info.getPhone());
		mBrithdayEt.setText(info.getBirthday());
		mNameEt.setSelection(info.getUserName().length());
		if(info.isMale()){
			mSexRg.check(R.id.male_rb);
		}
		else{
			mSexRg.check(R.id.femail_rb);
		}
		
	}
	@Override
	public void onSetFinished(boolean isSuccessed) {
		// TODO Auto-generated method stub
		if(isSuccessed){
			Toast.makeText(this,"设置成功", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this, "设置失败", Toast.LENGTH_SHORT).show();
		}
		presenter.loadUserInfo();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.return_layout:
			finish();
			break;
		case R.id.save_btn:
			UserInfo info = new UserInfo();
			info.setUserName(mNameEt.getText().toString());
			info.setBirthday(mBrithdayEt.getText().toString());
			info.setAddress(mAddressEt.getText().toString());
			info.setEMail(mMailET.getText().toString());
			info.setPhone(mPhoneNumEt.getText().toString());
			if(mFemaleRb.isChecked()){
				info.setisMale(false);
			}
			else{
				info.setisMale(true);
			}
			presenter.setUserInfo(info);
			break;
		}
	}

}
