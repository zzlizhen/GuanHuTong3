/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.changhongit.guanhutong3.health;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.changhongit.guanhutong3.R;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.View;

/**
 * Average temperature demo chart.
 */
public class Chart extends AbstractDemoChart {
	private Context context;
	
	/**
	 * Returns the chart name.
	 * 
	 * @return the chart name
	 */
	public String getName() {
		return "Average temperature";
	}

	/**
	 * Returns the chart description.
	 * 
	 * @return the chart description
	 */
	public String getDesc() {
		return "The average temperature in 4 Greek islands (line chart)";
	}

	/**
	 * Executes the chart demo.
	 * 
	 * @param context
	 *            the context
	 * @return the built intent
	 */
	public Intent execute(Context context) {
		String[] titles = new String[] { "Crete", "Corfu", "Thassos",
				"Skiathos" };
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		}
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4,
				26.1, 23.6, 20.3, 17.2, 13.9 });
		values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14,
				11 });
		values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
		values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });
		int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN,
				Color.YELLOW };
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE,
				PointStyle.DIAMOND, PointStyle.TRIANGLE, PointStyle.SQUARE };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}
		setChartSettings(renderer, "Average temperature", "Month",
				"Temperature", 0.5, 12.5, -10, 40, Color.LTGRAY, Color.LTGRAY);
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.TRANSPARENT);
		renderer.setXLabels(12);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setZoomButtonsVisible(true);
		renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
		renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });

		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
		XYSeries series = dataset.getSeriesAt(0);
//		series.addAnnotation("Vacation", 6, 30);
		View view = ChartFactory.getLineChartView(context, dataset, renderer);
		view.setBackgroundResource(R.drawable.title_newbg);
		Intent intent = ChartFactory.getLineChartIntent(context, dataset,
				renderer, "Average temperature");
		return intent;
	}
	
	public View execute1(Context context,double[] x_data,double[]... values1) {
		this.context = context;
		String[] titles;
		if (values1.length == 1){
			titles = new String[] { "血氧"/*, "Corfu", "Thassos",
				"Skiathos" */};
		}
		else{
			titles = new String[] { "收缩压", "舒张压"/*, "Thassos",
			"Skiathos" */};
		}
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(x_data);
		}
		List<double[]> values = new ArrayList<double[]>();
		for(double[] item:values1){
		values.add(item);
		}
		int[] colors ;
		PointStyle[] styles;
		if (values1.length == 1){
		colors = new int[] { Color.BLUE/*, Color.GREEN, Color.CYAN,
				Color.YELLOW*/ };
		styles = new PointStyle[] { PointStyle.CIRCLE/*,
				PointStyle.DIAMOND, PointStyle.TRIANGLE, PointStyle.SQUARE*/ };
		}
		else{
			colors = new int[] { Color.BLUE,
			Color.YELLOW };
			styles = new PointStyle[] { PointStyle.CIRCLE,
			PointStyle.DIAMOND/*, PointStyle.TRIANGLE, PointStyle.SQUARE*/ };
		}
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}
		setChartSettings(renderer, "血氧饱和度", "",
				"血氧饱和度:%", 0, 9, 70, 100, Color.BLACK, Color.BLACK);
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.TRANSPARENT);
		renderer.setMarginsColor(Color.WHITE);
		renderer.addYTextLabel(94, "94");
		
		renderer.setXLabels(1);
		renderer.setYLabels(1);
		renderer.setShowGridY(false);
		renderer.setShowGridX(false);
		renderer.setShowCustomTextGrid(true);
		
		renderer.addXTextLabel(1, "12-02\n19:00");
		renderer.addXTextLabel(2, "12-02\n19:00");
		renderer.addXTextLabel(3, "12-02\n19:00");
		renderer.addXTextLabel(4, "12-02\n19:00");
		renderer.addXTextLabel(5, "12-02\n19:00");
		renderer.addXTextLabel(6, "12-02\n19:00");
		renderer.addXTextLabel(7, "12-02\n19:00");
		renderer.addXTextLabel(8, "12-02\n19:00");
		renderer.addXTextLabel(9, "12-02\n19:00");
		
//		renderer.setClickEnabled(true);
//		renderer.setSelectableBuffer(20);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		
		renderer.setZoomButtonsVisible(false);
//		renderer.setZoomEnabled(false);
		renderer.setZoomEnabled(false, false);
		renderer.setPanEnabled(false, false); 
		
/*		renderer.setPanLimits(new double[] { 0, 20, 0, 40 });
		renderer.setZoomLimits(new double[] { 0, 20, 0, 40 });*/
		renderer.setLabelsTextSize(20);

		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
		XYSeries series = dataset.getSeriesAt(0);
//		series.addAnnotation("血糖", 6, 30);
		
		View view = ChartFactory.getLineChartView(context, dataset, renderer);
		view.setBackgroundColor(Color.WHITE);
