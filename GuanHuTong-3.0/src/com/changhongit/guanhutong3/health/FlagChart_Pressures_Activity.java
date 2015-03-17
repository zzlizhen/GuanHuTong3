package com.changhongit.guanhutong3.health;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.achartengine.GraphicalView;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.utils.GHTUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.DebugUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;


public class FlagChart_Pressures_Activity extends Activity {
	private ImageView imageview_return;
	private int type;
	private List<BloodPressuresInfo> mList;

	private String sugar;
	private double[] mData;
	private double[] mData_1;
	private String[] mTimes;
	GraphicalView mFlagView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flagchar_act);
		setContent();
		mList = new ArrayList<BloodPressuresInfo>();
		type = getIntent().getExtras().getInt("type");
		sugar = "http://new.guanhutong.com.cn:9080/ilove.webapi/bloodpressures?imei="
				+ GhtApplication.mCurrentTerminal.getImei();

	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				setmData();
				setmTimes();
				initView();
				break;
			case 1000:
				initView();
			}
		};
	};

	public void setmData() {
		mData = new double[mList.size()];
		mData_1= new double[mList.size()];
		for (int i = 0; i < mList.size(); i++) {
			mData[i] = Double.valueOf(mList.get(i).getSystolic());
			mData_1[i] = Double.valueOf(mList.get(i).getDiastolic());
		}
	}

	public void setmTimes() {
		mTimes = new String[mList.size()];
		for (int i = 0; i < mList.size(); i++) {
			String[] tmpstr = mList.get(i).getTimestamp().split(" ");
			StringBuilder builder = new StringBuilder();
			String[] date = tmpstr[0].split("-");
			builder.append(date[1] + "-" + date[2]);
			builder.append("\n");
			String[] time = tmpstr[1].split(":");
			builder.append(time[0] + ":" + time[1]);
			mTimes[i] = builder.toString();
		}
	}

	public void initView() {
		LinearLayout layout = (LinearLayout) findViewById(R.id.flagchart_layout);
		layout.removeView(mFlagView);
		if (mData != null && mData.length > 0) {
			double[] tmp = new double[mData.length];
			for (int i = 0; i < mData.length; i++) {
				tmp[i] = i + 1;
			}

			double[][] datas = { mData, mData_1 };

			mFlagView =(GraphicalView) new Chart()
					.executePressures(this, tmp, mTimes,datas);
			layout.addView(mFlagView);
		}
		imageview_return = (ImageView) findViewById(R.id.imageview_return);
	}



	public void parData(String str, int type) {
		parPressureList(str);
	}

	public double[] parSugarList(String str) {
		double[] data = null;
		try {
			List<Double> list = new BloodSugarParser()
					.parseBloodSugarValues(new ByteArrayInputStream(str
							.getBytes()));
			data = new double[list.size()];
			for (int i = 0; i < list.size(); i++) {
				data[i] = list.get(i).doubleValue();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public void parPressureList(String str) {
		mList.clear();
		if (str != null) {
			BloodPressuresParser parser = new BloodPressuresParser();
			try {
				mList.addAll(parser
						.parseBloodPressuresList(new ByteArrayInputStream(str
								.getBytes())));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			handler.sendEmptyMessage(0);
		} else {
			handler.sendEmptyMessage(1);
		}

	}

	public List<double[]> parserPressureList(String str) {
		List<double[]> data = null;
		try {
			List<Double[]> list = new BloodPressuresParser()
					.parseBloodPressuresValueList(new ByteArrayInputStream(str
							.getBytes()));
			data = new ArrayList<double[]>(2);
			data.add(0, new double[list.size()]);
			data.add(1, new double[list.size()]);
			for (int i = 0; i < list.size(); i++) {
				data.get(0)[i] = list.get(i)[0];
				data.get(1)[i] = list.get(i)[1];
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public String getUrl(int type, int number,boolean isMonth) {
		String url = "";
		if(isMonth){
			url = sugar +"&startTime="+GHTUtil.lastMonth(1)+"&endTime="+GHTUtil.getCurrentTime()+"&page=1&pageSize="+ number;
		}
		else{
			url = sugar +"&startTime="+GHTUtil.lastWeek() +"&endTime="+GHTUtil.getCurrentTime()+"&page=1&pageSize="+ number;
		}
		GHTUtil.Debug("url string === " + url);
		return url;
	}

	
	public void getHttpDataThread(final boolean isMonth){
//		new Thread() {
//			public void run() {
//				String result = GhtApplication.mWebService.getRequest_veri(
//						getUrl(type, 10,isMonth), "user", "password");
//				GHTUtil.Debug("presure ..... === " + result);
//				if (result != null) {
//					parData(result,3);
//					handler.sendEmptyMessage(0);
//				} else {
//					handler.sendEmptyMessage(1000);
//				}
//			};
//		}.start();
		
	}

	public void setContent() {

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.simple_spinner_item);
		String level[] = getResources().getStringArray(R.array.last_array);// 资源文件
		for (int i = 0; i < level.length; i++) {
			adapter.add(level[i]);
		}
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner spinner = (Spinner) findViewById(R.id.last_sp);

		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position == 0){
					getHttpDataThread(false);
				}
				else{
					getHttpDataThread(true);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});

	}
}
