package com.changhongit.guanhutong3.alert;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.utils.pulldata.Alert;
import com.changhongit.guanhutong3.utils.pulldata.AlertList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AlertAdapter extends BaseAdapter{
	
	AlertList list = new AlertList();
	Context context = null;
	
	public AlertAdapter(Context context){
		this.context = context;
	}
	
	class ViewHolder{
		TextView name;
		TextView type;
		TextView time;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.getlist().size();
	}

	@Override
	public Alert getItem(int position) {
		// TODO Auto-generated method stub
		return list.getlist().get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub		
		ViewHolder iViewHolder = null;
		if(convertView == null){
			convertView  = LayoutInflater.from(context).inflate(R.layout.item_alert_layout, null);
			iViewHolder = new ViewHolder();
			iViewHolder.name = (TextView) convertView.findViewById(R.id.tv_alert_name);
			iViewHolder.type = (TextView) convertView.findViewById(R.id.tv_alert_type);
			iViewHolder.time = (TextView) convertView.findViewById(R.id.tv_alert_time);
			convertView.setTag(iViewHolder);
		}
		else{
			iViewHolder =  (ViewHolder) convertView.getTag();
		}
		iViewHolder.name.setText(list.get(position).getDevicename());
		iViewHolder.type.setText(list.get(position).getType());
		iViewHolder.time.setText(list.get(position).getTime());
		return convertView;
	}
	
	public void addList(AlertList temp){
		list.addList(temp);
	}
	
	public void clear(){
		list = new AlertList();
	}

}
