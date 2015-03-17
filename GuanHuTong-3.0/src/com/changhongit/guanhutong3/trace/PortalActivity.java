package com.changhongit.guanhutong3.trace;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.changhongit.guanhutong3.R;

public class PortalActivity extends Activity implements OnClickListener {

    // 不想写太多模块，代码全部写在里面
    // startTime必然小于endTime，还未添加这个约束条件

    private String current;

    private EditText etstart;
    private EditText etend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);

        ((Button) findViewById(R.id.btn_cancle)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_confirm)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_setstart)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_setend)).setOnClickListener(this);
        etstart = (EditText) findViewById(R.id.et_starttime);
        etend = (EditText) findViewById(R.id.et_endtime);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date(System.currentTimeMillis());// 获取当前时间
        current = formatter.format(time);
        etstart.setText(current);
        etend.setText(current);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
        case R.id.btn_cancle:
            this.finish();
            break;
        case R.id.btn_confirm:
        	if(timeIsGood()){
                Intent traceIntent = new Intent(this, TraceActivity.class);
                String startTime = etstart.getText().toString().replace("-", "").replace(":","").replace(" ", "");
                String endTime = etend.getText().toString().replace("-", "").replace(":","").replace(" ", "");
                traceIntent.putExtra("starttime", startTime);
                traceIntent.putExtra("endtime", endTime);
                startActivity(traceIntent);
                this.finish();
            	}
            	else{
            		Toast.makeText(this, "结束时间应大于开始时间，且相差不超过30天", Toast.LENGTH_LONG).show();
            	}
                break;
        case R.id.btn_setstart:
        		Intent startintent = new Intent(this, SetTimeActivity.class);
        		startintent.putExtra("which", 1);
        		startActivityForResult(startintent, 1);
        		break;
        case R.id.btn_setend:
            Intent endintent = new Intent(this, SetTimeActivity.class);
            endintent.putExtra("which", 2);
            startActivityForResult(endintent, 2);
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
            switch (resultCode) {
            case 1:
                String starttime = data.getStringExtra("result");
                etstart.setText(starttime);
                break;
            case 2:
                String endtime = data.getStringExtra("result");
                etend.setText(endtime);
                break;
            }
    }
    
    private boolean timeIsGood() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = null;
        Date endTime = null;
		try {
			startTime = formatter.parse(etstart.getText().toString());
	        endTime = formatter.parse(etend.getText().toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int days = (int)((endTime.getTime()-startTime.getTime())/86400000);
        if(days<=30&endTime.getTime()>=startTime.getTime()) return true;
        else return false;
    }
}
