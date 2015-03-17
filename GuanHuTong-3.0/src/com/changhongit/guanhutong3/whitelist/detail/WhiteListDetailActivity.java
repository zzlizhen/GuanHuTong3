package com.changhongit.guanhutong3.whitelist.detail;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.base.BaseActivity;

public class WhiteListDetailActivity extends BaseActivity implements WhiteListDetailView ,OnClickListener{
	
	private EditText etName;
	private EditText etPhone;
	private String id;
	private boolean isNew;
	
	WhiteListDetailPresenterImpl presenter = new WhiteListDetailPresenterImpl(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		iniview();
		inidata();
	}

	
	public void iniview(){   //视图初始化
		setContentView(R.layout.activity_setting_whitelist_detail);
		etName = (EditText)findViewById(R.id.et_whitelistdetail_name);
		etPhone = (EditText)findViewById(R.id.et_whitelistdetail_phone);
		((LinearLayout)findViewById(R.id.return_layout)).setOnClickListener(this);
		((Button)findViewById(R.id.save_btn)).setOnClickListener(this);
	}
	
	public void inidata(){
		Intent intent = getIntent();
		
		isNew = intent.getBooleanExtra("isnew",false);
		if(!isNew){ //如果不是在新建白名单
			String name = intent.getStringExtra("name");
			if(name!=null)etName.setText(name);
			etName.setSelection(name.length());
			String phone = intent.getStringExtra("phone");
			if(phone!=null)etPhone.setText(phone);
			String id = intent.getStringExtra("id");
			if(id!=null)this.id = id;
		}
	}


	@Override
	public void showprogress() {
		// TODO Auto-generated method stub
		waitDialog(true);
	}


	@Override
	public void hideprogress(boolean success) {
		// TODO Auto-generated method stub
		waitDialog(false);
		String msg;
		if(success)msg = new String("已保存");
		else msg = new String ("上传失败");
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		this.finish();
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.save_btn:
			presenter.save(isNew, etName.getText().toString(), etPhone.getText().toString(), id);
			break;
		case R.id.return_layout:
			this.finish();
			break;
		}
	}
	
}

