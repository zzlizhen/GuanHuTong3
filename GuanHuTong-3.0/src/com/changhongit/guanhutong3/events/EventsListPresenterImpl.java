package com.changhongit.guanhutong3.events;

import java.util.List;

import com.changhongit.guanhutong3.utils.pulldata.Reminder;

public class EventsListPresenterImpl implements EventsListPresenter,onLoadFinishedListener{

	EventsView mView;
	EventsModel mModel;
	
	
	public EventsListPresenterImpl(EventsView mView) {
		super();
		this.mView = mView;
		mModel = new EventsModelImpl(this);
	}
	


	@Override
	public void onLoadFinished(List<Reminder> items) {
		// TODO Auto-generated method stub
		mView.setItems(items);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		mModel.findItems();
	}

	@Override
	public void onItemClicked(int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int position) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Reminder getItem(int position) {
		// TODO Auto-generated method stub
		return mModel.getItem(position);
	}

}
