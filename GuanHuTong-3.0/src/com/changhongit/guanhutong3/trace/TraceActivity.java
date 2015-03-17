package com.changhongit.guanhutong3.trace;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;

import com.amap.api.maps2d.AMap.OnInfoWindowClickListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.base.MapActivity;
import com.changhongit.guanhutong3.map.utils.DebugUtil;
import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.QueryAreas;
import com.changhongit.guanhutong3.utils.pulldata.Status;

public class TraceActivity extends MapActivity implements TraceView, OnClickListener,OnSeekBarChangeListener,OnItemSelectedListener,OnMarkerClickListener, OnInfoWindowClickListener{
	
	private SeekBar seekbar;
	private Spinner spinner;
	
	Polyline polyline;
    TracePresenter presenter = null;
    String startTime;
    String endTime;
    int progress;
    Polyline line = null;
    List<LatLng> latlnglist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace);
        iniMap();
        startTime = getIntent().getStringExtra("starttime");
        endTime = getIntent().getStringExtra("endtime");
        presenter = new TracePresenterImpl(this);
        presenter.loadHistory(startTime,endTime,GhtApplication.mCurrentTerminal.getImei());
    }
    
    @Override
    public void iniView(){
    	((Button)findViewById(R.id.btn_trace_previous)).setOnClickListener(this);
    	((Button)findViewById(R.id.btn_trace_next)).setOnClickListener(this);
    	((Button)findViewById(R.id.btn_trace_play)).setOnClickListener(this);
    	seekbar = (SeekBar)findViewById(R.id.traceSeekbar);
    	seekbar.setOnSeekBarChangeListener(this);
        seekbar.setMax(presenter.getlist().gettotalcount());
    	spinner = (Spinner)findViewById(R.id.spn_speed);
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.spn_speed_array));
    	spinner.setAdapter(adapter);
    	spinner.setOnItemSelectedListener(this);
    }
    
    @Override
	public void setMap(Location loc, Status status, QueryAreas areas){
        // TODO Auto-generated method stub
        DebugUtil.Debug("loc.getLongitude() === " + loc.getLongitude() + "/loc.getLatitude()===" + loc.getLatitude());
        LatLng location = new LatLng(Double.parseDouble(loc.getLatitude()), Double.parseDouble(loc.getLongitude()));
        if(amarker==null){
        amarker = mAMap.addMarker(new MarkerOptions()
                .position(location)
                .title(loc.getLocationTimeToString())
                .snippet("")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
        }
        else{
        amarker.setPosition(location);
        amarker.setTitle(loc.getLocationTimeToString());
        amarker.setSnippet("");
        }
        mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f));
        amarker.showInfoWindow();
	}

    @Override
    public void setTrace() {
        // TODO Auto-generated method stub
    	Log.v("TraceActivity", String.valueOf(presenter.getlist().gettotalcount()));
    	if(line==null)
    	line = super.mAMap.addPolyline(new PolylineOptions().add(new LatLng(43.828, 87.621), new LatLng(45.808, 126.55)));
    	latlnglist= new ArrayList<LatLng>();
    	for(int i = 0;i<presenter.getlist().gettotalcount();i++){
        	latlnglist.add(new LatLng(Double.parseDouble(presenter.getlist().getitem(i).getLatitude()), Double.parseDouble(presenter.getlist().getitem(i).getLongitude())));
    	}
    	if(progress<1){
    		line.setVisible(false);
    	}
    	if(progress>=1){
    		presenter.setSignal(false);
    		line.setVisible(true);
        line.setPoints(latlnglist.subList(0, progress));
        presenter.setSignal(true);;
    	}
    }
    
    @Override
    public void nextTrace(){
    	if(progress>=1){
    		if(line==null)line = super.mAMap.addPolyline(new PolylineOptions().add(new LatLng(43.828, 87.621), new LatLng(45.808, 126.55)));
    		line.setPoints(latlnglist.subList(0, progress));
    		seekbar.setProgress(progress);
    	}
    	
    	
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
    public void onResume() {
        // TODO Auto-generated method stub
    	presenter.onResume();
    	super.onResume();
    }

    @Override
    public void onPause() {
    	presenter.OnPause();
    	super.onPause();
    }

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		this.progress = progress;
		presenter.jumpto(progress);
		}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		presenter.OnPause();
		// TODO Auto-generated method stub
	}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		presenter.onResume();
		setTrace();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_trace_previous:
			presenter.jumpto(--progress);
			seekbar.setProgress(progress);
			break;
		case R.id.btn_trace_play:
			if(presenter.playSwitch()){
				((Button)v).setText("暂停");
			}
			else{
				((Button)v).setText("播放");
			}
			break;
		case R.id.btn_trace_next:
			presenter.jumpto(++progress);
			seekbar.setProgress(progress);
			break;
		}
	}

	@Override
	public void setProgress(int progress, Location loc) {
		// TODO Auto-generated method stub
		this.progress = progress;
		seekbar.setProgress(progress);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch(position){
		case 0:
			presenter.setPeriod(1000);
			break;
		case 1:
			presenter.setPeriod(800);
			break;
		case 2:
			presenter.setPeriod(600);
			break;
		case 3:
			presenter.setPeriod(400);
			break;
		case 4:
			presenter.setPeriod(200);
			break;
		}
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}
	
}
