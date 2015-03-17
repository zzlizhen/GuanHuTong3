package com.changhongit.guanhutong3.events;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.utils.pulldata.Reminder;

public class EventsAdapter extends BaseAdapter {
	List<Reminder> list;
	Context context;
	
	class ViewHolder{
		TextView event_name_tv;
		TextView event_type_tv;
		TextView event_time_tv;
	}

	public EventsAdapter(Context context,List<Reminder> list) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Reminder getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder iViewHolder = null;
		if(convertView == null){
			convertView  = LayoutInflater.from(context).inflate(R.layout.item_events_layout, null);
			iViewHolder = new ViewHolder();
			iViewHolder.event_name_tv = (TextView) convertView.findViewById(R.id.tv_events_name);
			iViewHolder.event_type_tv = (TextView) convertView.findViewById(R.id.tv_events_type);
			convertView.setTag(iViewHolder);
		}
		else{
			iViewHolder =  (ViewHolder) convertView.getTag();
		}
		iViewHolder.event_name_tv.setText(list.get(position).getIndexNum());
		switch(list.get(position).getReminderType()){
		case 0:     //仅一次
			iViewHolder.event_type_tv.setText("仅一次");
			break;
		case 1:     //每天
			iViewHolder.event_type_tv.setText("每天");
			break;
		case 2:     //每周
			iViewHolder.event_type_tv.setText("每周");
			break;
		}
		
		return convertView;
	}

}
