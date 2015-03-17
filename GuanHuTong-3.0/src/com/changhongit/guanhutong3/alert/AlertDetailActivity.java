package com.changhongit.guanhutong3.alert;

import java.text.ParseException;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.base.MapActivity;
import com.changhongit.guanhutong3.utils.pulldata.Alert;
import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.QueryAreas;
import com.changhongit.guanhutong3.utils.pulldata.Status;

public class AlertDetailActivity extends MapActivity implements OnClickListener{
	
	Alert mAlert;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alert_detail);
		iniMap();
		iniview();
		setData();
	}
	
	private void iniview(){
		((LinearLayout)findViewById(R.id.return_layout)).setOnClickListener(this);
	}
	
	private void setData(){
		mAlert  = getIntent().getExtras().getParcelable("alert");
		Location location = new Location();
		try {
			location.setLongitude(mAlert.getLongitude());
			location.setLocationtime(mAlert.getLatitude());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setMap(null,null,null);
	}
	
	@Override
	public void setMap(Location loc, Status status, QueryAreas areas){
        // TODO Auto-generated method stub
        LatLng location = new LatLng(Double.parseDouble(mAlert.getLatitude()), Double.parseDouble(mAlert.getLongitude()));
        amarker = mAMap.addMarker(new MarkerOptions()
                .position(location)
                .title(getInfoWindowTitle(null,null))
                .snippet(getInfoWindowContent(null,null))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
        mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f));
        amarker.showInfoWindow();
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.return_layout:
			this.finish();
			break;
		}
	}

	@Override
	public String getInfoWindowTitle(Location loc, Status status) {
		// TODO Auto-generated method stub
		return "终端名称: " + mAlert.getDevicename();
	}

	@Override
	public String getInfoWindowContent(Location loc, Status status) {
		// TODO Auto-generated method stub
		return "报警类型: " + mAlert.getType() +'\n'
				+ "报警时间: " +mAlert.getTime();
	}

}
