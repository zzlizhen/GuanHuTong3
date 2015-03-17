package com.changhongit.guanhutong3.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.changhongit.guanhutong3.R;



import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PopWinDown extends PopupWindow {

	protected final View anchor;
	private View mRoot;
	private final static int MIN_INTERVAL = 500;
	private final int maxHeight = 500;//popwin
	protected final WindowManager windowManager;
	
	private Context mContext;
	private LayoutInflater mInflater;
	private RadioGroup mGroup;
	private OnCheckedChangeListener onCheckedChangeListener;
	private static long sTime;
	
	List<String> mItems;
	private int yPos;
	
	public PopWinDown(View anchor) {
		super(anchor);
		this.anchor = anchor;
		mItems = new ArrayList<String>();
		
		// when a touch even happens outside of the window
		// make the window go away
		setTouchInterceptor(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					dismiss();
					return true;
				}
				return false;
			}
		});
		windowManager = (WindowManager) anchor.getContext().getSystemService(Context.WINDOW_SERVICE);
		mContext = anchor.getContext();
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = mInflater.inflate(R.layout.popup, null);
		this.setContentView(mRoot);
		mGroup = (RadioGroup) mRoot.findViewById(R.id.group);
	}
	
	public static PopWinDown getInstance(View anchor){
		
		if(new Date().getTime() - sTime < MIN_INTERVAL){
			//avoid two popWin exist at same time
			return null;
		} else {
			sTime = new Date().getTime();
			return new PopWinDown(anchor);
		}
	}
	
	protected void preShow(){
		this.preShow(0);
	}
	
	protected void preShow(int height) {
		if (mRoot == null) {
			throw new IllegalStateException("setContentView was not called with a view to display.");
		}

		setBackgroundDrawable(new BitmapDrawable());
		// if using PopupWindow#setBackgroundDrawable this is the only values of the width and hight that make it work
		// otherwise you need to set the background of the root viewgroup
		// and set the popupwindow background to an empty BitmapDrawable
//		setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
		setWidth(anchor.getWidth());
		if(height > maxHeight){
			setHeight(maxHeight);
		} else {
			setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
		}
		setTouchable(true);
		setFocusable(true);
		setOutsideTouchable(true);
		setContentView(mRoot);
	}

	/**
	 * Will inflate and set the view from a resource id
	 * 
	 * @param layoutResID
	 */
	public void setContentView(int layoutResID) {
		LayoutInflater inflator =
				(LayoutInflater) anchor.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		super.setContentView(inflator.inflate(layoutResID, null));
	}

	/**
	 * If you want to do anything when {@link dismiss} is called
	 * 
	 * @param listener
	 */
	public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
		setOnDismissListener(listener);
	}
	
	public void show(){
		
		setPopItems();
		
		mRoot.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mRoot.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		int rootHeight   = mRoot.getMeasuredHeight();
//		int rootWidth    = mRoot.getMeasuredWidth();

		preShow(rootHeight);
		
		int xPos;
		int[] location = new int[2];
		
		anchor.getLocationOnScreen(location);
		
		Rect anchorRect = new Rect(location[0], location[1], location[0]
				+ anchor.getWidth(), location[1] + anchor.getHeight());
		
		xPos = anchorRect.left+8 ;
  
		yPos =location[1] - rootHeight;
		
		setAnimationStyle();
		
		showAtLocation(anchor, Gravity.NO_GRAVITY, xPos, yPos);
	}
	
	private void setPopItems(){
		
		LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		mGroup.setGravity(Gravity.CENTER_HORIZONTAL);
		for(int i=0; i<mItems.size();i++){
			if(i != 0){
				ImageView img = new ImageView(mContext);
				img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,10));
				img.setImageResource(R.drawable.pop_bg_line);
				img.setScaleType(ScaleType.FIT_XY);
				mGroup.addView(img, lp);
			}
			RadioButton rb = getPopItemView(i,mItems.get(i));
			if(onCheckedChangeListener != null){
				rb.setOnCheckedChangeListener(onCheckedChangeListener);
			}
//			LayoutParams lp2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
			mGroup.addView(rb);
		}
	}
	
	private RadioButton getPopItemView(int id, String item){
		RadioButton radioButton = new RadioButton(mContext);
		radioButton.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,80));
		radioButton.setId(id);
		radioButton.setText(item);
		radioButton.setTextColor(Color.BLACK);
		radioButton.setGravity(Gravity.CENTER);
//		radioButton.setTextAppearance(mContext, android.R.style.TextAppearance_DeviceDefault_Medium);
		radioButton.setPadding(10, 0, 10, 0);
		radioButton.setButtonDrawable(android.R.color.transparent);
		return radioButton;
	}
	
	private void setAnimationStyle(){
		
		setAnimationStyle(R.style.Animations_PopDownMenu_Center);
	}
	
	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public void setyPos(View view) {
		int[] location = new int[2];
		view.getLocationOnScreen(location);
		this.yPos = location[1] - view.getHeight();
	}

	public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
		this.onCheckedChangeListener = onCheckedChangeListener;
	}

	public void setItems(List<String> items) {
		this.mItems = items;
	}
	
	public void addItem(String item){
		mItems.add(item);
	}
	public void clearItems(){
		mItems.clear();
	}

}
