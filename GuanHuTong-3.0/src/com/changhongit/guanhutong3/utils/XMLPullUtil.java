package com.changhongit.guanhutong3.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Xml;

import com.changhongit.guanhutong3.health.bodycompositions.BodyCompositionsActivity;
import com.changhongit.guanhutong3.utils.pulldata.Alert;
import com.changhongit.guanhutong3.utils.pulldata.AlertList;
import com.changhongit.guanhutong3.utils.pulldata.BloodOxygen;
import com.changhongit.guanhutong3.utils.pulldata.BloodPressure;
import com.changhongit.guanhutong3.utils.pulldata.BloodSugar;
import com.changhongit.guanhutong3.utils.pulldata.BodyCompositions;
import com.changhongit.guanhutong3.utils.pulldata.Contact;
import com.changhongit.guanhutong3.utils.pulldata.Location;
import com.changhongit.guanhutong3.utils.pulldata.LocationHistoryList;
import com.changhongit.guanhutong3.utils.pulldata.QueryAreas;
import com.changhongit.guanhutong3.utils.pulldata.Reminder;
import com.changhongit.guanhutong3.utils.pulldata.Status;
import com.changhongit.guanhutong3.utils.pulldata.Terminal;
import com.changhongit.guanhutong3.utils.pulldata.TerminalSetting;
import com.changhongit.guanhutong3.utils.pulldata.UserInfo;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminal;
import com.changhongit.guanhutong3.utils.pulldata.UserTerminalList;
import com.changhongit.guanhutong3.utils.pulldata.WhiteListInfo;

/**
 * 已测试
 * 
 */
public class XMLPullUtil {

	public static XMLPullUtil instance = null;

	public static XMLPullUtil getinstance() {
		if (instance == null)
			instance = new XMLPullUtil();
		return instance;
	}

