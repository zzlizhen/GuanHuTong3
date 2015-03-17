package com.changhongit.guanhutong3.setting.terminal;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.utils.pulldata.Terminal;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;

public class TerminalListActivity extends BaseActivity implements  TerminalSettingView,OnClickListener {
	private LinearLayout mReturnBtn;
	private ListView mListView;
	private TerminalListAdapter mAdapter;
	private TerminalSettingPresenter presenter;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_terminal_list);
		presenter = new TerminalSettingPresenterImpl(this);
		initView();
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		presenter.loadList();
	}
	private void initView(){
		mListView = (ListView) findViewById(R.id.terminal_list_lv);
		mReturnBtn = (LinearLayout) findViewById(R.id.return_layout);
		mReturnBtn.setOnClickListener(this);
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				createCustomDialog1(position);
				return false;
			}
		});
	}
	Dialog dialog1 ;
	public void createCustomDialog1(final int position){  
		dialog1 = new Dialog(this);  
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.dialog_terminal_del);  
        Button cancle_btn = (Button) dialog1.findViewById(R.id.cancle_btn);
        cancle_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog1.dismiss();
			}
		});
        
        Button ok_btn = (Button) dialog1.findViewById(R.id.ensure_btn);
        
        ok_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				presenter.deleteTerminal(position);
				dialog1.dismiss();
			}
		});
        
        dialog1.show();  
    }  
	@Override
	public void showprogress() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hideprogress() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showMessage(String msg) {
		// TODO Auto-generated method stub
		
	}
	
	//For teminal detail activity, it isn't useful in this class.
	@Override
	public void setData(Terminal result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.return_layout:
			finish();
			break;
		}
	}
	@Override
	public void setListData(UserTerminalList list) {
		// TODO Auto-generated method stub
		if(mAdapter == null){
			mAdapter = new TerminalListAdapter(this, list);
			mListView.setAdapter(mAdapter);
		}
		else{
			mAdapter.setList(list);
			mAdapter.notifyDataSetChanged();
		}
	}
	@Override
	public void onDeleteFinished(boolean isSuccess) {
		// TODO Auto-generated method stub
		if(isSuccess){
			presenter.loadList();
		}
		else{
			Toast.makeText(getApplicationContext(), "删除终端失败，请重试",Toast.LENGTH_SHORT).show();
		}
	}

}
