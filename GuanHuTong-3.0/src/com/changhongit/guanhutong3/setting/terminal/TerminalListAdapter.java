package com.changhongit.guanhutong3.setting.terminal;

import java.util.ArrayList;
import java.util.List;

import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.utils.pulldata.Terminal;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminal;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;
import com.changhongit.guanhutong3.utils.pulldata.WhiteListInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TerminalListAdapter extends BaseAdapter {
	UserTerminalList list;
	Context context;
	
	
	public TerminalListAdapter(Context context,UserTerminalList list) {
		super();
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
	}

	class ViewHolder{
		TextView name;
		TextView phone_num;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.getlist().size();
	}

	@Override
	public UserTerminal getItem(int position) {
		// TODO Auto-generated method stub
		return list.getlist().get(position);
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
			convertView  = LayoutInflater.from(context).inflate(R.layout.item_whitelist_layout, null);
			iViewHolder = new ViewHolder();
			iViewHolder.name = (TextView) convertView.findViewById(R.id.wl_name_tv);
			iViewHolder.phone_num = (TextView) convertView.findViewById(R.id.wl_phone_tv);
			convertView.setTag(iViewHolder);
		}
		else{
			iViewHolder =  (ViewHolder) convertView.getTag();
		}
		iViewHolder.name.setText(list.getlist().get(position).getTerminal());
//		iViewHolder.phone_num.setText(list.get(position).getPhone());
		
		return convertView;
	}

	public List<UserTerminal> getList() {
		return list.getlist();
	}

	public void setList(UserTerminalList list) {
		this.list = list;
	}

}
