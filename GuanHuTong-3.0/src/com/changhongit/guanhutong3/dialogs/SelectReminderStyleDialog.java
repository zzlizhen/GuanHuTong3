package com.changhongit.guanhutong3.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.changhongit.guanhutong3.R;

public class SelectReminderStyleDialog extends Activity implements OnClickListener{
	private Button mEnsureBtn,mCancleBtn;
	private TextView mDayTv,mOneTv,mWeekTv;
	private String mResultStr = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_select_remindertype);
		initView();
	}
	
	private void initView(){
		mEnsureBtn = (Button)findViewById(R.id.ensure_btn);
		mCancleBtn = (Button) findViewById(R.id.cancle_btn);
		mDayTv = (TextView) findViewById(R.id.day_tv);
		mOneTv = (TextView) findViewById(R.id.one_tv);
		mWeekTv = (TextView) findViewById(R.id.week_tv);
		mEnsureBtn.setOnClickListener(this);
		mCancleBtn.setOnClickListener(this);
		mDayTv.setOnClickListener(this);
		mOneTv.setOnClickListener(this);
		mWeekTv.setOnClickListener(this);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 0 ){
			if(resultCode == 0){
				String result = data.getExtras().getString("result");
				mWeekTv.setText(result);
				mResultStr = result;
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent= new Intent();
		switch(v.getId()){
		case R.id.ensure_btn:
			intent.putExtra("resultStr", mResultStr);
			setResult(0,intent);
			finish();
			break;
		case R.id.cancle_btn:
			finish();
			break;
		case R.id.day_tv:
			mResultStr = "每天";
			intent.putExtra("resultStr", mResultStr);
			setResult(0,intent);
			finish();
			break;
		case R.id.one_tv:
			mResultStr = "仅一次";
			intent.putExtra("resultStr", mResultStr);
			setResult(0,intent);
			finish();
			break;
		case R.id.week_tv:
			Intent intent1 = new Intent(SelectReminderStyleDialog.this, SelectWeekDialog.class);
			
			startActivityForResult(intent1,0);
			break;
		}
	}

}
