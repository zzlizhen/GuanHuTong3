package com.changhongit.guanhutong3.health.bloodpressure;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.utils.pulldata.BloodPressure;

public class BloodPressureActivity extends BaseActivity implements BloodPressureView{
	
	BloodPressurePresenterImpl presenter = new BloodPressurePresenterImpl(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_bloodpressure);
		waitDialog(true);
		presenter.LoadData();
	}

	@Override
	public void showprogress() {
		// TODO Auto-generated method stub
		waitDialog(true);
	}

	@Override
	public void hideprogress() {
		// TODO Auto-generated method stub
		waitDialog(false);
	}

	@Override
	public void showResult(BloodPressure result) {
		// TODO Auto-generated method stub
		((TextView)findViewById(R.id.tv_bloodpressure_advice_daily)).setText(result.getAdviceDaily());
		((TextView)findViewById(R.id.tv_bloodpressure_advice_doctor)).setText(result.getAdviceDoctor());
		((TextView)findViewById(R.id.tv_bloodpressure_advice_food)).setText(result.getAdviceFood());
		((TextView)findViewById(R.id.tv_bloodpressure_advice_result)).setText(result.getAdviceResult());
		((TextView)findViewById(R.id.tv_bloodpressure_advice_sport)).setText(result.getAdviceSport());
		((TextView)findViewById(R.id.tv_bloodpressure_pulse)).setText(result.getPulse());
		((TextView)findViewById(R.id.tv_bloodpressure_diastolic)).setText(result.getDiastolic());
		((TextView)findViewById(R.id.tv_bloodpressure_systolic)).setText(result.getSystolic());
		((TextView)findViewById(R.id.tv_bloodpressure_time)).setText(result.getTimeStamp());
	}

	@Override
	public void displayMessage(String message) {
		// TODO Auto-generated method stub
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

}
