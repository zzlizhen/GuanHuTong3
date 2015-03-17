package com.changhongit.guanhutong3.contacts;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.base.BaseActivity;
import com.changhongit.guanhutong3.map.utils.DebugUtil;
import com.changhongit.guanhutong3.utils.pulldata.Contact;
import com.changhongit.guanhutong3.utils.pulldata.WhiteListInfo;

public class ContactsActivity extends BaseActivity implements OnClickListener,
		ContactsView, onLoadFinishedListener {
	private LinearLayout mReturnLayout;
	private EditText mFirstEt, mSecondEt, mThirdEt, mFourthEt;
	private EditText mFirstNameEt, mSecondNameEt, mThirdNameEt, mFourthNameEt;
	private ImageView mFirstIv, mSecondIv, mThirdIv, mFourthIv;
	private ContactsPresenter presenter;
	private Button mSaveBtn;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_contacts);
		presenter = new ContactsPresenterImpl(this, this);
		initView();
	}

	public void initView() {
		mReturnLayout = (LinearLayout) findViewById(R.id.return_layout);
		mReturnLayout.setOnClickListener(this);
		mFirstEt = (EditText) findViewById(R.id.first_contact_et);
		mSecondEt = (EditText) findViewById(R.id.second_contact_et);
		mThirdEt = (EditText) findViewById(R.id.third_contact_et);
		mFourthEt = (EditText) findViewById(R.id.fourth_contact_et);
		mFirstEt.setEnabled(false);
		mSecondEt.setEnabled(false);
		mThirdEt.setEnabled(false);
		mFourthEt.setEnabled(false);
		mFirstIv = (ImageView) findViewById(R.id.frist_edit_icon);
		mSecondIv = (ImageView) findViewById(R.id.second_edit_icon);
		mThirdIv = (ImageView) findViewById(R.id.third_edit_icon);
		mFourthIv = (ImageView) findViewById(R.id.fourth_edit_icon);
		mFirstIv.setOnClickListener(this);
		mSecondIv.setOnClickListener(this);
		mThirdIv.setOnClickListener(this);
		mFourthIv.setOnClickListener(this);

		mFirstNameEt = (EditText) findViewById(R.id.first_name_et);
		mSecondNameEt = (EditText) findViewById(R.id.second_name_et);
		mThirdNameEt = (EditText) findViewById(R.id.third_name_et);
		mFourthNameEt = (EditText) findViewById(R.id.fourth_name_et);

		mFirstNameEt.setEnabled(false);
		mSecondNameEt.setEnabled(false);
		mThirdNameEt.setEnabled(false);
		mFourthNameEt.setEnabled(false);

		mSaveBtn = (Button) findViewById(R.id.save_btn);
		mSaveBtn.setOnClickListener(this);
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
		switch (v.getId()) {
		case R.id.return_layout:
			finish();
			break;
		case R.id.frist_edit_icon:
			if (mFirstEt.isEnabled()) {
				mFirstEt.setEnabled(false);
				mFirstNameEt.setEnabled(false);
				presenter.setContactForIndex(0, mFirstNameEt.getText().toString(), mFirstEt.getText().toString());
			} else {
				mFirstEt.setEnabled(true);
				mFirstNameEt.requestFocus();
				mFirstNameEt.setSelection(mFirstNameEt.getText().toString().length());
				mFirstNameEt.setEnabled(true);
			}
			break;
		case R.id.second_edit_icon:
			if (mSecondEt.isEnabled()) {
				mSecondEt.setEnabled(false);
				mSecondNameEt.setEnabled(false);
				presenter.setContactForIndex(1, mSecondNameEt.getText().toString(), mSecondEt.getText().toString());
			} else {
				mSecondEt.setEnabled(true);
				mSecondNameEt.requestFocus();
				mSecondNameEt.setSelection(mSecondNameEt.getText().toString().length());
				mSecondNameEt.setEnabled(true);
			}
			break;
		case R.id.third_edit_icon:
			if (mThirdEt.isEnabled()) {
				mThirdEt.setEnabled(false);
				mThirdNameEt.setEnabled(false);
				presenter.setContactForIndex(2, mThirdNameEt.getText().toString(), mThirdEt.getText().toString());
			} else {
				mThirdEt.setEnabled(true);
				mThirdNameEt.requestFocus();
				mThirdNameEt.setSelection(mThirdNameEt.getText().toString().length());
				mThirdNameEt.setEnabled(true);
			}
			break;
		case R.id.fourth_edit_icon:
			if (mFourthEt.isEnabled()) {
				mFourthEt.setEnabled(false);
				mFourthNameEt.setEnabled(false);
				presenter.setContactForIndex(2, mFourthNameEt.getText().toString(), mFourthEt.getText().toString());
			} else {
				mFourthEt.setEnabled(true);
				mFourthNameEt.requestFocus();
				mFourthNameEt.setSelection(mFourthNameEt.getText().toString().length());
				mFourthNameEt.setEnabled(true);
			}
			break;
		case R.id.save_btn:
			buildContacts();
			break;
		}
	}

	public void buildContacts() {
		ArrayList<Contact> list = new ArrayList<Contact>();
		list.add(new Contact(mFirstNameEt.getText().toString(), mFirstEt
				.getText().toString()));
		list.add(new Contact(mSecondNameEt.getText().toString(), mSecondEt
				.getText().toString()));
		list.add(new Contact(mThirdNameEt.getText().toString(), mThirdEt
				.getText().toString()));
		list.add(new Contact(mFourthNameEt.getText().toString(), mFourthEt
				.getText().toString()));
		for (Contact item : list) {
			DebugUtil.Debug(item.toString());
		}
	}

	public void setContactSingle(int index) {

	}


	@Override
	public void onLoadFinished(ArrayList<Contact> items) {
		// TODO Auto-generated method stub
		mFirstEt.setText(items.get(0).getPhone());
		mSecondEt.setText(items.get(1).getPhone());
		mThirdEt.setText(items.get(2).getPhone());
		mFourthEt.setText(items.get(3).getPhone());

		mFirstNameEt.setText(items.get(0).getName());
		mSecondNameEt.setText(items.get(1).getName());
		mThirdNameEt.setText(items.get(2).getName());
		mFourthNameEt.setText(items.get(3).getName());
	}

	@Override
	public void onSetFinished(int index,boolean isSuccessed) {
		// TODO Auto-generated method stub
		String pmtStr = null;
		switch(index){
		case 0:
			pmtStr = "第一联络人";
			break;
		case 1:
			pmtStr = "第二联络人";
			break;
		case 2:
			pmtStr = "第三联络人";
			break;
		case 3:
			pmtStr = "第四联络人";
			break;
		}
		if(isSuccessed){
			Toast.makeText(ContactsActivity.this, pmtStr+"设置成功", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(ContactsActivity.this, pmtStr+"设置失败", Toast.LENGTH_SHORT).show();
		}
	}


}
