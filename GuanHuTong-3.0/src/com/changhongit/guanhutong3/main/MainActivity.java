package com.changhongit.guanhutong3.main;

import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.AMap.OnInfoWindowClickListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.Circle;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.PolygonOptions;
import com.changhongit.guanhutong3.R;
import com.changhongit.guanhutong3.alert.AlertActivity;
import com.changhongit.guanhutong3.application.GhtApplication;
import com.changhongit.guanhutong3.area.AreaMainActivity;
import com.changhongit.guanhutong3.base.MapActivity;
import com.changhongit.guanhutong3.call_setting.CallSettingActivity;
import com.changhongit.guanhutong3.contacts.ContactsActivity;
import com.changhongit.guanhutong3.events.EventsListActivity;
import com.changhongit.guanhutong3.events.EventsListPresenterImpl;
import com.changhongit.guanhutong3.health.bloodoxygen.BloodOxygenActivity;
import com.changhongit.guanhutong3.health.bloodpressure.BloodPressureActivity;
import com.changhongit.guanhutong3.health.bloodsugar.BloodSugarActivity;
import com.changhongit.guanhutong3.health.bodycompositions.BodyCompositionsActivity;
import com.changhongit.guanhutong3.map.utils.DebugUtil;
import com.changhongit.guanhutong3.personal_setting.PersonalSettingActivity;
import com.changhongit.guanhutong3.setting.terminal.TerminalListActivity;
import com.changhongit.guanhutong3.setting.terminal.TerminalSettingActivity;
import com.changhongit.guanhutong3.trace.PortalActivity;
import com.changhongit.guanhutong3.utils.GHTUtil;
import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.QueryAreas;
import com.changhongit.guanhutong3.utils.pulldata.Status;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;
import com.changhongit.guanhutong3.utils.spdata.User;
import com.changhongit.guanhutong3.whitelist.WhiteListActivity;

