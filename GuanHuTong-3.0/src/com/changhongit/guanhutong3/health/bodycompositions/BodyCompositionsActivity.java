package com.changhongit.guanhutong3.health.bodycompositions;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.utils.pulldata.BodyCompositions;

public class BodyCompositionsActivity extends BaseActivity implements BodyCompositionsView{
	
	BodyCompositionsPresenterImpl presenter = new BodyCompositionsPresenterImpl(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_bodycompositions);
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
	public void showResult(BodyCompositions result) {
		// TODO Auto-generated method stub
		((TextView)findViewById(R.id.tv_bodycompositions_advice_daily)).setText(result.getAdviceDaily());
		((TextView)findViewById(R.id.tv_bodycompositions_advice_doctor)).setText(result.getAdviceDoctor());
		((TextView)findViewById(R.id.tv_bodycompositions_advice_food)).setText(result.getAdviceFood());
		((TextView)findViewById(R.id.tv_bodycompositions_advice_result)).setText(result.getAdviceResult());
		((TextView)findViewById(R.id.tv_bodycompositions_advice_sport)).setText(result.getAdviceSport());
		((TextView)findViewById(R.id.tv_bodycompositions_bmi)).setText(result.getBmi());
		((TextView)findViewById(R.id.tv_bodycompositions_bmr)).setText(result.getBmr());
		((TextView)findViewById(R.id.tv_bodycompositions_bone)).setText(result.getBone());
		((TextView)findViewById(R.id.tv_bodycompositions_impedance)).setText(result.getImpedance());
		((TextView)findViewById(R.id.tv_bodycompositions_moisture)).setText(result.getMoisture());
		((TextView)findViewById(R.id.tv_bodycompositions_muscle)).setText(result.getMuscle());
		((TextView)findViewById(R.id.tv_bodycompositions_thermal)).setText(result.getThermal());
		((TextView)findViewById(R.id.tv_bodycompositions_time)).setText(result.getTimeStamp());
		((TextView)findViewById(R.id.tv_bodycompositions_visceralfat)).setText(result.getVisceralfat());
	}

	@Override
	public void displayMessage(String message) {
		// TODO Auto-generated method stub
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

}
