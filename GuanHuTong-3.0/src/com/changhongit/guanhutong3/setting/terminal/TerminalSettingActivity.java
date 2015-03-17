package com.changhongit.guanhutong3.setting.terminal;

import java.text.DecimalFormat;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.utils.pulldata.Terminal;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;

public class TerminalSettingActivity extends BaseActivity implements TerminalSettingView,OnClickListener,OnDateSetListener {

	TerminalSettingPresenterImpl presenter;
	
	EditText imei;
	EditText name;
	EditText nickname;
	EditText birthday;
	EditText height;
	EditText weight;
	EditText sbp;
	EditText dbp;
	EditText allergy;
	EditText history;
	EditText backlog;
	RadioGroup gender;
	Spinner bloodType;
	Button mSaveBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_terminalmanage_detail);
		presenter = new TerminalSettingPresenterImpl(this);
		iniview();
		boolean isNew = getIntent().getExtras().getBoolean("isnew", true);
		if(!isNew){
			presenter.loaddata();
			showprogress();
		}
		
	}
	
	private void iniview(){
		imei = (EditText)findViewById(R.id.et_terminal_imei);
		name = (EditText)findViewById(R.id.et_terminal_name);
		nickname = (EditText)findViewById(R.id.et_terminal_nickname);
		birthday = (EditText)findViewById(R.id.et_terminal_bithday);
		birthday.setOnClickListener(this);
		height = (EditText)findViewById(R.id.et_terminal_height);
		weight = (EditText)findViewById(R.id.et_terminal_weight);
		sbp = (EditText)findViewById(R.id.et_terminal_bphigh);
		dbp = (EditText)findViewById(R.id.et_terminal_bplow);
		allergy = (EditText)findViewById(R.id.et_terminal_allergy);
		history = (EditText)findViewById(R.id.et_terminal_medicalhistory);
		backlog = (EditText)findViewById(R.id.et_terminal_remark);
		gender = (RadioGroup)findViewById(R.id.rg_terminal_gender);
		bloodType = (Spinner)findViewById(R.id.sp_terminal_bloodtype);
	    String[] m_arr = {"A","B","AB","O"};
		ArrayAdapter<String> ada = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m_arr);
		ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		bloodType.setAdapter(ada);
		((LinearLayout)findViewById(R.id.return_layout)).setOnClickListener(this);
		((Button)findViewById(R.id.save_btn)).setOnClickListener(this);
		
	}

	@Override
	public void setData(Terminal result) {
		// TODO Auto-generated method stub
		imei.setText(result.getImei());
		name.setText(result.getName());
		name.setSelection(result.getName().length());
		nickname.setText(result.getNickname());
		birthday.setText(result.getBirthday());
		height.setText(result.getHeight());
		weight.setText(result.getWeight());
		sbp.setText(result.getBPHigh());
		dbp.setText(result.getBPLow());
		allergy.setText(result.getAllergy());
		history.setText(result.getMedicalHistory());
		backlog.setText(result.getRemark());
		if(result.getIsMale())gender.check(R.id.rbtn_terminal_male);
		else gender.check(R.id.rbtn_terminal_female);
		bloodType.setSelection(result.getBlood());
	}
	
	public void saveData(){
		Terminal terminal = new Terminal();
		terminal.setImei(imei.getText().toString());
		terminal.setName(name.getText().toString());
		terminal.setNickname(nickname.getText().toString());
		terminal.setBirthday(birthday.getText().toString());
		terminal.setHeight(height.getText().toString());
		terminal.setWeight(weight.getText().toString());
		terminal.setBPHigh(sbp.getText().toString());
		terminal.setBPLow(dbp.getText().toString());
		terminal.setAllergy(allergy.getText().toString());
		if("null".equals(history.getText().toString())){
			terminal.setMedicalHistory("");
		}
		else{
			terminal.setMedicalHistory(history.getText().toString());
		}
		if("null".equals(backlog.getText().toString())){
			terminal.setRemark("");
		}
		else{
			terminal.setRemark(backlog.getText().toString());
		}
		terminal.setBlood(bloodType.getSelectedItemPosition());
		if(gender.getCheckedRadioButtonId()==0)terminal.setIsMale(true);
		else terminal.setIsMale(false);
		presenter.savedata(terminal);
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
	public void showMessage(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.return_layout:
			this.finish();
			break;
		case R.id.save_btn:
			saveData();
			showprogress();
			break;
		case R.id.et_terminal_bithday:
		    Dialog dialog = new DatePickerDialog(this,  
                    this,  
                    Calendar.getInstance().get(Calendar.YEAR),  
                    Calendar.getInstance().get(Calendar.MONTH),  
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));  
		    dialog.show();
			break;
		}
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
        DecimalFormat format = new DecimalFormat("00");
        String result = "" + year + '-' + format.format(monthOfYear) + '-' + format.format(dayOfMonth);
        birthday.setText(result);
	}

	@Override
	public void setListData(UserTerminalList list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeleteFinished(boolean isSuccess) {
		// TODO Auto-generated method stub
		
	}

}
