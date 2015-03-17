package com.changhongit.guanhutong3.whitelist;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.utils.pulldata.WhiteListInfo;
import com.changhongit.guanhutong3.whitelist.detail.WhiteListDetailActivity;

public class WhiteListActivity extends BaseActivity implements
		OnItemClickListener, OnItemLongClickListener, OnClickListener,
		WhiteListView {
	private ListView mListView;
	private WhiteListAdapter adapter;
	private WhiteListPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_whitelist);
		mListView = (ListView) findViewById(R.id.lv_whitelsit);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		((Button) findViewById(R.id.new_btn))
				.setOnClickListener(this);
		((LinearLayout) findViewById(R.id.return_layout)).setOnClickListener(this);
		presenter = new WhiteListPresenterImpl(this);
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		presenter.onResume();
	}

	@Override
	public void showProgress() {
		// TODO Auto-generated method stub
		waitDialog(true);
	}

	@Override
	public void hideProgress() {
		// TODO Auto-generated method stub
		waitDialog(false);
	}

	@Override
	public void setItems(List<WhiteListInfo> items) {
		// TODO Auto-generated method stub
		if (adapter == null) {
			adapter = new WhiteListAdapter(this, items);
			mListView.setAdapter(adapter);
		}
		else{
			adapter.setList(items);
			adapter.notifyDataSetChanged();
		}
	}
	

	@Override
	public void showMessage(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		presenter.onItemClicked(position);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			final int position, long id) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(
				WhiteListActivity.this);
		builder.setMessage("是否删除该条记录").setCancelable(false)
				.setPositiveButton("是", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						presenter.delete(position);
						dialog.cancel();
					}
				})
				.setNegativeButton("否", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
		return false;
	}

	@Override
	public void turnToDetail(int position) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(WhiteListActivity.this,
				WhiteListDetailActivity.class);
		if (position >= 0) {
			intent.putExtra("isnew", false);
			intent.putExtra("name", adapter.getItem(position).getName());
			intent.putExtra("phone", adapter.getItem(position).getPhone());
			intent.putExtra("id", adapter.getItem(position).getId());
		} else {
			intent.putExtra("isnew", true);
		}
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.new_btn:
			turnToDetail(-1);
			break;
		case R.id.return_layout:
			this.finish();
		}
	}

}
