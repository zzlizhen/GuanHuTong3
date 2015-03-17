package com.changhongit.guanhutong3.alert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.utils.pulldata.AlertList;
import com.changhongit.guanhutong3.view.PullToRefreshView;
import com.changhongit.guanhutong3.view.PullToRefreshView.OnFooterRefreshListener;
import com.changhongit.guanhutong3.view.PullToRefreshView.OnHeaderRefreshListener;

public class AlertActivity extends BaseActivity implements AlertView,OnClickListener,OnItemClickListener,OnFooterRefreshListener,OnHeaderRefreshListener{
	
	TextView backtext;
	AlertPresenterImpl presenter;
	PullToRefreshView mListview;
	AlertAdapter adapter = null;
	private int currentpage = 0;//当前显示的页数
	private int max = 0;  //以此为依据判断列表中是否还有未加载的项目
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alert);
		presenter = new AlertPresenterImpl(this);
		iniview();
	}
	
	private void iniview(){
		mListview = (PullToRefreshView)findViewById(R.id.alert_pull_refresh_view);
		mListview.setOnHeaderRefreshListener(this);
		mListview.setOnFooterRefreshListener(this);
		((ListView)mListview.findViewById(R.id.alert_listview)).setOnItemClickListener(this);
		((LinearLayout)findViewById(R.id.return_layout)).setOnClickListener(this);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		adapter = new AlertAdapter(this);
		currentpage = 0;
		presenter.onResume();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		presenter.onPause();
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.return_layout:
			this.finish();
			break;
		}
	}

	@Override
	public void setData(AlertList list) {
		// TODO Auto-generated method stub
		if(currentpage==0){
			mListview.onHeaderRefreshComplete();  //根据页数判断是上拉还是下拉刷新
		}
		else mListview.onFooterRefreshComplete();
		if(list!=null){
			max = list.getTotalCount();
			adapter.addList(list);
			((ListView)mListview.findViewById(R.id.alert_listview)).setAdapter(adapter);
			currentpage++;
		}
		else showFailure();
	}

	@Override
	public void showFailure() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "读取失败，请检查网络", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putParcelable("alert", adapter.getItem(position));
		Intent intent = new Intent();
		intent.setClass(AlertActivity.this, AlertDetailActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		// TODO Auto-generated method stub
		onResume();
	}

	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		// TODO Auto-generated method stub
		if(currentpage*10-max>=0)Toast.makeText(this, "已无加载项", Toast.LENGTH_SHORT).show();
		else presenter.getData(++currentpage);
	}
	
}