public class MainActivity extends MapActivity implements OnMarkerClickListener,
		OnInfoWindowClickListener, OnClickListener, MainView, OnItemClickListener {
	// private LinearLayout mMenuContentLayout;
	private MainPresenter presenter;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private ListView mlistview;
	private LinearLayout mTerminalNameLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iniMap();
		initView();
		presenter = new MainPresenterImpl(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		iniMap();
		presenter.onResume(); // 调用用户关联的终端列表
		super.onResume();
	}

	@Override
	protected void onPause() {
		presenter.onPause();
		super.onPause();
	}

	private void initView() {
		mTerminalNameLayout = (LinearLayout) findViewById(R.id.terminal_name_layout);
		mTerminalNameLayout.setOnClickListener(this);
		LinearLayout menu = (LinearLayout) findViewById(R.id.bottom_menu_layout);
		Button menu1 = (Button) menu.findViewById(R.id.menu_first_btn);
		menu1.setOnClickListener(this);
		Button menu2 = (Button) menu.findViewById(R.id.menu_second_btn);
		menu2.setOnClickListener(this);
		Button menu3 = (Button) menu.findViewById(R.id.menu_third_btn);
		menu3.setOnClickListener(this);
		Button menu4 = (Button) menu.findViewById(R.id.menu_fourth_btn);
		menu4.setOnClickListener(this);

		LinearLayout switchMenu = (LinearLayout) findViewById(R.id.switch_layout);
		((Button) switchMenu.findViewById(R.id.tracking_button))
				.setOnClickListener(this);
		((Button) switchMenu.findViewById(R.id.locating_button))
				.setOnClickListener(this);
		LinearLayout mdrawer = (LinearLayout) findViewById(R.id.left_drawer);
		mlistview = (ListView) mdrawer.findViewById(R.id.drawer_list);
		mlistview.setAdapter(new DrawerAdapter(this));
		mlistview.setOnItemClickListener(this);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_launcher, R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		((Button) findViewById(R.id.draw_switch))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
							mDrawerLayout.closeDrawer(GravityCompat.START);
						} else {
							mDrawerLayout.openDrawer(GravityCompat.START);
						}
					}

				});
		// mDrawerLayout.openDrawer(GravityCompat.START);
	}

	private List<LatLng> createRectangleNew(LatLng center, double leftXcoord,
			double leftYcoord, double rightXcoord, double rightYcoord) {

		return Arrays.asList(new LatLng(leftYcoord, leftXcoord), new LatLng(
				rightYcoord, leftXcoord), new LatLng(rightYcoord, rightXcoord),
				new LatLng(leftYcoord, rightXcoord));
	}

	/**
	 * 显示防护圈
	 * 
	 * @param info
	 */
	public void setUpAreas(QueryAreas info) {
		Circle c;
		int fillColor_set = Color.HSVToColor(100, new float[] { 1, 1, 100 });
		if (info.isAreaType()) {
			mAMap.addPolygon(new PolygonOptions()
					.addAll(createRectangleNew(
							new LatLng(Double.valueOf(info.getYcoord()), Double
									.valueOf(info.getXcoord())), Double
									.valueOf(info.getLeftXcoord()), Double
									.valueOf(info.getLeftYcoord()), Double
									.valueOf(info.getRightXcoord()), Double
									.valueOf(info.getRightYCoord())))
					.fillColor(fillColor_set).strokeColor(Color.GRAY)
					.strokeWidth(1));
		} else {

			c = mAMap.addCircle(new CircleOptions()
					.center(new LatLng(Double.valueOf(info.getYcoord()), Double
							.valueOf(info.getXcoord())))
					.radius(Double.valueOf(info.getRadius()))
					.strokeColor(Color.GRAY).fillColor(fillColor_set)
					.strokeWidth(3));
		}
		// circleGate(false);
	}

	public boolean isHaveDefTerminal(String userId) {
		boolean isHaveDef = false;
		if (GhtApplication.mSQLiteUtil.ifExists(userId)) {
			User user = GhtApplication.mSQLiteUtil.queryUser(userId);
			for (int i = 0; i < GhtApplication.mTerminals.getlist().size(); i++) {
				if (user.getImei().equals(
						GhtApplication.mTerminals.getlist().get(i).getImei())) {
					isHaveDef = true;
					GhtApplication.mCurrentTerminal = GhtApplication.mTerminals
							.getlist().get(i);
					break;
				}
			}
		}
		return isHaveDef;
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		// TODO Auto-generated method stub
		marker.hideInfoWindow();
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.menu_first_btn:
			presenter.initPopItems(v);
			break;
		case R.id.menu_second_btn:
			// startActivity(new Intent(MainActivity.this,
			// WhiteListActivity.class));
			presenter.initPopItems(v);
			break;
		case R.id.menu_third_btn:
			presenter.initPopItems(v);
			break;
		case R.id.menu_fourth_btn:
			presenter.initPopItems(v);
			break;
		case R.id.tracking_button:
			if (presenter.trackingSwitch())
				Toast.makeText(this, "追踪模式开启", Toast.LENGTH_SHORT)
						.show();
			else
				Toast.makeText(this, "追踪模式关闭", Toast.LENGTH_SHORT)
						.show();
			break;
		case R.id.locating_button:
			break;
		case R.id.terminal_name_layout:
			presenter.initPopItems(v);
			break;
		}
	}

	/**
	 * 点击popupWindow中的item接口
	 * 
	 * @param position
	 *            : 点击项的position
	 */
	@Override
	public void onPopItemClick(int position) {
		// TODO Auto-generated method stub
		GHTUtil.Debug("onPopItemClick ===== " + position);
		Intent intent;
		switch (position) {
		case 0:
			intent = new Intent(MainActivity.this, TerminalSettingActivity.class);
			intent.putExtra("isnew", true);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(MainActivity.this,TerminalListActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(MainActivity.this,
					TerminalSettingActivity.class);
			intent.putExtra("isnew", false);
			startActivity(intent);
			break;
		case 3:
			intent = new Intent(MainActivity.this,ContactsActivity.class);
			startActivity(intent);
			break;
		case 4:
			intent = new Intent(MainActivity.this, WhiteListActivity.class);
			startActivity(intent);
			break;
		case 5:
			intent = new Intent(MainActivity.this,CallSettingActivity.class);
			startActivity(intent);
			break;
		case 6:
			intent = new Intent(MainActivity.this, EventsListActivity.class);
			startActivity(intent);
			break;
		case 7:
			intent = new Intent(MainActivity.this, AreaMainActivity.class);
			startActivity(intent);
			break;
		case 8:
			intent = new Intent(MainActivity.this, PortalActivity.class);
			startActivity(intent);
			break;
		case 9:
			intent = new Intent(MainActivity.this, AlertActivity.class);
			startActivity(intent);
			break;
		case 10:
			intent = new Intent(MainActivity.this, BloodPressureActivity.class);
			startActivity(intent);
			break;
		case 11:
			intent = new Intent(MainActivity.this, BloodSugarActivity.class);
			startActivity(intent);
			break;
		case 12:
			intent = new Intent(MainActivity.this, BloodOxygenActivity.class);
			startActivity(intent);
			break;
		case 13:
			intent = new Intent(MainActivity.this,
					BodyCompositionsActivity.class);
			startActivity(intent);
			break;
		}
		if (position >= 15) {
			GhtApplication.mCurrentTerminal = GhtApplication.mTerminals
					.getitem(position - 15);
			((TextView) findViewById(R.id.terminarl_name_tv))

			.setText(GhtApplication.mCurrentTerminal.getTerminal());
			User user = new User();
			user.setUserId(GhtApplication.mUserId);
			user.setImei(GhtApplication.mCurrentTerminal.getImei());
			user.setNickName(GhtApplication.mCurrentTerminal.getTerminal());
			GhtApplication.mSQLiteUtil.updateUser(user);
			presenter.locate(GhtApplication.mCurrentTerminal.getImei());
		}

		// DebugUtil.show(this, "id = = = " + position);
	}

	/**
	 * 加载完成数据，设置地图
	 */
	@Override
	public void setMap(Location loc, Status status, QueryAreas areas) {
		// TODO Auto-generated method stub
		if (loc != null) {
			DebugUtil.Debug("loc.getLongitude() === " + loc.getLongitude()
					+ "/loc.getLatitude()===" + loc.getLatitude());

			LatLng location = new LatLng(Double.parseDouble(loc.getLatitude()),
					Double.parseDouble(loc.getLongitude()));
			GhtApplication.location_marker = location;
			if (amarker != null)// 如果开启追踪模式，添加新marker之前清除掉上一个marker
				amarker.remove();
			if (mAMap != null) {
				amarker = mAMap.addMarker(new MarkerOptions()
						.position(location)
						.title(getInfoWindowTitle(loc, status))
						.snippet(getInfoWindowContent(loc, status))
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.old_man_marker)));
				mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,
						12f));
			}
			// 测试追踪模式
		}
