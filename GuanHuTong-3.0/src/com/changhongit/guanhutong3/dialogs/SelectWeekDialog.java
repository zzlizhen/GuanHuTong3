package com.changhongit.guanhutong3.dialogs;

import com.changhongit.guanhutong3.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class SelectWeekDialog extends Activity implements OnClickListener{
	
	private Button mOkBtn,mCancleBtn;
	private CheckBox mMondayCb,mTuesdayCb,mWednessdayCb
			,mThursdayCb,mFridayCb,mSaturdayCb,mSundayCb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_select_week);
		initView();
	}
	
	private void initView(){
		mOkBtn = (Button) findViewById(R.id.ensure_btn);
		mCancleBtn = (Button) findViewById(R.id.cancle_btn);
		mMondayCb = (CheckBox) findViewById(R.id.moday_tv);
		mTuesdayCb = (CheckBox) findViewById(R.id.tuesday_tv);
		mWednessdayCb = (CheckBox) findViewById(R.id.wednesday_tv);
		mThursdayCb = (CheckBox) findViewById(R.id.thursday_tv);
		mFridayCb = (CheckBox) findViewById(R.id.friday_tv);
		mSaturdayCb = (CheckBox) findViewById(R.id.saturday_tv);
		mSundayCb = (CheckBox) findViewById(R.id.sunday_tv);
		mOkBtn.setOnClickListener(this);
		mCancleBtn.setOnClickListener(this);
	}
	
	private String buildStr(){
		String result = "星期";
		if(mSundayCb.isChecked()){
			result+="日";
		}
		if(mMondayCb.isChecked()){
			result+="一";
		}
		if(mTuesdayCb.isChecked()){
			result+="二";
		}
		if(mWednessdayCb.isChecked()){
			result+="三";
		}
		if(mThursdayCb.isChecked()){
			result+="四";
		}
		if(mFridayCb.isChecked()){
			result+="五";
		}
		if(mSaturdayCb.isChecked()){
			result+="六";
		}
		return result;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.cancle_btn:
			finish();
			break;
		case R.id.ensure_btn:
			Intent intent = new Intent();
			intent.putExtra("result", buildStr());
			setResult(0,intent);
			finish();
			break;
		}
	}
}
