package com.changhongit.guanhutong3.health.bloodoxygen;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.utils.pulldata.BloodOxygen;

public class BloodOxygenActivity extends BaseActivity implements BloodOxygenView{
	
	BloodOxygenPresenterImpl presenter = new BloodOxygenPresenterImpl(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_bloodoxygen);
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
	public void showResult(BloodOxygen result) {
		// TODO Auto-generated method stub
		((TextView)findViewById(R.id.tv_bloodoxygen_advice_daily)).setText(result.getAdviceDaily());
		((TextView)findViewById(R.id.tv_bloodoxygen_advice_doctor)).setText(result.getAdviceDoctor());
		((TextView)findViewById(R.id.tv_bloodoxygen_advice_food)).setText(result.getAdviceFood());
		((TextView)findViewById(R.id.tv_bloodoxygen_advice_result)).setText(result.getAdviceResult());
		((TextView)findViewById(R.id.tv_bloodoxygen_advice_sport)).setText(result.getAdviceSport());
		((TextView)findViewById(R.id.tv_bloodoxygen_pulse)).setText(result.getPulse());
		((TextView)findViewById(R.id.tv_bloodoxygen_saturation)).setText(result.getOxygenSaturation());
		((TextView)findViewById(R.id.tv_bloodoxygen_time)).setText(result.getTiemStamp());
	}

	@Override
	public void displayMessage(String message) {
		// TODO Auto-generated method stub
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

}
