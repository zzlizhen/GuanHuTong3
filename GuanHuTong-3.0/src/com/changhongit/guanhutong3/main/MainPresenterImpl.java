package com.changhongit.guanhutong3.main;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.map.utils.DebugUtil;
import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.QueryAreas;
import com.changhongit.guanhutong3.utils.pulldata.Status;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;
import com.changhongit.guanhutong3.view.PopWinDown;
import com.changhongit.guanhutong3.view.PopWinUp;

public class MainPresenterImpl implements MainPresenter, onLoadFinishedListener {
    List<String> mPopItems = new ArrayList<String>();
    MainView mView;
    MainModel mModle;
    PopWinDown popWin;
    PopWinUp popWinUp;
    static boolean tracking = false;// 当前是否要追踪

    public MainPresenterImpl(MainView view) {
        super();
        // TODO Auto-generated constructor stub
        mView = view;
        mModle = new MainModelImpl(this);
    }

    void showPopWindow(View view, List<String> list, OnCheckedChangeListener listener) {
        popWin = PopWinDown.getInstance(view);
        if (popWin != null) {
            popWin.setOnCheckedChangeListener(listener);
            popWin.setItems(list);
            popWin.setyPos(view);
            popWin.show();
        }
    }
    
    void showPopWindowUp(View view, List<String> list, OnCheckedChangeListener listener) {
    	popWinUp = PopWinUp.getInstance(view);
        if (popWinUp != null) {
        	popWinUp.setOnCheckedChangeListener(listener);
        	popWinUp.setItems(list);
        	popWinUp.setyPos(view);
        	popWinUp.show();
        }
    }

    @Override
    public void initPopItems(View view) {
        // TODO Auto-generated method stub
        switch (view.getId()) {
        case R.id.menu_first_btn:
            mPopItems.clear();
            mPopItems.add("新增终端");
            mPopItems.add("删除终端");
            mPopItems.add("编辑终端");
            break;
        case R.id.menu_second_btn:
        	 mPopItems.clear();
             mPopItems.add("联络人");
             mPopItems.add("白名单");
             mPopItems.add("接听设置");
             mPopItems.add("事件提醒");
             mPopItems.add("电子围栏");
            break;
        case R.id.menu_third_btn:
            mPopItems.clear();
            mPopItems.add("轨迹回放");
            mPopItems.add("报警信息");
            break;
        case R.id.menu_fourth_btn:
            mPopItems.clear();
            mPopItems.add("血压数据");
            mPopItems.add("血糖数据");
            mPopItems.add("血氧数据");
            mPopItems.add("体脂数据");
            mPopItems.add("健康设置");
            break;
        case R.id.terminal_name_layout:
        	  mPopItems.clear();
        	  for(int i = 0;i<GhtApplication.mTerminals.getlist().size();i++){
        		  mPopItems.add(GhtApplication.mTerminals.getlist().get(i).getTerminal());
        	  }
        	break;
        }
        if(view.getId()!=R.id.terminal_name_layout){
        	showPopWindow(view, mPopItems, initListener(view));
        }
        else{
        	showPopWindowUp(view, mPopItems, initListener(view));
        }
    }

    public OnCheckedChangeListener initListener(final View v) {
        int position = 0;
        switch (v.getId()) {
        case R.id.menu_first_btn:
            position = 0;
            break;
        case R.id.menu_second_btn:
            position = 3;
            break;
        case R.id.menu_third_btn:
            position = 8;
            break;
        case R.id.menu_fourth_btn:
            position = 10;
            break;
        case R.id.terminal_name_layout:
        	position = 15;
        }
        final int tmp = position;
        OnCheckedChangeListener checkListener = new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mView.onPopItemClick((tmp + buttonView.getId()));
                if(v.getId()!=R.id.terminal_name_layout){
                	popWin.dismiss();
                }
                else{
                	popWinUp.dismiss();
                }
            };
        };

        return checkListener;
    }

    void initItems(ArrayList<String> list) {
        if (mPopItems == null) {
            mPopItems = new ArrayList<String>();
        } else {
            mPopItems.clear();
        }
        for (String item : list) {
            mPopItems.add(item);
        }
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        mModle.loadUserTerminalData();
        if (tracking)
            mModle.TrackingSwitch();// 返回时调用，如果中断之前在追踪模式，则重新开始追踪
    }

    @Override
    public void onPause() {
        if (tracking)
            mModle.TrackingSwitch();// 中断时调用，如果在追踪则停止，保存追踪状态
    }

    @Override
    public void onLoadFinished(UserTerminalList teminals) {
        // TODO Auto-generated method stub
        mView.setTerminals(teminals);
    }

    @Override
    public void loadTerminalInfos(String imei) {
        // TODO Auto-generated method stub
    	DebugUtil.Debug("imei ==== " + imei);
        mModle.loadCurrentTerminalInfo(imei);
    }

    @Override
    public void onLocationFinished(Location loc, Status status, QueryAreas areas) {
        // TODO Auto-generated method stub
        ((MainActivity)mView).setMap(loc, status, areas);
    }

    @Override
    public boolean trackingSwitch() { // 仅限用户调用
        // TODO Auto-generated method stub
        tracking = mModle.TrackingSwitch();
        return tracking;
    }

	@Override
	public void locate(String imei) {
		// TODO Auto-generated method stub
		mModle.loadCurrentTerminalInfo(imei);
	}

	@Override
	public void onNetWorkError() {
		// TODO Auto-generated method stub
		Toast.makeText((MainActivity)mView, "请求位置失败，请检查网络", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onEmptyData() {
		// TODO Auto-generated method stub
		Toast.makeText((MainActivity)mView, "请求终端数据为空，请检查网络", Toast.LENGTH_LONG).show();
	}
}
