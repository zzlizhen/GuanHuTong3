package com.changhongit.guanhutong3.base;

import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.OnInfoWindowClickListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.SupportMapFragment;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.map.utils.CustomInfoWindowAdapter;
import com.changhongit.guanhutong3.map.utils.DebugUtil;
import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.QueryAreas;
import com.changhongit.guanhutong3.utils.pulldata.Status;


public abstract class MapActivity extends BaseActivity implements OnMarkerClickListener, OnInfoWindowClickListener{

	protected MapView mMapView;
	protected AMap mAMap;
	protected Marker amarker;
	/**
	 * 继承这个类的Activity，在正常步骤创建之后再调用iniMap()
	 */
	
    /**
     * 加载完成数据，设置地图
     */
	public void setMap(Location loc, Status status, QueryAreas areas){
        // TODO Auto-generated method stub
        DebugUtil.Debug("loc.getLongitude() === " + loc.getLongitude() + "/loc.getLatitude()===" + loc.getLatitude());
        LatLng location = new LatLng(Double.parseDouble(loc.getLatitude()), Double.parseDouble(loc.getLongitude()));
        if (amarker != null)// 如果开启追踪模式，添加新marker之前清除掉上一个marker
            amarker.remove();
        amarker = mAMap.addMarker(new MarkerOptions()
                .position(location)
                .title(getInfoWindowTitle(loc,status))
                .snippet(getInfoWindowContent(loc,status))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
        mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f));
        // 测试追踪模式
        Toast.makeText(this, "locating", Toast.LENGTH_SHORT).show();
	}
		
	/**
	 * 在onCreate()内调用
	 */
	public void iniMap(){ //地图名字写死，相应在布局文件中地图控件也必须用对应的id和类型
        mAMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        UiSettings mUiSettings = mAMap.getUiSettings();
        mUiSettings.setScaleControlsEnabled(true);
        CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(this);
        mAMap.setInfoWindowAdapter(adapter);
        mAMap.setOnMarkerClickListener(this);
        mAMap.setOnInfoWindowClickListener(this);
	}
	/**
	 * 必须重写
	 */
	@Override
	public abstract void onInfoWindowClick(Marker arg0);
	@Override
	public abstract boolean onMarkerClick(Marker arg0);
	public abstract String getInfoWindowTitle(Location loc, Status status);
	public abstract String getInfoWindowContent(Location loc, Status status);

}
