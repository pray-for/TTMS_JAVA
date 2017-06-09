package xupt.se.ttms.view.statistics;


import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class StatisticsPicture2 {
	ChartPanel frame1;
	public StatisticsPicture2(){
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("月销售金额变化统计", "日期", "金额",xydataset, true, true, true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        frame1=new ChartPanel(jfreechart,true);
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=xyplot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体

	} 
	 private static XYDataset createDataset() {  
		 int i=0;
		 
	        TimeSeries timeseries = new TimeSeries("月销售量",org.jfree.data.time.Month.class);
//	        timeseries.add(new Month(6, 2016), 158800.00000000001D);
	        timeseries.add(new Month(7, 2016), 1483.00000000001D);
	        timeseries.add(new Month(8, 2016), 1539.00000000001D);
	        timeseries.add(new Month(9, 2016), 1426.99999999999D);
	        timeseries.add(new Month(10, 2016), 1232.0D);
	        timeseries.add(new Month(11, 2016), 1318.00000000001D);
	        timeseries.add(new Month(12, 2016), 1395.99999999999D);
	        timeseries.add(new Month(1, 2017), 1429.00000000001D);
	        timeseries.add(new Month(2, 2017), 1818.00000000001D);
	        timeseries.add(new Month(3, 2017), 1673.00000000001D);
	        timeseries.add(new Month(4, 2017), 1538.00000000001D);
	        timeseries.add(new Month(5, 2017), 1675.99999999999D);
	        timeseries.add(new Month(6, 2017), 1588.00000000001D);
	        
	     
	        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
	        timeseriescollection.addSeries(timeseries);
	        return timeseriescollection;
	    }
	  public ChartPanel getChartPanel(){
	    	return frame1;
	    	
	    }
}
