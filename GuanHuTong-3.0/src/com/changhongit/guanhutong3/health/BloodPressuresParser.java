package com.changhongit.guanhutong3.health;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;


public class BloodPressuresParser {

	public List<BloodPressuresInfo> parseBloodPressuresList(InputStream is) throws Exception {
		List<BloodPressuresInfo> pressurelist = null;
		BloodPressuresInfo info = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");

		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				pressurelist = new ArrayList<BloodPressuresInfo>();
				break;

			case XmlPullParser.START_TAG:

				if (parser.getName().equals("bloodPressure")) {

					info = new BloodPressuresInfo();
					info.setDelete_url(parser.getAttributeValue(0));

				} else if (parser.getName().equals("imei")) {
					info.setImei(parser.nextText());
					// System.out.println("----------soleId:"+parser.nextText());
				} else if (parser.getName().equals("pulse")) {
					info.setPulse(Float.valueOf(parser.nextText()));
					// System.out.println("----------name:"+parser.nextText());

				}  else if (parser.getName().equals("systolic")) {
					info.setSystolic(parser.nextText());
					// System.out.println("----------name:"+parser.nextText());

				}  else if (parser.getName().equals("diastolic")) {
					info.setDiastolic(parser.nextText());
					// System.out.println("----------name:"+parser.nextText());

				} else if (parser.getName().equals("dector")) {
					info.setDectorId(parser.getAttributeValue(0));
					info.setDectorType(parser.getAttributeValue(1));
					info.setDectorCotent(parser.nextText());
					// System.out.println("----------description:"+parser.nextText());
				} else if (parser.getName().equals("timestamp")) {
					String str = parser.nextText().replace("T", " ");
					int index = str.lastIndexOf('.');
					str = str.substring(0, index);
					info.setTimestamp(str);
				} else if (parser.getName().equals("adviceStatus")) {
					info.setAdviceCode(parser.getAttributeValue(0));
					info.setAdviceSource(parser.nextText());
				} else if (parser.getName().equals("adviceSource ")) {
					info.setAdviceSource(parser.nextText());
				}else if (parser.getName().equals("advice")) {
					String str = parser.getAttributeValue(0);
					if (str.equals("result")){
						info.setAdvice_name_result(parser.nextText());
					}else if(str.equals("food")){
						info.setAdvice_name_food(parser.nextText());
					}else if(str.equals("sport")){
						info.setAdvice_name_sport(parser.nextText());
					}else if(str.equals("doctor")){
						info.setAdvice_name_doctor(parser.nextText());
					}else if(str.equals("daily")){
						info.setAdvice_name_daily(parser.nextText());
					}
					
				}
				break;

			case XmlPullParser.END_TAG:
				if (parser.getName().equals("bloodPressure")) {
					pressurelist.add(info);
					info = null;
				}
				break;
			}
			eventType = parser.next();
		}
		return pressurelist;
	}
	
	public BloodPressuresInfo parseBloodPressuresInfo(InputStream is) throws Exception {
		BloodPressuresInfo info = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");

		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:

				if (parser.getName().equals("bloodPressure")) {

					info = new BloodPressuresInfo();

				} else if (parser.getName().equals("imei")) {
					info.setImei(parser.nextText());
					// System.out.println("----------soleId:"+parser.nextText());
				} else if (parser.getName().equals("pulse")) {
					info.setPulse(Float.valueOf(parser.nextText()));
					// System.out.println("----------name:"+parser.nextText());
				} else if (parser.getName().equals("systolic")) {
					info.setSystolic(parser.nextText());
					// System.out.println("----------name:"+parser.nextText());

				}  else if (parser.getName().equals("diastolic")) {
					info.setDiastolic(parser.nextText());
					// System.out.println("----------name:"+parser.nextText());

				} else if (parser.getName().equals("dector")) {
					info.setDectorId(parser.getAttributeValue(0));
					info.setDectorType(parser.getAttributeValue(1));
					info.setDectorCotent(parser.nextText());
					// System.out.println("----------description:"+parser.nextText());
				} else if (parser.getName().equals("timestamp")) {
					String str = parser.nextText().replace("T", " ");
					int index = str.lastIndexOf('.');
					str = str.substring(0, index);
					info.setTimestamp(str);
				} else if (parser.getName().equals("adviceStatus")) {
					info.setAdviceCode(parser.getAttributeValue(0));
					info.setAdviceSource(parser.nextText());
				} else if (parser.getName().equals("adviceSource ")) {
					info.setAdviceSource(parser.nextText());
				}else if (parser.getName().equals("advice")) {
					String str = parser.getAttributeValue(0);
					if (str.equals("result")){
						info.setAdvice_name_result(parser.nextText());
					}else if(str.equals("food")){
						info.setAdvice_name_food(parser.nextText());
					}else if(str.equals("sport")){
						info.setAdvice_name_sport(parser.nextText());
					}else if(str.equals("doctor")){
						info.setAdvice_name_doctor(parser.nextText());
					}else if(str.equals("daily")){
						info.setAdvice_name_daily(parser.nextText());
					}
					
				}
				break;

			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return info;
	}

	
	public List<Double[]> parseBloodPressuresValueList(InputStream is) throws Exception {
		List<Double[]> pressurelist = null;
		Double[] info = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");

		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {

			case XmlPullParser.START_DOCUMENT:
				pressurelist = new ArrayList<Double[]>();
				break;

			case XmlPullParser.START_TAG:

				if (parser.getName().equals("bloodPressure")) {

					info = new Double[2];

				}  else if (parser.getName().equals("systolic")) {
					info[0]=(Double.valueOf(parser.nextText()));
					// System.out.println("----------name:"+parser.nextText());

				}  else if (parser.getName().equals("diastolic")) {
					info[1]=(Double.valueOf(parser.nextText()));
					// System.out.println("----------name:"+parser.nextText());

				}
				break;

			case XmlPullParser.END_TAG:
				if (parser.getName().equals("bloodPressure")) {
					pressurelist.add(info);
					info = null;
				}
				break;
			}
			eventType = parser.next();
		}
		return pressurelist;
	}
}