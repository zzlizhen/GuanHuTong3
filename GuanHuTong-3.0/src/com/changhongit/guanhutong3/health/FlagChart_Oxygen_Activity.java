package com.changhongit.guanhutong3.health;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.achartengine.GraphicalView;
import org.achartengine.model.SeriesSelection;

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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


public class FlagChart_Oxygen_Activity extends Activity {
	private ImageView imageview_return;
	private int type;
	private List<BloodOxygenInfo> mList;

	private String oxygen ;
	private double[] mData;
	private String[] mTimes;
	GraphicalView mFlagView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flagchar_act);
		setContent();
		mList = new ArrayList<BloodOxygenInfo>();
		type = getIntent().getExtras().getInt("type");
		oxygen = "http://new.guanhutong.com.cn:9080/ilove.webapi/bloodoxygens?imei="
				+ GhtApplication.mCurrentTerminal.getImei();
		getHttpDataThread(false);

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
	
	public void setmData(){
		mData = new double[mList.size()];
		for(int i = 0;i<mList.size();i++){
			mData[i] = Double.valueOf(mList.get(i).getOxygenSaturation());
		}
	}
	public void setmTimes(){
		mTimes =new String[mList.size()];
		StringBuilder builder = new StringBuilder();
		for(int i= 0;i<mList.size();i++){
			String[] tmpstr = mList.get(i).getTimestamp().split(" ");
			
			String[] date = tmpstr[0].split("-");
			builder.append(date[1]+"-"+date[2]);
			builder.append("\n");
			String[] time = tmpstr[1].split(":");
			builder.append(time[0]+":"+time[1]);
			mTimes[i] = builder.toString();
			builder.delete(0, builder.length());
		}
	}

	class ChartViewClick implements View.OnClickListener {  
		  
	    @Override  
	    public void onClick(View v) {  
	      GraphicalView graphicalView = (GraphicalView) v;  
	       
	      //获取当前点击点  
	      SeriesSelection seriesSelection = graphicalView.getCurrentSeriesAndPoint();  
	        
	      if (seriesSelection == null) {  
	        return;  
	      }  
	      int x = (int) seriesSelection.getXValue();  
	      Toast.makeText(FlagChart_Oxygen_Activity.this, "血氧值：" + mData[x-1], Toast.LENGTH_SHORT).show();  
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
			if (type != 3) {
				mFlagView = (GraphicalView)new Chart().executeOxygen(this, tmp,
						mTimes,mData);
				
				layout.addView(mFlagView);
				mFlagView.zoomIn();
				mFlagView.setOnClickListener(new ChartViewClick());
			} 
		}
		imageview_return = (ImageView) findViewById(R.id.imageview_return);
	}

	public String getUrl(int type, int number,boolean isMonth) {
		String url = "";
		if(isMonth){
			url = oxygen +"&startTime="+GHTUtil.lastMonth(1)+"&endTime="+GHTUtil.getCurrentTime()+"&page=1&pageSize="+ number;
		}
		else{
			url = oxygen +"&startTime="+GHTUtil.lastWeek() +"&endTime="+GHTUtil.getCurrentTime()+"&page=1&pageSize="+ number;
		}
		return url;
	}

	public void parData(String str, int type) {
			parOxygenList(str);
	}


	public void parOxygenList(String str) {
		mList.clear();
		if (str != null) {
			BloodOxygenParser parser = new BloodOxygenParser();
			try {
				mList.addAll(parser
						.parseBloodOxygenInfoList(new ByteArrayInputStream(
								str.getBytes())));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			handler.sendEmptyMessage(0);
		} else {
			handler.sendEmptyMessage(1);
		}

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
	
	public void getHttpDataThread(final boolean isMonth){
//		new Thread() {
//			public void run() {
//				String result = GhtApplication.mWebService.getRequest_veri(
//						getUrl(type, 100,isMonth), "user", "password");
//				if (result != null) {
//						parData(result, type);
//						handler.sendEmptyMessage(0);
//				} else {
//						handler.sendEmptyMessage(1000);
//
//				}
//			};
//		}.start();
		
	}
	
}