/*		Intent intent = ChartFactory.getLineChartIntent(context, dataset,
				renderer, "Average temperature");*/
		return view;
	}

	
	public View executeOxygen(Context context,double[] x_data,String[] time,double[]... values1) {
		this.context = context;
		String[] titles= new String[] { "血氧"};
	
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(x_data);
		}
		List<double[]> values = new ArrayList<double[]>();
		for(double[] item:values1){
			values.add(item);
		}
		int[] colors ;
		PointStyle[] styles;
		colors = new int[] { Color.BLUE};    
		styles = new PointStyle[] { PointStyle.CIRCLE };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}
		setChartSettings(renderer, "血氧饱和度", "",
				"血氧饱和度:%", 0, x_data.length, 70, 100, Color.BLACK, Color.BLACK);
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.TRANSPARENT);
		renderer.setMarginsColor(Color.WHITE);
		renderer.addYTextLabel(94, "94");
		
		renderer.setXLabels(1);
		renderer.setYLabels(1);
		renderer.setShowGridY(false);
		renderer.setShowGridX(false);
		renderer.setShowCustomTextGrid(true);
		
		for(int i = 0 ; i<time.length;i++){
			renderer.addXTextLabel(i, time[i]);
		}

		
		renderer.setClickEnabled(true);
		renderer.setSelectableBuffer(20);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		
		renderer.setZoomButtonsVisible(false);
	
		renderer.setZoomEnabled(true, false);
//		renderer.setFitLegend(true);
//		renderer.setZoomInLimitX(10f);
		
		
		
		
	
		
		renderer.setPanLimits(new double[] { 0, 20, 0, 40 });
		renderer.setZoomLimits(new double[] { 0, 20, 0, 40 });
		renderer.setZoomEnabled(true);
		renderer.setZoomRate(3f);
		
		renderer.setLabelsTextSize(20);

		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
//		XYSeries series = dataset.getSeriesAt(0);
		
		View view = ChartFactory.getLineChartView(context, dataset, renderer);
		((GraphicalView)view).zoomIn();
		view.setBackgroundColor(Color.WHITE);
		
		return view;
	}
	
	
	
	public View executeSugar(Context context,double[] x_data,String[] time,double[]... values1) {
		this.context = context;
		String[] titles= new String[] { "血糖"};
	
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(x_data);
		}
		List<double[]> values = new ArrayList<double[]>();
		for(double[] item:values1){
			values.add(item);
		}
		int[] colors ;
		PointStyle[] styles;
		colors = new int[] { Color.BLUE};    
		styles = new PointStyle[] { PointStyle.CIRCLE };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}
		setChartSettings(renderer, "空腹血糖值", "",
				"血糖（mmol/l）", 0, x_data.length, 0, 30, Color.BLACK, Color.BLACK);
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.TRANSPARENT);
		renderer.setMarginsColor(Color.WHITE);
		renderer.addYTextLabel(4.4, "4.4");
		renderer.addYTextLabel(6.2, "6.2");
		renderer.addYTextLabel(7.1, "7.1");
		
		renderer.setXLabels(1);
		renderer.setYLabels(1);
		renderer.setShowGridY(false);
		renderer.setShowGridX(false);
		renderer.setShowCustomTextGrid(true);
		
		for(int i = 0 ; i<time.length;i++){
			renderer.addXTextLabel(i, time[i]);
		}
//		renderer.setClickEnabled(true);
//		renderer.setSelectableBuffer(20);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		
		renderer.setZoomButtonsVisible(false);
	
		renderer.setZoomEnabled(true, false);
//		renderer.setFitLegend(true);
//		renderer.setZoomInLimitX(10f);
		
		renderer.setPanLimits(new double[] { 0, 20, 0, 40 });
		renderer.setZoomLimits(new double[] { 0, 20, 0, 40 });
		renderer.setZoomEnabled(true);
		renderer.setZoomRate(3f);
		
		renderer.setLabelsTextSize(20);

		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
//		XYSeries series = dataset.getSeriesAt(0);
		
		View view = ChartFactory.getLineChartView(context, dataset, renderer);
		((GraphicalView)view).zoomIn();
		view.setBackgroundColor(Color.WHITE);
		
		return view;
	}
	
	public View executePressures(Context context,double[] x_data,String[] time,double[]... values1) {
		this.context = context;
		String[] titles= new String[] { "舒张压","收缩压"};
	
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(x_data);
		}
		List<double[]> values = new ArrayList<double[]>();
		for(double[] item:values1){
			values.add(item);
		}
		int[] colors ;
		PointStyle[] styles;
		colors = new int[] { Color.GREEN,Color.BLUE};    
		styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.CIRCLE};
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}
		setChartSettings(renderer, "", "",
				"血压:(mmHg)", 0, x_data.length, 0, 200, Color.BLACK, Color.BLACK);
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.TRANSPARENT);
		renderer.setMarginsColor(Color.WHITE);
		renderer.addYTextLabel(60, "60");
		renderer.addYTextLabel(90, "90");
		renderer.addYTextLabel(140, "140");
		
		
		renderer.setXLabels(1);
		renderer.setYLabels(1);
		renderer.setShowGridY(false);
		renderer.setShowGridX(false);
		renderer.setShowCustomTextGrid(true);
		
		for(int i = 0 ; i<time.length;i++){
			renderer.addXTextLabel(i, time[i]);
		}

		
//		renderer.setClickEnabled(true);
//		renderer.setSelectableBuffer(20);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		
		renderer.setZoomButtonsVisible(false);
	
		renderer.setZoomEnabled(true, false);
//		renderer.setFitLegend(true);
//		renderer.setZoomInLimitX(10f);
		
		renderer.setPanLimits(new double[] { 0, 20, 0, 40 });
		renderer.setZoomLimits(new double[] { 0, 20, 0, 40 });
		renderer.setZoomEnabled(true);
		renderer.setZoomRate(3f);
		renderer.setLabelsTextSize(20);

		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
		
		
		View view = ChartFactory.getLineChartView(context, dataset, renderer);
		((GraphicalView)view).zoomIn();
		view.setBackgroundColor(Color.WHITE);
		
		return view;
	}

	
	


}
