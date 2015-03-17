package com.changhongit.guanhutong3.dialogs;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.changhongit.guanhutong3.R;

public class SelectTimeDialog extends Activity implements OnClickListener{
	private Button mEnsureBtn,mCancleBtn;
	private TimePicker mTp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_select_time);
		initView();
	}

	private void initView(){
		mEnsureBtn = (Button) findViewById(R.id.ensure_btn);
		mCancleBtn = (Button) findViewById(R.id.cancle_btn);
		mEnsureBtn.setOnClickListener(this);
		mCancleBtn.setOnClickListener(this);
		
		mTp = (TimePicker)findViewById(R.id.time_picker);
        //设置成24小时，隐藏AM/PM picker
		mTp.setIs24HourView(true);
        //修改TimePicker字体的大小
//        setNumberPickerTextSize(tp);
        //修改TimePicker中NumberPicker的大小
//        resizeTimerPicker(tp);
	}
	private List<NumberPicker> findNumberPicker(ViewGroup viewGroup)
    {
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        
        if (null != viewGroup)
        {
            for (int i = 0; i < viewGroup.getChildCount(); i++)
            {
                child = viewGroup.getChildAt(i);
                
                if (child instanceof NumberPicker)
                {
                    npList.add((NumberPicker)child);
                }
                else if (child instanceof LinearLayout)
                {
                    List<NumberPicker> result = findNumberPicker((ViewGroup)child);
                    if (result.size() > 0)
                    {
                        return result;
                    }
                }
            }
        }
        
        return npList;
    }
    
    @SuppressLint("NewApi")
	private EditText findEditText(NumberPicker np)
    {
        if (null != np)
        {
            for (int i = 0; i < np.getChildCount(); i++)
            {
                View child = np.getChildAt(i);
                
                if (child instanceof EditText)
                {
                    return (EditText)child;
                }
            }
        }
        
        return null;
    }
    
    private void setNumberPickerTextSize(ViewGroup viewGroup)
    {
        List<NumberPicker> npList = findNumberPicker(viewGroup);
        if (null != npList)
        {
            for (NumberPicker np : npList)
            {
                EditText et = findEditText(np);
                et.setFocusable(false);
                et.setGravity(Gravity.CENTER);
                et.setTextSize(18);
                et.setTextColor(Color.BLACK);
            }
        }
    }
    
    @SuppressLint("NewApi")
	private void resizeTimerPicker(TimePicker tp)
    {
        List<NumberPicker> npList = findNumberPicker(tp);
        
        for (NumberPicker np : npList)
        {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 0, 10, 0);
            np.setLayoutParams(params);
        }
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.ensure_btn:
			Intent intent = new Intent();
			String hour = null;
			if( mTp.getCurrentHour() < 10 ){
				hour = "0"+ mTp.getCurrentHour();
			}
			else{
				hour =  mTp.getCurrentHour()+"";
			}
			String minute = null;
			if(mTp.getCurrentMinute() < 10){
				minute = "0" + mTp.getCurrentMinute();
			}
			else{
				minute =  mTp.getCurrentMinute()+"";
			}
			
			String tmp = hour+":"+minute;
			
			intent.putExtra("result", tmp);
			setResult(1, intent);
			finish();
			break;
		case R.id.cancle_btn:
			finish();
		}
	}
}