	/**
	 * 读取终端列表
	 * 
	 * @return
	 * 
	 * @throws IOException
	 * @throws XmlPullParserException
	 *             *
	 */
	public UserTerminalList getUserTerminal(String input, String encode)
			throws XmlPullParserException, IOException {

		UserTerminalList terminallist = null;
		UserTerminal Uter = null;

		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, encode);
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT: {// 开始读取时对终端列表初始化
				terminallist = new UserTerminalList();
				break;
			}
			case XmlPullParser.START_TAG: { // 碰到标签后执行

				if (parser.getName().equals("resource")) {

				} else if ("userTerminal".equals(parser.getName())) {
					if (Uter == null)
						Uter = new UserTerminal();
				} else if (parser.getName().equals("imei")) {
					if (Uter == null)
						Uter = new UserTerminal();
					String imei = parser.nextText();
					Uter.setImei(imei);
				} else if (parser.getName().equals("nickName")) {
					if (Uter == null)
						Uter = new UserTerminal();
					String nic = parser.nextText();
					Uter.setTerminal(nic);
					terminallist.additem(Uter); // 加入到列表
					Uter = new UserTerminal();
				} else if ("totalCount".equals(parser.getName())) {
					int tot = Integer.parseInt(parser.nextText());
					terminallist.setTotalcount(tot);
				}
				break;
			}
			case XmlPullParser.END_TAG: {
				// if ("nickName".equals(parser.getName())) {//
				// 一个UserTermnal读取完成后执行
				// terminallist.additem(Uter); // 加入到列表
				// Uter = null; // 将以加入列表的对象置空
				// new UserTerminal();
				// }
				break;
			}
			default:
				break;
			}
			eventType = parser.next();
		}
		return terminallist;// 返回列表
	}

	/**
	 * 读取位置
	 * 
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 * @throws ParseException
	 */
	public Location getLocation(String input, String encode)
			throws XmlPullParserException, IOException, ParseException {

		Location loc = new Location();

		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, encode);
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_TAG: {
				if ("terminalImei".equals(parser.getName())) {
					String imei = parser.nextText();
					loc.setIemi(imei);
				} else if ("latitude".equals(parser.getName())) {
					String lat = parser.nextText();
					loc.setLatitude(lat);
				} else if ("longitude".equals(parser.getName())) {
					String lon = parser.nextText();
					loc.setLongitude(lon);
				} else if ("username".equals(parser.getName())) {
					String user = parser.nextText();
					loc.setUsername(user);
				} else if ("locationTime".equals(parser.getName())) {
					String loctime = parser.nextText();
					loc.setLocationtime(loctime);
				}
				break;
			}
			default:
				break;
			}
			eventType = parser.next();
		}
		return loc;
	}

	/**
	 * 读取终端状态
	 * 
	 * @return
	 * 
	 * @throws XmlPullParserException
	 * @throws IOException
	 * @throws ParseException
	 */
	public Status getStatus(String input, String encode)
			throws XmlPullParserException, IOException, ParseException {

		Status sta = new Status();

		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, encode);
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_TAG: {
				if ("imei".equals(parser.getName())) {
					String imei = parser.nextText();
					sta.setImei(imei);
				} else if ("validTime".equals(parser.getName())) {
					String val = parser.nextText();
					sta.setValidTime(val);
				} else if ("state".equals(parser.getName())) {
					String stat = parser.nextText();
					if (stat.equals("正常")) {
						sta.setState(Status.STATE_ONLINE);
					} else if (stat.equals("离线")) {
						sta.setState(Status.STATE_OFFLINE);
					} else if (stat.equals("欠费")) {
						sta.setState(Status.STATE_ARREARAGE);
					} else if (stat.equals("该缴费")) {
						sta.setState(Status.STATE_mARREARAGE);
					}
				} else if ("cash".equals(parser.getName())) {
					String cash = parser.nextText();
					if (cash.equals("1")) {
						sta.setCash(true);
					} else if (cash.equals("0")) {
						sta.setCash(false);
					}
				} else if ("gift".equals(parser.getName())) {
					String gift = parser.nextText();
					if (gift.equals("1")) {
						sta.setGift(true);
					} else if (gift.equals("0")) {
						sta.setGift(false);
					}
				} else if ("power".equals(parser.getName())) {
					String power = parser.nextText();
					sta.setPower(power);
				} else if ("listenset".equals(parser.getName())) {
					String lis = parser.nextText();
					if (lis.equals("1")) {
						sta.setListenset(true);
					} else if (lis.equals("0")) {
						sta.setListenset(false);
					}
				} else if ("autolisten".equals(parser.getName())) {
					String aut = parser.nextText();
					if (aut.equals("1")) {
						sta.setAutolisten(true);
					} else if (aut.equals("0")) {
						sta.setAutolisten(false);
					}
				}
				break;
			}
			default:
				break;
			}
			eventType = parser.next();
		}
		return sta;
	}

	/**
	 * 读取防护圈
	 * 
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @throws NumberFormatException
	 */
	public QueryAreas getQueryAreas(String input, String encode)
			throws NumberFormatException, XmlPullParserException, IOException {

		QueryAreas area = null;

		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, encode);
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_TAG: {
				if ("id".equals(parser.getName())) {
					String id = parser.nextText();
					area.setId(id);
				} else if ("devicename".equals(parser.getName())) {
					String dev = parser.nextText();
					area.setDeviceName(dev);
				} else if ("areaType".equals(parser.getName())) {
					String type = parser.nextText();
					if (type.equals("1"))
						area.setAreaType(true);
					else if (type.equals("0"))
						area.setAreaType(false);
				} else if ("XCoord".equals(parser.getName())) {
					String xc = parser.nextText();
					area.setXcoord(xc);
				} else if ("YCoord".equals(parser.getName())) {
					String yc = parser.nextText();
					area.setYcoord(yc);
				} else if ("radius".equals(parser.getName())) {
					String rad = parser.nextText();
					area.setRadius(rad);
				} else if ("leftXcoord".equals(parser.getName())) {
					String lefx = parser.nextText();
					area.setLeftXcoord(lefx);
				} else if ("leftYcoord".equals(parser.getName())) {
					String lefy = parser.nextText();
					area.setLeftYcoord(lefy);
				} else if ("rightXcoord".equals(parser.getName())) {
					String rigx = parser.nextText();
					area.setRightXcoord(rigx);
				} else if ("rightYcoord".equals(parser.getName())) {
					String rigy = parser.nextText();
					area.setRightYCoord(rigy);
				} else if ("contactName".equals(parser.getName())) {
					String con = parser.nextText();
					area.setContactName(con);
				} else if ("index".equals(parser.getName())) {
					int ind = Integer.parseInt(parser.nextText());
					area.setIndex(ind);
				} else if ("totalCount".equals(parser.getName())) { // 这里弄错了，以前是nextText,导致解析抛出异常
					int tot = Integer.parseInt(parser.nextText());
					area.setTotalcount(tot);
				} else if ("secarea".equals(parser.getName())) { // 这里应该解析标签，多个防护圈的时候，添加列表
					area = new QueryAreas();
				}
				break;
			}
			case XmlPullParser.END_TAG:
				break;
			default:
				break;
			}
			eventType = parser.next();
		}
		return area;
	}
	
	
	
	/**
	 * 读取防护圈
	 * 
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @throws NumberFormatException
	 */
	public ArrayList<QueryAreas> getListQueryAreas(String input, String encode)
			throws NumberFormatException, XmlPullParserException, IOException {

		ArrayList<QueryAreas> areas = null;
		QueryAreas area = null;
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, encode);
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				areas = new ArrayList<QueryAreas>();
				break;
			case XmlPullParser.START_TAG: {
				if ("id".equals(parser.getName())) {
					String id = parser.nextText();
					area.setId(id);
				} else if ("devicename".equals(parser.getName())) {
					String dev = parser.nextText();
					area.setDeviceName(dev);
				} else if ("areaType".equals(parser.getName())) {
					String type = parser.nextText();
					if (type.equals("1"))
						area.setAreaType(true);
					else if (type.equals("0"))
						area.setAreaType(false);
				} else if ("XCoord".equals(parser.getName())) {
					String xc = parser.nextText();
					area.setXcoord(xc);
				} else if ("YCoord".equals(parser.getName())) {
					String yc = parser.nextText();
					area.setYcoord(yc);
				} else if ("radius".equals(parser.getName())) {
					String rad = parser.nextText();
					area.setRadius(rad);
				} else if ("leftXcoord".equals(parser.getName())) {
					String lefx = parser.nextText();
					area.setLeftXcoord(lefx);
				} else if ("leftYcoord".equals(parser.getName())) {
					String lefy = parser.nextText();
					area.setLeftYcoord(lefy);
				} else if ("rightXcoord".equals(parser.getName())) {
					String rigx = parser.nextText();
					area.setRightXcoord(rigx);
				} else if ("rightYcoord".equals(parser.getName())) {
					String rigy = parser.nextText();
					area.setRightYCoord(rigy);
				} else if ("contactName".equals(parser.getName())) {
					String con = parser.nextText();
					area.setContactName(con);
				} else if ("index".equals(parser.getName())) {
					int ind = Integer.parseInt(parser.nextText());
					area.setIndex(ind);
				} else if ("totalCount".equals(parser.getName())) { // 这里弄错了，以前是nextText,导致解析抛出异常
					int tot = Integer.parseInt(parser.nextText());
					area.setTotalcount(tot);
				} else if ("secarea".equals(parser.getName())) { // 这里应该解析标签，多个防护圈的时候，添加列表
					area = new QueryAreas();
				}
				break;
			}
			case XmlPullParser.END_TAG:
				if("secarea".equals(parser.getName())){
					areas.add(area);
				}
				break;
			default:
				break;
			}
			eventType = parser.next();
		}
		return areas;
	}

	public LocationHistoryList getLocationHistoryList(String input,
			String encode) throws XmlPullParserException, IOException,
			ParseException {

		LocationHistoryList list = null;

		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, encode);
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT: {// 开始读取时对终端列表初始化
				list = new LocationHistoryList();
				break;
			}
			case XmlPullParser.START_TAG: { // 碰到标签后执行
				if ("poi".equals(parser.getName())) {
					Location loc = new Location();
					String info = parser.nextText();
					loc.setLocationInfo(info);
					list.additem(loc); // 加入到列表
				}
				if ("totalCount".equals(parser.getName())) {
					int cou = Integer.parseInt(parser.nextText());
					list.settotalcount(cou);
				}
				break;
			}
			default:
				break;
			}
			eventType = parser.next();
		}
		return list;

	}

	public String parseLogin(String stringXML) throws Exception {

		String loginUserid = null;
		InputStream is = null;
		is = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");

		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:

				if (parser.getName().equals("resource")) {

				} else if (parser.getName().equals("userId")) {
					loginUserid = parser.nextText();
					// System.out.println("----------soleId:"+parser.nextText());
				}
				break;

			case XmlPullParser.END_TAG:

				break;
			}
			eventType = parser.next();
		}
		return loginUserid;
	}

	public List<WhiteListInfo> parserWhiteList(String stringXML)
			throws Exception {
		List<WhiteListInfo> datas = null;
		WhiteListInfo whiteList = null;
		InputStream is = null;
		is = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				datas = new ArrayList<WhiteListInfo>();
				break;

			case XmlPullParser.START_TAG:

				if (parser.getName().equals("whiteItem")) {

					whiteList = new WhiteListInfo();

				} else if (parser.getName().equals("id")) {
					whiteList.setId(parser.nextText());
					// System.out.println("----------soleId:"+parser.nextText());
				} else if (parser.getName().equals("phone")) {
					whiteList.setPhone(parser.nextText());
					// System.out.println("----------soleId:"+parser.nextText());
				} else if (parser.getName().equals("name")) {
					whiteList.setName(parser.nextText());
					// System.out.println("----------soleId:"+parser.nextText());
				}

				break;

			case XmlPullParser.END_TAG:
				if (parser.getName().equals("whiteItem")) {
					datas.add(whiteList);
					whiteList = null;
				}
				break;
			}
			eventType = parser.next();
		}
		return datas;
	}

	public TerminalSetting parserTerminalSetting(String stringXML)
			throws Exception {

		TerminalSetting setting = new TerminalSetting();

		InputStream inputStream = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {

			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("autoReceive")) {
					String tmp = parser.nextText();
					if (tmp.equals("打开"))
						setting.setAuto(true);
					else if (tmp.equals("关闭"))
						setting.setAuto(false);
					break;
				} else if (parser.getName().equals("answerScope")) {
					String tmp = parser.nextText();
					if (tmp.equals("白名单电话"))
						setting.setReceiveAll(false);
					else if (tmp.equals("所有电话"))
						setting.setReceiveAll(true);
					break;
				} else if (parser.getName().equals("imei")) {
					setting.setImei(parser.nextText());
					break;
				}
			default:
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return setting;
	}

	public ArrayList<Contact> parserContactList(String stringXML)
			throws Exception {

		ArrayList<Contact> datas = null;
		Contact tmp = null;

		InputStream inputStream = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				datas = new ArrayList<Contact>();
				break;

			case XmlPullParser.START_TAG:

				if (parser.getName().equals("contact")) {
					tmp = new Contact();
					break;
				} else if (parser.getName().equals("phone")) {
					tmp.setPhone(parser.nextText());
					break;
					// System.out.println("----------soleId:"+parser.nextText());
				} else if (parser.getName().equals("name")) {
					tmp.setName(parser.nextText());
					break;
					// System.out.println("----------soleId:"+parser.nextText());
				}
			default:
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("contact")) {
					datas.add(tmp);
					tmp = null;
				}
				break;
			}
			eventType = parser.next();
		}
		return datas;
	}

	public AlertList parseAlerts(String stringXML)
			throws XmlPullParserException, IOException {

		AlertList list = new AlertList();
		Alert temp = null;

		InputStream inputStream = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:
				if (parser.getName().equals("alert")) {
					temp = new Alert();
					break;
				} else if (parser.getName().equals("id")) {
					temp.setId(parser.nextText());
					break;
				} else if (parser.getName().equals("imei")) {
					temp.setImei(parser.nextText());
					break;
				} else if (parser.getName().equals("devicename")) {
					temp.setDevicename(parser.nextText());
					break;
				} else if (parser.getName().equals("lat")) {
					temp.setLatitude(parser.nextText());
					break;
				} else if (parser.getName().equals("lon")) {
					temp.setLongitude(parser.nextText());
					break;
				} else if (parser.getName().equals("type")) {
					temp.setType(parser.nextText());
					break;
				} else if (parser.getName().equals("time")) {
					temp.setTime(parser.nextText());
					break;
				} else if (parser.getName().equals("totalCount")) {
					list.setTotalCount(Integer.parseInt(parser.nextText()));
					break;
				}

			case XmlPullParser.END_TAG:
				if (parser.getName().equals("alert")) {
					list.addAlert(temp);
					temp = null;
				}
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

	public List<Reminder> parseReminderList(String stringXML)
			throws XmlPullParserException, IOException {

		ArrayList<Reminder> list = new ArrayList<Reminder>();
		Reminder temp = null;

		InputStream inputStream = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:
				if (parser.getName().equals("reminder")) {
					temp = new Reminder();
					String sourceUrl = parser.getAttributeValue("", "href");
					String[] source = sourceUrl.split("=");
					temp.setId(source[1]);
					break;
				} else if (parser.getName().equals("indexNum")) {
					temp.setIndexNum(parser.nextText());
					break;
				} else if (parser.getName().equals("reminderStyle")) {
					String type = parser.nextText();
					if (type.equals("每天"))
						temp.setReminderType(Reminder.DAY);
					else if (type.equals("仅一次"))
						temp.setReminderType(Reminder.ONE);
					else if (type.equals("星期几（选择周几）"))
						temp.setReminderType(Reminder.WEEK);
					break;
				} else if (parser.getName().equals("reminderTime")) {
					temp.setReminderTime(parser.nextText());
					break;
				}

			case XmlPullParser.END_TAG:
				if (parser.getName().equals("reminder")) {
					list.add(temp);
					temp = null;
				}

			}
			eventType = parser.next();
		}
		return list;
	}

	public Reminder parseReminder(String stringXML)
			throws XmlPullParserException, IOException {

		Reminder reminder = new Reminder();

		InputStream inputStream = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:
				if (parser.getName().equals("imei")) {
					reminder.setImei(parser.nextText());
					break;
				} else if (parser.getName().equals("reminderType")) {
					String tmp = parser.nextText();
					if (tmp.equals("one"))
						reminder.setReminderType(Reminder.ONE);
					else if (tmp.equals("day"))
						reminder.setReminderType(Reminder.DAY);
					else if (tmp.equals("week"))
						reminder.setReminderType(Reminder.WEEK);
					break;
				} else if (parser.getName().equals("switchFlag")) {
					String tmp = parser.nextText();
					if (tmp.equals("开"))
						reminder.setSwichFlag(true);
					else if (tmp.equals("关"))
						reminder.setSwichFlag(false);
					break;
				} else if (parser.getName().equals("reminderTime")) {
					reminder.setReminderTime(parser.nextText());
					break;
				} else if (parser.getName().equals("chooseWeek")) {
					String tmp = parser.nextText();
					boolean[] bools = new boolean[7];
					if (tmp.equals("null")) {
						reminder.setWeek(bools);
					} else {
						String[] weeks = tmp.split(",");
						for (int i = 0; i < weeks.length; i++) {
							if (weeks[i].equals("0")) {
								bools[0] = true;
							} else if (weeks[i].equals("1")) {
								bools[1] = true;
							} else if (weeks[i].equals("2")) {
								bools[2] = true;
							} else if (weeks[i].equals("3")) {
								bools[3] = true;
							} else if (weeks[i].equals("4")) {
								bools[4] = true;
							} else if (weeks[i].equals("5")) {
								bools[5] = true;
							} else if (weeks[i].equals("6")) {
								bools[6] = true;
							}
						}
						reminder.setWeek(bools);
					}

					break;
				} else if (parser.getName().equals("content")) {
					reminder.setContent(parser.nextText());
					break;
				}

			case XmlPullParser.END_DOCUMENT:
				break;

			}
			eventType = parser.next();
		}
		return reminder;
	}

	public UserInfo parseUserInfo(String stringXML)
			throws XmlPullParserException, IOException {

		UserInfo info = new UserInfo();

		InputStream inputStream = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:
				if (parser.getName().equals("usercode")) {
					info.setUserCode(parser.nextText());
					break;
				} else if (parser.getName().equals("username")) {
					info.setUserName(parser.nextText());
					break;
				} else if (parser.getName().equals("gender")) {
					String tmp = parser.nextText();
					if (tmp.equals("1"))
						info.setisMale(true);
					else if (tmp.equals("0"))
						info.setisMale(false);
					break;
				} else if (parser.getName().equals("email")) {
					info.setEMail(parser.nextText());
					break;
				} else if (parser.getName().equals("address")) {
					info.setAddress(parser.nextText());
					break;
				} else if (parser.getName().equals("birthday")) {

					String tmp = parser.nextText();
					if (tmp != null && !"".equals(tmp)) {
						String[] tmps = tmp.split(" ");
						if (tmps.length >= 2) {
							info.setBirthday(tmps[0]);
						} else {
							info.setBirthday(tmp);
						}
					} else {
						info.setBirthday("");
					}
					break;
				} else if (parser.getName().equals("phone")) {
					info.setPhone(parser.nextText());
					break;
				}

			case XmlPullParser.END_DOCUMENT:
				break;

			}
			eventType = parser.next();
		}
		return info;
	}

	public Terminal parseTerminal(String stringXML)
			throws XmlPullParserException, IOException {

		Terminal terminal = new Terminal();

		InputStream inputStream = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:
				if (parser.getName().equals("id")) {
					terminal.setId(parser.nextText());
					break;
				} else if (parser.getName().equals("userId")) {
					terminal.setUserId(parser.nextText());
					break;
				} else if (parser.getName().equals("imei")) {
					terminal.setImei(parser.nextText());
					break;
				} else if (parser.getName().equals("sn")) {
					terminal.setSn(parser.nextText());
					break;
				} else if (parser.getName().equals("code")) {
					terminal.setCode(parser.nextText());
					break;
				} else if (parser.getName().equals("username")) {
					terminal.setName(parser.nextText());
					break;
				} else if (parser.getName().equals("nickname")) {
					terminal.setNickname(parser.nextText());
					break;
				} else if (parser.getName().equals("sex")) {
					String tmp = parser.nextText();
					if (tmp.equals("0"))
						terminal.setIsMale(true);
					else if (tmp.equals("1"))
						terminal.setIsMale(false);
					break;
				} else if (parser.getName().equals("birthday")) {
					String tmp = parser.nextText();
					String[] tmps = tmp.split(" ");
					if (tmps != null && tmps.length >= 2) {
						terminal.setBirthday(tmps[0]);
					} else {
						terminal.setBirthday(tmp);
					}

					break;
				} else if (parser.getName().equals("height")) {
					terminal.setHeight(parser.nextText());
					break;
				} else if (parser.getName().equals("weight")) {
					terminal.setWeight(parser.nextText());
					break;
				} else if (parser.getName().equals("bloodType")) {
					String bloodtype = parser.nextText();
					if (bloodtype.equals("null"))
						terminal.setBlood(0);
					else
						terminal.setBlood(Integer.parseInt(bloodtype));
					// 设置血型，对应关系见terminal类
					break;
				} else if (parser.getName().equals("sbp")) {
					terminal.setBPHigh(parser.nextText());
					break;
				} else if (parser.getName().equals("dbp")) {
					terminal.setBPLow(parser.nextText());
					break;
				} else if (parser.getName().equals("grugallergy")) {
					String tmpstr = parser.nextText();
					if ("null".equals(tmpstr)) {
						terminal.setAllergy("无");
					} else {
						terminal.setAllergy(tmpstr);
					}
					break;
				} else if (parser.getName().equals("health")) {
					String tmpstr = parser.nextText();
					if ("null".equals(tmpstr)) {
						terminal.setMedicalHistory("无");
					} else {
						terminal.setMedicalHistory(tmpstr);
					}
					break;
				} else if (parser.getName().equals("remark")) {
					String tmpstr = parser.nextText();
					if ("null".equals(tmpstr)) {
						terminal.setRemark("无");
					} else {
						terminal.setRemark(tmpstr);
					}
					break;
				}
			case XmlPullParser.END_DOCUMENT:
				break;

			}
			eventType = parser.next();
		}
		return terminal;
	}

	public ArrayList<Terminal> parseTerminalList(String stringXML)
			throws XmlPullParserException, IOException {

		ArrayList<Terminal> list = new ArrayList<Terminal>();
		Terminal tmp = null;

		InputStream inputStream = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:
				if (parser.getName().equals("imei")) {
					tmp = new Terminal();
					tmp.setImei(parser.nextText());
					break;
				} else if (parser.getName().equals("nickName")) {
					tmp.setNickname(parser.nextText());
					list.add(tmp);
					tmp = null;
					break;
				}
			case XmlPullParser.END_DOCUMENT:
				break;

			}
			eventType = parser.next();
		}
		return list;
	}

	public List<BloodOxygen> parseBloodOxygen(String stringXML)
			throws XmlPullParserException, IOException {

		ArrayList<BloodOxygen> list = new ArrayList<BloodOxygen>();
		BloodOxygen tmp = null;

		InputStream inputStream = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:
				if (parser.getName().equals("bloodOxygen")) {
					tmp = new BloodOxygen();
					break;
				} else if (parser.getName().equals("imei")) {
					tmp.setImei(parser.nextText());
					break;
				} else if (parser.getName().equals("dector")) {
					tmp.setDector(parser.nextText());
					break;
				} else if (parser.getName().equals("oxygenSaturation")) {
					tmp.setOxygenSaturation(parser.nextText());
					break;
				} else if (parser.getName().equals("pulse")) {
					tmp.setPulse(parser.nextText());
					break;
				} else if (parser.getName().equals("timestamp")) {
					tmp.setTiemStamp(parser.nextText());
					break;
				} else if (parser.getName().equals("adviceStatus")) {
					tmp.setAdviceStatus(parser.nextText());
					break;
				} else if (parser.getName().equals("advice")) {
					String i = parser.getAttributeValue("", "name");
					if (i.equals("result"))
						tmp.setAdviceResult(parser.nextText());
					else if (i.equals("food"))
						tmp.setAdviceFood(parser.nextText());
					else if (i.equals("sport"))
						tmp.setAdviceDoctor(parser.nextText());
					else if (i.equals("doctor"))
						tmp.setAdviceDoctor(parser.nextText());
					else if (i.equals("daily"))
						tmp.setAdviceDaily(parser.nextText());
					break;
				}
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("bloodOxygen")) {
					list.add(tmp);
					tmp = null;
				}
			case XmlPullParser.END_DOCUMENT:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

	public List<BloodPressure> parseBloodPressure(String stringXML)
			throws XmlPullParserException, IOException {

		ArrayList<BloodPressure> list = new ArrayList<BloodPressure>();
		BloodPressure tmp = null;

		InputStream inputStream = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:
				if (parser.getName().equals("bloodPressure")) {
					tmp = new BloodPressure();
					break;
				} else if (parser.getName().equals("imei")) {
					tmp.setImei(parser.nextText());
					break;
				} else if (parser.getName().equals("dector")) {
					tmp.setDector(parser.nextText());
					break;
				} else if (parser.getName().equals("systolic")) {
					tmp.setSystolic(parser.nextText());
					break;
				} else if (parser.getName().equals("diastolic")) {
					tmp.setDiastolic(parser.nextText());
					break;
				} else if (parser.getName().equals("pulse")) {
					tmp.setPulse(parser.nextText());
					break;
				} else if (parser.getName().equals("timestamp")) {
					tmp.setTimeStamp(parser.nextText());
					break;
				} else if (parser.getName().equals("adviceStatus")) {
					tmp.setAdviceStatus(parser.nextText());
					break;
				} else if (parser.getName().equals("advice")) {
					String i = parser.getAttributeValue("", "name");
					if (i.equals("result"))
						tmp.setAdviceResult(parser.nextText());
					else if (i.equals("food"))
						tmp.setAdviceFood(parser.nextText());
					else if (i.equals("sport"))
						tmp.setAdviceDoctor(parser.nextText());
					else if (i.equals("doctor"))
						tmp.setAdviceDoctor(parser.nextText());
					else if (i.equals("daily"))
						tmp.setAdviceDaily(parser.nextText());
					break;
				}
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("bloodPressure")) {
					list.add(tmp);
					tmp = null;
				}
			case XmlPullParser.END_DOCUMENT:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

	public List<BodyCompositions> parseBodyComposition(String stringXML)
			throws XmlPullParserException, IOException {

		ArrayList<BodyCompositions> list = new ArrayList<BodyCompositions>();
		BodyCompositions tmp = null;

		InputStream inputStream = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:
				if (parser.getName().equals("bodyComposition")) {
					tmp = new BodyCompositions();
					break;
				} else if (parser.getName().equals("imei")) {
					tmp.setImei(parser.nextText());
					break;
				} else if (parser.getName().equals("dector")) {
					tmp.setDector(parser.nextText());
					break;
				} else if (parser.getName().equals("muscle")) {
					tmp.setMuscle(parser.nextText());
					break;
				} else if (parser.getName().equals("adiposerate")) {
					tmp.setAdiposerate(parser.nextText());
					break;
				} else if (parser.getName().equals("visceralfat")) {
					tmp.setVisceralfat(parser.nextText());
					break;
				} else if (parser.getName().equals("timestamp")) {
					tmp.setTimeStamp(parser.nextText());
					break;
				} else if (parser.getName().equals("adviceStatus")) {
					tmp.setAdviceStatus(parser.nextText());
					break;
				} else if (parser.getName().equals("advice")) {
					String i = parser.getAttributeValue("", "name");
					if (i.equals("result"))
						tmp.setAdviceResult(parser.nextText());
					else if (i.equals("food"))
						tmp.setAdviceFood(parser.nextText());
					else if (i.equals("sport"))
						tmp.setAdviceDoctor(parser.nextText());
					else if (i.equals("doctor"))
						tmp.setAdviceDoctor(parser.nextText());
					else if (i.equals("daily"))
						tmp.setAdviceDaily(parser.nextText());
					break;
				}
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("bodyComposition")) {
					list.add(tmp);
					tmp = null;
				}
			case XmlPullParser.END_DOCUMENT:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

	public List<BloodSugar> parseBloodSugar(String stringXML)
			throws XmlPullParserException, IOException {

		ArrayList<BloodSugar> list = new ArrayList<BloodSugar>();
		BloodSugar tmp = null;

		InputStream inputStream = new ByteArrayInputStream(stringXML.getBytes());
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(inputStream, "utf-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:
				if (parser.getName().equals("bloodSugar")) {
					tmp = new BloodSugar();
					break;
				} else if (parser.getName().equals("imei")) {
					tmp.setImei(parser.nextText());
					break;
				} else if (parser.getName().equals("dector")) {
					tmp.setDector(parser.nextText());
					break;
				} else if (parser.getName().equals("FPG")) {
					tmp.setFPG(parser.nextText());
					break;
				} else if (parser.getName().equals("timestamp")) {
					tmp.setTimestamp(parser.nextText());
					break;
				} else if (parser.getName().equals("adviceStatus")) {
					tmp.setAdviceStatus(parser.nextText());
					break;
				} else if (parser.getName().equals("advice")) {
					String i = parser.getAttributeValue("", "name");
					if (i.equals("result"))
						tmp.setAdviceResult(parser.nextText());
					else if (i.equals("food"))
						tmp.setAdviceFood(parser.nextText());
					else if (i.equals("sport"))
						tmp.setAdviceDoctor(parser.nextText());
					else if (i.equals("doctor"))
						tmp.setAdviceDoctor(parser.nextText());
					else if (i.equals("daily"))
						tmp.setAdviceDaily(parser.nextText());
					break;
				}
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("bloodSugar")) {
					list.add(tmp);
					tmp = null;
				}
			case XmlPullParser.END_DOCUMENT:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}

}