//		Toast.makeText(this, "locating", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void setTerminals(UserTerminalList terminals) {
		// TODO Auto-generated method stub
		GhtApplication.mTerminals = terminals;
		if (!isHaveDefTerminal(GhtApplication.mUserId)) {
			GhtApplication.mCurrentTerminal = terminals.getlist().get(0);
			User user = new User();
			user.setUserId(GhtApplication.mUserId);
			user.setImei(GhtApplication.mCurrentTerminal.getImei());
			user.setNickName(GhtApplication.mCurrentTerminal.getTerminal());
			GhtApplication.mSQLiteUtil.addUser(user);
		}

		((TextView) findViewById(R.id.terminarl_name_tv))
				.setText(GhtApplication.mCurrentTerminal.getTerminal());
		presenter.loadTerminalInfos(GhtApplication.mCurrentTerminal.getImei()); // 获取当前选中的终端信息
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
		       Intent home = new Intent(Intent.ACTION_MAIN);
               home.addCategory(Intent.CATEGORY_HOME);
               startActivity(home);
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void setLocation(Location loc) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getInfoWindowTitle(Location loc, Status status) {
		// TODO Auto-generated method stub
		return "终端号:" + status.getImei();
	}

	@Override
	public String getInfoWindowContent(Location loc, Status status) {
		// TODO Auto-generated method stub
		String listenStr = null;
		if(status.isListenset()){
			listenStr = "所有电话";
		}
		else{
			listenStr = "白名单电话";
		}
		String autoStr = null;
		if(status.isAutolisten()){
			autoStr = "打开";
		}
		else{
			autoStr = "关闭";
		}
		String chargeStyle = null;
		if(status.isCash()){
			chargeStyle = "充电中";
		}
		else{
			chargeStyle = "未充电";
		}
		String powerStr = null;
		if(status.getPower()==null || "".equals(status.getPower())){
			powerStr = "未知";
		}
		else{
		switch(Integer.valueOf(status.getPower())){
		case 0:
			powerStr = "小于10%";
			break;
		case 1:
			powerStr = "20%";
			break;
		case 2:
			powerStr = "40%";
			break;
		case 3:
			powerStr = "60%";
			break;
		case 4:
			powerStr = "80%";
			break;
		case 5:
			powerStr = "100%";
			break;
		}
		}
		return "有效期:" + status.getValidTimeToString() + '\n' + "充电方式:"
				+ chargeStyle + '\n' + "状态:" + status.getStateToString()
				+ '\n' + "电量:" + powerStr + '\n' + "定位时间:"
				+ loc.getLocationTimeToString() + '\n' + "来电接听方式:"
				+ listenStr + '\n' + "自动接听:" + autoStr;
	}

	//侧滑菜单选中事件
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent;
		switch(position){
		case 0:   //个人设置
			intent = new Intent(MainActivity.this, PersonalSettingActivity.class);
			startActivity(intent);
			break;
		case 1:
			break;
		}
		
	}
}
