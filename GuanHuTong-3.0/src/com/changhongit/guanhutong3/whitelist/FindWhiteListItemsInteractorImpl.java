/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.changhongit.guanhutong3.whitelist;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.utils.HttpUtil;
import com.changhongit.guanhutong3.utils.XMLPullUtil;
import com.changhongit.guanhutong3.utils.pulldata.WhiteListInfo;
import com.changhongit.guanhutong3.webservice.WebService;

public class FindWhiteListItemsInteractorImpl implements FindWhiteListItemsInteractor {
	private List<WhiteListInfo> mList;
	private onLoadFinishedListener mListener;
	private final int GET_DATA_OK = 0;
	private final int GET_DATA_ERROR = 1;
	private final int DELETE_OK = 2;
	private final int DELETE_ERROR = 3;
	
	public FindWhiteListItemsInteractorImpl(onLoadFinishedListener listener) {
		super();
		// TODO Auto-generated constructor stub
		mListener = listener;
		mList = new ArrayList<WhiteListInfo>();
	}

	@Override
	public void findItems() {
		getWhiteListThread();
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case GET_DATA_OK:
				try {
					if(mList!=null){
						mList.clear();
						mList.addAll(XMLPullUtil.getinstance().parserWhiteList(msg.getData().getString("result")));
					}
			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mListener.onLoadFinished(mList);
				break;
			case GET_DATA_ERROR://获取列表失败报错
				mListener.onReturnStatus(0);
				break;
			case DELETE_OK://删除成功
				mListener.onReturnStatus(1);
				break;
			case DELETE_ERROR://删除失败报错
				mListener.onReturnStatus(2);
				break;
			}
		};
	};

	public void getWhiteListThread(){
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				String result = HttpUtil.getinstance().RequestWhiteList(GhtApplication.mCurrentTerminal.getImei());
				if(result!=null){
					Message msg = Message.obtain();
					msg.what = GET_DATA_OK;
					Bundle bundle = new Bundle();
					bundle.putString("result", result);
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
				else{
					handler.sendEmptyMessage(GET_DATA_ERROR);
				}
			}
		}.start();
	}
	@Override
	public WhiteListInfo getItems(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(final int position) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				if(HttpUtil.getinstance().deleteWhiteList(GhtApplication.mCurrentTerminal.getImei(), mList.get(position).getId())){
					handler.sendEmptyMessage(DELETE_OK);
				}
				else {
					handler.sendEmptyMessage(DELETE_ERROR);
				}
			}
		}.start();
	}
	
	
	
}
