package com.changhongit.guanhutong3.call_setting;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.utils.pulldata.TerminalSetting;

public class CallSettingActivity extends BaseActivity implements OnClickListener,CallSettingView{
	private LinearLayout mReturnLayout;
	private Button mSaveBtn;
	private CallSettingPresenter presenter;
	private ToggleButton mTb;
	private RadioGroup mGroup;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_call_setting);
		presenter = new CallSettingPresenterImpl(this);
		initView();
	}
	private void initView(){
		mReturnLayout = (LinearLayout) findViewById(R.id.return_layout);
		mSaveBtn = (Button) findViewById(R.id.save_btn);
		mReturnLayout.setOnClickListener(this);
		mSaveBtn.setOnClickListener(this);
		mGroup = (RadioGroup) findViewById(R.id.income_setting);
		mTb = (ToggleButton) findViewById(R.id.auto_listen_tb);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		presenter.loadData();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.return_layout:
			finish();
			break;
		case R.id.save_btn:
			TerminalSetting info = new TerminalSetting();
			info.setImei(GhtApplication.mCurrentTerminal.getImei());
			if(mTb.isChecked()){
				info.setAuto(true);
			}
			else{
				info.setAuto(false);
			}
			if(mGroup.getCheckedRadioButtonId() == R.id.all_call_rb){
				info.setReceiveAll(true);
			}
			else{
				info.setReceiveAll(false);
			}
			presenter.savaData(info);
			break;
		}
	}
	@Override
	public void setData(TerminalSetting info) {
		// TODO Auto-generated method stub
		Log.d("Landylitest","info test === " + info.isAuto());
		if(info.isAuto()){
			mTb.setChecked(true);
		}
		else{
			mTb.setChecked(true);
		}
		if(info.isReceiveAll()){
			mGroup.check(R.id.all_call_rb);
		}
		else{
			mGroup.check(R.id.white_call_rb);
		}
	}
}
