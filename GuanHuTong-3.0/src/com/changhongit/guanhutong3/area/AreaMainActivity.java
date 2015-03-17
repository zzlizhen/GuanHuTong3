package com.changhongit.guanhutong3.area;

import java.util.Arrays;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.AMap.OnMapClickListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.Circle;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.PolygonOptions;
import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.base.MapActivity;
import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.QueryAreas;
import com.changhongit.guanhutong3.utils.pulldata.Status;

public class AreaMainActivity extends MapActivity implements AreaView, OnClickListener
	, OnMapClickListener,OnMarkerClickListener{
	private LinearLayout mReturnLayout;
	private AreaPresenter presenter;
	double mapX;// 防护圈中心经度
	double mapY;// 防护圈中心维度
	double mapR = 0;// 防护圈半径
	Marker locationMarker;// 终端位置
	Circle circle;
	
	int fillColor_set = Color.HSVToColor(0, new float[] { 0, 0, 0 });
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_area);
		iniMap(); //初始化地图
		presenter = new AreaPresenterImpl(this);
		initView();
	}
	
	private void initView(){
		mReturnLayout = (LinearLayout) findViewById(R.id.return_layout);
		mReturnLayout.setOnClickListener(this);
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		presenter.onResume();
	}
	
	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getInfoWindowTitle(Location loc, Status status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfoWindowContent(Location loc, Status status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQuery(QueryAreas area) {
		// TODO Auto-generated method stub
		displayArea(area);
	}
	
	/**
	 * 在地图上显示防护圈
	 * 
	 * @param info
	 */
	public void displayArea(QueryAreas info) {
		if (info.isAreaType()) {
			mAMap.addPolygon(new PolygonOptions()
					.addAll(createRectangleNew(
							new LatLng(Double.valueOf(info.getYcoord()), Double
									.valueOf(info.getXcoord())), Double
									.valueOf(info.getLeftXcoord()), Double
									.valueOf(info.getLeftYcoord()), Double
									.valueOf(info.getRightXcoord()), Double
									.valueOf(info.getRightYCoord())))
					.fillColor(fillColor_set).strokeColor(Color.BLUE)
					.strokeWidth(5));
		} else {
			mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GhtApplication.location_marker, 12f));
			amarker = mAMap.addMarker(new MarkerOptions()
			.position(GhtApplication.location_marker)
			.icon(BitmapDescriptorFactory
					.fromResource(R.drawable.old_man_marker)));
			circle = mAMap.addCircle(new CircleOptions()
					.center(new LatLng(Double.valueOf(info.getYcoord()), Double
							.valueOf(info.getXcoord())))
					.radius(Double.valueOf(info.getRadius()))
					.strokeColor(0xFF429ee8).fillColor(fillColor_set)
					.strokeWidth(5));
		}
		circleGate(false);
	}

	
	// 清空防护圈
	private void circleGate(boolean gate) {
		if (gate) {
			mAMap.clear();
			mAMap.setOnMapClickListener(this);
			mAMap.setOnMarkerClickListener(this);
			mapX = 0.0;
			mapY = 0.0;
			mapR = 0;
			com.amap.api.maps2d.model.MarkerOptions mo = new MarkerOptions().position(
					GhtApplication.location_marker).icon(
					BitmapDescriptorFactory.fromAsset("person_online.png"));
			locationMarker = mAMap.addMarker(mo);

		} else {
			mAMap.setOnMapClickListener(null);
			mAMap.setOnMarkerClickListener(null);
		}
	}
	
	//创建方型
	private List<LatLng> createRectangleNew(LatLng center, double leftXcoord,
			double leftYcoord, double rightXcoord, double rightYcoord) {

		return Arrays.asList(new LatLng(leftYcoord, leftXcoord), new LatLng(
				rightYcoord, leftXcoord), new LatLng(rightYcoord, rightXcoord),
				new LatLng(leftYcoord, rightXcoord));
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.return_layout:
			finish();
			break;
			
		}
		
	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		
	}



}
