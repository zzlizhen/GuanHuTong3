package com.changhongit.guanhutong3.trace;

import android.widget.Toast;

import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.LocationHistoryList;



public class TracePresenterImpl implements TracePresenter, onTraceLoadedListener {

    private TraceModel mModel = null;
    private TraceView mView = null;
    private boolean playing = false;//是否在播放状态

    public TracePresenterImpl(TraceView view) {
        this.mModel = new TraceModelImpl(this);
        this.mView = view;
    }
    
    public void loadHistory(String startTime,String endTime,String imei){
    	((TraceActivity)mView).waitDialog(true);
    	mModel.loadHistory(startTime,endTime,imei);
    }

	@Override
	public boolean playSwitch() {
		// TODO Auto-generated method stub
		playing = mModel.playSwitch();
		return playing;
	}
    
    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        if (playing)
            mModel.playSwitch();// 返回时调用，如果中断之前在播放，则重新开始播放
    }

	@Override
	public void OnPause() {
		// TODO Auto-generated method stub
        if (playing)
            mModel.playSwitch();// 中断时调用，如果在播放则停止，保存播放状态
	}

	@Override
	public void onFinish(LocationHistoryList list) {
		// TODO Auto-generated method stub
        mView.iniView();
		((TraceActivity)mView).waitDialog(false);
		((TraceActivity)mView).setMap(list.getitem(0), null, null);
		((TraceActivity)mView).setTrace();
	}

	@Override
	public void jumpto(int progress) {//从model中获取该点坐标，给activity显示
		// TODO Auto-generated method stub
		((TraceActivity)mView).setMap(mModel.jumpto(progress), null, null);
	}

	@Override
	public void onNext(int progress,Location loc) {//model中计时器执行“下一个点”操作时执行
		// TODO Auto-generated method stub
		mView.setProgress(progress,loc);
		((TraceActivity)mView).setMap(loc, null, null);
		mView.nextTrace();
	}

	@Override
	public void setPeriod(int period) {
		// TODO Auto-generated method stub
		mModel.setPeriod(period);
	}

	@Override
	public void onEmptyList() {
		// TODO Auto-generated method stub
		((TraceActivity)mView).waitDialog(false);
		Toast.makeText((TraceActivity)mView, "数据为空，请检查查找的时间", Toast.LENGTH_LONG).show();
	}	
	
	@Override
	public void onNetWorkError() {
		// TODO Auto-generated method stub
		((TraceActivity)mView).waitDialog(false);
		Toast.makeText((TraceActivity)mView, "请求数据失败，请检查网络", Toast.LENGTH_LONG).show();
	}

	@Override
	public LocationHistoryList getlist() {
		// TODO Auto-generated method stub
		return mModel.getList();
	}

	@Override
	public void setSignal(boolean signal) {
		// TODO Auto-generated method stub
		mModel.setSignal(signal);
	}

}
