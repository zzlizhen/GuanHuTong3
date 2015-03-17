package com.changhongit.guanhutong3.trace;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import com.changhongit.guanhutong3.R;

public class SetTimeActivity extends Activity implements OnClickListener {

    private int which; // 判断返回给哪个，1是初始时间，2是结尾时间
    private Long timemills;
    private Date time;

    private static int year;
    private static int month;
    private static int day;
    private static int hour;
    private static int minute;

    private DatePicker mDatePicker;
    private TimePicker mTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settime);
        which = this.getIntent().getIntExtra("which", 1);
        ini();
        iniview();
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
        case R.id.btn_settime_cancel:
            this.setResult(0);
            this.finish();
            break;
        case R.id.btn_settime_confirm:
            this.setResult(which, this.getIntent().putExtra("result", getResult()));
            this.finish();
            break;
        }
    }

    private void ini() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        year = t.year;
        month = t.month;
        day = t.monthDay;
        hour = t.hour; // 0-23
        minute = t.minute;
    }

    private void iniview() {
        ((Button) findViewById(R.id.btn_settime_cancel)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_settime_confirm)).setOnClickListener(this);
        mDatePicker = (DatePicker) findViewById(R.id.datePicker);
        mTimePicker = (TimePicker) findViewById(R.id.timePicker);
        mDatePicker.init(year, month, day, null);// 初始化datepicker
        mDatePicker.setMaxDate(System.currentTimeMillis());
    }

    private String getResult() {
        year = mDatePicker.getYear();
        month = mDatePicker.getMonth() + 1;
        day = mDatePicker.getDayOfMonth();
        hour = mTimePicker.getCurrentHour();
        minute = mTimePicker.getCurrentMinute();
        DecimalFormat format = new DecimalFormat("00");
        String result = "" + year + '-' + format.format(month) + '-' + format.format(day) + ' ' + format.format(hour) + ':' + format.format(minute) + ":"+ "00";
        return result;
    }
}
