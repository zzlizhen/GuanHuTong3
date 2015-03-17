package com.changhongit.guanhutong3.events;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.dialogs.SelectReminderStyleDialog;
import com.changhongit.guanhutong3.dialogs.SelectTimeDialog;
import com.changhongit.guanhutong3.map.utils.DebugUtil;
import com.changhongit.guanhutong3.utils.HttpUtil;
import com.changhongit.guanhutong3.utils.XMLPullUtil;
import com.changhongit.guanhutong3.utils.pulldata.Reminder;

public class EventsDetailActivity extends BaseActivity implements
		OnClickListener {
	private TextView mEventType, mEventTime;
	private EditText mEventContent;
	private ToggleButton mTogBtn;
	private String mId, mReminderTime;
	private LinearLayout mReturnLayout;
	private boolean isAdd = false;
	private Button mSaveBtn;
	private RelativeLayout mReminderTypeLayout,mEventTimeLayout;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_events_detail);
		initView();
		if (getIntent().getExtras() != null) {
			mId = getIntent().getExtras().getString("reminderId");
			mReminderTime = getIntent().getExtras().getString("reminderTime");
			getEventDetail();
		} else {
			isAdd = true;
		}
	}

	private void initView() {
		mEventType = (TextView) findViewById(R.id.tv_event_type);
		mEventTime = (TextView) findViewById(R.id.tv_event_time);
		mEventContent = (EditText) findViewById(R.id.et_event_content);
		mTogBtn = (ToggleButton) findViewById(R.id.tb_switch);
		mReturnLayout = (LinearLayout) findViewById(R.id.return_layout);
		mReturnLayout.setOnClickListener(this);
		mSaveBtn = (Button) findViewById(R.id.save_btn);
		mSaveBtn.setOnClickListener(this);
		mReminderTypeLayout = (RelativeLayout) findViewById(R.id.remindertype_layout);
		mReminderTypeLayout.setOnClickListener(this);
		mEventTimeLayout = (RelativeLayout) findViewById(R.id.event_time_layout);
		mEventTimeLayout.setOnClickListener(this);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				parseData(msg.getData().getString("result"));
				waitDialog(false);
				break;
			case 1:
				break;
			case 2:
				waitDialog(false);
				Toast.makeText(EventsDetailActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
				break;
			case 3:
				waitDialog(false);
				Toast.makeText(EventsDetailActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};

	
	private void parseData(String str) {
		try {
			Reminder info = XMLPullUtil.getinstance().parseReminder(str);
			if (info.isSwichFlag()) {
				mTogBtn.setChecked(true);
			} else {
				mTogBtn.setChecked(false);
			}
			switch (info.getReminderType()) {
			case Reminder.DAY:
				mEventType.setText("每天");
				break;
			case Reminder.ONE:
				mEventType.setText("仅一次");
				break;
			case Reminder.WEEK:
				boolean[] week = info.getWeek();
				String tmp = "星期";
				mEventType.setText("星期");
				for (int i = 0; i < week.length; i++) {
					if (week[i] && i == 0) {
						tmp = tmp + "日";
					} else if (week[i] && i == 1) {
						tmp = tmp + "一";
					} else if (week[i] && i == 2) {
						tmp = tmp + "二";
					} else if (week[i] && i == 3) {
						tmp = tmp + "三";
					} else if (week[i] && i == 4) {
						tmp = tmp + "四";
					} else if (week[i] && i == 5) {
						tmp = tmp + "五";
					} else if (week[i] && i == 6) {
						tmp = tmp + "六";
					}

				}
				mEventType.setText(tmp);
				break;
			}
			// if(info.getReminderType() == Reminder.DAY){
			mEventTime.setText(mReminderTime);
			mEventContent.setText(info.getContent());
			// }
			// else{
			//
			// }

		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getEventDetail() {
		new Thread() {
			public void run() {
				String result = HttpUtil.getinstance().RequestReminderInfo(mId);
				DebugUtil.Debug("Reminder detail === " + result);
				if (result != null) {
					Message msg = Message.obtain();
					msg.what = 0;
					Bundle bundle = new Bundle();
					bundle.putString("result", result);
					msg.setData(bundle);
					handler.sendMessage(msg);
				} else {
					handler.sendEmptyMessage(1);
				}
			};
		}.start();
	}

	private void addEventThread(final int reminderType,
			final boolean[] chooseWeek, final boolean switchFlag,
			final String reminderTime, final String content) {
		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				boolean result = HttpUtil.getinstance().addReminder(
						GhtApplication.mCurrentTerminal.getImei(),
						reminderType, chooseWeek, switchFlag, reminderTime,
						content);
				if (result) {
					handler.sendEmptyMessage(2);
				} else {
					handler.sendEmptyMessage(3);
				}
			}
		}.start();
	}
	
	
	private void updateEventThread(final int reminderType,
			final boolean[] chooseWeek, final boolean switchFlag,
			final String reminderTime, final String content) {
		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				boolean result = HttpUtil.getinstance().updateReminder(mId,
						GhtApplication.mCurrentTerminal.getImei(),
						reminderType, chooseWeek, switchFlag, reminderTime,
						content);
				if (result) {
					handler.sendEmptyMessage(2);
				} else {
					handler.sendEmptyMessage(3);
				}
			}
		}.start();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.return_layout:
			finish();
			break;
		case R.id.save_btn:
			waitDialog(true);
			int remindtype = 0;
			boolean[] bools = new boolean[7];
			if (mEventType.getText().toString().equals("每天")) {
				remindtype = 1;
			} else if (mEventType.getText().toString().contains("星期")) {
				remindtype = 2;
				String tmpStr = mEventType.getText().toString();
				for (int i = 2; i < tmpStr.length(); i++) {
					if (tmpStr.charAt(i) == '日') {
						bools[0] = true;
					}  else if (tmpStr.charAt(i) == '一') {
						bools[1] = true;
					} else if (tmpStr.charAt(i) == '二') {
						bools[2] = true;
					} else if (tmpStr.charAt(i) == '三') {
						bools[3] = true;
					} else if (tmpStr.charAt(i) == '四') {
						bools[4] = true;
					} else if (tmpStr.charAt(i) == '五') {
						bools[5] = true;
					} else if (tmpStr.charAt(i) == '六') {
						bools[6] = true;
					}
				}
			} else if (mEventType.getText().toString()
					.equalsIgnoreCase("仅一次")) {
				remindtype = 0;
			}
			if (isAdd) { // 新增事件提醒
				addEventThread(remindtype, bools, mTogBtn.isChecked(),
						mEventTime.getText().toString(), mEventContent
								.getText().toString());
			} else { // 更新事件提醒
				updateEventThread(remindtype, bools, mTogBtn.isChecked(),
						mEventTime.getText().toString(), mEventContent
						.getText().toString());
			}
			break;
		case R.id.remindertype_layout:
			Intent intent = new Intent(EventsDetailActivity.this, SelectReminderStyleDialog.class);
			startActivityForResult(intent,0);
			break;
		case R.id.event_time_layout:
			Intent intent1 = new Intent(EventsDetailActivity.this, SelectTimeDialog.class);
			startActivityForResult(intent1,1);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 0){
			if(resultCode == 0 && data !=null){
				String result = data.getExtras().getString("resultStr");
				mEventType.setText(result);
				Toast.makeText(EventsDetailActivity.this, result, Toast.LENGTH_SHORT).show();
			}
		}
		else if(requestCode == 1 && resultCode == 1 && data!=null){
			String result = data.getExtras().getString("result");
			if(mEventType.getText().toString().contains("星期")){
				mEventTime.setText(result+":00");
			}
			else{
				mEventTime.setText(result);
			}
		}
	}
}
