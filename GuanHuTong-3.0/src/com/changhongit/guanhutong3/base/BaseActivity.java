package com.changhongit.guanhutong3.base;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;

public class BaseActivity extends FragmentActivity{
	
	private ProgressDialog mWaitDialog;
	
	/**
	 * 显示隐藏加载窗口
	 * @param show  显示或隐藏窗
	 */
	public void waitDialog(boolean show) {
		if (show) {
			if (mWaitDialog == null) {
				mWaitDialog = new ProgressDialog(this);
				mWaitDialog.setMessage("正在加载......");
				mWaitDialog.setCanceledOnTouchOutside(false);
				mWaitDialog.setCancelable(true);
			}
			mWaitDialog.show();
		} else {
			if (mWaitDialog != null && mWaitDialog.isShowing()) {
				try {
					mWaitDialog.dismiss();
				} catch (Exception e) {
					mWaitDialog.dismiss();
				}
			}
		}
	}

}
