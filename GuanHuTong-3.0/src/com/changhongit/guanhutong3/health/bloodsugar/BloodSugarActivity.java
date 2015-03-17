package com.changhongit.guanhutong3.health.bloodsugar;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.utils.pulldata.BloodSugar;

public class BloodSugarActivity extends BaseActivity implements BloodSugarView{
	
	BloodSugarPresenterImpl presenter = new BloodSugarPresenterImpl(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_bloodsugar);
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
	public void showResult(BloodSugar result) {
		// TODO Auto-generated method stub
		((TextView)findViewById(R.id.tv_bloodsugar_advice_daily)).setText(result.getAdviceDaily());
		((TextView)findViewById(R.id.tv_bloodsugar_advice_doctor)).setText(result.getAdviceDoctor());
		((TextView)findViewById(R.id.tv_bloodsugar_advice_food)).setText(result.getAdviceFood());
		((TextView)findViewById(R.id.tv_bloodsugar_advice_result)).setText(result.getAdviceResult());
		((TextView)findViewById(R.id.tv_bloodsugar_advice_sport)).setText(result.getAdviceSport());
		((TextView)findViewById(R.id.tv_bloodsugar_FPG)).setText(result.getFPG());
		((TextView)findViewById(R.id.tv_bloodsugar_time)).setText(result.getTimestamp());
	}

	@Override
	public void displayMessage(String message) {
		// TODO Auto-generated method stub
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

}
