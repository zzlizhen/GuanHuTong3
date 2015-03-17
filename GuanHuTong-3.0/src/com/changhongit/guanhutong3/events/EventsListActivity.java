package com.changhongit.guanhutong3.events;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.map.utils.DebugUtil;
import com.changhongit.guanhutong3.utils.pulldata.Reminder;

public class EventsListActivity extends BaseActivity implements OnClickListener,EventsView, OnItemClickListener {
	
	private ListView mListView;
	private Button mAddBtn;
	private LinearLayout mReturnLayout;
	private EventsListPresenter presenter;
	private EventsAdapter mAdapter;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_events_list);
		presenter = new EventsListPresenterImpl(this);
		initView();
	}
	
	private void initView(){
		mListView = (ListView) findViewById(R.id.lv_events);
		mReturnLayout = (LinearLayout) findViewById(R.id.return_layout);
		mAddBtn = (Button) findViewById(R.id.new_btn);
		mReturnLayout.setOnClickListener(this);
		mAddBtn.setOnClickListener(this);
		mListView.setOnItemClickListener(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		presenter.onResume();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.return_layout:
			finish();
			break;
		case R.id.new_btn:
			Intent intent = new Intent(EventsListActivity.this,EventsDetailActivity.class);
			startActivity(intent);
			break;
		}
	}

	@Override
	public void setItems(List<Reminder> items) {
		// TODO Auto-generated method stub
		DebugUtil.Debug("item ==== ");
		mAdapter = new EventsAdapter(this, items);
		mListView.setAdapter(mAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Reminder item = presenter.getItem(position);
		DebugUtil.Debug("Reminder item id ==== " + item.getId());
		Intent intent = new Intent(EventsListActivity.this,EventsDetailActivity.class);
		intent.putExtra("reminderId", item.getId());
		intent.putExtra("reminderTime", item.getReminderTime());
		startActivity(intent);
	}

}
