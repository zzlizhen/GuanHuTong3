package com.changhongit.guanhutong3.map.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.maps2d.AMap.InfoWindowAdapter;
import com.amap.api.maps2d.model.Marker;
import com.changhongit.guanhutong3.R;

public class CustomInfoWindowAdapter implements InfoWindowAdapter {

		private final RadioGroup mOptions;
		private final View mWindow;
		private final View mContents;

		public CustomInfoWindowAdapter(Context context) {
			mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window,
					null);
			mContents =LayoutInflater.from(context).inflate(
					R.layout.custom_info_contents, null);
			mOptions = (RadioGroup)((Activity)context).findViewById(R.id.custom_info_window_options);
		}

		private void render(Marker marker, View view) {
			int badge = 0;

			if (marker.equals(Constants.CHENGDU)) {
//				badge = R.drawable.badge_qld;
			} else if (marker.equals(Constants.XIAN)) {
//				badge = R.drawable.badge_nsw;
			} else {
				badge = 0;
		
	}
			((ImageView) view.findViewById(R.id.badge)).setImageResource(badge);
			String title = marker.getTitle();
			TextView titleUi = ((TextView) view.findViewById(R.id.title));

			if (title != null) {
				SpannableString titleText = new SpannableString(title);
				titleText.setSpan(new ForegroundColorSpan(Color.RED), 0,
						titleText.length(), 0);
				titleUi.setText(titleText);
			} else {
				titleUi.setText("");
			}
			String snippet = marker.getSnippet();
			TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
			if (snippet != null) {
				SpannableString snippetText = new SpannableString(snippet);
				snippetText.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0,
						10, 0);
				snippetText.setSpan(new ForegroundColorSpan(Color.BLUE), 12,
						21, 0);
				snippetUi.setText(snippetText);
			} else {
				snippetUi.setText("");
			}
		}

		@Override
		public View getInfoContents(Marker marker) {
			// TODO Auto-generated method stub
			if (mOptions.getCheckedRadioButtonId() != R.id.custom_info_contents) {
				return null;
			}
			render(marker, mContents);

			return mContents;
		}

		@Override
		public View getInfoWindow(Marker marker) {
			// TODO Auto-generated method stub
			if (mOptions.getCheckedRadioButtonId() != R.id.custom_info_window) {
				return null;
			}
			render(marker, mWindow);
			return mWindow;
		}
	}